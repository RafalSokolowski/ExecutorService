package excercise_2;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import static util.Color.*;

@Slf4j
public class Magazyn {

    private final int capacity;
    @Getter
    private volatile int amountOnStock;

    public Magazyn(int capacity, int amountOnStock) {
        this.capacity = ifCapacityIsNegative(capacity);
        this.amountOnStock = amountOnStock;
    }

    public boolean addItem() {
        if (amountOnStock >= capacity) {
            log.info(RED + "Magazyn is full, new item cannot be added" + RESET);
            return false;
        }
        log.info(BLUE + "iteam was added" + RESET);
        amountOnStock++;
        return true;
    }

    public boolean removeItem() {
        if (capacity == 0) {
            log.error(RED + "Magazyn is empty, item cannot be sold" + RESET);
            return false;
        }
        log.info(BLUE + "iteam was removed / sold" + RESET);
        amountOnStock--;
        return true;
    }

    public boolean isEmpty() {
        return amountOnStock == 0;
    }

    public boolean isFull() {
        return amountOnStock == capacity;
    }

    public void printStock() {
        System.out.println(GREEN + "Amount of items on stock: " + amountOnStock + RESET);
    }


    private int ifCapacityIsNegative(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("capacity can not be negative!");
        return capacity;
    }

}
