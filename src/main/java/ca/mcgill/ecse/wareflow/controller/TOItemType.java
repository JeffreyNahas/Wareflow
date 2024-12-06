/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/

package ca.mcgill.ecse.wareflow.controller;
import java.util.*;

// line 93 "../../../../../WareFlowTransferObjects.ump"
public class TOItemType
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOItemType Attributes
  private String name;
  private int expectedLifeSpanInDays;

  //TOItemType Associations
  private List<TOItemContainer> tOItemContainers;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOItemType(String aName, int aExpectedLifeSpanInDays)
  {
    name = aName;
    expectedLifeSpanInDays = aExpectedLifeSpanInDays;
    tOItemContainers = new ArrayList<TOItemContainer>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setExpectedLifeSpanInDays(int aExpectedLifeSpanInDays)
  {
    boolean wasSet = false;
    expectedLifeSpanInDays = aExpectedLifeSpanInDays;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public int getExpectedLifeSpanInDays()
  {
    return expectedLifeSpanInDays;
  }
  /* Code from template association_GetMany */
  public TOItemContainer getTOItemContainer(int index)
  {
    TOItemContainer aTOItemContainer = tOItemContainers.get(index);
    return aTOItemContainer;
  }

  public List<TOItemContainer> getTOItemContainers()
  {
    List<TOItemContainer> newTOItemContainers = Collections.unmodifiableList(tOItemContainers);
    return newTOItemContainers;
  }

  public int numberOfTOItemContainers()
  {
    int number = tOItemContainers.size();
    return number;
  }

  public boolean hasTOItemContainers()
  {
    boolean has = tOItemContainers.size() > 0;
    return has;
  }

  public int indexOfTOItemContainer(TOItemContainer aTOItemContainer)
  {
    int index = tOItemContainers.indexOf(aTOItemContainer);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfTOItemContainers()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
  public boolean addTOItemContainer(TOItemContainer aTOItemContainer)
  {
    boolean wasAdded = false;
    if (tOItemContainers.contains(aTOItemContainer)) { return false; }
    tOItemContainers.add(aTOItemContainer);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTOItemContainer(TOItemContainer aTOItemContainer)
  {
    boolean wasRemoved = false;
    if (tOItemContainers.contains(aTOItemContainer))
    {
      tOItemContainers.remove(aTOItemContainer);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addTOItemContainerAt(TOItemContainer aTOItemContainer, int index)
  {  
    boolean wasAdded = false;
    if(addTOItemContainer(aTOItemContainer))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTOItemContainers()) { index = numberOfTOItemContainers() - 1; }
      tOItemContainers.remove(aTOItemContainer);
      tOItemContainers.add(index, aTOItemContainer);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTOItemContainerAt(TOItemContainer aTOItemContainer, int index)
  {
    boolean wasAdded = false;
    if(tOItemContainers.contains(aTOItemContainer))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTOItemContainers()) { index = numberOfTOItemContainers() - 1; }
      tOItemContainers.remove(aTOItemContainer);
      tOItemContainers.add(index, aTOItemContainer);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTOItemContainerAt(aTOItemContainer, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    tOItemContainers.clear();
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "expectedLifeSpanInDays" + ":" + getExpectedLifeSpanInDays()+ "]";
  }
}