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
 * <p>Java class for acousticBeamFundamentalParameter complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="acousticBeamFundamentalParameter">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="activeEmissionParameterIndex" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="azimuthalBeamwidth" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="beamCenterAzimuth" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="beamCenterDE" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="deBeamwidth" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="scanPattern" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "acousticBeamFundamentalParameter", propOrder = {
    "activeEmissionParameterIndex",
    "azimuthalBeamwidth",
    "beamCenterAzimuth",
    "beamCenterDE",
    "deBeamwidth",
    "scanPattern"
})
public class AcousticBeamFundamentalParameter {

    protected int activeEmissionParameterIndex;
    protected float azimuthalBeamwidth;
    protected float beamCenterAzimuth;
    protected float beamCenterDE;
    protected float deBeamwidth;
    protected int scanPattern;

    /**
     * Gets the value of the activeEmissionParameterIndex property.
     * 
     */
    public int getActiveEmissionParameterIndex() {
        return activeEmissionParameterIndex;
    }

    /**
     * Sets the value of the activeEmissionParameterIndex property.
     * 
     */
    public void setActiveEmissionParameterIndex(int value) {
        this.activeEmissionParameterIndex = value;
    }

    /**
     * Gets the value of the azimuthalBeamwidth property.
     * 
     */
    public float getAzimuthalBeamwidth() {
        return azimuthalBeamwidth;
    }

    /**
     * Sets the value of the azimuthalBeamwidth property.
     * 
     */
    public void setAzimuthalBeamwidth(float value) {
        this.azimuthalBeamwidth = value;
    }

    /**
     * Gets the value of the beamCenterAzimuth property.
     * 
     */
    public float getBeamCenterAzimuth() {
        return beamCenterAzimuth;
    }

    /**
     * Sets the value of the beamCenterAzimuth property.
     * 
     */
    public void setBeamCenterAzimuth(float value) {
        this.beamCenterAzimuth = value;
    }

    /**
     * Gets the value of the beamCenterDE property.
     * 
     */
    public float getBeamCenterDE() {
        return beamCenterDE;
    }

    /**
     * Sets the value of the beamCenterDE property.
     * 
     */
    public void setBeamCenterDE(float value) {
        this.beamCenterDE = value;
    }

    /**
     * Gets the value of the deBeamwidth property.
     * 
     */
    public float getDeBeamwidth() {
        return deBeamwidth;
    }

    /**
     * Sets the value of the deBeamwidth property.
     * 
     */
    public void setDeBeamwidth(float value) {
        this.deBeamwidth = value;
    }

    /**
     * Gets the value of the scanPattern property.
     * 
     */
    public int getScanPattern() {
        return scanPattern;
    }

    /**
     * Sets the value of the scanPattern property.
     * 
     */
    public void setScanPattern(int value) {
        this.scanPattern = value;
    }

}
