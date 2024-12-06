/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/

package ca.mcgill.ecse.wareflow.controller;

// line 67 "../../../../../WareFlowTransferObjects.ump"
public class TOEmployee
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOEmployee Attributes
  private String username;
  private String name;
  private String password;
  private String phoneNumber;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOEmployee(String aUsername, String aName, String aPassword, String aPhoneNumber)
  {
    username = aUsername;
    name = aName;
    password = aPassword;
    phoneNumber = aPhoneNumber;
  }

  //------------------------
  // INTERFACE
  //------------------------

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
            "username" + ":" + getUsername()+ "," +
            "name" + ":" + getName()+ "," +
            "password" + ":" + getPassword()+ "," +
            "phoneNumber" + ":" + getPhoneNumber()+ "]";
  }
}