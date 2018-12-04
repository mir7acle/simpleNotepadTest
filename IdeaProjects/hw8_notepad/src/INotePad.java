import java.util.ArrayList;

public interface INotePad {

    public void newPage(String title, String text);
    public void addTextToPage(int page, String text);
    public void clearTextPage(int page);
    public void viewAllPages();

}
