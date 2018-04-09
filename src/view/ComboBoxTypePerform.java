package view;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
public class ComboBoxTypePerform {

	private ArrayList<String> types;
	private final String SHORTCUT_PATH_INPUT = "Inputs.Design_EMS29xx.xlsm";
	private final String SHORTCUT_PATH_OUTPUT = "Outputs.Design_EMS29xx.xlsm";
	private final String ERROR_NO_SELECTION = "No file selected";
	private final String ERROR_BAD_SELECTION = "no good file";

	//functions
	public void existFile(String path){
		if(path.equals("") || path.equals(ERROR_NO_SELECTION) || path.equals(ERROR_BAD_SELECTION)){
			final Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Information");
			alert.setHeaderText("Choose a file before!!!");
			alert.show();
		}
	}


	public void display(ComboBox cb, ObservableList data){
		cb.setItems(data);
	    cb.getSelectionModel().selectFirst();
	}




}
