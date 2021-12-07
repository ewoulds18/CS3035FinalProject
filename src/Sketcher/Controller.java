package Sketcher;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;


public class Controller {
	
	GraphicsContext gc;
	Canvas canvas;
	Model model;
	Toolbar t;
	
	public Controller(){
		model = Main.model;
		t = Main.view.getToolbar();
		gc = Main.view.getGraphicsContext();
		canvas = Main.view.getCanvas();
		Main.view.addEventHandler(MouseEvent.ANY, new MouseHandler());
	}
	
	public class MouseHandler implements EventHandler<javafx.scene.input.MouseEvent>{
		
		@Override
		public void handle(MouseEvent e) {
			canvas.setOnMousePressed(event -> {
				if(t.getFreeDrawButton().isSelected()){
					gc.beginPath();
					gc.lineTo(event.getX(),event.getY());
				}
			});
			canvas.setOnMouseDragged(event -> {
				if(t.getFreeDrawButton().isSelected()){
					gc.lineTo(event.getX(), event.getY());
					gc.stroke();
				}
			});
			canvas.setOnMouseReleased(event -> {
				if(t.getFreeDrawButton().isSelected()){
					gc.lineTo(event.getX(), event.getY());
					gc.stroke();
					gc.closePath();
				}
			});
		}
	}
}
