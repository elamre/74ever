package org.seven4ever.sensor;

import org.seven4ever.util.Logger;
import org.seven4ever.util.Ports;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 4/10/13
 * Time: 3:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class SensorManager implements Runnable {
    private TouchWrapper touchFront;
    private TouchWrapper touchBack;
    private boolean running = false;

    public SensorManager() {
        touchFront = new TouchWrapper(Ports.TOUCHPORTFRONT);
        touchFront.setAction(new Action() {
            public void action() {
                System.out.println("Front collision!!!");
            }
        });
        touchBack = new TouchWrapper(Ports.TOUCHPORTBACK);
        touchBack.setAction(new Action() {
            @Override
            public void action() {
                System.out.println("Back collision!!!");
            }
        });
        running = true;
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
        while (!running) ;
        while (running) {
            touchBack.update();
            touchFront.update();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                exception(e);
            }
            Logger.getInstance().debug("Updated Sensors");
        }
        running = false;
    }

    /** Should be called before the thread is started. */
    public void start() {
        running = true;
    }

    /**
     * Use this function when something goes wrong.
     *
     * @param exception the exception that was thrown.
     */
    private void exception(Exception exception) {
        running = false;
    }
}
