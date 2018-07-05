package com.sparrow.ibe.bookingservice.airbook.validation;

import com.sparrow.ibe.bookingservice.airbook.model.AirBookRequest;
import com.sparrow.integration.handler.ValidationHandler;
import org.springframework.stereotype.Component;

import java.io.Serializable;

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

        /*AirBookResponse validationResult = new AirBookResponse();
        List<DefaultError> errorList = new ArrayList<DefaultError>();

        //验证用户名和密码是否正确
        if (!(WebServiceUtil.checkUser(userId, password))) {
            DefaultError defaultError = new DefaultError();
            defaultError.setCode(CommonErrorEnum.ERROR_002.getErrorCode());
            defaultError.setCnMessage(CommonErrorEnum.ERROR_002.getCnMessage());
            defaultError.setShortText(CommonErrorEnum.ERROR_002.getEnMessage());
            errorList.add(defaultError);
        }

        //验证office代码是否为空、office代码格式是否正确
        String pseudoCityCode = request.getPseudoCityCode();
        if (StringUtils.isEmpty(pseudoCityCode)) {
            DefaultError defaultError = new DefaultError();
            defaultError.setCode("-500");
            defaultError.setCnMessage("office代码为空");
            defaultError.setShortText("office code is empty");
            errorList.add(defaultError);
        } else {
            // 验证Office号格式是否有效：office代码，大写，例如：BJS723
            String str = "^([A-Z0-9])+$";
            Pattern p = Pattern.compile(str);
            Matcher m = p.matcher(pseudoCityCode);
            boolean mflag = m.matches();
            if (!mflag) {
                DefaultError defaultError = new DefaultError();
                defaultError.setCode("-507");
                defaultError.setCnMessage("Office格式不对");
                defaultError.setShortText("Office code is not valid!");
                errorList.add(defaultError);
            }
        }

        List<AirTraveler> airTravelerList = request.getAirTravelerList();
        Map<String, Integer> map = new HashMap<String, Integer>();
        String surname = null;
        if (airTravelerList != null) {
            for (AirTraveler item : airTravelerList) {
                List<PersonName> personNameList = item.getPersonNameList();
                if (personNameList != null) {
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
            }
        } else {
            //验证旅客信息是否为空
            DefaultError defaultError = new DefaultError();
            defaultError.setCode("-530");
            defaultError.setCnMessage("旅客信息为空");
            defaultError.setShortText("passenger information is empty");
            errorList.add(defaultError);
        }
        if (surname != null && map.get(surname) > 1) {
            DefaultError error = new DefaultError();
            error.setCnMessage("同一个订单中不允许出现同名旅客或者拼音相同的情况");
            errorList.add(error);
        }
        validationResult.setErrorList(errorList);
        setValidationResult(validationResult);

        if (errorList.size() < 1) {
            flag = true;
        }*/

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
