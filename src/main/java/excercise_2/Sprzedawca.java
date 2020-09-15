package excercise_2;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

import static util.Color.*;

@Slf4j
@AllArgsConstructor
public class Sprzedawca implements Runnable {

    private final String name;
    private Magazyn magazyn;

    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            Thread.sleep(new Random().nextInt(500)); // czas sprzedaży to random od 0 do 1/2 sec
            boolean flag = saleItem();
            if (!flag) {
                Thread.sleep(1000);
            }
        }

    }

    private boolean saleItem() {
        if (magazyn.isEmpty()) {
            log.info(RED + "Magazyn is empty, item cannot be sold" + RESET);
            return false;
        }
        magazyn.removeItem();
        log.info(BLUE + name + " sold one item" + RESET);
        return true;
    }


}
