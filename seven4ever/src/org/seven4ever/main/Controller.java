package org.seven4ever.main;

import org.seven4ever.driver.Driver;
import org.seven4ever.sensor.Action;
import org.seven4ever.sensor.SensorManager;
import org.seven4ever.util.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 4/11/13
 * Time: 2:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class Controller implements Runnable {
    SensorManager sensorManager;
    Thread sensorManagerThread;
    Driver driver;
    Thread driverThread;

    /** Default constructor. Will call the initialize function */
    public Controller() {
        initialize();
    }

    /** Initializes and creates all the objects */
    private void initialize() {
        driver = new Driver();
        sensorManager = new SensorManager();
        sensorManager.getTouchBack().setAction(new Action() {
            @Override
            public void action() {
                driver.emergencyStop();
                Logger.getInstance().debug("Touch sensor back was touched");
            }
        });
        sensorManager.getTouchFront().setAction(new Action() {
            @Override
            public void action() {
                driver.emergencyStop();
                Logger.getInstance().debug("Touch sensor front was touched");
            }
        });
        sensorManager.getVision().setAction(new Action() {
            @Override
            public void action() {
                driver.stop();
                Logger.getInstance().debug("Vision sensor detects something");
            }
        });
    }

    /** Will create and start all the required threads. */
    public void start() {
        driverThread = new Thread(driver);
        driverThread.start();

        sensorManagerThread = new Thread(sensorManager);
        sensorManagerThread.start();
        sensorManager.start();
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p/>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        while (true) {
            try {
                driver.setSpeed(1700);
                Thread.sleep(2000);
                driver.setSpeed(700);
                driver.setRotation(40);
                Thread.sleep(2000);
                driver.emergencyStop();
                Thread.sleep(1000);
                driver.setSpeed(1700);
                Thread.sleep(2000);
                driver.stop();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Logger.getInstance().error("Something went wrong while sleeping, Controller class");
                Logger.getInstance().error(e);
                System.exit(0);
            }
            Logger.getInstance().debug("Doing something");
        }
    }
}
