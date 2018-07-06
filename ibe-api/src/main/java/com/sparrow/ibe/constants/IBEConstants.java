package com.sparrow.ibe.constants;

import com.sparrow.ibe.enums.IBEError;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 机票接口常量类
 * 
 * @author wangjc
 * 
 */
public class IBEConstants {

	public static final Map<String, String> AIRPORT_MAP = new HashMap<String, String>();// 机场
	public static final Map<String, String> DAYOFWEEK_MAP = new HashMap<String, String>();//
	public static final Map<String, String> DAYOFWEEK_MAP2 = new HashMap<String, String>();
	public static final Map<String, String> CURRENCY_CODE_MAP = new HashMap<String, String>();
	public static final Map<String, String> PASSENGER_TYPE_MAP = new HashMap<String, String>();
	public static final Map<String, String> FARE_TYPE_MAP = new HashMap<String, String>();
	public static final Map<String, String> CABIN_CLASS_MAP = new HashMap<String, String>();
	public static final Map<String, String> PHYSICAL_CABIN_MAP = new HashMap<String, String>();// 服务等级
	public static final Map<String, IBEError> IBE_ERROR_MAP = new HashMap<String, IBEError>();
	public static final Map<String, String> PAYMENT_TYPE_MAP = new HashMap<String, String>();// 支付方式
	public static final Map<String, String> DOCUMENT_TYPE_MAP = new HashMap<String, String>();// 证件类型
	public static final Map<String, String> SEGMENT_TYPE_MAP = new HashMap<String, String>();// 航段类型
	public static final Map<String, String> SEGMENT_STATUS_MAP = new HashMap<String, String>();// 航段状态
	public static final Map<String, String> TICKET_MAP = new HashMap<String, String>();// 票类型
	public static final Map<String, String> MEAL_MAP = new HashMap<String, String>();// 餐食标识
	public static final Map<String, String> TV_MAP = new HashMap<String, String>();// 娱乐标识
	public static final Map<String, String> ET_MAP = new HashMap<String, String>();// 电子客票标识
	public static final Map<String, String> SOURCE_SYSTEM_MAP = new HashMap<String, String>();// 数据源标识字段
	public static final Map<String, String> JOURNEY_TYPE_MAP = new HashMap<String, String>();// 单程还是往返
	public static final Map<String, String> FARE_BASIC_CODE_MAP = new HashMap<String, String>();// 运价基础
	public static final Map<String, String> DIRECTION_MAP = new HashMap<String, String>();// 方向标识
	public static final Map<String, String> AGENCY_FEES_TYPE_MAP = new HashMap<String, String>();// 代理费类型

	public static final Map<String, String> TYPE_MAP = new HashMap<String, String>();// 类型：附加检查的类型、ATP附加费的类型
	public static final Map<String, String> TRAVEL_APPLICATION_MAP = new HashMap<String, String>();// 旅行申请
	public static final Map<String, String> REASON_MAP = new HashMap<String, String>();// 其他失败原因的描述
	public static final Map<String, String> FUNCTIONAL_CONTROL_CHARACTER_MAP = new HashMap<String, String>();// 功能控制字符--售票报价的状态
	public static final Map<String, String> TICKET_TIME_CONVERSION_MAP = new HashMap<String, String>();// 变更出票时间
	public static final Map<String, String> GLOBAL_INDICATOR_MAP = new HashMap<String, String>();// 全球化标识
	public static final Map<String, String> COUPON_TYPE_MAP = new HashMap<String, String>();// 票面类型

	public static final Map<String, String> TAX_CODE_MAP = new HashMap<String, String>();// 税费代码
	public static final Map<String, String> CODE_TYPE_TYPE_MAP = new HashMap<String, String>();// 代号类型类型

	public static final Map<String, String> STATUS_MAP = new HashMap<String, String>();// 是否找到翻译结果
	public static final Map<String, String> CATEGORY_MAP = new HashMap<String, String>();// 规则类型
	public static final Map<String, String> FARE_REFERENCE_MAP = new HashMap<String, String>();// 运价参考信息
	public static final Map<String, String> RES_BOOK_DESIG_CODE_MAP = new HashMap<String, String>();// RBD代码

	public static final Map<String, String> CHANGED_INDICATOR_MAP = new HashMap<String, String>();// RBD代码

	public static final Map<String, String> GROUP_TICKET_IND_MAP = new HashMap<String, String>();// 团体票标识
	public static final Map<String, String> MANUAL_IND_MAP = new HashMap<String, String>();// 手工票标识
	public static final Map<String, String> TICKETING_STATUS_MAP = new HashMap<String, String>();// 航段状态
	public static final Map<Boolean, String> INTER_SEGMENT_MAP = new HashMap<Boolean, String>();// 是否是国际航段

	public static final Map<String, String> BSP_TKT_INDICATOR_MAP = new HashMap<String, String>();// BSP票类型
	
	public static final Map<String, String> SSR_CODE_MAP = new HashMap<String, String>();// 特殊服务信息服务类型
	
	static {

		AIRPORT_MAP.put("CAN", "白云国际机场");
		AIRPORT_MAP.put("WUH", "天河机场");

		DAYOFWEEK_MAP.put("SUN", "星期日");
		DAYOFWEEK_MAP.put("MON", "星期一");
		DAYOFWEEK_MAP.put("TUE", "星期二");
		DAYOFWEEK_MAP.put("WED", "星期三");
		DAYOFWEEK_MAP.put("THU", "星期四");
		DAYOFWEEK_MAP.put("FRI", "星期五");
		DAYOFWEEK_MAP.put("SAT", "星期六");

		DAYOFWEEK_MAP2.put("SU", "星期日");
		DAYOFWEEK_MAP2.put("MO", "星期一");
		DAYOFWEEK_MAP2.put("TU", "星期二");
		DAYOFWEEK_MAP2.put("WE", "星期三");
		DAYOFWEEK_MAP2.put("TH", "星期四");
		DAYOFWEEK_MAP2.put("FR", "星期五");
		DAYOFWEEK_MAP2.put("SA", "星期六");

		CURRENCY_CODE_MAP.put("CNY", "人民币");
		CURRENCY_CODE_MAP.put("USD", "美元");
		CURRENCY_CODE_MAP.put("EUR", "欧元");
		CURRENCY_CODE_MAP.put("JPY", "日元");
		CURRENCY_CODE_MAP.put("GBP", "英镑");

		PASSENGER_TYPE_MAP.put("ADT", "成人");
		PASSENGER_TYPE_MAP.put("CHD", "儿童（2-12周岁）");
		PASSENGER_TYPE_MAP.put("INF", "婴儿（2周岁以下）");
		PASSENGER_TYPE_MAP.put("UM", "无人陪伴儿童");
		PASSENGER_TYPE_MAP.put("GM", "伤残军人");
		PASSENGER_TYPE_MAP.put("JC", "因公带伤警察");
		PASSENGER_TYPE_MAP.put("CNN", "CNN");
		PASSENGER_TYPE_MAP.put("OTHER", "其他旅客类型");

		FARE_TYPE_MAP.put("0", "公布价单端相加");
		FARE_TYPE_MAP.put("1", "国航其它航空公司中转");
		FARE_TYPE_MAP.put("3", "全程");
		FARE_TYPE_MAP.put("4", "组合价");
		FARE_TYPE_MAP.put("5", "拆分价");

		CABIN_CLASS_MAP.put("Y", "经济舱");
		CABIN_CLASS_MAP.put("C", "公务舱");
		CABIN_CLASS_MAP.put("F", "头等舱");

		PHYSICAL_CABIN_MAP.put("FIRST", "头等舱");
		PHYSICAL_CABIN_MAP.put("BUSINESS", "公务舱");
		PHYSICAL_CABIN_MAP.put("ECONOMY", "经济舱");

		PAYMENT_TYPE_MAP.put("CASH", "现金");
		PAYMENT_TYPE_MAP.put("CREDIT_CARD", "信用卡");
		PAYMENT_TYPE_MAP.put("DEBIT_CARD", "借记卡");
		PAYMENT_TYPE_MAP.put("CHECK", "支票");
		PAYMENT_TYPE_MAP.put("DEPOSIT", "存款/押金");
		PAYMENT_TYPE_MAP.put("PREPAY", "预付款");
		PAYMENT_TYPE_MAP.put("GUARANTEE", "担保");
		PAYMENT_TYPE_MAP.put("MCO", "旅费证");
		PAYMENT_TYPE_MAP.put("OTHER", "其他");
		PAYMENT_TYPE_MAP.put("UNKNOWN", "未知");

		DOCUMENT_TYPE_MAP.put("PP", "护照");
		DOCUMENT_TYPE_MAP.put("NI", "身份证");

		SEGMENT_TYPE_MAP.put("NORMAL", "普通航段");
		SEGMENT_TYPE_MAP.put("OPEN", "不定期航段");
		SEGMENT_TYPE_MAP.put("ARRIVAL_UNKOWN_ARNK", "信息航段");

		SEGMENT_STATUS_MAP.put("OK", "成人或者儿童");
		SEGMENT_STATUS_MAP.put("NS", "婴儿不占座");
		SEGMENT_STATUS_MAP.put("OPEN", "XE PNR 后");

		TICKET_MAP.put("ETICKET", "电子机票");
		TICKET_MAP.put("PAPER", "纸质机票");
		TICKET_MAP.put("EMD", "中国航信电子杂费平台");
		TICKET_MAP.put("MCO", "旅费证");
		TICKET_MAP.put("OTHER", "其他机票类型");
		TICKET_MAP.put("UNKNOWN", "未知机票类型");

		MEAL_MAP.put("B", "早餐");
		MEAL_MAP.put("L", "午餐");
		MEAL_MAP.put("S", "小吃");

		TV_MAP.put("0", "无娱乐设施");
		TV_MAP.put("1", "有娱乐设施");

		ET_MAP.put("0", "不支持电子客票");
		ET_MAP.put("1", "支持电子客票");
		ET_MAP.put("E", "E");

		SOURCE_SYSTEM_MAP.put("MATRIX", "代表结果是系统运价");
		SOURCE_SYSTEM_MAP.put("EASYFARE", "代表非标金额类纸质运价");
		SOURCE_SYSTEM_MAP.put("MATRIX-EASYFARE", "纸质大客户运价");

		JOURNEY_TYPE_MAP.put("OW", "one way(单程)");
		JOURNEY_TYPE_MAP.put("RT", "round trip(往返)");
		JOURNEY_TYPE_MAP.put("TS", "two segments(联程-两航段)");
		JOURNEY_TYPE_MAP.put("MS", "multiple segments(联程-多航段)");
		JOURNEY_TYPE_MAP.put("OTHER", "其他航程类型");
		JOURNEY_TYPE_MAP.put("UNKNOWN", "未知航程类型");

		FARE_BASIC_CODE_MAP.put("FBR", "运价基础");
		FARE_BASIC_CODE_MAP.put("YOW", "运价基础");

		DIRECTION_MAP.put("TVL", "跟旅行方向一致");
		DIRECTION_MAP.put("RET", "跟旅行方向相反");
		DIRECTION_MAP.put("AXI", "无此信息");

		AGENCY_FEES_TYPE_MAP.put("G", "Gross");
		AGENCY_FEES_TYPE_MAP.put("N", "Net");
		AGENCY_FEES_TYPE_MAP.put("B", "Both");

		TYPE_MAP.put("bhc", "单程回拽最低收费检查");
		TYPE_MAP.put("dtf", "运价差额");
		TYPE_MAP.put("ctm", "环程最低收费检查");
		TYPE_MAP.put("cop", "支付国检查");
		TYPE_MAP.put("cpm", "支付国检查");
		TYPE_MAP.put("osc", "单程次航程收费检查");
		TYPE_MAP.put("dmc", "方向性最低收费检查");
		TYPE_MAP.put("rsc", "回程次行程检查");
		TYPE_MAP.put("A", "Airport/Terminal");
		TYPE_MAP.put("B", "Business class");
		TYPE_MAP.put("C", "Supersonic");
		TYPE_MAP.put("D", "Peak travel time");
		TYPE_MAP.put("E", "Equipment");
		TYPE_MAP.put("F", "Fuel");
		TYPE_MAP.put("G", "Peak");
		TYPE_MAP.put("H", "Holiday");
		TYPE_MAP.put("I", "Side trip");
		TYPE_MAP.put("J", "Seasonal");
		TYPE_MAP.put("K", "Weekend");
		TYPE_MAP.put("L", "Sleeperette");
		TYPE_MAP.put("M",
				"Waiver for advance purchase requirements");
		TYPE_MAP.put("N", "Service upgrade");
		TYPE_MAP.put("O", "Security");
		TYPE_MAP.put("P", "Maximum stay waiver");
		TYPE_MAP.put("Q", "Miscellaneous/Other");
		TYPE_MAP.put("S", "Stopover charge");
		TYPE_MAP.put("T", "Transfer charge");

		TRAVEL_APPLICATION_MAP.put("1", "Fare component");
		TRAVEL_APPLICATION_MAP.put("2", "Round trip");
		TRAVEL_APPLICATION_MAP.put("3", "Per transfer");
		TRAVEL_APPLICATION_MAP.put("4", "Per ticket");
		TRAVEL_APPLICATION_MAP.put("5", "Per coupon");
		TRAVEL_APPLICATION_MAP.put("6", "Per direction");
		TRAVEL_APPLICATION_MAP.put("<blank>", "Fare component");

		REASON_MAP.put("BookingClass", "其他失败原因的描述");
		REASON_MAP.put("Routing", "其他失败原因的描述");
		REASON_MAP.put("PaxType", "其他失败原因的描述");
		REASON_MAP.put("Rec1Unavailable", "其他失败原因的描述");
		REASON_MAP.put("SurfaceSector", "其他失败原因的描述");
		REASON_MAP.put("Loit", "其他失败原因的描述");
		REASON_MAP.put("Currency", "其他失败原因的描述");
		REASON_MAP.put("NonSecurityCat15", "其他失败原因的描述");
		REASON_MAP.put("PricingUnit", "其他失败原因的描述");
		REASON_MAP.put("AbonnementFare", "其他失败原因的描述");
		REASON_MAP.put("YYSupressionTable", "其他失败原因的描述");
		REASON_MAP.put("HIPCheck", "其他失败原因的描述");
		REASON_MAP.put("TourCodeCombine", "其他失败原因的描述");
		REASON_MAP.put("CarrierVsYYCarrier", "其他失败原因的描述");
		REASON_MAP.put("CarrierSurfaceSector", "其他失败原因的描述");
		REASON_MAP.put("Mileage", "其他失败原因的描述");

		FUNCTIONAL_CONTROL_CHARACTER_MAP.put("D",
				"Display only,not for Ticketing");
		FUNCTIONAL_CONTROL_CHARACTER_MAP.put("F",
				"Error response,invalid input format");
		FUNCTIONAL_CONTROL_CHARACTER_MAP.put("T", "Ticketable data");
		FUNCTIONAL_CONTROL_CHARACTER_MAP
				.put("W",
						"May be used for Ticketing,however specific Warning Message is attached which may need Agent verification");
		FUNCTIONAL_CONTROL_CHARACTER_MAP.put("X",
				"Error response,processing error");

		TICKET_TIME_CONVERSION_MAP.put("TRUE", "时间需要变更（基于预定时间）");
		TICKET_TIME_CONVERSION_MAP.put("FALSE", "不需要变更（基于出发）");

		GLOBAL_INDICATOR_MAP.put("TRUE", "允许经停(O)");
		GLOBAL_INDICATOR_MAP.put("FALSE", "不允许经停(X)");

		COUPON_TYPE_MAP.put("A", "Auditor");
		COUPON_TYPE_MAP.put("P", "Passenger");
		COUPON_TYPE_MAP.put("B", "Both");

		TAX_CODE_MAP.put("TR", "TR");//国内税费代码
		TAX_CODE_MAP.put("XY", "XY");
		TAX_CODE_MAP.put("XT", "综合税");
		TAX_CODE_MAP.put("US", "US");
		TAX_CODE_MAP.put("CN", "机场建设费");
		TAX_CODE_MAP.put("YQ", "燃油税");
		

		CODE_TYPE_TYPE_MAP.put("T", "Tourcode");

		STATUS_MAP.put("0", "未找到翻译结果");
		STATUS_MAP.put("1", "找到翻译结果");

		CATEGORY_MAP.put("MaxStay", "规则类型(Category)");
		CATEGORY_MAP.put("MinStay", "规则类型(Category)");
		CATEGORY_MAP.put("Penaltie", "规则类型(Category)");

		FARE_REFERENCE_MAP.put("CYS", "运价参考信息(FareReference)");

		CHANGED_INDICATOR_MAP.put("Blank", "指定条件下允许变更且没有次数限制");
		CHANGED_INDICATOR_MAP.put("1", "允许客票变更次数1次");
		CHANGED_INDICATOR_MAP.put("2", "允许客票变更次数2次");
		CHANGED_INDICATOR_MAP.put("3", "允许客票变更次数3次");
		CHANGED_INDICATOR_MAP.put("4", "允许客票变更次数4次");
		CHANGED_INDICATOR_MAP.put("5", "允许客票变更次数5次");
		CHANGED_INDICATOR_MAP.put("6", "允许客票变更次数6次");
		CHANGED_INDICATOR_MAP.put("7", "允许客票变更次数7次");
		CHANGED_INDICATOR_MAP.put("8", "允许客票变更次数8次");
		CHANGED_INDICATOR_MAP.put("9", "允许客票变更次数9次");
		CHANGED_INDICATOR_MAP.put("N", "任何条件下不得变更");
		CHANGED_INDICATOR_MAP.put("X", "变更文本规则");

		GROUP_TICKET_IND_MAP.put("I", "非团队票");
		GROUP_TICKET_IND_MAP.put("G", "团队票");

		MANUAL_IND_MAP.put("A", "非手工票");
		MANUAL_IND_MAP.put("M", "手工票");

		TICKETING_STATUS_MAP.put("F", "已使用");
		TICKETING_STATUS_MAP.put("R", "退票");
		TICKETING_STATUS_MAP.put("V", "废票");
		TICKETING_STATUS_MAP.put("E", "换开");
		TICKETING_STATUS_MAP.put("G", "因为航班中断出现ET票联换开");
		TICKETING_STATUS_MAP.put("X", "承运人打印了一张新票，但是没有影响原来的支付和规则");

		INTER_SEGMENT_MAP.put(true, "国际票");
		INTER_SEGMENT_MAP.put(false, "国内票");

		BSP_TKT_INDICATOR_MAP.put("D", "非BEP电子客票(B系统发起的)");
		BSP_TKT_INDICATOR_MAP.put("B", "BSP电子客票(C系统发起的)");
		
		SSR_CODE_MAP.put("FQTV", "特殊服务信息服务类型");

		List<IBEError> airTicketErrorList = Arrays.asList(IBEError.values());
		for (IBEError item : airTicketErrorList) {
			IBE_ERROR_MAP.put(item.getCode(), item);
		}
	}

	private IBEConstants() {
	}

}
