package com.sparrow.ibe.bookingservice.airbookmodify.validation;

import com.sparrow.ibe.bookingservice.airbook.model.AirReservation;
import com.sparrow.ibe.bookingservice.airbook.model.BookingReferenceID;
import com.sparrow.ibe.bookingservice.airbookmodify.model.AirBookModifyRequest;
import com.sparrow.ibe.bookingservice.airbookmodify.model.AirBookModifyResponse;
import com.sparrow.ibe.enums.IBEError;
import com.sparrow.ibe.model.DefaultError;
import com.sparrow.integration.handler.ValidationHandler;
import com.sparrow.utils.StringUtils;
import com.sparrow.utils.WebServiceUtil;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 接口类别：预订服务
 * 接口名称：自动修改服务
 * 接口ID：JP012
 * 自动修改服务的验证处理器
 * @author gengbl
 * @date 2014-08-15
 */
@Component("airBookModifyValidationHandler")
public class AirBookModifyValidationHandler implements ValidationHandler<AirBookModifyRequest> {

	private Serializable validationResult;
	
	@Override
	public boolean validate(String userId, String password, AirBookModifyRequest request) {
		boolean flag = false;
		AirBookModifyResponse validationResult = new AirBookModifyResponse();
		List<DefaultError> errorList = new ArrayList<DefaultError>();
		
		//验证用户名和密码是否正确
		if (!(WebServiceUtil.checkUser(userId, password))) {
			DefaultError defaultError = new DefaultError();
			defaultError.setCode(IBEError.SYSTEM_ERROR_002.getCode());
			defaultError.setCnMessage(IBEError.SYSTEM_ERROR_002.getCnMessage());
			defaultError.setShortText(IBEError.SYSTEM_ERROR_002.getEnMessage());
			errorList.add(defaultError);
		}
		
		//验证office代码是否为空
		String pseudoCityCode = request.getPseudoCityCode();
		if(StringUtils.isEmpty(pseudoCityCode)){
			DefaultError defaultError = new DefaultError();
			defaultError.setCode(IBEError.BUSINESS_ERROR_500.getCode());
			defaultError.setCnMessage(IBEError.BUSINESS_ERROR_500.getCnMessage());
			defaultError.setShortText(IBEError.BUSINESS_ERROR_500.getEnMessage());
			errorList.add(defaultError);
		} else {
			// 验证Office号格式是否有效：office代码，大写，例如：BJS723
			String str = "^([A-Z0-9])+$";
			Pattern p = Pattern.compile(str);
			Matcher m = p.matcher(pseudoCityCode);
			boolean mflag = m.matches();
			if (!mflag) {
				DefaultError defaultError = new DefaultError();
				defaultError.setCode(IBEError.BUSINESS_ERROR_507.getCode());
				defaultError.setCnMessage(IBEError.BUSINESS_ERROR_507.getCnMessage());
				defaultError.setShortText(IBEError.BUSINESS_ERROR_507.getEnMessage());
				errorList.add(defaultError);
			}
		}
		
		if (pnrIsEmpty(request)) {
			DefaultError defaultError = new DefaultError();
			defaultError.setCode(IBEError.BUSINESS_ERROR_560.getCode());
			defaultError.setCnMessage(IBEError.BUSINESS_ERROR_560.getCnMessage());
			defaultError.setShortText(IBEError.BUSINESS_ERROR_560.getEnMessage());
			errorList.add(defaultError);
		}

		validationResult.setErrorList(errorList);
		setValidationResult(validationResult);
		if(errorList.isEmpty()){
			flag = true;
		}
		return flag;
	}

	/**
	 * 验证pnr号是否为空
	 *
	 * @param request
	 */
	private boolean pnrIsEmpty(AirBookModifyRequest request) {
		boolean flag = true;
		AirReservation airReservation = request.getAirReservation();
		if (airReservation == null) {
			return flag;
		}
		List<BookingReferenceID> bookingReferenceIDList = airReservation.getBookingReferenceIDList();
		if (bookingReferenceIDList == null || bookingReferenceIDList.isEmpty()) {
			return flag;
		}
		for(BookingReferenceID bookingReferenceID : bookingReferenceIDList){
			if (bookingReferenceID == null) {
				continue;
			}
			String pnr = bookingReferenceID.getId();
			if(StringUtils.isNotEmpty(pnr)){
				flag = false;
				break;
			}
		}
		return flag;
	}


	@Override
	public void setValidationResult(Serializable validationResult) {
		this.validationResult = validationResult;
	}

	@Override
	public Serializable getValidationResult() {
		return this.validationResult;
	}
	
}
