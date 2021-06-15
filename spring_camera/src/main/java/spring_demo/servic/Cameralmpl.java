package spring_demo.servic;

public class Cameralmpl implements Camera {
    private CameraRoll cameraRoll;


    public CameraRoll getCameraRoll() {
        return cameraRoll;
    }

    public void setCameraRoll(CameraRoll cameraRoll) {
        this.cameraRoll = cameraRoll;
    }

    public void doPhotograph () {
        System.out.println("Щелк");
        cameraRoll.processing();
    }
}
