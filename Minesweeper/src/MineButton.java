import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

class MineButton extends Button{
	int state; //number of mines around, -1 for mine.
	int clicked = 0; // 0 is unclicked, 1 is clicked
	int scanned = 0; //0 = never mass uncovered, 1 = has.
	ImageView imageCover, image0 ;
	boolean flagged = false;
	double size = 30;
	public MineButton(int x){
		state = x;
		
		setMinWidth(size);
		setMaxWidth(size);
		setMinHeight(size);
		setMaxHeight(size);

		imageCover = new ImageView(new Image("/images/cover.png"));
		if (state == -1){
			image0 = new ImageView(new Image("/images/mine-red.png"));
			//FaceButton.setFace(2);


		}else {
			image0 = new ImageView(new Image("/images/" + state + ".png"));
		}


		imageCover.setFitWidth(size);
		imageCover.setFitHeight(size);
		image0.setFitWidth(size);
		image0.setFitHeight(size);


		setGraphic(imageCover);
	}
	
	public void setClicked(int x){
		clicked = x;
	} 
	
	public int getClicked(){
		return clicked;
	}
	
	public void cover(){
		setGraphic(imageCover);
		
		
	}
	public void uncover(){
		if(clicked == 0){
		setGraphic(image0);
		if (state > 0){
			clicked = 1;
			scanned = 1;
		}
		}
	}

	public int state(){
		return state;
	}

	public void setScanned(int x){
		scanned = x;
	} 
	
	public int getScanned(){
		return scanned;
	}
	public void setflagged(boolean x){
		flagged = x;
	}
	public boolean getflagged(){
		return flagged;
	}
	
	public String getGud(){
		if (clicked == 0 || flagged == true){
			return "Cover";
		}
		return "NotCover";
	}
	
	public void setValue(int x){
		state = x;
		if (state == -1){
			image0 = new ImageView(new Image("/images/mine-red.png"));
			//FaceButton.setFace(2);


		}else {
			image0 = new ImageView(new Image("/images/" + state + ".png"));
		}
	}

}