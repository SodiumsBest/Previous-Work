package gamePackage;

public class Player
{
    // instance variables - replace the example below with your own
    private int x;
    private int y;
    private int hp;
    private int ammo;
    private int weapon;
    private boolean alive;
    private int damage;
    private int crit;
    private int grenade;
    boolean hasSg;
    int bonusdamage;
    String s;
    private int maxammo, maxhp;

    /**
     * Constructor for objects of class Player
     */
    public Player(int xx, int yy)
    {
        // initialise instance variables
        x = xx;
        y = yy;
        hp = 100;
        ammo = 30;
        alive = true;
        weapon = 1;
        damage = 1;
        crit = 10;
        grenade = 2;
        hasSg = false;
        maxammo = 60;
        maxhp = 100;
        bonusdamage = 0;
    }

    public void gainAmmo(){
    	
        ammo += 15;
        if( ammo > maxammo){
        	ammo = maxammo;
        }

    }

    public void gainHp(){
        hp += 20;
        if(hp > maxhp){
        	hp = maxhp;
        }

    }
    public void gainMaxHp(){
    	maxhp += 25;
    }
    public void gainMaxAmmo(){
    	maxammo += 15;
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

    public int getHp(){
        return hp;
    }
    
    public int getMaxHp(){
        return maxhp;
    }

    public int getAmmo(){
        return ammo;
    }
    
    public void reset(){
        ammo = 30;
        maxammo = 30;
        hp = 100;
        maxhp = 100;
        damage = 1;
        bonusdamage = 0;
    }
    
    public int getMaxAmmo(){
        return maxammo;
    }
    
    public void gainDamage(){
    	bonusdamage ++;
    }

    public int getWeapon(){
        return weapon;
    }
    
    public int getbonusdamage(){
    	return bonusdamage;
    }

    public int getGrenade(){
        return grenade;
    }

    public boolean hasAmmo(){
        if (ammo > 0){
            return true;

        } else {
            weapon = 0;
            return false;
        }

    }

    public void KnifeSet () {
        weapon = 0;
    }

    public void PistolSet () {
        weapon = 1;
    }

    public void ShotgunSet () {
        weapon = 2;
    }
    
    public void pickSg() {
        hasSg = true;
    }

    public boolean hasSG() {
        if (hasSg == true) {
            return true;
        } else {
            return false;
        }
    }

    public int damage()
    {
      
    	  damage = 1;
      
      if (weapon == 2){
    	  
    	  damage = 2;
      }
        return damage + bonusdamage;
    }

    public int grenade(int zy, int zx, char [][] field) {
        damage = 70;
        return damage;
    }

    public void hurt(int damage){
        if (damage > 0) {
            System.out.println("You have been bitten!");
        }
       
        hp -= damage;
        s = "hurt";
        Game.Sound(s);
    }

    public void shoot() {
      ammo--;
     

    }
    
    public String WeaponString(){
    	if (weapon == 0){
    		s = "Knife";
    	}
    	if (weapon == 1){
    		s = "Pistol";
    	}
    	if (weapon == 2){
    		s = "Shotgun";
    	}
    	return s;
    }
}