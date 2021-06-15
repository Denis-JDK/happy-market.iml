package ru.geekbrains.home_Java;


import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Car implements Runnable {
    private static int CARS_COUNT;
    private long startTime;
    private static final Logger logger = Logger.getLogger(Car.class.getName());


    static {
        CARS_COUNT = 0;
    }

    private Race race;
    private int speed;
    private String name;
    CountDownLatch countDownLatch = new  CountDownLatch(CARS_COUNT) ;
    private CountDownLatch cdlReady;
    private CountDownLatch cdlFinish;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed,CountDownLatch countDownLatchReady, CountDownLatch countDownLatchFinish) {
        this.race = race;
        this.speed = speed;
        this.cdlReady = countDownLatchReady;
        this.cdlFinish = countDownLatchFinish;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");
            logger.log(Level.SEVERE, "готов ", this);

            countDownLatch.countDown();
            cdlReady.countDown();

        } catch (Exception e) {
           logger.log(Level.SEVERE, "ошибка готовности ", this);
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            logger.log(Level.SEVERE, "ошибка ожидания ", this);
        }

        startTime = System.currentTimeMillis();

        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this, i + 1, race.getStages().size(), startTime);
            cdlFinish.countDown();
        }
    }
}
