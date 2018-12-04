import java.util.ArrayList;

public class SimpleNotepad implements INotePad {

    private ArrayList<Page> content;

    public SimpleNotepad() {
        this.content = new ArrayList<Page>();
    }

    @Override
    public void newPage(String title, String text) {
        Page newPage = new Page(title, text);
        this.content.add(newPage);
    }

    @Override
    public void addTextToPage(int page, String text) {
        if (content.size() < page) {
            System.out.println("The text is not added to page " + page + ". Page " + page + " does not exist!\n");
            return;
        }

        content.get(page-1).addText("", text);
    }

    @Override
    public void clearTextPage(int page) {
        System.out.println("Clear text on page " + page + ".");
        content.get(page-1).emptyPage();
    }

    @Override
    public void viewAllPages() {
        for (Page p : this.content) {
            System.out.println(p.viewPage());
        }
    }
}
