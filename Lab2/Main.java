import java.util.Arrays;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.lang.Math;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        System.out.println("Лабораторная работа №2");
    }
}

class Task1 {
    public static String getLargestUnique(String instr)
    {
        Map<Character, Integer> visited = new HashMap<>();
        String output = "";
        for (int start=0, end=0; end < instr.length(); end++) {
            char currChar = instr.charAt(end);
            if (visited.containsKey(currChar)) {
                start = Math.max(visited.get(currChar)+1, start);
            }
            if (output.length() < end - start + 1) {
                output = instr.substring(start, end+1);
            }
            visited.put(currChar, end);
        }
        return output;
    }

    public static void main(String[] args) {
        System.out.println("№1. Наибольшая подстрока без повторяющихся символов");

        Scanner input = new Scanner(System.in);
        String tekstr = input.nextLine();

        String output = getLargestUnique(tekstr);
        System.out.println("Наибольшая подстрока с уникальными символами в данной строке: " + output);

        input.close();
    }
}

class Task2 {
    public static boolean isSorted(int[] arr)
    {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] <= arr[i+1]) {
                return true;
            }
        }
        return false;
    }

    public static int[] mergeSortedPair(int[] arr1, int[] arr2)
    {
        int[] merged = IntStream.concat(Arrays.stream(arr1), Arrays.stream(arr2)).toArray();
        if (!(isSorted(arr1) && isSorted(arr2))) {
            merged = null;
        }
        else {
            Arrays.sort(merged);
        }
        return merged;
    }

    public static void main(String[] args) {
        System.out.println("2. Объединить два отсортированных массива");
        Scanner data = new Scanner(System.in);

        int m = data.nextInt();
        int n = data.nextInt();
        int[] mas1 = new int[m];
        int[] mas2 = new int[n];

        for (int i = 0; i < mas1.length; i++) {
            mas1[i] = data.nextInt();
        }
        System.out.println("-----");
        for (int j = 0; j < mas2.length; j++) {
            mas2[j] = data.nextInt();
        }

        int[] result = mergeSortedPair(mas1, mas2);

        if (result == null) {
            System.out.println("Исходные массивы не отсортированы!");
        } else {
            System.out.print("{ ");
            for (int k : result) {
                System.out.print(k+" ");
            }
            System.out.print("}");
        }
        data.close();
    }
}

class Task3 {
    //алгоритм Кадане
    public static int findMaxSubarrSum(int[] arr)
    {
        // сохраняет максимальный суммарный подмассив, найденный на данный момент
        int maxSoFar = 0;

        // сохраняет максимальную сумму подмассива, заканчивающегося на текущей позиции
        int maxEndingHere = 0;

        for (int el: arr)
        {
            maxEndingHere = maxEndingHere + el;

            if (maxEndingHere < 0) {
                maxEndingHere = 0;
            } else {
                maxSoFar = Integer.max(maxSoFar, maxEndingHere);
            }
        }

        return maxSoFar;
    }

    public static void main(String[] args) {
        System.out.println("3. Найти наибольшую сумму подмассива");
        Scanner ints = new Scanner(System.in);

        int n = ints.nextInt();
        int[] mas = new int[n];
        for (int i = 0; i < mas.length; i++) {
            mas[i] = ints.nextInt();
        }
        System.out.print("{ ");
        for (int el: mas) System.out.print(el+" ");
        System.out.print("}\n");

        System.out.println("Сумма последовательного подмассива с " +
                           "наибольшей суммой: " + findMaxSubarrSum(mas));
    }
}

class Task4 {
    public static void rightRotate(int[][] mat, int n)
    {
        for (int i=0; i<n; i++) {
            for (int j=i; j<n; j++) {
                int temp = mat[i][j];
                mat[i][j] = mat[j][i];
                mat[j][i] = temp;
            }
        }

        for (int i=0; i<n; i++) {
            int low = 0;
            int high = n-1;

            while (low<high) {
                int temp = mat[i][low];
                mat[i][low] = mat[i][high];
                mat[i][high] = temp;

                low++;
                high--;
            }
        }

        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                System.out.print(mat[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        System.out.println("4. Повернуть двумерный массив на " +
                "90 градусов по часовой стрелке");
        Scanner set = new Scanner(System.in);

        int n = set.nextInt();
        int[][] matrix = new int[n][n];
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                matrix[i][j] = set.nextInt();
            }
        }
        System.out.print("----------------\n");
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
        System.out.print("----------------\n");
        rightRotate(matrix, n);

        set.close();
    }
}

class Task5 {
    public static int[] pairEqualsToTarget(int[] arr, int target)
    {
        HashMap<Integer, Integer> elementIdMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < arr.length; i++) {
            if (elementIdMap.containsKey(target - arr[i]))
            {
                return new int[]{arr[elementIdMap.get(target - arr[i])], arr[i]};
            }
            elementIdMap.put(arr[i], i);
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println("5. Найти пару элементов в массиве, сумма которых " +
                "равна заданному числу");

        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] mas = new int[n];
        for (int i = 0; i < n; i++) {
            mas[i] = input.nextInt();
        }

        int X = input.nextInt();
        int[] pairEqualsToX = pairEqualsToTarget(mas, X);
        for (int i = 0; i < pairEqualsToX.length; i++) {
            System.out.println(" " + pairEqualsToX[i] + " ");
        }

        input.close();
    }
}

class Task6 {
    public static int sumWholeMatrix(int[][] mat)
    {
        int sum = 0;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                sum += mat[i][j];
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println("6. Найти сумму всех элементов в двумерном массиве");

        Scanner data = new Scanner(System.in);
        int n = data.nextInt();
        int m = data.nextInt();
        int[][] mat = new int[n][m];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                mat[i][j] = data.nextInt();
            }
        }

        int result = sumWholeMatrix(mat);
        System.out.println(result);

        data.close();
    }
}

class Task7
{
    public static int[] maxRowsElements(int[][] mat)
    {
        int[] maxis = new int[mat.length];

        for (int i = 0; i < mat.length; i++) {
            maxis[i] = Integer.MIN_VALUE;
            for (int j = 0; j < mat[i].length; j++) {
                maxis[i] = Math.max(maxis[i], mat[i][j]);
            }
        }

        return maxis;
    }

    public static void main(String[] args) {
        System.out.println("7. Найти максимальный элемент в каждой " +
                "строке двумерного массива");

        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int m = input.nextInt();
        int[][] mat = new int[n][m];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                mat[i][j] = input.nextInt();
            }
        }

        int[] rowsMaxes = maxRowsElements(mat);
        for (int i = 0; i < rowsMaxes.length; i++) {
            System.out.printf(" " + rowsMaxes[i] + " ");
        }

        input.close();
    }
}

class Task8
{
    public static void leftRotate(int[][] mat, int n)
    {
        for (int i=0; i<n; i++) {
            for (int j=i; j<n; j++) {
                int temp = mat[i][j];
                mat[i][j] = mat[j][i];
                mat[j][i] = temp;
            }
        }

        for (int i=0; i<n; i++) {
            int low = 0;
            int high = n-1;

            while (low<high) {
                int temp = mat[low][i];
                mat[low][i] = mat[high][i];
                mat[high][i] = temp;

                low++;
                high--;
            }
        }

        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                System.out.print(mat[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        System.out.println("8. Повернуть двумерный массив на " +
                "90 градусов против часовой стрелки");

        Scanner set = new Scanner(System.in);
        int n = set.nextInt();
        int[][] mat = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                mat[i][j] = set.nextInt();
            }
        }

        System.out.print("----------------\n");
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                System.out.print(mat[i][j]+" ");
            }
            System.out.println();
        }
        System.out.print("----------------\n");
        leftRotate(mat, n);

        set.close();
    }
}