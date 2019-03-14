package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.Transition;
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
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import supermemo.IdeaItem;

public class Main extends Application {

	private Scene listScene;
	private Scene addItemScene;
	private TextArea reviewPane = new TextArea();
	private ArrayList<IdeaItem> ideas = new ArrayList<IdeaItem>();
	private int ideaIndex = 0;
	
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
		reviewPane.setEditable(false);
		reviewPane.setWrapText(true);
		
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

		doneReviewButton.setOnAction(e -> 
		{ try { ideaIndex++; 
		  		reviewPane.setText(ideas.get(ideaIndex).toString()); }
		  catch (IndexOutOfBoundsException exception) {
			doneReviewButton.setDisable(true);
			reviewPane.setText("[You have reviewed all the ideas in the \n queue for this session]");
		  }
		  });
		
		Button addItemButton = new Button("Add");
		addItemButton.setOnAction(e -> primaryStage.setScene(addItemScene));
		addItemButton.setAlignment(Pos.BOTTOM_RIGHT);
		
		metaButtonPanel.getChildren().addAll(doneReviewButton, addItemButton);
		
		VBox layout1 = new VBox(20);
		layout1.getChildren().addAll(
				reviewPane, surpriseGradePanel, 
				goodIdeaGradePanel, metaButtonPanel);
		layout1.setStyle("-fx-background: #222222;");
		listScene = new Scene(layout1, 300, 250);
		//Set up Settings scene
		Label label2 = new Label("Add");
		HBox saveButtonPanel = new HBox(50);
		Button backToList = new Button("Back");
		//move button 50 right
		backToList.setTranslateX(50);
		//move button 50 down
		backToList.setTranslateY(50);
		backToList.setOnAction(e -> primaryStage.setScene(listScene));
		
		TextField ideaTextInput = new TextField();
		ideaTextInput.setPromptText("Your idea reminder here");
		Button addItemSave = new Button("Save");
		
		VBox layout2 = new VBox(20);

		addItemSave.setOnAction( new EventHandler<ActionEvent>() {
			@Override 
			public void handle(ActionEvent e) {
				String sanitizedIdeaText = ideaTextInput.getText().replace("\n",""); // Prevent parsing issues
				sanitizedIdeaText = sanitizedIdeaText.replace("\t", "    ");
				ideas.add(new IdeaItem(((Integer) ideas.size()).toString(), ideaTextInput.getText()));

				// This page helped: https://docs.oracle.com/javafx/2/api/javafx/animation/Transition.html
				Animation savingTextBar = new Transition() {
					{
						setCycleDuration(Duration.millis(500));
						setOnFinished(e -> 
						{ideaTextInput.clear(); 
						ideaTextInput.setEditable(true); 
						addItemSave.setDisable(false);});
					}
					
					@Override
					protected void interpolate(double frac) {
						ideaTextInput.setText("Saving...");
						ideaTextInput.setEditable(false);
						addItemSave.setDisable(true);
					}
					
				};
				savingTextBar.play();
				// Update in case this is the first insert and we can read items now
				reviewPane.setText(ideas.get(ideaIndex).toString());
			}
		});
		
		saveButtonPanel.getChildren().addAll(backToList, addItemSave);
		
		layout2.getChildren().addAll(label2, ideaTextInput, saveButtonPanel);
		layout2.setStyle("-fx-background: #222222");
		addItemScene = new Scene(layout2, 300, 250);
		// scene2.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		// scene1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(listScene);
		
		try {
			BufferedReader inFileReader = new BufferedReader(new FileReader("ideas.tsv"));
			String line;
			IdeaItem deserializeIdeas = new IdeaItem("-1", "This should never appear in your ideas.tsv"); 
			while ((line = inFileReader.readLine()) != null) {
				this.ideas.add((IdeaItem) deserializeIdeas.deserializeItem(line));
				
			}
			reviewPane.setText(ideas.get(ideaIndex).toString());
		}
		catch (FileNotFoundException e) {
			reviewPane.setText("[You currently have no ideas added to \n the database]");
		}

		
		
		
		primaryStage.show();
		} 
	catch(Exception e) {
		e.printStackTrace();
		}
	}

	@Override 
	public void stop() {
		try {
			BufferedWriter outFileWriter = new BufferedWriter(new FileWriter("ideas.tsv"));
			for (int i = 0; i < this.ideas.size(); i++) {
				outFileWriter.write(ideas.get(i).serializeItem());
			}
			outFileWriter.flush();
			outFileWriter.close();
		}
		catch (IOException e) {
			System.out.println("Couldn't write the ideas file...");
			e.printStackTrace();
		}
	}
}
