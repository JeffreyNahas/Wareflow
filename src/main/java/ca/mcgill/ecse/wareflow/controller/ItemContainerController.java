package ca.mcgill.ecse.wareflow.controller;

import java.sql.Date;
import ca.mcgill.ecse.wareflow.application.WareFlowApplication;

import ca.mcgill.ecse.wareflow.model.ItemContainer;
import ca.mcgill.ecse.wareflow.model.WareFlow;
import ca.mcgill.ecse.wareflow.model.ItemType;
import ca.mcgill.ecse.wareflow.persistence.WareFlowPersistence;
public class ItemContainerController {
    private static WareFlow wareflow=WareFlowApplication.getWareFlow();

  /**
   * @author Rami EL-NAWAM
   */
  public static String addItemContainer(int containerNumber, int areaNumber, int slotNumber,
      Date addedOnDate, String itemTypeName) {
    
    String error="";
    
    //check if itemTypeName is in the list of name by iterating through them
    boolean exists=false;
    ItemType correctItem=null;
    for(ItemType item :wareflow.getItemTypes()) {
      if(item.getName().equals(itemTypeName)) {
        exists=true;
        correctItem=item;
        break;
      }
    }
    for(ItemContainer test:wareflow.getItemContainers()) {
    	if(test.getContainerNumber()==containerNumber) {
    		return "Container already exists";
    	}
    }
    if(!exists) {
      return "The item type does not exist";
    }
    
    //check if containerNumber is less than 1
    if (containerNumber < 1) {
      return "The container number shall not be less than 1";
    }
    
    //check if the areaNumber is less than 0 which is supposed to be impossible
    if (areaNumber < 0) {
      return "The area number shall not be less than 0";
    }
    
    //check if slot number is negative
    if (slotNumber < 0) {
      return "The slot number shall not be less than 0";
    }
    
    wareflow.addItemContainer(containerNumber, areaNumber, slotNumber, addedOnDate,correctItem );
    WareFlowPersistence.save();
    return error; 
  }
  
  
  /**
   * @author Rami EL-NAWAM
   */
  public static String updateItemContainer(int containerNumber, int newAreaNumber,
      int newSlotNumber, Date newAddedOnDate, String newItemTypeName) {
    String error="";
    //check if itemtype is valid by iterating through the know itemtypes and hold the name of the correct itemtype to update it
    boolean exists=false;
    ItemType correctItem=null;
    for(ItemType item :wareflow.getItemTypes()) {
      if(item.getName().equals(newItemTypeName)) {
        exists=true;
        correctItem=item;
        break;
      }
    }
    if(!exists) {
      return "The item type does not exist";
    }
    
    //check if the areaNumber is less than 0 which is supposed to be impossible
    if (newAreaNumber < 0) {
      return "The area number shall not be less than 0";
    }
    
    //check if slot number is negative
    if (newSlotNumber < 0) {
      return "The slot number shall not be less than 0";
    }
    
    //no problems were found in the input
    //update the AreaNumber, AddedOnDate, SlotNumber and the ItemTypeName
    wareflow.getItemContainer(containerNumber-1).setAreaNumber(newAreaNumber);
    wareflow.getItemContainer(containerNumber-1).setAddedOnDate(newAddedOnDate);
    wareflow.getItemContainer(containerNumber-1).setSlotNumber(newSlotNumber);
    wareflow.getItemContainer(containerNumber-1).setItemType(correctItem);
    WareFlowPersistence.save();
    return error; 
    //comments
    
  }
  
  
  /**
   *@author Paolo Lahoud
   */
  public static void deleteItemContainer(int containerNumber) {
	  
    ItemContainer itemContainer = ItemContainer.getWithContainerNumber(containerNumber);
    //delete item container if it exists otherwise task is already done
    if (itemContainer != null) {
      itemContainer.delete();
      WareFlowPersistence.save();
  }
}
}
