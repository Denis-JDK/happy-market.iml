public class Road extends Stage {
    private long finishTime;

    public Road(int length) {
        this.length = length;
        this.description = "Дорога " + length + " метров";
    }

    @Override
    public void go(Car c, int stagePost, int stageCount, long startTime) {


            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                //
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
                finishTime = System.currentTimeMillis() - startTime;

                if (stagePost == stageCount && MainClass.firstFinish) {
                    MainClass.firstFinish = false;
                    System.out.println(c.getName() + " Победа!");
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
