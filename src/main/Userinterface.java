package main;

import java.awt.Desktop;
import java.io.File;

/**
 * <p> Title: UI Class, UserInterface . </p>
 * 
 * <p> Description: A demonstration package to show the notion of a package and to 
 * 		exercise array usage </p>
 * 
 * 
 */

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * 
 * 
 * The following methods add the elements which are going to be added in the
 * window.
 *
 */

public class Userinterface {

	File selectedFile;

	/**
	 * Attributes which are going to be used
	 */
	private final double BUTTON_WIDTH = 10;
	@SuppressWarnings("unused")
	private final double BUTTON_OFFSET = BUTTON_WIDTH;

	// Labels
	private Label head = new Label("Project 2: Gradebook");
	private Label opt = new Label("Choose Option to open Excel Sheets");
	private Button sub_master = new Button("Subject Master");
	private Button stud_master = new Button("Student Master");
	private Button gradebook = new Button("Gradebook");
	private TextField t_browse = new TextField();
	
	private Button browse = new Button("Browse");

	private Button bcalc = new Button("Generate Report");

//	private Button convert = new Button("Convert");

	String currentUnit = "";
	String finalResult = "";

	public Userinterface(Pane theRoot) {

		// Alignments of labels
		setupLabelUI(head, "Arial", 18, Mainline.WINDOW_WIDTH, Pos.CENTER, 0, 10);

		setupLabelUI(opt, "Arial", 16, Mainline.WINDOW_WIDTH, Pos.CENTER, 0, 50);
		
		// Alignment of buttons

		setupButtonUI(stud_master, "Symbol", 14, FontWeight.BOLD, BUTTON_WIDTH, Pos.BASELINE_LEFT, 50, 100);
		stud_master.setStyle("-fx-background-color: #F0E68C");
		stud_master.setOnAction((event) -> {
			openSpreadsheet("Student_Master.xlsx");
		});
		setupButtonUI(sub_master, "Symbol", 14, FontWeight.BOLD, BUTTON_WIDTH, Pos.BASELINE_LEFT, 210, 100);
		sub_master.setStyle("-fx-background-color: #F0E68C");
		sub_master.setOnAction((event) -> {
			openSpreadsheet("Subject_Master.xlsx");
		});
		setupButtonUI(gradebook, "Symbol", 14,FontWeight.BOLD, BUTTON_WIDTH, Pos.BASELINE_LEFT, 350, 100);
		gradebook.setStyle("-fx-background-color: #F0E68C");
		gradebook.setOnAction((event) -> {
			openSpreadsheet("Gradebook.xlsx");
		});
		// Alignments of textfields

		setupTextUI(t_browse, "Arial", 12, Mainline.WINDOW_WIDTH / 2, Pos.BASELINE_LEFT, 130, 140, true);
		t_browse.textProperty().addListener((observable, oldValue, newValue) -> {

		});

		t_browse.setStyle("-fx-text-fill:black;");

		setupButtonUI(browse, "Symbol", 16,FontWeight.BOLD, BUTTON_WIDTH, Pos.BASELINE_LEFT, 130, 170);
		browse.setStyle("-fx-background-color: #00FA9A");
		browse.setOnAction((event) -> {
			Stage stage = null;
			FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Excel", "*.xlsx"));
			fileChooser.setTitle("Select Excel File");
			selectedFile = fileChooser.showOpenDialog(stage);
			t_browse.setText(selectedFile.getAbsolutePath());

		});
	

		setupButtonUI(bcalc, "Symbol", 16, FontWeight.BOLD,  BUTTON_WIDTH, Pos.BASELINE_LEFT, 240, 170);
		bcalc.setStyle("-fx-background-color: #FA8072");
		bcalc.setOnAction((event) -> {
			report();
		});

		theRoot.getChildren().addAll(opt, gradebook, head, stud_master, sub_master, t_browse, browse, bcalc);

	}

	/**
	 * The following method is used to open the project related spreadsheets using
	 * the default app for excel sheets present in the system.
	 */
	private void openSpreadsheet(String url) {
		try {
			// constructor of file class having file as argument
			File file = new File(url);
			if (!Desktop.isDesktopSupported())// check if Desktop is supported by Platform or not
			{
				System.out.println("Not supported");
				return;
			}
			Desktop desktop = Desktop.getDesktop();
			if (file.exists()) // checks file exists or not
				desktop.open(file); // opens the specified file
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * The following method is used for alignment of a textfields
	 * 
	 * @param t  name of textfield
	 * @param ff it is a string used for font
	 * @param f  it is double type used for font
	 * @param w  it is used for minimum width
	 * @param p  it is used for position
	 * @param x  it is used for x-coordinate
	 * @param y  it is used for y-cordinate
	 * @param e  it is used for adjustment
	 */
	private void setupTextUI(TextField t, String ff, double f, double w, Pos p, double x, double y, boolean e) {
		t.setFont(Font.font(ff, f));
		t.setMinWidth(w);
		t.setMaxWidth(w);
		t.setAlignment(p);
		t.setLayoutX(x);
		t.setLayoutY(y);
		t.setEditable(e);
	}

	/**
	 * The following method is used for alignment of a button
	 * 
	 * @param b  name of button
	 * @param ff it is a string used for font
	 * @param f  it is double type used for font
	 * @param w  it is used for minimum width
	 * @param p  it is used for position
	 * @param x  it is used for x-coordinate
	 * @param y  it is used for y-cordinate
	 */
	private void setupButtonUI(Button b, String ff, double f, FontWeight fw, double w, Pos p, double x, double y) {
		b.setFont(Font.font(ff, fw, f));
		b.setMinWidth(w);
		b.setAlignment(p);
		b.setLayoutX(x);
		b.setLayoutY(y);
	}

	/**
	 * The following method is used for alignment of a label
	 * 
	 * @param l  name of label
	 * @param ff it is a string used for font
	 * @param f  it is double type used for font
	 * @param w  it is used for minimum width
	 * @param p  it is used for position
	 * @param x  it is used for x-coordinate
	 * @param y  it is used for y-cordinate
	 */
	private void setupLabelUI(Label l, String ff, double f, double w, Pos p, double x, double y) {
		l.setFont(Font.font(ff, f));
		l.setMinWidth(w);
		l.setAlignment(p);
		l.setLayoutX(x);
		l.setLayoutY(y);

	}

	public void report() {
		String url = t_browse.getText();
		System.out.println("Selected File: " + url);
		BusinessLogic logic = new BusinessLogic();
		logic.importMarksheet(url);
	}

}
