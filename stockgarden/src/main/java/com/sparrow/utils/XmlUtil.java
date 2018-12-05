package com.sparrow.utils;

import com.sparrow.constants.SysConst;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: XmlUtil</p>
 * <p>Description: XML 操作工具类</p>
 *
 * @author wjc
 * @date 2018/12/5
 */
public class XmlUtil {

    private static final Logger logger = LoggerFactory.getLogger(XmlUtil.class);

    private static class XmlUtilHolder {
        private static final XmlUtil INSTANCE = new XmlUtil();
    }

    public static XmlUtil getInstance() {
        return XmlUtilHolder.INSTANCE;
    }

    private XmlUtil() {
    }

    /**
     * 功能：将字符串格式化为xml格式
     *
     * @author sunyj
     * @date 2014-8-1
     */
    public String formatXml(String str) {
        XMLWriter writer = null;
        StringWriter sw = new StringWriter();
        try {
            Document doc = readXmlFile(str);
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding(SysConst.ENCODING_UTF_8);
            writer = new XMLWriter(sw, format);
            writer.write(doc);
        } catch (IOException e) {
            logger.error("IOException", e);
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                logger.error("IOException", e);
            }
        }
        return sw.toString();
    }


    public Document readXmlFile(File file) {
        Document document = null;
        try {
            if (file.length() > 0) {
                document = new SAXReader().read(file);
            }
        } catch (DocumentException e) {
            logger.error("DocumentException", e);
        } catch (Exception e) {
            logger.error("Exception", e);
        }
        return document;
    }

    public Document readXmlFile(InputStream is) {
        Document document = null;
        try {
            document = new SAXReader().read(is);
        } catch (DocumentException e) {
            logger.error("DocumentException", e);
        } catch (Exception e) {
            logger.error("Exception", e);
        }
        return document;
    }

    public Document readXmlFile(String content) {
        Document document = null;
        try {
            if (StringUtils.isNotEmpty(content) && !content.toUpperCase().startsWith("<HTML>")) {
                document = DocumentHelper.parseText(content);
            }
        } catch (DocumentException e) {
            logger.error("DocumentException", e);
        } catch (Exception e) {
            logger.error("Exception", e);
        }
        return document;
    }

    public String elementText(Element element, String subElementName) {
        return StringUtil.defaultValue(element.elementText(subElementName));
    }

    /**
     * 功能：获取单节点带有命名空间的节点
     *
     * @author wangjc
     * @date 2014-8-24
     */
    public Element getDestElement(Map<String, String> xmlMap, Document doc, String id) {
        // 要获取哪个节点，改这里就可以了
        XPath xpath = doc.createXPath(id);
        xpath.setNamespaceURIs(xmlMap);
        return (Element) xpath.selectSingleNode(doc);
    }

    /**
     * 功能：获取单节点带有命名空间的节点
     *
     * @author wangjc
     * @date 2014-8-24
     */
    public Element getDestElement(Map<String, String> nsMap, Element element, String id) {
        // 要获取哪个节点，改这里就可以了
        XPath xpath = element.createXPath(id);
        xpath.setNamespaceURIs(nsMap);
        return (Element) xpath.selectSingleNode(element);
    }

    /**
     * 获取多节点带有命名空间的节点
     *
     * @param xmlMap
     * @param doc
     * @param id
     * @return
     */
    public List<Node> getDestElements(Map<String, String> xmlMap, Document doc, String id) {
        // 要获取哪个节点，改这里就可以了
        XPath xpath = doc.createXPath(id);
        xpath.setNamespaceURIs(xmlMap);
        return xpath.selectNodes(doc);
    }

    /**
     * 功能：获取多节点带有命名空间的节点
     *
     * @author wangjc
     * @date 2014-8-24
     */
    public List<Node> getDestElements(Map<String, String> nsMap, Element element, String id) {
        // 要获取哪个节点，改这里就可以了
        XPath xpath = element.createXPath(id);
        xpath.setNamespaceURIs(nsMap);
        return xpath.selectNodes(element);
    }

}
