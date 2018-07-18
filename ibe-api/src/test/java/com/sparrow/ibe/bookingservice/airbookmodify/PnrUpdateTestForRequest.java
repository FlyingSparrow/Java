package com.sparrow.ibe.bookingservice.airbookmodify;

import com.sparrow.ibe.bookingservice.airbook.model.*;
import com.sparrow.ibe.bookingservice.airbookmodify.model.AbmAirReservation;
import com.sparrow.ibe.bookingservice.airbookmodify.model.AirBookModifyRQ;
import com.sparrow.ibe.bookingservice.airbookmodify.model.AirBookModifyRequest;
import com.sparrow.ibe.bookingservice.airbookmodify.model.TravelerInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能：PNR修改接口测试请求参数
 * @author wanglw
 * @date 2014-8-14
 */
public class PnrUpdateTestForRequest {
	
	/**
	 * 功能：PNR修改接口测试请求参数
	 * @author wanglw
	 * @date 2014-8-14
	 */
	public static void main(String[] args) throws Exception {
		PnrUpdateTestForRequest subRequest = new PnrUpdateTestForRequest();
		subRequest.buildRequest(1);
	}
	
	/**
	 * 功能：构造请求参数
	 * @author wanglw
	 * @date 2014-8-14
	 */
	public AirBookModifyRequest buildRequest(int type){
		AirBookModifyRequest request = new AirBookModifyRequest();
		//输入参数结构：2大信息组 
		//1.POS信息    2.出票请求信息（PNR号信息     票面信息    打票机信息 ）
		if(type==1){
//			request=buildRequest01();	//场景1
		}else if(type == 2){
//			request=buildRequest02();	//场景2
		}else if(type == 3){
			request=buildRequest03();	//场景3
		}else if(type == 4){
			request=buildRequest04();	//场景4 
		}else if(type == 5){
			request=buildRequest05();	//场景5
		}else if(type == 6){
			request=buildRequest06();	//场景6
		}else if(type == 7){
			request=buildRequest07();	//场景7
		}else if(type == 8){
			request=buildRequest08();	//场景8
		}else if(type == 9){
			request=buildRequest09();	//场景9
		}else if(type == 10){
			request=buildRequest10();	//场景10
		}else if(type == 11){
			request=buildRequest11();	//场景11
		}else if(type == 12){
			request=buildRequest12();	//场景12
		}else if(type == 13){
			request=buildRequest13();	//场景13
		}else if(type == 14){
			request=buildRequest14();	//场景14
		}else if(type == 15){
			request=buildRequest15();	//场景15
		}else if(type == 16){
			request=buildRequest16();	//场景16
		}else if(type == 17){
			request=buildRequest17();	//场景17
		}else if(type == 18){
			request=buildRequest18();	//场景18
		}else if(type == 19){
			request=buildRequest19();	//场景19
		}else if(type == 20){
			request=buildRequest20();	//场景20
		}else if(type == 21){
			request=buildRequest21();	//场景21
		}else if(type == 22){
			request=buildRequest22();	//场景22
		}else if(type == 23){
			request=buildRequest23();	//场景23
		}else if(type == 24){
			request=buildRequest24();	//场景24
		}
		
		return request;
	}

	/**
	 * 场景3：添加OSI
	 * @author wanglw
	 * @date 2014-8-14
	 */
	private static AirBookModifyRequest buildRequest03() {
		// 输入参数结构（3大信息）
		// 1.POS信息  2.修改后的预定信息    3.修改前的预定信息

		AirBookModifyRequest request = new AirBookModifyRequest();
		request.setPseudoCityCode("sparrow");

		//2.修改后的预定信息
		AirBookModifyRQ airBookModifyRQ = new AirBookModifyRQ();
		airBookModifyRQ.setModificationType("OSI_ADD");				//修改类型
		List<AbmAirReservation> abmAirReservationList = new ArrayList<AbmAirReservation>();
		AbmAirReservation abmAirReservation = new AbmAirReservation();
		List<TravelerInfo> trList = new ArrayList<TravelerInfo>();
		TravelerInfo trInfo = new TravelerInfo();

		//要添加的OSI信息（修改的关键点）
		List<OtherServiceInformation> osiList = new ArrayList<OtherServiceInformation>();
		OtherServiceInformation osi = new OtherServiceInformation();
		osi.setTravelerRefNumberRPH("1");
		osi.setAirlineCode("MU");
		osi.setText("OSI");
		//osi.setAirlineCompanyShortName("中国东方航空公司");
		osiList.add(osi);

		trInfo.setOsiList(osiList);
		trList.add(trInfo);
		abmAirReservation.setTravelerInfoList(trList);
		abmAirReservationList.add(abmAirReservation);
		airBookModifyRQ.setAbmAirReservationList(abmAirReservationList);

		//3.修改前的预定信息
		AirReservation airReservation = new AirReservation();
		//旅客信息
		List<TravelerInfo> travelerInfoList = new ArrayList<TravelerInfo>();
		TravelerInfo travelerInfo = new TravelerInfo();
		List<AirTraveler> airTravelerList = new ArrayList<AirTraveler>();
		AirTraveler airTraveler = new AirTraveler();
		airTraveler.setPassengerTypeCode("ADT");
		airTraveler.setRph("1");

		List<PersonName> personNameList = new ArrayList<PersonName>();
		PersonName personName = new PersonName();
		personName.setLanguageType("ZH");
		personName.setSurname("高明");
		personNameList.add(personName);
		airTraveler.setPersonNameList(personNameList);

		List<Document> documentList = new ArrayList<Document>();
		Document document = new Document();
		document.setDocType("NI");
		document.setDocId("120221197001011150");
		documentList.add(document);
		airTraveler.setDocumentList(documentList);

		List<TravelerRefNumber> travelerRefNumberList = new ArrayList<TravelerRefNumber>();
		TravelerRefNumber travelerRefNumber = new TravelerRefNumber();
		travelerRefNumber.setRph("1");
		travelerRefNumberList.add(travelerRefNumber);
		airTraveler.setTravelerRefNumberList(travelerRefNumberList);

		airTravelerList.add(airTraveler);
		travelerInfo.setAirTravelerList(airTravelerList);
		travelerInfoList.add(travelerInfo);
		airReservation.setTravelerInfoList(travelerInfoList);

		//PNR号信息
		List<BookingReferenceID> bookingReferenceIDList = new ArrayList<BookingReferenceID>();
		BookingReferenceID bookID = new BookingReferenceID();
		bookID.setId("HTTD1H");
		bookingReferenceIDList.add(bookID);
		airReservation.setBookingReferenceIDList(bookingReferenceIDList);

		request.setAirBookModifyRQ(airBookModifyRQ);
		request.setAirReservation(airReservation);

		return request;
	}
	
	/**
	 * 场景4：添加RMK
	 * @author wanglw
	 * @date 2014-8-14
	 */
	private static AirBookModifyRequest buildRequest04() {
		// 输入参数结构（3大信息）
		// 1.POS信息  2.修改后的预定信息    3.修改前的预定信息
		
		AirBookModifyRequest request = new AirBookModifyRequest();
		request.setPseudoCityCode("sparrow");
		
		//2.修改后的预定信息
		AirBookModifyRQ airBookModifyRQ = new AirBookModifyRQ();
		airBookModifyRQ.setModificationType("REMARK_ADD");				//修改类型
		List<AbmAirReservation> abmAirReservationList = new ArrayList<AbmAirReservation>();
		AbmAirReservation abmAirReservation = new AbmAirReservation();
		List<TravelerInfo> trList = new ArrayList<TravelerInfo>();
		TravelerInfo trInfo = new TravelerInfo();
		
		//要添加的RMK信息（修改的关键点）
		List<SpecialRemark> remarkList = new ArrayList<SpecialRemark>();
		SpecialRemark rmk = new SpecialRemark();
		rmk.setTravelerRefNumberRPH("1");
		rmk.setFlightRefNumberRPH("1");
		rmk.setText("测试添加RMK");
		remarkList.add(rmk);
		trInfo.setRemarkList(remarkList);
		
		trList.add(trInfo);
		abmAirReservation.setTravelerInfoList(trList);
		abmAirReservationList.add(abmAirReservation);
		airBookModifyRQ.setAbmAirReservationList(abmAirReservationList);
		
		//3.修改前的预定信息
		AirReservation airReservation = new AirReservation();
		
		//行程信息
		List<FlightSegment> fsList = new ArrayList<FlightSegment>();
		FlightSegment fs = new FlightSegment();
			fs.setRph("1");
			fs.setDepartureDateTime("2014-08-26T07:00:00");
			fs.setArrivalDateTime("2014-08-26T09:10:00");
			fs.setCodeshareInd("false");
			fs.setFlightNumber("MU5138");   //MU5138 
			fs.setStatus("NN");
			fs.setSegmentType("NORMAL");
			fs.setDepartureAirport("PEK");
			fs.setArrivalAirport("SHA");	//SHA HKG
			fs.setAirEquipType("733");
			fs.setMarketingAirline("MU");
			fs.setResBookDesigCode("Y");
		fsList.add(fs);
		airReservation.setFlightSegmentList(fsList);
		
		//旅客信息
		List<TravelerInfo> travelerInfoList = new ArrayList<TravelerInfo>();
		TravelerInfo travelerInfo = new TravelerInfo();
		List<AirTraveler> airTravelerList = new ArrayList<AirTraveler>();
		AirTraveler airTraveler = new AirTraveler();
		airTraveler.setPassengerTypeCode("ADT");
		airTraveler.setRph("1");

		List<PersonName> personNameList = new ArrayList<PersonName>();
		PersonName personName = new PersonName();
		personName.setLanguageType("ZH");
		personName.setSurname("高明");
		personNameList.add(personName);
		airTraveler.setPersonNameList(personNameList);

		List<Document> documentList = new ArrayList<Document>();
		Document document = new Document();
		document.setDocType("NI");
		document.setDocId("120221197001011150");
		documentList.add(document);
		airTraveler.setDocumentList(documentList);
		
		List<TravelerRefNumber> travelerRefNumberList = new ArrayList<TravelerRefNumber>();
		TravelerRefNumber travelerRefNumber = new TravelerRefNumber();
		travelerRefNumber.setRph("1");
		travelerRefNumberList.add(travelerRefNumber);
		airTraveler.setTravelerRefNumberList(travelerRefNumberList);
		
		airTravelerList.add(airTraveler);
		travelerInfo.setAirTravelerList(airTravelerList);
		travelerInfoList.add(travelerInfo);
		airReservation.setTravelerInfoList(travelerInfoList);

		//PNR号信息
		List<BookingReferenceID> bookingReferenceIDList = new ArrayList<BookingReferenceID>();
		BookingReferenceID bookID = new BookingReferenceID();
		bookID.setId("JG6R08");
		bookingReferenceIDList.add(bookID);
		airReservation.setBookingReferenceIDList(bookingReferenceIDList);
		
		request.setAirBookModifyRQ(airBookModifyRQ);
		request.setAirReservation(airReservation);
		
		return request;
	}
	
	/**
	 * 场景5：添加 SSR-CHLD
	 * @author wanglw
	 * @date 2014-8-14
	 */
	private static AirBookModifyRequest buildRequest05() {
		// 输入参数结构（3大信息）
		// 1.POS信息  2.修改后的预定信息    3.修改前的预定信息
		
		AirBookModifyRequest request = new AirBookModifyRequest();
		request.setPseudoCityCode("sparrow");
		
		//2.修改后的预定信息
		AirBookModifyRQ airBookModifyRQ = new AirBookModifyRQ();
		airBookModifyRQ.setModificationType("SSR_ADD");				//修改类型
		List<AbmAirReservation> abmAirReservationList = new ArrayList<AbmAirReservation>();
		AbmAirReservation abmAirReservation = new AbmAirReservation();
		List<TravelerInfo> trList = new ArrayList<TravelerInfo>();
		TravelerInfo trInfo = new TravelerInfo();
		
		//要添加的SSR-CHLD信息（修改的关键点）
		List<SpecialServiceRequest> ssrList = new ArrayList<SpecialServiceRequest>();
		SpecialServiceRequest ssr = new SpecialServiceRequest();
		ssr.setSsrCode("CHLD");
		ssr.setStatus("HK");
		ssr.setFlightRefNumberRPH("1");
		ssr.setTravelerRefNumberRPH("2");
		ssrList.add(ssr);
		trInfo.setSsrList(ssrList);
		
		
		trList.add(trInfo);
		abmAirReservation.setTravelerInfoList(trList);
		abmAirReservationList.add(abmAirReservation);
		airBookModifyRQ.setAbmAirReservationList(abmAirReservationList);
		
		//3.修改前的预定信息
		AirReservation airReservation = new AirReservation();
		
		//行程信息
		List<OriginDestinationOption> odList = new ArrayList<OriginDestinationOption>();
		OriginDestinationOption od = new OriginDestinationOption();
		List<FlightSegment> fsList = new ArrayList<FlightSegment>();
		FlightSegment fs = new FlightSegment();
			fs.setRph("1");
			fs.setDepartureDateTime("2014-08-26T07:00:00");
			fs.setFlightNumber("MU5138");   					//MU5138 
			fs.setNumberInParty("1");
			fs.setStatus("NN");
			fs.setSegmentType("NORMAL");
			fs.setOperatingAirline("MU");						//承运方两字码
			fs.setFlightNumberOfOperatingAirline("MU5138");		//承运方航班号
			fs.setDepartureAirport("PEK");
			fs.setArrivalAirport("SHA");						//SHA HKG
			fs.setAirEquipType("733");
			fs.setMarketingAirline("MU");
			fs.setResBookDesigCode("Y");
		fsList.add(fs);
		od.setFlightSegmentList(fsList);
		odList.add(od);
		airReservation.setOriginDestinationList(odList);
		
		//旅客信息
		List<TravelerInfo> travelerInfoList = new ArrayList<TravelerInfo>();
		TravelerInfo travelerInfo = new TravelerInfo();
		List<AirTraveler> airTravelerList = new ArrayList<AirTraveler>();
		AirTraveler airTraveler = new AirTraveler();
		airTraveler.setPassengerTypeCode("ADT");
		airTraveler.setRph("2");
		airTraveler.setBirthDate("2008-05-01");

		List<PersonName> personNameList = new ArrayList<PersonName>();
		PersonName personName = new PersonName();
		personName.setLanguageType("ZH");
		personName.setSurname("高一");
		personNameList.add(personName);
		airTraveler.setPersonNameList(personNameList);
		
		List<Document> documentList1 = new ArrayList<Document>();
		Document document1 = new Document();
		document1.setDocType("NI");
		document1.setDocId("120221200805011150");
		document1.setBirthDate("2008-05-01");
		documentList1.add(document1);
		airTraveler.setDocumentList(documentList1);

		List<TravelerRefNumber> travelerRefNumberList = new ArrayList<TravelerRefNumber>();
		TravelerRefNumber travelerRefNumber = new TravelerRefNumber();
		travelerRefNumber.setRph("2");
		travelerRefNumberList.add(travelerRefNumber);
		airTraveler.setTravelerRefNumberList(travelerRefNumberList);
		
		airTravelerList.add(airTraveler);
		travelerInfo.setAirTravelerList(airTravelerList);
		travelerInfoList.add(travelerInfo);
		airReservation.setTravelerInfoList(travelerInfoList);

		//PNR号信息
		List<BookingReferenceID> bookingReferenceIDList = new ArrayList<BookingReferenceID>();
		BookingReferenceID bookID = new BookingReferenceID();
		bookID.setId("JG6R1Y");
		bookingReferenceIDList.add(bookID);
		airReservation.setBookingReferenceIDList(bookingReferenceIDList);
		
		request.setAirBookModifyRQ(airBookModifyRQ);
		request.setAirReservation(airReservation);
		
		return request;
	}
	
	/**
	 * 场景6：添加SSR-DOCS
	 * @author wanglw
	 * @date 2014-8-14
	 */
	private static AirBookModifyRequest buildRequest06() {
		// 输入参数结构（3大信息）
		// 1.POS信息  2.修改后的预定信息    3.修改前的预定信息
		
		AirBookModifyRequest request = new AirBookModifyRequest();
		request.setPseudoCityCode("sparrow");
		
		//2.修改后的预定信息
		AirBookModifyRQ airBookModifyRQ = new AirBookModifyRQ();
		airBookModifyRQ.setModificationType("SSR_ADD");				//修改类型
		List<AbmAirReservation> abmAirReservationList = new ArrayList<AbmAirReservation>();
		AbmAirReservation abmAirReservation = new AbmAirReservation();
		List<TravelerInfo> trList = new ArrayList<TravelerInfo>();
		TravelerInfo trInfo = new TravelerInfo();
		
		//要添加的SSR-DOCS信息（修改的关键点）
		List<SpecialServiceRequest> ssrList = new ArrayList<SpecialServiceRequest>();
		SpecialServiceRequest ssr = new SpecialServiceRequest();
		ssr.setSsrCode("DOCS");
		ssr.setServiceQuantity("1");
		ssr.setStatus("NN");
		ssr.setRph("1");
		ssr.setAirlineCode("CA");
		ssr.setTravelerRefNumberRPH("1");
		ssrList.add(ssr);
		
		trInfo.setSsrList(ssrList);
		trList.add(trInfo);
		abmAirReservation.setTravelerInfoList(trList);
		abmAirReservationList.add(abmAirReservation);
		airBookModifyRQ.setAbmAirReservationList(abmAirReservationList);
		
		//3.修改前的预定信息
		AirReservation airReservation = new AirReservation();
		
		//旅客信息
		List<TravelerInfo> travelerInfoList = new ArrayList<TravelerInfo>();
		TravelerInfo travelerInfo = new TravelerInfo();
		List<AirTraveler> airTravelerList = new ArrayList<AirTraveler>();
		AirTraveler airTraveler = new AirTraveler();
		airTraveler.setPassengerTypeCode("ADT");
		airTraveler.setRph("1");
		/***姓名***/
		List<PersonName> personNameList = new ArrayList<PersonName>();
		PersonName personName = new PersonName();
		personName.setLanguageType("ZH");
		personName.setSurname("高明");
		personNameList.add(personName);
		airTraveler.setPersonNameList(personNameList);
		/***证件信息***/
		List<Document> documentList = new ArrayList<Document>();
		Document document = new Document();
		document.setDocType("PP");						//证件类型，PP - 护照；NI - 身份证
		document.setDocTypeDetail("P");					//证件类型描述，在证件类型为PP时，提供具体护照类型，如：F、P、AC等
		document.setDocId("G446164");					//证件号
		document.setDocHolderNationality("CN");			//证件持有人国籍
		document.setDocIssueCountry("CN");				//发证国家
		document.setBirthDate("1984-09-04");			//出生日期
		document.setGender("MALE");						//证件持有人性别，证件类型为PP时，需要指定性别
		document.setExpireDate("2031-12-19");			//证件有效期截止日期，证件类型为PP时，需要指定到期时间
		document.setRph("1");							//证件编号，若多个证件编号，输入不同编号，都写入主机
		document.setDocHolderGivenName("DANA");			//证件持有人姓名的名，若填写护照，需要填写此项，姓名为zhang/san时，这里是san
		document.setDocHolderSurname("WANG");				//证件持有人姓名的姓，若填写护照，需要填写此项，姓名为zhang/san时，这里是zhang
		documentList.add(document);
		airTraveler.setDocumentList(documentList);
		
		List<TravelerRefNumber> travelerRefNumberList = new ArrayList<TravelerRefNumber>();
		TravelerRefNumber travelerRefNumber = new TravelerRefNumber();
		travelerRefNumber.setRph("1");
		travelerRefNumberList.add(travelerRefNumber);
		airTraveler.setTravelerRefNumberList(travelerRefNumberList);
		
		airTravelerList.add(airTraveler);
		travelerInfo.setAirTravelerList(airTravelerList);
		travelerInfoList.add(travelerInfo);
		airReservation.setTravelerInfoList(travelerInfoList);

		//PNR号信息
		List<BookingReferenceID> bookingReferenceIDList = new ArrayList<BookingReferenceID>();
		BookingReferenceID bookID = new BookingReferenceID();
		bookID.setId("JG6R08");
		bookingReferenceIDList.add(bookID);
		airReservation.setBookingReferenceIDList(bookingReferenceIDList);
		
		request.setAirBookModifyRQ(airBookModifyRQ);
		request.setAirReservation(airReservation);
		
		return request;
	}
	
	/**
	 * 场景7：添加 SSR-FQTV
	 * @author wanglw
	 * @date 2014-8-14
	 */
	private static AirBookModifyRequest buildRequest07() {
		// 输入参数结构（3大信息）
		// 1.POS信息  2.修改后的预定信息    3.修改前的预定信息
		
		AirBookModifyRequest request = new AirBookModifyRequest();
		request.setPseudoCityCode("sparrow");
		
		//2.修改后的预定信息
		AirBookModifyRQ airBookModifyRQ = new AirBookModifyRQ();
		airBookModifyRQ.setModificationType("SSR_ADD");				//修改类型
		List<AbmAirReservation> abmAirReservationList = new ArrayList<AbmAirReservation>();
		AbmAirReservation abmAirReservation = new AbmAirReservation();
		List<TravelerInfo> trList = new ArrayList<TravelerInfo>();
		TravelerInfo trInfo = new TravelerInfo();
		
		//要添加的SSR-FQTV信息（修改的关键点）
		List<SpecialServiceRequest> ssrList = new ArrayList<SpecialServiceRequest>();
		SpecialServiceRequest ssr = new SpecialServiceRequest();
		ssr.setSsrCode("FQTV");
		ssr.setStatus("HK");
		ssr.setAirlineCode("CA");
		ssr.setText("CA185336034");			//常旅客代码
		ssr.setTravelerRefNumberRPH("1");
		ssrList.add(ssr);
		
		trInfo.setSsrList(ssrList);
		trList.add(trInfo);
		abmAirReservation.setTravelerInfoList(trList);
		abmAirReservationList.add(abmAirReservation);
		airBookModifyRQ.setAbmAirReservationList(abmAirReservationList);
		
		//3.修改前的预定信息
		AirReservation airReservation = new AirReservation();
		
		//旅客信息
		List<TravelerInfo> travelerInfoList = new ArrayList<TravelerInfo>();
		TravelerInfo travelerInfo = new TravelerInfo();
		List<AirTraveler> airTravelerList = new ArrayList<AirTraveler>();
		AirTraveler airTraveler = new AirTraveler();
		airTraveler.setPassengerTypeCode("ADT");
		airTraveler.setRph("1");
		/***姓名***/
		List<PersonName> personNameList = new ArrayList<PersonName>();
		PersonName personName = new PersonName();
		personName.setLanguageType("ZH");
		personName.setSurname("高明");
		personNameList.add(personName);
		airTraveler.setPersonNameList(personNameList);
		/***旅客序号***/
		List<TravelerRefNumber> travelerRefNumberList = new ArrayList<TravelerRefNumber>();
		TravelerRefNumber travelerRefNumber = new TravelerRefNumber();
		travelerRefNumber.setRph("1");
		travelerRefNumberList.add(travelerRefNumber);
		airTraveler.setTravelerRefNumberList(travelerRefNumberList);
		
		airTravelerList.add(airTraveler);
		travelerInfo.setAirTravelerList(airTravelerList);
		travelerInfoList.add(travelerInfo);
		airReservation.setTravelerInfoList(travelerInfoList);

		//PNR号信息
		List<BookingReferenceID> bookingReferenceIDList = new ArrayList<BookingReferenceID>();
		BookingReferenceID bookID = new BookingReferenceID();
		bookID.setId("HPJW2Q");
		bookingReferenceIDList.add(bookID);
		airReservation.setBookingReferenceIDList(bookingReferenceIDList);
		
		request.setAirBookModifyRQ(airBookModifyRQ);
		request.setAirReservation(airReservation);
		
		return request;
	}
	
	/**
	 * 场景8：添加 SSR-SPML
	 * @author wanglw
	 * @date 2014-8-14
	 */
	private static AirBookModifyRequest buildRequest08() {
		// 输入参数结构（3大信息）
		// 1.POS信息  2.修改后的预定信息    3.修改前的预定信息
		
		AirBookModifyRequest request = new AirBookModifyRequest();
		request.setPseudoCityCode("sparrow");
		
		//2.修改后的预定信息
		AirBookModifyRQ airBookModifyRQ = new AirBookModifyRQ();
		airBookModifyRQ.setModificationType("SSR_ADD");				//修改类型
		List<AbmAirReservation> abmAirReservationList = new ArrayList<AbmAirReservation>();
		AbmAirReservation abmAirReservation = new AbmAirReservation();
		List<TravelerInfo> trList = new ArrayList<TravelerInfo>();
		TravelerInfo trInfo = new TravelerInfo();
		
		//要添加的SSR-SPML信息（修改的关键点）
		List<SpecialServiceRequest> ssrList = new ArrayList<SpecialServiceRequest>();
		SpecialServiceRequest ssr = new SpecialServiceRequest();
		ssr.setSsrCode("SPML");							//特殊餐食
		ssr.setStatus("NN");
		ssr.setAirlineCode("CA");
		ssr.setText("NOSALT");							//无盐餐
		ssr.setFlightRefNumberRPH("1");
		ssr.setTravelerRefNumberRPH("1");
		ssrList.add(ssr);
		
		trInfo.setSsrList(ssrList);
		trList.add(trInfo);
		abmAirReservation.setTravelerInfoList(trList);
		abmAirReservationList.add(abmAirReservation);
		airBookModifyRQ.setAbmAirReservationList(abmAirReservationList);
		
		//3.修改前的预定信息
		AirReservation airReservation = new AirReservation();
		
		//行程信息
		List<OriginDestinationOption> odList = new ArrayList<OriginDestinationOption>();
		OriginDestinationOption od = new OriginDestinationOption();
		List<FlightSegment> fsList = new ArrayList<FlightSegment>();
			FlightSegment fs = new FlightSegment();
				fs.setRph("1");
				fs.setDepartureDateTime("2014-08-29T07:30:00");
				//fs.setArrivalDateTime("2014-08-29T09:40:00");
				fs.setOperatingAirline("CA");						//承运方两字码
				fs.setFlightNumberOfOperatingAirline("1831");		//承运方航班号
				fs.setNumberInParty("1");
				fs.setCodeshareInd("false");
				fs.setFlightNumber("1831");   //MU5138    CA1831
				fs.setStatus("NN");
				fs.setSegmentType("NORMAL");
				fs.setDepartureAirport("PEK");
				fs.setArrivalAirport("SHA");	//SHA HKG
				fs.setAirEquipType("333");
				fs.setMarketingAirline("CA");
				fs.setResBookDesigCode("Y");
		fsList.add(fs);
		od.setFlightSegmentList(fsList);
		odList.add(od);
		airReservation.setOriginDestinationList(odList);
		
		//旅客信息
		List<TravelerInfo> travelerInfoList = new ArrayList<TravelerInfo>();
		TravelerInfo travelerInfo = new TravelerInfo();
		List<AirTraveler> airTravelerList = new ArrayList<AirTraveler>();
		AirTraveler airTraveler = new AirTraveler();
		airTraveler.setPassengerTypeCode("ADT");
		airTraveler.setRph("1");
		/***姓名***/
		List<PersonName> personNameList = new ArrayList<PersonName>();
		PersonName personName = new PersonName();
		personName.setLanguageType("ZH");
		personName.setSurname("高明");
		personNameList.add(personName);
		airTraveler.setPersonNameList(personNameList);
		/***证件信息***/
		List<Document> documentList = new ArrayList<Document>();
		Document document = new Document();
		document.setDocType("NI");
		document.setDocId("31010420080101573X");
		documentList.add(document);
		airTraveler.setDocumentList(documentList);
		/***旅客序号***/
		List<TravelerRefNumber> travelerRefNumberList = new ArrayList<TravelerRefNumber>();
		TravelerRefNumber travelerRefNumber = new TravelerRefNumber();
		travelerRefNumber.setRph("1");
		travelerRefNumberList.add(travelerRefNumber);
		airTraveler.setTravelerRefNumberList(travelerRefNumberList);
		
		airTravelerList.add(airTraveler);
		travelerInfo.setAirTravelerList(airTravelerList);
		travelerInfoList.add(travelerInfo);
		airReservation.setTravelerInfoList(travelerInfoList);

		//PNR号信息
		List<BookingReferenceID> bookingReferenceIDList = new ArrayList<BookingReferenceID>();
		BookingReferenceID bookID = new BookingReferenceID();
		bookID.setId("HPJW2Q");
		bookingReferenceIDList.add(bookID);
		airReservation.setBookingReferenceIDList(bookingReferenceIDList);
		
		request.setAirBookModifyRQ(airBookModifyRQ);
		request.setAirReservation(airReservation);
		
		return request;
	}
	
	/**
	 * 场景9：添加 SSR-UMNR
	 * @author wanglw
	 * @date 2014-8-14
	 */
	private static AirBookModifyRequest buildRequest09() {
		// 输入参数结构（3大信息）
		// 1.POS信息  2.修改后的预定信息    3.修改前的预定信息
		
		AirBookModifyRequest request = new AirBookModifyRequest();
		request.setPseudoCityCode("sparrow");
		
		//2.修改后的预定信息
		AirBookModifyRQ airBookModifyRQ = new AirBookModifyRQ();
		airBookModifyRQ.setModificationType("SSR_ADD");				//修改类型
		List<AbmAirReservation> abmAirReservationList = new ArrayList<AbmAirReservation>();
		AbmAirReservation abmAirReservation = new AbmAirReservation();
		List<TravelerInfo> trList = new ArrayList<TravelerInfo>();
		TravelerInfo trInfo = new TravelerInfo();
		
		//要添加的SSR-UMNR信息（修改的关键点）
		List<SpecialServiceRequest> ssrList = new ArrayList<SpecialServiceRequest>();
		SpecialServiceRequest ssr = new SpecialServiceRequest();
		ssr.setSsrCode("UMNR");
		ssr.setStatus("NN");
		ssr.setAirlineCode("CA");
		ssr.setText("UM7");
		ssr.setFlightRefNumberRPH("1");
		ssr.setTravelerRefNumberRPH("1");
		ssrList.add(ssr);
		
		trInfo.setSsrList(ssrList);
		trList.add(trInfo);
		abmAirReservation.setTravelerInfoList(trList);
		abmAirReservationList.add(abmAirReservation);
		airBookModifyRQ.setAbmAirReservationList(abmAirReservationList);
		
		//3.修改前的预定信息
		AirReservation airReservation = new AirReservation();
		
		//行程信息
		List<OriginDestinationOption> odList = new ArrayList<OriginDestinationOption>();
		OriginDestinationOption od = new OriginDestinationOption();
		List<FlightSegment> fsList = new ArrayList<FlightSegment>();
		FlightSegment fs = new FlightSegment();
			fs.setRph("1");
			fs.setDepartureDateTime("2014-08-26T07:00:00");
			fs.setArrivalDateTime("2014-08-26T09:10:00");
			fs.setCodeshareInd("false");
			fs.setOperatingAirline("MU");						//承运方两字码
			fs.setFlightNumberOfOperatingAirline("5138");		//承运方航班号
			fs.setNumberInParty("1");
			fs.setFlightNumber("5138");   //MU5138 
			fs.setStatus("NN");
			fs.setSegmentType("NORMAL");
			fs.setDepartureAirport("PEK");
			fs.setArrivalAirport("SHA");	//SHA HKG
			fs.setAirEquipType("733");
			fs.setMarketingAirline("MU");
			fs.setResBookDesigCode("Y");
		fsList.add(fs);
		od.setFlightSegmentList(fsList);
		odList.add(od);
		airReservation.setOriginDestinationList(odList);
		
		//旅客信息
		List<TravelerInfo> travelerInfoList = new ArrayList<TravelerInfo>();
		TravelerInfo travelerInfo = new TravelerInfo();
		List<AirTraveler> airTravelerList = new ArrayList<AirTraveler>();
		AirTraveler airTraveler = new AirTraveler();
			airTraveler.setGender("MALE");
			airTraveler.setPassengerTypeCode("CHD");
			airTraveler.setRph("1");
			airTraveler.setComment("HK");
			airTraveler.setBirthDate("20080101");
			/***姓名***/
			List<PersonName> personNameList = new ArrayList<PersonName>();
			PersonName personName = new PersonName();
			personName.setLanguageType("ZH");
			personName.setSurname("高明");
			personNameList.add(personName);
			airTraveler.setPersonNameList(personNameList);
			/***证件信息***/
			List<Document> documentList = new ArrayList<Document>();
			Document document = new Document();
			document.setDocType("NI");
			//身份证生日符合儿童规则时，主机会自动加入SSR CHLD，否则需要对预订航段每个航空公司手工写入SSR CHLD
			document.setDocId("31010420080101573X");
			//document.setBirthDate("20080101");
			documentList.add(document);
			airTraveler.setDocumentList(documentList);
			/***旅客序号***/
			List<TravelerRefNumber> travelerRefNumberList = new ArrayList<TravelerRefNumber>();
			TravelerRefNumber travelerRefNumber = new TravelerRefNumber();
			travelerRefNumber.setRph("1");
			travelerRefNumberList.add(travelerRefNumber);
			airTraveler.setTravelerRefNumberList(travelerRefNumberList);
			airTravelerList.add(airTraveler);
			travelerInfo.setAirTravelerList(airTravelerList);
		travelerInfoList.add(travelerInfo);
		airReservation.setTravelerInfoList(travelerInfoList);

		//PNR号信息
		List<BookingReferenceID> bookingReferenceIDList = new ArrayList<BookingReferenceID>();
		BookingReferenceID bookID = new BookingReferenceID();
		bookID.setId("HPJW56");
		bookingReferenceIDList.add(bookID);
		airReservation.setBookingReferenceIDList(bookingReferenceIDList);
		
		request.setAirBookModifyRQ(airBookModifyRQ);
		request.setAirReservation(airReservation);
		
		return request;
	}
	
	/**
	 * 场景10：添加 TC
	 * @author wanglw
	 * @date 2014-8-14
	 */
	private static AirBookModifyRequest buildRequest10() {
		// 输入参数结构（3大信息）
		// 1.POS信息  2.修改后的预定信息   3. 修改前的预定信息
		
		AirBookModifyRequest request = new AirBookModifyRequest();
		request.setPseudoCityCode("sparrow");
		
		//2.修改后的预定信息
		AirBookModifyRQ airBookModifyRQ = new AirBookModifyRQ();
		airBookModifyRQ.setModificationType("TC_ADD");			// 修改类型
		List<AbmAirReservation> abmAirReservationList = new ArrayList<AbmAirReservation>();
		AbmAirReservation abmAirReservation = new AbmAirReservation();
		abmAirReservation.setTourCode("IT1CA1BJS187");
		abmAirReservationList.add(abmAirReservation);
		airBookModifyRQ.setAbmAirReservationList(abmAirReservationList);
		
		//3.修改前的预定信息
		AirReservation airReservation = new AirReservation();
		
		//PNR号信息
		List<BookingReferenceID> bookingReferenceIDList = new ArrayList<BookingReferenceID>();
		BookingReferenceID bookID = new BookingReferenceID();
		bookID.setId("HPJW56");
		bookingReferenceIDList.add(bookID);
		airReservation.setBookingReferenceIDList(bookingReferenceIDList);
		
		request.setAirBookModifyRQ(airBookModifyRQ);
		request.setAirReservation(airReservation);

		return request;
	}
	
	/**
	 * 场景11：添加航段
	 * @author wanglw
	 * @date 2014-8-14
	 */
	private static AirBookModifyRequest buildRequest11() {
		// 输入参数结构（3大信息）
		// 1.POS信息  2.修改后的预定信息   3. 修改前的预定信息
		
		AirBookModifyRequest request = new AirBookModifyRequest();
		request.setPseudoCityCode("sparrow");
		
		//2.修改后的预定信息
		AirBookModifyRQ airBookModifyRQ = new AirBookModifyRQ();
		airBookModifyRQ.setModificationType("SEGMENT_ADD");			// 修改类型
		List<AbmAirReservation> abmAirReservationList = new ArrayList<AbmAirReservation>();
		AbmAirReservation abmAirReservation = new AbmAirReservation();
		/***行程信息***/
		List<OriginDestinationOption> odList = new ArrayList<OriginDestinationOption>();
		OriginDestinationOption od = new OriginDestinationOption();
		List<FlightSegment> fsList = new ArrayList<FlightSegment>();
		FlightSegment fs = new FlightSegment();
			fs.setRph("1");
			fs.setDepartureDateTime("2014-10-02T07:00:00");
			fs.setArrivalDateTime("2014-10-02T09:10:00");
			fs.setCodeshareInd("false");
			fs.setOperatingAirline("MU");						//承运方两字码
			fs.setFlightNumberOfOperatingAirline("5138");		//承运方航班号
			fs.setNumberInParty("1");
			fs.setFlightNumber("5138");   //MU5138 
			fs.setStatus("NN");
			fs.setSegmentType("NORMAL");
			fs.setDepartureAirport("PEK");
			fs.setArrivalAirport("SHA");	//SHA HKG
			fs.setAirEquipType("733");
			fs.setMarketingAirline("MU");
			fs.setResBookDesigCode("Y");
		fsList.add(fs);
		od.setFlightSegmentList(fsList);
		odList.add(od);
		abmAirReservation.setOriginDestinationList(odList);
		abmAirReservationList.add(abmAirReservation);
		airBookModifyRQ.setAbmAirReservationList(abmAirReservationList);
		
		//3.修改前的预定信息
		AirReservation airReservation = new AirReservation();
		
		//PNR号信息
		List<BookingReferenceID> bookingReferenceIDList = new ArrayList<BookingReferenceID>();
		BookingReferenceID bookID = new BookingReferenceID();
		bookID.setId("JP7D1Z");
		bookingReferenceIDList.add(bookID);
		airReservation.setBookingReferenceIDList(bookingReferenceIDList);
		
		request.setAirBookModifyRQ(airBookModifyRQ);
		request.setAirReservation(airReservation);

		return request;
	}
	
	/**
	 * 场景12：修改航段
	 * @author wanglw
	 * @date 2014-8-14
	 */
	private static AirBookModifyRequest buildRequest12() {
		// 输入参数结构（3大信息）
		// 1.POS信息  2.修改后的预定信息   3. 修改前的预定信息
		
		AirBookModifyRequest request = new AirBookModifyRequest();
		request.setPseudoCityCode("sparrow");
		
		//2.修改后的预定信息
		AirBookModifyRQ airBookModifyRQ = new AirBookModifyRQ();
		airBookModifyRQ.setModificationType("SEGMENT_MODIFY");			// 修改类型
		List<AbmAirReservation> abmAirReservationList = new ArrayList<AbmAirReservation>();
		AbmAirReservation abmAirReservation = new AbmAirReservation();
		/***行程信息***/
		List<OriginDestinationOption> odList = new ArrayList<OriginDestinationOption>();
		OriginDestinationOption od = new OriginDestinationOption();
		List<FlightSegment> fsList = new ArrayList<FlightSegment>();
		FlightSegment fs = new FlightSegment();
			fs.setRph("1");
			fs.setDepartureDateTime("2014-10-02T07:00:00");
			//fs.setArrivalDateTime("2014-08-27T09:10:00");
			fs.setCodeshareInd("false");
			fs.setOperatingAirline("MU");						//承运方两字码
			fs.setFlightNumberOfOperatingAirline("1831");		//承运方航班号
			fs.setNumberInParty("1");
			fs.setFlightNumber("5138");   //MU5138 
			fs.setStatus("NN");
			fs.setSegmentType("NORMAL");
			fs.setDepartureAirport("PEK");
			fs.setArrivalAirport("SHA");	//SHA HKG
			fs.setAirEquipType("733");
			fs.setMarketingAirline("MU");
			fs.setResBookDesigCode("Y");
		fsList.add(fs);
		od.setFlightSegmentList(fsList);
		odList.add(od);
		abmAirReservation.setOriginDestinationList(odList);
		abmAirReservationList.add(abmAirReservation);
		airBookModifyRQ.setAbmAirReservationList(abmAirReservationList);
		
		//3.修改前的预定信息
		AirReservation airReservation = new AirReservation();
		/***行程信息***/
		List<OriginDestinationOption> odList1 = new ArrayList<OriginDestinationOption>();
		OriginDestinationOption od1 = new OriginDestinationOption();
		List<FlightSegment> fsList1 = new ArrayList<FlightSegment>();
		FlightSegment fs1 = new FlightSegment();
			fs1.setRph("1");
			fs1.setDepartureDateTime("2014-10-01T07:00:00");
			fs1.setArrivalDateTime("2014-10-01T09:10:00");
			fs1.setCodeshareInd("false");
			fs1.setOperatingAirline("MU");						//承运方两字码
			fs1.setFlightNumberOfOperatingAirline("5138");		//承运方航班号
			fs1.setNumberInParty("1");
			fs1.setFlightNumber("5138");   //MU5138 
			fs1.setStatus("NN");
			fs1.setSegmentType("NORMAL");
			fs1.setDepartureAirport("PEK");
			fs1.setArrivalAirport("SHA");	//SHA HKG
			fs1.setAirEquipType("733");
			fs1.setMarketingAirline("MU");
			fs1.setResBookDesigCode("Y");
		fsList1.add(fs1);
		od1.setFlightSegmentList(fsList1);
		odList1.add(od1);
		airReservation.setOriginDestinationList(odList1);
		
		//PNR号信息
		List<BookingReferenceID> bookingReferenceIDList = new ArrayList<BookingReferenceID>();
		BookingReferenceID bookID = new BookingReferenceID();
		bookID.setId("HQ1WEK");
		bookingReferenceIDList.add(bookID);
		airReservation.setBookingReferenceIDList(bookingReferenceIDList);
		
		request.setAirBookModifyRQ(airBookModifyRQ);
		request.setAirReservation(airReservation);

		return request;
	}
	
	/**
	 * 场景13：修改证件号
	 * @author wanglw
	 * @date 2014-8-14
	 */
	private static AirBookModifyRequest buildRequest13() {
		// 输入参数结构（3大信息）
		// 1.POS信息  2.修改后的预定信息   3. 修改前的预定信息
		
		AirBookModifyRequest request = new AirBookModifyRequest();
		request.setPseudoCityCode("sparrow");
		
		//2.修改后的预定信息
		AirBookModifyRQ airBookModifyRQ = new AirBookModifyRQ();
		airBookModifyRQ.setModificationType("PASSENGER_FOID_MODIFY");			// 修改类型
		List<AbmAirReservation> abmAirReservationList = new ArrayList<AbmAirReservation>();
		AbmAirReservation abmAirReservation = new AbmAirReservation();
		//旅客信息
		List<TravelerInfo> travelerInfoList = new ArrayList<TravelerInfo>();
		TravelerInfo travelerInfo = new TravelerInfo();
		List<AirTraveler> airTravelerList = new ArrayList<AirTraveler>();
		AirTraveler airTraveler = new AirTraveler();
		airTraveler.setPassengerTypeCode("ADT");
		airTraveler.setAccompaniedByInfant("false");
		/***姓名***/
		List<PersonName> personNameList = new ArrayList<PersonName>();
		PersonName personName = new PersonName();
		personName.setLanguageType("ZH");
		personName.setSurname("高明");
		personNameList.add(personName);
		airTraveler.setPersonNameList(personNameList);
		/***证件信息***/
		List<Document> documentList = new ArrayList<Document>();
		Document document = new Document();
		document.setDocType("NI");
		document.setDocId("220204195901192717");
		documentList.add(document);
		airTraveler.setDocumentList(documentList);
		/***旅客序号***/
		List<TravelerRefNumber> travelerRefNumberList = new ArrayList<TravelerRefNumber>();
		TravelerRefNumber travelerRefNumber = new TravelerRefNumber();
		travelerRefNumber.setRph("1");
		travelerRefNumberList.add(travelerRefNumber);
		airTraveler.setTravelerRefNumberList(travelerRefNumberList);
		
		airTravelerList.add(airTraveler);
		travelerInfo.setAirTravelerList(airTravelerList);
		travelerInfoList.add(travelerInfo);
		abmAirReservation.setTravelerInfoList(travelerInfoList);
		
		abmAirReservationList.add(abmAirReservation);
		airBookModifyRQ.setAbmAirReservationList(abmAirReservationList);
		
		//3.修改前的预定信息
		AirReservation airReservation = new AirReservation();
		
		//PNR号信息
		List<BookingReferenceID> bookingReferenceIDList = new ArrayList<BookingReferenceID>();
		BookingReferenceID bookID = new BookingReferenceID();
		bookID.setId("HPJW8T");
		bookingReferenceIDList.add(bookID);
		airReservation.setBookingReferenceIDList(bookingReferenceIDList);
		
		request.setAirBookModifyRQ(airBookModifyRQ);
		request.setAirReservation(airReservation);

		return request;
	}
	
	/**
	 * 场景14：删除旅客
	 * @author wanglw
	 * @date 2014-8-14
	 */
	private static AirBookModifyRequest buildRequest14() {
		// 输入参数结构（3大信息）
		// 1.POS信息  2.修改后的预定信息   3. 修改前的预定信息
		
		AirBookModifyRequest request = new AirBookModifyRequest();
		request.setPseudoCityCode("sparrow");
		
		//2.修改后的预定信息
		AirBookModifyRQ airBookModifyRQ = new AirBookModifyRQ();
		airBookModifyRQ.setModificationType("PASSENGER_DELETE");			// 修改类型
		List<AbmAirReservation> abmAirReservationList = new ArrayList<AbmAirReservation>();
		AbmAirReservation abmAirReservation = new AbmAirReservation();
		//旅客信息
		List<TravelerInfo> travelerInfoList = new ArrayList<TravelerInfo>();
		TravelerInfo travelerInfo = new TravelerInfo();
		List<AirTraveler> airTravelerList = new ArrayList<AirTraveler>();
		AirTraveler airTraveler = new AirTraveler();
		airTraveler.setPassengerTypeCode("CHD");
		airTraveler.setAccompaniedByInfant("false");
		/***姓名***/
		List<PersonName> personNameList = new ArrayList<PersonName>();
		PersonName personName = new PersonName();
		personName.setLanguageType("ZH");
		personName.setSurname("高一");
		personNameList.add(personName);
		airTraveler.setPersonNameList(personNameList);
		/***旅客序号***/
		List<TravelerRefNumber> travelerRefNumberList = new ArrayList<TravelerRefNumber>();
		TravelerRefNumber travelerRefNumber = new TravelerRefNumber();
		travelerRefNumber.setRph("2");
		travelerRefNumberList.add(travelerRefNumber);
		airTraveler.setTravelerRefNumberList(travelerRefNumberList);
		
		airTravelerList.add(airTraveler);
		travelerInfo.setAirTravelerList(airTravelerList);
		travelerInfoList.add(travelerInfo);
		abmAirReservation.setTravelerInfoList(travelerInfoList);
		
		abmAirReservationList.add(abmAirReservation);
		airBookModifyRQ.setAbmAirReservationList(abmAirReservationList);
		
		//3.修改前的预定信息
		AirReservation airReservation = new AirReservation();
		
		//PNR号信息
		List<BookingReferenceID> bookingReferenceIDList = new ArrayList<BookingReferenceID>();
		BookingReferenceID bookID = new BookingReferenceID();
		bookID.setId("HPJW9Z");
		bookingReferenceIDList.add(bookID);
		airReservation.setBookingReferenceIDList(bookingReferenceIDList);
		
		request.setAirBookModifyRQ(airBookModifyRQ);
		request.setAirReservation(airReservation);

		return request;
	}
	
	/**
	 * 场景15：取消航段
	 * @author wanglw
	 * @date 2014-8-14
	 */
	private static AirBookModifyRequest buildRequest15() {
		// 输入参数结构（3大信息）
		// 1.POS信息  2.修改后的预定信息   3. 修改前的预定信息
		
		AirBookModifyRequest request = new AirBookModifyRequest();
		request.setPseudoCityCode("sparrow");
		
		//2.修改后的预定信息
		AirBookModifyRQ airBookModifyRQ = new AirBookModifyRQ();
		airBookModifyRQ.setModificationType("SEGMENT_CANCEL");			// 修改类型
		List<AbmAirReservation> abmAirReservationList = new ArrayList<AbmAirReservation>();
		AbmAirReservation abmAirReservation = new AbmAirReservation();
		/***行程信息***/
		List<OriginDestinationOption> odList = new ArrayList<OriginDestinationOption>();
		OriginDestinationOption od = new OriginDestinationOption();
		List<FlightSegment> fsList1 = new ArrayList<FlightSegment>();
		FlightSegment fs1 = new FlightSegment();
			fs1.setRph("2");
			fs1.setDepartureDateTime("2014-08-30T09:30:00");//0930
			fs1.setArrivalDateTime("2014-08-30T11:55:00");
			fs1.setCodeshareInd("false");
			fs1.setFlightNumber("5139");   //MU5138 
			fs1.setStatus("DK");
			fs1.setNumberInParty("1");
			//fs1.setTicketType(ticketType);
			fs1.setSegmentType("NORMAL");
			fs1.setDepartureAirport("SHA");
			fs1.setArrivalAirport("PEK");	//SHA HKG
			fs1.setAirEquipType("733");
			fs1.setMarketingAirline("MU");
			fs1.setResBookDesigCode("Y");
		fsList1.add(fs1);
		od.setFlightSegmentList(fsList1);
		odList.add(od);
		abmAirReservation.setOriginDestinationList(odList);
		abmAirReservationList.add(abmAirReservation);
		airBookModifyRQ.setAbmAirReservationList(abmAirReservationList);
		
		//3.修改前的预定信息
		AirReservation airReservation = new AirReservation();
		//PNR号信息
		List<BookingReferenceID> bookingReferenceIDList = new ArrayList<BookingReferenceID>();
		BookingReferenceID bookID = new BookingReferenceID();
		bookID.setId("JG6R88");
		bookingReferenceIDList.add(bookID);
		airReservation.setBookingReferenceIDList(bookingReferenceIDList);
		
		request.setAirBookModifyRQ(airBookModifyRQ);
		request.setAirReservation(airReservation);

		return request;
	}
	
	/**
	 * 场景16：取消 PNR
	 * @author wanglw
	 * @date 2014-8-14
	 */
	private static AirBookModifyRequest buildRequest16() {
		// 输入参数结构（3大信息）
		// 1.POS信息  2.修改后的预定信息   3. 修改前的预定信息
		
		AirBookModifyRequest request = new AirBookModifyRequest();
		request.setPseudoCityCode("sparrow");
		
		//2.修改后的预定信息
		AirBookModifyRQ airBookModifyRQ = new AirBookModifyRQ();
		String modificationType = "PNR_CANCEL"; 					// 修改类型
		airBookModifyRQ.setModificationType(modificationType);
		
		//3.修改前的预定信息
		AirReservation airReservation = new AirReservation();
		
		//PNR号信息
		List<BookingReferenceID> bookingReferenceIDList = new ArrayList<BookingReferenceID>();
		BookingReferenceID bookID = new BookingReferenceID();
		bookID.setId("JSQR1S");
		bookingReferenceIDList.add(bookID);
		airReservation.setBookingReferenceIDList(bookingReferenceIDList);
		
		request.setAirBookModifyRQ(airBookModifyRQ);
		request.setAirReservation(airReservation);

		return request;
	}
	
	/**
	 * 场景17：按行删除 PNR 信息
	 * @author wanglw
	 * @date 2014-8-14
	 */
	private static AirBookModifyRequest buildRequest17() {
		// 输入参数结构（3大信息）
		// 1.POS信息  2.修改后的预定信息   3. 修改前的预定信息
		
		AirBookModifyRequest request = new AirBookModifyRequest();
		request.setPseudoCityCode("sparrow");
		
		//2.修改后的预定信息
		AirBookModifyRQ airBookModifyRQ = new AirBookModifyRQ();
		String modificationType = "ITEM_CANCEL"; 					// 修改类型
		airBookModifyRQ.setModificationType(modificationType);
		List<AbmAirReservation> abmAirReservationList = new ArrayList<AbmAirReservation>();
		AbmAirReservation abmAirReservation = new AbmAirReservation();
		//PNR号信息
		List<BookingReferenceID> brIDList1 = new ArrayList<BookingReferenceID>();
		BookingReferenceID bookID1 = new BookingReferenceID();
		bookID1.setIdContext("6");
		brIDList1.add(bookID1);
		abmAirReservation.setBookingReferenceIDList(brIDList1);
		
		abmAirReservationList.add(abmAirReservation);
		airBookModifyRQ.setAbmAirReservationList(abmAirReservationList);
		
		//3.修改前的预定信息
		AirReservation airReservation = new AirReservation();
		
		//PNR号信息
		List<BookingReferenceID> bookingReferenceIDList = new ArrayList<BookingReferenceID>();
		BookingReferenceID bookID = new BookingReferenceID();
		bookID.setId("JP7D1Z");
		bookingReferenceIDList.add(bookID);
		airReservation.setBookingReferenceIDList(bookingReferenceIDList);
		
		request.setAirBookModifyRQ(airBookModifyRQ);
		request.setAirReservation(airReservation);

		return request;
	}
	
	/**
	 * 场景18：RR航段信息
	 * @author wanglw
	 * @date 2014-8-14
	 */
	private static AirBookModifyRequest buildRequest18() {
		// 输入参数结构（3大信息）
		// 1.POS信息  2.修改后的预定信息   3. 修改前的预定信息
		
		AirBookModifyRequest request = new AirBookModifyRequest();
		request.setPseudoCityCode("sparrow");
		
		//2.修改后的预定信息
		AirBookModifyRQ airBookModifyRQ = new AirBookModifyRQ();
		airBookModifyRQ.setModificationType("SEGMENT_RECONFIRM");			// 修改类型
		List<AbmAirReservation> abmAirReservationList = new ArrayList<AbmAirReservation>();
		AbmAirReservation abmAirReservation = new AbmAirReservation();
		/***行程信息***/
		List<OriginDestinationOption> odList = new ArrayList<OriginDestinationOption>();
		OriginDestinationOption od = new OriginDestinationOption();
		List<FlightSegment> fsList = new ArrayList<FlightSegment>();
		FlightSegment fs = new FlightSegment();
			fs.setRph("1");
			fs.setDepartureDateTime("2014-09-17T07:00:00");
			fs.setArrivalDateTime("2014-09-17T09:10:00");
			fs.setCodeshareInd("false");
			fs.setFlightNumber("5138");   //MU5138 
			fs.setStatus("NN");
			fs.setSegmentType("NORMAL");
			fs.setDepartureAirport("PEK");
			fs.setArrivalAirport("SHA");	//SHA HKG
			fs.setAirEquipType("733");
			fs.setMarketingAirline("MU");
			fs.setResBookDesigCode("Y");
		fsList.add(fs);
		od.setFlightSegmentList(fsList);
		odList.add(od);
		abmAirReservation.setOriginDestinationList(odList);
		abmAirReservationList.add(abmAirReservation);
		airBookModifyRQ.setAbmAirReservationList(abmAirReservationList);
		
		//3.修改前的预定信息
		AirReservation airReservation = new AirReservation();
		//PNR号信息
		List<BookingReferenceID> bookingReferenceIDList = new ArrayList<BookingReferenceID>();
		BookingReferenceID bookID = new BookingReferenceID();
		bookID.setId("JP7D25");
		bookingReferenceIDList.add(bookID);
		airReservation.setBookingReferenceIDList(bookingReferenceIDList);
		
		request.setAirBookModifyRQ(airBookModifyRQ);
		request.setAirReservation(airReservation);

		return request;
	}
	
	/**
	 * 场景19：PNR分离
	 * @author wanglw
	 * @date 2014-8-14
	 */
	private static AirBookModifyRequest buildRequest19() {
		// 输入参数结构（3大信息）
		// 1.POS信息  2.修改后的预定信息   3. 修改前的预定信息
		
		AirBookModifyRequest request = new AirBookModifyRequest();
		request.setPseudoCityCode("sparrow");
		
		//2.修改后的预定信息
		AirBookModifyRQ airBookModifyRQ = new AirBookModifyRQ();
		airBookModifyRQ.setModificationType("PNR_SPLIT");			// 修改类型
		List<AbmAirReservation> abmAirReservationList = new ArrayList<AbmAirReservation>();
		AbmAirReservation abmAirReservation = new AbmAirReservation();
		//旅客信息
		List<TravelerInfo> travelerInfoList = new ArrayList<TravelerInfo>();
		TravelerInfo travelerInfo = new TravelerInfo();
		List<AirTraveler> airTravelerList = new ArrayList<AirTraveler>();
		/******旅客1******/
		AirTraveler airTraveler = new AirTraveler();
		airTraveler.setPassengerTypeCode("ADT");
		airTraveler.setAccompaniedByInfant("false");
		/***姓名***/
		List<PersonName> personNameList = new ArrayList<PersonName>();
		PersonName personName = new PersonName();
		personName.setLanguageType("ZH");
		personName.setSurname("高明");
		personNameList.add(personName);
		airTraveler.setPersonNameList(personNameList);
		/***证件信息***/
		List<Document> documentList = new ArrayList<Document>();
		Document document = new Document();
		document.setDocType("NI");
		document.setDocId("120221197001011150");
		documentList.add(document);
		airTraveler.setDocumentList(documentList);
		/***旅客序号***/
		List<TravelerRefNumber> travelerRefNumberList = new ArrayList<TravelerRefNumber>();
		TravelerRefNumber travelerRefNumber = new TravelerRefNumber();
		travelerRefNumber.setRph("1");
		travelerRefNumberList.add(travelerRefNumber);
		airTraveler.setTravelerRefNumberList(travelerRefNumberList);
		
		airTravelerList.add(airTraveler);
		
		/******旅客2******/
		AirTraveler airTraveler1 = new AirTraveler();
		airTraveler1.setGender("MALE");
		airTraveler1.setPassengerTypeCode("ADT");			
		/***姓名***/
		List<PersonName> personNameList1 = new ArrayList<PersonName>();
		PersonName personName1 = new PersonName();
		personName1.setLanguageType("ZH");
		personName1.setSurname("高一");
		personNameList1.add(personName1);
		airTraveler1.setPersonNameList(personNameList1);
		/***证件信息***/
		List<Document> documentList1 = new ArrayList<Document>();
		Document document1 = new Document();
		document1.setDocType("NI");
		document1.setDocId("120221197001011150");
		documentList1.add(document1);
		airTraveler1.setDocumentList(documentList1);
		/***旅客序号***/
		List<TravelerRefNumber> travelerRefNumberList1 = new ArrayList<TravelerRefNumber>();
		TravelerRefNumber travelerRefNumber1 = new TravelerRefNumber();
		travelerRefNumber1.setRph("1");
		travelerRefNumberList1.add(travelerRefNumber1);
		
		airTraveler1.setRph("2");
		airTraveler1.setComment("HK");
		airTraveler1.setTravelerRefNumberList(travelerRefNumberList1);
		
		airTravelerList.add(airTraveler1);
		
		travelerInfo.setAirTravelerList(airTravelerList);
		travelerInfoList.add(travelerInfo);
		abmAirReservation.setTravelerInfoList(travelerInfoList);
		
		abmAirReservationList.add(abmAirReservation);
		airBookModifyRQ.setAbmAirReservationList(abmAirReservationList);
		
		//3.修改前的预定信息
		AirReservation airReservation = new AirReservation();
		
		//PNR号信息
		List<BookingReferenceID> bookingReferenceIDList = new ArrayList<BookingReferenceID>();
		BookingReferenceID bookID = new BookingReferenceID();
		bookID.setId("HR9W1J");      //要分离的PNR
		bookingReferenceIDList.add(bookID);
		airReservation.setBookingReferenceIDList(bookingReferenceIDList);
		
		request.setAirBookModifyRQ(airBookModifyRQ);
		request.setAirReservation(airReservation);

		return request;
	}
	
	/**
	 * 场景20：NO位PNR补位
	 * @author wanglw
	 * @date 2014-8-14
	 */
	private static AirBookModifyRequest buildRequest20() {
		// 输入参数结构（3大信息）
		// 1.POS信息  2.修改后的预定信息   3. 修改前的预定信息
		
		AirBookModifyRequest request = new AirBookModifyRequest();
		request.setPseudoCityCode("sparrow");
		
		//2.修改后的预定信息
		AirBookModifyRQ airBookModifyRQ = new AirBookModifyRQ();
		airBookModifyRQ.setModificationType("SEGMENT_NO");			// 修改类型
		airBookModifyRQ.setModificationInfo("K");
		request.setAirBookModifyRQ(airBookModifyRQ);
		
		//3.修改前的预定信息
		AirReservation airReservation = new AirReservation();
		
		//PNR号信息
		List<BookingReferenceID> bookingReferenceIDList = new ArrayList<BookingReferenceID>();
		BookingReferenceID bookID = new BookingReferenceID();
		bookID.setId("JSQRB7");				
		bookingReferenceIDList.add(bookID);
		airReservation.setBookingReferenceIDList(bookingReferenceIDList);
		
		request.setAirReservation(airReservation);

		return request;
	}
	
	/**
	 * 场景21：修改RMK
	 * @author wanglw
	 * @date 2014-8-14
	 */
	private static AirBookModifyRequest buildRequest21() {
		// 输入参数结构（3大信息）
		// 1.POS信息  2.修改后的预定信息    3.修改前的预定信息
		
		AirBookModifyRequest request = new AirBookModifyRequest();
		request.setPseudoCityCode("sparrow");
		
		//2.修改后的预定信息
		AirBookModifyRQ airBookModifyRQ = new AirBookModifyRQ();
		airBookModifyRQ.setModificationType("REMARK_MODIFY");				//修改类型
		List<AbmAirReservation> abmAirReservationList = new ArrayList<AbmAirReservation>();
		AbmAirReservation abmAirReservation = new AbmAirReservation();
		List<TravelerInfo> trList = new ArrayList<TravelerInfo>();
		TravelerInfo trInfo = new TravelerInfo();
		
		//要修改的RMK信息（修改的关键点）
		List<SpecialRemark> remarkList = new ArrayList<SpecialRemark>();
		SpecialRemark rmk = new SpecialRemark();
		rmk.setTravelerRefNumberRPH("1");
		rmk.setFlightRefNumberRPH("1");
		rmk.setText("测试修改RMK");
		remarkList.add(rmk);
		trInfo.setRemarkList(remarkList);
		
		trList.add(trInfo);
		abmAirReservation.setTravelerInfoList(trList);
		abmAirReservationList.add(abmAirReservation);
		airBookModifyRQ.setAbmAirReservationList(abmAirReservationList);
		
		//3.修改前的预定信息
		AirReservation airReservation = new AirReservation();
		//旅客信息
		List<TravelerInfo> travelerInfoList = new ArrayList<TravelerInfo>();
		TravelerInfo travelerInfo = new TravelerInfo();
		//要添加的RMK信息（修改的关键点）
		List<SpecialRemark> remarkList1 = new ArrayList<SpecialRemark>();
		SpecialRemark rmk1 = new SpecialRemark();
		rmk1.setText("特殊备注组信息");
		remarkList1.add(rmk1);
		travelerInfo.setRemarkList(remarkList1);
		
		travelerInfoList.add(travelerInfo);
		airReservation.setTravelerInfoList(travelerInfoList);

		//PNR号信息
		List<BookingReferenceID> bookingReferenceIDList = new ArrayList<BookingReferenceID>();
		BookingReferenceID bookID = new BookingReferenceID();
		bookID.setId("JG6R8X");
		bookingReferenceIDList.add(bookID);
		airReservation.setBookingReferenceIDList(bookingReferenceIDList);
		
		request.setAirBookModifyRQ(airBookModifyRQ);
		request.setAirReservation(airReservation);
		
		return request;
	}
	
	/**
	 * 场景22：修改OSI
	 * @author wanglw
	 * @date 2014-8-14
	 */
	private static AirBookModifyRequest buildRequest22() {
		// 输入参数结构（3大信息）
		// 1.POS信息  2.修改后的预定信息    3.修改前的预定信息
		
		AirBookModifyRequest request = new AirBookModifyRequest();
		request.setPseudoCityCode("sparrow");
		
		//2.修改后的预定信息
		AirBookModifyRQ airBookModifyRQ = new AirBookModifyRQ();
		airBookModifyRQ.setModificationType("OSI_MODIFY");				//修改类型
		List<AbmAirReservation> abmAirReservationList = new ArrayList<AbmAirReservation>();
		AbmAirReservation abmAirReservation = new AbmAirReservation();
		List<TravelerInfo> trList = new ArrayList<TravelerInfo>();
		TravelerInfo trInfo = new TravelerInfo();
		
		//要修改的OSI信息（修改的关键点）
		List<OtherServiceInformation> osiList = new ArrayList<OtherServiceInformation>();
		OtherServiceInformation osi = new OtherServiceInformation();
		osi.setOsiCode("OTHS");
		osi.setRph("1");
		osi.setAirlineCode("CA");
		osi.setText("OSI");
		osiList.add(osi);
		
		trInfo.setOsiList(osiList);
		trList.add(trInfo);
		abmAirReservation.setTravelerInfoList(trList);
		abmAirReservationList.add(abmAirReservation);
		airBookModifyRQ.setAbmAirReservationList(abmAirReservationList);
		
		//3.修改前的预定信息
		AirReservation airReservation = new AirReservation();
		//旅客信息
		List<TravelerInfo> travelerInfoList = new ArrayList<TravelerInfo>();
		TravelerInfo travelerInfo = new TravelerInfo();
		
		//要修改的OSI信息（修改的关键点）
		List<OtherServiceInformation> osiList1 = new ArrayList<OtherServiceInformation>();
		OtherServiceInformation osi1 = new OtherServiceInformation();
		osi1.setOsiCode("OTHS");
		osi1.setRph("1");
		osi1.setAirlineCode("CA");
		osi1.setText("CTCT13666666666");
		osiList1.add(osi1);
		travelerInfo.setOsiList(osiList1);
		
		travelerInfoList.add(travelerInfo);
		airReservation.setTravelerInfoList(travelerInfoList);

		//PNR号信息
		List<BookingReferenceID> bookingReferenceIDList = new ArrayList<BookingReferenceID>();
		BookingReferenceID bookID = new BookingReferenceID();
		bookID.setId("JG6R93");
		bookingReferenceIDList.add(bookID);
		airReservation.setBookingReferenceIDList(bookingReferenceIDList);
		
		request.setAirBookModifyRQ(airBookModifyRQ);
		request.setAirReservation(airReservation);
		
		return request;
	}
	
	/**
	 * 场景23：删除OSI
	 * @author wanglw
	 * @date 2014-8-14
	 */
	private static AirBookModifyRequest buildRequest23() {
		// 输入参数结构（3大信息）
		// 1.POS信息  2.修改后的预定信息    3.修改前的预定信息
		
		AirBookModifyRequest request = new AirBookModifyRequest();
		request.setPseudoCityCode("sparrow");
		
		//2.修改后的预定信息
		AirBookModifyRQ airBookModifyRQ = new AirBookModifyRQ();
		airBookModifyRQ.setModificationType("OSI_DELETE");				//修改类型
		
		//3.修改前的预定信息
		AirReservation airReservation = new AirReservation();
		
		List<TravelerInfo> trList = new ArrayList<TravelerInfo>();
		TravelerInfo trInfo = new TravelerInfo();
		//要修改的OSI信息（修改的关键点）
		List<OtherServiceInformation> osiList = new ArrayList<OtherServiceInformation>();
		OtherServiceInformation osi = new OtherServiceInformation();
		osi.setOsiCode("OTHS");
		osi.setRph("1");
		osi.setAirlineCode("CA");
		osi.setText("OSI");
		osiList.add(osi);
		trInfo.setOsiList(osiList);
		trList.add(trInfo);
		airReservation.setTravelerInfoList(trList);

		//PNR号信息
		List<BookingReferenceID> bookingReferenceIDList = new ArrayList<BookingReferenceID>();
		BookingReferenceID bookID = new BookingReferenceID();
		bookID.setId("JG6R93");
		bookingReferenceIDList.add(bookID);
		airReservation.setBookingReferenceIDList(bookingReferenceIDList);
		
		request.setAirBookModifyRQ(airBookModifyRQ);
		request.setAirReservation(airReservation);
		
		return request;
	}
	
	/**
	 * 场景24：删除RMK
	 * @author wanglw
	 * @date 2014-8-14
	 */
	private static AirBookModifyRequest buildRequest24() {
		// 输入参数结构（3大信息）
		// 1.POS信息  2.修改后的预定信息    3.修改前的预定信息
		
		AirBookModifyRequest request = new AirBookModifyRequest();
		request.setPseudoCityCode("sparrow");
		
		//2.修改后的预定信息
		AirBookModifyRQ airBookModifyRQ = new AirBookModifyRQ();
		airBookModifyRQ.setModificationType("REMARK_DELETE");				//修改类型
		
		//3.修改前的预定信息
		AirReservation airReservation = new AirReservation();
		//旅客信息
		List<TravelerInfo> travelerInfoList = new ArrayList<TravelerInfo>();
		TravelerInfo travelerInfo = new TravelerInfo();
		//要添加的RMK信息（修改的关键点）
		List<SpecialRemark> remarkList1 = new ArrayList<SpecialRemark>();
		SpecialRemark rmk1 = new SpecialRemark();
		rmk1.setText("测试修改RMK");
		remarkList1.add(rmk1);
		travelerInfo.setRemarkList(remarkList1);
		
		travelerInfoList.add(travelerInfo);
		airReservation.setTravelerInfoList(travelerInfoList);
		
		//PNR号信息
		List<BookingReferenceID> bookingReferenceIDList = new ArrayList<BookingReferenceID>();
		BookingReferenceID bookID = new BookingReferenceID();
		bookID.setId("JG6R8X");
		bookingReferenceIDList.add(bookID);
		airReservation.setBookingReferenceIDList(bookingReferenceIDList);
		
		request.setAirBookModifyRQ(airBookModifyRQ);
		request.setAirReservation(airReservation);
		
		return request;
	}
	
 }