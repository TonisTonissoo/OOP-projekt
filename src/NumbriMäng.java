import java.util.Random;
import java.util.Scanner;

public class NumbriMäng implements Mäng{
    private int randomNumber;
    private int maxRange;
    private int numberOfAttempts;

    public NumbriMäng(int maxRange, int numberOfAttempts) {
        this.maxRange = maxRange;
        this.numberOfAttempts = numberOfAttempts;
        generateRandomNumber();
    }

    private void generateRandomNumber() {
        Random random = new Random();
        this.randomNumber = random.nextInt(maxRange) + 1;
    }

    public void alustaMängu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Tere tulemast numbrimängu!");
        System.out.println("Ma valin juhusliku numbri vahemikus 1 kuni " + maxRange + ". Proovi ära arvata!");

        int guess;
        boolean hasGuessed = false;

        for (int i = 0; i < numberOfAttempts; i++) {
            System.out.println("Sisesta oma pakkumine (" + (numberOfAttempts - i) + " katset jäänud):");
            guess = scanner.nextInt();

            if (guess == randomNumber) {
                hasGuessed = true;
                break;
            } else if (guess < randomNumber) {
                System.out.println("Arvuti mõeldud number on suurem.");
            } else {
                System.out.println("Arvuti mõeldud number on väiksem.");
            }
        }

        if (hasGuessed) {
            System.out.println("Õige arvamus! Arv oli: " + randomNumber);
        } else {
            System.out.println("Kahjuks ei arvanud sa õigesti. Arv oli: " + randomNumber);
        }

    }
}