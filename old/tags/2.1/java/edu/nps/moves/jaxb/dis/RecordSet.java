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
 * <p>Java class for recordSet complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="recordSet">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pad4" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="recordCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="recordID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="recordLength" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="recordSetSerialNumber" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="recordValues" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "recordSet", propOrder = {
    "pad4",
    "recordCount",
    "recordID",
    "recordLength",
    "recordSetSerialNumber",
    "recordValues"
})
public class RecordSet {

    protected short pad4;
    protected int recordCount;
    protected long recordID;
    protected int recordLength;
    protected long recordSetSerialNumber;
    protected int recordValues;

    /**
     * Gets the value of the pad4 property.
     * 
     */
    public short getPad4() {
        return pad4;
    }

    /**
     * Sets the value of the pad4 property.
     * 
     */
    public void setPad4(short value) {
        this.pad4 = value;
    }

    /**
     * Gets the value of the recordCount property.
     * 
     */
    public int getRecordCount() {
        return recordCount;
    }

    /**
     * Sets the value of the recordCount property.
     * 
     */
    public void setRecordCount(int value) {
        this.recordCount = value;
    }

    /**
     * Gets the value of the recordID property.
     * 
     */
    public long getRecordID() {
        return recordID;
    }

    /**
     * Sets the value of the recordID property.
     * 
     */
    public void setRecordID(long value) {
        this.recordID = value;
    }

    /**
     * Gets the value of the recordLength property.
     * 
     */
    public int getRecordLength() {
        return recordLength;
    }

    /**
     * Sets the value of the recordLength property.
     * 
     */
    public void setRecordLength(int value) {
        this.recordLength = value;
    }

    /**
     * Gets the value of the recordSetSerialNumber property.
     * 
     */
    public long getRecordSetSerialNumber() {
        return recordSetSerialNumber;
    }

    /**
     * Sets the value of the recordSetSerialNumber property.
     * 
     */
    public void setRecordSetSerialNumber(long value) {
        this.recordSetSerialNumber = value;
    }

    /**
     * Gets the value of the recordValues property.
     * 
     */
    public int getRecordValues() {
        return recordValues;
    }

    /**
     * Sets the value of the recordValues property.
     * 
     */
    public void setRecordValues(int value) {
        this.recordValues = value;
    }

}
