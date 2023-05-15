import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Nam {
    private Cash[] b;
    private final int[] nam = new int[]{20, 50, 100};

    public Nam(int n) {
        b = new Cash[nam.length];
        Random r = new Random();//для автозаполнения банкомата
        for (int i = 0; i < n; i++) {
            b[i] = new Cash(nam[i], r.nextInt(100) + 1);
        }
    }

    public void setCash() {
        System.out.println("Идет заправка банкомата...");
        Scanner sca = new Scanner(System.in);
        for (int i = 0; i < nam.length; i++) {
            System.out.println("ВВедите количество купюр номиналом " + nam[i] + " byn.");
            int n = sca.nextInt();
            if (b[i] != null) {
                b[i].addCash(n);
            } else {
                b[i] = new Cash(nam[i], n);
            }
        }
//        sca.close();
    }

    public void getCash() {
        System.out.print("В банкомате купюры номиналом: ");
        printNam();
        System.out.println("\nВВедите суму");
        Scanner scan = new Scanner(System.in);
        int suma = scan.nextInt();
//        scan.close();
        System.out.println(calc(suma) ? "\n\n" : "операция невозможна\n\n");
    }

    public void printNam() {
        for (int i = 0; i < b.length; i++) {
            if (b[i] != null && b[i].isMoney()) {
                System.out.print(b[i].getNominal() + ". ");
            }
        }
    }

    public boolean calc(int suma) {
        ArrayList<Loc> l = new ArrayList<Loc>();
        if (suma % 10 != 0) {
            return false;
        } else {
            for (int i = b.length; i > 0; i++) {
                if (b[i] != null) {
                    //провепяеи хватит ли денег в банкомате
                    int request = suma % b[i].getNominal();
                    if (b[i].CashVNorme(request)) {//денег хватает
                        l.add(new Loc(i, request));
                        suma -= request * b[i].getNominal();
                    }
                    if (suma == 0) {
                        System.out.println("Не достаточно средств");
                        break;
                    }
                }
            }
            if (suma != 0) {
                return false;
            } else {
                //Выдаем сумму
                System.out.println("Вы поллучили сумму купюрами:");
                for (int i = 0; i < l.size(); i++) {
                    b[l.get(i).position].getMoney(l.get(i).n);
                }
                return true;
            }
        }
    }

    protected static class Cash {
        private final int nominal;
        private int n;

        public Cash(int nom, int n) {
            nominal = nom;
            this.n = n;
        }

        private void addCash(int n) {
            this.n += n;
        }

        private boolean isMoney() {
            return n > 0;
        }

        private int getNominal() {
            return nominal;
        }

        private boolean CashVNorme(int request) {
            return request <= n;
        }

        private void getMoney(int n) {
            this.n -= n;
            System.out.println(nominal + "byn. = " + n);
        }
    }

    protected static class Loc {
        int position;
        int n;

        public Loc(int p, int nn) {
            position = p;
            n = nn;
        }
    }
}
