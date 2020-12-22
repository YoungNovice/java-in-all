package com.yangxuan;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.security.KeyStore;

@XmlRootElement
@Data
@AllArgsConstructor
public class Key {

    private String roomNum;

    private Key() {

    }

}
