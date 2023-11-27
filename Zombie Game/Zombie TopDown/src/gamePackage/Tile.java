package gamePackage;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tile extends Button {
	double size = 30;
	int state; // 0 for empty, 1 for zombie, 2 for player, 3 for ammo, 4 for health
	ImageView image;
	int value;

	public Tile (int x){
		state = x;



		setMinWidth(size);
		setMaxWidth(size);
		setMinHeight(size);
		setMaxHeight(size);
		if (state <= 0)
			image = new ImageView(new Image("/images/Blank.png"));
		if (state == 1)
			image = new ImageView(new Image("/images/Zombie.png"));
		
		if (state == 2)
			image = new ImageView(new Image("/images/Player.png"));
		
		if (state == 3)
			image = new ImageView(new Image("/images/Ammo.png"));
		
		if (state == 4)
			image = new ImageView(new Image("/images/Health.png"));


		image.setFitWidth(size);
		image.setFitHeight(size);

		setGraphic(image);
	}
	
	public void change(int x){
		state = x;

		


		setMinWidth(size);
		setMaxWidth(size);
		setMinHeight(size);
		setMaxHeight(size);
		if (state <= 0)
			image = new ImageView(new Image("/images/Blank.png"));
		if (state == 1)
			image = new ImageView(new Image("/images/" + value + ".png"));
		
		if (state == 2)
			image = new ImageView(new Image("/images/Player.png"));
		
		if (state == 3)
			image = new ImageView(new Image("/images/Ammo.png"));
		
		if (state == 4)
			image = new ImageView(new Image("/images/Health.png"));

		image.setFitWidth(size);
		image.setFitHeight(size);

		setGraphic(image);
	}
	
	public int getState(){
		return state;
		
	}
	public void setHp(int x){
		value = x;
	}
}
