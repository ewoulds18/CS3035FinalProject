package Sketcher;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class Model{
	
	Line line;
	Rectangle rect;
	Circle circle;
	
	private GraphicsContext gc;
	
	public Model(){
		gc = Main.view.getGraphicsContext();
		line = new Line();
		rect = new Rectangle();
		circle = new Circle();
	}
	
	public void freeDraw(double x, double y){
		gc.beginPath();
		gc.lineTo(x,y);
	}
	
	public void erase(){
	
	}
	
	public void drawLine(){
	
	}
	
	public void drawRect(){
	
	}
	
	public void drawCircle(){
	
	}
	
	public void drawText(){
	
	}
}
