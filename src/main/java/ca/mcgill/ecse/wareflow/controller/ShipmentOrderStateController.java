package ca.mcgill.ecse.wareflow.controller;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse.wareflow.application.WareFlowApplication;
import ca.mcgill.ecse.wareflow.model.Client;
import ca.mcgill.ecse.wareflow.model.Employee;
import ca.mcgill.ecse.wareflow.model.ItemContainer;
import ca.mcgill.ecse.wareflow.model.ShipmentNote;
import ca.mcgill.ecse.wareflow.model.ShipmentOrder;
import ca.mcgill.ecse.wareflow.model.ShipmentOrder.PriorityLevel;
import ca.mcgill.ecse.wareflow.model.ShipmentOrder.TimeEstimate;
import ca.mcgill.ecse.wareflow.persistence.WareFlowPersistence;
import ca.mcgill.ecse.wareflow.model.User;

import ca.mcgill.ecse.wareflow.model.WareFlow;

public class ShipmentOrderStateController {
	private static WareFlow wareflow=WareFlowApplication.getWareFlow();
	private static String error;
	
	/**
	   * @author Joseph Rassi
	   */
	public static List<ShipmentOrder> getOrders(){
		return wareflow.getOrders();//return to view orders
	}
	
	/**
	   * @author Joseph Rassi
	   */
	public static String startOrder(String orderId) {
		error = "";
		ShipmentOrder order = null;//default order
		for (int i=0; i<wareflow.getOrders().size(); i++) {//iterate through all orders
			if (wareflow.getOrder(i).getId()==Integer.parseInt(orderId)) {//check id of every order
				order = wareflow.getOrder(i);//if order exists
				break;
			}
		}
		if (order==null) {//if order doesn't exist, error
			return "shipment order does not exist.";
		}

		order.start();//start order
	  WareFlowPersistence.save();//save


		return error;
	}
	
	/**
	   * @author Joseph Rassi
	   */
	public static String assignOrder(String orderId, String employeeUsername, String timeEstimate, String priority, String requiresApproval) {
		error = "";
		ShipmentOrder order = null;//default order
		for (int i=0; i<wareflow.getOrders().size(); i++) {//iterate through all orders
			if (wareflow.getOrder(i).getId()==Integer.parseInt(orderId)) {//check id
				order = wareflow.getOrder(i);//if found, order exists
				break;
			}
		}
		if (order==null) {//if order doesn't exist, error
			return "shipment order does not exist.";
		}
		Employee empl=null;//default employee
		for (Employee e : wareflow.getEmployees()) {//check every employee
			if (e.getUsername().equals(employeeUsername)) {//compare Usernames
				empl = e;//employee found
			}
		}
		if(empl==null )return "Staff to assign does not exist.";//if no employee, error
		PriorityLevel p =null;
		if(priority!=null&&!priority.isEmpty()) {
			System.out.println("priority not parsed");
			p=PriorityLevel.valueOf(priority);
		}
		
		order.setPriority(p);
		TimeEstimate t=null;
		if(timeEstimate!=null&&!timeEstimate.isEmpty()) {
			t = TimeEstimate.valueOf(timeEstimate);//string to TimeEstimate
		}
		order.assign(empl, t, p);//assign order
		if(requiresApproval!=null&&!requiresApproval.isEmpty()) {
			try {
					order.setApprovalRequired(Boolean.parseBoolean(requiresApproval));
			}
			catch (Exception e) {
				return "Approvale is a boolean";
			}
		 WareFlowPersistence.save();
		}
		return error;
	}

	/**
	   * @author Jeffery Nahas
	   */
	public static String completeOrder(String orderId) {
		if(orderId==null||orderId.length()==0) {//check if input correct
			return "Shipment order does not exist.";
			
		}
		int number=Integer.parseInt(orderId);
		  ShipmentOrder order=null;
		  for(ShipmentOrder currOrder:getOrders()) {//check order
			  if(currOrder.getId()==number) {//check for id
				  order=currOrder;//order exists
			  }
		  }
		  if (order == null) {//if order doesn't exist, error
	      return "Shipment order does not exist.";
	    }
	    // Check if status is 'InProgress'
	    String status = order.getStatusFullName();//get status, and then compare status
	    if ("Open".equals(status)) {
	      return "Cannot complete a shipment order which is open.";
	    }
	    if("Assigned".equals(status)) {
	    	return "Cannot complete a shipment order which is assigned.";
	    }
	    if("Completed".equals(status)) {
	    	return "The shipment order is already closed.";
	    }
	    if("Closed".equals(status)) {
	    	return "The shipment order is already completed.";
	    }
	    boolean requiresApproval = order.getApprovalRequired();//get approval
	    order.complete(requiresApproval);//complete order
      WareFlowPersistence.save();
	    
	    return "";
	}

	/**
	   * @author Jeffery Nahas
	   */
	public static String approveOrder(String orderIdStr) {
		 if(orderIdStr==null||orderIdStr.length()==0) {//check correct input
			return "Shipment order does not exist.";
				
		}
		//ShipmentOrder order = wareflow.getOrder(Integer.parseInt(orderIdStr));
		ShipmentOrder orderToApprove = null;
		int orderId=Integer.parseInt(orderIdStr);
		// Look for the specific order that matches the orderId
		for (ShipmentOrder order : wareflow.getOrders()) {//check each order
		     if (order.getId() == orderId) {//check id
		         orderToApprove = order;//order exists
		         break;
		     }
		 }
	
	  	  // If no order with the given ID is found, return an error message
	  	  if (orderToApprove == null) {
	  	      return "Shipment order does not exist.";
	  	  }
	  
	  	  // Get the status of the order to approve
	  	  String status = orderToApprove.getStatusFullName();
	  
	  	  // Check if the order is in the 'Completed' status before approving
	  	  if (!"Completed".equals(status)) {
	  	      return "Cannot approve a shipment order which is " + status + ".";
	  	  }
	  	  boolean wasApproved = orderToApprove.approve();
	  	  if (!wasApproved) {//if not approved, error
	  	    return "The shipment order could not be approved.";
	  	  }

  	  // If no order with the given ID is found, return an error message
  	
      WareFlowPersistence.save();
      return "";
}

	/**
	   * @author Jeffery Nahas
	   */
	public static String disapproveOrder(String orderIdStr, String dateStr, String reason) {
			//Check that the inputs are correct
		if (orderIdStr == null || orderIdStr.isEmpty()) {
        return "Order ID cannot be empty.";
      }

      if (dateStr == null || dateStr.isEmpty()) {
          return "Date cannot be empty.";
      }
  
      if (reason == null || reason.isEmpty()) {
          return "Reason cannot be empty.";
      }
  
      Date date = Date.valueOf(dateStr);
  
      int number=Integer.parseInt(orderIdStr);
	  ShipmentOrder orderToDisapprove=null;
	  for(ShipmentOrder currOrder:wareflow.getOrders()) {//check orders
		  if(currOrder.getId()==number) {//check id
			  orderToDisapprove=currOrder;//order exists
		  }
	  }
      if (orderToDisapprove == null) {//if order doesnt exist, error
          return "Shipment order does not exist.";
      }
  
      String status = orderToDisapprove.getStatusFullName();//get status
  
      if (!"Completed".equals(status)) {//check that order is not completed
          return "Cannot disapprove a shipment order which is " + status + ".";
      }
      if(orderToDisapprove.getOrderApprover()==null) {//set to manager if no order approver
    	  orderToDisapprove.setOrderApprover(wareflow.getManager());
      }
      //Create a new shipment note and then call for the correct functions to make it work
      ShipmentNote note=new ShipmentNote(date, reason, orderToDisapprove, wareflow.getManager());
      orderToDisapprove.addShipmentNote(note);
      boolean wasDisapproved = orderToDisapprove.reject(date, reason);
      if (!wasDisapproved) {
          return "The shipment order could not be disapproved.";
      }
      WareFlowPersistence.save();
      return "";
  	}
}