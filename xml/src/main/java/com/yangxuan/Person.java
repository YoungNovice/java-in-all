package com.yangxuan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Data
@AllArgsConstructor
@XmlRootElement(name = "human")
@Generated
public class Person {

    private Map<String, String> extendProps;

    private String name;

    private String sex;

    private String addr;

    private String area;

    public Person() {
    }

    public Map<String, String> getExtendProps() {
        return extendProps;
    }

    @XmlElementWrapper(name = "extendProps")
    public void setExtendProps(Map<String, String> extendProps) {
        this.extendProps = extendProps;
    }

}
