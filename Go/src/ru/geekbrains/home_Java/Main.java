package ru.geekbrains.home_Java;


import java.util.concurrent.CountDownLatch;
import java.util.logging.Filter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class Main {
    public static final int CARS_COUNT = 4;
    public static volatile boolean firstFinish = true;
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {

        Filter fltONE = new  Filter() {
            @Override
            public boolean isLoggable(LogRecord record) {
                if (record.getMessage().startsWith(String.valueOf(Level.SEVERE)))
                    return true;
                System.out.println("отфильтровал логирования");
                return false;
            }
        };

        logger.setFilter(fltONE);
        logger.log(Level.INFO,"ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");

        Race race = new Race(new Road(60), new Tunnel());
        logger.log(Level.SEVERE,"инициализирована трасса");

        Car[] cars = new Car[CARS_COUNT];
        logger.log(Level.SEVERE,"инициализирована участники ");
        CountDownLatch countDownLatchReady = new CountDownLatch(CARS_COUNT);
        CountDownLatch countDownLatchFinish = new CountDownLatch(CARS_COUNT);
        long time;
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10)+ (int) (Math.random() * 10), countDownLatchReady, countDownLatchFinish);
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
        try {
            countDownLatchReady.await();
        } catch (InterruptedException e) {
            logger.log(Level.SEVERE, "Ошибка потока", e);
        }
        logger.log(Level.INFO,"ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        time = System.currentTimeMillis();

        try {
            countDownLatchFinish.await();
        } catch (InterruptedException e) {
            logger.log(Level.SEVERE, "Ошибка потока", e);
        }

        logger.log(Level.INFO,"ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!" + ((float)System.currentTimeMillis() / 1000));


    }
}



