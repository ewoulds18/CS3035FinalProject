package Sketcher;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;


public class Controller {
	
	GraphicsContext gc;
	Canvas canvas;
	Model model;
	Toolbar t;
	
	Line line;
	Circle circle;
	Rectangle rectangle;
	
	public Controller(){
		line = new Line();
		circle = new Circle();
		rectangle = new Rectangle();
		model = Main.model;
		t = Main.view.getToolbar();
		gc = Main.view.getGraphicsContext();
		canvas = Main.view.getCanvas();
		Main.view.addEventHandler(MouseEvent.ANY, new MouseHandler());
	}
	
	public class MouseHandler implements EventHandler<javafx.scene.input.MouseEvent>{
		
		@Override
		public void handle(MouseEvent e) {
			if(e.getEventType() == MouseEvent.MOUSE_PRESSED){
				if(e.getTarget().getClass() == Canvas.class){
					model.setCurStateToDraw();
					if(t.getFreeDrawButton().isSelected()){
						model.freeDraw(e.getX(),e.getY());
					}else if(t.getEraserButton().isSelected()){
						model.erase(e.getX(), e.getY());
					}else if(t.getLineButton().isSelected()){
						model.drawLine(e.getX(), e.getY());
					}else if(t.getRectButton().isSelected()){
						model.drawRect(e.getX(), e.getY());
					}else if(t.getCircleButton().isSelected()){
						model.drawCircle(e.getX(), e.getY());
					}else if(t.getTextButton().isSelected()){
						model.drawText(e.getX(), e.getY(), t.getLineWidth().getValue(), t.getText().getText());
					}
				}
			}
			if(e.getEventType() == MouseEvent.MOUSE_DRAGGED){
				if(e.getTarget().getClass() == Canvas.class){
					model.setCurStateToDragging();
					if(t.getFreeDrawButton().isSelected()){
						model.freeDraw(e.getX(),e.getY());
					}else if(t.getEraserButton().isSelected()){
						model.erase(e.getX(), e.getY());
					}
				}
			}
			if(e.getEventType() == MouseEvent.MOUSE_RELEASED){
				if(e.getTarget().getClass() == Canvas.class){
					model.setCurStateToRelease();
					if(t.getFreeDrawButton().isSelected()){
						model.freeDraw(e.getX(), e.getY());
					}else if(t.getEraserButton().isSelected()){
						model.erase(e.getX(), e.getY());
					}else if(t.getLineButton().isSelected()){
						model.drawLine(e.getX(), e.getY());
					}else if(t.getRectButton().isSelected()){
						model.drawRect(e.getX(), e.getY());
					}else if(t.getCircleButton().isSelected()){
						model.drawCircle(e.getX(), e.getY());
					}
				}
			}
		}
	}
}
