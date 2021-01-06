package com;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XMLToMapUtil3 {

    String xmlstr = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
            "<alipay>\n" +
            "<is_success>T</is_success>\n" +
            "<request>\n" +
            "<param name=\"out_trade_no\">2088022099716000</param>\n" +
            "<param name=\"partner\">2088721410829743</param>\n" +
            "<param name=\"_input_charset\">utf-8</param>\n" +
            "<param name=\"service\">alipay.acquire.refund</param>\n" +
            "<param name=\"sign\">7fb81444f669e9677ab32fe0527f659d</param>\n" +
            "<param name=\"refund_amount\">0.01</param>\n" +
            "<param name=\"sign_type\">MD5</param>\n" +
            "</request>\n" +
            "<response>\n" +
            "<alipay>\n" +
            "<buyer_logon_id>151****8169</buyer_logon_id>\n" +
            "<buyer_user_id>2088022099716000</buyer_user_id>\n" +
            "<fund_change>Y</fund_change>\n" +
            "<gmt_refund_pay>2018-06-14 14:53:02</gmt_refund_pay>\n" +
            "<out_trade_no>2088022099716267</out_trade_no>\n" +
            "<refund_fee>0.01</refund_fee>\n" +
            "<result_code>SUCCESS</result_code>\n" +
            "<trade_no>2018061421001004260599050597</trade_no>\n" +
            "</alipay>\n" +
            "</response>\n" +
            "<sign>19268a328722a3f71575629990719299</sign>\n" +
            "<sign_type>MD5</sign_type>\n" +
            "</alipay>";//xml报文

    /**
     * @Author : lilong
     * @Description :多层xml  转 map
     * @Date : 15:37 2018/6/14
     * @Param : * xml格式字符串
     **/
    public Map<String, Object> xmlToMap(String resultXml) {
        Document doc = null;
        try {
            doc = DocumentHelper.parseText(resultXml);
        } catch (DocumentException e) {
            //logger.error("parse text error : " + e);
        }
        Element rootElement = doc.getRootElement();
        Map<String,Object> mapXml = new HashMap<String,Object>();
        elementToMap(mapXml,rootElement);
        return mapXml;
    }



    /**
     * 遍历子节点
     * @param map
     * @param rootElement
     */
    public  void elementToMap(Map<String, Object> map, Element rootElement){
        //获得当前节点的子节点
        List<Element> elements = rootElement.elements();
        Map<String,Object> childMap = new HashMap<String,Object>();
        //如果还存在子节点，就考虑list情况，继续递归
        for (Element element : elements) {
            List<Element> es = element.elements();
            if(es.size()>0){
                //获取当前节点下的子节点
                ArrayList<Map> list = new ArrayList<Map>();
                for(Element e:es){
                    elementChildToList(list,e);
                }
                map.put(element.getName(), list);
            }else{
                map.put(element.getName(),element.getText());
            }

        }
    }



    /**
     * 递归子节点
     * @param arrayList
     * @param rootElement
     */
    public  void elementChildToList(ArrayList<Map> arrayList, Element rootElement){
        //获得当前节点的子节点
        List<Element> elements = rootElement.elements();
        if(elements.size()>0){
            ArrayList<Map> list = new ArrayList<Map>();
            Map<String,Object> sameTempMap = new HashMap<String,Object>();
            for(Element element:elements){
                elementChildToList(list,element);
                sameTempMap.put(element.getName(), element.getText());
            }
            System.out.println(elements.size());
            arrayList.add(sameTempMap);

        }

    }

}
