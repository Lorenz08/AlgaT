import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


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
    public void inizializzaEsercitazioni(Integer i) throws IOException {
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
        if (current_exercise == 1) x = "Text_file/Test/TxtExercise1";
        if (current_exercise == 2) x = "Text_file/Test/TxtExercise2";
        if (current_exercise == 3) x = "Text_file/Test/TxtExercise3";
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
        Integer i = 0;
        String complete_page = "";
        String[] risposte = new String[4];
        String line_of_page_to_add;
        Image image_to_load = null;
        //finche il file non e finito
        while ((line_of_page_to_add = tmp.readLine())!= null) {
            //se esiste il file
            if (line_of_page_to_add.startsWith("TXT:")){
                //se il file inizia con TXT: , rimpiazzalo con ""
                line_of_page_to_add = line_of_page_to_add.replace("TXT:", "");
                char control = line_of_page_to_add.charAt(line_of_page_to_add.length() - 1);
                //se dopo la linea TXT: trovo un #
                if (control == '#') {
                    //rimuovo il #
                    line_of_page_to_add = line_of_page_to_add.replace("#", "");
                    //vai a capo
                    complete_page = complete_page.concat(line_of_page_to_add);
                    complete_page += '\n';
                }
                else complete_page = complete_page.concat(line_of_page_to_add);
            }
            else if (line_of_page_to_add.startsWith("IMG:")){
                //se trovo IMG:
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
            else if (line_of_page_to_add.startsWith("END")){
                Page newP = new Page(i, complete_page, image_to_load,risposte);
                list.add(newP);
                complete_page = "";
                i++;
            }
        }
        //chiudi il file
        tmp.close();
    }

// setta il numero di esercitazione da svolgere
    public void setPage(Integer currentExercise, LinkedList<Page> list){
        Page nPage = new Page(0,"",null);
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

    public void setNew_Page() throws IOException {
        LinkedList<Page> tmp = new LinkedList<Page>();
        Integer x = 0;

        switch (current_exercise) {
            case 1:
                x = current_exercize_page1;
                tmp = list_of_pages1;
            case 2:
                x = current_exercize_page2;
                tmp = list_of_pages2;
            case 3:
                x = current_exercize_page3;
                tmp = list_of_pages3;
        }
        if (x == tmp.size() && complete){
            //sblocco delle lezioni
            switch (current_exercise){
                case 1:
                    ok_lesson2 = true;
                case 2:
                    ok_lesson3 = true;
            }
            showHome();
        }else if(x == tmp.size())
                setPage(current_exercise, tmp);
        else if (x < 0){
            showHome();
        }else
            setPage(current_exercise, tmp);
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
            case 2:
                current_exercize_page2++;
            case 3:
                current_exercize_page3++;
        }
        setNew_Page();
    }

    public void prevPage(ActionEvent event) throws IOException{
        switch (current_lesson) {
            case 1:
                current_exercize_page1--;
            case 2:
                current_exercize_page2--;
            case 3:
                current_exercize_page3--;
        }
        setNew_Page();
    }

}





