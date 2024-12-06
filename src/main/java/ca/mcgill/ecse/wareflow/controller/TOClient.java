/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/

package ca.mcgill.ecse.wareflow.controller;

// line 58 "../../../../../WareFlowTransferObjects.ump"
public class TOClient
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOClient Attributes
  private String address;
  private String username;
  private String name;
  private String password;
  private String phoneNumber;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOClient(String aAddress, String aUsername, String aName, String aPassword, String aPhoneNumber)
  {
    address = aAddress;
    username = aUsername;
    name = aName;
    password = aPassword;
    phoneNumber = aPhoneNumber;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public String getAddress()
  {
    return address;
  }

  public String getUsername()
  {
    return username;
  }

  public String getName()
  {
    return name;
  }

  public String getPassword()
  {
    return password;
  }

  public String getPhoneNumber()
  {
    return phoneNumber;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "address" + ":" + getAddress()+ "," +
            "username" + ":" + getUsername()+ "," +
            "name" + ":" + getName()+ "," +
            "password" + ":" + getPassword()+ "," +
            "phoneNumber" + ":" + getPhoneNumber()+ "]";
  }
}