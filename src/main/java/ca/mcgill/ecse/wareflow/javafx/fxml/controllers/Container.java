package ca.mcgill.ecse.wareflow.javafx.fxml.controllers;



import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import ca.mcgill.ecse.wareflow.controller.ItemContainerController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Container {
	@FXML
	private Button addContainerButton;
	@FXML
	private Button updateContainerButton;
	@FXML
	private Button deleteContainerButton;
	
	@FXML
	private TextField number;
	@FXML
	private TextField areaNum;
	@FXML
	private TextField slotNum;
	@FXML
	private TextField date;
	@FXML
	private TextField type;


	@FXML
	void addContainerButtonClicked(ActionEvent event) {
		String idStr=number.getText();
		String areaSTD=areaNum.getText();
		String slotStr=slotNum.getText();
		String dateStr=date.getText();
		String typeSter=type.getText();
		int id;
		int area;
		int slot;
		
		try {
			id=Integer.parseInt(idStr);
			area=Integer.parseInt(areaSTD);
			slot=Integer.parseInt(slotStr);
		}
		catch(Exception e) {
			ViewUtils.showError("area, id, slot must be integers");
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
		String error=ItemContainerController.addItemContainer(id, area, slot, aDate, typeSter);
		if(!error.isEmpty()) {
			ViewUtils.showError(error);
			return;
		}
		number.setText("");
		areaNum.setText("");
		slotNum.setText("");
		date.setText("");
		type.setText("");
	}
	@FXML
	void updateContainerButtonClicked(ActionEvent event) {
		String idStr=number.getText();
		String areaSTD=areaNum.getText();
		String slotStr=slotNum.getText();
		String dateStr=date.getText();
		String typeSter=type.getText();
		int id;
		int area;
		int slot;
		try {
			id=Integer.parseInt(idStr);
			area=Integer.parseInt(areaSTD);
			slot=Integer.parseInt(slotStr);
		}
		catch(Exception e) {
			ViewUtils.showError("area, id, slot must be integers");
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
		String error=ItemContainerController.updateItemContainer(id, area, slot, aDate, typeSter);
		if(!error.isEmpty()) {
			ViewUtils.showError(error);
			return;
		}
		number.setText("");
		areaNum.setText("");
		slotNum.setText("");
		date.setText("");
		type.setText("");
	}
	@FXML
	void deleteContainerButtonClicked(ActionEvent event) {
		
		String idStr=number.getText();
		
		int id;
		try {
			id=Integer.parseInt(idStr);
		}
		catch(Exception e) {
			ViewUtils.showError("id must be an integer");
			return;
		}
		ItemContainerController.deleteItemContainer(id);
	}
}