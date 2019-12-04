import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.TextAlignment;


import java.io.*;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;


public class ExerciseClass extends Main implements Initializable{

    private boolean complete = false;
    private boolean is_showSolution = true;
//    private boolean complete2= false;
//    private boolean complete3 = false;


    private Integer current_exercize_page1 = 0;
    private Integer current_exercize_page2 = 0;
    private Integer current_exercize_page3 = 0;

    private double current_prog_exercize1 = 0;
    private double current_prog_exercize2 = 0;
    private double current_prog_exercize3 = 0;
    private double prog_exercize1 = 0;
    private double prog_exercize2 = 0;
    private double prog_exercize3 = 0;
    @FXML private ProgressBar pb1;
    @FXML private ProgressBar pb2;
    @FXML private ProgressBar pb3;


    private LinkedList<Page> list_of_pages1 = null;
    private LinkedList<Page> list_of_pages2 = null;
    private LinkedList<Page> list_of_pages3 = null;

    private Integer number_of_pages_of_exercise1 = 0;
    private Integer number_of_pages_of_exercise2 = 0;
    private Integer number_of_pages_of_exercise3 = 0;

    @FXML private Label Title;
    @FXML private Label LaberlDomanda;
    @FXML private Label solution;
    @FXML private ImageView ImageView1;
    @FXML private CheckBox value1;
    @FXML private CheckBox value2;
    @FXML private CheckBox value3;
    @FXML private CheckBox value4;

    /* METHODS */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            inizializzaEsercitazioni(current_exercise);
            showSolution();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //crea la lista di pagine da vedere
    private void inizializzaEsercitazioni(Integer i) throws IOException {
        if (i == 1){
            //lista delle pagine della prima lezione
            list_of_pages1 = new LinkedList<Page>();
            creat_listPages_of_all_Exercise(number_of_pages_of_exercise1, list_of_pages1);
        }
        else if (i == 2){
            //lista delle pagine della seconda lezione
            list_of_pages2 = new LinkedList<Page>();
            creat_listPages_of_all_Exercise(number_of_pages_of_exercise2, list_of_pages2);
        }
        else if (i == 3){
            //lista delle pagine della terza lezione
            list_of_pages3 = new LinkedList<Page>();
            creat_listPages_of_all_Exercise(number_of_pages_of_exercise3, list_of_pages3);
        }

    }

    private void creat_listPages_of_all_Exercise(Integer i, LinkedList<Page> tmp) throws IOException {
        String x = "";
        if (current_exercise == 1) x = "Text_file/TxtExercise1";
        if (current_exercise == 2) x = "Text_file/TxtExercise2";
        if (current_exercise == 3) x = "Text_file/TxtExercise3";
        InputStream file_to_open = getClass().getResourceAsStream(x);  //seleziona il file txt da aprire
        InputStreamReader file_decode = new InputStreamReader(file_to_open);    //trasforma il contenuto del file che apre da bit TxtLesson2 caratteri ASCII
        BufferedReader file_to_read = new BufferedReader(file_decode);    //legge e bufferizza i caratteri letti da uno stream di caratteri in input

        creat_list_exercise_Pages(file_to_read, tmp);
        //variabile che indica il numero di pagine per esercitazione
        i = tmp.size();
        setPage(current_exercise, tmp);
    }


    //crea la lista di pagine delle esercitazioni da svolgere
    //tmp = file tct con le domande e risposte
    //list = lista delle pagine dell'esercitazione
    private void creat_list_exercise_Pages(BufferedReader tmp, LinkedList<Page> list) throws IOException {
        String complete_page = "";
        String line_of_page_to_add;
        Image image_to_load = null;
        String[] risposte = new String[4];

        //finche il file non e finito
        while ((line_of_page_to_add = tmp.readLine())!= null) {
            //se esiste il file
            if (line_of_page_to_add.startsWith("TXT:")) {
                //se il file inizia con TXT: , rimpiazzalo con ""
                line_of_page_to_add = line_of_page_to_add.replace("TXT:", "");
                complete_page = complete_page.concat(line_of_page_to_add);
            }
            if (line_of_page_to_add.startsWith("IMG:")){
                line_of_page_to_add = line_of_page_to_add.replace("IMG:", "");
                //rimuovo IMG: e se non ce nessuna immagine non caricare nulla
                if (line_of_page_to_add.contains("null")) image_to_load = null;
                //altrimenti crea una nuova instanza di immagine
                else image_to_load = new Image(getClass().getResourceAsStream(line_of_page_to_add));
            }else if(line_of_page_to_add.startsWith("CK1:")){
                line_of_page_to_add = line_of_page_to_add.replace("CK1:", "");
                risposte[0]=line_of_page_to_add;
            }else if(line_of_page_to_add.startsWith("CK2:")){
                line_of_page_to_add = line_of_page_to_add.replace("CK2:", "");
                risposte[1]=line_of_page_to_add;
            }else if(line_of_page_to_add.startsWith("CK3:")){
                line_of_page_to_add = line_of_page_to_add.replace("CK3:", "");
                risposte[2]=line_of_page_to_add;
            }else if(line_of_page_to_add.startsWith("CK4:")){
                line_of_page_to_add = line_of_page_to_add.replace("CK4:", "");
                risposte[3]=line_of_page_to_add;
            }
            //se inizia con END crea una nuova pagina aggiungendo il testo e l'immagine, aggiungila alla lista dell'esercitazione e incrementa il contatore delle pagine
            if (line_of_page_to_add.startsWith("END")){
                Page newP = new Page(complete_page, image_to_load,risposte);
                list.add(newP);
                complete_page = "";
            }
        }
        //chiudi il file
        tmp.close();
    }

    // setta il numero di esercitazione da svolgere
    public void setPage(Integer currentExercise, LinkedList<Page> list){
        Page nPage = new Page("",null);
        switch (currentExercise){
            case 1:
                nPage = list.get(current_exercize_page1);
            case 2:
                nPage = list.get(current_exercize_page2);
            case 3:
                nPage = list.get(current_exercize_page3);
        }
        LaberlDomanda.setText(nPage.getText());
        value1.setText(nPage.getValue(0));
        value2.setText(nPage.getValue(1));
        value3.setText(nPage.getValue(2));
        value4.setText(nPage.getValue(3));
        ImageView1.setImage(nPage.getImage());
    }


    private void setPage(){
        if (current_lesson == 1) setPage(Label1, ImageView1, list_of_pages1, current_exercize_page1);
        else if (current_lesson == 2) setPage(Label2, ImageView2, list_of_pages2, current_exercize_page2);
        else if (current_lesson == 3) setPage(Label3, ImageView3,list_of_pages3,current_exercize_page3);
    }

    private void setPage(Label l1, ImageView img1, LinkedList<Page> list, double currentl){
        Page nPage = list.get((int) currentl);
        l1.setText(nPage.getText());
        l1.setTextAlignment(TextAlignment.CENTER);
        l1.setWrapText(true);
        img1.setImage(nPage.getImage());
    }

    private void setNew_Page(double currentLess, LinkedList<Page> list) throws IOException {
        if (currentLess == list.size()){
            //sblocco degli esercizi
            switch (current_lesson){
                case 1:
                    ok_exercise1 = true;
                case 2:
                    ok_exercise2 = true;
                case 3:
                    ok_exercise3 = true;
            }
            showHome();
        } else if (currentLess < 0) {
            showHome();
        } else {
            setPage();
        }
    }

    private void showHome() throws IOException {
        Parent simulatorLayout = FXMLLoader.load(getClass().getResource("/Fxml_file/InitialScene.fxml"));
        Scene simulatorScene = new Scene(simulatorLayout);
        window.setScene(simulatorScene);
    }

    public void showSolution(){
        if(is_showSolution){
            solution.setVisible(false);
            is_showSolution =false;
        }
        else{
            solution.setVisible(true);
            is_showSolution=true;
        }
    }

    public void nextPage(ActionEvent event) throws IOException{
        switch (current_lesson) {
            case 1:
                current_exercize_page1++;
                setNew_Page(current_exercize_page1, list_of_pages1);
                current_prog_exercize1 += prog_exercize1;
                pb1.setProgress(current_prog_exercize1);
                break;
            case 2:
                current_exercize_page2++;
                setNew_Page(current_exercize_page2, list_of_pages2);
                current_prog_exercize2 += prog_exercize2;
                pb2.setProgress(current_prog_exercize2);
                break;
            case 3:
                current_exercize_page3++;
                setNew_Page(current_exercize_page3, list_of_pages3);
                current_prog_exercize3 += prog_exercize3;
                pb3.setProgress(current_prog_exercize3);
                break;
        }
    }

    public void prevPage(ActionEvent event) throws IOException{
        switch (current_lesson) {
            case 1:
                current_exercize_page1--;
                setNew_Page(current_exercize_page1, list_of_pages1);
                current_prog_exercize1 -= prog_exercize2;
                pb1.setProgress(current_prog_exercize1);
                break;
            case 2:
                current_exercize_page2--;
                setNew_Page(current_exercize_page2, list_of_pages2);
                current_prog_exercize2 -= prog_exercize2;
                pb1.setProgress(current_prog_exercize2);
                break;
            case 3:
                current_exercize_page3--;
                setNew_Page(current_exercize_page3, list_of_pages3);
                current_prog_exercize3 -= prog_exercize3;
                pb1.setProgress(current_prog_exercize3);
                break;
        }
    }

}





