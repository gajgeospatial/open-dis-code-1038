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
 * <p>Java class for articulationParameter complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="articulationParameter">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="changeIndicator" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="parameterType" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="parameterTypeDesignator" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="parameterValue" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="partAttachedTo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "articulationParameter", propOrder = {
    "changeIndicator",
    "parameterType",
    "parameterTypeDesignator",
    "parameterValue",
    "partAttachedTo"
})
public class ArticulationParameter {

    protected short changeIndicator;
    protected int parameterType;
    protected short parameterTypeDesignator;
    protected double parameterValue;
    protected int partAttachedTo;

    /**
     * Gets the value of the changeIndicator property.
     * 
     */
    public short getChangeIndicator() {
        return changeIndicator;
    }

    /**
     * Sets the value of the changeIndicator property.
     * 
     */
    public void setChangeIndicator(short value) {
        this.changeIndicator = value;
    }

    /**
     * Gets the value of the parameterType property.
     * 
     */
    public int getParameterType() {
        return parameterType;
    }

    /**
     * Sets the value of the parameterType property.
     * 
     */
    public void setParameterType(int value) {
        this.parameterType = value;
    }

    /**
     * Gets the value of the parameterTypeDesignator property.
     * 
     */
    public short getParameterTypeDesignator() {
        return parameterTypeDesignator;
    }

    /**
     * Sets the value of the parameterTypeDesignator property.
     * 
     */
    public void setParameterTypeDesignator(short value) {
        this.parameterTypeDesignator = value;
    }

    /**
     * Gets the value of the parameterValue property.
     * 
     */
    public double getParameterValue() {
        return parameterValue;
    }

    /**
     * Sets the value of the parameterValue property.
     * 
     */
    public void setParameterValue(double value) {
        this.parameterValue = value;
    }

    /**
     * Gets the value of the partAttachedTo property.
     * 
     */
    public int getPartAttachedTo() {
        return partAttachedTo;
    }

    /**
     * Sets the value of the partAttachedTo property.
     * 
     */
    public void setPartAttachedTo(int value) {
        this.partAttachedTo = value;
    }

}
