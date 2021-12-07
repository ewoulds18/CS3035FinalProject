package Sketcher;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.text.Font;

public class ToolbarController {
	
	public ToolbarController(){
		GraphicsContext gc = Main.view.getGraphicsContext();
		Toolbar toolbar = Main.view.getToolbar();
		
		ToggleButton freeDrawButton = toolbar.getFreeDrawButton();
		ToggleButton eraserButton = toolbar.getEraserButton();
		ToggleButton lineButton = toolbar.getLineButton();
		ToggleButton rectButton = toolbar.getRectButton();
		ToggleButton circleButton = toolbar.getCircleButton();
		ToggleButton textButton = toolbar.getTextButton();
		
		Button undo = toolbar.getUndoButton();
		Button redo = toolbar.getRedoButton();
		
		Slider lineWidth = toolbar.getLineWidth();
		
		Label curFontSize = toolbar.getCurFontSize();
		
		ColorPicker fillColor = toolbar.getFillColor();
		ColorPicker lineColor = toolbar.getLineColor();
		
		freeDrawButton.setOnMouseClicked(event -> {
			System.out.println("Free Draw Pressed");
			gc.setStroke(lineColor.getValue());
		});
		
		eraserButton.setOnMouseClicked(event -> {
			System.out.println("Eraser Pressed");
			Main.model.erase();
		});
		
		lineButton.setOnMouseClicked(event -> {
			System.out.println("Draw Line Pressed");
			Main.model.drawLine();
		});
		
		rectButton.setOnMouseClicked(event -> {
			System.out.println("Draw Rect Pressed");
			Main.model.drawRect();
		});
		
		circleButton.setOnMouseClicked(event -> {
			System.out.println("Draw Circle Pressed");
			Main.model.drawCircle();
		});
		
		textButton.setOnMouseClicked(event -> {
			System.out.println("Draw Text Pressed");
			Main.model.drawText();
		});
		
		undo.setOnMouseClicked(event -> {
			System.out.println("Undo Pressed");
		});
		
		redo.setOnMouseClicked(event -> {
			System.out.println("Redo Pressed");
		});
		
		lineWidth.valueProperty().addListener(e->{
			double width = lineWidth.getValue();
			if(textButton.isSelected()){
				gc.setLineWidth(1);
				gc.setFont(Font.font(lineWidth.getValue()));
				curFontSize.setText(String.format("%.1f", width));
				return;
			}
			curFontSize.setText(String.format("%.1f", width));
			gc.setLineWidth(width);
		});
		
		fillColor.setOnAction(e->{
			System.out.println("New Fill selected" + fillColor.getValue());
			gc.setFill(fillColor.getValue());
		});
		
		lineColor.setOnAction(event -> {
			System.out.println("New Line selected" + lineColor.getValue());
			gc.setStroke(lineColor.getValue());
		});
	}
}
