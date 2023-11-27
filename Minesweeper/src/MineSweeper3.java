import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MineSweeper3 extends Application{
	int maxsize = 16;
	int minesAmount = 40;
	int mineFlag = minesAmount;
	int minesPlaced = 0;
	int[][] Mines = new int[maxsize][maxsize];
	Button face;
	ImageView  image0 ;
	int size = 30;
	int winAmount = (maxsize * maxsize) - minesAmount;
	int amountfound = 0;
	boolean gameStart = true;
	GridPane gp = new GridPane();
	HBox hbox = new HBox();
	Label MCB = new Label(" "+ mineFlag);
	boolean firstClick = true;
	int flagAround = 0;
	boolean start = true;
	boolean finished = false;
	int clickTimes = 0;
	boolean lose = false;
	public static void main(String[] args) {

		launch(args);

	}
	public void start(Stage stage){


		BorderPane bp = new BorderPane();

		hbox.setAlignment(Pos.CENTER_LEFT);
		face = new Button();


		if(amountfound >= winAmount){
			face.setGraphic(new ImageView(new Image("/images/face-win.png")));
		} else {
			face.setGraphic(new ImageView(new Image("/images/face-smile.png")));
		}


		hbox.getChildren().addAll(MCB,face);
		hbox.setSpacing(maxsize * 6);


		bp.setCenter(gp);
		bp.setTop(hbox);







		MineButton buttons[][] = new MineButton[maxsize][maxsize];
		//setup();

		doTheButtons(buttons);

		face.setOnAction(e -> {
			restart(buttons);
		});
		hbox.setPadding(new Insets(12,maxsize * 5,12,maxsize * 5));
		gp.setPadding(new Insets(0,12,12,12));
		stage.setTitle("MineSweeper");
		stage.setScene(new Scene(bp));
		stage.sizeToScene();
		stage.show();
	}

	public void restart(MineButton[][] buttons){
		//gameStart = true;
		gp.setDisable(false);
		lose = false;
		clickTimes = 0;
		face.setGraphic(new ImageView(new Image("/images/face-smile.png")));
		amountfound = 0;
		minesPlaced = 0;
		start = true;
		mineFlag = minesAmount;
		MCB.setText(" " + mineFlag);
		//setup(-1, -1);
		doTheButtons(buttons);

		face.setOnAction(e -> {
			restart(buttons);
		});
	}


	public void empty(int a, int b, MineButton[][] buttons, boolean flag){


		if (buttons[a][b].getClicked()== 1 && flag == false){
			return;
		}
		//System.out.println("Empty called" + a + b);
		buttons[a][b].setClicked(1);
		amountfound ++;
		try{
			buttons[a][b+1].uncover();//bottom
			//amountfound ++;

		} catch (IndexOutOfBoundsException e){

		}
		try{
			buttons[a][b-1].uncover();//top
			//amountfound ++;
		} catch (IndexOutOfBoundsException e){

		}
		try{
			buttons[a+1][b+1].uncover();//bottom right
			//amountfound ++;
		} catch (IndexOutOfBoundsException e){

		}
		try{
			buttons[a-1][b+1].uncover();//bottom left
			//amountfound ++;
		} catch (IndexOutOfBoundsException e){

		}
		try{
			buttons[a+1][b-1].uncover();//top right
			//amountfound ++;
		} catch (IndexOutOfBoundsException e){

		}
		try{
			buttons[a-1][b-1].uncover();//top left
			//amountfound ++;
		} catch (IndexOutOfBoundsException e){

		}
		try{
			buttons[a+1][b].uncover();//right
			//amountfound ++;
		} catch (IndexOutOfBoundsException e){

		}
		try{
			buttons[a-1][b].uncover();//left
			//amountfound ++;
		} catch (IndexOutOfBoundsException e){

		}
		int i = -1;
		try{
			for(i = -1; i < 2; i++){
				int j = -1;
				try{
					for(j = -1; j < 2; j++){
						if(buttons[a+i][b+j].state() == 0){

							empty(a+i,b+j, buttons, false);

						}
					}
				} catch (IndexOutOfBoundsException e){
					j ++;
				}

			}
		} catch (IndexOutOfBoundsException e){
			i ++;
		}

	}
	public void setup (int r, int c){

		//System.out.println(r + "  " + c);
		for(int y = 0; y < maxsize; y++){
			for(int x = 0; x < maxsize; x++){
				Mines[y][x] = 0;

			}
		}
		while (minesPlaced < minesAmount){
			int q = (int) (Math.random() * maxsize);
			int p = (int) (Math.random() * maxsize);
			if( q >= r -2 && p >= c -2 && q <= r +2 && p <= c + 2){
				continue;
			} else {
				if (Mines[q][p] == 0){

					Mines[q][p] = -1;
					minesPlaced += 1;
				}
			}
		}
	


		for(int y = 0; y < maxsize; y++){
			for(int x = 0; x < maxsize ; x++){
				try{
					if (Mines[y][x] == -1){
						continue;
					}
				} catch (IndexOutOfBoundsException e){

				}
				try {
					if(x > 0 && y > 0 && Mines[y-1][x-1] == -1){
						//bottom right
						Mines[y][x] += 1;
					}
				} catch (IndexOutOfBoundsException e){

				}
				try{
					if( y > 0 && Mines[y-1][x] == -1){
						//bottom
						Mines[y][x] += 1;
					}
				} catch (IndexOutOfBoundsException e){

				}
				try{
					if(x > 0 && Mines[y][x-1] == -1){
						//right
						Mines[y][x] += 1;
					}
				} catch (IndexOutOfBoundsException e){

				}
				try{
					if(x < maxsize && y > 0 && Mines[y-1][x+1] == -1){
						//bottom left
						Mines[y][x] += 1;
					}
				} catch (IndexOutOfBoundsException e){

				}
				try{
					if(x < maxsize && Mines[y][x+1] == -1){
						//left
						Mines[y][x] += 1;
					}
				} catch (IndexOutOfBoundsException e){

				}
				try{
					if(x > 0 && y < maxsize && Mines[y+1][x-1] == -1){
						//top right
						Mines[y][x] += 1;
					}
				} catch (IndexOutOfBoundsException e){

				}
				try{
					if( y <maxsize && Mines[y+1][x] == -1){
						//top
						Mines[y][x] += 1;
					}
				} catch (IndexOutOfBoundsException e){

				}
				try{
					if(x < maxsize && y < maxsize && Mines[y+1][x+1] == -1){
						//top left
						Mines[y][x] += 1;
					}
				} catch (IndexOutOfBoundsException e){

				}
				//System.out.print(Mines[y][x] +" ");
			}
		}
		//System.out.println();
		/*for(int y = 0; y < maxsize; y++){
			for(int x = 0; x < maxsize; x++){
				if(Mines[y][x] == -1){
					System.out.print("*");
				} else{
				System.out.print(Mines[y][x]);
				}
			}
			System.out.println();
		}*/
	}
	public void doTheButtons(MineButton[][] buttons){

		for(int i = 0; i < maxsize; i++){
			for(int j = 0; j < maxsize; j++){

				//int q = Mines[i][j];

				buttons[i][j] = new MineButton(Mines[i][j]);
				Button k = buttons[i][j];
				int a = i;
				int b = j;
				flagAround = 0;

				buttons[i][j].addEventFilter(MouseEvent.MOUSE_PRESSED,e -> {
					int q = Mines[a][b];
					if (e.isPrimaryButtonDown()) {
						if(start == true){
							setup(a,b);
							//System.out.println("called");
							
							for(int y = 0; y < maxsize; y++){
								for(int x = 0; x < maxsize; x++){
									buttons[y][x].setValue(Mines[y][x]);

								}
								
							}
							start = false;
						}

						q = Mines[a][b];
						if (buttons[a][b].getClicked() == 0){


							if (q == -1){
								image0 = new ImageView(new Image("/images/mine-red.png"));
								lose = true;
								for(int y = 0; y < maxsize; y++){
									for(int x = 0; x < maxsize ; x++){
										if (Mines[y][x] == -1){
											if(y == a && x == b){

											}  else{
												buttons[y][x].setGraphic(new ImageView(new Image("/images/mine-grey.png")));
											}
										}
										if(buttons[y][x].getflagged() == true && Mines[y][x] != -1){
											buttons[y][x].setGraphic(new ImageView(new Image("/images/mine-misflagged.png")));
										}
									}
								}

							}else {

								image0 = new ImageView(new Image("/images/" + q + ".png"));
								if(buttons[a][b].getClicked() == 0){
									amountfound ++;
									//buttons[a][b].setClicked(1);
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
							if(q == 0){

								empty(a, b, buttons, false);
								for(int o = 0; o < maxsize; o++){
									for(int p = 0; p < maxsize; p++){
										if(buttons[o][p].getClicked() == 1 && buttons[o][p].state() == 0 && buttons[o][p].getScanned() == 0){
											buttons[o][p].setScanned(1);
											empty(o,p,buttons, false);

										}
									}
								}
							} else {
								buttons[a][b].setClicked(1);
							}
						}	
					} else if (e.isSecondaryButtonDown()) {

						if(buttons[a][b].getClicked() == 0 && buttons[a][b].getScanned() == 0){
							if (mineFlag > 0){
								mineFlag --;
								image0 = new ImageView(new Image("/images/flag.png"));
								image0.setFitWidth(size);
								image0.setFitHeight(size);
								k.setGraphic(image0);
								buttons[a][b].setClicked(1);
								buttons[a][b].setflagged(true);
							}
						} else if (buttons[a][b].getScanned() == 0 && buttons[a][b].getflagged() == true) {
							mineFlag++;
							image0 = new ImageView(new Image("/images/cover.png"));
							image0.setFitWidth(size);
							image0.setFitHeight(size);
							k.setGraphic(image0);
							buttons[a][b].setClicked(0);
							buttons[a][b].setflagged(false);
						}
					} else if( e.isMiddleButtonDown()){
						flagAround = 0;
						int t = -1;
						try{
							for(t = -1; t < 2; t++){
								int r = -1;
								try{
									for(r = -1; r < 2; r++){
										if(buttons[a- t][b - r].getflagged() == true){
											flagAround ++;

										}
									}
								} catch (IndexOutOfBoundsException zz){
									r ++;
								}

							}
						} catch (IndexOutOfBoundsException xx){
							t ++;
						}
						if (flagAround == q){
							amountfound += flagAround;
							empty(a,b,buttons, true);

						}
					}

					MCB.setText(" " + mineFlag);
					finished = true;
					for(int g = 0; g < maxsize; g++){
						for(int h = 0; h < maxsize; h++){
							if((buttons[g][h].getGud().equals("Cover") && Mines[g][h] == -1) || ((buttons[g][h].getGud().equals("NotCover") || buttons[g][h].getClicked() == 1 || buttons[g][h].getScanned() == 1) && Mines[g][h] != -1)){

							} else {
								finished = false;
							}
						}
					}

					//System.out.println(buttons[a][b].getGud() + "	" + buttons[a][b].getClicked() + start + "  " + buttons[a][b].state());
					if (finished == true){
						face.setGraphic(new ImageView(new Image("/images/face-win.png")));
						for(int g = 0; g < maxsize; g++){
							for(int h = 0; h < maxsize; h++){
								buttons[g][h].setClicked(1);
							}
						}
					}
					if (lose == true){
						for(int g = 0; g < maxsize; g++){
							for(int h = 0; h < maxsize; h++){
								buttons[g][h].setClicked(1);
							}
						}
					}

				});



				gp.add(buttons[i][j], i, j);
			}
		}

		face.setOnAction(e -> {
			restart(buttons);
		});
	}
}


