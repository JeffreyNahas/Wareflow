/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/

package ca.mcgill.ecse.wareflow.controller;
import java.sql.Date;
import java.util.*;

// line 3 "../../../../../WareFlowTransferObjects.ump"
public class TOShipmentOrder
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOShipmentOrder Attributes
  private int id;
  private int quantity;
  private Date placedOnDate;
  private String description;
  private String orderPlacer;
  private String status;
  private String processedBy;
  private String timeToResolve;
  private String priority;
  private boolean approvalRequired;
  private String itemName;
  private int expectedLifeSpanInDays;
  private Date addedOnDate;
  private int areaNumber;
  private int slotNumber;

  //TOShipmentOrder Associations
  private List<TOShipmentNote> notes;
  private TOUser tOUser;
  private TOWarehouseStaff tOWarehouseStaff;
  private TOManager orderApprover;
  private TOItemContainer tOItemContainer;

  //Helper Variables
  private boolean canSetNotes;
  private boolean canSetTOUser;
  private boolean canSetTOWarehouseStaff;
  private boolean canSetOrderApprover;
  private boolean canSetTOItemContainer;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOShipmentOrder(int aId, int aQuantity, Date aPlacedOnDate, String aDescription, String aOrderPlacer, String aStatus, String aProcessedBy, String aTimeToResolve, String aPriority, boolean aApprovalRequired, String aItemName, int aExpectedLifeSpanInDays, Date aAddedOnDate, int aAreaNumber, int aSlotNumber, TOUser aTOUser, TOWarehouseStaff aTOWarehouseStaff, TOManager aOrderApprover, TOItemContainer aTOItemContainer, TOShipmentNote... allNotes)
  {
    id = aId;
    quantity = aQuantity;
    placedOnDate = aPlacedOnDate;
    description = aDescription;
    orderPlacer = aOrderPlacer;
    status = aStatus;
    processedBy = aProcessedBy;
    timeToResolve = aTimeToResolve;
    priority = aPriority;
    approvalRequired = aApprovalRequired;
    itemName = aItemName;
    expectedLifeSpanInDays = aExpectedLifeSpanInDays;
    addedOnDate = aAddedOnDate;
    areaNumber = aAreaNumber;
    slotNumber = aSlotNumber;
    canSetNotes = true;
    canSetTOUser = true;
    canSetTOWarehouseStaff = true;
    canSetOrderApprover = true;
    canSetTOItemContainer = true;
    notes = new ArrayList<TOShipmentNote>();
    boolean didAddNotes = setNotes(allNotes);
    if (!didAddNotes)
    {
      throw new RuntimeException("Unable to create TOShipmentOrder, must not have duplicate notes. See http://manual.umple.org?RE001ViolationofImmutability.html");
    }
    setTOUser(aTOUser);
    setTOWarehouseStaff(aTOWarehouseStaff);
    setOrderApprover(aOrderApprover);
    setTOItemContainer(aTOItemContainer);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public int getId()
  {
    return id;
  }

  public int getQuantity()
  {
    return quantity;
  }

  public Date getPlacedOnDate()
  {
    return placedOnDate;
  }

  public String getDescription()
  {
    return description;
  }

  public String getOrderPlacer()
  {
    return orderPlacer;
  }

  public String getStatus()
  {
    return status;
  }

  /**
   * the following three attributes are set to null if no one has been assigned to the order
   */
  public String getProcessedBy()
  {
    return processedBy;
  }

  public String getTimeToResolve()
  {
    return timeToResolve;
  }

  public String getPriority()
  {
    return priority;
  }

  public boolean getApprovalRequired()
  {
    return approvalRequired;
  }

  /**
   * the following five attributes are set to null (String/Date) / -1 (Integer) if no item container is specified for the order yet
   */
  public String getItemName()
  {
    return itemName;
  }

  public int getExpectedLifeSpanInDays()
  {
    return expectedLifeSpanInDays;
  }

  public Date getAddedOnDate()
  {
    return addedOnDate;
  }

  public int getAreaNumber()
  {
    return areaNumber;
  }

  public int getSlotNumber()
  {
    return slotNumber;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isApprovalRequired()
  {
    return approvalRequired;
  }
  /* Code from template association_GetMany */
  public TOShipmentNote getNote(int index)
  {
    TOShipmentNote aNote = notes.get(index);
    return aNote;
  }

  public List<TOShipmentNote> getNotes()
  {
    List<TOShipmentNote> newNotes = Collections.unmodifiableList(notes);
    return newNotes;
  }

  public int numberOfNotes()
  {
    int number = notes.size();
    return number;
  }

  public boolean hasNotes()
  {
    boolean has = notes.size() > 0;
    return has;
  }

  public int indexOfNote(TOShipmentNote aNote)
  {
    int index = notes.indexOf(aNote);
    return index;
  }
  /* Code from template association_GetOne */
  public TOUser getTOUser()
  {
    return tOUser;
  }

  public boolean hasTOUser()
  {
    boolean has = tOUser != null;
    return has;
  }
  /* Code from template association_GetOne */
  public TOWarehouseStaff getTOWarehouseStaff()
  {
    return tOWarehouseStaff;
  }

  public boolean hasTOWarehouseStaff()
  {
    boolean has = tOWarehouseStaff != null;
    return has;
  }
  /* Code from template association_GetOne */
  public TOManager getOrderApprover()
  {
    return orderApprover;
  }

  public boolean hasOrderApprover()
  {
    boolean has = orderApprover != null;
    return has;
  }
  /* Code from template association_GetOne */
  public TOItemContainer getTOItemContainer()
  {
    return tOItemContainer;
  }

  public boolean hasTOItemContainer()
  {
    boolean has = tOItemContainer != null;
    return has;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfNotes()
  {
    return 0;
  }
  /* Code from template association_SetUnidirectionalMany */
  private boolean setNotes(TOShipmentNote... newNotes)
  {
    boolean wasSet = false;
    if (!canSetNotes) { return false; }
    canSetNotes = false;
    ArrayList<TOShipmentNote> verifiedNotes = new ArrayList<TOShipmentNote>();
    for (TOShipmentNote aNote : newNotes)
    {
      if (verifiedNotes.contains(aNote))
      {
        continue;
      }
      verifiedNotes.add(aNote);
    }

    if (verifiedNotes.size() != newNotes.length)
    {
      return wasSet;
    }

    notes.clear();
    notes.addAll(verifiedNotes);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetUnidirectionalOptionalOne */
  private boolean setTOUser(TOUser aNewTOUser)
  {
    boolean wasSet = false;
    if (!canSetTOUser) { return false; }
    canSetTOUser = false;
    tOUser = aNewTOUser;
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetUnidirectionalOptionalOne */
  private boolean setTOWarehouseStaff(TOWarehouseStaff aNewTOWarehouseStaff)
  {
    boolean wasSet = false;
    if (!canSetTOWarehouseStaff) { return false; }
    canSetTOWarehouseStaff = false;
    tOWarehouseStaff = aNewTOWarehouseStaff;
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetUnidirectionalOptionalOne */
  private boolean setOrderApprover(TOManager aNewOrderApprover)
  {
    boolean wasSet = false;
    if (!canSetOrderApprover) { return false; }
    canSetOrderApprover = false;
    orderApprover = aNewOrderApprover;
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetUnidirectionalOptionalOne */
  private boolean setTOItemContainer(TOItemContainer aNewTOItemContainer)
  {
    boolean wasSet = false;
    if (!canSetTOItemContainer) { return false; }
    canSetTOItemContainer = false;
    tOItemContainer = aNewTOItemContainer;
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "," +
            "quantity" + ":" + getQuantity()+ "," +
            "description" + ":" + getDescription()+ "," +
            "orderPlacer" + ":" + getOrderPlacer()+ "," +
            "status" + ":" + getStatus()+ "," +
            "processedBy" + ":" + getProcessedBy()+ "," +
            "timeToResolve" + ":" + getTimeToResolve()+ "," +
            "priority" + ":" + getPriority()+ "," +
            "approvalRequired" + ":" + getApprovalRequired()+ "," +
            "itemName" + ":" + getItemName()+ "," +
            "expectedLifeSpanInDays" + ":" + getExpectedLifeSpanInDays()+ "," +
            "areaNumber" + ":" + getAreaNumber()+ "," +
            "slotNumber" + ":" + getSlotNumber()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "placedOnDate" + "=" + (getPlacedOnDate() != null ? !getPlacedOnDate().equals(this)  ? getPlacedOnDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "addedOnDate" + "=" + (getAddedOnDate() != null ? !getAddedOnDate().equals(this)  ? getAddedOnDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "tOUser = "+(getTOUser()!=null?Integer.toHexString(System.identityHashCode(getTOUser())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "tOWarehouseStaff = "+(getTOWarehouseStaff()!=null?Integer.toHexString(System.identityHashCode(getTOWarehouseStaff())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "orderApprover = "+(getOrderApprover()!=null?Integer.toHexString(System.identityHashCode(getOrderApprover())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "tOItemContainer = "+(getTOItemContainer()!=null?Integer.toHexString(System.identityHashCode(getTOItemContainer())):"null");
  }
}