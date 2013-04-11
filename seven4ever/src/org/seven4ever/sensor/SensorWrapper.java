package org.seven4ever.sensor;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 4/11/13
 * Time: 2:14 PM
 * To change this template use File | Settings | File Templates.
 */
public interface SensorWrapper {
    public void setAction(Action action);

    public void setThreshold(int threshold);

    public void update();
}
