package UzayOyunu;

public class Bullet {
    public int x, y;

    public Bullet(int x,int y) {
        this.x=x;
        this.y=y;
    }
    public void addX(int x){
        this.x+=x;
    }
}
