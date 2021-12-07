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
			canvas.setOnMousePressed(event -> {
				if(t.getFreeDrawButton().isSelected()){
					gc.beginPath();
					gc.lineTo(event.getX(),event.getY());
				}else if(t.getEraserButton().isSelected()){
					gc.clearRect(event.getX() - gc.getLineWidth() / 2, event.getY() - gc.getLineWidth() / 2, gc.getLineWidth(), gc.getLineWidth());
				}else if(t.getLineButton().isSelected()){
					line.setStartX(event.getX());
					line.setStartY(event.getY());
				}else if(t.getRectButton().isSelected()){
					rectangle.setX(event.getX());
					rectangle.setY(event.getY());
				}else if(t.getCircleButton().isSelected()){
					circle.setCenterX(event.getX());
					circle.setCenterY(event.getY());
				}else if(t.getTextButton().isSelected()){
					gc.setLineWidth(1);
					gc.setFont(Font.font(t.getLineWidth().getValue()));
					gc.fillText(t.getText().getText(),event.getX(), event.getY());
					gc.strokeText(t.getText().getText(),event.getX(), event.getY());
				}
			});
			canvas.setOnMouseDragged(event -> {
				if(t.getFreeDrawButton().isSelected()){
					gc.lineTo(event.getX(), event.getY());
					gc.stroke();
				}else if(t.getEraserButton().isSelected()){
					gc.clearRect(event.getX() - gc.getLineWidth() / 2, event.getY() - gc.getLineWidth() / 2, gc.getLineWidth(), gc.getLineWidth());
				}
			});
			canvas.setOnMouseReleased(event -> {
				if(t.getFreeDrawButton().isSelected()){
					gc.lineTo(event.getX(), event.getY());
					gc.stroke();
					gc.closePath();
				}else if(t.getEraserButton().isSelected()){
					gc.clearRect(event.getX() - gc.getLineWidth() / 2, event.getY() - gc.getLineWidth() / 2, gc.getLineWidth(), gc.getLineWidth());
				}else if(t.getLineButton().isSelected()){
					line.setEndX(event.getX());
					line.setEndY(event.getY());
					gc.strokeLine(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY());
				}else if(t.getRectButton().isSelected()){
					rectangle.setWidth(Math.abs(event.getX() - rectangle.getX()));
					rectangle.setHeight(Math.abs(event.getY() - rectangle.getY()));
					if(rectangle.getX() > event.getX()){
						rectangle.setX(event.getX());
					}
					if(rectangle.getY() > event.getY()){
						rectangle.setY(event.getY());
					}
					
					gc.fillRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
					gc.strokeRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
				}else if(t.getCircleButton().isSelected()){
					circle.setRadius((Math.abs(event.getX() - circle.getCenterX()) + Math.abs(event.getY() - circle.getCenterY())) / 2);
					if(circle.getCenterX() > event.getX()){
						circle.setCenterX(event.getX());
					}
					if(circle.getCenterY() > event.getY()){
						circle.setCenterY(event.getY());
					}
					gc.fillOval(circle.getCenterX(), circle.getCenterY(), circle.getRadius(), circle.getRadius());
					gc.strokeOval(circle.getCenterX(), circle.getCenterY(), circle.getRadius(), circle.getRadius());
				}
				
			});
		}
	}
}
