import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Exercicio3 {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new FileReader("Exercicio3-input.txt"));
        String[] values = sc.nextLine().split(" ");
        int[] arr = new int[values.length];
        for (int i = 0; i < arr.length; i++)
            arr[i] = Integer.parseInt(values[i]);
        List<List<Integer>> subsets = gerarSubconjuntos(arr, null, null, null, null);
        System.out.println(subsets);
        sc.close();
    }

    static List<List<Integer>> gerarSubconjuntos(int[] nums, Integer max_size, Integer min_size,
            Boolean distinct_only,
            Boolean sort_subsets) {
        List<List<Integer>> result = new ArrayList<>();

        max_size = max_size == null ? nums.length : max_size;
        min_size = min_size == null ? 0 : min_size;
        distinct_only = distinct_only == null ? false : distinct_only;
        sort_subsets = sort_subsets == null ? false : sort_subsets;

        generateSubsets(nums, 0, new ArrayList<>(), result, max_size, min_size, distinct_only);

        if (sort_subsets) {
            result.sort(Comparator.comparingInt(List::size));
            result.forEach(list -> Collections.sort(list));
        }

        return result;
    }

    private static void generateSubsets(int[] nums, int start, List<Integer> current, List<List<Integer>> result,
            Integer max_size, Integer min_size, Boolean distinct_only) {

        if (current.size() >= min_size && current.size() <= max_size) {
            if (distinct_only) {
                Set<Integer> set = new HashSet<>(current);
                if (set.size() == current.size()) {
                    result.add(new ArrayList<>(current));
                }
            } else {
                result.add(new ArrayList<>(current));
            }
        }

        for (int i = start; i < nums.length; i++) {
            if (distinct_only && current.contains(nums[i])) continue;
            current.add(nums[i]);
            generateSubsets(nums, i + 1, current, result, max_size, min_size, distinct_only);
            current.remove(current.size() - 1);
        }
    }

}
