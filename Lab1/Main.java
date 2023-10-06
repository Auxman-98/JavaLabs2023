import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Math.floor;
import static java.lang.Math.pow;

public class Main {
     public static void main(String[] args){
        System.out.println("Лабораторная работа №1");
    }
}

class Task1 {
    public static void main(String[] args) {
        System.out.println("1. Сиракузская последовательность");

        // Входные данные: число, вводимое с клавиатуры, и счётчик
        Scanner in = new Scanner(System.in);
        int start = in.nextInt();
        int count = 0;
        if (start != 1) {
            if (start < 1) {
                // Обработка ввода неположительного числа
                System.out.println("Введено неположительное число! Попробуйте еще *раз*:");
                return;
            }
            while (start > 1) {
                // Условия сиракузской гипотезы
                if (start%2 == 0) {
                    start /= 2;
                }
                else {
                    start = (start*3) + 1;
                }
                count++;
            }
        }

        System.out.println(count);
        in.close();
    }
}

class Task2 {
    public static void main(String[] args) {
        System.out.println("2. Сумма ряда");

        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] terms = new int[n];
        for (int i = 0; i < n; i++) terms[i] = input.nextInt();

        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (i%2 == 0) {
                sum += terms[i];
            }
            else {
                sum -= terms[i];
            }
        }

        System.out.print("Сумма ряда: " + sum);
        input.close();
    }
}

class Task3 {
    public static void main(String[] args) {
        System.out.println("3. Поиски клада");

        Scanner map = new Scanner(System.in);
        int[] treasure = new int[]{0, 0};
        System.out.println("Введите здесь координаты клада:");
        for (int cord : treasure){
            cord = map.nextInt();
        }
        int[] pnt = new int[]{0, 0};

        System.out.println("Введите здесь указания карты:");
        map.nextLine();
        int ins = 0;

        switch(map.next()) {
            case "север":
                int stepsN = map.nextInt();
                if (stepsN <= 0) {
                    System.out.println("Не натуральное число!");
                    return;
                }
                else {
                    map.nextLine();
                    ins++;

                    pnt[0] += stepsN;
                    if (pnt[0] > treasure[0]){
                        pnt[0] -= stepsN;
                        ins--;
                    }
                }
            case "восток":
                int stepsE = map.nextInt();
                if (stepsE <= 0) {
                    System.out.println("Не натуральное число!");
                    return;
                } else {
                    map.nextLine();
                    ins++;

                    pnt[1] += stepsE;
                    if (pnt[1] > treasure[1]){
                        pnt[1] -= stepsE;
                        ins--;
                    }
                }
            case "юг":
                int stepsS = map.nextInt();
                if (stepsS <= 0) {
                    System.out.println("Не натуральное число!");
                    return;
                } else {
                    map.nextLine();
                    ins++;

                    pnt[0] -= stepsS;
                    if (pnt[0] < treasure[0]){
                        pnt[0] += stepsS;
                        ins--;
                    }
                }
            case "запад":
                int stepsW = map.nextInt();
                if (stepsW <= 0) {
                    System.out.println("Не натуральное число!");
                    return;
                } else {
                    map.nextLine();
                    ins++;

                    pnt[1] -= stepsW;
                    if (pnt[1] < treasure[1]){
                        pnt[1] += stepsW;
                        ins--;
                    }
                }
            case "стоп":
                System.out.println(ins);
            default:
                System.out.println("Не указано направление!");
        }
    }
}

class Task4 {
    public static void main(String[] args) {
        System.out.println("4. Логистический максимин");
        Scanner in = new Scanner(System.in);
        
        in.close();
    }
}

class Task5 {
    public static void main(String[] args) {
        System.out.println("5. Дважды чётное число");
        Scanner dial = new Scanner (System.in);

        int input = dial.nextInt();
        System.out.println(input);

        if (input<100 || input > 999) {
            System.out.println("Число не трёхзначное!");
            return;
        } else {
            int diff = 2;
            double s = floor(input/pow(10,diff));
            double[] digits = new double[3];

            digits[0] = s;
            for (int i=1; i < digits.length; i++) {
                while (diff >= 1) {
                    s = floor(input/pow(10, diff-1))%10;
                    digits[i] = s;
                    --diff;
                }
            }

            double sum = 0;
            for (int i=0; i<digits.length; i++) {
                sum += digits[i];
            }
            double mult = 1;
            for (int i=0; i<digits.length; i++) {
                mult *= digits[i];
            }

            if (sum%2 == 0 && mult%2 == 0) {
                System.out.println("Данное трёхзначное число - дважды чётное!");
            } else System.out.println("Данноё трёхзначное число не является дважды чётным!");
        }

        dial.close();
    }
}