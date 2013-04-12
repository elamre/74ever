/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.seven4ever.driver;

import lejos.nxt.*;
import org.seven4ever.util.Ports;

/**
 *
 * @author merijn
 */
public class Driver implements Runnable {

    int neutral = 10; // set the neutral value of the motor
    int currentSpeed;
    int turnNeutral;


    public Driver() {
    }
    /**
     * Set the speed of the motor
     * @param speed 
     */
    public void setSpeed(int speed) {
        Ports.MOTORPORT.setSpeed(speed);
    }
    /**
     * Set the rotation of the turning motor
     * @param degrees 
     */
    public void setRotation(int degrees) {
        Ports.JOINTMOTORPORT.rotate(degrees);
    }
    /**
     * Returns the current position of JOINTMOTORPORT
     */
    public void getRotation() {
        Ports.JOINTMOTORPORT.getPosition();
    }
    /**
     * Returns the current speed of MOTORPORT
     */
    public void getSpeed() {
        Ports.MOTORPORT.getSpeed();
    }
    /**
     * The slow stop
     */
    public void stop() {
        currentSpeed = Ports.MOTORPORT.getSpeed();
        turnNeutral();
        for(int i = currentSpeed;i > 0;i--){
            setSpeed(i);
        }
    }
    /**
     * Emergency stop, stops the car immediately
     */
    public void emergencyStop() {
        Ports.MOTORPORT.stop();
        Ports.JOINTMOTORPORT.stop();
    }
    /**
     * Gets the current state of the car
     */
    public void getState() {
    }
    /**
     * Sets the JOINTMOTORPORT to neutral/straight
     */
    public void turnNeutral() {
        turnNeutral = Ports.JOINTMOTORPORT.getPosition();
        if (turnNeutral != neutral) {
            Ports.JOINTMOTORPORT.rotate(neutral);
        }
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
