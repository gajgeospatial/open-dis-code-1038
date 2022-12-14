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
 * <p>Java class for beamAntennaPattern complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="beamAntennaPattern">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="azimuthBeamwidth" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="beamDirection" type="{}orientation" minOccurs="0"/>
 *         &lt;element name="ex" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="ez" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="padding1" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="padding2" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="phase" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="referenceSystem" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "beamAntennaPattern", propOrder = {
    "azimuthBeamwidth",
    "beamDirection",
    "ex",
    "ez",
    "padding1",
    "padding2",
    "phase",
    "referenceSystem"
})
public class BeamAntennaPattern {

    protected float azimuthBeamwidth;
    protected Orientation beamDirection;
    protected float ex;
    protected float ez;
    protected short padding1;
    protected byte padding2;
    protected float phase;
    protected float referenceSystem;

    /**
     * Gets the value of the azimuthBeamwidth property.
     * 
     */
    public float getAzimuthBeamwidth() {
        return azimuthBeamwidth;
    }

    /**
     * Sets the value of the azimuthBeamwidth property.
     * 
     */
    public void setAzimuthBeamwidth(float value) {
        this.azimuthBeamwidth = value;
    }

    /**
     * Gets the value of the beamDirection property.
     * 
     * @return
     *     possible object is
     *     {@link Orientation }
     *     
     */
    public Orientation getBeamDirection() {
        return beamDirection;
    }

    /**
     * Sets the value of the beamDirection property.
     * 
     * @param value
     *     allowed object is
     *     {@link Orientation }
     *     
     */
    public void setBeamDirection(Orientation value) {
        this.beamDirection = value;
    }

    /**
     * Gets the value of the ex property.
     * 
     */
    public float getEx() {
        return ex;
    }

    /**
     * Sets the value of the ex property.
     * 
     */
    public void setEx(float value) {
        this.ex = value;
    }

    /**
     * Gets the value of the ez property.
     * 
     */
    public float getEz() {
        return ez;
    }

    /**
     * Sets the value of the ez property.
     * 
     */
    public void setEz(float value) {
        this.ez = value;
    }

    /**
     * Gets the value of the padding1 property.
     * 
     */
    public short getPadding1() {
        return padding1;
    }

    /**
     * Sets the value of the padding1 property.
     * 
     */
    public void setPadding1(short value) {
        this.padding1 = value;
    }

    /**
     * Gets the value of the padding2 property.
     * 
     */
    public byte getPadding2() {
        return padding2;
    }

    /**
     * Sets the value of the padding2 property.
     * 
     */
    public void setPadding2(byte value) {
        this.padding2 = value;
    }

    /**
     * Gets the value of the phase property.
     * 
     */
    public float getPhase() {
        return phase;
    }

    /**
     * Sets the value of the phase property.
     * 
     */
    public void setPhase(float value) {
        this.phase = value;
    }

    /**
     * Gets the value of the referenceSystem property.
     * 
     */
    public float getReferenceSystem() {
        return referenceSystem;
    }

    /**
     * Sets the value of the referenceSystem property.
     * 
     */
    public void setReferenceSystem(float value) {
        this.referenceSystem = value;
    }

}
