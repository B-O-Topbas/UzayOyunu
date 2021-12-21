package UzayOyunu;

import java.awt.geom.Rectangle2D;

public class Target extends Rectangle2D.Float {
    public Target(float x,float y,float width,float height){
        setFrame(x,y,width,height);
    }
    public  void addX(float x){
        this.x+=x;
    }
}
