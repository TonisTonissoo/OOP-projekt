import java.util.Scanner;

public class Casino {
    public static void main(String[] args) {
        System.out.println("Tere tulemast kasiinosse!");
        while (true){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Millist mängu soovite mängida? (täringud/numbrimäng/exit)");
            String vastus = scanner.nextLine();
            if (vastus.equalsIgnoreCase("täringud")) {
                Mäng täringumäng = new Täringumäng();
                täringumäng.alustaMängu();
            } else if (vastus.equalsIgnoreCase("numbrimäng")) {
                Mäng numbriMäng = new NumbriMäng(5, 3);
                numbriMäng.alustaMängu();
            } else if (vastus.equalsIgnoreCase("exit")) {
                System.out.println("Head aega!");
                scanner.close();
                break;
            }
            else {
                System.out.println("Sellist mängu meil ei ole.");
            }
        }

    }
}

