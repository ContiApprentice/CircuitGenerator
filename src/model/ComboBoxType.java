package model;

import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;

public class ComboBoxType {

	private ArrayList types;

	//constructors
	public ComboBoxType() {
		super();
	}

	public ComboBoxType(ArrayList types) {
		super();
		this.types = types;
	}

	//getters/setters
	public ArrayList getTypes() {
		return types;
	}

	public void setTypes(ArrayList types) {
		this.types = types;
	}

	//functions
	public ObservableList populate(){
	    ObservableList data = FXCollections.observableList(types);
	    return data;
	}

	public ArrayList readSheets(Workbook wb){
		int cpt = 0;
		Sheet sheet = wb.getSheetAt(cpt);
		ArrayList types = new ArrayList();
		while (sheet.getSheetName().endsWith("Inputs") || sheet.getSheetName().endsWith("Outputs")){
	    	types.add(sheet.getSheetName());
	    	sheet = wb.getSheetAt(++cpt);
		}
		return types;
	}
}
