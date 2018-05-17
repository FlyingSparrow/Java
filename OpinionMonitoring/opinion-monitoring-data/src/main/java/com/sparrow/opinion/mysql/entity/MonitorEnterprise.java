package com.sparrow.opinion.mysql.entity;

import com.sparrow.opinion.base.bean.BasePageRequest;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * <p>Title: MonitorEnterprise</p>
 * <p>Description: 监控企业信息表entity</p>
 *
 * @author zhangtong
 * @date 2017年6月20日
 */
@Entity
@Table(name = "t_monitor_enterprise")
public class MonitorEnterprise extends BasePageRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "enterprise_name", nullable = false)
    private String enterpriseName;    // 企业名称

    @Column(name = "expire_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date expireDate;    //到期时间，默认值：创建时间+3个月

    @Column(name = "c_status", nullable = false)
    private String status;    //状态：监控中，已到期

    @Column(name = "delete_flag", nullable = false)
    private String deleteFlag; // 删除标记，已删除：是：未删除：否

    @Column(name = "created_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdDate; // 创建时间

    @Column(name = "created_user", nullable = false)
    private String createdUser; // 创建人

    @Column(name = "modified_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifiedDate; // 更新时间

    @Column(name = "modified_user", nullable = false)
    private String modifiedUser; // 更新人

    @Column(name = "user_account", nullable = false)
    private String userAccount;    //关联t_userbase表的user_account

    @Column(name = "mongo_enterprise_id", nullable = false)
    private String mongoEnterpriseId;    //mongodb中的enterprise_info表的id

    @Transient
    private Integer groupId;

    @Transient
    private String sortParam;    //需要排序的字段

    @Transient
    private String sortType;    //排序类型
    @Transient
    private Double ystdPressureIndex;    //整体压力指数，昨天的压力指数
    @Transient
    private Integer totalOpinion; // 舆情总数(当日的舆情总数)
    @Transient
    private Integer negativeOpinionCount; // 负面舆情数(当日的负面舆情数)
    @Transient
    private Double redPressureIndex; // 红色预警压力指数
    @Transient
    private Double orangePressureIndex; // 橙色预警压力指数
    @Transient
    private Double yellowPressureIndex; // 黄色预警压力指数
    @Transient
    private String indicatorStatus;//指标状态(指标正常；红色预警；橙色预警；黄色预警)

    @Transient
    private Double negativePressureIndexProportion; //负面压力的比例

    @Transient
    private Double negativePressureIndex;   //负面压力

    @Transient
    private Double pressureIndex;   //整体压力

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getModifiedUser() {
        return modifiedUser;
    }

    public void setModifiedUser(String modifiedUser) {
        this.modifiedUser = modifiedUser;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getMongoEnterpriseId() {
        return mongoEnterpriseId;
    }

    public void setMongoEnterpriseId(String mongoEnterpriseId) {
        this.mongoEnterpriseId = mongoEnterpriseId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getSortParam() {
        return sortParam;
    }

    public void setSortParam(String sortParam) {
        this.sortParam = sortParam;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    public Double getYstdPressureIndex() {
        return ystdPressureIndex;
    }

    public void setYstdPressureIndex(Double ystdPressureIndex) {
        this.ystdPressureIndex = ystdPressureIndex;
    }

    public Integer getTotalOpinion() {
        return totalOpinion;
    }

    public void setTotalOpinion(Integer totalOpinion) {
        this.totalOpinion = totalOpinion;
    }

    public Integer getNegativeOpinionCount() {
        return negativeOpinionCount;
    }

    public void setNegativeOpinionCount(Integer negativeOpinionCount) {
        this.negativeOpinionCount = negativeOpinionCount;
    }

    public Double getRedPressureIndex() {
        return redPressureIndex;
    }

    public void setRedPressureIndex(Double redPressureIndex) {
        this.redPressureIndex = redPressureIndex;
    }

    public Double getOrangePressureIndex() {
        return orangePressureIndex;
    }

    public void setOrangePressureIndex(Double orangePressureIndex) {
        this.orangePressureIndex = orangePressureIndex;
    }

    public Double getYellowPressureIndex() {
        return yellowPressureIndex;
    }

    public void setYellowPressureIndex(Double yellowPressureIndex) {
        this.yellowPressureIndex = yellowPressureIndex;
    }

    public String getIndicatorStatus() {
        return indicatorStatus;
    }

    public void setIndicatorStatus(String indicatorStatus) {
        this.indicatorStatus = indicatorStatus;
    }

    public Double getNegativePressureIndexProportion() {
        return negativePressureIndexProportion;
    }

    public void setNegativePressureIndexProportion(Double negativePressureIndexProportion) {
        this.negativePressureIndexProportion = negativePressureIndexProportion;
    }

    public Double getNegativePressureIndex() {
        return negativePressureIndex;
    }

    public void setNegativePressureIndex(Double negativePressureIndex) {
        this.negativePressureIndex = negativePressureIndex;
    }

    public Double getPressureIndex() {
        return pressureIndex;
    }

    public void setPressureIndex(Double pressureIndex) {
        this.pressureIndex = pressureIndex;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
