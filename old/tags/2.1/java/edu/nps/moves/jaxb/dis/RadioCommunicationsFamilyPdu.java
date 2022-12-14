//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.1.5-b01-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2008.01.31 at 02:05:02 PM PST 
//


package edu.nps.moves.jaxb.dis;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for radioCommunicationsFamilyPdu complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="radioCommunicationsFamilyPdu">
 *   &lt;complexContent>
 *     &lt;extension base="{}pdu">
 *       &lt;sequence>
 *         &lt;element name="entityId" type="{}entityID" minOccurs="0"/>
 *         &lt;element name="radioId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "radioCommunicationsFamilyPdu", propOrder = {
    "entityId",
    "radioId"
})
@XmlSeeAlso({
    ReceiverPdu.class,
    IntercomControlPdu.class,
    IntercomSignalPdu.class,
    SignalPdu.class,
    TransmitterPdu.class
})
public class RadioCommunicationsFamilyPdu
    extends Pdu
{

    protected EntityID entityId;
    protected int radioId;

    /**
     * Gets the value of the entityId property.
     * 
     * @return
     *     possible object is
     *     {@link EntityID }
     *     
     */
    public EntityID getEntityId() {
        return entityId;
    }

    /**
     * Sets the value of the entityId property.
     * 
     * @param value
     *     allowed object is
     *     {@link EntityID }
     *     
     */
    public void setEntityId(EntityID value) {
        this.entityId = value;
    }

    /**
     * Gets the value of the radioId property.
     * 
     */
    public int getRadioId() {
        return radioId;
    }

    /**
     * Sets the value of the radioId property.
     * 
     */
    public void setRadioId(int value) {
        this.radioId = value;
    }

}
