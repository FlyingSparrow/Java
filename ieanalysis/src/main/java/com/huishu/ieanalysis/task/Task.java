package com.huishu.ieanalysis.task;

import com.alibaba.fastjson.JSONObject;
import com.huishu.config.CustomConfig;
import com.huishu.ieanalysis.es.entity.DgapData;
import com.huishu.ieanalysis.es.repository.DgapDataRepository;
import com.huishu.ieanalysis.mysql.entity.KingBaseDgap;
import com.huishu.ieanalysis.mysql.service.KingBaseDgapService;
import com.huishu.ieanalysis.utils.DateUtils;
import com.huishu.ieanalysis.utils.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 任务
 * <p>
 * Title: OpinionWarnTask
 * </p>
 * <p>
 * Description:
 * </p>
 *
 * @author xiaobo
 * @date 2017年4月6日
 */
@Component
public class Task {

    private static Logger logger = LoggerFactory.getLogger(Task.class);

    @Autowired
    private DgapDataRepository dgapDataRepository;
    @Autowired
    private KingBaseDgapService kingBaseDgapService;
    @Autowired
    private CustomConfig customConfig;

    /**
     * 间隔30秒钟执行一次
     */
    @Scheduled(fixedDelay = 1000 * 30)
    public void execute() {
        if (customConfig.isAnalysisMark()) {
            logger.error("开始任务...");
            handleFile(customConfig.getSourceMorePath());
            handleFile(customConfig.getSourceLessPath());
            logger.error("结束任务...");
        }
    }

    private void handleFile(String directory){
        if (StringUtils.isNotEmpty(directory)) {
            File file = new File(directory);
            initFile(file);
            logger.error("目录[{}]开始任务...", directory);
            analysisFile(file);
            logger.error("目录[{}]结束任务...", directory);
        }
    }

    private void analysisFile(File file) {
        if (!file.exists()) {
            return;
        }
        if (!file.isDirectory()) {
            logger.error("文件路径错误,不是文件夹");
            return;
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null || listFiles.length < 1) {
            return;
        }
        logger.error("开始读取文件:" + listFiles.length);

        int len = listFiles.length > customConfig.getAnalysisNum() ? customConfig.getAnalysisNum() : listFiles.length;
        List<DgapData> list = new ArrayList<DgapData>();
        for (int i = 0; i < len; i++) {
            String absolutePath = listFiles[i].getAbsolutePath();
            File tempFile = new File(absolutePath);
            if (!com.huishu.ieanalysis.utils.StringUtils.isWindows()) {
                try {
                    Runtime.getRuntime().exec("chmod 777 " + absolutePath);
                    readAndDeleteFile(tempFile, list);
                } catch (IOException e) {
                    e.printStackTrace();
                    logger.error("文件权限修改失败", e);
                }
            } else {
                readAndDeleteFile(tempFile, list);
            }
        }
    }

    private void readAndDeleteFile(File tempFile, List<DgapData> list) {
        String fileContent = FileUtils.readFile(tempFile.getAbsolutePath());
        DgapData dgapData = JSONObject.parseObject(fileContent, DgapData.class);
        if (dgapData == null) {
            logger.info("空文件" + tempFile.getAbsolutePath());
            return;
        }

        dgapData.setId(null);
        if (dgapData.getDataType() <= 2) {
            boolean existMark = false;
            for (DgapData dgap : list) {
                if (isExists(dgapData, dgap)) {
                    existMark = true;
                    break;
                }
            }
            if (!existMark) {
                if (isExistsInES(dgapData.getPolicyTitle(), dgapData.getPolicyUrl())) {
                    tempFile.delete();
                } else {
                    String content = dgapData.getContent();
                    if (StringUtils.isNotEmpty(content)) {
                        if(content.length() > 300){
                            content = content.substring(0, 300);
                        }
                        dgapData.setContent(content);
                    }
                    list.add(dgapData);
                    dgapDataRepository.save(dgapData);
                    saveToKingbase(dgapData);
                    copyAndDeleteFile(tempFile);
                }
            } else {
                tempFile.delete();
            }
        } else {
            dgapDataRepository.save(dgapData);
            saveToKingbase(dgapData);
            copyAndDeleteFile(tempFile);
        }
    }

    private boolean isExists(DgapData dgapData, DgapData dgap){
        boolean flag = false;

        boolean titleFlag = true;
        if (StringUtils.isNotEmpty(dgapData.getPolicyTitle()) && StringUtils.isNotEmpty(dgap.getPolicyTitle())
                && !dgapData.getPolicyTitle().equals(dgap.getPolicyTitle())) {
            titleFlag = false;
        }
        boolean urlFlag = true;
        if (StringUtils.isNotEmpty(dgapData.getPolicyUrl()) && StringUtils.isNotEmpty(dgap.getPolicyUrl())
                && !dgapData.getPolicyUrl().equals(dgap.getPolicyUrl())) {
            urlFlag = false;
        }
        if (titleFlag && urlFlag) {
            flag = true;
        }

        return flag;
    }

    /**
     * 根据标题和URL判断在ES中是否存在同样的数据
     * @param policyTitle
     * @param policyUrl
     * @return
     */
    private boolean isExistsInES(String policyTitle, String policyUrl){
        BoolQueryBuilder bool = QueryBuilders.boolQuery();
        if (StringUtils.isNotEmpty(policyTitle)) {
            bool.must(QueryBuilders.termQuery("policyTitle", policyTitle));
        }
        if (StringUtils.isNotEmpty(policyUrl)) {
            bool.must(QueryBuilders.termQuery("policyUrl", policyUrl));
        }
        Iterable<DgapData> search = dgapDataRepository.search(bool, new PageRequest(0, 2));

        return (search != null && search.iterator().hasNext());
    }

    /**
     * 复制并删除临时文件
     * @param tempFile
     */
    private void copyAndDeleteFile(File tempFile) {
        String targetPath = customConfig.getTargetPath() + File.separator + DateUtils.getDateFilePath(new Date());
        File targetFile = new File(targetPath);
        initFile(targetFile);
        String sourceFilePath = tempFile.getAbsolutePath();
        String targetFilePath = targetPath + File.separator + tempFile.getName();
        if (FileUtils.copeFile(sourceFilePath, targetFilePath)) {
            tempFile.delete();
        }
    }

    private void initFile(File file){
        if (!file.exists()) {
            file.setWritable(true, false);
            file.setReadable(true, false);
            file.mkdirs();
        }
    }

    /**
     * 保存数据到人大金仓数据库
     * @param source
     */
    private void saveToKingbase(DgapData source) {
        KingBaseDgap target = new KingBaseDgap();
        BeanUtils.copyProperties(source, target);
        target.setId(com.huishu.ieanalysis.utils.StringUtils.getUUID());
        kingBaseDgapService.save(target);
    }
}
