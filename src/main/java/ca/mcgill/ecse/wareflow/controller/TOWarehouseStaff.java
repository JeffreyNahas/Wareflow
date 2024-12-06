/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/

package ca.mcgill.ecse.wareflow.controller;
import java.util.*;

// line 49 "../../../../../WareFlowTransferObjects.ump"
public class TOWarehouseStaff
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOWarehouseStaff Attributes
  private String username;
  private String name;
  private String password;
  private String phoneNumber;

  //TOWarehouseStaff Associations
  private List<TOShipmentNote> tOShipmentNotes;

  //Helper Variables
  private boolean canSetTOShipmentNotes;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOWarehouseStaff(String aUsername, String aName, String aPassword, String aPhoneNumber, TOShipmentNote... allTOShipmentNotes)
  {
    username = aUsername;
    name = aName;
    password = aPassword;
    phoneNumber = aPhoneNumber;
    canSetTOShipmentNotes = true;
    tOShipmentNotes = new ArrayList<TOShipmentNote>();
    boolean didAddTOShipmentNotes = setTOShipmentNotes(allTOShipmentNotes);
    if (!didAddTOShipmentNotes)
    {
      throw new RuntimeException("Unable to create TOWarehouseStaff, must not have duplicate tOShipmentNotes. See http://manual.umple.org?RE001ViolationofImmutability.html");
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
  public TOShipmentNote getTOShipmentNote(int index)
  {
    TOShipmentNote aTOShipmentNote = tOShipmentNotes.get(index);
    return aTOShipmentNote;
  }

  public List<TOShipmentNote> getTOShipmentNotes()
  {
    List<TOShipmentNote> newTOShipmentNotes = Collections.unmodifiableList(tOShipmentNotes);
    return newTOShipmentNotes;
  }

  public int numberOfTOShipmentNotes()
  {
    int number = tOShipmentNotes.size();
    return number;
  }

  public boolean hasTOShipmentNotes()
  {
    boolean has = tOShipmentNotes.size() > 0;
    return has;
  }

  public int indexOfTOShipmentNote(TOShipmentNote aTOShipmentNote)
  {
    int index = tOShipmentNotes.indexOf(aTOShipmentNote);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfTOShipmentNotes()
  {
    return 0;
  }
  /* Code from template association_SetUnidirectionalMany */
  private boolean setTOShipmentNotes(TOShipmentNote... newTOShipmentNotes)
  {
    boolean wasSet = false;
    if (!canSetTOShipmentNotes) { return false; }
    canSetTOShipmentNotes = false;
    ArrayList<TOShipmentNote> verifiedTOShipmentNotes = new ArrayList<TOShipmentNote>();
    for (TOShipmentNote aTOShipmentNote : newTOShipmentNotes)
    {
      if (verifiedTOShipmentNotes.contains(aTOShipmentNote))
      {
        continue;
      }
      verifiedTOShipmentNotes.add(aTOShipmentNote);
    }

    if (verifiedTOShipmentNotes.size() != newTOShipmentNotes.length)
    {
      return wasSet;
    }

    tOShipmentNotes.clear();
    tOShipmentNotes.addAll(verifiedTOShipmentNotes);
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