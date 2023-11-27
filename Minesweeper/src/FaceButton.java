import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

class FaceButton extends Button implements EventHandler<ActionEvent>{
	  ImageView imageFace;
	public FaceButton(int x){
		int state = x;
		int size = 70;

		imageFace = (new ImageView(new Image("/images/face-smile.png")));
		switch(state){
		case 0:
			imageFace = (new ImageView(new Image("/images/face-smile.png")));
			break;
		case 1:
			imageFace = (new ImageView(new Image("/images/face-win.png")));
			break;
		case 2:
			imageFace = (new ImageView(new Image("/images/face-dead.png")));
			break;
		}
		imageFace.setFitWidth(size);
		imageFace.setFitHeight(size);

		setGraphic(imageFace);
	}

	public void setFace(int x) {
		int state = x;
		int size = 70;
		switch(state){
		case 0:
			imageFace = (new ImageView(new Image("/images/face-smile.png")));
			break;
		case 1:
			imageFace = (new ImageView(new Image("/images/face-win.png")));
			break;
		case 2:
			imageFace = (new ImageView(new Image("/images/face-dead.png")));
			break;
		}
		imageFace.setFitWidth(size);
		imageFace.setFitHeight(size);

	
	}

	@Override
	public void handle(ActionEvent event) {

		setGraphic(imageFace);
	}

}
