//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.08.08 at 11:11:39 PM GST 
//


package com.gcaa.nm.eurocontrol._2_5_0;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ExecutionEnvironment.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ExecutionEnvironment"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="OPS"/&gt;
 *     &lt;enumeration value="OPT"/&gt;
 *     &lt;enumeration value="PRE_RELEASE"/&gt;
 *     &lt;enumeration value="DEV"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "ExecutionEnvironment", namespace = "eurocontrol/cfmu/b2b/CommonServices")
@XmlEnum
public enum ExecutionEnvironment {

    OPS,
    OPT,
    PRE_RELEASE,
    DEV;

    public String value() {
        return name();
    }

    public static ExecutionEnvironment fromValue(String v) {
        return valueOf(v);
    }

}
