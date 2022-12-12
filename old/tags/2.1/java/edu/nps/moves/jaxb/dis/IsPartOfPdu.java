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
 * <p>Java class for isPartOfPdu complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="isPartOfPdu">
 *   &lt;complexContent>
 *     &lt;extension base="{}entityManagementFamilyPdu">
 *       &lt;sequence>
 *         &lt;element name="namedLocationID" type="{}namedLocation" minOccurs="0"/>
 *         &lt;element name="orginatingEntityID" type="{}entityID" minOccurs="0"/>
 *         &lt;element name="partEntityType" type="{}entityType" minOccurs="0"/>
 *         &lt;element name="partLocation" type="{}vector3Float" minOccurs="0"/>
 *         &lt;element name="receivingEntityID" type="{}entityID" minOccurs="0"/>
 *         &lt;element name="relationship" type="{}relationship" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "isPartOfPdu", propOrder = {
    "namedLocationID",
    "orginatingEntityID",
    "partEntityType",
    "partLocation",
    "receivingEntityID",
    "relationship"
})
public class IsPartOfPdu
    extends EntityManagementFamilyPdu
{

    protected NamedLocation namedLocationID;
    protected EntityID orginatingEntityID;
    protected EntityType partEntityType;
    protected Vector3Float partLocation;
    protected EntityID receivingEntityID;
    protected Relationship relationship;

    /**
     * Gets the value of the namedLocationID property.
     * 
     * @return
     *     possible object is
     *     {@link NamedLocation }
     *     
     */
    public NamedLocation getNamedLocationID() {
        return namedLocationID;
    }

    /**
     * Sets the value of the namedLocationID property.
     * 
     * @param value
     *     allowed object is
     *     {@link NamedLocation }
     *     
     */
    public void setNamedLocationID(NamedLocation value) {
        this.namedLocationID = value;
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
     * Gets the value of the partEntityType property.
     * 
     * @return
     *     possible object is
     *     {@link EntityType }
     *     
     */
    public EntityType getPartEntityType() {
        return partEntityType;
    }

    /**
     * Sets the value of the partEntityType property.
     * 
     * @param value
     *     allowed object is
     *     {@link EntityType }
     *     
     */
    public void setPartEntityType(EntityType value) {
        this.partEntityType = value;
    }

    /**
     * Gets the value of the partLocation property.
     * 
     * @return
     *     possible object is
     *     {@link Vector3Float }
     *     
     */
    public Vector3Float getPartLocation() {
        return partLocation;
    }

    /**
     * Sets the value of the partLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Vector3Float }
     *     
     */
    public void setPartLocation(Vector3Float value) {
        this.partLocation = value;
    }

    /**
     * Gets the value of the receivingEntityID property.
     * 
     * @return
     *     possible object is
     *     {@link EntityID }
     *     
     */
    public EntityID getReceivingEntityID() {
        return receivingEntityID;
    }

    /**
     * Sets the value of the receivingEntityID property.
     * 
     * @param value
     *     allowed object is
     *     {@link EntityID }
     *     
     */
    public void setReceivingEntityID(EntityID value) {
        this.receivingEntityID = value;
    }

    /**
     * Gets the value of the relationship property.
     * 
     * @return
     *     possible object is
     *     {@link Relationship }
     *     
     */
    public Relationship getRelationship() {
        return relationship;
    }

    /**
     * Sets the value of the relationship property.
     * 
     * @param value
     *     allowed object is
     *     {@link Relationship }
     *     
     */
    public void setRelationship(Relationship value) {
        this.relationship = value;
    }

}
