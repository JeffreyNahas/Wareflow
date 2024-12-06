package ca.mcgill.ecse.wareflow.features;

import java.util.List;

import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.sql.Date;
import ca.mcgill.ecse.wareflow.application.WareFlowApplication;
import ca.mcgill.ecse.wareflow.model.Employee;
import ca.mcgill.ecse.wareflow.model.Manager;
import ca.mcgill.ecse.wareflow.model.ItemType;
import ca.mcgill.ecse.wareflow.model.ItemContainer;
import ca.mcgill.ecse.wareflow.model.ShipmentOrder;
import ca.mcgill.ecse.wareflow.model.ShipmentOrder.PriorityLevel;
import ca.mcgill.ecse.wareflow.model.ShipmentOrder.TimeEstimate;
import ca.mcgill.ecse.wareflow.model.User;
import ca.mcgill.ecse.wareflow.model.ShipmentNote;
import ca.mcgill.ecse.wareflow.model.WarehouseStaff;
import ca.mcgill.ecse.wareflow.model.WareFlow;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import ca.mcgill.ecse.wareflow.controller.ShipmentOrderController;
import ca.mcgill.ecse.wareflow.controller.ShipmentOrderStateController;
import ca.mcgill.ecse.wareflow.controller.TOShipmentNote;
import ca.mcgill.ecse.wareflow.controller.TOShipmentOrder;



public class ShipmentOrderStepDefinitions {

	String error;
	private WareFlow wareFlow = WareFlowApplication.getWareFlow();
  List<TOShipmentOrder> orders;
  
  /**
   * @author Soraya Jureidini
   */
  @Given("the following employees exist in the system")
  public void the_following_employees_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {
	  List<Map<String, String>> rows = dataTable.asMaps();
	  
	  for (var row : rows) {
		  String username = row.get("username");
	      String name = row.get("name");
	      String password = row.get("password");
	      String phoneNumber = row.get("phoneNumber");
	      new Employee (username, name, password, phoneNumber, wareFlow);
	    }
   
  }

  /**
   * @author Soraya Jureidini
   */
  @Given("the following manager exists in the system")
  public void the_following_manager_exists_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {
	  List<Map<String, String>> rows = dataTable.asMaps();
		
		for (var row : rows) {
			String username = row.get("username");
	    	String password = row.get("password");
	    	String name = null;
	        String phoneNumber = null;
	        // no name no phone number in feature
	    	new Manager(username, password ,name, phoneNumber, wareFlow);
	    }
  }

  /**
   * @author Soraya Jureidini
   */
  @Given("the following items exist in the system")
  public void the_following_items_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
	  List<Map<String, String>> rows = dataTable.asMaps();
		
		for (var row : rows) {
			String name = row.get("name");
			int expectedLifeSpanInDays = Integer.parseInt(row.get("expectedLifeSpanInDays"));
	     	new ItemType(name, expectedLifeSpanInDays, wareFlow);
		}
  }

  /**
   * @author Soraya Jureidini
   */
  @Given("order {string} is marked as {string} with requires approval {string}")
  public void order_is_marked_as_with_requires_approval(String string, String string2,
      String string3) {
	  int number=Integer.parseInt(string);
	  ShipmentOrder order=null;
	  for(ShipmentOrder currOrder:wareFlow.getOrders()) {
		  if(currOrder.getId()==number) {
			  order=currOrder;
		  }
	  }
	  if(order==null) {
		  return;
	  }
	  order.setApprovalRequired(string3.equals("true"));
	  if(order.getApprovalRequired() ) {
		  order.setOrderApprover(wareFlow.getManager());
	  }
	  if(string2.equals("Assigned")){
		  order.assign(order.getOrderPicker(), order.getTimeToFullfill(), order.getPriority());
	  }
	  else if (string2.equals("InProgress")) {
		  order.assign(order.getOrderPicker(), order.getTimeToFullfill(), order.getPriority());
		  order.start();
	  }
	  else if(string2.equals("Completed")) {
		  order.assign(order.getOrderPicker(), order.getTimeToFullfill(), order.getPriority());
		  order.start();
		  order.complete(order.getApprovalRequired());
	  }
	  else if(string2.equals("Closed")) {
		  order.assign(order.getOrderPicker(), order.getTimeToFullfill(), order.getPriority());
		  order.start();
		  order.complete(false);
	  }
  }

  /**
   * @author Soraya Jureidini
   */
  @Given("the following containers exist in the system")
  public void the_following_containers_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {
	  List<Map<String, String>> rows = dataTable.asMaps();

		for (var row : rows) {
			int containerNumber = Integer.parseInt(row.get("containerNumber"));
			ItemType type = ItemType.getWithName(row.get("type"));
			Date purchaseDate = Date.valueOf(row.get("purchaseDate"));
			int areaNumber = Integer.parseInt(row.get("areaNumber"));
			int slotNumber = Integer.parseInt(row.get("slotNumber"));
	     	new ItemContainer(containerNumber, areaNumber, slotNumber, purchaseDate, wareFlow, type);
		}
  }

  /**
   * @author Soraya Jureidini
   */
  @Given("the following orders exist in the system")
  public void the_following_orders_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
	  List<Map<String, String>> rows = dataTable.asMaps();
	  
	  boolean process=false;
	  
	  if(rows.get(0).size()==7) {
		  process=true;
	  }
		  
		for (var row : rows) {
			
			int id = Integer.parseInt(row.get("id"));
			User orderPlacer = User.getWithUsername(row.get("orderPlacer"));
			Date placedOnDate = Date.valueOf(row.get("placedOnDate"));
			String description = row.get("description");
			int quantity = Integer.parseInt(row.get("quantity"));
			// process shipment (+ status) / enhanced: +status, processedBy, timeToResolve, priority, approvalRequired
	     	ShipmentOrder currOrder=new ShipmentOrder(id, placedOnDate, description, quantity,wareFlow, orderPlacer);
	     	int number=Integer.parseInt(row.get("containerNumber"));
	     	List <ItemContainer> containers=wareFlow.getItemContainers();
	     	for(ItemContainer container:containers) {
	     		if (container.getContainerNumber()==number) {
	     			currOrder.setContainer(container);
	     			break;
	     		}
	     	}
	     	if(!process) {
				WarehouseStaff processedBy = null;
				if(row.get("processedBy")!=null && row.get("processedBy").length()>0) {
					processedBy=(WarehouseStaff)User.getWithUsername(row.get("processedBy"));
				}
				TimeEstimate timeToResolve = null;
				if(row.get("timeToresolve")!=null && row.get("timeToResolve").length()>0) {
					timeToResolve=TimeEstimate.valueOf(row.get("timeToResolve")) ;
				}
				PriorityLevel priority = null;
				if(row.get("priority")!=null && row.get("priority").length()>0) {
					priority=PriorityLevel.valueOf(row.get("priority"));
				}
				boolean  approvalRequired = false;
				if(row.get("approvalRequired")!=null && row.get("approvalRequired").length()>0) {
					approvalRequired=row.get("approvalRequired").equals("true");
				}
				currOrder.setApprovalRequired(approvalRequired);
				currOrder.setTimeToFullfill(timeToResolve);
				currOrder.setPriority(priority);
				currOrder.setOrderPicker(processedBy);
			}
			
		}
  }

  /**
   * @author Soraya Jureidini & Marc Germanos
   */
  @Given("the following notes exist in the system")
  public void the_following_notes_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
	  List<Map<String, String>> rows = dataTable.asMaps();

		for (var row : rows) {
			WarehouseStaff noteTaker = (WarehouseStaff) WarehouseStaff.getWithUsername(row.get("noteTaker"));
			ShipmentOrder order = ShipmentOrder.getWithId(Integer.parseInt(row.get("orderId")));
			Date addedOnDate = Date.valueOf(row.get("addedOnDate"));
			String description = row.get("description");
	     	new ShipmentNote(addedOnDate,description,order,noteTaker);
		}
  }

  /**
   * @author Soraya Jureidini & Marc Germanos
   */
  @Given("order {string} is marked as {string}")
  public void order_is_marked_as(String string, String string2) {
	  int number=Integer.parseInt(string);
	  ShipmentOrder order=null;
	  for(ShipmentOrder currOrder:wareFlow.getOrders()) {
		  if(currOrder.getId()==number) {
			  order=currOrder;
		  }
	  }
	  if(order==null) {
		  return;
	  }
	  if(string2.equals("Assigned")){
		  order.assign(order.getOrderPicker(), order.getTimeToFullfill(), order.getPriority());
	  }
	  else if (string2.equals("InProgress")) {
		  order.assign(order.getOrderPicker(), order.getTimeToFullfill(), order.getPriority());
		  order.start();
	  }
	  else if(string2.equals("Completed")) {
		  order.assign(order.getOrderPicker(), order.getTimeToFullfill(), order.getPriority());
		  order.start();
		  order.complete(true);
	  }
	  else if(string2.equals("Closed")) {
		  order.assign(order.getOrderPicker(), order.getTimeToFullfill(), order.getPriority());
		  order.start();
		  order.complete(false);
	  }
  }
  
  /**
	 * @author Joseph Rassi
	 */
  @When("the manager attempts to view all shipment orders in the system")
  public void the_manager_attempts_to_view_all_shipment_orders_in_the_system() {
	  orders = ShipmentOrderController.getOrders();
  }
  
  /**
	 * @author Joseph Rassi
	 */
  @When("the warehouse staff attempts to start the order {string}")
  public void the_warehouse_staff_attempts_to_start_the_order(String orderId) {
	  error = ShipmentOrderStateController.startOrder(orderId);
  }
  
  /**
	 * @author Joseph Rassi
	 */
  @When("the manager attempts to assign the order {string} to {string} with estimated time {string}, priority {string}, and requires approval {string}")
  public void the_manager_attempts_to_assign_the_order_to_with_estimated_time_priority_and_requires_approval(
      String orderId, String employeeUsername, String timeEstimate, String priority, String requiresApproval) {
	  error = ShipmentOrderStateController.assignOrder(orderId, employeeUsername, timeEstimate, priority, requiresApproval);
  }
  
  /**
	 * @author Joseph Rassi
	 */
  @When("the warehouse staff attempts to complete the order {string}")
  public void the_warehouse_staff_attempts_to_complete_the_order(String orderId) {
	  error = ShipmentOrderStateController.completeOrder(orderId);
  }
  
  /**
	 * @author Joseph Rassi
	 */
  @When("the manager attempts to disapprove the order {string} on date {string} and with reason {string}")
  public void the_manager_attempts_to_disapprove_the_order_on_date_and_with_reason(String orderId,
      String date, String reason) {
     
	  error = ShipmentOrderStateController.disapproveOrder(orderId, date, reason);
  }
  
  /**
	 * @author Joseph Rassi
	 */
  @When("the manager attempts to approve the order {string}")
  public void the_manager_attempts_to_approve_the_order(String orderId) {
	  error = ShipmentOrderStateController.approveOrder(orderId);
  }

  /**
   * @author Rami EL-NAWAM
   */
  @Then("the following shipment orders shall be presented")
  public void the_following_shipment_orders_shall_be_presented(
      io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> rows = dataTable.asMaps();
    int i = 0;
    for (var row : rows) {
      TOShipmentOrder currOrder = orders.get(i);
      int id = Integer.parseInt(row.get("id"));
      assertEquals(id, currOrder.getId());
      String orderPlacerUsername = row.get("orderPlacer");
      assertEquals(orderPlacerUsername, currOrder.getOrderPlacer());
      String description = row.get("description");
      assertEquals(description, currOrder.getDescription());
      String expectLifeSpanStr = row.get("expectLifeSpan");
      int expectLifeSpan = -1;
      if (expectLifeSpanStr != null) {
        expectLifeSpan = Integer.parseInt(expectLifeSpanStr);
      }
      assertEquals(expectLifeSpan, currOrder.getExpectedLifeSpanInDays());
      String placedOnDateStr = row.get("purchaseDate");
      Date placedOnDate = null;
      if (placedOnDateStr != null) {
       placedOnDate = Date.valueOf(placedOnDateStr);
      }
      assertEquals(placedOnDate, currOrder.getAddedOnDate());
      String areaNumberStr = row.get("areaNumber");
      int areaNumber = -1;
      if (areaNumberStr != null) {
        areaNumber = Integer.parseInt(areaNumberStr);
      }
      assertEquals(areaNumber, currOrder.getAreaNumber());
      String slotNumberStr = row.get("slotNumber");
      int slotNumber = -1;
      if (slotNumberStr != null) {
        slotNumber = Integer.parseInt(slotNumberStr);
      }
      assertEquals(slotNumber, currOrder.getSlotNumber());
      i++;
    }
  }

  /**
   * @author Rami EL-NAWAM
   */
  @Then("the order with id {string} shall have the following notes")
  public void the_order_with_id_shall_have_the_following_notes(String string,
      io.cucumber.datatable.DataTable dataTable) {
    int orderID = Integer.parseInt(string);
    TOShipmentOrder currOrder = null;
    if(orders==null||orders.size()==0) {
    	return;
    }
    for (var order : orders) {
      if (order.getId() == orderID) {
        currOrder = order;
      }
    }

    assertNotNull(currOrder);

    List<TOShipmentNote> currShipmentNotes = currOrder.getNotes();
    List<Map<String, String>> rows = dataTable.asMaps();
    int i = 0;
    for (var row : rows) {
      TOShipmentNote currNote = currShipmentNotes.get(i);
      String noteTaker = row.get("noteTaker");
      String expectedTaker=currNote.getNoteTakerUsername();
      assertEquals(noteTaker, expectedTaker);
      Date date = Date.valueOf(row.get("addedOnDate"));
      assertEquals(date, currNote.getDate());
      String description = row.get("description");
      assertEquals(description, currNote.getDescription());
      i++;
    }
  }

  /**
   * @author Rami EL-NAWAM
   */
  @Then("the order with id {string} shall have no notes")
  public void the_order_with_id_shall_have_no_notes(String string) {
    int orderID = Integer.parseInt(string);
    TOShipmentOrder currOrder = null;
    for (var order : orders) {
      if (order.getId() == orderID) {
        currOrder = order;
      }
    }

    assertNotNull(currOrder);
    assertEquals(currOrder.hasNotes(), false);
  
  }

  /**
   * @author Rami EL-NAWAM
   */
  @Then("the order {string} shall not exist in the system")
  public void the_order_shall_not_exist_in_the_system(String string) {
    int orderId = Integer.parseInt(string);
    
    // Assuming there is a method to check if an order exists, which returns null or the order.
    ShipmentOrder order = ShipmentOrder.getWithId(orderId);
    
    // Assert that the order does not exist. If 'order' is null, it means the order doesn't exist.
    assertNull(order, "Order with ID " + orderId + " should not exist in the system.");
    
  }

  /**
   * @author Paolo Lahoud
   */
  @Then("the number of orders in the system shall be {string}")
  public void the_number_of_orders_in_the_system_shall_be(String string) {

    int numOrders = Integer.parseInt(string);
    int actualNumOrders = wareFlow.getOrders().size();
    assertEquals("The actual number of orders in the system does not match the expected number.", numOrders, actualNumOrders);

  }

  /**
   * @author Paolo Lahoud
   */
  @Then("the system shall raise the error {string}")
  public void the_system_shall_raise_the_error(String string) {
    assertNotNull(string);
  }

  /**
   * @author Paolo Lahoud
   */
  @Then("the order {string} shall be marked as {string}")
  public void the_order_shall_be_marked_as(String string, String string2) {

    int number=Integer.parseInt(string);
	  ShipmentOrder order=null;
	  for(ShipmentOrder currOrder:wareFlow.getOrders()) {
		  if(currOrder.getId()==number) {
			  order=currOrder;
		  }
	  }
	  if(order==null) {
		  return;
	  }
    String status = order.getStatusFullName();
    assertEquals(string2, status);
  }

  /**
   * @author Paolo Lahoud
   */
  @Then("the order {string} shall be assigned to {string}")
  public void the_order_shall_be_assigned_to(String string, String string2) {

	  int number=Integer.parseInt(string);
	  ShipmentOrder order=null;
	  for(ShipmentOrder currOrder:wareFlow.getOrders()) {
		  if(currOrder.getId()==number) {
			  order=currOrder;
		  }
	  }
	  if(order==null) {
		  return;
	  }
    WarehouseStaff assignedTo = order.getOrderPicker();
    String name = assignedTo.getName();
    assertTrue(string2.equalsIgnoreCase(name));

  }

  /**
   * @author Paolo Lahoud
   */
  @Then("the order {string} shall have estimated time {string}, priority {string}, and requires approval {string}")
  public void the_order_shall_have_estimated_time_priority_and_requires_approval(String string,
      String string2, String string3, String string4) {

	  int number=Integer.parseInt(string);
	  ShipmentOrder order=null;
	  for(ShipmentOrder currOrder:wareFlow.getOrders()) {
		  if(currOrder.getId()==number) {
			  order=currOrder;
		  }
	  }
	  if(order==null) {
		  return;
	  }
        String actualEstimatedTime = (order.getTimeToFullfill()).toString();
        String actualPriority = (order.getPriority()).toString();

        boolean expectedRequiresApproval = Boolean.parseBoolean(string4);
        boolean actualRequiresApproval = order.getApprovalRequired();

        assertEquals(string2, actualEstimatedTime);
        assertEquals(string3, actualPriority);
        assertEquals(expectedRequiresApproval, actualRequiresApproval);
  }
}
