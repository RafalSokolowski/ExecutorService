package excercise_2;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class Main {
    public static void main(String[] args) {

        Magazyn magazyn = new Magazyn(100, 10);
        Fabryka fabryka = new Fabryka(magazyn);

        Sprzedawca bob = new Sprzedawca("Bob", magazyn);
        Sprzedawca tim = new Sprzedawca("Tim", magazyn);
        Sprzedawca rav = new Sprzedawca("Rav", magazyn);

        ExecutorService exService = Executors.newCachedThreadPool();
        exService.execute(fabryka);
        exService.execute(bob);
        exService.execute(tim);
        exService.execute(rav);

        exService.shutdown(); // bo petle nieskonczone

        magazyn.printStock();

    }
}
