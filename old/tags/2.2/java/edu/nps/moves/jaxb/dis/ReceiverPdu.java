//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.1.5-b01-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2008.01.31 at 02:05:02 PM PST 
//


package edu.nps.moves.jaxb.dis;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for receiverPdu complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="receiverPdu">
 *   &lt;complexContent>
 *     &lt;extension base="{}radioCommunicationsFamilyPdu">
 *       &lt;sequence>
 *         &lt;element name="padding1" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="receivedPoser" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="receiverState" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="transmitterEntityId" type="{}entityID" minOccurs="0"/>
 *         &lt;element name="transmitterRadioId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "receiverPdu", propOrder = {
    "padding1",
    "receivedPoser",
    "receiverState",
    "transmitterEntityId",
    "transmitterRadioId"
})
public class ReceiverPdu
    extends RadioCommunicationsFamilyPdu
{

    protected int padding1;
    protected float receivedPoser;
    protected int receiverState;
    protected EntityID transmitterEntityId;
    protected int transmitterRadioId;

    /**
     * Gets the value of the padding1 property.
     * 
     */
    public int getPadding1() {
        return padding1;
    }

    /**
     * Sets the value of the padding1 property.
     * 
     */
    public void setPadding1(int value) {
        this.padding1 = value;
    }

    /**
     * Gets the value of the receivedPoser property.
     * 
     */
    public float getReceivedPoser() {
        return receivedPoser;
    }

    /**
     * Sets the value of the receivedPoser property.
     * 
     */
    public void setReceivedPoser(float value) {
        this.receivedPoser = value;
    }

    /**
     * Gets the value of the receiverState property.
     * 
     */
    public int getReceiverState() {
        return receiverState;
    }

    /**
     * Sets the value of the receiverState property.
     * 
     */
    public void setReceiverState(int value) {
        this.receiverState = value;
    }

    /**
     * Gets the value of the transmitterEntityId property.
     * 
     * @return
     *     possible object is
     *     {@link EntityID }
     *     
     */
    public EntityID getTransmitterEntityId() {
        return transmitterEntityId;
    }

    /**
     * Sets the value of the transmitterEntityId property.
     * 
     * @param value
     *     allowed object is
     *     {@link EntityID }
     *     
     */
    public void setTransmitterEntityId(EntityID value) {
        this.transmitterEntityId = value;
    }

    /**
     * Gets the value of the transmitterRadioId property.
     * 
     */
    public int getTransmitterRadioId() {
        return transmitterRadioId;
    }

    /**
     * Sets the value of the transmitterRadioId property.
     * 
     */
    public void setTransmitterRadioId(int value) {
        this.transmitterRadioId = value;
    }

}
