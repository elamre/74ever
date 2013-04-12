package org.seven4ever.sensor;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 4/10/13
 * Time: 3:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class SensorManager implements Runnable{
    private TouchWrapper touchWrapper;

    public SensorManager(){
        touchWrapper = TouchWrapper
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
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
