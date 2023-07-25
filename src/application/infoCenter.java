package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

public class infoCenter {
	
	private StackPane pane ; 
	private  Label message ; 
	private  Button startGameButton ; 
	
	public infoCenter() {
		
		pane = new StackPane();
		pane.setMinSize(UIconstant.APP_WIDTH, UIconstant.INFO_CENTER_HEIGHT);
		
		pane.setTranslateX(UIconstant.APP_WIDTH / 2);
		
		pane.setTranslateY(UIconstant.INFO_CENTER_HEIGHT / 2);
		
		message = new Label ("Tic-Tac-Toe");
		
		message.setMinSize(UIconstant.APP_WIDTH,UIconstant.INFO_CENTER_HEIGHT);
		
		message.setFont(Font.font(24));
		
		message.setAlignment(Pos.CENTER);
		message.setTranslateY(-20);
		
		pane.getChildren().add(message);
		
		startGameButton = new Button("Start New Game");
		startGameButton.setMinSize(135, 30);
		startGameButton.setTranslateY(20);
		
		pane.getChildren().add(startGameButton);
		
	}
		
		public StackPane getStackPane() {
			return pane ; 
		}
		
		public void updateMessage(String messagee) {
			
			this.message.setText(messagee);
			
		
	}
		
		public void showStartButton() {
			
			this.startGameButton.setVisible(true);
			
		}
		
		public void hideStartButton() {
			
			this.startGameButton.setVisible(false);
		}
		
		public void setStartButtononAction(EventHandler<ActionEvent> onAction) {
			
			this.startGameButton.setOnAction(onAction);
			
		}
}
