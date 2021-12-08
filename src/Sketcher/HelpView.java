package Sketcher;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HelpView extends Pane {
	public HelpView(){
	
	}
	public void createHelpView(){
		Stage stage = new Stage();
		VBox root = new VBox();
		Label title = new Label("Help");
		Label tool = new Label("Drawing Tools - Right click on canvas to draw with selected tool");
		Label borderColor = new Label("Border Color - Select color for shape and text borders");
		Label fillColor = new Label("Fill Color - Select color for shape and text fill");
		Label eraser = new Label("Eraser - Select to remove parts of your drawing");
		Label freeDraw = new Label("Draw - Select to draw freely around the canvas");
		Label lineDraw = new Label("Line - Select to draw a line from first click to release");
		Label rectDraw = new Label("Rectangle - Select to draw a rectangle from first click to release");
		Label circleDraw = new Label("Circle - Select to draw a rectangle from first click to release");
		Label textDraw = new Label("Text - Select to create text that is typed in text area below text button");
		Label slider = new Label("Slider - Move slider to set line width and font size size");
		Label undo = new Label("Undo - undo last drawn item (Does not work with draw)");
		Label redo = new Label("Redo - redo last drawn item (Does not work with draw)");
		Label save = new Label("File -> save - Save current drawing to PNG");
		Label open = new Label("File -> open - load selected PNG to canvas");
		
		root.getChildren().addAll(title, tool, borderColor, fillColor, eraser, freeDraw, lineDraw,
				rectDraw, circleDraw, textDraw,slider,undo,redo, save, open);
		
		Scene scene = new Scene(root, 500, 500);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}
}
