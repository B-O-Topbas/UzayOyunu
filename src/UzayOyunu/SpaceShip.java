package UzayOyunu;

import java.awt.geom.Rectangle2D;

public class SpaceShip extends Rectangle2D.Float {
    public SpaceShip(float x,float y,float width,float height){
        setFrame(x,y,width,height);
    }
    public void addX(float x){
        this.x+=x;
    }
    public void addY(float y){
        this.y+=y;
    }
}
