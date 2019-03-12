package application;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

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
		//Set up review scene
		
		TextArea reviewPane = new TextArea();
		reviewPane.setEditable(false);
		reviewPane.setText("Test");
		
		HBox surpriseGradePanel = new HBox();
		Label surpriseGradePanelLabel = new Label("Surprise: ");
		ToggleGroup surprisePanelGroup = new ToggleGroup();
		ToggleButton surprise1 = new ToggleButton("0");
		surprise1.setToggleGroup(surprisePanelGroup);
		ToggleButton surprise2 = new ToggleButton("1");
		surprise2.setToggleGroup(surprisePanelGroup);
		ToggleButton surprise3 = new ToggleButton("2");
		surprise3.setToggleGroup(surprisePanelGroup);
		ToggleButton surprise4 = new ToggleButton("3");
		surprise4.setToggleGroup(surprisePanelGroup);
		ToggleButton surprise5 = new ToggleButton("4");
		surprise5.setToggleGroup(surprisePanelGroup);
		ToggleButton surprise6 = new ToggleButton("5");
		surprise6.setToggleGroup(surprisePanelGroup);
		surpriseGradePanel.getChildren().addAll(
				surpriseGradePanelLabel,
				surprise1,surprise2,surprise3,
				surprise4,surprise5,surprise6);
		
		HBox goodIdeaGradePanel = new HBox();
		Label goodIdeaGradePanelLabel = new Label("Good Idea: ");
		ToggleGroup goodIdeaPanelGroup = new ToggleGroup();
		ToggleButton goodIdea1 = new ToggleButton("0");
		goodIdea1.setToggleGroup(goodIdeaPanelGroup);
		ToggleButton goodIdea2 = new ToggleButton("1");
		goodIdea2.setToggleGroup(goodIdeaPanelGroup);
		ToggleButton goodIdea3 = new ToggleButton("2");
		goodIdea3.setToggleGroup(goodIdeaPanelGroup);
		ToggleButton goodIdea4 = new ToggleButton("3");
		goodIdea4.setToggleGroup(goodIdeaPanelGroup);
		ToggleButton goodIdea5 = new ToggleButton("4");
		goodIdea5.setToggleGroup(goodIdeaPanelGroup);
		ToggleButton goodIdea6 = new ToggleButton("5");
		goodIdea6.setToggleGroup(goodIdeaPanelGroup);
		goodIdeaGradePanel.getChildren().addAll(
				goodIdeaGradePanelLabel,
				goodIdea1,goodIdea2,goodIdea3,
				goodIdea4,goodIdea5,goodIdea6);
		
		HBox metaButtonPanel = new HBox(175);

		Button doneReviewButton = new Button("Done");
		
		Button settingsButton = new Button("Settings");
		settingsButton.setOnAction(e -> primaryStage.setScene(settingsScene));
		settingsButton.setAlignment(Pos.BOTTOM_RIGHT);
		
		metaButtonPanel.getChildren().addAll(doneReviewButton, settingsButton);
		
		VBox layout1 = new VBox(20);
		layout1.getChildren().addAll(
				reviewPane, surpriseGradePanel, 
				goodIdeaGradePanel, metaButtonPanel);
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
