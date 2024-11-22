import java.util.Scanner;


public class Main {
       
     public static Scanner input = new Scanner (System.in);
     public static DocumentSearch SE = new DocumentSearch();


    public static void main(String[] args) {
        // Load data from files
        SE.LoadData("C:\\Users\\acer\\Desktop\\DataStr212p\\stop.txt", "C:\\Users\\acer\\Desktop\\DataStr212p\\dataset.csv");

        // Main menu loop
        int choice;
        do {
            choice = displayMenu();
            handleMenuChoice(choice);
        } while (choice != 5);

        System.out.println("Exiting... Goodbye!");
    }

    private static int displayMenu() {
        System.out.println("\n********** Main Menu **********");
        System.out.println("1. Boolean Retrieval");
        System.out.println("2. Ranked Retrieval");
        System.out.println("3. Indexed Documents");
        System.out.println("4. Indexed Tokens");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");

        // Read and return user choice
        return input.nextInt();
    }

    private static void handleMenuChoice(int choice) {
        switch (choice) {
            case 1:
                displayBooleanRetrievalMenu();
                break;
            case 2:
                displayRankedRetrievalMenu();
                break;
            case 3:
                displayIndexedDocumentsMenu();
                break;
            case 4:
                displayIndexedTokensMenu();
                break;
            case 5:
                // Exit gracefully
                break;
            default:
                System.out.println("Invalid choice, please try again!");
        }
    }

    private static void displayBooleanRetrievalMenu() {
        String[] queries = {
            "market AND sports",
            "weather AND warming",
            "business AND world",
            "weather OR warming",
            "market OR sports",
            "market OR sports AND warming.... AND has a higher precedence than OR"
        };

        System.out.println("\n########## Boolean Retrieval ##########");
        for (String query : queries) {
            System.out.println("#Q: " + query);
            boolean[] results = SE.BooleanRetrieval(query, 2);
            printBooleanResults(results);
            System.out.println();
        }
    }

    private static void displayRankedRetrievalMenu() {
        String[] queries = {
            "market sports",
            "weather warming",
            "business world market"
        };

        System.out.println("\n########### Ranked Retrieval ############");
        for (String query : queries) {
            System.out.println("##Q: " + query);
            System.out.println("DocID\tScore");
            SE.RankedRetrieval(query);
            System.out.println();
        }
    }

    private static void displayIndexedDocumentsMenu() {
        System.out.println("\n######## Indexed Documents ######");
        System.out.println("Indexed Documents: " + SE.index.Docindex.length);
    }

    private static void displayIndexedTokensMenu() {
        System.out.println("\n########Indexed Tokens#########");
        System.out.println("Total Tokens: " + SE.tokens);
    }

    private static void printBooleanResults(boolean[] results) {
        Record resultRecord = new Record("", results);
        System.out.println(resultRecord);
    }
}