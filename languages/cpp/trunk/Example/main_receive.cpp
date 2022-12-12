
#include <Example/Connection.h>                  // for reading packets from the socket
#include <Example/EntityStatePduProcessor.h>     // for usage
#include <Example/Utils.h>

#include <DIS/IncomingMessage.h>                 // for library usage
#include <DIS/EntityStatePdu.h>                  // for library usage

#include <cstring>                       // for strlen
#include <cstddef>                       // for size_t definition

#include <errno.h>
#include <memory.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include <pcap.h>

int main(int argc, char* argv[])
{
   unsigned int port(3000);
   std::string ip("239.1.2.3");
   bool from_file = false;
   std::string filename;
   if( argc > 2 )
   {
	   std::string test = argv[1];
	   if (test == "file")
	   {
		   from_file = true;
		   filename = argv[2];
	   }
	   else
	   {
		   port = Example::ToType<unsigned int>(argv[1]);
		   ip = argv[2];
	   }
   }

   if (from_file)
   {
	   pcap_t *fp;
	   char errbuf[PCAP_ERRBUF_SIZE];
	   /* Open the capture file */
	   if ((fp = pcap_open_offline(filename.c_str(),			// name of the device
								   errbuf					// error buffer
								    )) == NULL)
	   {
		   fprintf(stderr, "\nUnable to open the file %s.\n", filename.c_str());
		   return -1;
	   }
	   int status = 0;
	   DIS::Endian endian = DIS::BIG;

	   Example::EntityStatePduProcessor processor;

	   const unsigned char es_pdu_type = 1;
	   DIS::IncomingMessage incoming;
	   incoming.AddProcessor(es_pdu_type, &processor);

	   while (status == 0)
	   {
		   struct pcap_pkthdr h;
		   char buffer[Example::MTU_SIZE + 1];
		   buffer[Example::MTU_SIZE] = '\0';   // should be NULL terminated somewhere in case the network read does not do this?

		   status = pcap_next_packet(fp, &h, (u_char *)buffer, Example::MTU_SIZE);

		   if (status) {
			   if (status == 1)
				   return (0);
			   return (status);
		   }
		   // engage the higher level support
		   int bytes_read = h.len - 42;
		   if(bytes_read > 0)
				incoming.Process(buffer+42, bytes_read, endian);

	   }
	   incoming.RemoveProcessor(es_pdu_type, &processor);
	   pcap_close(fp);

   }
   else
   {
	   Example::Connection multicast;
	   multicast.Connect(port, ip);
	   DIS::Endian endian = DIS::BIG;

	   char buffer[Example::MTU_SIZE + 1];
	   buffer[Example::MTU_SIZE] = '\0';   // should be NULL terminated somewhere in case the network read does not do this?

	   Example::EntityStatePduProcessor processor;

	   const unsigned char es_pdu_type = 1;
	   DIS::IncomingMessage incoming;
	   incoming.AddProcessor(es_pdu_type, &processor);
	   while (true)
	   {
		   ///\todo find a way to use the stream rather than a raw char buffer,
		   /// so that copying the buffer into the DataStream within the IncomingMessage
		   /// will not be necessary.
		   size_t bytes_read = multicast.Receive(buffer, Example::MTU_SIZE);

		   // engage the higher level support
		   incoming.Process(buffer, bytes_read, endian);
	   }

	   incoming.RemoveProcessor(es_pdu_type, &processor);
	   multicast.Disconnect();
   }
   return 0;
}

