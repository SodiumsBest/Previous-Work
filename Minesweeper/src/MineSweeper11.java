import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MineSweeper11 extends Application{
	int maxsize = 5;
	int minesAmount = 3;
	int minesPlaced = 0;
	int[][] Mines = new int[maxsize + 1][maxsize + 1];
	Button face;
	ImageView  image0 ;
	int size = 30;
	int winAmount = (maxsize * maxsize) - minesAmount;
	int amountfound;
	public static void main(String[] args) {

		launch(args);

	}
	public void start(Stage stage){

		BorderPane bp = new BorderPane();
		HBox hbox = new HBox();
		hbox.setAlignment(Pos.CENTER);
		face = new Button();
		
		if(amountfound >= 1){
			face.setGraphic(new ImageView(new Image("/images/face-win.png")));
		} else {
			face.setGraphic(new ImageView(new Image("/images/face-smile.png")));
		}
		
		
		hbox.getChildren().add(face);

		GridPane gp = new GridPane();
		bp.setCenter(gp);
		bp.setTop(hbox);




		setup();


		MineButton buttons[][] = new MineButton[maxsize][maxsize];
		for(int i = 0; i < maxsize; i++){
			for(int j = 0; j < maxsize; j++){
				//amountfound = 0;
				int q = Mines[i][j];
				
				buttons[i][j] = new MineButton(Mines[i][j]);
				Button k = buttons[i][j];
				int a = i;
				int b = j;
				buttons[i][j].setOnAction(e -> {
					
					if (q == -1){
						image0 = new ImageView(new Image("/images/mine-red.png"));
						


					}else {
						image0 = new ImageView(new Image("/images/" + q + ".png"));
						if(buttons[a][b].getClicked() == 0){
						amountfound ++;
						buttons[a][b].setClicked(1);
						}
					
						if(amountfound >= winAmount){
							face.setGraphic(new ImageView(new Image("/images/face-win.png")));
						}
					}

					image0.setFitWidth(size);
					image0.setFitHeight(size);


					k.setGraphic(image0);
					if(q == -1){
						
						face.setGraphic(new ImageView(new Image("/images/face-dead.png")));
					}
					
				});
				
				gp.add(buttons[i][j], i, j);
			}
		}

		face.setOnAction(e -> {
			restart(buttons);
		});

		stage.setTitle("MineSweeper");
		stage.setScene(new Scene(bp));
		stage.sizeToScene();
		stage.show();
	}

	public void restart(MineButton[][] buttons){
		setup();
		amountfound = 0;
		face.setGraphic(new ImageView(new Image("/images/face-smile.png")));
		for(int i = 0; i < maxsize; i++){
			for(int j = 0; j < maxsize; j++){
				buttons[i][j].cover();
				buttons[i][j].setClicked(0);
			}
		}
	}
	public void setup (){
		
		
		for(int y = 0; y < maxsize; y++){
			for(int x = 0; x < maxsize; x++){
				Mines[y][x] = 0;

			}
		}
		/*while (minesPlaced < minesAmount){
			int q = (int) (Math.random() * maxsize);
			int p = (int) (Math.random() * maxsize);
			if (Mines[q][p] == 0){
				Mines[q][p] = -1;
				minesPlaced += 1;
				System.out.println("Help" + q + p);
			}
		}*/
		Mines[1][1] = -1;
		Mines[3][2] = -1;
		Mines[3][3] = -1;
		
		for(int y = 0; y < maxsize; y++){
			for(int x = 0; x < maxsize ; x++){
				if (Mines[y][x] == -1){
					continue;
				}
				if(x > 0 && y > 0 && Mines[y-1][x-1] == -1){
					//bottom right
					Mines[y][x] += 1;
				}
				if( y > 0 && Mines[y-1][x] == -1){
					//bottom
					Mines[y][x] += 1;
				}
				if(x > 0 && Mines[y][x-1] == -1){
					//right
					Mines[y][x] += 1;
				}
				if(x < maxsize && y > 0 && Mines[y-1][x+1] == -1){
					//bottom left
					Mines[y][x] += 1;
				}
				if(x < maxsize && Mines[y][x+1] == -1){
					//left
					Mines[y][x] += 1;
				}
				if(x > 0 && y < maxsize && Mines[y+1][x-1] == -1){
					//top right
					Mines[y][x] += 1;
				}
				if( y <maxsize && Mines[y+1][x] == -1){
					//top
					Mines[y][x] += 1;
				}
				if(x < maxsize && y < maxsize && Mines[y+1][x+1] == -1){
					//top left
					Mines[y][x] += 1;
				}
			}
		}
	}
}


