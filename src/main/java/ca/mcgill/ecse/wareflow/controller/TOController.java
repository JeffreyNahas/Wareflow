package ca.mcgill.ecse.wareflow.controller;

import java.sql.Date;
import ca.mcgill.ecse.wareflow.application.WareFlowApplication;
import ca.mcgill.ecse.wareflow.controller.model.Employee;
import ca.mcgill.ecse.wareflow.model.Client;
import ca.mcgill.ecse.wareflow.model.ItemContainer;
import ca.mcgill.ecse.wareflow.model.WareFlow;
import ca.mcgill.ecse.wareflow.model.ItemType;
import ca.mcgill.ecse.wareflow.model.Manager;
import ca.mcgill.ecse.wareflow.persistence.WareFlowPersistence;

//------------------------
  // INTERFACE
  //------------------------
//@author Paolo Lahoud

public class TOController{
/*	private static WareFlow wareflow = WareFlowApplication.getWareFlow();

	
	public static void toAddClient (String aAddress, String aUsername, String aName, String aPassword, String aPhoneNumber){
		UserController.addEmployeeOrClient(aUsername, aPassword, aName, aPhoneNumber, true, aAddress);
	}

public static void toDeleteClient (String username){
  UserController.deleteEmployeeOrClient(username);
}
//comment
public static void toUpdateClient (Client client, String aUsername, String aName, String aPassword, String aPhoneNumber) {
  UserController.updateEmployeeOrClient(aUsername, aPassword, aName, aPhoneNumber, aPhoneNumber);
}

//------------------------
// INTERFACE
//------------------------

//@author Paolo Lahoud

public static void toAddEmployee (String aUsername, String aName, String aPassword, String aPhoneNumber){
	UserController.addEmployeeOrClient(aUsername, aPassword, aName, aPhoneNumber, true, aPhoneNumber);
}

public static void toDeleteEmployee (String aUsername){
	UserController.deleteEmployeeOrClient(aUsername);
}

public static void toUpdateEmployee (Employee employee, String aUsername, String aName, String aPassword, String aPhoneNumber) {
	UserController.updateEmployeeOrClient(aUsername, aPassword, aName, aPhoneNumber, aPhoneNumber);
}




public static void addItemType(String aName, int aExpectedLifeSpanInDays) {
    ItemTypeController.addItemType(aName, aExpectedLifeSpanInDays);
  }

  public static void deleteItemType (String aName){
    ItemTypeController.deleteItemType(aName);
  }
  
  public static void updateItemType ( String name1, String name2, int aExpectedLifeSpanInDays) {
    ItemTypeController.updateItemType(name1, name2, aExpectedLifeSpanInDays);
  }
  
  
  public void updatePassword(String newPassword) {
	  wareflow.getManager().setPassword(newPassword);
  }
  
//------------------------
  // INTERFACE
  //------------------------

  public static void addItemContainer(int aContainerNumber, int aAreaNumber, int aSlotNumber, Date aAddedOnDate,String itemType) {
	  ItemContainerController.addItemContainer(aContainerNumber, aAreaNumber, aSlotNumber, aAddedOnDate,itemType );
  }

  public static void deleteItemContainer (int number){
    ItemContainerController.deleteItemContainer(number);
  }
  
  public static void updateItemContainer ( int aContainerNumber, int aAreaNumber, int aSlotNumber, Date aAddedOnDate, String itemName) {
	  ItemContainerController.updateItemContainer(aContainerNumber,aAreaNumber,aSlotNumber,aAddedOnDate,itemName);
  }
  
  public static void addShipmentNote(Date date, String description,int id,String username) {
	  ShipmentNoteController.addShipmentNote(date, description,id,username);
  }*/
}