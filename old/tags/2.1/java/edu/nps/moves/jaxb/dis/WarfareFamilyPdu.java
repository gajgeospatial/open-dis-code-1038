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
 * <p>Java class for warfareFamilyPdu complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="warfareFamilyPdu">
 *   &lt;complexContent>
 *     &lt;extension base="{}pdu">
 *       &lt;sequence>
 *         &lt;element name="firingEntityID" type="{}entityID" minOccurs="0"/>
 *         &lt;element name="targetEntityID" type="{}entityID" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "warfareFamilyPdu", propOrder = {
    "firingEntityID",
    "targetEntityID"
})
@XmlSeeAlso({
    FirePdu.class,
    DetonationPdu.class
})
public class WarfareFamilyPdu
    extends Pdu
{

    protected EntityID firingEntityID;
    protected EntityID targetEntityID;

    /**
     * Gets the value of the firingEntityID property.
     * 
     * @return
     *     possible object is
     *     {@link EntityID }
     *     
     */
    public EntityID getFiringEntityID() {
        return firingEntityID;
    }

    /**
     * Sets the value of the firingEntityID property.
     * 
     * @param value
     *     allowed object is
     *     {@link EntityID }
     *     
     */
    public void setFiringEntityID(EntityID value) {
        this.firingEntityID = value;
    }

    /**
     * Gets the value of the targetEntityID property.
     * 
     * @return
     *     possible object is
     *     {@link EntityID }
     *     
     */
    public EntityID getTargetEntityID() {
        return targetEntityID;
    }

    /**
     * Sets the value of the targetEntityID property.
     * 
     * @param value
     *     allowed object is
     *     {@link EntityID }
     *     
     */
    public void setTargetEntityID(EntityID value) {
        this.targetEntityID = value;
    }

}
