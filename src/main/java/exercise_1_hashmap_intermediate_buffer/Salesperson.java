package exercise_1_hashmap_intermediate_buffer;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Random;

import static util.Color.*;

@Slf4j
@AllArgsConstructor
public class Salesperson implements Runnable {

    private final String name;
    private volatile Map<Integer, String> warehouse;

    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            Thread.sleep(new Random().nextInt(6_000) + 500);  // time to sale one item from 0.5 to 6.5 sec.
            boolean flag = saleItem();
            if (!flag) {
                Thread.sleep(1000);
            }
        }
    }

    private synchronized boolean saleItem() {
        if (warehouse.isEmpty()) {
            System.out.println(RED + "Warehouse is empty, " + name + " cannot sale any item :(" + RESET);
            return false;
        }

        int firstIndexToBeSold = findLastItemIndex();
        String soldItem = warehouse.get(firstIndexToBeSold);
        warehouse.remove(firstIndexToBeSold);

        System.out.println(BLUE + name + " has just sold (" + soldItem +", "+ firstIndexToBeSold + ")" + ", bravo! :)" + RESET);
        return true;
    }

    private synchronized int findLastItemIndex () {
        Integer[] result = warehouse.keySet().toArray(Integer[]::new);
//        return result[result.length-1];   // sprzedaż w systemie LIFO
        return result[0];                   // sprzedaż w systemie FIFO
    }


}
