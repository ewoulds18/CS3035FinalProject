package Sketcher;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;


public class Controller {
	
	GraphicsContext gc;
	Canvas canvas;
	
	public Controller(){
		gc = Main.view.getGraphicsContext();
		canvas = Main.view.getCanvas();
		Main.view.addEventHandler(MouseEvent.ANY, new MouseHandler());
	}
	
	public class MouseHandler implements EventHandler<javafx.scene.input.MouseEvent>{
		
		@Override
		public void handle(MouseEvent e) {
			canvas.setOnMousePressed(event -> {
				System.out.println("Canvas Pressed");
			});
		}
	}
}
