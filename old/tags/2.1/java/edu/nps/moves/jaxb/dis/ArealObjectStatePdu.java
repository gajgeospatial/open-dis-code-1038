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
 * <p>Java class for arealObjectStatePdu complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="arealObjectStatePdu">
 *   &lt;complexContent>
 *     &lt;extension base="{}syntheticEnvironmentFamilyPdu">
 *       &lt;sequence>
 *         &lt;element name="forceID" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="modifications" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="numberOfPoints" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="objectAppearance" type="{}sixByteChunk" minOccurs="0"/>
 *         &lt;element name="objectID" type="{}entityID" minOccurs="0"/>
 *         &lt;element name="objectLocation" type="{http://www.w3.org/2001/XMLSchema}anyType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="objectType" type="{}entityType" minOccurs="0"/>
 *         &lt;element name="receivingID" type="{}simulationAddress" minOccurs="0"/>
 *         &lt;element name="referencedObjectID" type="{}entityID" minOccurs="0"/>
 *         &lt;element name="requesterID" type="{}simulationAddress" minOccurs="0"/>
 *         &lt;element name="updateNumber" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "arealObjectStatePdu", propOrder = {
    "forceID",
    "modifications",
    "numberOfPoints",
    "objectAppearance",
    "objectID",
    "objectLocation",
    "objectType",
    "receivingID",
    "referencedObjectID",
    "requesterID",
    "updateNumber"
})
public class ArealObjectStatePdu
    extends SyntheticEnvironmentFamilyPdu
{

    protected short forceID;
    protected short modifications;
    protected int numberOfPoints;
    protected SixByteChunk objectAppearance;
    protected EntityID objectID;
    @XmlElement(nillable = true)
    protected List<Object> objectLocation;
    protected EntityType objectType;
    protected SimulationAddress receivingID;
    protected EntityID referencedObjectID;
    protected SimulationAddress requesterID;
    protected int updateNumber;

    /**
     * Gets the value of the forceID property.
     * 
     */
    public short getForceID() {
        return forceID;
    }

    /**
     * Sets the value of the forceID property.
     * 
     */
    public void setForceID(short value) {
        this.forceID = value;
    }

    /**
     * Gets the value of the modifications property.
     * 
     */
    public short getModifications() {
        return modifications;
    }

    /**
     * Sets the value of the modifications property.
     * 
     */
    public void setModifications(short value) {
        this.modifications = value;
    }

    /**
     * Gets the value of the numberOfPoints property.
     * 
     */
    public int getNumberOfPoints() {
        return numberOfPoints;
    }

    /**
     * Sets the value of the numberOfPoints property.
     * 
     */
    public void setNumberOfPoints(int value) {
        this.numberOfPoints = value;
    }

    /**
     * Gets the value of the objectAppearance property.
     * 
     * @return
     *     possible object is
     *     {@link SixByteChunk }
     *     
     */
    public SixByteChunk getObjectAppearance() {
        return objectAppearance;
    }

    /**
     * Sets the value of the objectAppearance property.
     * 
     * @param value
     *     allowed object is
     *     {@link SixByteChunk }
     *     
     */
    public void setObjectAppearance(SixByteChunk value) {
        this.objectAppearance = value;
    }

    /**
     * Gets the value of the objectID property.
     * 
     * @return
     *     possible object is
     *     {@link EntityID }
     *     
     */
    public EntityID getObjectID() {
        return objectID;
    }

    /**
     * Sets the value of the objectID property.
     * 
     * @param value
     *     allowed object is
     *     {@link EntityID }
     *     
     */
    public void setObjectID(EntityID value) {
        this.objectID = value;
    }

    /**
     * Gets the value of the objectLocation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the objectLocation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getObjectLocation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Object }
     * 
     * 
     */
    public List<Object> getObjectLocation() {
        if (objectLocation == null) {
            objectLocation = new ArrayList<Object>();
        }
        return this.objectLocation;
    }

    /**
     * Gets the value of the objectType property.
     * 
     * @return
     *     possible object is
     *     {@link EntityType }
     *     
     */
    public EntityType getObjectType() {
        return objectType;
    }

    /**
     * Sets the value of the objectType property.
     * 
     * @param value
     *     allowed object is
     *     {@link EntityType }
     *     
     */
    public void setObjectType(EntityType value) {
        this.objectType = value;
    }

    /**
     * Gets the value of the receivingID property.
     * 
     * @return
     *     possible object is
     *     {@link SimulationAddress }
     *     
     */
    public SimulationAddress getReceivingID() {
        return receivingID;
    }

    /**
     * Sets the value of the receivingID property.
     * 
     * @param value
     *     allowed object is
     *     {@link SimulationAddress }
     *     
     */
    public void setReceivingID(SimulationAddress value) {
        this.receivingID = value;
    }

    /**
     * Gets the value of the referencedObjectID property.
     * 
     * @return
     *     possible object is
     *     {@link EntityID }
     *     
     */
    public EntityID getReferencedObjectID() {
        return referencedObjectID;
    }

    /**
     * Sets the value of the referencedObjectID property.
     * 
     * @param value
     *     allowed object is
     *     {@link EntityID }
     *     
     */
    public void setReferencedObjectID(EntityID value) {
        this.referencedObjectID = value;
    }

    /**
     * Gets the value of the requesterID property.
     * 
     * @return
     *     possible object is
     *     {@link SimulationAddress }
     *     
     */
    public SimulationAddress getRequesterID() {
        return requesterID;
    }

    /**
     * Sets the value of the requesterID property.
     * 
     * @param value
     *     allowed object is
     *     {@link SimulationAddress }
     *     
     */
    public void setRequesterID(SimulationAddress value) {
        this.requesterID = value;
    }

    /**
     * Gets the value of the updateNumber property.
     * 
     */
    public int getUpdateNumber() {
        return updateNumber;
    }

    /**
     * Sets the value of the updateNumber property.
     * 
     */
    public void setUpdateNumber(int value) {
        this.updateNumber = value;
    }

}
