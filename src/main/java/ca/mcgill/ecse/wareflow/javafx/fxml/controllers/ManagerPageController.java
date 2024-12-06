package ca.mcgill.ecse.wareflow.javafx.fxml.controllers;


import ca.mcgill.ecse.wareflow.controller.UserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
public class ManagerPageController{
	@FXML
	private Button updateButton;
	@FXML
	private TextField password;
	
	@FXML 
	void updateButtonClicked(ActionEvent event) {
		String pass=password.getText();
		String error=UserController.updateManager(pass);
		if(!error.isEmpty()) {
			ViewUtils.showError(error);
			return;
		}
		password.setText("");
	}
}