import java.util.Random;
import java.util.Scanner;

/**
 * kood defineerib klassi NumbriMäng, mis implementeerib liidest Mäng.
 * See käivitab numbrimängu, kus arvuti genereerib juhusliku numbri vahemikus 1 kuni antud maksimaalse arvu,
 * ning mängija peab seda arvamise teel ära arvama piiratud arvu katsetega.
 * Mäng lõpeb, kui mängija arvab õigesti või tema katsete arv lõpeb,
 * väljastades vastavalt vastava teate.
 */

public class NumbriMäng implements Mäng{
    private int suvalineNr;
    private int maxArv;
    private int katseteArv;

    public NumbriMäng(int maxArv, int katseteArv) {
        this.maxArv = maxArv;
        this.katseteArv = katseteArv;
        suvalineArv();
    }

    private void suvalineArv() {
        Random random = new Random();
        this.suvalineNr = random.nextInt(maxArv) + 1;
    }

    public void alustaMängu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Tere tulemast numbrimängu!");
        System.out.println("Ma valin juhusliku numbri vahemikus 1 kuni " + maxArv + ". Proovi ära arvata!");

        int arvamine;
        boolean onArvanud = false;

        for (int i = 0; i < katseteArv; i++) {
            System.out.println("Sisesta oma pakkumine (" + (katseteArv - i) + " katset jäänud):");
            arvamine = scanner.nextInt();

            if (arvamine == suvalineNr) {
                onArvanud = true;
                break;
            } else if (arvamine < suvalineNr) {
                System.out.println("Arvuti mõeldud number on suurem.");
            } else {
                System.out.println("Arvuti mõeldud number on väiksem.");
            }
        }

        if (onArvanud) {
            System.out.println("Õige arvamus! Arv oli: " + suvalineNr);
        } else {
            System.out.println("Kahjuks ei arvanud sa õigesti. Arv oli: " + suvalineNr);
        }
    }
}