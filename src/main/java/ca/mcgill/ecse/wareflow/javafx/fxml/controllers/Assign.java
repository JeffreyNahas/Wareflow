package ca.mcgill.ecse.wareflow.javafx.fxml.controllers;


import ca.mcgill.ecse.wareflow.controller.ShipmentOrderStateController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Assign {
	@FXML
	private Button assignButton;
	
	@FXML
	private TextField idTxt;
	@FXML
	private TextField name;
	@FXML
	private TextField priority;
	@FXML
	private TextField approval;
	@FXML
	private TextField timeTxt;

	
	



	@FXML
	void assignButtonClicked(ActionEvent event) {
		String userName=name.getText();
		String idStr=idTxt.getText();
		String priorityStr=priority.getText();
		String approvalStr=approval.getText();
		String timeStr=timeTxt.getText();
		
		String error =ShipmentOrderStateController.assignOrder(idStr, userName, timeStr, priorityStr, approvalStr);
		if(!error.isEmpty()) {
			ViewUtils.showError(error);
			return;
		}
		name.setText("");
		idTxt.setText("");
		priority.setText("");
		approval.setText("");
		timeTxt.setText("");
	}
}