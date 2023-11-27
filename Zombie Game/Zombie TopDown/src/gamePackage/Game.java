package gamePackage;
import javafx.stage.Stage;

import javafx.geometry.*;

import java.io.File;
import java.util.ArrayList;

import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Game extends Application {
	int ammo = 30;
	int php = 100;
	int turns = 2;
	int tempzx;
	int tempzy;
	int wave = 1;
	int ax;
	int ay;
	int score= 0;
	int firstStore = 0;
	
	Label scoreLeft = new Label ("Score:   " + score);
	Label sorry = new Label("");
	Label DamageLabel = new Label("");

	int maxsize = 30;
	int size = 30;
	int change = 2;

	Label ammoLabel = new Label("Ammo:  "+ ammo + "   Turns Left:  " + turns + "\nCurrent Weapon:   Pistol");
	Label hpLabel = new Label("Health:  "+ php + "   Score:  " + score +  "\nWave:   " + wave);

	Tile tile[][] = new Tile[maxsize][maxsize/change];
	int[][] Pos = new int[maxsize][maxsize/change];
	BorderPane bp = new BorderPane();
	BorderPane dbp = new BorderPane();

	int playery = 7;
	int playerx = 15;
	Player player = new Player(playerx, playery);
	VBox menu = new VBox();
	HBox up = new HBox();
	HBox down = new HBox();
	VBox left = new VBox();
	VBox right = new VBox();
	GridPane gp = new GridPane();
	GridPane store = new GridPane();
	GridPane gameover = new GridPane();


	ArrayList<Zombie> zz = new ArrayList<Zombie>();

	public static void main(String[] args) {
		launch(args);

	}
	public void start(Stage stage){


		Pos[playerx][playery] = 2;
		//Pos[2][2] = 1;
		moveButtons();

		bp.setTop(up);
		bp.setLeft(left);
		bp.setBottom(down);
		bp.setRight(right);
		bp.setCenter(gp);
		dbp.setTop(menu);
		dbp.setCenter(bp);

		MenuBar menuBar = new MenuBar();
		Menu file = new Menu("File");
		Menu edit = new Menu("Edit");
		Menu weapons = new Menu("Weapon");
		menuBar.getMenus().addAll(file, edit, weapons);
		menu.getChildren().add(menuBar);
		
		MenuItem Knife = new MenuItem("Knife");
		MenuItem Pistol = new MenuItem("Pistol");
		MenuItem Shotgun = new MenuItem("Shotgun");
		Knife.setOnAction(e -> {
			player.KnifeSet();
			ammoLabel.setText("Ammo:  "+ ammo + "   Turns Left:  " + turns + "\nCurrent Weapon:   " + player.WeaponString());
		});
		Pistol.setOnAction(e -> {
			player.PistolSet();
			ammoLabel.setText("Ammo:  "+ ammo + "   Turns Left:  " + turns + "\nCurrent Weapon:   " + player.WeaponString());
		});
		Shotgun.setOnAction(e -> {
			player.ShotgunSet();
			ammoLabel.setText("Ammo:  "+ ammo + "   Turns Left:  " + turns + "\nCurrent Weapon:   " + player.WeaponString());
		});
		weapons.getItems().add(Knife);
		weapons.getItems().add(Pistol);
		weapons.getItems().add(Shotgun);

		MenuItem newgame = new MenuItem("New");
		file.getItems().add(newgame);


		newgame.setOnAction( e -> {
			score = 0;
			turns = 2;
			wave = 1;
			player.reset();
			dbp.getChildren().clear();
			dbp.setCenter(bp);
			dbp.setTop(menu);
			zz.removeAll(zz);
			doTheButtons();
			NewWave();
			ammoLabel.setText("Ammo:  "+ ammo + "   Turns Left:  " + turns + "\nCurrent Weapon:   " + player.WeaponString());
			hpLabel.setText("Health:  "+ php + "   Score:  " + score +  "\nWave:   " + wave);
			//tbd
		});

		MenuItem save = new MenuItem("Save");
		file.getItems().add(save);

		//Lambda Function
		save.setOnAction( e -> {
			System.out.println("Save");
		});


		MenuItem open = new MenuItem("Open");
		file.getItems().add(open);

		//Lambda Function
		open.setOnAction( e -> {
			System.out.println("Open What");
		});
		doTheButtons();
		for (int q = 0; q < 4; q++){
			Zombie z = new Zombie();
			tempzx = z.getX();
			tempzy = z.getY();
			tile[tempzx][tempzy].setHp(z.getHP());
			tile[tempzx][tempzy].change(1);

			zz.add(z);
			//System.out.println(zz.get(q));
		}

		gp.setPadding(new Insets(6,6,6,6));
		Scene scene = new Scene(dbp);
		stage.setMaxHeight(750);
		stage.setMinHeight(750);
		stage.setMaxWidth(1170);
		stage.setMinWidth(1170);
		stage.setScene(scene);
		stage.show();
	}

	public void NewTurn(){
		
		php = player.getHp();

		ammo = player.getAmmo();
		if( ammo <= 0){
			player.KnifeSet();
		}
		ammoLabel.setText("Ammo:  "+ ammo + "   Turns Left:  " + turns + "\nCurrent Weapon:   " + player.WeaponString());

		for(int r = 0; r < zz.size(); r ++){
			zz.get(r).move(playery, playerx, tile, player);
		}
		turns = 2;
		php = player.getHp();
		ammoLabel.setText("Ammo:  "+ ammo + "   Turns Left:  " + turns + "\nCurrent Weapon:   " + player.WeaponString());
		hpLabel.setText("Health:  "+ php + "   Score:  " + score +  "\nWave:   " + wave);
		if(zz.isEmpty() == true){
			wave ++;
			NewWave();
		}
		if( php < 0){
			dbp.getChildren().remove(bp);
			dbp.setCenter(gameover);
			gameover.autosize();
			//store.setGridLinesVisible(true);
			gameover.setMinSize(200, 200);
			gameover.setVgap(25);
			gameover.setHgap(100);
			gameover.setAlignment(javafx.geometry.Pos.CENTER);
			gameover.add(new Label("Game Over"), 0, 0);
		}
	}
	public void NewWave(){
		if (wave % 4 == 0){
			Store();
		}
		zz.removeAll(zz);
		int d = (int) (Math.random()*3) +1;
		//int d =1;
		if (d == 1)
			while(d == 1){
				ax = ((int) (Math.random() * 30));
				ay = ((int) (Math.random() * 15));
				if (tile[ax][ay].state == 0){
					tile[ax][ay].change(3);
					Pos[ax][ay] = 3;
					break;
				}
			}
		if (d == 2)
			while(d == 2){
				ax = ((int) (Math.random() * 30));
				ay = ((int) (Math.random() * 15));
				if (tile[ax][ay].state == 0){
					tile[ax][ay].change(4);
					Pos[ax][ay] = 4;
					break;
				}
			}

		for (int q = 0; q < ((wave * 3)/2); q++){
			if (wave == 2){
				Zombie z = new Zombie();
				tempzx = z.getX();
				tempzy = z.getY();
				tile[tempzx][tempzy].setHp(z.getHP());
				tile[tempzx][tempzy].change(1);

				zz.add(z);
			}
			if(wave < 8){
				Zombie z = new Zombie(wave);
				tempzx = z.getX();
				tempzy = z.getY();
				tile[tempzx][tempzy].setHp(z.getHP());
				tile[tempzx][tempzy].change(1);

				zz.add(z);
			} else {
				Zombie z = new Zombie(10);
				tempzx = z.getX();
				tempzy = z.getY();
				tile[tempzx][tempzy].setHp(z.getHP());
				tile[tempzx][tempzy].change(1);

				zz.add(z);
			}

		}
	}

	public void doTheButtons(){

		for(int i = 0; i < maxsize; i++){
			for(int j = 0; j < maxsize/change; j++){
				tile[i][j] = new Tile(Pos[i][j]);
				int a = i;
				int b = j;
				tile[i][j].addEventFilter(MouseEvent.MOUSE_PRESSED,e -> {

					if (turns == 0){
						NewTurn();

					}

					if(tile[a][b].getState() == 0){

					}
					if(tile[a][b].getState() == 1){
						
						

						
							
							for(int q = 0; q < zz.size(); q++){
								if(zz.get(q).getX() == a && zz.get(q).getY() == b){
									if (ammo > 0){
									if(player.getWeapon() == 2){
										if((((zz.get(q).getX() - playerx) * (zz.get(q).getX() - playerx) / 2) <= 2) && (((zz.get(q).getY() - playery) * (zz.get(q).getY() - playery) / 2) <= 2)){
											zz.get(q).Shot(player.damage());
											turns --;

										} else {
											zz.get(q).Shot((player.damage()) / 2);
											turns --;

										}
									} else if (player.getWeapon() == 1) {
									zz.get(q).Shot(player.damage());
									turns --;

									}
									}
									if(player.getWeapon() == 0){
										if((((zz.get(q).getX() - playerx) * (zz.get(q).getX() - playerx) / 2) <= 1) && (((zz.get(q).getY() - playery) * (zz.get(q).getY() - playery) / 2) <= 1)){
											zz.get(q).Shot(player.damage());
											turns --;
											Sound("knife");
										}
									} else {
										
									}
									/*if(player.getWeapon() == 2 && (((zz.get(q).getX() - playerx) * (zz.get(q).getX() - playerx) / 2) <= 2) && (((zz.get(q).getY() - playery) * (zz.get(q).getY() - playery) / 2) <= 2)){
										if(playerx > zz.get(q).getX()){
											tile[zz.get(q).getX()][zz.get(q).getY()].change(0);
											zz.get(q).setX(zz.get(q).getX() - 1);
											tile[zz.get(q).getX()][zz.get(q).getY()].change(1);
										} else{
											tile[zz.get(q).getX()][zz.get(q).getY()].change(0);
											zz.get(q).setX(zz.get(q).getX() + 1);
											tile[zz.get(q).getX()][zz.get(q).getY()].change(1);
										}
										if(playery > zz.get(q).getY()){
											tile[zz.get(q).getX()][zz.get(q).getY()].change(0);
											zz.get(q).setY(zz.get(q).getY() - 1);
											tile[zz.get(q).getX()][zz.get(q).getY()].change(1);
										} else{
											tile[zz.get(q).getX()][zz.get(q).getY()].change(0);
											zz.get(q).setY(zz.get(q).getY() + 1);
											tile[zz.get(q).getX()][zz.get(q).getY()].change(1);
										}
									}*/  //for knockback with shotgun, not working.
									tile[a][b].setHp(zz.get(q).getHP());

									if(zz.get(q).alive() == false){
										score += zz.get(q).getScore();
										zz.remove(q);

										tile[a][b].change(0);
									} else {
										tile[a][b].change(1);
									}
								}
							}
							
							if(player.getWeapon() == 1){
								Sound("pistol");
							}
							if(player.getWeapon() == 2){
								Sound("shotgun");
							}

							
							
							if (player.getWeapon() != 0){
							player.shoot();
							}
							ammo = player.getAmmo();
							ammoLabel.setText("Ammo:  "+ ammo + "   Turns Left:  " + turns + "\nCurrent Weapon:   " + player.WeaponString());
							
							if(turns == 0)
								NewTurn();
							
							
						}
					
					if(tile[a][b].getState() == 2){

					}
					

				});
				gp.add(tile[i][j], i, j);
			}
		}
	}
	public void moveButtons(){
		Button aup = new Button();
		Button adown = new Button();
		Button aleft = new Button();
		Button aright = new Button();
		aup.setGraphic(new ImageView(new Image("/images/Arrow Up.png")));
		adown.setGraphic(new ImageView(new Image("/images/Arrow Down.png")));
		aleft.setGraphic(new ImageView(new Image("/images/Arrow Left.png")));
		aright.setGraphic(new ImageView(new Image("/images/Arrow Right.png")));

		aleft.setOnAction(e -> {

			if (playerx > 0 && tile[playerx - 1][playery].getState() != 1){
				if (tile[playerx-1][playery].getState() == 3){
					player.gainAmmo();
					ammo = player.getAmmo();
					ammoLabel.setText("Ammo:  "+ ammo + "   Turns Left:  " + turns + "\nCurrent Weapon:   " + player.WeaponString());
				}
				if (tile[playerx-1][playery].getState() == 4){

					player.gainHp();
					php = player.getHp();
					hpLabel.setText("Health:  "+ php + "   Score:  " + score +  "\nWave:   " + wave);
				}
				Pos[playerx][playery] = 0;
				tile[playerx][playery].change(0);
				Pos[playerx - 1][playery] = 2;
				tile[playerx -1][playery].change(2);
				playerx --;
				turns --;
				ammoLabel.setText("Ammo:  "+ ammo + "   Turns Left:  " + turns + "\nCurrent Weapon:   " + player.WeaponString());
				player.setX(playerx);
			} else {
				System.out.println("here");
			}
			if (turns == 0){
				NewTurn();
			} 
		});

		aright.setOnAction(e -> {
			if (playerx < maxsize - 1 && tile[playerx + 1][playery].getState() != 1){
				if (tile[playerx+1][playery].getState() == 3){
					player.gainAmmo();
					ammo = player.getAmmo();
					ammoLabel.setText("Ammo:  "+ ammo + "   Turns Left:  " + turns + "\nCurrent Weapon:   " + player.WeaponString());
				}
				if (tile[playerx+1][playery].getState() == 4){

					player.gainHp();
					php = player.getHp();
					hpLabel.setText("Health:  "+ php + "   Score:  " + score +  "\nWave:   " + wave);
				}
				Pos[playerx][playery] = 0;
				tile[playerx][playery].change(0);
				Pos[playerx + 1][playery] = 2;
				tile[playerx + 1][playery].change(2);
				playerx ++;
				turns --;
				ammoLabel.setText("Ammo:  "+ ammo + "   Turns Left:  " + turns + "\nCurrent Weapon:   " + player.WeaponString());
				player.setX(playerx);
			} else {
				System.out.println("here");
			}
			if (turns == 0){
				NewTurn();
			}
		});

		aup.setOnAction(e -> {
			if (playery > 0 && tile[playerx][playery - 1].getState() != 1){
				if (tile[playerx][playery-1].getState() == 3){

					player.gainAmmo();
					ammo = player.getAmmo();
					ammoLabel.setText("Ammo:  "+ ammo + "   Turns Left:  " + turns + "\nCurrent Weapon:   " + player.WeaponString());
				}
				if (tile[playerx][playery-1].getState() == 4){

					player.gainHp();
					php = player.getHp();
					hpLabel.setText("Health:  "+ php + "   Score:  " + score +  "\nWave:   " + wave);
				}
				Pos[playerx][playery] = 0;
				tile[playerx][playery].change(0);
				Pos[playerx][playery - 1] = 2;
				tile[playerx][playery - 1].change(2);
				playery --;
				turns --;
				ammoLabel.setText("Ammo:  "+ ammo + "   Turns Left:  " + turns + "\nCurrent Weapon:   " + player.WeaponString());
				player.setY(playery);
			} else {
				System.out.println("here");
			}
			if (turns == 0){
				NewTurn();
			}
		});

		adown.setOnAction(e -> {
			if (playery < (maxsize/change) - 1 && tile[playerx][playery + 1].getState() != 1){
				if (tile[playerx][playery +1].getState() == 3){
					player.gainAmmo();
					ammo = player.getAmmo();
					ammoLabel.setText("Ammo:  "+ ammo + "   Turns Left:  " + turns + "\nCurrent Weapon:   " + player.WeaponString());
				}
				if (tile[playerx][playery+1].getState() == 4){

					player.gainHp();
					php = player.getHp();
					hpLabel.setText("Health:  "+ php + "   Score:  " + score +  "\nWave:   " + wave);
				}
				Pos[playerx][playery] = 0;
				tile[playerx][playery].change(0);
				Pos[playerx][playery + 1] = 2;
				tile[playerx][playery + 1].change(2);
				playery ++;
				turns --;
				ammoLabel.setText("Ammo:  "+ ammo + "   Turns Left:  " + turns + "\nCurrent Weapon:   " + player.WeaponString());
				player.setY(playery);

			} else {
				System.out.println("here");
			}
			if (turns == 0){
				NewTurn();
			}
		});

		up.setSpacing(maxsize * 6);
		up.getChildren().addAll(hpLabel, aup, ammoLabel);
		down.getChildren().add(adown);
		left.getChildren().add(aleft);
		right.getChildren().add(aright);
		up.setAlignment(javafx.geometry.Pos.CENTER);
		down.setAlignment(javafx.geometry.Pos.CENTER);
		left.setAlignment(javafx.geometry.Pos.CENTER);
		right.setAlignment(javafx.geometry.Pos.CENTER);
	}

	public static void Sound(String s){
		String musicFile = s + ".wav";   

		Media sound = new Media(new File(musicFile).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.play();
	}

	public void Store(){
		dbp.getChildren().remove(bp);
		scoreLeft.setText("Score:   " + score);
		sorry.setText("");
		DamageLabel.setText("Increase Damage (" + 30*(player.getbonusdamage()+1) + ")");
		if(firstStore == 0){
		store.autosize();
		//store.setGridLinesVisible(true);
		store.setMinSize(200, 200);
		store.setVgap(25);
		store.setHgap(100);

		
		Label storeLabel = new Label("Hello and Welcome to the Store, \nYou can spend your score to upgrade your stats.");
		
		Button hpb = new Button("Purchase");
		Button ammob = new Button("Purchase");
		Button damageb = new Button("Purchase");
		Button exit = new Button("Exit");
		hpb.setOnAction(e -> {
			if (score >= 15){
				score -= 15;
				player.gainMaxHp();
				scoreLeft.setText("Score:   " + score);
				sorry.setText("Your Max Health is now " + player.getMaxHp());
				player.gainHp();
			} else {
				sorry.setText("You dont have enough score for that");
			}
		});
		ammob.setOnAction(e -> {
			if (score >= 10){
				score -= 10;
				player.gainMaxAmmo();
				scoreLeft.setText("Score:   " + score);
				sorry.setText("Your Max Ammo is now " + player.getMaxAmmo());
				player.gainAmmo();
			} else {
				sorry.setText("You dont have enough score for that");
			}
		});
		damageb.setOnAction(e -> {
			if (score >= 30*(player.getbonusdamage() +1)){
				score -= 30*(player.getbonusdamage() +1);
				player.gainDamage();
				scoreLeft.setText("Score:   " + score);
				sorry.setText("Your Bonus Damage is now " + (player.damage() - 1));
				DamageLabel.setText("Increase Damage (" + 30*(player.getbonusdamage()+1) + ")");
			} else {
				sorry.setText("You dont have enough score for that");
			}
		});
		exit.setOnAction(e -> {
			dbp.getChildren().remove(store);
			dbp.getChildren().add(bp);
		});
		
		store.add(storeLabel, 0, 0);
		store.add(scoreLeft, 0, 1);
		store.add(sorry, 0, 7);
		store.add(new Label("Increase Max Health (15)"),0,2);
		store.add(new Label("Increase Max Ammo (10)"),0,4);
		store.add(DamageLabel,0,6);
		store.add(hpb,1,2);
		store.add(ammob,1,4);
		store.add(damageb,1,6);
		store.add(exit, 1,7);
		store.setAlignment(javafx.geometry.Pos.CENTER);
		//bp.getChildren().add(store);
		firstStore ++;
}
		dbp.setCenter(store);
	}
}

