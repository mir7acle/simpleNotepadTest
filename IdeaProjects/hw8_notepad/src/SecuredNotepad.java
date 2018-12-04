import java.util.Scanner;

public class SecuredNotepad extends SimpleNotepad {

    private String password;

    public SecuredNotepad() {
        super();
        password = readPasswordFromConsole();
    }


    private String readPasswordFromConsole() {
        Scanner scan = new Scanner(System.in);

        String enterpwd = null;
        System.out.println("Please, enter password to create secured notepad: ");
        enterpwd = scan.nextLine();
        while (enterpwd == null) {
            System.out.println();
            System.out.print("The password is empty! Please, enter new password: ");
            scan.nextLine();
        }
        return enterpwd;
    }

    private boolean validateEnteredPassword() {

        String enteredpwd = readPasswordFromConsole();
        if (enteredpwd.equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void newPage(String title, String text) {
        if (validateEnteredPassword()) {
            super.newPage(title, text);
        } else {
            System.out.println("The password is not correct! The notepad is secured. Can not add new page to the notepad.");
        }
    }

    @Override
    public void addTextToPage(int page, String text) {
        if (validateEnteredPassword()) {
            super.addTextToPage(page, text);
        } else {
            System.out.println("The password is not correct! The notepad is secured. Can not add text to page " + page + ".");
        }
    }

    @Override
    public void clearTextPage(int page) {
        if (validateEnteredPassword()) {
            super.clearTextPage(page);
        } else {
            System.out.println("The password is not correct! The notepad is secured. Can not clear page " + page + ".");
        }

    }

    @Override
    public void viewAllPages() {
        if (validateEnteredPassword()) {
            super.viewAllPages();
        } else {
            System.out.println("The password is not correct! The notepad is secured. Can not view pages.");
        }
    }
}
