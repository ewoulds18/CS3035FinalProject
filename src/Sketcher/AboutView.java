package Sketcher;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AboutView extends Pane {
	public AboutView(){
	
	}
	public void createAboutView(){
		Stage stage = new Stage();
		VBox root = new VBox(10);
		
		Image image = new Image(getClass().getClassLoader().getResourceAsStream("Images/SketcherCropped.png"));
		
		Label name = new Label("Author: Eric Woulds");
		
		root.setAlignment(Pos.CENTER);
		if(image != null){
			root.getChildren().addAll(new ImageView(image), name);
		}else{
			Label imageNotFound = new Label("Error No Image Found");
			root.getChildren().addAll(imageNotFound, name);
		}
		
		Scene scene = new Scene(root, 500, 500);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}
}
