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
 * <p>Java class for AddFlightsToMeasureReplyData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AddFlightsToMeasureReplyData"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="addedFlights" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="item" maxOccurs="100" minOccurs="0"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;choice&gt;
 *                             &lt;element name="FlightInMcdmOnly" type="{eurocontrol/cfmu/b2b/FlowServices}FlightInMcdmOnly"/&gt;
 *                             &lt;element name="FlightInRerouting" type="{eurocontrol/cfmu/b2b/FlowServices}FlightInRerouting"/&gt;
 *                             &lt;element name="FlightInRegulation" type="{eurocontrol/cfmu/b2b/FlowServices}FlightInRegulation"/&gt;
 *                           &lt;/choice&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="unsuccessfullyAddedFlights" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="item" maxOccurs="100"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="value"&gt;
 *                               &lt;simpleType&gt;
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                 &lt;/restriction&gt;
 *                               &lt;/simpleType&gt;
 *                             &lt;/element&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
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
@XmlType(name = "AddFlightsToMeasureReplyData", propOrder = {
    "addedFlights",
    "unsuccessfullyAddedFlights"
})
public class AddFlightsToMeasureReplyData {

    protected AddFlightsToMeasureReplyData.AddedFlights addedFlights;
    protected AddFlightsToMeasureReplyData.UnsuccessfullyAddedFlights unsuccessfullyAddedFlights;

    /**
     * Gets the value of the addedFlights property.
     * 
     * @return
     *     possible object is
     *     {@link AddFlightsToMeasureReplyData.AddedFlights }
     *     
     */
    public AddFlightsToMeasureReplyData.AddedFlights getAddedFlights() {
        return addedFlights;
    }

    /**
     * Sets the value of the addedFlights property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddFlightsToMeasureReplyData.AddedFlights }
     *     
     */
    public void setAddedFlights(AddFlightsToMeasureReplyData.AddedFlights value) {
        this.addedFlights = value;
    }

    /**
     * Gets the value of the unsuccessfullyAddedFlights property.
     * 
     * @return
     *     possible object is
     *     {@link AddFlightsToMeasureReplyData.UnsuccessfullyAddedFlights }
     *     
     */
    public AddFlightsToMeasureReplyData.UnsuccessfullyAddedFlights getUnsuccessfullyAddedFlights() {
        return unsuccessfullyAddedFlights;
    }

    /**
     * Sets the value of the unsuccessfullyAddedFlights property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddFlightsToMeasureReplyData.UnsuccessfullyAddedFlights }
     *     
     */
    public void setUnsuccessfullyAddedFlights(AddFlightsToMeasureReplyData.UnsuccessfullyAddedFlights value) {
        this.unsuccessfullyAddedFlights = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="item" maxOccurs="100" minOccurs="0"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;choice&gt;
     *                   &lt;element name="FlightInMcdmOnly" type="{eurocontrol/cfmu/b2b/FlowServices}FlightInMcdmOnly"/&gt;
     *                   &lt;element name="FlightInRerouting" type="{eurocontrol/cfmu/b2b/FlowServices}FlightInRerouting"/&gt;
     *                   &lt;element name="FlightInRegulation" type="{eurocontrol/cfmu/b2b/FlowServices}FlightInRegulation"/&gt;
     *                 &lt;/choice&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
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
    @XmlType(name = "", propOrder = {
        "item"
    })
    public static class AddedFlights {

        protected List<AddFlightsToMeasureReplyData.AddedFlights.Item> item;

        /**
         * Gets the value of the item property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the item property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getItem().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link AddFlightsToMeasureReplyData.AddedFlights.Item }
         * 
         * 
         */
        public List<AddFlightsToMeasureReplyData.AddedFlights.Item> getItem() {
            if (item == null) {
                item = new ArrayList<AddFlightsToMeasureReplyData.AddedFlights.Item>();
            }
            return this.item;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType&gt;
         *   &lt;complexContent&gt;
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *       &lt;choice&gt;
         *         &lt;element name="FlightInMcdmOnly" type="{eurocontrol/cfmu/b2b/FlowServices}FlightInMcdmOnly"/&gt;
         *         &lt;element name="FlightInRerouting" type="{eurocontrol/cfmu/b2b/FlowServices}FlightInRerouting"/&gt;
         *         &lt;element name="FlightInRegulation" type="{eurocontrol/cfmu/b2b/FlowServices}FlightInRegulation"/&gt;
         *       &lt;/choice&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "flightInMcdmOnly",
            "flightInRerouting",
            "flightInRegulation"
        })
        public static class Item {

            @XmlElement(name = "FlightInMcdmOnly")
            protected FlightInMcdmOnly flightInMcdmOnly;
            @XmlElement(name = "FlightInRerouting")
            protected FlightInRerouting flightInRerouting;
            @XmlElement(name = "FlightInRegulation")
            protected FlightInRegulation flightInRegulation;

            /**
             * Gets the value of the flightInMcdmOnly property.
             * 
             * @return
             *     possible object is
             *     {@link FlightInMcdmOnly }
             *     
             */
            public FlightInMcdmOnly getFlightInMcdmOnly() {
                return flightInMcdmOnly;
            }

            /**
             * Sets the value of the flightInMcdmOnly property.
             * 
             * @param value
             *     allowed object is
             *     {@link FlightInMcdmOnly }
             *     
             */
            public void setFlightInMcdmOnly(FlightInMcdmOnly value) {
                this.flightInMcdmOnly = value;
            }

            /**
             * Gets the value of the flightInRerouting property.
             * 
             * @return
             *     possible object is
             *     {@link FlightInRerouting }
             *     
             */
            public FlightInRerouting getFlightInRerouting() {
                return flightInRerouting;
            }

            /**
             * Sets the value of the flightInRerouting property.
             * 
             * @param value
             *     allowed object is
             *     {@link FlightInRerouting }
             *     
             */
            public void setFlightInRerouting(FlightInRerouting value) {
                this.flightInRerouting = value;
            }

            /**
             * Gets the value of the flightInRegulation property.
             * 
             * @return
             *     possible object is
             *     {@link FlightInRegulation }
             *     
             */
            public FlightInRegulation getFlightInRegulation() {
                return flightInRegulation;
            }

            /**
             * Sets the value of the flightInRegulation property.
             * 
             * @param value
             *     allowed object is
             *     {@link FlightInRegulation }
             *     
             */
            public void setFlightInRegulation(FlightInRegulation value) {
                this.flightInRegulation = value;
            }

        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="item" maxOccurs="100"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="value"&gt;
     *                     &lt;simpleType&gt;
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                       &lt;/restriction&gt;
     *                     &lt;/simpleType&gt;
     *                   &lt;/element&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
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
    @XmlType(name = "", propOrder = {
        "item"
    })
    public static class UnsuccessfullyAddedFlights {

        @XmlElement(required = true)
        protected List<AddFlightsToMeasureReplyData.UnsuccessfullyAddedFlights.Item> item;

        /**
         * Gets the value of the item property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the item property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getItem().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link AddFlightsToMeasureReplyData.UnsuccessfullyAddedFlights.Item }
         * 
         * 
         */
        public List<AddFlightsToMeasureReplyData.UnsuccessfullyAddedFlights.Item> getItem() {
            if (item == null) {
                item = new ArrayList<AddFlightsToMeasureReplyData.UnsuccessfullyAddedFlights.Item>();
            }
            return this.item;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType&gt;
         *   &lt;complexContent&gt;
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *       &lt;sequence&gt;
         *         &lt;element name="value"&gt;
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
        @XmlType(name = "", propOrder = {
            "value"
        })
        public static class Item {

            @XmlElement(required = true)
            protected String value;

            /**
             * Gets the value of the value property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getValue() {
                return value;
            }

            /**
             * Sets the value of the value property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setValue(String value) {
                this.value = value;
            }

        }

    }

}
