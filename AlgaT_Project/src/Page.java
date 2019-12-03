import javafx.scene.image.Image;

public class Page {

    private Integer number_of_page;
    private Image image;
    private String text;

    Page(Integer i, String txt, Image img) {
        super();
        number_of_page = i;
        image = img;
        text = txt;
    }

    public Image getImage() {
        return(image);
    }

    public String getText() {
        return(text);
    }

}