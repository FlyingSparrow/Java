package com.sparrow.ibe.constants;

/**
 * 机票接口常量类
 * 
 * @author wangjc
 * 
 */
public class IBEConst {

	/**
	 * <p>Title: DocumentType</p>
	 * <p>Description: 证件类型枚举类</p>
	 *
	 * @author Wangjianchun
	 * @date 2017年7月5日
	 */
	public enum DocumentType {

		PASSPORT("PP", "护照"),
		ID("NI", "身份证");

		private String code;
		private String type;

		DocumentType(String code, String type) {
			this.code = code;
			this.type = type;
		}

		public String getCode() {
			return code;
		}

		public String getType() {
			return type;
		}
	}

	/**
	 * <p>Title: PassengerType</p>
	 * <p>Description: 旅客类型枚举类</p>
	 *
	 * @author Wangjianchun
	 * @date 2017年7月5日
	 */
	public enum PassengerType {

		ADULT("ADT", "成人"),
		CHILD("CHD", "儿童（2-12周岁）"),
		INFANT("INF", "婴儿（2周岁以下）"),
		UNACCOMPANIED_MINORS("UM", "无人陪伴儿童"),
		GM("GM", "伤残军人"),
		JC("JC", "因公带伤警察"),
		CNN("CNN", "CNN"),
		OTHER("OTHER", "其他旅客类型");

		private String code;
		private String type;

		PassengerType(String code, String type) {
			this.code = code;
			this.type = type;
		}

		public String getCode() {
			return code;
		}

		public String getType() {
			return type;
		}
	}

	/**
	 * <p>Title: CabinClass</p>
	 * <p>Description: 舱位等级枚举类</p>
	 *
	 * @author Wangjianchun
	 * @date 2017年7月5日
	 */
	public enum CabinClass {

		FIRST("F", "头等舱"),
		BUSINESS("C", "公务舱"),
		ECONOMY("Y", "经济舱"),;

		/**
		 * 舱位代码
		 */
		private String code;
		/**
		 * 舱位名称
		 */
		private String name;

		CabinClass(String code, String name) {
			this.code = code;
			this.name = name;
		}

		public String getCode() {
			return code;
		}

		public String getName() {
			return name;
		}

	}

	/**
	 * <p>Title: LanguageType</p>
	 * <p>Description: 语言类型枚举类</p>
	 *
	 * @author Wangjianchun
	 * @date 2017年7月5日
	 */
	public enum LanguageType {

		ZH("ZH", "中文"),
		EN("EN", "英文");

		private String code;
		private String type;

		LanguageType(String code, String type) {
			this.code = code;
			this.type = type;
		}

		public String getCode() {
			return code;
		}

		public String getType() {
			return type;
		}
	}

	/**
	 * <p>Title: Gender</p>
	 * <p>Description: 性别枚举类</p>
	 *
	 * @author Wangjianchun
	 * @date 2017年7月5日
	 */
	public enum Gender {

		MALE("MALE", "男"),
		FEMALE("FEMALE", "女");

		private String code;
		private String name;

		Gender(String code, String name) {
			this.code = code;
			this.name = name;
		}

		public String getCode() {
			return code;
		}

		public String getName() {
			return name;
		}
	}

	/**
	 * <p>Title: ModificationType</p>
	 * <p>Description: 修改类型枚举类</p>
	 *
	 * @author Wangjianchun
	 * @date 2017年7月5日
	 */
	public enum ModificationType {

		SSR_ADD("SSR_ADD", "特殊服务请求添加"),
		REMARK_ADD("REMARK_ADD", "备注信息添加"),
		OSI_ADD("OSI_ADD", "其它服务信息添加"),
		EI_ADD("EI_ADD", "EI项添加"),
		SEGMENT_CONFIRM("SEGMENT_CONFIRM", "航段确认"),
		PASSENGER_FOID_MODIFY("PASSENGER_FOID_MODIFY", "旅客证件号修改");

		private String code;
		private String name;

		ModificationType(String code, String name) {
			this.code = code;
			this.name = name;
		}

		public String getCode() {
			return code;
		}

		public String getName() {
			return name;
		}
	}

	/**
	 * <p>Title: SSRCode</p>
	 * <p>Description: 特殊服务请求代码枚举类</p>
	 *
	 * @author Wangjianchun
	 * @date 2017年7月5日
	 */
	public enum SSRCode {

		CHLD("CHLD", "Child", "儿童标识"),
		DOCS("DOCS", "Documents", "证件标识"),
		FQTV("FQTV", "Frequent Traveler", "常旅客计划"),
		SPML("SPML", "Special Meal", "特殊餐食"),
		UMNR("UMNR", "Unaccompanied Minor", "无成人陪伴儿童");

		private String code;
		private String name;
		private String remark;

		SSRCode(String code, String name, String remark) {
			this.code = code;
			this.name = name;
			this.remark = remark;
		}

		public String getCode() {
			return code;
		}

		public String getName() {
			return name;
		}

		public String getRemark() {
			return remark;
		}
	}

	private IBEConst() {
	}

}
