#ifndef DATAREPRESENTATION__H
#define DATAREPRESENTATION__H

#include <string>
#include <disenum/Enumeration.h>

namespace DIS {

class DataRepresentation : public Enumeration {
  public:
    static DataRepresentation TYPE_0;
    static DataRepresentation TYPE_1;
    static DataRepresentation TYPE_2;
    

    /** Returns the string description associated with the enumerated instance with this value.
     * If there is no enumerated instance for this value, the string Invalid enumeration: <val> is returned.     */
    static std::string getDescriptionForValue(int aVal);

    /** Returns the enumerated instance with this value.
     * If there is no enumerated instance for this value, the exception is thrown.     */
    static DataRepresentation getEnumerationForValue(int aVal) throw(EnumException);

    /** Returns true if there is an enumerated instance for this value, false otherwise. */
    static bool enumerationForValueExists(int aVal);

    typedef hashMap<int,DataRepresentation*> enumContainer;
    static enumContainer getEnumerations();

    DataRepresentation& operator=(const int& aVal) throw(EnumException);

  private:
    /** Constructor */
	  DataRepresentation(int value, std::string description);

	  static DataRepresentation* findEnumeration(int aVal);
    static enumContainer enumerations;

};  /* DataRepresentation */


}  /* namespace DIS */

#endif /* DATAREPRESENTATION__H */

