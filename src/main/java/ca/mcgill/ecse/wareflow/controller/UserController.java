package ca.mcgill.ecse.wareflow.controller;

import ca.mcgill.ecse.wareflow.application.WareFlowApplication;
import ca.mcgill.ecse.wareflow.model.Client;
import ca.mcgill.ecse.wareflow.model.Employee;
import ca.mcgill.ecse.wareflow.model.Manager;
import ca.mcgill.ecse.wareflow.model.WareFlow;
import ca.mcgill.ecse.wareflow.persistence.WareFlowPersistence;

public class UserController {
  private static WareFlow wareflow=WareFlowApplication.getWareFlow();
	
  
  /**
   * @author Marc Germanos
   */
	public static String updateManager(String password) {
	   String error="";
	   //check for errors
	   
	   if(password==null||password.length()==0) {
		   return "Password cannot be empty";
	   }
	   if (password.length()<4) {
		   return "Password must be at least four characters long";
	   }
	   
	   //boolean to check that !#$ were used at least once
	   boolean special=false;
	   //check if upper case letter is in the password
	   boolean upper=false;
	   //check if lower case letter in the password
	   boolean lower=false;
	   
	   int i=0;
	   //iterate through the password while at least one of the requirements is not fulfilled
	   while (i<password.length()&& !(special && upper && lower)) {
		   char curr = password.charAt(i++);
		   //check if current character is ! or # or $
		   if (curr=='#'||curr=='!'||curr=='$') {
			   special=true;
		   }
		   //check if character is a capital letter
		   else if (curr>=65 && curr<=90) {
			   upper=true;
		   }
		   //check if character is a lower case letter
		   else if(curr>=97 && curr <=122) {
			   lower=true;
		   }
	   }
	   //check if not special character was found
	   if(!special) {
		   return "Password must contain one character out of !#$";
	   }
	   //check if no lower case letter was found
	   if (!lower) {
		   return "Password must contain one lower-case character";
	   }
	   //check if no upper case letter was found
	   if (!upper) {
		   return "Password must contain one upper-case character";
	   }
	   //get manager since only one manager for the application
	   Manager manager=wareflow.getManager();
	   if(manager==null) {
		   manager=new Manager("manager",null,null,null,wareflow);
	   }
	   manager.setPassword(password);
	   WareFlowPersistence.save();
	   return error; 
	   }


	/**
	 * @author Marc Germanos
	 */
  // address is ignored if the isEmployee is true
  public static String addEmployeeOrClient(String username, String password, String name,
      String phoneNumber, boolean isEmployee, String address) {
    String error="";
    //check for errors
    if(username==null||username.length()==0) {
    	return "Username cannot be empty";
    }
    if(password==null||password.length()==0) {
    	return "Password cannot be empty";
    }
    if(username.equals("manager")) {
    	return "Username cannot be manager";
    }
    
    //boolean to check if '@' character was used
    boolean atSign=false;
    //boolean to check if '.' character was used after the '@' character
    boolean dotSign=false;
    
    for(int i=0;i<username.length();i++) {
    	char curr=username.charAt(i);
    	//check if a space is in the username (illegal)
    	if(curr==' ') {
    		return "Invalid username";
    	}
    	
    	if(curr=='@') {
    		//check if '@' was already used or '@' is the last character in the username (illegal)
    		if (atSign||i==username.length()-1) {
    			return "Invalid username";
    		}
    		
    		//check if '.' char follows immediately the '@' character (illegal)
    		if(username.charAt(i+1)=='.') {
    			return "Invalid username";
    		}
    		//shows that '@' was used for next char checks
    		atSign=true;
    	}
    	
    	else if(curr =='.') {
    		//check if '.' was already used after '@' (illegal)
    		if(dotSign) {
    			return "Invalid username";
    		}
    		//check if '@' char is at an earlier index if not no issue possible
    		if (atSign) {
    			//check if the last char is the '.' which is after the '@' (illegal)
	    		if((i==username.length()-1)) {
	    			return "Invalid username";
	    		}
	    		//show that a '.' char was used after the '@' char for next char checks
	    		dotSign=true;
    		}
    	}
    }
    //check if '@' was used without a '.' following it (illegal)
    if(atSign && !dotSign){
    	return "Invalid username";
    }

    if(isEmployee) {
    	//check for each employee username if that username is already taken (illegal)
    	for (Employee e: wareflow.getEmployees()) {
    		if (e.getUsername().equals(username)) {
    			return "Username already linked to an employee account";
    		}
    	}
    	

    	wareflow.addEmployee(username, name, password, phoneNumber);
    }
    else {
    	//account is for client
    	//check for each client in the system if the username is already taken (illegal)
    	for (Client c:wareflow.getClients()) {
    		if (c.getUsername().equals(username)) {
    			return "Username already linked to a client account";
    		}
    	}
    	//no collision found
    	//add client with given arguments
    	wareflow.addClient(username, name, password, phoneNumber, address);
    }
    WareFlowPersistence.save();
    return error;
  }
  
  
  /**
   * @author Marc Germanos
   */
  // newAddress is ignored if the user is an employee
  public static String updateEmployeeOrClient(String username, String newPassword, String newName,
      String newPhoneNumber, String newAddress) {
    //String used for return statement
	String error="";

	if(newPassword==null||newPassword.length()==0) {
    	return "Password cannot be empty";
    }
    Client c=null;
    //check if client with given username exists in the system
    for(Client test:wareflow.getClients()) {
    	//check if client with username username is found
    	if(test.getUsername().equals(username)) {
    		c=test;
    		break;
    	}
    }
    //check if client with username username is not in the system
    //user might be an employee
    if(c==null) {
    	Employee e=null;
    	//check if employee with given username is exists in the system
    	for(Employee test:wareflow.getEmployees()) {
    		//check if employee with given username is found
    		if(test.getUsername().equals(username)) {
    			e=test;
    			break;
    		}
    	}
    	//check if no employee nor user with given username is found
    	if(e==null) {
    		return error;
    	}
    	//change the informations of the employee
    	e.setName(newName);
    	e.setPassword(newPassword);
    	e.setPhoneNumber(newPhoneNumber);
    }
    //client with given username was found
    else {
    	//change the informations of the employee
    	c.setAddress(newAddress);
    	c.setName(newName);
    	c.setPassword(newPassword);
    	c.setPhoneNumber(newPhoneNumber);
    	
    }
    WareFlowPersistence.save();
    return error;
  }
  
  
  /**
   * @author Marc Germanos
   */
//delete an employee or a client method
  public static void deleteEmployeeOrClient(String username) {
	Client c=null;
    //check if client with given username exists in the system
	for(Client test:wareflow.getClients()) {
    	//check if client with username username is found
    	if(test.getUsername().equals(username)) {
    		c=test;
    		break;
    	}
    }
	//check if client with username username is not in the system
    //user might be an employee
    if(c==null) {
    	Employee e=null;
    	//check if employee with given username is exists in the system
    	for(Employee test:wareflow.getEmployees()) {
    		//check if employee with given username is found
    		if(test.getUsername().equals(username)) {
    			e=test;
    			break;
    		}
    	}
    	//check if no employee nor user with given username is found
    	if(e==null) {
    		return;
    	}
    	//employee was found with given username and no client was found
    	//delete employee from system
    	e.delete();
    	//e.delete() removes first the employee from the wareflow system
    	//then deletes the instance of employee
    	return;
    }
    //client with given username was found in the system 
    //delete employee from system
    c.delete();
  //c.delete() removes first the client from the wareflow system
	//then deletes the instance of client
    WareFlowPersistence.save();
    return;
  }
}
