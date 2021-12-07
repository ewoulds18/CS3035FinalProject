package Sketcher;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class View extends BorderPane {
	Canvas canvas;
	GraphicsContext gc;
	
	private Toolbar toolbar;
	
	public View(){
		canvas = new Canvas(1080, 800);
		gc = canvas.getGraphicsContext2D();
		
		//create border around canvas
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(2);
		gc.moveTo(0,0);
		gc.lineTo(1080,0);//draw top line
		gc.lineTo(1080, 800);//draw left line
		gc.lineTo(0, 800);//draw bottom line
		gc.moveTo(0,0);
		gc.lineTo(0,1080);//draw left line
		gc.stroke();
		
		gc.setLineWidth(1);
		
		this.toolbar = new Toolbar();
		this.setLeft(toolbar);
		this.setRight(canvas);
	}
	
	public Toolbar getToolbar() {return this.toolbar;}
	
	public GraphicsContext getGraphicsContext(){return this.gc;}
	
	public Canvas getCanvas() {return this.canvas;}
}
