import java.util.Scanner;

public class Main {
    private static Nam atm;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        atm =new Nam(3);
        run();


    }

    private static void run() throws NumberFormatException{
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Выберите действие: 1 - заправить терминал, 2 - снять деньги, 0 - выход");
            int code = sc.nextInt();
            try {
                if (code == 0) {
                    break;
                } else if (code == 1) {
                    atm.setCash();
                } else if (code == 2) {
                    atm.getCash();
                } else {
                    System.err.println("Неизвестная операция");
                }
            } catch (NumberFormatException e) {
                System.err.println("Произошла ошибка!");
            }
        }
//        sc.close();
    }
}
