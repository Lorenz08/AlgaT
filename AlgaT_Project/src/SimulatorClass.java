import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;


public class SimulatorClass extends Main implements Initializable {



    /*  FIELDS */
    @FXML private AnchorPane windowww;
    @FXML private TextField inputValue;;
    private int currentIndex = 1;
    private LinkedList<NodePP> list_nodes = new LinkedList<NodePP>();



    /*  METHODS */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }


    public void xxx(ActionEvent event){
        for(int i = 0; i < list_nodes.size(); i++){
                System.out.print(list_nodes.get(i).getIndex_of_node());
                System.out.print(" - ");
                System.out.println(list_nodes.get(i).getValue_of_node());
        }
    }


    private void drawTree(){
        Circle tmp1 = new Circle(30);
        tmp1.setCenterX(340);
        tmp1.setCenterY(150);
        Text tmp2 = new Text(inputValue.getText());
        tmp2.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        tmp2.setFill(Color.WHITE);
        StackPane tmp3 = new StackPane(tmp1, tmp2);
        tmp3.toFront();
        windowww.getChildren().add(tmp3);
    }


   public void addNode(ActionEvent event){
        NodePP n1 = new NodePP(currentIndex, Integer.parseInt(inputValue.getText()));
        list_nodes.add(n1);
        drawTree();
        inputValue.clear();
        currentIndex++;
    }



    //Draw the Array and HeapTree
    /*//Draw the Array and HeapTree
    private void drawAll() {
        //Draw the HeapTree

    }

    //Cycles through the array and put every node in the right position (thanks to the offset)
    private void drawTree(int index, double xOld, double yOld, double xNew, double yNew, double offset) {
        if (index < vector.length) {

            if (vector[index] != NIL) {
                double newOffset = offset / 2;

                if ((xOld == 0) && (yOld == 0)) {


                    drawTree(index * 2, xNew, yNew, xNew - newOffset, yNew + 100, newOffset);
                    drawTree(index * 2 + 1, xNew, yNew, xNew + newOffset, yNew + 100, newOffset);

                    Circle tmp1 = new Circle(30);
                    Text tmp2 = new Text(vector[index].toString());
                    tmp2.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
                    tmp2.setFill(Color.WHITE);
                    StackPane tmp3 = new StackPane(tmp1, tmp2);
                    tmp3.relocate(xNew, yNew);
                    tmp3.toFront();
                    treeView.getChildren().add(tmp3);

                } else {

                    Line tmp0 = new Line(xOld + 30, yOld + 30, xNew + 30, yNew + 30);
                    treeView.getChildren().add(tmp0);

                    drawTree(index * 2, xNew, yNew, xNew - newOffset, yNew + 100, newOffset);
                    drawTree(index * 2 + 1, xNew, yNew, xNew + newOffset, yNew + 100, newOffset);

                    Circle tmp1 = new Circle(30);
                    tmp1.setId("my-circle");
                    Text tmp2 = new Text(vector[index].toString());
                    tmp2.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
                    tmp2.setFill(Color.WHITE);
                    StackPane tmp3 = new StackPane(tmp1, tmp2);
                    tmp3.relocate(xNew, yNew);
                    tmp3.toFront();
                    treeView.getChildren().add(tmp3);
                }
            }
        }
    }
*/


}