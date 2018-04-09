package model;

import java.util.ArrayList;

import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ListViewCDS {

	private ArrayList components;
	ComponentsCDS c_cds = new ComponentsCDS();

	//constructors

	//getters/setters
	public ArrayList getComponents() {
		return components;
	}


	public void setComponents(ArrayList components) {
		this.components = components;
	}


	public ArrayList readComponent(Workbook wb, String type){
		int cptRow = 3;
		Sheet sheet = wb.getSheet(type);
		ArrayList types = new ArrayList();
		Row row = sheet.getRow(cptRow);
		while(!row.getCell(0).getStringCellValue().equals("END")){
			if(!row.getCell(0).getStringCellValue().equals("")){
				c_cds.setName(row.getCell(0).getStringCellValue());
				c_cds.setVersion(row.getCell(1).getStringCellValue());
				types.add(c_cds.getName()+ " ( " +c_cds.getVersion()+ " )");

				//types.add(row.getCell(0).getStringCellValue()+ " ( " +row.getCell(1).getStringCellValue()+ " )");
				cptRow++;
				row = sheet.getRow(cptRow);
			}
			else{
				cptRow++;
				row = sheet.getRow(cptRow);
			}
		}
		return types;
	}


	public ObservableList populateList(){
	    ObservableList data = FXCollections.observableList(components);
	    return data;
	}

	public void verifySelection(){
		//if(ObservableList.)
	}
}
