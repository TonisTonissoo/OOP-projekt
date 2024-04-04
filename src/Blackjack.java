import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;

/*
Mängureeglid:
    Kaartide tõmbamist alustab mängija ning kui tema on lõpetanud hakkab diiler kaarte tõmbama
    Eesmärk on saada kokku 21 punkti
    Kaartide väärtused:
        Numbritega kaartide väärtusteks on nende number,
        Piltidega kaartide väärtuseks on 10 välja arvatud ässal
        Ässa väärtus on 1 või 11
    Ässa väärtus muutub siis kui mängija või diiler on lõhki minemas
    Diiler ei tõmba uut kaarti kui tal on koos üle 16ne
    Kui mängija läheb üle 21 või mängijal on kokku vähem kui diileril on ta kaotanud
    Diiler läheb ka lõhki kui tal on üle 21
    Kui mängijal on diilerist rohkem kokku on ta võitnud
    Viigi korral on viik
    Mängijale ja diilerile jagatakse mängu alguses 2 kaarti ning hiljem on võimalik soovi korral kaarte juurde saada
Programm küsib kasutajalt kas ta soovib kaarti endale juurde(hit) või soovib jääda oma olemasolevate kaartidega(pass)
Programm loeb failist(kaardid.txt) kaardid ning annab neile funktsiooni abil väärtuse
Programmis olevad funktsioonid aitavad arvutada välja mängija ja diileri kaartide summa
Lõpus väljastatakse, kes võitis ning mis oli seis
 */
public class Blackjack implements Mäng {
    public static List<String> loeFailist(String failiNimi) throws FileNotFoundException {
        List<String> kaardid = new ArrayList<>();
        File file = new File(failiNimi);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()){
            String rida = scanner.nextLine();
            kaardid.add(rida);
        }
        return kaardid;
    }

    // Kaartidele väärtuste andmine
    public static int väärtus(String card) {
        String[] kaart = card.split(" ");
        if (kaart[1].equals("poiss") || kaart[1].equals("emand") || kaart[1].equals("kuningas")) {
            return 10;
        } else if (kaart[1].equals("äss")) {
            return 11;
        } else {
            return Integer.parseInt(kaart[1]);
        }
    }

    // Kaartide arvutamine
    public static int arvutus(int summa, int kaart) {
        summa += kaart;
        if (summa > 21 && kaart == 11) {
            summa -= 10;
        }
        return summa;
    }

    // Uue kaardi tõmbamine ning selle pakkist eemaldamine
    public static String tõmme(List<String> kaardid) {
        Random rand = new Random();
        int index = rand.nextInt(kaardid.size());
        return kaardid.remove(index);
    }

    // Ässa tuvastamine
    public static int bust(List<String> a) {
        int skaardid = 0;
        while (skaardid < a.size()) {
            String b = a.get(skaardid);
            String[] c = b.split(" ");
            if (c[1].equals("äss")) {
                return 10;
            }
            skaardid++;
        }
        return 0;
    }

    // Ässa hävitamine
    public static String gone(List<String> a) {
        int skaardid = a.size();
        while (skaardid >= 0) {
            skaardid--;
            String b = a.get(skaardid);
            String[] c = b.split(" ");
            if (c[1].equals("äss")) {
                return b;
            }
        }
        return null;
    }

    @Override
    public void alustaMängu() {
        List<String> kaardid = null;
        try {
            kaardid = loeFailist("kaardid.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        List<String> mängija = new ArrayList<>();
        List<String> diiler = new ArrayList<>();

        // Mõlemale antakse kaks kaarti
        mängija.add(tõmme(kaardid));
        diiler.add(tõmme(kaardid));
        mängija.add(tõmme(kaardid));
        diiler.add(tõmme(kaardid));

        // Mäng
        int mängijakaardid = arvutus(arvutus(0, väärtus(mängija.get(0))), väärtus(mängija.get(1)));
        int diilerikaardid = arvutus(arvutus(0, väärtus(diiler.get(0))), väärtus(diiler.get(1)));
        System.out.println("Teie kaardid on " + mängija.get(0) + " ja " + mängija.get(1));
        System.out.println("Kokku on teil " + mängijakaardid);
        System.out.println("Diileri esimene kaart on " + diiler.get(0) + " ja teine kaart laual tagurpidi");

        Scanner input = new Scanner(System.in);
        String küss;
        int a = 1;

        while (mängijakaardid < 21) {
            System.out.print("Hit või pass?\n");
            küss = input.nextLine();
            while (küss.equals("hit")) {
                mängija.add(tõmme(kaardid));
                mängijakaardid = arvutus(mängijakaardid, väärtus(mängija.get(a)));
                System.out.println("Teie kaart on " + mängija.get(a));
                a++;
                if (31 >= mängijakaardid && mängijakaardid >= 22) {
                    if (bust(mängija) == 10) {
                        mängijakaardid -= 10;
                        mängija.remove(gone(mängija));
                        a--;
                        System.out.println("Kokku on teil " + mängijakaardid);
                        continue;
                    } else {
                        System.out.println("Kokku on teil " + mängijakaardid);
                        break;
                    }
                } else if (mängijakaardid == 21) {
                    küss = "pass";
                    break;
                } else {
                    System.out.println("Kokku on teil " + mängijakaardid);
                    break;
                }
            }

            // Diileri osa
            if (küss.equals("pass")) {
                if (22 > diilerikaardid && diilerikaardid > mängijakaardid) {
                    break;
                } else if (16 > diilerikaardid && diilerikaardid < mängijakaardid) {
                    while (diilerikaardid < mängijakaardid && mängijakaardid < 22) {
                        diiler.add(tõmme(kaardid));
                        int b = diiler.size() - 1;
                        diilerikaardid = arvutus(diilerikaardid, väärtus(diiler.get(b)));
                        System.out.println("Diileri kaart on " + diiler.get(b));
                        if (21 >= diilerikaardid && diilerikaardid > mängijakaardid) {
                            break;
                        } else if (diilerikaardid >= 16) {
                            if (bust(diiler) == 10) {
                                diiler.remove(gone(diiler));
                                diilerikaardid -= 10;
                                b = diiler.size() - 1;
                            } else {
                                break;
                            }
                        }
                    }
                } else {
                    break;
                }
            }
        }

        // Kas ja kes võitis või kaotas
        if (mängijakaardid <= 21) {
            if (diilerikaardid < mängijakaardid) {
                System.out.println("Mängijal on kokku " + mängijakaardid + " ja diileril on kokku " + diilerikaardid);
                System.out.println("Palju Õnne!\nTe võitsite!");
            } else if (diilerikaardid == mängijakaardid) {
                System.out.println("Mängijal on kokku " + mängijakaardid + " ja diileril on kokku " + diilerikaardid);
                System.out.println("Viik");
            } else if (22 > diilerikaardid && diilerikaardid > mängijakaardid) {
                System.out.println("Mängijal on kokku " + mängijakaardid + " ja diileril on kokku " + diilerikaardid);
                System.out.println("Te kaotasite!");
            } else {
                System.out.println("Mängijal on kokku " + mängijakaardid + " ja diileril on kokku " + diilerikaardid);
                System.out.println("Palju õnne!\nTe võitsite!");
            }
        } else {
            System.out.println("Mängijal on kokku " + mängijakaardid + " ja diileril on kokku " + diilerikaardid);
            System.out.println("Läksite lõhki!\nTe kaotasite!");
        }
    }
}
