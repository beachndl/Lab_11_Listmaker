import java.util.ArrayList;
import java.util.Scanner;

public class ListMaker {
    // ArrayList
    private static final ArrayList<String> itemList = new ArrayList<>(); // diamond notation on the type parameter <>

    public static void main(String[] args) {
        // Scanner + variable declaration
        Scanner in = new Scanner(System.in);
        String menuChoice = "";
        boolean done = false;

        // Main loop
        do {
            // Display the current list + menu
            displayListAndMenu();

            // Using getRedExString method from SafeInput.java to get user menu choice
            menuChoice = SafeInput.getRegExString(in, "Enter your choice (A|a|D|d|I|i|P|p|Q|q)", "[AaDdIiPpQq]");
            menuChoice = menuChoice.toUpperCase(); // Convert to uppercase for easier comparison

            // Process user choice
            switch (menuChoice) {
                case "A":
                    addItem(in);
                    break;
                case "D":
                    deleteItem(in);
                    break;
                case "I":
                    insertItem(in);
                    break;
                case "P":
                    printList();
                    break;
                case "Q":
                    done = confirmQuit(in);
                    break;
            }

        } while (!done);
    }

    // Method to display current list + menu options
    private static void displayListAndMenu() {
        // Variable declaration
        String item = "";

        // Print information
        System.out.println("\n-------- List Maker -------");
        System.out.println("Current List:");

        // Condition to check if list is empty
        if (itemList.isEmpty()) {
            System.out.println("No items in the list.");
        } else {
            for (int i = 0; i < itemList.size(); i++) {
                item = itemList.get(i);
                System.out.println((i + 1) + ". " + item);
            }
        }

        // Print menu options
        System.out.println("\nMenu Options:");
        System.out.println("A – Add an item to the list");
        System.out.println("D – Delete an item from the list");
        System.out.println("I – Insert an item into the list");
        System.out.println("P – Print (i.e. display) the list");
        System.out.println("Q – Quit the program");
    }

    // Static method to add a new item to the end of the list
    private static void addItem(Scanner pipe) {
        // Variable declaration
        String newItem = "";

        // Using getNonZeroLenString method from SafeInput.java to add item
        newItem = SafeInput.getNonZeroLenString(pipe, "Enter the item to add");
        itemList.add(newItem); // Add element to the end of the list
        System.out.println("\nItem added successfully.");
    }

    // Static method to delete an item from the list based on position
    private static void deleteItem(Scanner pipe) {
        // Variable declaration
        int itemNum;
        String removedItem = "";

        // Condition to check if list is empty
        if (itemList.isEmpty()) {
            System.out.println("No items in the list.");
            return;
        }

        // Display the list with numbers
        printNumberedList();

        // Using getRedExString method from SafeInput.java to get item number to delete
        itemNum = SafeInput.getRangedInt(pipe, "Enter the item number to delete", 1, itemList.size());

        // Delete the item; account for 0-based index
        removedItem = itemList.remove(itemNum - 1);
        System.out.println("\nItem \"" + removedItem + "\" has been deleted.");
    }

    // Static method to insert an item at a specific position in the list
    private static void insertItem(Scanner pipe) {
        // Variable declaration
        int position;
        String newItem = "";

        // Condition to check if list is empty; add to the beginning if empty
        if (itemList.isEmpty()) {
            System.out.println("No items in the list. Item will be added at the beginning.");
            addItem(pipe);
            return;
        }

        // Display the current list
        printNumberedList();

        // Using getRangedInt method from SafeInput.java to get the position to insert
        position = SafeInput.getRangedInt(pipe, "Enter position to insert (1 to " + (itemList.size() + 1) + ")", 1, itemList.size() + 1);

        // Using getNonZeroLenString method from SafeInput.java to get the item to insert
        newItem = SafeInput.getNonZeroLenString(pipe, "Enter the item to insert");

        // Insert the item at specified position; account for 0-based index
        itemList.add(position - 1, newItem);
        System.out.println("\nItem inserted successfully at position " + position + ".");
    }

    // Static method to print current list
    private static void printList() {
        // Variable declaration
        String item = "";

        // Print information
        System.out.println("\n-------- Current List --------");

        // Condition to check if list is empty
        if (itemList.isEmpty()) {
            System.out.println("No items in the list.");
        } else {
            for (int i = 0; i < itemList.size(); i++) {
                item = itemList.get(i); // Getting item at index i
                System.out.println((i + 1) + ". " + item);
            }
        }
        System.out.println("-------------------------------");
    }

    // Static method to display the numbered version of the list
    private static void printNumberedList() {
        // Variable declaration
        String item = "";

        // Print information
        System.out.println("\nCurrent List:");

        for (int i = 0; i < itemList.size(); i++) {
            item = itemList.get(i); // Reading element at index i
            System.out.println((i + 1) + ". " + item);
        }
    }

    // Static method to confirm if user wants to quit
    private static boolean confirmQuit(Scanner pipe) {
        // Using getYNConfirm method from SafeInput.java to ask user if they are sure they want to quit
        return SafeInput.getYNConfirm(pipe, "Are you sure you want to quit?");
    }
}