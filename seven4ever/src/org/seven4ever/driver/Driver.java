/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.seven4ever.driver;

import org.seven4ever.util.Logger;
import org.seven4ever.util.Ports;

/** @author merijn */
public class Driver implements Runnable {
    private DriverState state = DriverState.IDLE;
    /** Left = up, right = down. True-False */
    private int targetRotation = 0;
    private int motorTimer = 100;
    int neutral = 10; // set the neutral value of the motor


    public Driver() {
        neutral = Ports.JOINTMOTORPORT.getPosition();
    }

    /**
     * Set the speed of the motor
     *
     * @param speed
     */
    public void setSpeed(int speed) {
        Ports.MOTORPORT.setSpeed(speed);
        Ports.MOTORPORT.forward();
    }

    /**
     * Set the rotation of the turning motor
     *
     * @param degrees
     */
    public void setRotation(int degrees) {
        Ports.JOINTMOTORPORT.rotate(degrees);
    }

    /** @param degrees  */
    public void turn(int degrees) {
        Logger.getInstance().debug("CD: " + Ports.JOINTMOTORPORT.getPosition());
        Logger.getInstance().debug("D: " + degrees);
        targetRotation += degrees;
        Logger.getInstance().debug("TD: " + targetRotation);

        //state = (degrees > 0) ? DriverState.TURNINGRIGHT : DriverState.TURNINGLEFT;
    }

    /** The slow stop */
    public void stop() {
        state = DriverState.STOPPING;
    }

    /** Emergency stop, stops the car immediately */
    public void emergencyStop() {
        state = DriverState.EMERGENCYSTOP;
    }

    public void moveForward(){}

    /** @return  */
    public DriverState getState() {
        return state;
    }

    @Override
    public void run() {
        while (true) {
            switch (state) {
                case IDLE:
                    if (Ports.MOTORPORT.isMoving()) {
                        Ports.MOTORPORT.stop();
                    }
                                                // Do nothing
                    break;
                case STOPPING:
                    int speed = 0;
                    if ((speed = Ports.MOTORPORT.getSpeed()) > 0) {
                        Ports.MOTORPORT.setSpeed(speed - 5);
                    }
                    if (speed <= 0) {
                    	speed = 0;
                    	Ports.MOTORPORT.setSpeed(0);
                        state = DriverState.IDLE;
                    }
                                                // Slow down and go to idle, Make sure robot is in neutral position though
                    break;
                case MOVING:
                	motorTimer-=5;
                                                // Move as long as the timer > 0 then stop
                    break;
                case EMERGENCYSTOP:
                	
                                                // Stop right away and stay in this state
                    break;
                case TURNING:
                                                // Turn, then go to STOPPING state
                    break;
            }
            try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
    }
}
