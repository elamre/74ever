package org.seven4ever.main;

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

    /** Default constructor. Will call the initialize function */
    public Controller() {
        initialize();
    }

    /** Initializes and creates all the objects */
    private void initialize() {
        sensorManager = new SensorManager();
    }

    /** Will create and start all the required threads. */
    public void start() {
        sensorManagerThread = new Thread(sensorManager);
        sensorManagerThread.start();
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
