import java.io.*;
import java.util.*;

public class ArrayTest {

    private static final ArrayList<String> name = new ArrayList<String>();
    private static final ArrayList<Integer> balance = new ArrayList<Integer>();
    private static final double BETTING_ODDS = 52;

    public static void main(String[] args) throws FileNotFoundException {
        String inputName = "";
        int inputIndex = 0;

        Scanner console = new Scanner(System.in);

        String inFile = "database.txt";
        String outFile = "database.txt";

        File file = new File(inFile);
        Scanner input = new Scanner(file);

        while (input.hasNext()) {
            String newInputName = input.next();
            name.add(newInputName);
            int inputNum = input.nextInt();
            balance.add(inputNum);
        }

        System.out.println(name.toString());
        System.out.println(balance.toString());

        System.out.print("Name to look for? ");
        inputName = console.next();

        while(!(name.contains(inputName))) {
            System.out.println("You are not on the list! I have added you in and you now have 10 points!");
            name.add(inputName);
            balance.add(10);

            System.out.println();
            System.out.print("Name to look for? ");
            inputName = console.next();
        }

        inputIndex = name.indexOf(inputName);

        System.out.println();

        System.out.println("Name: " + name.get(inputIndex));
        System.out.println("Balance: " + balance.get(inputIndex));

        System.out.println();


        // BETTING
        int storeBalance = balance.get(inputIndex);

        System.out.print("How much to bet? ");
        int changeBalance = console.nextInt();

        while (changeBalance > balance.get(inputIndex) || changeBalance <= 0) {
            if (changeBalance <= 0) {
                System.out.println("Please bet above zero!");
            } else {
                System.out.println("That's over your bet amount! Please try again!");

            }
            System.out.print("How much to bet? ");
            changeBalance = console.nextInt();
        }

        int roll = new Random().nextInt(100) + 1;
        System.out.println("You rolled " + roll + "!");
        if (roll > BETTING_ODDS) {
            storeBalance += changeBalance;
            System.out.println("You won " + changeBalance + "!");
        } else {
            storeBalance -= changeBalance;
            System.out.println("You lost " + changeBalance + "!");
        }
        System.out.println("Your balance is now: " + storeBalance);
        // END BET


        balance.remove(inputIndex);
        balance.add(inputIndex, storeBalance);

        System.out.println();

        System.out.println("Name: " + name.get(name.indexOf(inputName)));
        System.out.println("Balance: " + balance.get(name.indexOf(inputName)));

        System.out.println();

        PrintStream output = new PrintStream(new File(outFile));

        for (int i = 0; i < name.size(); i++) {
            output.println(name.get(i) + " " + balance.get(i));
        }

        System.out.println("done!");
    }
}