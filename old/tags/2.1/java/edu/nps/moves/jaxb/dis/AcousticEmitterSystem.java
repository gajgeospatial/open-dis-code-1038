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
 * <p>Java class for acousticEmitterSystem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="acousticEmitterSystem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="acousticFunction" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="acousticID" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="acousticName" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "acousticEmitterSystem", propOrder = {
    "acousticFunction",
    "acousticID",
    "acousticName"
})
public class AcousticEmitterSystem {

    protected short acousticFunction;
    protected short acousticID;
    protected int acousticName;

    /**
     * Gets the value of the acousticFunction property.
     * 
     */
    public short getAcousticFunction() {
        return acousticFunction;
    }

    /**
     * Sets the value of the acousticFunction property.
     * 
     */
    public void setAcousticFunction(short value) {
        this.acousticFunction = value;
    }

    /**
     * Gets the value of the acousticID property.
     * 
     */
    public short getAcousticID() {
        return acousticID;
    }

    /**
     * Sets the value of the acousticID property.
     * 
     */
    public void setAcousticID(short value) {
        this.acousticID = value;
    }

    /**
     * Gets the value of the acousticName property.
     * 
     */
    public int getAcousticName() {
        return acousticName;
    }

    /**
     * Sets the value of the acousticName property.
     * 
     */
    public void setAcousticName(int value) {
        this.acousticName = value;
    }

}
