import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Exercicio2 {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new FileReader("Exercicio2-input.txt"));
        String[] values = sc.nextLine().split(" ");
        int[] arr = new int[values.length];
        for (int i = 0; i < arr.length; i++)
            arr[i] = Integer.parseInt(values[i]);
        System.out.println(getMininumDifference(arr, null, null, null));
        sc.close();
    }

    static List<Pair> getMininumDifference(int[] arr, Boolean allow_duplicates, Boolean sorted_pairs,
            Boolean unique_pairs) {
        List<Pair> pairs = new ArrayList<>();
        Integer minDifference = null, difference;

        allow_duplicates = allow_duplicates == null ? true : allow_duplicates;
        sorted_pairs = sorted_pairs == null ? false : sorted_pairs;
        unique_pairs = unique_pairs == null ? false : unique_pairs;

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (i == j)
                    continue;
                Pair pair = new Pair(arr[i], arr[j]);
                difference = pair.getAbsoluteDifference();

                if (minDifference == null || difference < minDifference) {
                    minDifference = difference;
                    pairs.clear();
                }

                if (minDifference == difference) {
                    if (unique_pairs && pairs.contains(pair.reverted()))
                        continue;
                    if (allow_duplicates || !pairs.contains(pair))
                        pairs.add(pair);
                }
            }
        }

        if (sorted_pairs)
            pairs.sort((a, b) -> a.compareTo(b));
        return pairs;
    }

}

class Pair implements Comparable<Pair> {
    private int a, b;

    Pair(int a, int b) {
        this.a = a;
        this.b = b;
    }

    int getAbsoluteDifference() {
        return Math.abs(a - b);
    }

    Pair reverted() {
        return new Pair(b, a);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Pair) {
            Pair other = (Pair) obj;
            return this.a == other.a && this.b == other.b;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.a + this.b;
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", this.a, this.b);
    }

    @Override
    public int compareTo(Pair other) {
        if (this.a < other.a)
            return -1;
        if (this.a > other.a)
            return 1;
        if (this.b < other.b)
            return -1;
        if (this.b > other.b)
            return 1;
        return 0;
    }
}
