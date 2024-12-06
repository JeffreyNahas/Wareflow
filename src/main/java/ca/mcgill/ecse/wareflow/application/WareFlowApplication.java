package ca.mcgill.ecse.wareflow.application;

import ca.mcgill.ecse.wareflow.javafx.fxml.WareFlowFxmlView;
import ca.mcgill.ecse.wareflow.model.WareFlow;
import javafx.application.Application;;

public class WareFlowApplication {

  private static WareFlow wareFlow;

  public static void main(String[] args) {
	  	wareFlow=getWareFlow();	
	  	// TODO add this line
	    Application.launch(WareFlowFxmlView.class, args);  }

  public static WareFlow getWareFlow() {
    if (wareFlow == null) {
      // these attributes are default, you should set them later with the setters
      wareFlow = new WareFlow();
    }
    return wareFlow;
  }

}
