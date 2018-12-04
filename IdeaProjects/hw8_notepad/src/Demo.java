public class Demo {

    public static void main(String[] args) {

        /*
        System.out.println("Simple notepad.........");

        SimpleNotepad simpleNotepad = new SimpleNotepad();
        simpleNotepad.newPage("Chapter 1","Once upon a time.....");
        simpleNotepad.addTextToPage(1,"there was one little girl....");
        simpleNotepad.newPage("Chapter 2", "The Red hat met the big evil wolf.");
        simpleNotepad.viewAllPages();
        simpleNotepad.clearTextPage(2);
        simpleNotepad.viewAllPages();

        separateLogicInConsole();
        */
        System.out.println("Secured notepad..........");
        SecuredNotepad securedNotepad = new SecuredNotepad();
        securedNotepad.newPage("Chapter 1","Once upon a time.....");
        securedNotepad.addTextToPage(1,"there was one little girl....");
        securedNotepad.newPage("Chapter 2", "The Red hat met the big evil wolf.");
        securedNotepad.viewAllPages();
        securedNotepad.clearTextPage(2);
        securedNotepad.viewAllPages();

//
//        separateLogicInConsole();
//
//        System.out.println("Electronic secured notepad......");
//        ElectronicSecuredNotepad esnp = new ElectronicSecuredNotepad();
    }

    private static void separateLogicInConsole() {
        System.out.println();
        System.out.println("--------------------------");
        System.out.println();
    }
}
