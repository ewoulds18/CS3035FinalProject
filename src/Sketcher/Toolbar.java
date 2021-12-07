package Sketcher;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Toolbar extends Pane {
	
	private ToggleGroup buttons;
	private ToggleButton freeDrawButton;
	private ToggleButton eraserButton;
	private ToggleButton lineButton;
	private ToggleButton rectButton;
	private ToggleButton circleButton;
	private ToggleButton textButton;
	
	private TextArea text;
	
	private ColorPicker lineColor;
	private ColorPicker fillColor;
	
	private Button undoButton;
	private Button redoButton;
	
	private Slider lineWidth;
	
	private Label lineColorLabel;
	private Label fillColorLabel;
	private Label curFontSize;
	
	public Toolbar(){
		createToolbar();
	}
	
	private void createToolbar(){
		
		this.buttons = new ToggleGroup();
		
		this.freeDrawButton = new ToggleButton("Draw");
		this.freeDrawButton.setMinWidth(100);
		this.eraserButton = new ToggleButton("Eraser");
		this.eraserButton.setMinWidth(100);
		this.lineButton = new ToggleButton("Line");
		this.lineButton.setMinWidth(100);
		this.rectButton = new ToggleButton("Rectangle");
		this.rectButton.setMinWidth(100);
		this.circleButton = new ToggleButton("Circle");
		this.circleButton.setMinWidth(100);
		this.textButton = new ToggleButton("Text");
		this.textButton.setMinWidth(100);
		this.text = new TextArea();
		this.text.setPrefRowCount(1);
		
		buttons.getToggles().addAll(freeDrawButton,eraserButton,lineButton,rectButton,circleButton,textButton);
		
		this.lineColor = new ColorPicker(Color.BLACK);
		this.fillColor = new ColorPicker(Color.TRANSPARENT);
		
		this.undoButton = new Button("Undo");
		this.undoButton.setMinWidth(100);
		this.redoButton = new Button("Redo");
		this.redoButton.setMinWidth(100);
		
		this.lineWidth = new Slider(1,50,5);
		this.lineWidth.setShowTickMarks(true);
		this.lineWidth.setShowTickLabels(true);
		
		this.lineColorLabel = new Label("Line Color");
		this.fillColorLabel = new Label("Fill Color");
		this.curFontSize = new Label("5.0");
		
		VBox buttons = new VBox(10);
		buttons.getChildren().addAll(eraserButton, freeDrawButton, lineButton, rectButton,
				circleButton, textButton, text, curFontSize, lineWidth, lineColorLabel, lineColor, fillColorLabel,
				fillColor, undoButton, redoButton);
		buttons.setPadding(new Insets(5));
		buttons.setStyle("-fx-background-color: #C7C7C7");
		buttons.setPrefWidth(110);
		buttons.setPrefHeight(800);
		
		this.getChildren().add(buttons);
	}
	
	public Button getRedoButton() {
		return redoButton;
	}
	
	public Button getUndoButton() {
		return undoButton;
	}
	
	public ToggleButton getCircleButton() {
		return circleButton;
	}
	
	public ToggleButton getEraserButton() {
		return eraserButton;
	}
	
	public ToggleButton getFreeDrawButton() {
		return freeDrawButton;
	}
	
	public ToggleButton getLineButton() {
		return lineButton;
	}
	
	public ToggleButton getRectButton() {
		return rectButton;
	}
	
	public ToggleButton getTextButton() {
		return textButton;
	}
	
	public Slider getLineWidth() {
		return lineWidth;
	}
	
	public ColorPicker getFillColor() {
		return fillColor;
	}
	
	public ColorPicker getLineColor() {
		return lineColor;
	}
	
	public TextArea getText() {
		return text;
	}
	
	public Label getCurFontSize(){
		return curFontSize;
	}
	
}
