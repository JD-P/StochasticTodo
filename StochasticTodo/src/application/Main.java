package application;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

	private Scene listScene;
	private Scene newItemScene;
	private Scene settingsScene;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
	try { 
		primaryStage.setTitle("Stochastic ToDo List");
		//Set up Scene 1
		ArrayList<String> testStrings = new ArrayList<String>();
		String[] tempStrings = {"Test1","Test2","Test3","Test4","Test5","Test6"};
		testStrings.addAll(Arrays.asList(tempStrings));
		ObservableList<String> names = FXCollections.observableArrayList(
		          "Julia", "Ian", "Sue", "Matthew", "Hannah", "Stephan", "Denise");
		ListView<String> listView = new ListView<String>(names);	
		
		Button button1 = new Button("Settings");
		button1.setOnAction(e -> primaryStage.setScene(settingsScene));
		VBox layout1 = new VBox(20);
		layout1.getChildren().addAll(listView,button1);
		listScene = new Scene(layout1, 300, 250);
		//Set up Scene 2
		Label label2 = new Label("Settings");
		Button backToList = new Button("Back");
		//move button 50 right
		backToList.setTranslateX(50);
		//move button 50 down
		backToList.setTranslateY(50);
		backToList.setOnAction(e -> primaryStage.setScene(listScene));
		
		Label howManyItemsLabel = new Label("How many items to display:");
		TextField howManyItemsInput = new TextField();
		Button settingsSave = new Button("Save");
		settingsSave.setOnAction( new EventHandler<ActionEvent>() {
			@Override 
			public void handle(ActionEvent e) {
				System.out.println(howManyItemsInput.getText());
				names.clear();
				for (int i = 0; i < new Integer(howManyItemsInput.getText()); i++) {
					names.add(testStrings.get(i));
				}
			}
		});
		
		VBox layout2 = new VBox(20);
		layout2.setStyle("-fx-background:  #DFFDFF;");
		layout2.getChildren().addAll(label2, howManyItemsLabel, howManyItemsInput, settingsSave, backToList);
		settingsScene = new Scene(layout2, 300, 250);
		// scene2.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		// scene1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(listScene);
		primaryStage.show();
		} 
	catch(Exception e) {
		e.printStackTrace();
		}
	}

}
