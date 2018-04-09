package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;

public class ExcelFile {

	private String[] types;
	private String[] names;
	private String[] versions;

	//constructors
	public ExcelFile() {
		super();
	}
	public ExcelFile(String[] types, String[] names, String[] versions) {
		super();
		this.types = types;
		this.names = names;
		this.versions = versions;
	}

	//getters/setters
	public String[] getTypes() {
		return types;
	}
	public void setTypes(String[] types) {
		this.types = types;
	}
	public String[] getNames() {
		return names;
	}
	public void setNames(String[] names) {
		this.names = names;
	}
	public String[] getVersions() {
		return versions;
	}
	public void setVersions(String[] versions) {
		this.versions = versions;
	}

	//functions
	public Workbook openFile(String nameFile) throws IOException, EncryptedDocumentException, InvalidFormatException{
		InputStream inp = new FileInputStream(nameFile);
	    Workbook wb = WorkbookFactory.create(inp);
	    inp.close();
	    return wb;
	}

}
