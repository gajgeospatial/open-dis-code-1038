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
 * <p>Java class for environmentalProcessPdu complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="environmentalProcessPdu">
 *   &lt;complexContent>
 *     &lt;extension base="{}syntheticEnvironmentFamilyPdu">
 *       &lt;sequence>
 *         &lt;element name="environementalProcessID" type="{}entityID" minOccurs="0"/>
 *         &lt;element name="environmentRecords" type="{http://www.w3.org/2001/XMLSchema}anyType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="environmentStatus" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="environmentType" type="{}entityType" minOccurs="0"/>
 *         &lt;element name="modelType" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="numberOfEnvironmentRecords" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="sequenceNumber" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "environmentalProcessPdu", propOrder = {
    "environementalProcessID",
    "environmentRecords",
    "environmentStatus",
    "environmentType",
    "modelType",
    "numberOfEnvironmentRecords",
    "sequenceNumber"
})
public class EnvironmentalProcessPdu
    extends SyntheticEnvironmentFamilyPdu
{

    protected EntityID environementalProcessID;
    @XmlElement(nillable = true)
    protected List<Object> environmentRecords;
    protected short environmentStatus;
    protected EntityType environmentType;
    protected short modelType;
    protected short numberOfEnvironmentRecords;
    protected int sequenceNumber;

    /**
     * Gets the value of the environementalProcessID property.
     * 
     * @return
     *     possible object is
     *     {@link EntityID }
     *     
     */
    public EntityID getEnvironementalProcessID() {
        return environementalProcessID;
    }

    /**
     * Sets the value of the environementalProcessID property.
     * 
     * @param value
     *     allowed object is
     *     {@link EntityID }
     *     
     */
    public void setEnvironementalProcessID(EntityID value) {
        this.environementalProcessID = value;
    }

    /**
     * Gets the value of the environmentRecords property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the environmentRecords property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEnvironmentRecords().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Object }
     * 
     * 
     */
    public List<Object> getEnvironmentRecords() {
        if (environmentRecords == null) {
            environmentRecords = new ArrayList<Object>();
        }
        return this.environmentRecords;
    }

    /**
     * Gets the value of the environmentStatus property.
     * 
     */
    public short getEnvironmentStatus() {
        return environmentStatus;
    }

    /**
     * Sets the value of the environmentStatus property.
     * 
     */
    public void setEnvironmentStatus(short value) {
        this.environmentStatus = value;
    }

    /**
     * Gets the value of the environmentType property.
     * 
     * @return
     *     possible object is
     *     {@link EntityType }
     *     
     */
    public EntityType getEnvironmentType() {
        return environmentType;
    }

    /**
     * Sets the value of the environmentType property.
     * 
     * @param value
     *     allowed object is
     *     {@link EntityType }
     *     
     */
    public void setEnvironmentType(EntityType value) {
        this.environmentType = value;
    }

    /**
     * Gets the value of the modelType property.
     * 
     */
    public short getModelType() {
        return modelType;
    }

    /**
     * Sets the value of the modelType property.
     * 
     */
    public void setModelType(short value) {
        this.modelType = value;
    }

    /**
     * Gets the value of the numberOfEnvironmentRecords property.
     * 
     */
    public short getNumberOfEnvironmentRecords() {
        return numberOfEnvironmentRecords;
    }

    /**
     * Sets the value of the numberOfEnvironmentRecords property.
     * 
     */
    public void setNumberOfEnvironmentRecords(short value) {
        this.numberOfEnvironmentRecords = value;
    }

    /**
     * Gets the value of the sequenceNumber property.
     * 
     */
    public int getSequenceNumber() {
        return sequenceNumber;
    }

    /**
     * Sets the value of the sequenceNumber property.
     * 
     */
    public void setSequenceNumber(int value) {
        this.sequenceNumber = value;
    }

}
