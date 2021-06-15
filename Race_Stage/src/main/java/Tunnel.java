import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {

    private static final Semaphore semaphore = new Semaphore(MainClass.CARS_COUNT / 2);
    private long finishTime;

    public Tunnel(int length) {
        this.length = length;
        this.description = "Тоннель " + length + " метров";
    }

    @Override
    public void go(Car c, int stagePost, int stageCount, long startTime) {
        try {

            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                semaphore.acquire();
                //

                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                //

            } finally {
                finishTime = System.currentTimeMillis() - startTime;
                System.out.println(c.getName() + " закончил этап: " + description);
                semaphore.release();
                if (stagePost == stageCount && MainClass.firstFinish) {
                    MainClass.firstFinish = false;
                    System.out.println(c.getName() + " Победа!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            //

        }
    }
}