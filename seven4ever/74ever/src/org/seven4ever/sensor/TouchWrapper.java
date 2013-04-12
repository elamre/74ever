package org.seven4ever.sensor;

import lejos.nxt.ADSensorPort;
import lejos.nxt.TouchSensor;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 4/10/13
 * Time: 3:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class TouchWrapper extends TouchSensor implements SensorWrapper {
    private Action action;
    private boolean threshold;

    /**
     * Create a touch sensor object attached to the specified port.
     *
     * @param port an Analog/Digital port, e.g. SensorPort.S1
     */
    public TouchWrapper(ADSensorPort port) {
        super(port);
    }

    @Override
    public void setAction(Action action) {
        this.action = action;
    }

    @Override
    public void setThreshold(int threshold) {
        this.threshold = (threshold == 1) ? true : false;
    }

    @Override
    public void update() {
        if (this.isPressed() == threshold) {
            if (action != null) {
                action.action();
            }
        }
    }
}
