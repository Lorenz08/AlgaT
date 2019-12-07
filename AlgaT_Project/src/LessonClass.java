import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;


public class LessonClass extends Main implements Initializable {

    private double current_page1 = 0;
    private double current_page2 = 0;
    private double current_page3 = 0;
    private LinkedList<Page> list_of_pages1 = null;
    private LinkedList<Page> list_of_pages2 = null;
    private LinkedList<Page> list_of_pages3 = null;
    @FXML private AnchorPane AnchorPaneL1;
    @FXML private Label Label1;
    @FXML private ImageView ImageView1;
    @FXML private AnchorPane AnchorPaneL2;
    @FXML private Label Label2;
    @FXML private ImageView ImageView2;
    @FXML private AnchorPane AnchorPaneL3;
    @FXML private Label Label3;
    @FXML private ImageView ImageView3;
    @FXML private ProgressBar pb1;
    @FXML private ProgressBar pb2;
    @FXML private ProgressBar pb3;

    double current_prog_lesson1 = 0;
    double current_prog_lesson2 = 0;
    double current_prog_lesson3 = 0;
    double prog_lesson1 = 0;
    double prog_lesson2 = 0;
    double prog_lesson3 = 0;



    public void initialize(URL location, ResourceBundle resources) {
        list_of_pages1 = new LinkedList<Page>();
        list_of_pages2 = new LinkedList<Page>();
        list_of_pages3 = new LinkedList<Page>();
        try {
            creat_listPages_of_all_lessons(list_of_pages1, "Text_file/Lessons/TxtLesson1" );
            creat_listPages_of_all_lessons(list_of_pages2, "Text_file/Lessons/TxtLesson2");
            creat_listPages_of_all_lessons(list_of_pages3, "Text_file/Lessons/TxtLesson3");
        } catch (IOException e) {
            e.printStackTrace();
        }
        setPage();
        mouseWheelMoved();
        prog_lesson1 = 1.0/list_of_pages1.size();
        prog_lesson2 = 1.0/list_of_pages2.size();
        prog_lesson3 = 1.0/list_of_pages3.size();
        current_prog_lesson1 = prog_lesson1;
        current_prog_lesson2 = prog_lesson2;
        current_prog_lesson3 = prog_lesson3;
    }

    private void creat_listPages_of_all_lessons(LinkedList<Page> listOfPage, String String) throws IOException {
        InputStream file_to_open = getClass().getResourceAsStream(String);  //seleziona il file txt da aprire
        InputStreamReader file_decode = new InputStreamReader(file_to_open);    //trasforma il contenuto del file che apre da bit TxtLesson2 caratteri ASCII
        BufferedReader file_to_read = new BufferedReader(file_decode);    //legge e bufferizza i caratteri letti da uno stream di caratteri in input
        creat_listPages(file_to_read, listOfPage);
    }

    private void creat_listPages(BufferedReader tmp, LinkedList<Page> list) throws IOException {
        String complete_page = "";
        String line_of_page_to_add;
        Image image_to_load = null;

        while ((line_of_page_to_add = tmp.readLine())!= null) {
            if (line_of_page_to_add.startsWith("TXT:")){
                if (line_of_page_to_add.contains("#")){
                    line_of_page_to_add = line_of_page_to_add.replace("TXT:", "");
                    line_of_page_to_add = line_of_page_to_add.replace("#", "");
                    complete_page = complete_page.concat(line_of_page_to_add)+'\n';
                }
                else {
                    line_of_page_to_add = line_of_page_to_add.replace("TXT:", "");
                    complete_page = complete_page.concat(line_of_page_to_add);
                }
            }

            if (line_of_page_to_add.startsWith("IMG:")){
                line_of_page_to_add = line_of_page_to_add.replace("IMG:", "");
                if (line_of_page_to_add.contains("null")) image_to_load = null;
                else image_to_load = new Image(getClass().getResourceAsStream(line_of_page_to_add));
            }

            if (line_of_page_to_add.startsWith("END")){
                Page newP = new Page(complete_page, image_to_load);
                list.add(newP);
                complete_page = "";
            }
        }
        tmp.close();
    }

    public void setPage(){
        if (current_lesson == 1) xxx(Label1, ImageView1, list_of_pages1, current_page1);
        else if (current_lesson == 2) xxx(Label2, ImageView2, list_of_pages2, current_page2);
        else if (current_lesson == 3) xxx(Label3, ImageView3,list_of_pages3,current_page3);
    }

    public void xxx(Label l1, ImageView img1, LinkedList<Page> list, double currentl){
        Page nPage = list.get((int) currentl);
        l1.setText(nPage.getText());
        l1.setWrapText(true);
        img1.setImage(nPage.getImage());
    }

    public void setNew_Page(double currentLess, LinkedList<Page> list) throws IOException {
        if (currentLess == list.size()){
            switch (current_lesson){
                case 1:
                    ok_exercise1 = true;
                    break;
                case 2:
                    ok_exercise2 = true;
                    break;
                case 3:
                    ok_exercise3 = true;
                    break;
            }
            Parent simulatorLayout = FXMLLoader.load(getClass().getResource("/Fxml_file/InitialScene.fxml"));
            Scene simulatorScene = new Scene(simulatorLayout);
            window.setScene(simulatorScene);
        } else if (currentLess < 0) {
            Parent simulatorLayout = FXMLLoader.load(getClass().getResource("/Fxml_file/InitialScene.fxml"));
            Scene simulatorScene = new Scene(simulatorLayout);
            window.setScene(simulatorScene);
        } else {
            setPage();
        }
    }

    public void nextPage() throws IOException {
        if (current_lesson == 1) {
            current_page1++;
            setNew_Page(current_page1, list_of_pages1);
            current_prog_lesson1 += prog_lesson1;
            pb1.setProgress(current_prog_lesson1);
        }
        else if (current_lesson == 2) {
            current_page2++;
            setNew_Page(current_page2, list_of_pages2);
            current_prog_lesson2 += prog_lesson2;
            pb2.setProgress(current_prog_lesson2);

        }
        else if (current_lesson == 3) {
            current_page3++;
            setNew_Page(current_page3, list_of_pages3);
            current_prog_lesson3 += prog_lesson3;
            pb3.setProgress(current_prog_lesson3);
        }
    }

    public void prevPage() throws IOException {
        if (current_lesson == 1) {
            current_page1--;
            setNew_Page(current_page1, list_of_pages1);
            current_prog_lesson1 -= prog_lesson1;
            pb1.setProgress(current_prog_lesson1);
            System.out.println(current_page1);
        }
        else if (current_lesson == 2) {
            current_page2--;
            setNew_Page(current_page2, list_of_pages2);
            current_prog_lesson2 -= prog_lesson2;
            pb2.setProgress(current_prog_lesson2);
        }
        else if (current_lesson == 3) {
            current_page3--;
            setNew_Page(current_page3, list_of_pages3);
            current_prog_lesson3 -= prog_lesson3;
            pb3.setProgress(current_prog_lesson3);
        }
    }

    private void mouseWheelMoved(){
        AnchorPane ancorPn = null;
        if (current_lesson == 1) ancorPn = AnchorPaneL1;
        if (current_lesson == 2) ancorPn = AnchorPaneL2;
        if (current_lesson == 3) ancorPn = AnchorPaneL3;
        ancorPn.setOnScroll(event -> {
            double deltaY = event.getDeltaY();
            if (deltaY < 0) {
                try {
                    nextPage();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (deltaY > 0) {
                try {
                    prevPage();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            event.consume();
        });
    }
}