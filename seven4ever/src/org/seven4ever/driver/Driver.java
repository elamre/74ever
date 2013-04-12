/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.seven4ever.driver;

import org.seven4ever.util.Ports;

/** @author merijn */
public class Driver implements Runnable {
    private DriverState state = DriverState.IDLE;
    /** Left = up, right = down. True-False */
    private boolean rotateUp = false;
    private int targetRotation = 0;
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
        targetRotation += degrees;
        state = (degrees > 0) ? DriverState.TURNINGLEFT : DriverState.TURNINGRIGHT;
    }

    /** @return  */
    public int getRotation() {
        return Ports.JOINTMOTORPORT.getPosition();
    }

    /** @return  */
    public int getSpeed() {
        return Ports.MOTORPORT.getSpeed();
    }

    /** The slow stop */
    public void stop() {
        state = DriverState.STOPPING;
    }

    /** Emergency stop, stops the car immediately */
    public void emergencyStop() {
        state = DriverState.EMERGENCYSTOP;
    }

    /** @return  */
    public DriverState getState() {
        return state;
    }

    /** Sets the JOINTMOTORPORT to neutral/straight */
    public void turnNeutral() {
        this.setRotation(-1 * Ports.JOINTMOTORPORT.getPosition());
    }

    @Override
    public void run() {
        while (true) {
            switch (state) {
                case STOPPED:
                    //Wait for the next task
                    break;
                case IDLE:
                    Ports.JOINTMOTORPORT.rotate(Ports.JOINTMOTORPORT.getPosition() - neutral); //Make sure it's neutral at all times
                    break;
                case MOVEFORWARD:
                    break;
                case MOVEBACKWARD:
                    break;
                case STOPPING:
                    if (getSpeed() > 0) {
                        setSpeed(getSpeed() - 5);
                        if (getSpeed() < 0 || getSpeed() == 0) {
                            setSpeed(0);
                            state = DriverState.STOPPED;
                        }
                    }
                    Ports.JOINTMOTORPORT.rotate(Ports.JOINTMOTORPORT.getPosition() - neutral);
                    break;
                case TURNINGLEFT:
                    if (Ports.JOINTMOTORPORT.getPosition() <= targetRotation)
                        Ports.JOINTMOTORPORT.rotate(-1);
                    else
                        state = DriverState.IDLE;
                    break;
                case TURNINGRIGHT:
                    if (Ports.JOINTMOTORPORT.getPosition() >= targetRotation)
                        Ports.JOINTMOTORPORT.rotate(1);
                    else
                        state = DriverState.IDLE;
                    break;
                case EMERGENCYSTOP:
                    Ports.MOTORPORT.stop();
                    Ports.JOINTMOTORPORT.stop();
                    break;
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {

            }
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
