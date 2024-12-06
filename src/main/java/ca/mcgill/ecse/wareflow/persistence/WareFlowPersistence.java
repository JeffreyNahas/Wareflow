package ca.mcgill.ecse.wareflow.persistence;

import ca.mcgill.ecse.wareflow.application.WareFlowApplication;

import ca.mcgill.ecse.wareflow.model.WareFlow;


public class WareFlowPersistence {

	// update this to be consistent with instructions on myCourses
	private static String filename = "wareflow.json";
	private static JsonSerializer serializer = new JsonSerializer("ca.mcgill.ecse.wareflow");

	public static void setFilename(String filename) {
		WareFlowPersistence.filename = filename;
	}

	public static void save() {
		save(WareFlowApplication.getWareFlow());
	}

	public static void save(WareFlow wareflow) {
		serializer.serialize(wareflow, filename);
	}

	public static WareFlow load() {
		var wareflow = (WareFlow) serializer.deserialize(filename);
		// model cannot be loaded - create empty BTMS
		if (wareflow == null) {
			wareflow = new WareFlow();
		} else {
			wareflow.reinitialize();
		}
		return wareflow;
	}

}