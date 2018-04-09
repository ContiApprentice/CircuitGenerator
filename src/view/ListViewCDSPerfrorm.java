package view;

import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;

public class ListViewCDSPerfrorm {


	public void displayList(ListView lv, ObservableList ol){
		lv.setItems(ol);
	}
}
