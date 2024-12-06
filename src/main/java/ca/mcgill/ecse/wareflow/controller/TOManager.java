/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/

package ca.mcgill.ecse.wareflow.controller;
import java.util.*;

// line 75 "../../../../../WareFlowTransferObjects.ump"
public class TOManager
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOManager Attributes
  private String username;
  private String name;
  private String password;
  private String phoneNumber;

  //TOManager Associations
  private List<TOShipmentOrder> shipmentOrders;

  //Helper Variables
  private boolean canSetShipmentOrders;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOManager(String aUsername, String aName, String aPassword, String aPhoneNumber, TOShipmentOrder... allShipmentOrders)
  {
    username = aUsername;
    name = aName;
    password = aPassword;
    phoneNumber = aPhoneNumber;
    canSetShipmentOrders = true;
    shipmentOrders = new ArrayList<TOShipmentOrder>();
    boolean didAddShipmentOrders = setShipmentOrders(allShipmentOrders);
    if (!didAddShipmentOrders)
    {
      throw new RuntimeException("Unable to create TOManager, must not have duplicate shipmentOrders. See http://manual.umple.org?RE001ViolationofImmutability.html");
    }
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
  /* Code from template association_GetMany */
  public TOShipmentOrder getShipmentOrder(int index)
  {
    TOShipmentOrder aShipmentOrder = shipmentOrders.get(index);
    return aShipmentOrder;
  }

  public List<TOShipmentOrder> getShipmentOrders()
  {
    List<TOShipmentOrder> newShipmentOrders = Collections.unmodifiableList(shipmentOrders);
    return newShipmentOrders;
  }

  public int numberOfShipmentOrders()
  {
    int number = shipmentOrders.size();
    return number;
  }

  public boolean hasShipmentOrders()
  {
    boolean has = shipmentOrders.size() > 0;
    return has;
  }

  public int indexOfShipmentOrder(TOShipmentOrder aShipmentOrder)
  {
    int index = shipmentOrders.indexOf(aShipmentOrder);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfShipmentOrders()
  {
    return 0;
  }
  /* Code from template association_SetUnidirectionalMany */
  private boolean setShipmentOrders(TOShipmentOrder... newShipmentOrders)
  {
    boolean wasSet = false;
    if (!canSetShipmentOrders) { return false; }
    canSetShipmentOrders = false;
    ArrayList<TOShipmentOrder> verifiedShipmentOrders = new ArrayList<TOShipmentOrder>();
    for (TOShipmentOrder aShipmentOrder : newShipmentOrders)
    {
      if (verifiedShipmentOrders.contains(aShipmentOrder))
      {
        continue;
      }
      verifiedShipmentOrders.add(aShipmentOrder);
    }

    if (verifiedShipmentOrders.size() != newShipmentOrders.length)
    {
      return wasSet;
    }

    shipmentOrders.clear();
    shipmentOrders.addAll(verifiedShipmentOrders);
    wasSet = true;
    return wasSet;
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