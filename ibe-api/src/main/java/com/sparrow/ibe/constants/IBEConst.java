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

	private IBEConst() {
	}

}
