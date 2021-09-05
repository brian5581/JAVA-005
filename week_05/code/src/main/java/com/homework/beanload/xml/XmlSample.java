package com.homework.beanload.xml;

import org.springframework.stereotype.Component;

/**
 * xml方式，Bean装配
 * @author lw
 */
@Component
public class XmlSample {
    public XmlSample() {
        System.out.println("Construct Sample");
    }

    public void info() {
        System.out.println("Auto wiring Sample");
    }
}
