package com.sparrow.ibe.bookingservice.airbook.validation;

import com.sparrow.ibe.bookingservice.airbook.model.AirBookRequest;
import com.sparrow.ibe.bookingservice.airbook.model.AirBookResponse;
import com.sparrow.ibe.bookingservice.airbook.model.AirTraveler;
import com.sparrow.ibe.bookingservice.airbook.model.PersonName;
import com.sparrow.ibe.enums.IBEError;
import com.sparrow.ibe.model.DefaultError;
import com.sparrow.ibe.utils.IBEUtils;
import com.sparrow.integration.handler.ValidationHandler;
import com.sparrow.utils.WebServiceUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 自动预订服务的验证处理器
 *
 * @author wangjc
 * @date 2014-08-11
 */
@Component("airBookValidationHandler")
public class AirBookValidationHandler implements ValidationHandler<AirBookRequest> {

    private Serializable validationResult;

    @Override
    public boolean validate(String userId, String password, AirBookRequest request) {
        boolean flag = false;

        AirBookResponse validationResult = new AirBookResponse();
        List<DefaultError> errorList = new ArrayList<DefaultError>();

        //验证用户名和密码是否正确
        if (!WebServiceUtil.checkUser(userId, password)) {
            DefaultError error = new DefaultError();
            error.setCode(IBEError.SYSTEM_ERROR_002.getCode());
            error.setCnMessage(IBEError.SYSTEM_ERROR_002.getCnMessage());
            error.setShortText(IBEError.SYSTEM_ERROR_002.getEnMessage());
            errorList.add(error);
        }

        //验证office代码是否为空、office代码格式是否正确
        String officeCode = request.getPseudoCityCode();
        if (StringUtils.isEmpty(officeCode)) {
            DefaultError error = new DefaultError();
            error.setCode(IBEError.BUSINESS_ERROR_500.getCode());
            error.setCnMessage(IBEError.BUSINESS_ERROR_500.getCnMessage());
            error.setShortText(IBEError.BUSINESS_ERROR_500.getEnMessage());
            errorList.add(error);
        } else if (!IBEUtils.isValidOfficeCode(officeCode)) {
            DefaultError error = new DefaultError();
            error.setCode(IBEError.BUSINESS_ERROR_507.getCode());
            error.setCnMessage(IBEError.BUSINESS_ERROR_507.getCnMessage());
            error.setShortText(IBEError.BUSINESS_ERROR_507.getEnMessage());
            errorList.add(error);
        }

        List<AirTraveler> airTravelerList = request.getAirTravelerList();
        if (airTravelerList == null) {
            //验证旅客信息是否为空
            DefaultError error = new DefaultError();
            error.setCode(IBEError.BUSINESS_ERROR_530.getCode());
            error.setCnMessage(IBEError.BUSINESS_ERROR_530.getCnMessage());
            error.setShortText(IBEError.BUSINESS_ERROR_530.getEnMessage());
            errorList.add(error);
        } else if (existsDuplicatedSurname(airTravelerList)) {
            DefaultError error = new DefaultError();
            error.setCnMessage("同一个订单中不允许出现同名旅客或者拼音相同的情况");
            errorList.add(error);
        }

        validationResult.setErrorList(errorList);
        setValidationResult(validationResult);

        if (errorList.size() < 1) {
            flag = true;
        }

        return flag;
    }

    /**
     * 判断同一个订单中是否存在同名旅客或者拼音相同的情况
     *
     * @param airTravelerList
     * @return
     */
    private boolean existsDuplicatedSurname(List<AirTraveler> airTravelerList) {
        Map<String, Integer> map = new HashMap<String, Integer>(2);
        String surname = null;
        for (AirTraveler item : airTravelerList) {
            List<PersonName> personNameList = item.getPersonNameList();
            if (personNameList == null) {
                continue;
            }
            for (PersonName pnItem : personNameList) {
                surname = pnItem.getSurname();
                if (map.get(surname) != null) {
                    map.put(surname, map.get(surname) + 1);
                    break;
                } else {
                    map.put(surname, 1);
                }
            }
        }

        return (surname != null && map.get(surname) > 1);
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
