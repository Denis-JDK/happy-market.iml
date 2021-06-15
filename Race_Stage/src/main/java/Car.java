import java.util.concurrent.CountDownLatch;

public class Car implements Runnable  {
    private static int CARS_COUNT;
    private long startTime;




    static {
        CARS_COUNT = 0;
    }

    private Race race;
    private int speed;
    CountDownLatch countDownLatch;
    private CountDownLatch cdlReady;
    private CountDownLatch cdlFinish;
    private String name;

        public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed, CountDownLatch countDownLatchReady, CountDownLatch countDownLatchFinish) {
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
            countDownLatch.countDown();
            cdlReady.countDown();

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        startTime = System.currentTimeMillis();

        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this, i + 1, race.getStages().size(), startTime);
            cdlFinish.countDown();
        }
    }

   }



