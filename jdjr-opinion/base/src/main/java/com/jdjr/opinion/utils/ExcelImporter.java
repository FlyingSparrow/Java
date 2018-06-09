package com.jdjr.opinion.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;

/**
 * <p>
 * Title: ExcelImporter
 * </p>
 * <p>
 * Description: excel导入工具类
 * </p>
 *
 * @author wjc
 * @date 2017年1月16日
 */
public class ExcelImporter {

    private String fileName;

    public ExcelImporter(String fileName) {
        this.fileName = fileName;
    }

    public Workbook getWorkbook(InputStream fis) throws IOException {
        Workbook book = null;
        if (isExcel2003()) {
            book = new HSSFWorkbook(fis);
        } else if (isExcel2007()) {
            book = new XSSFWorkbook(fis);
        }
        return book;
    }

    /**
     * <p>Description: 根据文件名判断是否是2007格式的excel</p>
     *
     * @return
     * @author wjc
     * @date 2017年1月17日
     */
    private boolean isExcel2007() {
        if (fileName == null) {
            return false;
        }
        return fileName.toLowerCase().endsWith(".xlsx");
    }

    /**
     * <p>Description: 根据文件名判断是否是2003格式的excel</p>
     *
     * @return
     * @author wjc
     * @date 2017年1月17日
     */
    private boolean isExcel2003() {
        if (fileName == null) {
            return false;
        }
        return fileName.toLowerCase().endsWith(".xls");
    }

}
