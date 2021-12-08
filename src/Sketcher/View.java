package Sketcher;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class View extends BorderPane {
	Canvas canvas;
	GraphicsContext gc;
	
	private Toolbar toolbar;
	
	public View(){
		canvas = new Canvas(1080, 770);
		gc = canvas.getGraphicsContext2D();
		
		//create border around canvas
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(2);
		gc.moveTo(0,0);
		gc.lineTo(1080,0);//draw top line
		gc.lineTo(1080, 770);//draw left line
		gc.lineTo(0, 770);//draw bottom line
		gc.moveTo(0,0);
		gc.lineTo(0,1080);//draw left line
		gc.stroke();
		
		gc.setLineWidth(1);
		
		CreateMenuBar mb = new CreateMenuBar();
		setUpMenuItems(mb.getAllItems());
		
		this.toolbar = new Toolbar();
		this.setTop(mb);
		this.setLeft(toolbar);
		this.setRight(canvas);
	}
	
	private void setUpMenuItems(MenuItem[] allItems) {
		allItems[0].setOnAction(e->{//save
			FileChooser saveFile = new FileChooser();
			saveFile.setTitle("SaveFile");
			
			File file = saveFile.showSaveDialog(Main.getPrimaryStage());
			
			try{
				if( file != null){
					WritableImage writableImage = new WritableImage((int)canvas.getWidth(), (int)canvas.getHeight());
					canvas.snapshot(null, writableImage);
					RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
					ImageIO.write(renderedImage, "png", file);
				}
			}catch (IOException ex){
				ex.printStackTrace();
			}
		});
		allItems[1].setOnAction(e->{//open
			FileChooser openFile = new FileChooser();
			openFile.setTitle("Open File");
			
			File file = openFile.showOpenDialog(Main.getPrimaryStage());
			
			try{
				if(file != null){
					InputStream inputStream = new FileInputStream(file);
					Image image = new Image(inputStream);
					gc.drawImage(image,0,0);
				}
			}catch (IOException ex){
				ex.printStackTrace();
			}
		});
		allItems[2].setOnAction(e->{//about
			AboutView aboutView = new AboutView();
			aboutView.createAboutView();
		});
		allItems[3].setOnAction(e->{//help
			HelpView helpView = new HelpView();
			helpView.createHelpView();
		});
		allItems[4].setOnAction(e->{//clear
			gc.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
		});
		
	}
	
	public Toolbar getToolbar() {return this.toolbar;}
	
	public GraphicsContext getGraphicsContext(){return this.gc;}
	
	public Canvas getCanvas() {return this.canvas;}
}
