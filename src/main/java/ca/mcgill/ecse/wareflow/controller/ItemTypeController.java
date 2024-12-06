package ca.mcgill.ecse.wareflow.controller;

import ca.mcgill.ecse.wareflow.application.WareFlowApplication;
import ca.mcgill.ecse.wareflow.model.ItemType;
import ca.mcgill.ecse.wareflow.model.WareFlow;
import ca.mcgill.ecse.wareflow.persistence.WareFlowPersistence;

public class ItemTypeController {
	private static WareFlow wareflow=WareFlowApplication.getWareFlow();
	
	
	
	/**
	 * @author Marc Germanos
	 */
	//add Item Type method
  public static String addItemType(String name, int expectedLifeSpanInDays) {
    String error="";
    //check if name is empty
    if(name==null||name.length()==0) {
    	return "The name must not be empty";
    }
    //check if life span is not positive
    if (expectedLifeSpanInDays<=0) {
    	return "The expected life span must be greater than 0 days";
    }
    //find item in the system with the same name
    ItemType item=ItemType.getWithName(name);
    //check if item with the same name exists in the system
    if(item!=null) {
    	return "The item type already exists";
    }
    //no items with the same name were found and all inputs are valid
    wareflow.addItemType(name,expectedLifeSpanInDays);
    WareFlowPersistence.save();
    return error;
  }
  
  
  /**
   * @author Marc Germanos
   */
  public static String updateItemType(String oldName, String newName,
      int newExpectedLifeSpanInDays) {
    String error="";
    //check if any of the input strings is empty (illegal)
    if (oldName==null||newName==null||oldName.length()<1||newName.length()<1) {
    	return "The name must not be empty";
    }
    //check if the desired expected life span is not positive
    if(newExpectedLifeSpanInDays<1) {
    	return "The expected life span must be greater than 0 days";
    }
    //find item type with the name oldName
    ItemType item=ItemType.getWithName(oldName);
    //check if an item type with name oldName exists in the system
    if(item==null) {
    	return "The item type does not exist";
    }
    //check if both newName and oldNames are equal
    if(oldName.equals(newName)) {
    	//change expected life span while keeping the name the same
    	item.setExpectedLifeSpanInDays(newExpectedLifeSpanInDays);
        return error;
    }
    //find an item type with name newName 
    ItemType checkItem=ItemType.getWithName(newName);
    //check if an item type with name NewName exists in the system
    if(checkItem!=null) {
    	return "The item type already exists";
    }
    //No problems were found in the input
    //change the name and the date 
    item.setName(newName);
    item.setExpectedLifeSpanInDays(newExpectedLifeSpanInDays);
    WareFlowPersistence.save();
    return error;
  }
  //git pus
   /**
   * @author Marc Germanos
   */
  //delete an item Type
  public static void deleteItemType(String name) {
	//get item with name name can be null or empty it doesn't matter
    ItemType item =ItemType.getWithName(name);
    //check if item type with name name doesn't exists 
    //if item type doesn't exist exit the code
    if(item==null) {return;}
    //delete item type knowing it exists
    item.delete();
    WareFlowPersistence.save();
    }
}
