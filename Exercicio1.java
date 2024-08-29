import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Exercicio1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        List<String> lista = new ArrayList<>();
        int n;
        System.out.print("Insira o valor de N: ");
        n = sc.nextInt();
        for (int i = 0; i < n; i++)
            lista.add(sb.append("*").toString());
        System.out.println(lista);
        sc.close();
    }

}
