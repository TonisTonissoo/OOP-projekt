import java.util.Scanner;

class Täringumäng implements Mäng {
    private int mängijapunktid;
    private int arvutajapunktid;

    public void alustaMängu() {
        mängijapunktid = 0;
        arvutajapunktid = 0;

        while (true) {
            System.out.println("Sinu punktid: " + mängijapunktid);
            System.out.println("Arvuti punktid: " + arvutajapunktid);
            System.out.println("Vajuta 'enter', et veeretada täringut...");
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();

            int mängijatulemus = veeretaTäring();
            int arvutajatulemus = veeretaTäring();

            System.out.println("Sina veeretasid: " + mängijatulemus);
            System.out.println("Arvuti veeretas: " + arvutajatulemus);

            mängijapunktid += mängijatulemus;
            arvutajapunktid += arvutajatulemus;

            if (mängijapunktid >= 100 || arvutajapunktid >= 100) {
                lõpetaMäng();
                break;
            }
        }
    }

    private int veeretaTäring() {
        return (int) (Math.random() * 6) + 1;
    }

    private void lõpetaMäng() {
        System.out.println("Mäng läbi!");
        if (mängijapunktid >= 100) {
            System.out.println("Palju õnne, võitsid mängu!");
        } else {
            System.out.println("Arvuti võitis mängu. Proovi uuesti!");
        }
    }
}