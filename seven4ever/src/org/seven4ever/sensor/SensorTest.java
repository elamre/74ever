package org.seven4ever.sensor;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 4/12/13
 * Time: 2:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class SensorTest {
    public static void main(String[] args) {
        SensorManager sensorManager = new SensorManager();
        Thread thread = new Thread(sensorManager);
        thread.start();
        sensorManager.start();
        sensorManager.getTouchBack().setAction(new Action() {
            @Override
            public void action() {
                System.out.println("AU!");
            }
        });
    }
}
