package model;

public class TextFieldSelectionFile {

	private String[] files;
	private String path;
	private final String SHORTCUT_PATH_INPUT = "Inputs.Design_EMS29xx.xlsm";
	private final String SHORTCUT_PATH_OUTPUT = "Outputs.Design_EMS29xx.xlsm";

	//constructors
	public TextFieldSelectionFile() {
		super();
		files = new String[2];
		files[0] = SHORTCUT_PATH_INPUT;
		files[1] = SHORTCUT_PATH_OUTPUT;
	}

	//getters/setters
	public String[] getFiles() {
		return files;
	}

	public void setFiles(String[] files) {
		this.files = files;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String verify(String path){
		for(int i = 0; i < files.length; i++){
			if(path.equals(files[i])){
				return path;
			}
		}
		return "no good file";
	}




}
