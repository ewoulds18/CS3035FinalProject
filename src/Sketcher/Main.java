package Sketcher;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
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
			Pane pane = FXMLLoader.load((getClass().getResource("SplashScreen.fxml")));
			Scene splashScreen = new Scene(pane, 1200,800);
			primaryStage.setScene(splashScreen);
			primaryStage.setResizable(false);
			primaryStage.show();
			
			
			FadeTransition fadeIn = new FadeTransition(Duration.seconds(2),pane);
			fadeIn.setFromValue(0);
			fadeIn.setToValue(1);
			fadeIn.setCycleCount(1);
			
			FadeTransition fadeOut = new FadeTransition(Duration.seconds(2), pane);
			fadeOut.setFromValue(1);
			fadeOut.setToValue(0);
			fadeOut.setCycleCount(1);
			
			fadeIn.play();
			fadeIn.setOnFinished((e) -> {
				fadeOut.play();
			});
			
			fadeOut.setOnFinished((e) -> {
				try {
					stage = primaryStage;
					Scene scene = new Scene(view, 1200, 800);
					primaryStage.setScene(scene);
					primaryStage.setResizable(false);
					primaryStage.show();
					
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			});
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public static Stage getPrimaryStage(){return stage;}
}
