package main;

/**
 * <p> Title: Mainline Class, The mainline for the project 2. </p>
 * 
 * <p> Description: A demonstration package to show the notion of a package and to 
 * 		exercise array usage </p>
 * 
 * 
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * The execution of program starts from here
 * 
 *
 */

public class Mainline extends Application {

	public final static double WINDOW_WIDTH = 500;
	public final static double WINDOW_HEIGHT = 400;

	public Userinterface theGUI;

	public void start(Stage theStage) throws Exception {

		theStage.setTitle(" Team 2 ");
		
		Pane theRoot = new Pane();

		theGUI = new Userinterface(theRoot);

		Scene theScene = new Scene(theRoot, WINDOW_WIDTH, WINDOW_HEIGHT);
		BackgroundFill background_fill = new BackgroundFill(Color.AQUAMARINE,  
                CornerRadii.EMPTY, Insets.EMPTY); 
		 Background background = new Background(background_fill); 
		  
         // set background 
		 theRoot.setBackground(background); 
		theStage.setScene(theScene);

		theStage.show();

	}

	/**
	 * Execution starts from here
	 * 
	 * @param args start the program
	 */

	public static void main(String[] args) { // This method may not be required

		launch(args); // for all JavaFX applications using
	}
}
