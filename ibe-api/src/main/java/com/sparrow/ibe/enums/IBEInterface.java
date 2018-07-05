package com.sparrow.ibe.enums;

/**
 * 机票接口枚举类
 *
 * @author wangjc
 * @date 2014-10-6
 */
public enum IBEInterface {

    JP001("运价服务", "JP001", "国际 Shopping(AirFareFlightShop)服务"),
    JP002("运价服务", "JP002", "国际按 PNR 运价计算服务"),
    JP003("运价服务", "JP003", "国际运价计算(AirFarePrice)服务"),
    JP004("运价服务", "JP004", "国际退改签规则查询服务"),
    JP005("运价服务", "JP005", "国内 shopping 查询服务"),
    JP006("运价服务", "JP006", "国内按 PNR 的运价计算服务"),
    JP007("运价服务", "JP007", "国内变更计算服务"),
    JP008("运价服务", "JP008", "国内公布运价查询服务"),
    JP009("运价服务", "JP009", "国内航段运价计算(AirPriceBySeg)服务"),
    JP010("运价服务", "JP010", "国内退改签规则查询服务"),
    JP011("预订服务", "JP011", "自动预订服务"),
    JP012("预订服务", "JP012", "自动修改服务"),
    JP013("预订服务", "JP013", "PNR结构化信息提取服务"),
    JP014("客票服务", "JP014", "国际自动出票服务"),
    JP015("客票服务", "JP015", "国内自动出票服务"),
    JP016("客票服务", "JP016", "国内自动退票服务"),
    JP017("客票服务", "JP017", "票面信息提取服务"),
    JP018("智能服务", "JP018", "客票状态查询服务"),
    JP019("智能服务", "JP019", "航班班期查询服务");

    IBEInterface(String type, String id, String description) {
        this.type = type;
        this.id = id;
        this.description = description;
    }

    private String type;
    private String id;
    private String description;

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

}
