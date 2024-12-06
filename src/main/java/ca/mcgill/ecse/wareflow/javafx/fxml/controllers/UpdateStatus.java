package ca.mcgill.ecse.wareflow.javafx.fxml.controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import ca.mcgill.ecse.wareflow.controller.ShipmentOrderStateController;
import java.sql.Date;

public class UpdateStatus{
  @FXML
  private Button startButton;
  @FXML
  private Button completeButton;
  @FXML
  private Button approveButton;
  @FXML
  private Button disapproveButton;

  @FXML
  private TextField date;
  @FXML
  private TextField id;
  @FXML
  private TextField reason;

  @FXML
  void startButtonClicked(ActionEvent event){
    String idStr= id.getText();
    String error = ShipmentOrderStateController.startOrder(idStr);
    if(!error.isEmpty()) {
      ViewUtils.showError(error);
  }
    id.setText("");
  }
@FXML
  void completeButtonClicked(ActionEvent event) {
    String idStr= id.getText();
    String error = ShipmentOrderStateController.completeOrder(idStr);
    if(!error.isEmpty()) {
      ViewUtils.showError(error);
    }
    id.setText("");
  }
@FXML
  void approveButtonClicked(ActionEvent event) {
    String idStr= id.getText();
    String error = ShipmentOrderStateController.approveOrder(idStr);
    if(!error.isEmpty()) {
      ViewUtils.showError(error);
    }
    id.setText("");
  }
@FXML
  void disapproveButtonClicked(ActionEvent event) {
    String dateStr= date.getText();
    String idStr= id.getText();
    String reasonStr= reason.getText();

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    if(!dateStr.isEmpty()) {
      try {
         dateFormat.parse(dateStr);
      } catch (ParseException e) {
          ViewUtils.showError("Bad format of date it is: yyyy-MM-dd");
          return;
      }
  }
    String error = ShipmentOrderStateController.disapproveOrder(idStr, dateStr, reasonStr);
    if(!error.isEmpty()) {
      ViewUtils.showError(error);
    }
    id.setText("");
    date.setText("");
    reason.setText("");
  }


}