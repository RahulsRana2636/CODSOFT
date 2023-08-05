
import java.util.Random;
import java.util.Scanner;

class guessNumber {
    public int num;
    public int userInputNumber;
    public int noOfGuesses = 0;

    public int getNoOfGuesses() {
        return noOfGuesses;
    }

    public void setNoOfGuesses(int noOfGuesses) {
        this.noOfGuesses = noOfGuesses;
    }

    guessNumber() {
        Random rand = new Random();
        this.num = rand.nextInt(100);
    }

    void takeUserInput() { // this method is used to take a input of number from user
        System.out.println("Guess the number:");
        Scanner r = new Scanner(System.in);
        userInputNumber = r.nextInt();
    }

    boolean isCorrectNumber() { // isCorrectNumber() to detect whether the number entered by the user is correct
                                // or not
        noOfGuesses++;
        if (userInputNumber == num) {
            System.out.format("Yes you guessed it correct, it was %d\nYou guessed it in %d attempts", num, noOfGuesses);
            System.out.println();
            return true;
        } else if (userInputNumber < num) {
            System.out.println("Too low...");
        } else if (userInputNumber > num) {
            System.out.println("Too high...");
        }
        return false;
    }

}

public class number_Game {
    public static void main(String[] args) {
        Scanner p = new Scanner(System.in);

        int playAgain;

        do {
            guessNumber g = new guessNumber();
            System.out.println("You have only 7 attempts for guess the number:");
            boolean no = false;
            int attempts = 0;
            while (!no && attempts < 7) {
                attempts++;
                g.takeUserInput();
                no = g.isCorrectNumber();
                if (attempts == 7 && !no) {
                    System.out.println("You failed!  because your 7 attempts has been finished...");
                }
            }
            System.out.println("do you want, play again press 0");
            System.out.println("do you want exit from this game press any number except 0");
            playAgain = p.nextInt();
        } while (playAgain == 0);

    }
}
