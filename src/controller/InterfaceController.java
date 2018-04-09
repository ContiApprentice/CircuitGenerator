package controller;

import java.io.File;

import java.io.IOException;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import org.apache.poi.ss.usermodel.Workbook;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import javafx.stage.FileChooser;
import model.ComboBoxType;
import model.ComponentsProject;
import model.ExcelFile;
import model.ListViewCDS;
import model.ListViewProject;
import model.TextFieldSelectionFile;
import view.ComboBoxTypePerform;
import view.ListViewCDSPerfrorm;
import view.ListViewProjectPerform;
import view.TextFieldFilePerform;


public class InterfaceController implements Initializable{

	private final String ERROR_BAD_SELECTION = "no good file";

	TextFieldSelectionFile tfsf_file = new TextFieldSelectionFile();
	TextFieldFilePerform tffp_file =new TextFieldFilePerform();
	ComboBoxTypePerform cbtp_type = new ComboBoxTypePerform();
	ComboBoxType cbt_type = new ComboBoxType();
	ListViewCDS lvc_cds = new ListViewCDS();
	ListViewCDSPerfrorm lvcp_cds = new ListViewCDSPerfrorm();
	ExcelFile ef = new ExcelFile();
	ListViewProject lvp_project = new ListViewProject();
	ListViewProjectPerform lvpp_project = new ListViewProjectPerform();
	ComponentsProject cp_project = new ComponentsProject();
	ArrayList list_CDS = new ArrayList();
	ArrayList list_project = new ArrayList();

	//FXML attributs
	@FXML
	private Button btn_add;
	@FXML
	private Button btn_remove;
	@FXML
	private Button btn_up;
	@FXML
	private Button btn_down;
	@FXML
	private Button btn_reset;
	@FXML
	private Button btn_generate;
	@FXML
	private Button btn_file;
	@FXML
	private TextField tf_file;
	@FXML
	private ComboBox cb_type;
	@FXML
	private ListView lv_cds;
	@FXML
	private ListView lv_project;

	//constructor
	public InterfaceController() {
		super();
	}

	//functions
	//events
	//click on type ComboBox
	@FXML
	public void typeClick(){
		cb_type.setOnMouseClicked(new EventHandler<MouseEvent>() {
		    public void handle(MouseEvent me) {
		    	//get text in file TextField
		    	String getFile = tf_file.getText();
		    	//verification file
		    	cbtp_type.existFile(getFile);
		    }
		});
	}

	//change on type ComboBox
	@FXML
	public void typeChanged(){
		cb_type.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
		@Override
		public void changed(ObservableValue observable, Number oldValue, Number newValue) {
			ArrayList a_cds = new ArrayList();
			ObservableList o_cds = null;

			//ComboBox populated
			if(!cbt_type.getTypes().isEmpty()){
				//get text in type ComboBox
				String result = (String) cb_type.getItems().get((Integer) newValue);
				try {
					//opening excel file
					Workbook wb = ef.openFile(tfsf_file.getPath());
					//reading name and version
					a_cds = lvc_cds.readComponent(wb, result);
					lvc_cds.setComponents(a_cds);
					//populate ObservableList
					o_cds = lvc_cds.populateList();
					//display list CDS
					lvcp_cds.displayList(lv_cds, o_cds);
				} catch (EncryptedDocumentException e) {
					e.printStackTrace();
				} catch (InvalidFormatException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else{
				//clear CDS list
				list_CDS.clear();
				cbt_type.setTypes(a_cds);
				o_cds = cbt_type.populate();
				lvcp_cds.displayList(lv_cds, o_cds);
				//clear project list
				list_project.clear();
				lvpp_project.displayList(lv_project, o_cds);
				btn_add.setVisible(false);
				btn_remove.setVisible(false);
				btn_up.setVisible(false);
				btn_down.setVisible(false);
				btn_reset.setVisible(false);
				btn_generate.setVisible(false);
			}
		}
		});
	}

    //click on file button
	@FXML
	public void fileClick(){
		btn_file.setOnMouseClicked(new EventHandler<MouseEvent>() {
	    public void handle(MouseEvent me) {
	    	//open window file
	    	final FileChooser dialog = new FileChooser();
	    	dialog.getExtensionFilters().setAll(new FileChooser.ExtensionFilter("Fichiers texte", "*.xlsm", "*.xlsx"),
	    		 	   new FileChooser.ExtensionFilter("Tous les fichiers", "*.*"));
	 	    final File file = dialog.showOpenDialog(btn_file.getScene().getWindow());

	 	    ArrayList types = new ArrayList();
	 	    ObservableList empty;
	 	    String path;
	 	    //file selected
	 	    if (file != null) {
	 	    	path = file.getPath();
	 	    	System.out.println(path);
	 	    	tfsf_file.setPath(path);
	 	        String name = tfsf_file.verify(file.getName());
	 	        tf_file.setText(name);
	 	        System.out.println(tf_file.getText());
	 	        cbt_type.setTypes(types);
	 	        cbt_type.populate();
	 	        empty = cbt_type.populate();
	 	    	cbtp_type.display(cb_type, empty);
	 	    	//no good name of file
	 	        if (!tf_file.getText().equals(ERROR_BAD_SELECTION)){
		 	        try {
		 	        	list_CDS = new ArrayList();
		 	        	path = file.getPath();
						Workbook wb = ef.openFile(path);
						types = cbt_type.readSheets(wb);
						cbt_type.setTypes(types);
						ObservableList list = cbt_type.populate();
						cbtp_type.display(cb_type, list);
					} catch (EncryptedDocumentException e) {
						e.printStackTrace();
					} catch (InvalidFormatException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
	 	        }
	 	    }
	 	    //file not selected
	 	    else{
	 	    	tf_file.setText("No file selected");
	 	    	cbt_type.setTypes(types);
	 	    	empty = cbt_type.populate();
	 	    	cbtp_type.display(cb_type, empty);
	 	    }
	    }

	});
	}

	//click on CDS ListView
	@FXML
	public void cdsClick(){
		lv_cds.setOnMouseClicked(new EventHandler<MouseEvent>() {
	    public void handle(MouseEvent me) {
	    	String nameAndVersion = (String) lv_cds.getSelectionModel().getSelectedItem();
	        if (nameAndVersion != null){
	        	btn_add.setVisible(true);
	        	cp_project.setnameAndVersion(nameAndVersion);
	        	String type = (String) cb_type.getValue();
	        	cp_project.setType(type);
	        	list_CDS.add(cp_project.getType()+ " | " +cp_project.getnameAndVersion());
	        	lvp_project.setList_CDS(list_CDS);
	        }
	    }
	    });
	}

	//click on add Button
	@FXML
	public void addClick(){
		btn_add.setOnMouseClicked(new EventHandler<MouseEvent>() {
		    public void handle(MouseEvent me) {
		    	if(!lvp_project.getList_CDS().isEmpty()){
		    		if(lvp_project.getList_CDS().size() > 1){
		    			btn_up.setVisible(true);
			        	btn_down.setVisible(true);
		    		}
		    		ObservableList list_project = lvp_project.populate();
		        	lvpp_project.displayList(lv_project, list_project);
		        	btn_reset.setVisible(true);
		        	btn_generate.setVisible(true);
		    	}
		    }

		});
	}

	//click on remove Button
		@FXML
		public void removeClick(){
			btn_remove.setOnMouseClicked(new EventHandler<MouseEvent>() {
			    public void handle(MouseEvent me) {
			    	String data = (String) lv_project.getSelectionModel().getSelectedItem();
			    	System.out.println(data);
			    	if(data != null){
			    		lvp_project.getList_CDS().remove(data);
			    		ObservableList ol_newList= lvp_project.populate();
			    		lvpp_project.displayList(lv_project, ol_newList);

			    		if(lvp_project.getList_CDS().size() == 0){
			    			btn_remove.setVisible(false);
			    		}
			    		else if(lvp_project.getList_CDS().size() < 2){
			    			btn_up.setVisible(false);
			    			btn_down.setVisible(false);
			    		}
			    	}
			    }

			});
		}

		//click on reset Button
				@FXML
				public void resetClick(){
					btn_reset.setOnMouseClicked(new EventHandler<MouseEvent>() {
					    public void handle(MouseEvent me) {
					    	lvp_project.getList_CDS().clear();
				    		ObservableList ol_emptyList= lvp_project.populate();
				    		lvpp_project.displayList(lv_project, ol_emptyList);
				    		btn_remove.setVisible(false);
				    		btn_up.setVisible(false);
				    		btn_down.setVisible(false);
				    		btn_reset.setVisible(false);
				    		btn_generate.setVisible(false);
					    }

					});
				}

	//click on Project ListView
		@FXML
		public void projectClick(){
			lv_project.setOnMouseClicked(new EventHandler<MouseEvent>() {
			    public void handle(MouseEvent me) {
			    	String data = (String) lv_project.getSelectionModel().getSelectedItem();
			    	System.out.println(data);
			    	if (data != null){
			    		if(!lvp_project.getList_CDS().isEmpty()){
			    		btn_remove.setVisible(true);

			    		}
			    	}
			    }

			});
		}

//	//click on up Button
		@FXML
		public void upClick(){
//			btn_up.setOnMouseClicked(new EventHandler<MouseEvent>() {
//				public void handle(MouseEvent me) {
//					int position = lv_project.getSelectionModel().getSelectedIndex();
//					System.out.println(position);
////					String data = (String) lv_project.getSelectionModel().getSelectedItem();
//					if(position != -1){
//						String item = (String) lv_project.getSelectionModel().getSelectedItem();
//						ArrayList data = lvp_project.getList_CDS();
//						data.set(position-1, item);
//						lvp_project.setList_CDS(data);
//						ObservableList ol_newList= lvp_project.populate();
//			    		lvpp_project.displayList(lv_project, ol_newList);
//					}
//				}
//
//			});
		}
//
//	//click on down Button
		@FXML
		public void downClick(){
//			lv_project.setOnMouseClicked(new EventHandler<MouseEvent>() {
//				public void handle(MouseEvent me) {
//
//				}
//
//			});
		}

	//add button
	@FXML
	public void addEnter(){
		btn_add.setOnMouseEntered(new EventHandler<MouseEvent>() {
		    public void handle(MouseEvent me) {
		    	btn_add.setStyle("-fx-background-color: #BAA5BF;");
		    }

		});
	}

	@FXML
	public void addExit(){
		btn_add.setOnMouseExited(new EventHandler<MouseEvent>() {
		    public void handle(MouseEvent me) {
		    	btn_add.setStyle("-fx-background-color: #C7C7DB;");
		    }

		});
	}

		//remove button
		@FXML
		public void removeEnter(){
			btn_remove.setOnMouseEntered(new EventHandler<MouseEvent>() {
			    public void handle(MouseEvent me) {
			    	btn_remove.setStyle("-fx-background-color: #BAA5BF;");
			    }

			});
		}

		@FXML
		public void removeExit(){
			btn_remove.setOnMouseExited(new EventHandler<MouseEvent>() {
			    public void handle(MouseEvent me) {
			    	btn_remove.setStyle("-fx-background-color: #C7C7DB;");
			    }

			});
		}
		//up button
		@FXML
		public void upEnter(){
			btn_up.setOnMouseEntered(new EventHandler<MouseEvent>() {
			    public void handle(MouseEvent me) {
			    	btn_up.setStyle("-fx-background-color: #BAA5BF;");
			    }

			});
		}

		@FXML
		public void upExit(){
			btn_up.setOnMouseExited(new EventHandler<MouseEvent>() {
			    public void handle(MouseEvent me) {
			    	btn_up.setStyle("-fx-background-color: #C7C7DB;");
			    }

			});
		}
		//down button
		@FXML
		public void downEnter(){
			btn_down.setOnMouseEntered(new EventHandler<MouseEvent>() {
			    public void handle(MouseEvent me) {
			    	btn_down.setStyle("-fx-background-color: #BAA5BF;");
			    }

			});
		}

		@FXML
		public void downExit(){
			btn_down.setOnMouseExited(new EventHandler<MouseEvent>() {
			    public void handle(MouseEvent me) {
			    	btn_down.setStyle("-fx-background-color: #C7C7DB;");
			    }

			});
		}
		//reset button
		@FXML
		public void resetEnter(){
			btn_reset.setOnMouseEntered(new EventHandler<MouseEvent>() {
			    public void handle(MouseEvent me) {
			    	btn_reset.setStyle("-fx-background-color: #BAA5BF;");
			    }

			});
		}

		@FXML
		public void resetExit(){
			btn_reset.setOnMouseExited(new EventHandler<MouseEvent>() {
			    public void handle(MouseEvent me) {
			    	btn_reset.setStyle("-fx-background-color: #C7C7DB;");
			    }

			});
		}
		//generate button
		@FXML
		public void generateEnter(){
			btn_generate.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				btn_generate.setStyle("-fx-background-color: #BAA5BF;");
			}

			});
		}

		@FXML
		public void generateExit(){
			btn_generate.setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				btn_generate.setStyle("-fx-background-color: #C7C7DB;");
			}
			});
		}


	//init FXML
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		fileClick();
		typeClick();
		typeChanged();
		addClick();
		addEnter();
		addExit();
		addClick();
		cdsClick();
		removeEnter();
		removeExit();
		projectClick();
		removeClick();
		resetClick();
		upClick();
		downClick();
	}







}
