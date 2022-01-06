package sk.matus.asos.spark01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.util.DoubleAccumulator;
import scala.Tuple2;

/**
 *
 * @author Matus
 */
public class MainApp {

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("Spark01").setMaster("local[2]");
        JavaSparkContext sc = new JavaSparkContext(conf);
        sc.setLogLevel("ERROR");

         List<Account> accs = initData();
        
//        example1(sc, accs);
//        example2(sc);
//        example3(sc, accs);
//        example4(sc, accs);
//        example5(sc, accs);
//        example6(sc, accs);
//        example7(sc, accs);
//        example8(sc, accs);
//        example9(sc, accs);
//        example10(sc, accs);
//        example11(sc, accs);
//        example12(sc);
//        example13(sc);
        
        sc.stop();
    }

    public static void example1(JavaSparkContext sc, List<Account> accs) {
        JavaRDD<Account> rdd = sc.parallelize(accs);
        rdd.foreach(a -> System.out.println("" + a));

        Double sum = rdd.map(Account::getBalance).reduce((a, b) -> a + b);
        System.out.println(sum);
    }

    public static void example2(JavaSparkContext sc) {
        JavaRDD<String> rdd = sc.textFile("src/main/resources/data.txt");

        System.out.println("Row count: " + rdd.count());
        System.out.println("First row: " + rdd.first());
        System.out.println("Data:");
        // on main node
        rdd.collect().forEach(System.out::println);
        // on worker nodes
        rdd.foreach(l -> System.out.println(l));

        System.out.println("\npocet riadkov ktore obsahuju slovo riadok");
        System.out.println(rdd.filter(l -> l.contains("riadok")).count());

        System.out.println("Vsetky riadky, ktore neobsahuju slovo riadok:");
        rdd.filter(l -> !l.contains("riadok")).foreach(l -> System.out.println(l));

        System.out.println("\ndlzky riadkov");
        rdd.map(l -> l.length()).foreach(ln -> System.out.println(ln));

        System.out.println("\ndlzka najdlhsieho riadku");
        System.out.println(rdd.map(String::length).reduce(Math::max));

        System.out.println("\nriadky a ich dlzky");
        rdd.mapToPair(l -> new Tuple2(l, l.length())).collectAsMap().forEach((k, v) -> {
            System.out.println(k + ":" + v);
        });

        System.out.println("\nnajdlhsi riadok");
        System.out.println(rdd.groupBy(l -> l.length()).sortByKey(false).first()._2);

        System.out.println("\nrozdeli riadky na slova - vystupná sada obsahuje slová");
        rdd.flatMap(l -> Arrays.asList(l.split(" ")).iterator()).foreach(s -> System.out.println(s));

        JavaRDD<String> words = rdd.flatMap(l -> Arrays.asList(l.split(" ")).iterator());

        System.out.println("\npocet roznych slov");
        System.out.println(words.distinct().count());

        System.out.println("\npocetnosti slov");
        words.countByValue().forEach((k, v) -> System.out.println(k + ":" + v));

        System.out.println("\npocet roznych slov kratsich ako 5");
        System.out.println(words.distinct().filter(w -> w.length() < 5).count());

        System.out.println("\npocet roznych slov pricom sa nerozlisuju male a velke pismena");
        System.out.println(words.map(w -> w.toLowerCase()).distinct().count());

        System.out.println("\npocetnosti slov pricom sa nerozlisuju male a velke pismena");
        words.map(w -> w.toLowerCase()).countByValue().forEach((k, v) -> System.out.println(k + ":" + v));
    }

    public static void example3(JavaSparkContext sc, List<Account> accs) {
        JavaRDD<Account> rdd = sc.parallelize(accs);

        JavaRDD<Account> rdd2 = rdd.map(u -> {
            u.addInterest();
            System.out.println("" + u);
            return u;
        });
        JavaRDD<Account> rdd3 = rdd2.map(u -> {
            u.addInterest();
            System.out.println("" + u);
            return u;
        });
        JavaRDD<Account> rdd4 = rdd3.map(u -> {
            u.addInterest();
            System.out.println("" + u);
            return u;
        });

        System.out.println(" calling collect...");
        rdd4.collect();
//        rdd4.first();
//        rdd4.count();
    }

    public static void example4(JavaSparkContext sc, List<Account> accs) {

        JavaRDD<Account> rdd = sc.parallelize(accs);
        rdd = rdd.map(u -> {
            u.addInterest(0.01);
            return u;
        });
        rdd = rdd.map(u -> {
            u.addInterest(0.01);
            return u;
        });
        rdd = rdd.map(u -> {
            u.addInterest(0.00);
            return u;
        });
        
        rdd.collect().forEach(a -> System.out.println(a.getBalance()));
    }

    public static void example5(JavaSparkContext sc, List<Account> accs) {

        JavaRDD<Account> rdd = sc.parallelize(accs);
        Broadcast<AccountConfig> acb = sc.broadcast(new AccountConfig(0.01));

        rdd = rdd.map(u -> {
            u.addInterest(acb.value());
            return u;
        });
        rdd = rdd.map(u -> {
            u.addInterest(acb.value());
            return u;
        });
        rdd = rdd.map(u -> {
            u.addInterest(acb.value());
            return u;
        });
        
        rdd.collect().forEach(a -> System.out.println(a.getBalance()));
    }

    public static void example6(JavaSparkContext sc, List<Account> accs) {
        // nefunkcne terba accumulator

        JavaRDD<Account> rdd = sc.parallelize(accs);
        AccountLogger logger = new AccountLogger();

        rdd.map(a -> {
            a.addInterest();
            logger.log("" + a.getBalance());
            return a;
        }).collect();
        System.out.println("count" + logger.logCount());
        logger.logPrint();
    }

    public static void example7(JavaSparkContext sc, List<Account> accs) {

        // Accumulator
        AccountAccumulator aa = new AccountAccumulator();

        sc.sc().register(aa);
        sc.parallelize(accs).map(a -> {
            a.addInterest();
            aa.add("Log " + a.getBalance());
            return a;
        }).collect();

        List<String> logs = aa.value();
        System.out.println(logs.size());
        logs.forEach(System.out::println);
    }

    public static void example8(JavaSparkContext sc, List<Account> accs) {

        DoubleAccumulator da = new DoubleAccumulator();

        sc.sc().register(da);
        sc.parallelize(accs).map(u -> {
            double urok = 0.1 * u.getBalance();
            u.setBalance(u.getBalance() + urok);
            da.add(urok);
            return u;
        }).collect();

        Double sum = da.value();
        System.out.println(sum);
    }

    public static void example9(JavaSparkContext sc, List<Account> accs) {
        
        JavaRDD<Account> rdd2 = sc.parallelize(accs).map(a -> {
            a.addInterest();
            System.out.println("" + a);
            return a;
        });
        
        rdd2.persist(StorageLevel.MEMORY_ONLY());
        rdd2.count();
        rdd2.collect();
    }

    public static void example10(JavaSparkContext sc, List<Account> accs) {
        
        JavaRDD<Account> rdd2 = sc.parallelize(accs).map(a -> {
            a.addInterest();
            System.out.println("" + a);
            return a;
        });
        
        rdd2.persist(StorageLevel.MEMORY_ONLY());
        rdd2.first();
    }

    public static void example11(JavaSparkContext sc, List<Account> accs) {
        
        JavaRDD<Account> rdd = sc.parallelize(accs);

        // ked je persist zakomentovany rdd sa vykona az pri collecte s 
        // listom uctov kde je prvy zaznam zmeneny, ked vsak odkomentujeme persist 
        // prvy rdd sa vykona pri count a vysledok sa ulozi do pamate co znamena, 
        // ze collect uz nevytvara novy rdd a nepouzije teda zmeneny list uctov
        rdd.persist(StorageLevel.MEMORY_ONLY()).count();
        accs.get(0).setBalance(0.0);
        
        List<Account> ucty2 = rdd.collect();
        ucty2.forEach(a -> {
            System.out.println("" + a.getBalance());
        });
    }

    public static void example12(JavaSparkContext sc) {

        JavaPairRDD<String, String> pdd = sc.wholeTextFiles("src/main/resources");

        System.out.println("\nmeno-suboru : cely obsah");
        pdd.foreach(t -> System.out.println(t._1 + "\n" + t._2));
//        pdd.collectAsMap().forEach((k, v) -> System.out.println(k + "\n" + v));

        System.out.println("\nmeno suboru : velkost suboru");
        pdd.mapValues(d -> d.length()).foreach(t -> System.out.println(t._1 + ":" + t._2));

        System.out.println("\nmeno suboru : slova");
        pdd.flatMapValues(v -> Arrays.asList(v.split("\\s+")).iterator())
                .foreach(t -> System.out.println(t._1 + ":" + t._2));

        System.out.println("\nmeno suboru : dlzka najkratsieho slova v subore");
        pdd.flatMapValues(v -> Arrays.asList(v.split("\\s+")).iterator())
                .mapValues(w -> w.length()).reduceByKey(Math::min)
                .foreach(t -> System.out.println(t._1 + ":" + t._2));

        System.out.println("\nmeno suboru : dlzka najkratsieho textoveho slova v subore");
        pdd.flatMapValues(v -> Arrays.asList(v.split("\\s+")).iterator())
                .filter(p -> p._2.matches("[a-zA-Z]*"))
                .mapValues(w -> w.length()).reduceByKey(Math::min)
                .foreach(t -> System.out.println(t._1 + ":" + t._2));

        System.out.println("\nmeno suboru a najdlhsieho slova v subore");
        pdd.flatMapValues(v -> Arrays.asList(v.split("\\s+")).iterator())
                .reduceByKey((a, b) -> a.length() >= b.length() ? a : b)
                .foreach(t -> System.out.println(t._1 + ":" + t._2));

        System.out.println("\nmeno suboru : pocet roznych slov");
        pdd.flatMapValues(v -> Arrays.asList(v.split("\\s+")).iterator())
                .distinct()
                .countByKey()
                .forEach((k, v) -> System.out.println(k + ":" + v));

        System.out.println("\nslovo a pocet suborov v ktorych sa nachadza");
        pdd.flatMapValues(v -> Arrays.asList(v.split("\\s+")).iterator())
                .mapToPair(f -> new Tuple2<String, String>(f._2, f._1))
                .countByKey()
                .forEach((k, v) -> System.out.println(k + ":" + v));

        System.out.println("\nPre kazde slovo vypiste mena suborov v ktorych sa nachadza - spojene do jedneho retazca");
        pdd.flatMapValues(v -> Arrays.asList(v.split("\\s+")).iterator())
                .mapToPair(f -> new Tuple2<String, String>(f._2, f._1))
                .distinct()
                .reduceByKey((a, b) -> a + "," + b)
                .foreach(f -> System.out.println(f._1 + ":" + f._2));
    }
    
    public static void example13(JavaSparkContext sc) {

        JavaRDD<String> rdd1 = sc.textFile("src/main/resources/data.txt")
                .flatMap(l -> Arrays.asList(l.split(" ")).iterator());
        JavaRDD<String> rdd2 = sc.textFile("src/main/resources/data2.txt")
                .flatMap(l -> Arrays.asList(l.split(" ")).iterator());

        System.out.println("\nslova ktore sa chadzaju v oboch suboroch");
        rdd1.intersection(rdd2).foreach(w -> System.out.println(w));

        System.out.println("\npocet roznych slov ktore sa chadzaju v data2.txt ale nechachadzaju v data.txt");
        System.out.println(rdd2.distinct().subtract(rdd1.distinct()).count());

        System.out.println("\npocet roznych slov, ktore sa nachadzaju aspon v jednom zo suborov");
        System.out.println(rdd1.union(rdd2).distinct().count());
        
        System.out.println("\nSymetricka diferencia mnoziny slov v suboroch data.txt a data2.txt");
        System.out.println(rdd1.union(rdd2).distinct().count() - rdd1.intersection(rdd2).count());
    }

    private static List<Account> initData() {
        List<Account> accs = new ArrayList<>();
        accs.add(new Account("U001", 100));
        accs.add(new Account("U002", 200));
        accs.add(new Account("U003", 300));
        accs.add(new Account("U004", 400));

        return accs;
    }
}
