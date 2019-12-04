import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.LinkedList;

public class MyCircle extends Circle {

    private Integer levelNode;
    private boolean hasChildren;
    private LinkedList<MyCircle> sxList = new LinkedList<>();
    private LinkedList<MyCircle> ccList = new LinkedList<>();
    private LinkedList<MyCircle> dxList = new LinkedList<>();



    public MyCircle(double centerX, double centerY, double radius, Integer lvl) {
        super(centerX, centerY, radius);
        levelNode = lvl;
        sxList = null;
        ccList = null;
        dxList = null;
        hasChildren = false;
    }

    public Integer getLevelNode(){
        return levelNode;
    }

    public LinkedList<MyCircle> getSxList(){
        return sxList;
    }
    public LinkedList<MyCircle> getCcList(){
        return ccList;
    }
    public LinkedList<MyCircle> getDxList(){
        return dxList;
    }
    public boolean getHasChildren(){
        return hasChildren;
    }

    private void setHasChildren(){
        if((sxList == null)&&(ccList==null)&&(dxList==null)) hasChildren = true;
        else hasChildren = false;
    }





}

