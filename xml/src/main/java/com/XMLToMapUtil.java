package com;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * sax解析
 *
 * 2019/01/04
 * @author zhoujia
 */
public class XMLToMapUtil {
    public Map<String, Object> parseXml(File filePath) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(filePath);//读取xml文档
        Element root = document.getRootElement();//得到xml文档根节点元素，即最上层的"<xml>"
        Map<String, Object> map = new HashMap<String, Object>();
        this.elementTomap(root, map);
        return map;

    }

    /*** * XmlToMap核心方法，里面有递归调用
     * * @param map
     * * @param ele
     * */
    @SuppressWarnings("unchecked")
    public Map<String, Object> elementTomap(Element outele, Map<String, Object> outmap) {
        List<Element> list = outele.elements();
        int size = list.size();
        if (size == 0) {
            outmap.put(outele.getName(), outele.getTextTrim());
        } else {
            Map<String, Object> innermap = new HashMap<String, Object>();
            int i = 1;
            /*int svc = 0;*/
            for (Element ele1 : list) {
                String eleName = ele1.getName();
                /*if (eleName.equals("Svc")) {
                    svc = svc + 1;
                }*/
                String value = ele1.getText();
                Object obj = innermap.get(eleName);
                if (obj == null) {
                    elementTomap(ele1, innermap);
                } else {
                    if (obj instanceof java.util.Map) {
                        List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
                        list1.add((Map<String, Object>) innermap.remove(eleName));
                        elementTomap(ele1, innermap);
                        list1.add((Map<String, Object>) innermap.remove(eleName));
                        innermap.put(eleName, list1);
                    } else if (obj instanceof String) {
                        // for list same <dom>
                        innermap.put(eleName + i, value);
                        i++;
                    } else {
                        elementTomap(ele1, innermap);
                        Map<String, Object> listValue = (Map<String, Object>) innermap.get(eleName);
                        ((List<Map<String, Object>>) obj).add(listValue);
                        innermap.put(eleName, obj);
                    }

                }
            }
            outmap.put(outele.getName(), innermap);
        }
        return outmap;
    }

    /**
    * @param path
    * 功能测试
    */
    public Map<String, Object> xmlToMap(String path) {
        File file = new File(path);
        try {
            Map<String, Object> map = this.parseXml(file);
            return map;
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

}