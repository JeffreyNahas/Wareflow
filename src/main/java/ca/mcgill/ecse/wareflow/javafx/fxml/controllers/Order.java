package ca.mcgill.ecse.wareflow.javafx.fxml.controllers;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

import ca.mcgill.ecse.wareflow.controller.ShipmentOrderController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Order {
	@FXML
	private Button addOrderButton;
	@FXML
	private Button updateOrderrButton;
	@FXML
	private Button deleteOrderButton;
	
	@FXML
	private TextField id;
	@FXML
	private TextField description;
	@FXML
	private TextField num;
	@FXML
	private TextField datePlaced;
	@FXML
	private TextField username;
	@FXML
	private TextField quantity;



	@FXML
	void addOrderButtonClicked(ActionEvent event) {
		String idStr=id.getText();
		String desc=description.getText();
		String userName=username.getText();
		String quantityStr=quantity.getText();
		String dateStr=datePlaced.getText();
		String numberStr=num.getText();
		int idNum;
		int quantityNum;
		int number;
		try {
			number=Integer.parseInt(numberStr);
			idNum=Integer.parseInt(idStr);
			quantityNum=Integer.parseInt(quantityStr);
		}
		catch(Exception e) {
			ViewUtils.showError("Id, Container number and Quantity must be integers");
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
		String error=ShipmentOrderController.addShipmentOrder(idNum, aDate, desc, userName, number, quantityNum);
		if(!error.isEmpty()) {
			ViewUtils.showError(error);
			return;
		}
		id.setText("");
		description.setText("");
		username.setText("");
		quantity.setText("");
		datePlaced.setText("");
		num.setText("");
	}
	
	@FXML
	void updateOrderButtonClicked(ActionEvent event) {
		String idStr=id.getText();
		String desc=description.getText();
		String userName=username.getText();
		String quantityStr=quantity.getText();
		String dateStr=datePlaced.getText();
		String numberStr=num.getText();
		int idNum;
		int quantityNum;
		int number;
		try {
			number=Integer.parseInt(numberStr);
			idNum=Integer.parseInt(idStr);
			quantityNum=Integer.parseInt(quantityStr);
		}
		catch(Exception e) {
			ViewUtils.showError("Id, Container number and Quantity must be integers");
			return;
		}
		Date aDate=null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		if(!dateStr.isEmpty()) {
			try {
				aDate=(Date) dateFormat.parse(dateStr);
			} catch (ParseException e) {
				ViewUtils.showError("Bad format of date it is: yyyy-MM-dd");
				return;
			}
		}
		String error=ShipmentOrderController.updateShipmentOrder(idNum, aDate, desc, userName, number, quantityNum);
		if(!error.isEmpty()) {
			ViewUtils.showError(error);
		}
		id.setText("");
		description.setText("");
		username.setText("");
		quantity.setText("");
		datePlaced.setText("");
		num.setText("");
		}
	
	@FXML 
	void deleteOrderButtonCLicked(ActionEvent event) {
		String idStr=id.getText();
		int idNum;
		try {
			idNum=Integer.parseInt(idStr);
		}
		catch(Exception e) {
			ViewUtils.showError("Id must be an integer");
			return;
		}
		ShipmentOrderController.deleteShipmentOrder(idNum);
		id.setText("");
	}
}