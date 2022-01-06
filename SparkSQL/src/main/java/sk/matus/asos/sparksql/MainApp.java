package sk.matus.asos.sparksql;

import java.util.ArrayList;
import java.util.List;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import static org.apache.spark.sql.functions.col;

/**
 *
 * @author Matus
 */
public class MainApp {


    public static void main(String[] args) {

        SparkSession sparkSession = SparkSession.builder()
                .appName("Java Spark SQL")
                .master("local[2]")
                .getOrCreate();

        sparkSession.sparkContext().setLogLevel("ERROR");

        Dataset<Row> dfCSV = initDataFrameFromCsv(sparkSession);
        Dataset<Row> dfJSON = initDataFrameFromJson(sparkSession);
        List<Account> accs = initData();

//        example1(dfCSV);
//        example2(dfCSV);
//        example3(dfCSV);
//        example4(sparkSession, dfCSV);
//        example5(sparkSession, accs);
        example6(dfJSON);

        
        
        
        sparkSession.stop();
        sparkSession.close();
    }
    
    //  Nacitanie z csv-suboru do dataframe-u
    public static void example1(Dataset<Row> df) {

        // zobrazenie schémy
        df.printSchema();
        // výpis celej tabuľky
        df.show();
        //výpis vybraných stĺpcov
        df.select("height", "weight").show();
    }
    
    // Dataset functional API (filter, map, reduce,...) 
    public static void example2(Dataset<Row> df) {

        df.foreach(r -> System.out.println("height=" + r.getDouble(1)));
        df.filter(r -> r.getString(0).startsWith("E"))
                .foreach(r -> System.out.println(r));
        
        // BMI index
        df.map(r -> r.getDouble(2) / (r.getDouble(1) * r.getDouble(1)), Encoders.DOUBLE())
                .collectAsList()
                .forEach(bmi -> System.out.println(bmi));
    }
    
    // Použitie RDD API
    public static void example3(Dataset<Row> df) {
        df.javaRDD().map(r -> r.getDouble(2) / (r.getDouble(1) * r.getDouble(1)))
                .collect()
                .forEach(bmi -> System.out.println(bmi));
    }
    
     public static void example4(SparkSession sparkSession, Dataset<Row> df) {
         
          // Zaregistruje DataFrame ako SQL temporary view
         df.createOrReplaceTempView("people");
         
         sparkSession.sql("SELECT count(*) FROM people").show();
         sparkSession.sql("SELECT name, weight/(height * height) as BMI FROM people").show();
     }
     
    // Dataframe z JavaRDD
    public static void example5(SparkSession sparkSession, List<Account> accs) {
        
        JavaSparkContext sc = JavaSparkContext.fromSparkContext(sparkSession.sparkContext());
        JavaRDD<Account> rdd = sc.parallelize(accs);
        
        Dataset<Row> df =  sparkSession.createDataFrame(rdd, Account.class);
        df.createOrReplaceTempView("bank");
        sparkSession.sql("SELECT sum(balance) from bank").show();
        
    }
    
    public static void example6(Dataset<Row> df) {

        df.printSchema();
        df.show();

        //vypisat 4 stĺpce: vek mesto ulica a 1. hobby
        df.select(col("age"), col("address.city"), col("address.street"), col("hobby").getItem(0).as("1. hobby")).show();
    }
    
    private static Dataset<Row> initDataFrameFromCsv(SparkSession sparkSession) {

        // old way
//        return sparkSession.read()
//                .format("csv")
//                .option("sep", ";")
//                .option("inferSchema", "true")
//                .option("header", "true")
//                .load("src/main/resources/persons.csv");

        //new way
        return sparkSession.read()
                .option("sep", ";")
                .option("inferSchema", "true")
                .option("header", "true")
                .csv("src/main/resources/persons.csv");
    }
    
    private static Dataset<Row> initDataFrameFromJson(SparkSession sparkSession) {
        return sparkSession.read().json("src/main/resources/persons.jsonl");
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
