/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/

package ca.mcgill.ecse.wareflow.controller;
import java.sql.Date;

// line 34 "../../../../../WareFlowTransferObjects.ump"
public class TOShipmentNote
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOShipmentNote Attributes
  private Date date;
  private String description;
  private String noteTakerUsername;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOShipmentNote(Date aDate, String aDescription, String aNoteTakerUsername)
  {
    date = aDate;
    description = aDescription;
    noteTakerUsername = aNoteTakerUsername;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Date getDate()
  {
    return date;
  }

  public String getDescription()
  {
    return description;
  }

  public String getNoteTakerUsername()
  {
    return noteTakerUsername;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "description" + ":" + getDescription()+ "," +
            "noteTakerUsername" + ":" + getNoteTakerUsername()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "date" + "=" + (getDate() != null ? !getDate().equals(this)  ? getDate().toString().replaceAll("  ","    ") : "this" : "null");
  }
}