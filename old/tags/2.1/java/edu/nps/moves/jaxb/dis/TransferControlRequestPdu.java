//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.1.5-b01-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2008.01.31 at 02:05:02 PM PST 
//


package edu.nps.moves.jaxb.dis;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for transferControlRequestPdu complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="transferControlRequestPdu">
 *   &lt;complexContent>
 *     &lt;extension base="{}entityManagementFamilyPdu">
 *       &lt;sequence>
 *         &lt;element name="numberOfRecordSets" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="orginatingEntityID" type="{}entityID" minOccurs="0"/>
 *         &lt;element name="recevingEntityID" type="{}entityID" minOccurs="0"/>
 *         &lt;element name="recordSets" type="{http://www.w3.org/2001/XMLSchema}anyType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="requestID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="requiredReliabilityService" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="tranferType" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="transferEntityID" type="{}entityID" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "transferControlRequestPdu", propOrder = {
    "numberOfRecordSets",
    "orginatingEntityID",
    "recevingEntityID",
    "recordSets",
    "requestID",
    "requiredReliabilityService",
    "tranferType",
    "transferEntityID"
})
public class TransferControlRequestPdu
    extends EntityManagementFamilyPdu
{

    protected short numberOfRecordSets;
    protected EntityID orginatingEntityID;
    protected EntityID recevingEntityID;
    @XmlElement(nillable = true)
    protected List<Object> recordSets;
    protected long requestID;
    protected short requiredReliabilityService;
    protected short tranferType;
    protected EntityID transferEntityID;

    /**
     * Gets the value of the numberOfRecordSets property.
     * 
     */
    public short getNumberOfRecordSets() {
        return numberOfRecordSets;
    }

    /**
     * Sets the value of the numberOfRecordSets property.
     * 
     */
    public void setNumberOfRecordSets(short value) {
        this.numberOfRecordSets = value;
    }

    /**
     * Gets the value of the orginatingEntityID property.
     * 
     * @return
     *     possible object is
     *     {@link EntityID }
     *     
     */
    public EntityID getOrginatingEntityID() {
        return orginatingEntityID;
    }

    /**
     * Sets the value of the orginatingEntityID property.
     * 
     * @param value
     *     allowed object is
     *     {@link EntityID }
     *     
     */
    public void setOrginatingEntityID(EntityID value) {
        this.orginatingEntityID = value;
    }

    /**
     * Gets the value of the recevingEntityID property.
     * 
     * @return
     *     possible object is
     *     {@link EntityID }
     *     
     */
    public EntityID getRecevingEntityID() {
        return recevingEntityID;
    }

    /**
     * Sets the value of the recevingEntityID property.
     * 
     * @param value
     *     allowed object is
     *     {@link EntityID }
     *     
     */
    public void setRecevingEntityID(EntityID value) {
        this.recevingEntityID = value;
    }

    /**
     * Gets the value of the recordSets property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the recordSets property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRecordSets().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Object }
     * 
     * 
     */
    public List<Object> getRecordSets() {
        if (recordSets == null) {
            recordSets = new ArrayList<Object>();
        }
        return this.recordSets;
    }

    /**
     * Gets the value of the requestID property.
     * 
     */
    public long getRequestID() {
        return requestID;
    }

    /**
     * Sets the value of the requestID property.
     * 
     */
    public void setRequestID(long value) {
        this.requestID = value;
    }

    /**
     * Gets the value of the requiredReliabilityService property.
     * 
     */
    public short getRequiredReliabilityService() {
        return requiredReliabilityService;
    }

    /**
     * Sets the value of the requiredReliabilityService property.
     * 
     */
    public void setRequiredReliabilityService(short value) {
        this.requiredReliabilityService = value;
    }

    /**
     * Gets the value of the tranferType property.
     * 
     */
    public short getTranferType() {
        return tranferType;
    }

    /**
     * Sets the value of the tranferType property.
     * 
     */
    public void setTranferType(short value) {
        this.tranferType = value;
    }

    /**
     * Gets the value of the transferEntityID property.
     * 
     * @return
     *     possible object is
     *     {@link EntityID }
     *     
     */
    public EntityID getTransferEntityID() {
        return transferEntityID;
    }

    /**
     * Sets the value of the transferEntityID property.
     * 
     * @param value
     *     allowed object is
     *     {@link EntityID }
     *     
     */
    public void setTransferEntityID(EntityID value) {
        this.transferEntityID = value;
    }

}
