package sk.matus.asos.java8streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 *
 * @author Matus
 */
public class MainApp {

    public static void main(String[] args) throws IOException {
//        example1();
//        example2();
//        example3();
    }

    public static void example1() {
        
        List<Account> accs = initData();
        
        example1A(accs);
        example1B(accs);
        example1C(accs);
        example1D(accs);
    }

    public static void example2() throws IOException {

        example2A(initFileData());
        example2B(initFileData());
        example2C(initFileData());
        example2D(initFileData());
        example2E(initFileData());
        example2F(initFileData());
        example2G(initFileData());
        example2H(initFileData());
    }
    
     public static void example3() {
         example3A();
         average();
     }

    public static void example3A() {
        int n = 1000000;

        double res = IntStream.range(0, n).boxed().map(i -> {
            double x = ((double) i) / n;
            return x * x;
        }).reduce(0.0, (a, b) -> a + b);

        System.out.println(res / n);

        Double sum = 0.0;
        for (double i = 0; i < n; i++) {
            double x = i / n;
            double y = x * x;
            sum = sum + y;
        }
        System.out.println(sum / n);
    }
    
    static public void average() {
        Stream<Double> numbers = Stream.generate(() -> Math.random()).limit(2);
        double average = numbers.mapToDouble(a -> a).average().orElse(0);
        System.out.println("Priemer=" + average);
    }

    private static void example2A(Stream<String> lines) {
        lines.forEach(System.out::println);
    }

    private static void example2B(Stream<String> lines) {
        List<String> linesList = lines.collect(Collectors.toList());
        linesList.forEach(System.out::println);
    }

    private static void example2C(Stream<String> lines) {
        lines.map(String::length).forEach(System.out::println);
    }

    private static void example2D(Stream<String> lines) {
        System.out.println(lines.map(String::length).reduce(0, (a, b) -> a + b));
    }

    private static void example2E(Stream<String> lines) {
        System.out.println(lines.map(String::length).reduce(0, (a, b) -> Math.max(a, b)));
    }

    private static void example2F(Stream<String> lines) {
        lines.filter(s -> !s.isEmpty()).forEach(System.out::println);
    }

    private static void example2G(Stream<String> lines) {
        System.out.println(lines.flatMap(l -> Stream.of(l.split(", "))).distinct().count());
    }

    private static void example2H(Stream<String> lines) {
        System.out.println(lines.map(l -> l.split(", "))
                .flatMap(Stream::of)
                .filter(s -> !s.isEmpty())
                .distinct()
                .count());
    }

    private static void example1A(List<Account> accs) {
        accs.forEach(acc -> {
            acc.addInterest();
        });

        accs.forEach(acc -> {
            System.out.println(acc);
        });
    }

    private static void example1B(List<Account> accs) {
        accs.forEach(a -> a.addInterest());
        accs.forEach(System.out::println);
    }

    private static void example1C(List<Account> accs) {
        accs.parallelStream().forEach(a -> {
            a.addInterest();
            System.out.println(a);
        });
    }

    private static void example1D(List<Account> accs) {
        Stream<Double> sums = accs.stream().map(Account::getBalance);
        Double sum = sums.reduce(0.0, (a, b) -> a + b);
        System.out.println("Sum: " + sum);
    }

    private static List<Account> initData() {
        List<Account> accs = new ArrayList<>();
        accs.add(new Account("U001", 100));
        accs.add(new Account("U002", 200));
        accs.add(new Account("U003", 300));
        accs.add(new Account("U004", 400));

        return accs;
    }

    private static Stream<String> initFileData() throws IOException {
        return Files.lines(Paths.get("src/main/resources/accounts.csv"));
    }
}
