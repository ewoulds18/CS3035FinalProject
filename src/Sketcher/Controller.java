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
					if(t.getFreeDrawButton().isSelected()){
						gc.beginPath();
						gc.lineTo(e.getX() - 120,e.getY() - 25);
					}else if(t.getEraserButton().isSelected()){
						gc.clearRect(e.getX() - 120 - gc.getLineWidth() / 2, e.getY() - 25 - gc.getLineWidth() / 2, gc.getLineWidth(), gc.getLineWidth());
					}else if(t.getLineButton().isSelected()){
						line.setStartX(e.getX()- 120);
						line.setStartY(e.getY() - 25);
					}else if(t.getRectButton().isSelected()){
						rectangle.setX(e.getX() - 120);
						rectangle.setY(e.getY() - 25);
					}else if(t.getCircleButton().isSelected()){
						circle.setCenterX(e.getX() - 120);
						circle.setCenterY(e.getY() - 25 );
					}else if(t.getTextButton().isSelected()){
						gc.setLineWidth(1);
						gc.setFont(Font.font(t.getLineWidth().getValue()));
						gc.fillText(t.getText().getText(),e.getX() - 120, e.getY() - 25);
						gc.strokeText(t.getText().getText(),e.getX() - 120, e.getY() - 25);
					}
				}
			}
			if(e.getEventType() == MouseEvent.MOUSE_DRAGGED){
				if(e.getTarget().getClass() == Canvas.class){
					if(t.getFreeDrawButton().isSelected()){
						gc.lineTo(e.getX() - 120, e.getY() - 25);
						gc.stroke();
					}else if(t.getEraserButton().isSelected()){
						gc.clearRect(e.getX() - 120 - gc.getLineWidth() / 2, e.getY() - 25 - gc.getLineWidth() / 2, gc.getLineWidth(), gc.getLineWidth());
					}
				}
			}
			if(e.getEventType() == MouseEvent.MOUSE_RELEASED){
				if(e.getTarget().getClass() == Canvas.class){
					if(t.getFreeDrawButton().isSelected()){
						gc.lineTo(e.getX() - 120, e.getY() - 25);
						gc.stroke();
						gc.closePath();
					}else if(t.getEraserButton().isSelected()){
						gc.clearRect(e.getX() - 120 - gc.getLineWidth() / 2, e.getY() - 25 - gc.getLineWidth() / 2, gc.getLineWidth(), gc.getLineWidth());
					}else if(t.getLineButton().isSelected()){
						line.setEndX(e.getX() - 120);
						line.setEndY(e.getY() - 25);
						gc.strokeLine(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY());
					}else if(t.getRectButton().isSelected()){
						rectangle.setWidth(Math.abs(e.getX() - 120 - rectangle.getX()));
						rectangle.setHeight(Math.abs(e.getY() - 25 - rectangle.getY()));
						if(rectangle.getX() > e.getX() - 120){
							rectangle.setX(e.getX() - 120);
						}
						if(rectangle.getY() > e.getY() - 25){
							rectangle.setY(e.getY() - 25);
						}
						gc.fillRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
						gc.strokeRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
					}else if(t.getCircleButton().isSelected()){
						circle.setRadius((Math.abs(e.getX() - 120 - circle.getCenterX()) + Math.abs(e.getY() - circle.getCenterY())) / 2);
						if(circle.getCenterX() > e.getX() - 120){
							circle.setCenterX(e.getX() - 120);
						}
						if(circle.getCenterY() > e.getY() - 25){
							circle.setCenterY(e.getY() - 25);
						}
						gc.fillOval(circle.getCenterX(), circle.getCenterY(), circle.getRadius(), circle.getRadius());
						gc.strokeOval(circle.getCenterX(), circle.getCenterY(), circle.getRadius(), circle.getRadius());
					}
				}
			}
		}
	}
}
