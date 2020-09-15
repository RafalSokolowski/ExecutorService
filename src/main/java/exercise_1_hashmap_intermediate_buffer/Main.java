package exercise_1_hashmap_intermediate_buffer;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

//    private static volatile Map<Integer,String> warehouse = new HashMap<>(10);

    public static void main(String[] args) {

        int capacity = 10;
        Map<Integer,String> warehouse = new HashMap<>(capacity);


        Factory factory = new Factory(warehouse, capacity);

        Salesperson tim = new Salesperson("Tim", warehouse);
        Salesperson rav = new Salesperson("Rav", warehouse);

        ExecutorService exService = Executors.newCachedThreadPool();
        exService.execute(factory);
        exService.execute(tim);
        exService.execute(rav);

//        exService.shutdown(); // nie potrzebne bo petle nieskonczone

    }
}
