//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.08.08 at 11:11:39 PM GST 
//


package com.gcaa.nm.eurocontrol._2_5_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Hotspot complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Hotspot"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="hotspotId" type="{eurocontrol/cfmu/b2b/FlowServices}HotspotId"/&gt;
 *         &lt;element name="severity" type="{eurocontrol/cfmu/b2b/FlowServices}HotspotSeverity"/&gt;
 *         &lt;element name="status" type="{eurocontrol/cfmu/b2b/FlowServices}HotspotStatus"/&gt;
 *         &lt;element name="remark" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;pattern value="(((((((((((([a-zA-Z]|[0-9])|('))|(\())|(\)))|(\+))|(,))|(=))|(\?))|(\.))|(/))|(:))|( )){0,255}"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="trafficVolumeDescription" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Hotspot", propOrder = {
    "hotspotId",
    "severity",
    "status",
    "remark",
    "trafficVolumeDescription"
})
public class Hotspot {

    @XmlElement(required = true)
    protected HotspotId hotspotId;
    @XmlElement(required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String severity;
    @XmlElement(required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String status;
    protected String remark;
    protected String trafficVolumeDescription;

    /**
     * Gets the value of the hotspotId property.
     * 
     * @return
     *     possible object is
     *     {@link HotspotId }
     *     
     */
    public HotspotId getHotspotId() {
        return hotspotId;
    }

    /**
     * Sets the value of the hotspotId property.
     * 
     * @param value
     *     allowed object is
     *     {@link HotspotId }
     *     
     */
    public void setHotspotId(HotspotId value) {
        this.hotspotId = value;
    }

    /**
     * Gets the value of the severity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeverity() {
        return severity;
    }

    /**
     * Sets the value of the severity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeverity(String value) {
        this.severity = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the remark property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemark() {
        return remark;
    }

    /**
     * Sets the value of the remark property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemark(String value) {
        this.remark = value;
    }

    /**
     * Gets the value of the trafficVolumeDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrafficVolumeDescription() {
        return trafficVolumeDescription;
    }

    /**
     * Sets the value of the trafficVolumeDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrafficVolumeDescription(String value) {
        this.trafficVolumeDescription = value;
    }

}
