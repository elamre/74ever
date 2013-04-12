/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.seven4ever.sensor;
import org.seven4ever.util.*;

import lejos.nxt.Button;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;

/**
 *
 * @author marius
 * 
 * This is a calibration class for all the calibration.
 */
public class Calibration {

    LightSensor light = new LightSensor(SensorPort.S2);
    TouchSensor touch1 = new TouchSensor(SensorPort.S1);
    public boolean bool = false;

    
    /**
     * Calibrates the bright light level
     * @return the calibrated light value
     */
    public int clibrateLight() {

        int value;
        Logger.getInstance().system("Place light sensor on a light area then press enter");

        Button.ENTER.waitForPressAndRelease();
        value = light.readValue();

        return value;
    }
/**
 * Calibrates the bright light level
 * @return the calibrated light value
 */
    public int clibrateDark() {

        int value;

        Logger.getInstance().system("Place light sensor on a dark area then press enter");
        Button.ENTER.waitForPressAndRelease();
        value = light.readValue();

        return value;

    }
/**
 * Checks if the button in the front of the works(/is connected?)
 * @return true if sensor is ok.
 */
    public boolean calibrateTouch() {
        try {
            Logger.getInstance().system("Press it!");
            while(!touch1.isPressed());

            bool = touch1.isPressed();

        } catch (Exception e){ 
            Logger.getInstance().error("Something went wrong in the touchcalibration");
            Logger.getInstance().error(e);
        }
        return bool;
    }
}
