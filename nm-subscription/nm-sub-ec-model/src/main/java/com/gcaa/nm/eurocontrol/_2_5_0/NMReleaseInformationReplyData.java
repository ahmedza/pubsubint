//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.08.08 at 11:11:39 PM GST 
//


package com.gcaa.nm.eurocontrol._2_5_0;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NMReleaseInformationReplyData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NMReleaseInformationReplyData"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="release" type="{eurocontrol/cfmu/b2b/CommonServices}NMRelease"/&gt;
 *         &lt;element name="baseline" type="{eurocontrol/cfmu/b2b/CommonServices}NMB2BProviderVersion"/&gt;
 *         &lt;element name="supportedB2BVersions" type="{eurocontrol/cfmu/b2b/CommonServices}NMB2BVersion" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="platform" type="{eurocontrol/cfmu/b2b/CommonServices}NMPlatform"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NMReleaseInformationReplyData", namespace = "eurocontrol/cfmu/b2b/GeneralinformationServices", propOrder = {
    "release",
    "baseline",
    "supportedB2BVersions",
    "platform"
})
public class NMReleaseInformationReplyData {

    @XmlElement(required = true)
    protected String release;
    @XmlElement(required = true)
    protected String baseline;
    protected List<String> supportedB2BVersions;
    @XmlElement(required = true)
    protected NMPlatform platform;

    /**
     * Gets the value of the release property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRelease() {
        return release;
    }

    /**
     * Sets the value of the release property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRelease(String value) {
        this.release = value;
    }

    /**
     * Gets the value of the baseline property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBaseline() {
        return baseline;
    }

    /**
     * Sets the value of the baseline property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBaseline(String value) {
        this.baseline = value;
    }

    /**
     * Gets the value of the supportedB2BVersions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the supportedB2BVersions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSupportedB2BVersions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getSupportedB2BVersions() {
        if (supportedB2BVersions == null) {
            supportedB2BVersions = new ArrayList<String>();
        }
        return this.supportedB2BVersions;
    }

    /**
     * Gets the value of the platform property.
     * 
     * @return
     *     possible object is
     *     {@link NMPlatform }
     *     
     */
    public NMPlatform getPlatform() {
        return platform;
    }

    /**
     * Sets the value of the platform property.
     * 
     * @param value
     *     allowed object is
     *     {@link NMPlatform }
     *     
     */
    public void setPlatform(NMPlatform value) {
        this.platform = value;
    }

}
