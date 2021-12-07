package Sketcher;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static final View view = new View();
	public static final Model model = new Model();
	public static final Controller controller = new Controller();
	public static final ToolbarController toolbarController = new ToolbarController();
	
	public static void Main(String[] args){ launch(args);}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		try{
			Scene scene = new Scene(view, 1200, 800);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
