package application;
	
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Label l = new Label("hello");
			
			BorderPane root = new BorderPane();
			
			root.getChildren().add(l);

			Scene scene = new Scene(root,UIconstant.APP_WIDTH,UIconstant.APP_HEIGHT);

			initLayout(root);
			
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void initLayout(BorderPane root) {

		initinfoCenter(root);
		initTileBoard(root);
		
	}

	private void initTileBoard(BorderPane root) {
		
		
	}

	private void initinfoCenter(BorderPane root) {

		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
