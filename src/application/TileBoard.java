package application;

import application.TileBoard.Tile;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class TileBoard {

	private StackPane pane ; 
	private infoCenter InfoCentre ;
	private Tile[][] tiles = new Tile[3][3];
	private char playerTurn = 'X';
	private boolean isEndOfGame = false ;
	private Line winningLine;
	
	public TileBoard(infoCenter infoCenter) {
		this.InfoCentre = infoCenter ; 
		
		pane = new StackPane();
		pane.setMinSize(UIconstant.APP_WIDTH, UIconstant.TILE_BOARD_HEIGHT);
		pane.setTranslateX(UIconstant.APP_WIDTH/2);
		pane.setTranslateY((UIconstant.TILE_BOARD_HEIGHT/2)+UIconstant.INFO_CENTER_HEIGHT);
		
		
		addAllTitles();
		
	}
	
	private void addAllTitles() {
		
		for(int row = 0 ; row < 3 ; row++ ) {
			for(int column =0 ; column<3;column++) {
				Tile tile = new Tile();
				
				tile.getStackPane().setTranslateX((column*100) -100);
				tile.getStackPane().setTranslateY((row*100) -100);
				pane.getChildren().add(tile.getStackPane());
				tiles[row][column]=tile ;
			}
		}
		
	}
	
	public void startNewGame() {
		
		isEndOfGame = false ; 
		playerTurn = 'X';
		for(int row = 0 ; row < 3 ; row++ ) {
			for(int column =0 ; column<3;column++) {
				tiles[row][column].setvalue("");
			};	
		}
		winningLine.setVisible(false);
	}
	public void changePlayerTurn() {
		if(playerTurn=='X') {
			playerTurn='O';
		}else {
			playerTurn='X';

		}
		
		InfoCentre.updateMessage("Player" + playerTurn + "'s Turn");
	}
	
	public String getPlayerTurn() {
		
		return String.valueOf(playerTurn);
	}
	
	public StackPane getStackPane() {
		
		return pane; 
		
	}
	
	public void checkforWinner() {
		checkrowsforWinner();
		checkcolsforwinner();
		checkdiagonalforwinner();
		checkreversediagonalforwinner();
		checkforstalemate();
		
		
	}

	
	private void checkforstalemate() {
		if(!isEndOfGame) {
			for(int rows = 0 ; rows <3;rows++) {
				for(int col = 0 ; col <3;col++) {
					if(tiles[rows][col].getvalue().isEmpty())
						return;
				}
					
				}
			isEndOfGame=true;
			InfoCentre.updateMessage("Stalemate..");
			InfoCentre.showStartButton();
			//System.out.println("here in checkforstalemate");
			}
			
		
		}
		
	

	private void checkreversediagonalforwinner() {
		if(!isEndOfGame) {
		if(tiles[0][2].getvalue().equals(tiles[1][1].getvalue())
		&& tiles[0][2].getvalue().equals(tiles[2][0].getvalue())
		&& !tiles[0][2].getvalue().isEmpty()){
			
		String winner = tiles[0][2].getvalue();
		new WinningTiles(tiles[0][2],tiles[1][1] ,tiles[2][0]);

		endGame(winner,new WinningTiles(tiles[0][2],tiles[1][1] ,tiles[2][0]));
		//System.out.println("here in checkreversediagonalforwinner");

		return;	
		
		}
		}
	}
	

	private void checkdiagonalforwinner() {
		if(!isEndOfGame) {
			if(tiles[0][0].getvalue().equals(tiles[1][1].getvalue())
			&& tiles[0][0].getvalue().equals(tiles[2][2].getvalue())
			&& !tiles[0][0].getvalue().isEmpty()){
				
			String winner = tiles[0][0].getvalue();
			//new WinningTiles(tiles[0][0],tiles[1][1] ,tiles[2][2]);

			endGame(winner,new WinningTiles(tiles[0][0],tiles[1][1] ,tiles[2][2]));

			//System.out.println("here in checkdiagonalforwinner");

			return;	
			}
		}
		
		
	}

	private void checkcolsforwinner() {
		for(int col = 0 ; col <3;col++) {
			if(tiles[0][col].getvalue().equals(tiles[1][col].getvalue())
			&& tiles[0][col].getvalue().equals(tiles[2][col].getvalue())
			&& !tiles[0][col].getvalue().isEmpty()){
				
			String winner = tiles[0][col].getvalue();
			//new WinningTiles(tiles[0][col],tiles[1][col] ,tiles[2][col]);

			endGame(winner,new WinningTiles(tiles[0][col],tiles[1][col] ,tiles[2][col]));

			//System.out.println("here in checkcolsforwinner");

			return;	
			}
			
		}
		
	}

	private void checkrowsforWinner() {
		for(int rows = 0 ; rows <3;rows++) {
			if(tiles[rows][0].getvalue().equals(tiles[rows][1].getvalue())
			&& tiles[rows][0].getvalue().equals(tiles[rows][2].getvalue())
			&& !tiles[rows][0].getvalue().isEmpty()){
				
			String winner = tiles[rows][0].getvalue();
			// new WinningTiles(tiles[rows][0],tiles[rows][1] ,tiles[rows][2]);
			endGame(winner,new WinningTiles(tiles[rows][0],tiles[rows][1] ,tiles[rows][2]));

			//System.out.println("here in checkrowsforWinner");

			return;	
			}
			
		}
		
	}
	
	private void endGame(String winner,WinningTiles winningTiles) {
		isEndOfGame=true;
		InfoCentre.updateMessage("Player" + winner + "won");
		drawWinningLine(winningTiles);
		InfoCentre.showStartButton();
	}
	
	
	
	public void drawWinningLine(WinningTiles winningTiles) {

		winningLine.setStartX(winningTiles.start.getStackPane().getTranslateX());
		winningLine.setStartY(winningTiles.start.getStackPane().getTranslateY());
		winningLine.setTranslateX(winningTiles.middle.getStackPane().getTranslateX());
		winningLine.setTranslateY(winningTiles.middle.getStackPane().getTranslateY());
		winningLine.setEndX(winningTiles.end.getStackPane().getTranslateX());
		winningLine.setEndX(winningTiles.end.getStackPane().getTranslateY());
		winningLine.setVisible(true);
		StackPane lin = new StackPane(winningLine);
		pane.getChildren().add(lin);

		
	}
	
	public class WinningTiles{
		
		Tile start ; 
		Tile middle; 
		Tile end ; 
		
		public WinningTiles(Tile start,Tile middle,Tile end) {
			
			this.start = start; 
			this.middle = middle; 
			this.end = end ; 
			
		}
		
		
	}

	public class Tile {
		
		private StackPane pane ; 
		private Label label ; 

		public Tile() {
			
			pane = new StackPane();
			pane.setMinSize(100,100);
			
			Rectangle border = new Rectangle();
			
			border.setHeight(100);
			border.setWidth(100);
			
			border.setFill(Color.TRANSPARENT);
			border.setStroke(Color.BLACK);
			
			pane.getChildren().add(border);
			
			 label = new Label ("");
			
			label.setAlignment(Pos.CENTER);
			label.setFont(Font.font(24));
			pane.getChildren().add(label);
			
			pane.setOnMouseClicked(event ->{
				if(label.getText().isEmpty() && !isEndOfGame) {
					
					label.setText(getPlayerTurn());
					changePlayerTurn();
					checkforWinner();
					
				}
			});
			
		}
		
		public StackPane getStackPane() {
			return pane ;
		}
		public String getvalue() {
			return label.getText();
		}
		public void setvalue(String value) {
			 label.setText(value);
		}
		
	}
	
	
}
