package view;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

public class ListViewProjectPerform {

	public void displayList(ListView lv, ObservableList ol){
		lv.setItems(ol);
	}
}
