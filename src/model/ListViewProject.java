package model;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class ListViewProject {

	private ArrayList list_CDS;

	//constructors
	public ListViewProject() {
		super();
	}

	public ListViewProject(ArrayList list_CDS) {
		super();
		this.list_CDS = list_CDS;
	}

	//getters/setters
	public ArrayList getList_CDS() {
		return list_CDS;
	}

	public void setList_CDS(ArrayList list_CDS) {
		this.list_CDS = list_CDS;
	}


	//functions
	//populate the list
	public ObservableList populate(){
	    ObservableList data = FXCollections.observableList(list_CDS);
	    return data;
	}




}
