/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/

package ca.mcgill.ecse.wareflow.controller;
import java.sql.Date;

// line 85 "../../../../../WareFlowTransferObjects.ump"
public class TOItemContainer
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOItemContainer Attributes
  private int containerNumber;
  private int areaNumber;
  private int slotNumber;
  private Date addedOnDate;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOItemContainer(int aContainerNumber, int aAreaNumber, int aSlotNumber, Date aAddedOnDate)
  {
    containerNumber = aContainerNumber;
    areaNumber = aAreaNumber;
    slotNumber = aSlotNumber;
    addedOnDate = aAddedOnDate;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public int getContainerNumber()
  {
    return containerNumber;
  }

  public int getAreaNumber()
  {
    return areaNumber;
  }

  public int getSlotNumber()
  {
    return slotNumber;
  }

  public Date getAddedOnDate()
  {
    return addedOnDate;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "containerNumber" + ":" + getContainerNumber()+ "," +
            "areaNumber" + ":" + getAreaNumber()+ "," +
            "slotNumber" + ":" + getSlotNumber()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "addedOnDate" + "=" + (getAddedOnDate() != null ? !getAddedOnDate().equals(this)  ? getAddedOnDate().toString().replaceAll("  ","    ") : "this" : "null");
  }
}