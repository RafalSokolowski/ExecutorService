package excercise_2;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import static util.Color.*;

@Slf4j
@AllArgsConstructor
public class Fabryka implements Runnable{

    private Magazyn magazyn;

    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            Thread.sleep(500);      // czas produkcji Item
            boolean flag = produceItem();
            if (!flag) {
                Thread.sleep(1000); // jeżeli nie mogę produkować bo Magazyn is full to czekam sekundę i próbuje jeszcze raz...
            }
        }

    }

    private boolean produceItem() {
        if (magazyn.isFull()) {
            log.info(RED + "Magazyn is full, cannot produce/add new item" + RESET);
            return false;
        }
        magazyn.addItem();
        log.info(BLUE + "Fabryka produced one item and added it to the Magazyn" + RESET);
        return true;
    }





}
