/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.seven4ever.driver;

import org.seven4ever.util.Logger;
import org.seven4ever.util.Ports;

/**
 * @author merijn
 */
public class Driver implements Runnable {

    private DriverState state = DriverState.IDLE;
    /**
     * Left = up, right = down. True-False
     */
    private int targetRotation = 0;
    private int motorTimer = 100;
    private int currentDegree = 0;
    private int turnDegree;
    int neutral = 10; // set the neutral value of the motor

    public Driver() {
        neutral = Ports.JOINTMOTORPORT.getPosition();
    }

    /**
     * Set the speed of the motor
     *
     * @param speed
     */
    public void setSpeed(Speed speed) {
        Ports.MOTORPORT.setSpeed(speed.getSpeed());
    }

    /**
     * Set the rotation of the turning motor
     *
     * @param degrees
     */
    public void setRotation(int degree) {
        turnDegree = degree - currentDegree;
        if (Math.abs(turnDegree) < 170) {
            Ports.JOINTMOTORPORT.rotate(turnDegree);
            Logger.getInstance().debug("rotating to: " + degree);
        } else if (Math.abs(turnDegree) >= 170){
            Ports.JOINTMOTORPORT.rotate(turnDegree);
            Logger.getInstance().error("Turn too large!");
        }
        currentDegree = degree;
    }

    /**
     * The slow stop
     */
    public void stop() {
        state = DriverState.STOPPING;
    }

    /**
     * Emergency stop, stops the car immediately
     */
    public void emergencyStop() {
        state = DriverState.EMERGENCYSTOP;
    }

    public void moveForward(Speed speed) {
        setSpeed(speed);
        state = DriverState.MOVING;
        Ports.MOTORPORT.forward();
        motorTimer = 100;
    }

    public void moveBackward(Speed speed) {
        setSpeed(speed);
        state = DriverState.MOVING;
        Ports.MOTORPORT.backward();
        motorTimer = 100;
    }

    /**
     * @return
     */
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
                    motorTimer -= 5;
                    if (motorTimer <= 0) {
                        state = DriverState.STOPPING;
                    }
                    // Move as long as the timer > 0 then stop
                    break;
                case EMERGENCYSTOP:
                    Ports.MOTORPORT.stop();
                    Ports.MOTORPORT.setSpeed(0);
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
