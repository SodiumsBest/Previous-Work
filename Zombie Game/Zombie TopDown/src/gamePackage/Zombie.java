package gamePackage;

public class Zombie
{

	private int x;
	private int y;
	private int hp;    
	private boolean alive;
	private int score;

	public Zombie()
	{

		x = ((int) (Math.random() * 30));
		y = ((int) (Math.random() * 15));
		hp = ((int) (Math.random() * 3) + 1);        
		alive = true;
		score = hp;
	}
	public Zombie(int wave)
	{

		x = ((int) (Math.random() * 30));
		y = ((int) (Math.random() * 15));
		hp = ((int) (Math.random() * wave/2) + 3);      
		alive = true;
		score = hp;
	}

	public boolean alive (){
		if (hp <= 0) {
			return false;
		} else {
			return true;
		}
	}

	public void move (int py,int px, Tile [][] tile, Player player) {
		//System.out.println(x + " " + y + " : " + px + " " + py);
		if ((px - x) > 0) {
			if (tile[x+1][y].getState() == 2){
				player.hurt((hp * 3));
			}
			if (tile[x+1][y].getState() == 0){
				tile[x][y].setHp(0);
				tile[x][y].change(0);
				x++;
				tile[x][y].setHp(hp);
				tile[x][y].change(1);
				//System.out.println("Move Right");
			}

		} else if ((px - x) < 0) {
			if (tile[x-1][y].getState() == 2){
				player.hurt((hp * 3));
			}
			if (tile[x-1][y].getState() == 0){
				tile[x][y].setHp(0);
				tile[x][y].change(0);

				x--;
				tile[x][y].setHp(hp);
				tile[x][y].change(1);
				//System.out.println("Move Left");
			}

		} 

		if ((py - y) > 0) {
			if (tile[x][y+1].getState() == 2){
				player.hurt((hp * 3));
			}
			if (tile[x][y + 1].getState() == 0){
				tile[x][y].setHp(0);
				tile[x][y].change(0);

				y++;
				tile[x][y].setHp(hp);
				tile[x][y].change(1);
				//System.out.println("Move Down");
			}

		} else if ((py - y) < 0) {
			if (tile[x][y-1].getState() == 2){
				player.hurt((hp * 3));
			}
			if (tile[x][y - 1].getState() == 0){
				tile[x][y].setHp(0);
				tile[x][y].change(0);

				y--;
				tile[x][y].setHp(hp);
				tile[x][y].change(1);
				//System.out.println("Move Up");
			}

		} 
		//System.out.println(x + " " + y + " : " + px + " " + py);
	}

	public int getHP(){
		return hp;
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}

	public void setX(int xx){
		x = xx;
	}

	public void setY(int yy){
		y = yy;
	}

	public void Shot (int damage){
		hp -= damage;
		
		//return hp;
	}

	public int Attack (int py, int px) {
		if ((px == x) && (py == y)) {
			return 15;
		}else{
			return 0;
		}
	}

	public int getScore(){
		return score;
	}
}