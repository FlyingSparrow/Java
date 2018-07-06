package com.sparrow.ibe.enums;

/**
 * 机票接口错误枚举类
 *
 * @author wangjc
 */
public enum IBEError {

    SYSTEM_ERROR_001("-001", "There is a error occurred when performing the request, please contact the administrator to resolve", "执行请求时发生错误，请联系管理员解决"),
    SYSTEM_ERROR_002("-002", "Authentication failed, the username or password of PSP system is incorrect!", "认证失败，进入PSP系统的用户名或密码错误"),
    SYSTEM_ERROR_303("-303", "inner service error", "内部服务异常"),
    SYSTEM_ERROR_9999("-9999", "unknown error", "未知异常"),
    HTTP_ERROR_403("403", "Forbidden", "用户名、密码、IP错误或无权限"),
    BUSINESS_WARNING_10410("-10410", "the ticket information is not found", "出票成功但提取票面信息失败"),

    BUSINESS_ERROR_1("-1", "Biz Error", "业务异常"),
    BUSINESS_ERROR_200("-200", "service is unavailable", "访问的服务不可用"),
    BUSINESS_ERROR_201("-201", "service is not supported", "不支持的服务"),
    BUSINESS_ERROR_202("-202", "service permission denied", "服务访问被拒绝"),
    BUSINESS_ERROR_300("-300", "auth failed", "认证失败"),
    BUSINESS_ERROR_301("-301", "office permission denied", "没有office权限"),
    BUSINESS_ERROR_302("-302", "iata number permission denied", "没有iata number权限"),
    BUSINESS_ERROR_304("-304", "sending  commands  too fast, try again later", "访问速度过快，请稍后再试"),
    BUSINESS_ERROR_305("-305", "transaction times limited", "访问次数限制"),
    BUSINESS_ERROR_306("-306", "too  many  concurrent request for service", "访问频率限制"),
    BUSINESS_ERROR_308("-308", "tlh error", "tlh 无 Result 节点内容返回"),
    BUSINESS_ERROR_400("-400", "Format of xml string is not valid", "输入的请求XML格式无效"),
    BUSINESS_ERROR_401("-401", "input is not complete", "请求信息不完整"),
    BUSINESS_ERROR_402("-402", "input error", "输入错误"),
    BUSINESS_ERROR_500("-500", "Office code is empty", "office代码为空"),
    BUSINESS_ERROR_507("-507", "Office code is not valid", "office代码格式错误"),
    BUSINESS_ERROR_510("-510", "departure datetime is empty", "出发日期时间为空"),
    BUSINESS_ERROR_511("-511", "arrival datetime is empty", "到达日期时间为空"),
    BUSINESS_ERROR_520("-520", "departure location code is empty", "出发地代码为空"),
    BUSINESS_ERROR_521("-521", "arrival location code is empty", "到达地代码为空"),
    BUSINESS_ERROR_530("-530", "Passenger information is empty", "旅客信息为空"),
    BUSINESS_ERROR_531("-531", "Pnr number is empty", "Pnr号为空"),
    BUSINESS_ERROR_540("-540", "ticket number is empty", "票号为空"),
    BUSINESS_ERROR_541("-541", "flight number is empty", "航班号为空"),
    BUSINESS_ERROR_542("-542", "flight class is empty", "舱位代码为空"),
    BUSINESS_ERROR_543("-543", "ticketingCarrier is empty", "出票航空公司为空"),
    BUSINESS_ERROR_551("-551", "action code is empty", "行动代码为空"),
    BUSINESS_ERROR_552("-552", "equipment type is empty", "机型信息为空"),
    BUSINESS_ERROR_560("-560", "pnr locator is empty", "pnr号为空"),
    BUSINESS_ERROR_570("-570", "office code is not valid", "office号无效"),
    BUSINESS_ERROR_573("-573", "location format is not valid", "位置格式无效"),
    BUSINESS_ERROR_574("-574", "departure date is not valid", "起飞时间无效"),
    BUSINESS_ERROR_577("-577", "Segment index is not valid", "航段序号无效"),
    BUSINESS_ERROR_581("-581", "Passenger number is not valid", "旅客序号无效"),
    BUSINESS_ERROR_582("-582", "Pnr number is not valid", "Pnr号格式无效"),
    BUSINESS_ERROR_583("-583", "Fare index is not valid", "运价序号无效"),
    BUSINESS_ERROR_586("-586", "ticket number is not valid", "票号无效"),
    BUSINESS_ERROR_588("-588", "Printer number is not valid", "打印机号无效"),
    BUSINESS_ERROR_593("-593", "freeBaggageAllowance is not valid", "免费行李额无效"),
    BUSINESS_ERROR_700("-700", "office code is not valid", "office号格式错误"),
    BUSINESS_ERROR_730("-730", "passenger type code is not valid", "旅客类型代码错误"),
    BUSINESS_ERROR_731("-731", "passenger index is not valid", "旅客序号错误"),
    BUSINESS_ERROR_750("-750", "fare index is not valid", "运价序号错误"),
    BUSINESS_ERROR_760("-760", "format of pnr locator is not valid", "pnr号格式错误"),
    BUSINESS_ERROR_770("-770", "payment type is not valid", "支付类型格式错误"),
    BUSINESS_ERROR_780("-780", "printer number is not valid", "打印机号错误"),
    BUSINESS_ERROR_790("-790", "format of ticket number is not valid", "票号格式错误"),
    BUSINESS_ERROR_10000("-10000", "response is empty", "返回信息为空"),
    BUSINESS_ERROR_10020("-10020", "passenger not found", "没有找到旅客信息"),
    BUSINESS_ERROR_10030("-10030", "There is no fare information found", "国际运价计算没有可显示的运价"),
    BUSINESS_ERROR_10040("-10040", "action code error", "不可预订的行动代码"),
    BUSINESS_ERROR_10041("-10041", "Fail to demand the ticket! Error when adding fp to pnr", "系统在向PNR中添加FP项时发生错误"),
    BUSINESS_ERROR_10050("-10050", "there is no fare information found", "找不到运价信息"),
    BUSINESS_ERROR_10060("-10060", "pnr does not exist", "pnr不存在"),
    BUSINESS_ERROR_10070("-10070", "pnr has been cancelled already", "pnr已被取消"),
    BUSINESS_ERROR_10080("-10080", "the ticket information is not found", "找不到机票信息"),
    BUSINESS_ERROR_34001("-34001", "segment information is empty", "航段信息为空"),
    BUSINESS_ERROR_41109("-41109", "document info error", "证件信息错误"),
    BUSINESS_ERROR_41040("-41040", "no available seat for booking", "指定的航班在指定的日期不执行或指定的舱位已经无法订取"),
    BUSINESS_ERROR_42001("-42001", "book modify type is empty", "修改类型缺失"),
    BUSINESS_ERROR_43010("-43010", "no authority to retrieve pnr", "无权限提取PNR"),
    BUSINESS_ERROR_50010("-50010", "type of printer error", "打票机类型错误"),
    BUSINESS_ERROR_55001("-55001", "failure to price the refund fee", "计算退票费失败"),
    BUSINESS_ERROR_55011("-55011", "failure to refund the ticket", "退票失败"),
    BUSINESS_ERROR_55012("-55012", "failure to refund the tickets partly", "部分退票失败"),
    BUSINESS_ERROR_55032("-55032", "failure to query the refund form information", "查看退票单信息失败"),
    BUSINESS_ERROR_56000("-56000", "ticket not issued by office code(s)", "机票不是通过office号发出的"),
    BUSINESS_ERROR_56001("-56001", "ticket not issued by iata code(s)", "机票不是通过iata号发出的"),
    BUSINESS_ERROR_60001("-60001", "input too many ticket numbers", "输入的票号信息过多"),
    BUSINESS_ERROR_60003("-60003", "input too many order ids, only one permitted", "输入的预订信息过多"),
    BUSINESS_ERROR_60012("-60012", "order not found", "找不到订单"),
    BUSINESS_ERROR_60013("-60013", "status of order is not valid", "订单状态不合法"),
    BUSINESS_ERROR_60021("-60021", "order and pnr information don't match", "订单和PNR信息不匹配"),
    BUSINESS_ERROR_60022("-60022", "order and ticket information don't match", "票号和订单号不一致"),
    BUSINESS_ERROR_60025("-60025", "failure to validate information", "验证信息失败"),
    BUSINESS_ERROR_60030("-60030", "booking information not found, please input order id or pnr locator correctly", "未找到预订信息，请正确输入订单号或者PNR号"),
    BUSINESS_ERROR_60041("-60041", "failure to finish the transactions partly", "操作部分失败"),
    BUSINESS_ERROR_60042("-60042", "failure to finish the transactions", "操作失败"),
    BUSINESS_ERROR_61001("-61001", "failure to book order", "预订生成订单失败"),
    BUSINESS_ERROR_61101("-61101", "index of passenger should be assigned for SSR", "需要为SSR项指定旅客的序号"),
    BUSINESS_ERROR_PLUS_401("401", "don't satisfy the condition of automatic reieesue, please use other methods", "不符合自动判断条件，请用其他方式处理"),
    BUSINESS_ERROR_PLUS_402("402", "don't satisfy the condition of automatic reieesue, please use other methods", "历史运价错误"),
    BUSINESS_ERROR_PLUS_403("403", "no change reissue rule", "无变更规则"),
    BUSINESS_ERROR_PLUS_404("404", "no matched reissue rule", "没有匹配的变更规则"),
    BUSINESS_ERROR_PLUS_405("405", "change is forbidden under any conditions", "任何条件不得变更"),
    BUSINESS_ERROR_PLUS_406("406", "can not automatic calculation, the change transaction must be processed manually or reissue", "不能进行自动计算，转人工/变更处理"),

    BUSINESS_ERROR_PLUS_407("407", "the validation of change is failure", "变更校验失败"),
    BUSINESS_ERROR_PLUS_408("408", "new fare amount is lowwer the old amount", "变更后的运价低于原运价"),
    //	BUSINESS_ERROR_PLUS_409("409", "new fare amount is lowwer the old amount", "客票部分使用，无法改期或者换开"),
    BUSINESS_ERROR_PLUS_410("410", "reissue rule is not applicable", "变更规则不适用"),
    BUSINESS_ERROR_PLUS_450("450", "not matched servicelevel", "没有匹配的服务等级"),
    BUSINESS_ERROR_PLUS_451("451", "find too many fares", "发现太多运价"),
    BUSINESS_ERROR_PLUS_452("452", "find too many sectors", "发现太多航段"),
    BUSINESS_ERROR_PLUS_453("453", "FN format eror", "FN格式错误"),
    BUSINESS_ERROR_PLUS_454("454", "no fares error", "没有运价计算结果"),
    BUSINESS_ERROR_PLUS_455("455", "rule error", "规则配置错误"),
    BUSINESS_ERROR_PLUS_456("456", "no display rule", "没有显示规则"),
    BUSINESS_ERROR_PLUS_459("459", "fare DB is busy, please retry later", "运价数据库繁忙，请稍后重试"),;


    IBEError(String code, String enMessage, String cnMessage) {
        this.code = code;
        this.enMessage = enMessage;
        this.cnMessage = cnMessage;
    }

    private String code;
    private String enMessage;
    private String cnMessage;

    public String getCode() {
        return code;
    }

    public String getEnMessage() {
        return enMessage;
    }

    public String getCnMessage() {
        return cnMessage;
    }

}
