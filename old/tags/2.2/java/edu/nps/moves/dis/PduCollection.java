package edu.nps.moves.dis;

import java.util.ArrayList;
import java.util.List;

public class PduCollection 
{
    List<Pdu> pdus = new ArrayList();
    
    public PduCollection()
    {
    }
    
    public List<Pdu> getPdus()
    {
        return pdus;
    }
    
    public void setPdus(List<Pdu> pdus)
    {
        this.pdus = pdus;
    }
    
    

}
