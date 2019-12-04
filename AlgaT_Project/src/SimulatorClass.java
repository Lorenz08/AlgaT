
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;


public class SimulatorClass extends Main implements Initializable {



    /*  FIELDS */
    public static final double DEFAULT_CIRCLE = 8;
    @FXML private AnchorPane windowww;
    @FXML private RadioButton SX;
    @FXML private RadioButton CC;
    @FXML private RadioButton DX;

    LinkedList<MyCircle> MyCircleList= new LinkedList<>();
    LinkedList<Line> LineList = new LinkedList<>();

    private boolean MyCircle_is_selected = false;
    private boolean position_is_selected = false;

    private double x_NodeSelected = 0;
    private double y_NodeSelected = 0;
    private Integer lvl_NodeSelected = 0;
    private LinkedList<MyCircle> sxList_NodeSelected = null;
    private LinkedList<MyCircle> ccList_NodeSelected = null;
    private LinkedList<MyCircle> dxList_NodeSelected = null;
    private boolean hasChildren_NodeSelected = false;

    private double x_NewNodeSelected = 0;
    private double y_NewNodeSelected = 0;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ToggleGroup group = new ToggleGroup();
        SX.setToggleGroup(group);
        CC.setToggleGroup(group);
        DX.setToggleGroup(group);

        createNode(300, 100, DEFAULT_CIRCLE, 1);
        windowww.getChildren().add(MyCircleList.get(0));
    }

    public void selectPosition(){
        if (SX.isSelected() || CC.isSelected() || DX.isSelected()) {
            position_is_selected = true;
            if(SX.isSelected()) x_NewNodeSelected = x_NodeSelected - 2*((120 - (lvl_NodeSelected*30))/lvl_NodeSelected);
            else if(CC.isSelected())  x_NewNodeSelected = x_NodeSelected;
            else x_NewNodeSelected = x_NodeSelected + 2*((120 - (lvl_NodeSelected*30))/lvl_NodeSelected);
        }
    }

    private void setParameterNewNode(MyCircle x){
        x_NodeSelected = x.getCenterX();
        y_NodeSelected = x.getCenterY();
        lvl_NodeSelected = x.getLevelNode();
        sxList_NodeSelected = x.getSxList();
        ccList_NodeSelected = x.getCcList();
        dxList_NodeSelected = x.getDxList();
        hasChildren_NodeSelected = x.getHasChildren();
    }


    private void createNode(double x, double y, double s, Integer lvl){
        MyCircle c1 = new MyCircle(x, y, s, lvl);
        c1.setOnMouseClicked(event -> {
            if (!MyCircle_is_selected) {
                MyCircle_is_selected = true;
                setParameterNewNode(c1);
                for(int num = MyCircleList.size() - 1; num >= 0 ; num--) {
                    MyCircleList.get(num).setFill(Color.BLACK);
                }
                c1.setFill(Color.RED);
            } else {
                MyCircle_is_selected = false;
                c1.setFill(Color.BLACK);
            }
        });
        MyCircleList.add(c1);
    }

    private void createArch(double x1, double y1, double x2, double y2){
        Line l1 = new Line(x1, y1, x2, y2);
        LineList.add(l1);
    }


















    @FXML
    private void addNode(ActionEvent event) throws IOException {
        y_NewNodeSelected = y_NodeSelected + 60;
        selectPosition();
        if (MyCircle_is_selected) {
            if(position_is_selected) {
                if (lvl_NodeSelected + 1 <= 4) {
                    createNode(x_NewNodeSelected, y_NewNodeSelected, DEFAULT_CIRCLE, lvl_NodeSelected + 1);
                    createArch(x_NodeSelected, y_NodeSelected, x_NewNodeSelected, y_NewNodeSelected);
                } else setError_window(2);//raggiunto il limite massimo di livelli
            } else setError_window(2); //non selezionato la posizione
        } else setError_window(2);//non selezionato il nodo


        for(int num = MyCircleList.size() - 1; num > 0 ; num--) {
            //if (x_NodeSelected == MyCircleList.get(num).getCenterX() && y_NodeSelected == MyCircleList.get(num).getCenterY()) MyCircleList.get(num).addChildren(num);
            windowww.getChildren().add(MyCircleList.get(num));
            windowww.getChildren().add(LineList.get(num - 1));
        }

    }


    public void deleteNode(ActionEvent event) throws IOException {
        if(MyCircle_is_selected) {
            if (hasChildren_NodeSelected == false) {
                if(lvl_NodeSelected != 1) {
                    for (int num = MyCircleList.size() - 1; num > 0; num--) {
                        if (x_NodeSelected == MyCircleList.get(num).getCenterX() && y_NodeSelected == MyCircleList.get(num).getCenterY()) {
                            windowww.getChildren().remove(MyCircleList.get(num));
                            windowww.getChildren().remove(LineList.get(num - 1));
                            MyCircleList.remove(num);
                            LineList.remove(num - 1);
                        }
                    }
                } else setError_window(2); //non si puo cancellare il nodo radice
            } else setError_window(1);//non si puo eliminare siccome non è una foglia
        } else setError_window(2);//non selezionato il nodo
    }


}