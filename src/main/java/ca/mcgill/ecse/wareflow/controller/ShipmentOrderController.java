package ca.mcgill.ecse.wareflow.controller;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse.wareflow.application.WareFlowApplication;
import ca.mcgill.ecse.wareflow.model.Client;
import ca.mcgill.ecse.wareflow.model.Employee;
import ca.mcgill.ecse.wareflow.model.ItemContainer;
import ca.mcgill.ecse.wareflow.model.Manager;
import ca.mcgill.ecse.wareflow.model.ShipmentNote;
import ca.mcgill.ecse.wareflow.model.ShipmentOrder;
import ca.mcgill.ecse.wareflow.model.User;

import ca.mcgill.ecse.wareflow.model.WareFlow;
import ca.mcgill.ecse.wareflow.model.WarehouseStaff;
import ca.mcgill.ecse.wareflow.persistence.WareFlowPersistence;

public class ShipmentOrderController {
	private static WareFlow wareflow=WareFlowApplication.getWareFlow();
  // containerNumber -1 means that no container is specified and quantity has to be -1 as well
	
	/**
	 * @author Joseph Rassi
	 */
  public static String addShipmentOrder(int id, Date placedOnDate, String description,
      String username, int containerNumber, int quantity) {
    String error = "";
    int numOfOrders=wareflow.numberOfOrders();
    for (int i = 0; i<numOfOrders; i++) {//iterate through each order and check the id
    	ShipmentOrder order = wareflow.getOrder(i);
    	if (order.getId()==id) {//if order id exists, error
    		return "Order id already exists";
    	}
    }
    if (containerNumber==-1 && quantity!=0) {//if containerNumber is not specified and quantity is not zero, error
    	return "Order quantity must 0 when container is not specified";
    }
    List<ItemContainer> containers = wareflow.getItemContainers();
    boolean exists = false;
    for (ItemContainer i : containers) {//iterate to check each container's number
    	if (i.getContainerNumber()==containerNumber) {
    		exists = true;
    	}
    }
    if (!exists&&containerNumber!=-1) {//if container specified but doesn't exists, error
    	return "The container does not exist";
    }
    if (exists && quantity==0) {//if container exist but quantity is 0, error
    	return "Order quantity must be larger than 0 when container is specified";
    }
    User c = null;
    for (Client client : wareflow.getClients()) {//iterate to see if user is a client
    	if (username.equals(client.getUsername())) {
    		c=client;
    	}
    }
    for (Employee employee : wareflow.getEmployees()) {//iterate to see if user is an employee
    	if (username.equals(employee.getUsername())) {
    		c=employee;
    	}
    }
    if (username.equals("manager")){//iterate to see if the user is the manager
    	c=wareflow.getManager();
    }
    
    if (c==null) {//if user doesn't exist, error
    	return "The order placer does not exist";
    }
    if (description==null || description.isEmpty()) {//if no description, error
    	return "Order description cannot be empty";
    }
    
    ShipmentOrder o = new ShipmentOrder(id, placedOnDate, description, quantity, wareflow, c);
    
    if (containerNumber==-1) {//if containerNumber not specified, add to wareflow only
    	wareflow.addOrder(o);
    }
    else {//if container specified, add to wareflow and container
    	wareflow.addOrder(o); 
    	wareflow.getItemContainer(containerNumber-1).addShipmentOrder(o);
    }
    WareFlowPersistence.save();
   
    
    
    
    return error;
    
  }

  /**
	 * @author Joseph Rassi
	 */
  // newContainerNumber -1 means that no container is specified and newQuantity has to be -1 as well
  public static String updateShipmentOrder(int id, Date newPlacedOnDate, String newDescription,
      String newUsername, int newContainerNumber, int newQuantity) {
	  String error = "";
	    List<ItemContainer> containers = wareflow.getItemContainers();
	    if (newContainerNumber<0 && newQuantity!=0) {//if new container not specified and quantity not 0, error
	    	return "Order quantity must 0 when container is not specified";
	    }
	    boolean exists = false;
	    for (ItemContainer i : containers) {//iterate to check if container exists
	    	if (i.getContainerNumber()==newContainerNumber) {
	    		exists = true;
	    	}
	    }
	    if (!exists&&newContainerNumber!=-1) {//if container doesn't exist but specified, error
	    	return "The container does not exist";
	    }
	    else if (exists && newQuantity<=0) {//if container exists but new quantity not specified or 0, error
	    	return "Order quantity must be larger than 0 when container is specified";
	    }
	    if (newDescription==null || newDescription.isEmpty()) {//if no description, error
	    	return "Order description cannot be empty";
	    }
	    User c = null;
	    for (Client client : wareflow.getClients()) {//check if user is a client
	    	if (newUsername.equals(client.getUsername())) {
	    		c=client;
	    	}
	    }
	    for (Employee employee : wareflow.getEmployees()) {//check if user is an employee
	    	if (newUsername.equals(employee.getUsername())) {
	    		c=employee;
	    	}
	    }
	    if (newUsername.equals("manager")){//check if user is the manager
	    	c=wareflow.getManager();
	    }
	    
	    if (c==null) {//if user doesn't exist, error
	    	return "The order placer does not exist";
	    }
	    
	    if (newContainerNumber==-1) {//if container not specified, update order in wareflow
	    	ShipmentOrder o = wareflow.getOrder(id-1);
	    	o.setOrderPlacer(c);
		    o.setDescription(newDescription);
		    o.setPlacedOnDate(newPlacedOnDate);
		    o.setOrderPlacer(c);
		    o.setQuantity(newQuantity);
	    	o.getContainer().removeShipmentOrder(o);//remove order from container
	    }
	    else {//if container specified, update order everywhere
		    ShipmentOrder o = wareflow.getOrder(id-1);
		    o.setContainer(wareflow.getItemContainer(newContainerNumber-1));
		    o.setDescription(newDescription);
		    o.setPlacedOnDate(newPlacedOnDate);
		    o.setOrderPlacer(c);
		    o.setQuantity(newQuantity);
		    wareflow.getItemContainer(newContainerNumber-1).addShipmentOrder(o);
	    }
	    WareFlowPersistence.save();
	    
	    
	    return error;
  }
  
  
  /**
	 * @author Joseph Rassi
	 */

  public static void deleteShipmentOrder(int id) {
	  for (ShipmentOrder i : wareflow.getOrders()) {//iterate through each order to check id
		  if (i.getId()==id) {//if id is the same, remove the order
			  i.delete();
			  WareFlowPersistence.save();
			  break;
		  }
	  }
    
  }

  
  /**
	 * @author Joseph Rassi and Marc Germanos
	 */
  
  // returns all shipment orders
  public static List<TOShipmentOrder> getOrders() {
	  //list that holds To shipment order
	  List<TOShipmentOrder> l = new ArrayList<>();
	  //iterate through each shipment order in wareflow application
      for (ShipmentOrder o : wareflow.getOrders()) {
    	  //TOshipment Note array to hold the information of each ShipmentNote
    	  TOShipmentNote[] notes=new TOShipmentNote[o.getShipmentNotes().size()];
    	  //iterate through all the Shipment notes of each order
    	  for(int i=0;i<notes.length;i++) {
    		  //add the information needed for each TOShipmentNote
    		  ShipmentNote note=o.getShipmentNote(i);
    		  notes[i]=new TOShipmentNote(note.getDate(),note.getDescription(),note.getNoteTaker().getUsername());
    	  }
    	  //create default name value for null names in order placer
    	String name="manager";
    	//check if order placer name is null as default value
    	if(o.getOrderPlacer().getName()!=null) {
    		name=o.getOrderPlacer().getName();
    	}
    	name=name.toLowerCase();
    	//create instances of each Transfer object and instantiate
    	//add that transfer object to the instantiation of the TO order
    	Manager manager=wareflow.getManager();
    	TOManager toManager=new TOManager(manager.getUsername(),manager.getName(),manager.getPassword(),manager.getPhoneNumber());
    	User user=o.getOrderPlacer();
    	TOUser toUser=new TOUser(user.getUsername(),user.getName(),user.getPassword(),user.getPhoneNumber());
    	WarehouseStaff orderPicker=o.getOrderPicker();
    	TOWarehouseStaff toOrderPicker=null;
    	if(orderPicker!=null) {
    		toOrderPicker=new TOWarehouseStaff(orderPicker.getUsername(),orderPicker.getName(),orderPicker.getPassword(),orderPicker.getPhoneNumber());
    	}
    	TOItemContainer toContainer=null;
    	ItemContainer container=o.getContainer();
    	if(container!=null) {
    		toContainer=new TOItemContainer(container.getContainerNumber(),container.getAreaNumber(),container.getSlotNumber(),container.getAddedOnDate());
    	}
    	
    	//an order might not be associated to a container 
    	//split it into the two cases to assign default values in case not assigned to a container
    	if(o.getContainer()!=null) {
    	TOShipmentOrder t = new TOShipmentOrder(o.getId(), o.getQuantity(), o.getPlacedOnDate(), o.getDescription(), name, o.getContainer().getItemType().getName(), name, name, name, false, name, o.getContainer().getItemType().getExpectedLifeSpanInDays(), o.getContainer().getAddedOnDate(), o.getContainer().getAreaNumber(), o.getContainer().getSlotNumber(),toUser,toOrderPicker,toManager,toContainer,notes);
    	
    	l.add(t);
      }
    	else {
    		TOShipmentOrder t=new TOShipmentOrder(o.getId(), o.getQuantity(), o.getPlacedOnDate(), o.getDescription(), name, null, name, name, name, false, name, -1, null, -1, -1,toUser,toOrderPicker,toManager,toContainer,notes);
    	l.add(t);
    	}
     }


      return l;
  }

  
}

