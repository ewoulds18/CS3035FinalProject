package Sketcher;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.text.Font;

public class ToolbarController {
	
	public ToolbarController(){
		GraphicsContext gc = Main.view.getGraphicsContext();
		Toolbar toolbar = Main.view.getToolbar();
		ToggleButton textButton = toolbar.getTextButton();
		
		Slider lineWidth = toolbar.getLineWidth();
		
		Label curFontSize = toolbar.getCurFontSize();
		
		ColorPicker fillColor = toolbar.getFillColor();
		ColorPicker lineColor = toolbar.getLineColor();
		
		gc.setFill(fillColor.getValue());
		gc.setStroke(lineColor.getValue());
		
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
