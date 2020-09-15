package exercise_1_hashmap_intermediate_buffer;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

import static util.Color.*;

@Slf4j
public class Factory implements Runnable {

    private volatile Map<Integer, String> warehouse;
    private final int capacity;
    private volatile int order;

    public Factory(Map<Integer, String> warehouse, int capacity) {
        this.warehouse = warehouse;
        this.capacity = capacity;
        this.order = 0;
    }

    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            Thread.sleep(1000);      // time to produce one item = 1 sec
            boolean flag = produceItem();
            if (!flag) {
                Thread.sleep(2000); // if cannot produce / add to warehouse than wait 2 sec
            }
        }
    }

    private synchronized boolean produceItem() {
        if (warehouse.size() == capacity) {
            System.out.println(RED + "Warehouse is full, cannot produce new item :(" + RESET);
            return false;
        }

        warehouse.put(order, "item_" + order++);

        System.out.println(PURPLE + "Factory has just produced one more item, current Warehouse stock is: " + warehouse + RESET);
        return true;
    }

}
