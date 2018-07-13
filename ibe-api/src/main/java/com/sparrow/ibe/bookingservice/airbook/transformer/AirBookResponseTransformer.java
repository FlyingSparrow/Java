package com.sparrow.ibe.bookingservice.airbook.transformer;

import com.google.common.collect.Lists;
import com.sparrow.ibe.bookingservice.airbook.vo.AirBookResponseVO;
import com.sparrow.ibe.bookingservice.airbook.vo.FlightSegmentVO;
import com.sparrow.utils.XMLUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 接口类别：预订服务
 * 接口名称：自动预订服务
 * 接口ID：JP011
 * 自动预订服务响应结果的转换器，用于将中航信返回的xml格式的响应结果转换为 AirBookResponseVO 对象
 *
 * 说明：这里只对返回结果中的关键信息进行了解析，并未解析全部信息，例如：返回结果中的 Comment 信息并未解析，
 * 可以根据需要进行解析
 *
 * @author wangjianchun
 * @create 2018/7/11
 */
@Component
public class AirBookResponseTransformer {

    /**
     * 将中航信返回的xml格式的响应结果转换为 AirBookResponseVO 对象
     *
     * @param responseXml 响应结果的xml
     * @return
     */
    public AirBookResponseVO transform(String responseXml) {
        AirBookResponseVO response = new AirBookResponseVO();
        Document document = XMLUtils.getInstance().readXmlFile(responseXml);
        if (document != null) {
            Element rootElement = document.getRootElement();
            parseFlightSegment(rootElement, response);
            parsePnr(response, rootElement);
        }

        return response;
    }

    /**
     * 解析航段信息
     *
     * @param rootElement
     * @param airBookResponseVO
     * @return
     */
    private Element parseFlightSegment(Element rootElement, AirBookResponseVO airBookResponseVO) {
        String xpath = "AirReservation/AirItinerary/FlightSegments/FlightSegment";
        List<Element> list = rootElement.selectNodes(xpath);
        if (list != null) {
            List<FlightSegmentVO> flightSegmentVOList = Lists.newArrayList();
            for (Element element : list) {
                FlightSegmentVO flightSegmentVO = new FlightSegmentVO();
                flightSegmentVO.setDepartureDateTime(element.attributeValue("DepartureDateTime"));
                flightSegmentVO.setArrivalDateTime(element.attributeValue("ArrivalDateTime"));
                flightSegmentVO.setCodeShareInd(element.attributeValue("CodeshareInd"));
                flightSegmentVO.setFlightNumber(element.attributeValue("FlightNumber"));
                flightSegmentVO.setNumberInParty(element.attributeValue("NumberInParty"));
                flightSegmentVO.setStatus(element.attributeValue("Status"));
                flightSegmentVO.setSegmentType(element.attributeValue("SegmentType"));
                flightSegmentVO.setDepartureAirport(element.element("DepartureAirport").attributeValue("LocationCode"));
                flightSegmentVO.setArrivalAirport(element.element("ArrivalAirport").attributeValue("LocationCode"));
                flightSegmentVO.setMarketingAirline(element.element("MarketingAirline").attributeValue("Code"));
                flightSegmentVO.setResBookDesigCode(element.element("BookingClassAvail").attributeValue("ResBookDesigCode"));
                flightSegmentVOList.add(flightSegmentVO);
            }
            airBookResponseVO.setFlightSegmentList(flightSegmentVOList);
        }
        return rootElement;
    }

    /**
     * 解析 PNR
     *
     * @param airBookResponseVO
     * @param rootElement
     */
    private void parsePnr(AirBookResponseVO airBookResponseVO, Element rootElement) {
        Element brElement = (Element) rootElement.selectSingleNode("AirReservation/BookingReferenceID");
        if (brElement != null) {
            airBookResponseVO.setPnr(brElement.attributeValue("ID"));
        }
    }

}
