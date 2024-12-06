package ca.mcgill.ecse.wareflow.javafx.fxml.controllers;

import ca.mcgill.ecse.wareflow.application.WareFlowApplication;
import ca.mcgill.ecse.wareflow.controller.*;
import ca.mcgill.ecse.wareflow.model.WareFlow;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Date;
import java.util.List;

public class Status {

    @FXML
    private TableColumn<TOShipmentOrder, Boolean> approvalRequiredColumn;

    @FXML
    private TableColumn<TOShipmentOrder, Integer> areaNumberColumn;

    @FXML
    private TableColumn<TOShipmentOrder, String> assetNameColumn;

    @FXML
    private TableColumn<TOShipmentOrder, String> descriptionColumn;

    @FXML
    private TableColumn<TOShipmentOrder, String> estFulfillmentColumn;

    @FXML
    private TableColumn<TOShipmentOrder, Integer> expectedLifeSpanColumn;

    @FXML
    private TableColumn<TOShipmentOrder, Integer> idColumn;

    @FXML
    private TableColumn<TOShipmentOrder, String> orderPlacerColumn;

    @FXML
    private TableColumn<TOShipmentOrder, Date> placedOnColumn;

    @FXML
    private Button refresh;
    private Button previousPageButton;

    @FXML
    private TableColumn<TOShipmentOrder, String> priorityColumn;

    @FXML
    private TableColumn<TOShipmentOrder, String> processedByColumn;

    @FXML
    private TableColumn<TOShipmentOrder, Date> purchaseDateColumn;

    @FXML
    private TableColumn<TOShipmentOrder, Integer> quantityColumn;

    @FXML
    private TableView<TOShipmentOrder> shipmentOrderTable;

    @FXML
    private TableColumn<TOShipmentOrder, Integer> slotNumberColumn;

    @FXML
    private TableColumn<TOShipmentOrder, String> statusColumn;

    private WareFlow wareFlow = WareFlowApplication.getWareFlow();
    @FXML
    public void initialize() {
        setupColumn(idColumn, "id");
        setupColumn(orderPlacerColumn, "orderPlacer");
        setupColumn(placedOnColumn, "placedOnDate");
        setupColumn(descriptionColumn, "description");
        setupColumn(assetNameColumn, "itemName");
        setupColumn(expectedLifeSpanColumn, "expectedLifeSpanInDays");
        setupColumn(purchaseDateColumn, "addedOnDate");
        setupColumn(areaNumberColumn, "areaNumber");
        setupColumn(slotNumberColumn, "slotNumber");
        setupColumn(quantityColumn, "quantity");
        setupColumn(statusColumn, "status");
        setupColumn(processedByColumn, "processedBy");
        setupColumn(estFulfillmentColumn, "timeToResolve");
        setupColumn(priorityColumn, "priority");
        setupColumn(approvalRequiredColumn, "approvalRequired");
        loadShipmentOrders();
    }

    private void loadShipmentOrders() {
        List<TOShipmentOrder> orders = ShipmentOrderController.getOrders();
        ObservableList<TOShipmentOrder> observableOrders = FXCollections.observableArrayList(orders);
        shipmentOrderTable.setItems(observableOrders);
    }
    private void setupColumn(TableColumn<?, ?> column, String propertyName) {
        try {
            column.setCellValueFactory(new PropertyValueFactory<>(propertyName));
        } catch (Exception e) {
            // Set cell value factory to return null for all cells, effectively making them empty
            column.setCellValueFactory(cellData -> null);
        }
    }

    @FXML
    private void refreshClicked(ActionEvent event) {
    	initialize();
    }
    
}