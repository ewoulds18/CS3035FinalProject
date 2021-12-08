package Sketcher;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;

import java.util.Stack;

public class Model {
	
	public enum state {DRAWING, DRAGGING, RELEASE}
	
	private state curState;
	
	private Stack<Shape> undoHist = new Stack<>();
	private Stack<Shape> redoHist = new Stack<>();
	
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
			
			redoHist.clear();
			Shape lastUndo = undoHist.lastElement();
			lastUndo.setFill(gc.getFill());
			lastUndo.setStroke(gc.getStroke());
			lastUndo.setStrokeWidth(gc.getLineWidth());
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
			
			undoHist.push(new Line(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY()));
			redoHist.clear();
			
			Shape lastUndo = undoHist.lastElement();
			lastUndo.setFill(gc.getFill());
			lastUndo.setStroke(gc.getStroke());
			lastUndo.setStrokeWidth(gc.getLineWidth());
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
			
			undoHist.push(new Rectangle(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight()));
			redoHist.clear();
			Shape lastUndo = undoHist.lastElement();
			lastUndo.setFill(gc.getFill());
			lastUndo.setStroke(gc.getStroke());
			lastUndo.setStrokeWidth(gc.getLineWidth());
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
			
			undoHist.push(new Circle(circle.getCenterX(), circle.getCenterY(), circle.getRadius()));
			
			redoHist.clear();
			Shape lastUndo = undoHist.lastElement();
			lastUndo.setFill(gc.getFill());
			lastUndo.setStroke(gc.getStroke());
			lastUndo.setStrokeWidth(gc.getLineWidth());
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
	
	public void redo(){
		if(!redoHist.empty()){
			Shape shape = redoHist.lastElement();
			gc.setLineWidth(shape.getStrokeWidth());
			gc.setStroke(shape.getStroke());
			gc.setFill(shape.getFill());
			
			redoHist.pop();
			if(shape.getClass() == Line.class){
				Line temp = (Line)shape;
				gc.strokeLine(temp.getStartX(), temp.getStartY(), temp.getEndX(), temp.getEndY());
				
				undoHist.push(new Line(temp.getStartX(), temp.getStartY(), temp.getEndX(), temp.getEndY()));
			}else if(shape.getClass() == Rectangle.class) {
				Rectangle tempRect = (Rectangle) shape;
				gc.fillRect(tempRect.getX(), tempRect.getY(), tempRect.getWidth(), tempRect.getHeight());
				gc.strokeRect(tempRect.getX(), tempRect.getY(), tempRect.getWidth(), tempRect.getHeight());
				
				undoHist.push(new Rectangle(tempRect.getX(), tempRect.getY(), tempRect.getWidth(), tempRect.getHeight()));
			}else if(shape.getClass() == Circle.class) {
				Circle tempCirc = (Circle) shape;
				gc.fillOval(tempCirc.getCenterX(), tempCirc.getCenterY(), tempCirc.getRadius(), tempCirc.getRadius());
				gc.strokeOval(tempCirc.getCenterX(), tempCirc.getCenterY(), tempCirc.getRadius(), tempCirc.getRadius());
				
				undoHist.push(new Circle(tempCirc.getCenterX(), tempCirc.getCenterY(), tempCirc.getRadius()));
			}
			Shape lastUndo = undoHist.lastElement();
			lastUndo.setFill(gc.getFill());
			lastUndo.setStroke(gc.getStroke());
			lastUndo.setStrokeWidth(gc.getLineWidth());
		}else{
			System.out.println("redoHist is empty");
		}
	}
	
	public void undo(){
		if(!undoHist.empty()){
			gc.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
			Shape removed = undoHist.lastElement();
			if(removed.getClass() == Line.class) {
				Line tempLine = (Line) removed;
				tempLine.setFill(gc.getFill());
				tempLine.setStroke(gc.getStroke());
				tempLine.setStrokeWidth(gc.getLineWidth());
				
				redoHist.push(new Line(tempLine.getStartX(), tempLine.getStartY(), tempLine.getEndX(), tempLine.getEndY()));
			} else if(removed.getClass() == Rectangle.class) {
				Rectangle tempRect = (Rectangle) removed;
				tempRect.setFill(gc.getFill());
				tempRect.setStroke(gc.getStroke());
				tempRect.setStrokeWidth(gc.getLineWidth());
				
				redoHist.push(new Rectangle(tempRect.getX(), tempRect.getY(), tempRect.getWidth(), tempRect.getHeight()));
			} else if(removed.getClass() == Circle.class) {
				Circle tempCirc = (Circle) removed;
				tempCirc.setStrokeWidth(gc.getLineWidth());
				tempCirc.setFill(gc.getFill());
				tempCirc.setStroke(gc.getStroke());
				
				redoHist.push(new Circle(tempCirc.getCenterX(), tempCirc.getCenterY(), tempCirc.getRadius()));
			}
			
			Shape lastRedo = redoHist.lastElement();
			lastRedo.setFill(removed.getFill());
			lastRedo.setStroke(removed.getStroke());
			lastRedo.setStrokeWidth(removed.getStrokeWidth());
			undoHist.pop();
			
			for(int i=0; i < undoHist.size(); i++) {
				Shape shape = undoHist.elementAt(i);
				if (shape.getClass() == Line.class) {
					Line temp = (Line) shape;
					gc.setLineWidth(temp.getStrokeWidth());
					gc.setStroke(temp.getStroke());
					gc.setFill(temp.getFill());
					gc.strokeLine(temp.getStartX(), temp.getStartY(), temp.getEndX(), temp.getEndY());
				} else if (shape.getClass() == Rectangle.class) {
					Rectangle temp = (Rectangle) shape;
					gc.setLineWidth(temp.getStrokeWidth());
					gc.setStroke(temp.getStroke());
					gc.setFill(temp.getFill());
					gc.fillRect(temp.getX(), temp.getY(), temp.getWidth(), temp.getHeight());
					gc.strokeRect(temp.getX(), temp.getY(), temp.getWidth(), temp.getHeight());
				} else if (shape.getClass() == Circle.class) {
					Circle temp = (Circle) shape;
					gc.setLineWidth(temp.getStrokeWidth());
					gc.setStroke(temp.getStroke());
					gc.setFill(temp.getFill());
					gc.fillOval(temp.getCenterX(), temp.getCenterY(), temp.getRadius(), temp.getRadius());
					gc.strokeOval(temp.getCenterX(), temp.getCenterY(), temp.getRadius(), temp.getRadius());
				}
			}
		}else{
			System.out.println("undoHist is empty");
		}
	}
	
	public void setCurStateToDraw() {curState = state.DRAWING;}
	
	public void setCurStateToDragging() {curState = state.DRAGGING;}
	
	public void setCurStateToRelease(){curState = state.RELEASE;}
}

