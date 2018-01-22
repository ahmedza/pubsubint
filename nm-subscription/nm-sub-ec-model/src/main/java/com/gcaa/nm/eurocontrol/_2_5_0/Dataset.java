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
 * <p>Java class for Dataset complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Dataset"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="type" type="{eurocontrol/cfmu/b2b/CommonServices}DatasetType"/&gt;
 *         &lt;element name="simulationId" type="{eurocontrol/cfmu/b2b/CommonServices}SimulationId" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Dataset", namespace = "eurocontrol/cfmu/b2b/CommonServices", propOrder = {
    "type",
    "simulationId"
})
public class Dataset {

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected DatasetType type;
    protected String simulationId;

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link DatasetType }
     *     
     */
    public DatasetType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link DatasetType }
     *     
     */
    public void setType(DatasetType value) {
        this.type = value;
    }

    /**
     * Gets the value of the simulationId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSimulationId() {
        return simulationId;
    }

    /**
     * Sets the value of the simulationId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSimulationId(String value) {
        this.simulationId = value;
    }

}
