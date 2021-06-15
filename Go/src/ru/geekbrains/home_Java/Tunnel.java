package ru.geekbrains.home_Java;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tunnel extends Stage {
    private static final Semaphore semaphore = new Semaphore(Main.CARS_COUNT / 2);
    private static final Logger logger = Logger.getLogger(Tunnel.class.getName());
    private long finishTime;

    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }

    @Override
    public void go(Car c, int stagePost, int stageCount, long startTime) {
        try {
            try { System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                semaphore.acquire();
                System.out.println(c.getName() + " начал этап: " + description);
                logger.log(Level.SEVERE, c.getName() +" начал этап: "+ description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                //

            } finally {
                finishTime = System.currentTimeMillis() - startTime;
                System.out.println(c.getName() + " закончил этап: " + description);
                semaphore.release();
                if (stagePost == stageCount && Main.firstFinish) {
                    Main.firstFinish = false;
                    System.out.println(c.getName() + " Победа!");
                }
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "ошибка приостановки потока" + this);
        }
    }
}
