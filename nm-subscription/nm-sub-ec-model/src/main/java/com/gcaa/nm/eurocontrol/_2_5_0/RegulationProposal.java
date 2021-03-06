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
 * <p>Java class for RegulationProposal complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RegulationProposal"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{eurocontrol/cfmu/b2b/FlowServices}RegulationOrMCDMOnly"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="kind" type="{eurocontrol/cfmu/b2b/FlowServices}RegulationProposalKind"/&gt;
 *         &lt;element name="action" type="{eurocontrol/cfmu/b2b/FlowServices}RegulationProposalAction" minOccurs="0"/&gt;
 *         &lt;element name="approvalState" type="{eurocontrol/cfmu/b2b/FlowServices}MCDMApprovalState" minOccurs="0"/&gt;
 *         &lt;element name="mcdmState" type="{eurocontrol/cfmu/b2b/FlowServices}MCDMState" minOccurs="0"/&gt;
 *         &lt;element name="regulationState" type="{eurocontrol/cfmu/b2b/FlowServices}RegulationState" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RegulationProposal", propOrder = {
    "kind",
    "action",
    "approvalState",
    "mcdmState",
    "regulationState"
})
public class RegulationProposal
    extends RegulationOrMCDMOnly
{

    @XmlElement(required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String kind;
    @XmlSchemaType(name = "anySimpleType")
    protected String action;
    @XmlSchemaType(name = "anySimpleType")
    protected String approvalState;
    @XmlSchemaType(name = "anySimpleType")
    protected String mcdmState;
    @XmlSchemaType(name = "anySimpleType")
    protected String regulationState;

    /**
     * Gets the value of the kind property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKind() {
        return kind;
    }

    /**
     * Sets the value of the kind property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKind(String value) {
        this.kind = value;
    }

    /**
     * Gets the value of the action property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAction() {
        return action;
    }

    /**
     * Sets the value of the action property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAction(String value) {
        this.action = value;
    }

    /**
     * Gets the value of the approvalState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApprovalState() {
        return approvalState;
    }

    /**
     * Sets the value of the approvalState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApprovalState(String value) {
        this.approvalState = value;
    }

    /**
     * Gets the value of the mcdmState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMcdmState() {
        return mcdmState;
    }

    /**
     * Sets the value of the mcdmState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMcdmState(String value) {
        this.mcdmState = value;
    }

    /**
     * Gets the value of the regulationState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegulationState() {
        return regulationState;
    }

    /**
     * Sets the value of the regulationState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegulationState(String value) {
        this.regulationState = value;
    }

}
