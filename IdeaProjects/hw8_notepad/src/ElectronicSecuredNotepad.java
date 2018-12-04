public class ElectronicSecuredNotepad extends SecuredNotepad implements IElectronicDevice {

    private boolean isStarted;

    @Override
    public void start() {
        System.out.println("Starting....");
        isStarted = true;
    }

    @Override
    public void stop() {
        System.out.println("Stopping....");
        isStarted = false;
    }

    @Override
    public boolean isStarted() {
        return isStarted;
    }

    public ElectronicSecuredNotepad() {
        super();
    }

    @Override
    public void newPage(String title, String text) {
        if(isStarted) {
            super.newPage(title, text);
        } else {
            System.out.println("The device is not started! Please, start device before add new page.");
        }
    }

    @Override
    public void addTextToPage(int page, String text) {
        if(isStarted) {
            super.addTextToPage(page, text);
        } else {
            System.out.println("The device is not started! Please, start device before add text to page " + page + ".");
        }
    }

    @Override
    public void clearTextPage(int page) {
        if(isStarted) {
            super.clearTextPage(page);
        } else {
            System.out.println("The device is not started! Please, start device before clear page " + page + ".");
        }
    }

    @Override
    public void viewAllPages() {
        if(isStarted) {
            super.viewAllPages();
        } else {
            System.out.println("The device is not started! Please, start device before view pages.");
        }
    }
}
