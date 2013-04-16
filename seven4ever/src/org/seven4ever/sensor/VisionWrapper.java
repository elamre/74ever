package org.seven4ever.sensor;

import lejos.nxt.I2CPort;
import lejos.nxt.UltrasonicSensor;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 4/12/13
 * Time: 8:52 AM
 * To change this template use File | Settings | File Templates.
 */
public class VisionWrapper extends UltrasonicSensor implements SensorWrapper {
    int threshold = 0;
    Action action;

    public VisionWrapper(I2CPort port) {
        super(port);
    }

    @Override
    public void setAction(Action action) {
        this.action = action;
    }

    @Override
    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    @Override
    public void update() {
        if (getDistance() < threshold) {
            if (action != null) {
                action.action();
            }
        }
    }
}
