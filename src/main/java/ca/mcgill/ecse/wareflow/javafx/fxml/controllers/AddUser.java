package ca.mcgill.ecse.wareflow.javafx.fxml.controllers;


import ca.mcgill.ecse.wareflow.controller.UserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddUser {
	@FXML
	private Button addUserButton;
	@FXML
	private Button updateUserButton;
	@FXML
	private Button deleteUserButton;
	
	@FXML
	private TextField userName;
	@FXML
	private TextField name;
	@FXML
	private TextField password;
	@FXML
	private TextField phoneNumber;
	@FXML
	private TextField address;
	@FXML
	private TextField employeeBool;



	@FXML
	public void addUserButtonClicked(ActionEvent event) {
		String aname = name.getText();
		String aUserName=userName.getText();
		String pass=password.getText();
		String num=phoneNumber.getText();
		String aAddres=address.getText();
		String employee=employeeBool.getText();
		
		
		String error=UserController.addEmployeeOrClient(aUserName, pass, aname, num, employee.equals("true"), aAddres);
		if(error!=null&&!error.isEmpty()) {
			ViewUtils.showError(error);
			return;
		}
		if (error==null||error.isEmpty()); {
			name.setText("");
			userName.setText("");
			phoneNumber.setText("");
			password.setText("");
			address.setText("");
			employeeBool.setText("");
			return;
		}
	
		
	}
	
	@FXML 
	public void updateUserButtonClicked(ActionEvent event) {
		String aname = name.getText();
		String aUserName=userName.getText();
		String pass=password.getText();
		String num=phoneNumber.getText();
		String aAddres=address.getText();
		
		String error=UserController.updateEmployeeOrClient(aUserName, pass, aname, num,  aAddres);
		if (error==null||error.isEmpty()); {
			name.setText("");
			userName.setText("");
			phoneNumber.setText("");
			password.setText("");
			address.setText("");
			employeeBool.setText("");
		}
		if(error!=null&&!error.isEmpty()) {
			ViewUtils.showError(error);
		}
	}
	
	@FXML
	public void deleteUserButtonClicked(ActionEvent event) {
		String user=userName.getText();
		UserController.deleteEmployeeOrClient(user);
		userName.setText("");
	}
	
	
}