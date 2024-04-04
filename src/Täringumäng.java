import java.util.Scanner;

/*
Antud Java-kood defineerib klassi nimega "Täringumäng", mis implementeerib liidest "Mäng".
Programm võimaldab mängida täringumängu, kus mängija ja arvuti veeretavad täringut.
Mäng jätkub seni, kuni üks mängijatest (kas mängija või arvuti) saavutab või ületab 100 punkti.
Mängijale kuvatakse tema punktide arv, arvuti punktide arv ning juhend täringu veeretamiseks.
Mäng lõpeb, kui mängija või arvuti saavutab või ületab 100 punkti, ning vastavalt sellele väljastatakse vastav teade võitja kohta.
 */
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