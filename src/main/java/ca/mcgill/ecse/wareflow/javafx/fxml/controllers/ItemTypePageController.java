package ca.mcgill.ecse.wareflow.javafx.fxml.controllers;


import ca.mcgill.ecse.wareflow.controller.ItemTypeController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class ItemTypePageController{
	@FXML
	private Button addButton;
	@FXML
	private Button updateButton;
	@FXML
	private Button deleteButton;
	
	@FXML
	private TextField name;
	@FXML
	private TextField newName;
	@FXML
	private TextField expected;
	
	@FXML
	void addButtonClicked(ActionEvent event) {
		String itemName=name.getText();
		int life;
		try {
			life=Integer.parseInt(expected.getText());
		}
		catch(Exception e) {
			ViewUtils.showError("Life span is an integer");
			return;
		}
		String error=ItemTypeController.addItemType(itemName, life);
		if(!error.isEmpty()) {
			ViewUtils.showError(error);
			return;
		}
		name.setText("");
		expected.setText("");
	}
	
	@FXML
	void updateButtonClicked(ActionEvent event) {
		String itemName=name.getText();
		String aNewname=newName.getText();
		String lifeStr=expected.getText();
		int life;
		if(!lifeStr.isEmpty()){
			try {
				life=Integer.parseInt(expected.getText());
			}
			catch(Exception e) {
				ViewUtils.showError("Life span is an integer");
				return;
			}
		}
		else {
			life=0;
		}
		String error=ItemTypeController.updateItemType(itemName, aNewname, life);
		if(!error.isEmpty()) {
			ViewUtils.showError(error);
			return;
		}
		name.setText("");
		newName.setText("");
		expected.setText("");
	}
	@FXML
	void deleteButtonClicked(ActionEvent event) {
		String itemName=name.getText();
		ItemTypeController.deleteItemType(itemName);
		name.setText("");
	}
}