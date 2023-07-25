package application;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;


public class Main extends Application {
	
	private infoCenter InfoCenter ;
	private TileBoard tileBoard;
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			//Label l = new Label("hello");
			
			BorderPane root = new BorderPane();
			
			//root.getChildren().add(l);

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
		tileBoard = new TileBoard(InfoCenter);
		root.getChildren().add(tileBoard.getStackPane());
		
	}

	private void initinfoCenter(BorderPane root) {

		InfoCenter = new infoCenter();
		InfoCenter.setStartButtononAction(startnewgame());
		
		root.getChildren().add(InfoCenter.getStackPane());
		
		
	}
	
	private EventHandler<ActionEvent> startnewgame(){
		
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				
				InfoCenter.hideStartButton();
				InfoCenter.updateMessage("Player X's Turn");
				tileBoard.startNewGame();
				
			}
		};
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
