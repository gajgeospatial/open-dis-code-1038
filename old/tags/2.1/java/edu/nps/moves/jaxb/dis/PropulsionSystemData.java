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
 * <p>Java class for propulsionSystemData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="propulsionSystemData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="engineRpm" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="powerSetting" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "propulsionSystemData", propOrder = {
    "engineRpm",
    "powerSetting"
})
public class PropulsionSystemData {

    protected float engineRpm;
    protected float powerSetting;

    /**
     * Gets the value of the engineRpm property.
     * 
     */
    public float getEngineRpm() {
        return engineRpm;
    }

    /**
     * Sets the value of the engineRpm property.
     * 
     */
    public void setEngineRpm(float value) {
        this.engineRpm = value;
    }

    /**
     * Gets the value of the powerSetting property.
     * 
     */
    public float getPowerSetting() {
        return powerSetting;
    }

    /**
     * Sets the value of the powerSetting property.
     * 
     */
    public void setPowerSetting(float value) {
        this.powerSetting = value;
    }

}
