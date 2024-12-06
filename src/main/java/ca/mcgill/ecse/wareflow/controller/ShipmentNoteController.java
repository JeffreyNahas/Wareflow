package ca.mcgill.ecse.wareflow.controller;
import ca.mcgill.ecse.wareflow.application.WareFlowApplication;
import ca.mcgill.ecse.wareflow.model.*;
import ca.mcgill.ecse.wareflow.persistence.WareFlowPersistence;

import java.sql.Date;

public class ShipmentNoteController {
  private static WareFlow wareflow=WareFlowApplication.getWareFlow();
  
  
  /**
   * @author Jeffery Nahas
   */
  public static String addShipmentNote(Date date, String description, int orderID,
      String username) {
    // Initialize the order object to null
    ShipmentOrder order = null;

    // Iterate through all orders in the system to find the one with the given orderID
    for (ShipmentOrder o : wareflow.getOrders()) {
        if (orderID == o.getId()) {
            order = o;
            break; // Break the loop since if order is found 
        }
    }

    // return an error message if no order with the given ID is found
    if (order == null) {
        return "Order does not exist";
    }
    
    // Retrieve the User object based on the given username
    User user = User.getWithUsername(username);
    
    // Check if the user exists and if it is an instance of WarehouseStaff
    if (user == null || !(user instanceof WarehouseStaff)) {
        return "Staff does not exist";
    }
    
    // The following check seems redundant since it's identical to the one above
    if (user == null || !(user instanceof WarehouseStaff || user instanceof Manager)) {
        return "Staff does not exist";
    }

    // Validate the description to ensure it is not null or empty
    if (description == null || description.length() == 0) {
        return "Order description cannot be empty";
    }

    // Add the shipment note to the order with the given date, description, and user
    order.addShipmentNote(date, description, (WarehouseStaff) user);
    WareFlowPersistence.save();
    // Return an empty string to signify successful completion
    return "";
  }

  
  /**
   * @author Jeffery Nahas
   */
  // index starts at 0
  public static String updateShipmentNote(int orderID, int index, Date newDate,
      String newDescription, String newUsername) {
    
    // Attempt to retrieve the order by its ID
    ShipmentOrder order = ShipmentOrder.getWithId(orderID);
    
    // If the order is not found, return an error message
    if (order == null) {
      return "Order does not exist";
    }
    
    // Check if the note index is within the valid range of the shipment notes list for the order
    if (index < 0 || index >= order.numberOfShipmentNotes()) {
      return "Note does not exist";
    }

    // Retrieve the User object for the new username provided
    User user = User.getWithUsername(newUsername);
    
    // Check if the user exists and is a WarehouseStaff instance
    if (user == null || !(user instanceof WarehouseStaff)) {
      return "Staff does not exist";
    }

    // Validate that the new description is not null or empty after trimming white spaces
    if (newDescription == null || newDescription.trim().isEmpty()) {
      return "Order description cannot be empty";
    }

    // Retrieve the ShipmentNote object to be updated based on the index
    ShipmentNote note = order.getShipmentNote(index);
    
    // Update the date, description, and note taker of the shipment note
    note.setDate(newDate); 
    note.setDescription(newDescription); 
    note.setNoteTaker((WarehouseStaff) user); 
    note.setOrder(order); 
    WareFlowPersistence.save();
    // Return an empty string to indicate success. 
    return "";
  }

 
  /**
   * @author Paolo Lahoud and Jeffery Nahas
   */
  // index starts at 0
  public static void deleteShipmentNote(int orderID, int index) {
 // Retrieve the order by ID
    ShipmentOrder order = ShipmentOrder.getWithId(orderID);
    
    // Perform the deletion of the note if order is found and the index is valid
    if(order!=null&&index<order.getShipmentNotes().size()) {
    	ShipmentNote noteToRemove = order.getShipmentNote(index);
    	noteToRemove.delete();
    	WareFlowPersistence.save();
    }   
  }
}
