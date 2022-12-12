#ifndef _PDU_BANK_H_
#define _PDU_BANK_H_

#include <DIS/Pdu.h>
#include <DIS/PDUType.h>

namespace DIS
{
   class PduBank
   {
   public:
     static Pdu* GetStaticPDU( DIS::PDUType type );  
   };   
}

#endif // _PDU_BANK_H_

