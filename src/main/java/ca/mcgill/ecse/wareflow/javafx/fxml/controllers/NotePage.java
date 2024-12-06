package ca.mcgill.ecse.wareflow.javafx.fxml.controllers;


import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import ca.mcgill.ecse.wareflow.controller.ShipmentNoteController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class NotePage {
	@FXML
	private Button addNoteButton;
	@FXML
	private Button updateNoteButton;
	@FXML
	private Button deleteNoteButton;
	
	@FXML
	private TextField idText;
	@FXML
	private TextField userNameText;
	@FXML
	private TextField descriptionText;
	@FXML
	private TextField dateText;
	@FXML
	private TextField indexText;
	
	@FXML
	public void addNoteClicked(ActionEvent event) {
		String idStr=idText.getText();
		String userName=userNameText.getText();
		String description=descriptionText.getText();
		String dateStr=dateText.getText();
		
		int id;
		try {
			id=Integer.parseInt(idStr);
		}
		catch (Exception e) {
			ViewUtils.showError("Id must be an integer");
			return;
		}
		Date aDate = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		if (!dateStr.isEmpty()) {
		    try {
		        aDate = new Date(dateFormat.parse(dateStr).getTime());
		    } catch (ParseException e) {
		        ViewUtils.showError("Bad format of date; it should be in the format yyyy-MM-dd");
		        return;
		    }
		}
		String error=ShipmentNoteController.addShipmentNote(aDate, description, id, userName);
		if (error==null||error.isEmpty()) {
			ViewUtils.showError(error);
			return;
		}
		idText.setText("");
		userNameText.setText("");
		descriptionText.setText("");
		dateText.setText("");
	}
	
	@FXML
	public void updateNoteClicked(ActionEvent event) {
		String idStr=idText.getText();
		String userName=userNameText.getText();
		String description=descriptionText.getText();
		String dateStr=dateText.getText();
		String indexStr=indexText.getText();
		int id;
		int index;
		try {
			id=Integer.parseInt(idStr);
			index=Integer.parseInt(indexStr);
		}
		catch (Exception e) {
			ViewUtils.showError("Id and index must be an integes");
			return;
		}
		Date aDate = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		if (!dateStr.isEmpty()) {
		    try {
		        aDate = new Date(dateFormat.parse(dateStr).getTime());
		    } catch (ParseException e) {
		        ViewUtils.showError("Bad format of date; it should be in the format yyyy-MM-dd");
		        return;
		    }
		}
		String error=ShipmentNoteController.updateShipmentNote(id, index, aDate, description, userName);
		if(error==null||error.isEmpty()) {
			ViewUtils.showError(error);
			return;
		}
		idText.setText("");
		userNameText.setText("");
		descriptionText.setText("");
		dateText.setText("");
		indexText.setText("");
	}
	
	@FXML
	public void deleteNoteClicked(ActionEvent event) {
		String idStr=idText.getText();
		String indexStr=indexText.getText();
		int id;
		int index;
		try {
			id=Integer.parseInt(idStr);
			index=Integer.parseInt(indexStr);
			
		}
		catch (Exception e) {
			ViewUtils.showError("Id and index must be an integer");
			return;
		}
		ShipmentNoteController.deleteShipmentNote(id, index);
	}
}
	