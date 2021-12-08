package Sketcher;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class Main extends Application {
	
	static Stage stage;
	public static final View view = new View();
	public static final Model model = new Model();
	public static final Controller controller = new Controller();
	public static final ToolbarController toolbarController = new ToolbarController();
	
	public static void Main(String[] args){ launch(args);}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		try{
			stage = primaryStage;
			Scene scene = new Scene(view, 1200, 800);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();

		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public static Stage getPrimaryStage(){return stage;}
}
