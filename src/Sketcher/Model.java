package Sketcher;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class Model {
	
	public enum state {DRAWING, DRAGGING, RELEASE}
	
	private state curState;
	
	private final int X_PADDING = 120;
	private final int Y_PADDING = 25;
	
	private GraphicsContext gc;
	private Canvas canvas;
	
	private Line line;
	private Circle circle;
	private Rectangle rectangle;
	
	public Model() {
		
		line = new Line();
		circle = new Circle();
		rectangle = new Rectangle();
		
		gc = Main.view.getGraphicsContext();
		canvas = Main.view.getCanvas();
	}
	
	public void freeDraw(double x, double y) {
		if(curState.equals(state.DRAWING)){
			gc.beginPath();
			gc.lineTo(x - X_PADDING, y - Y_PADDING);
		}else if(curState.equals(state.DRAGGING)){
			gc.lineTo(x - X_PADDING, y - Y_PADDING);
			gc.stroke();
		}else if(curState.equals(state.RELEASE)){
			gc.lineTo(x - X_PADDING, y - Y_PADDING);
			gc.stroke();
			gc.closePath();
		}
	}
	
	public void erase(double x, double y){
		gc.clearRect(x - X_PADDING - gc.getLineWidth() / 2, y - Y_PADDING - gc.getLineWidth() / 2, gc.getLineWidth(), gc.getLineWidth());
	}
	
	public void drawLine(double x, double y) {
		if(curState.equals(state.DRAWING)){
			line.setStartX(x - X_PADDING);
			line.setStartY(y - Y_PADDING);
		}else if(curState.equals(state.RELEASE)){
			line.setEndX(x - X_PADDING);
			line.setEndY(y - Y_PADDING);
			gc.strokeLine(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY());
		}
	}
	
	public void drawRect(double x, double y) {
		if(curState.equals(state.DRAWING)){
			rectangle.setX(x - X_PADDING);
			rectangle.setY(y - Y_PADDING);
		}else if(curState.equals(state.RELEASE)){
			rectangle.setWidth(Math.abs(x - X_PADDING - rectangle.getX()));
			rectangle.setHeight(Math.abs(y - Y_PADDING - rectangle.getY()));
			if(rectangle.getX() > x - X_PADDING){
				rectangle.setX(x - X_PADDING);
			}
			if(rectangle.getY() > y - Y_PADDING){
				rectangle.setY(y - Y_PADDING);
			}
			gc.fillRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
			gc.strokeRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
		}
	}
	
	public void drawCircle(double x, double y) {
		if(curState.equals(state.DRAWING)){
			circle.setCenterX(x - X_PADDING);
			circle.setCenterY(y - Y_PADDING);
		}else if(curState.equals(state.RELEASE)){
			circle.setRadius((Math.abs(x - X_PADDING - circle.getCenterX()) + Math.abs(y - Y_PADDING - circle.getCenterY())) / 2);
			if(circle.getCenterX() > x - X_PADDING){
				circle.setCenterX(x - X_PADDING);
			}
			if(circle.getCenterY() > y - Y_PADDING){
				circle.setCenterY(y - Y_PADDING);
			}
			gc.fillOval(circle.getCenterX(), circle.getCenterY(), circle.getRadius(), circle.getRadius());
			gc.strokeOval(circle.getCenterX(), circle.getCenterY(), circle.getRadius(), circle.getRadius());
		}
	}
	
	public void drawText(double x, double y, double fontSize, String text) {
		double saveBorderSize = gc.getLineWidth();
		gc.setLineWidth(1);
		gc.setFont(Font.font(fontSize));
		gc.fillText(text,x - X_PADDING, y - Y_PADDING);
		gc.strokeText(text,x - X_PADDING, y - Y_PADDING);
		gc.setLineWidth(saveBorderSize);
	}
	
	public void setCurStateToDraw() {curState = state.DRAWING;}
	
	public void setCurStateToDragging() {curState = state.DRAGGING;}
	
	public void setCurStateToRelease(){curState = state.RELEASE;}
}

