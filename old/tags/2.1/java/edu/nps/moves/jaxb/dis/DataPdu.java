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
 * <p>Java class for dataPdu complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dataPdu">
 *   &lt;complexContent>
 *     &lt;extension base="{}simulationManagementFamilyPdu">
 *       &lt;sequence>
 *         &lt;element name="fixedDatums" type="{http://www.w3.org/2001/XMLSchema}anyType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="numberOfFixedDatumRecords" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="numberOfVariableDatumRecords" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="padding1" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="requestID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="variableDatums" type="{http://www.w3.org/2001/XMLSchema}anyType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataPdu", propOrder = {
    "fixedDatums",
    "numberOfFixedDatumRecords",
    "numberOfVariableDatumRecords",
    "padding1",
    "requestID",
    "variableDatums"
})
public class DataPdu
    extends SimulationManagementFamilyPdu
{

    @XmlElement(nillable = true)
    protected List<Object> fixedDatums;
    protected long numberOfFixedDatumRecords;
    protected long numberOfVariableDatumRecords;
    protected long padding1;
    protected long requestID;
    @XmlElement(nillable = true)
    protected List<Object> variableDatums;

    /**
     * Gets the value of the fixedDatums property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fixedDatums property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFixedDatums().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Object }
     * 
     * 
     */
    public List<Object> getFixedDatums() {
        if (fixedDatums == null) {
            fixedDatums = new ArrayList<Object>();
        }
        return this.fixedDatums;
    }

    /**
     * Gets the value of the numberOfFixedDatumRecords property.
     * 
     */
    public long getNumberOfFixedDatumRecords() {
        return numberOfFixedDatumRecords;
    }

    /**
     * Sets the value of the numberOfFixedDatumRecords property.
     * 
     */
    public void setNumberOfFixedDatumRecords(long value) {
        this.numberOfFixedDatumRecords = value;
    }

    /**
     * Gets the value of the numberOfVariableDatumRecords property.
     * 
     */
    public long getNumberOfVariableDatumRecords() {
        return numberOfVariableDatumRecords;
    }

    /**
     * Sets the value of the numberOfVariableDatumRecords property.
     * 
     */
    public void setNumberOfVariableDatumRecords(long value) {
        this.numberOfVariableDatumRecords = value;
    }

    /**
     * Gets the value of the padding1 property.
     * 
     */
    public long getPadding1() {
        return padding1;
    }

    /**
     * Sets the value of the padding1 property.
     * 
     */
    public void setPadding1(long value) {
        this.padding1 = value;
    }

    /**
     * Gets the value of the requestID property.
     * 
     */
    public long getRequestID() {
        return requestID;
    }

    /**
     * Sets the value of the requestID property.
     * 
     */
    public void setRequestID(long value) {
        this.requestID = value;
    }

    /**
     * Gets the value of the variableDatums property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the variableDatums property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVariableDatums().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Object }
     * 
     * 
     */
    public List<Object> getVariableDatums() {
        if (variableDatums == null) {
            variableDatums = new ArrayList<Object>();
        }
        return this.variableDatums;
    }

}
