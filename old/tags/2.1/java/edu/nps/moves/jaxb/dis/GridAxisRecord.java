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
 * <p>Java class for gridAxisRecord complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="gridAxisRecord">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dataRepresentation" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="sampleType" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "gridAxisRecord", propOrder = {
    "dataRepresentation",
    "sampleType"
})
@XmlSeeAlso({
    GridAxisRecordRepresentation1 .class,
    GridAxisRecordRepresentation2 .class,
    GridAxisRecordRepresentation0 .class
})
public class GridAxisRecord {

    protected int dataRepresentation;
    protected int sampleType;

    /**
     * Gets the value of the dataRepresentation property.
     * 
     */
    public int getDataRepresentation() {
        return dataRepresentation;
    }

    /**
     * Sets the value of the dataRepresentation property.
     * 
     */
    public void setDataRepresentation(int value) {
        this.dataRepresentation = value;
    }

    /**
     * Gets the value of the sampleType property.
     * 
     */
    public int getSampleType() {
        return sampleType;
    }

    /**
     * Sets the value of the sampleType property.
     * 
     */
    public void setSampleType(int value) {
        this.sampleType = value;
    }

}
