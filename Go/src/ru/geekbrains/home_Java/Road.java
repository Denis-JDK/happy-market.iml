package ru.geekbrains.home_Java;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Road extends Stage {
    private long finishTime;
    private static final Logger logger = Logger.getLogger(Road.class.getName());

    public Road(int length) {
        this.length = length;
        this.description = "Дорога " + length + " метров";
    }

    @Override
    public void go(Car c, int stagePost, int stageCount, long startTime) {
        try {    System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
            System.out.println(c.getName() + " начал этап: " + description);
            logger.log(Level.INFO, c.getName() + " начал этап " + description, description );
            Thread.sleep(length / c.getSpeed() * 1000);
            finishTime = System.currentTimeMillis() - startTime;

            if (stagePost == stageCount && Main.firstFinish) {
                Main.firstFinish = false;
                System.out.println(c.getName() + " Победа!");
            }
        } catch (InterruptedException e) {
            logger.log(Level.SEVERE, "ошибка приостановки потока" + this);
        }
    }
}
