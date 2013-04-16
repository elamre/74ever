package org.seven4ever.main;

import lejos.nxt.Sound;

import org.seven4ever.driver.Driver;
import org.seven4ever.driver.Speed;
import org.seven4ever.sensor.Action;
import org.seven4ever.sensor.SensorManager;
import org.seven4ever.util.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 4/11/13
 * Time: 2:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class Controller implements Runnable {
    SensorManager sensorManager;
    Thread sensorManagerThread;
    Driver driver;
    Thread driverThread;
    State state = State.DRIVENORMAL;
    private int randomTimer = 0;
    private int changeCourseTimer = 0;
    private boolean doneTurning = false;
    private int turnTimer = 0;

    /** Default constructor. Will call the initialize function */
    public Controller() {
        initialize();
    }

    /** Initializes and creates all the objects */
    private void initialize() {
        driver = new Driver();
        sensorManager = new SensorManager();
        
        sensorManager.getTouchBack().setAction(new Action() {
            @Override
            public void action() {
            	driver.emergencyStop();
        		state = State.EMERGENCYSTATE;
               // Logger.getInstance().debug("Touch sensor back was touched");
            }
        });
        sensorManager.getTouchBack().setThreshold(0);
        
        sensorManager.getTouchFront().setAction(new Action() {
            @Override
            public void action() {
            	if(state == State.DETECTOBJECT){
            		state = State.CHANGECOURSE;
            		driver.moveBackward(Speed.LOW);
            	}else{
            		driver.emergencyStop();
            		state = State.EMERGENCYSTATE;
            	}
               // Logger.getInstance().debug("Touch sensor front was touched");
            }
        });
        sensorManager.getTouchFront().setThreshold(0);
        
        sensorManager.getVision().setAction(new Action() {
            @Override
            public void action() {
            	if(state==State.DRIVENORMAL){
            		state = State.DETECTOBJECT;
            		Logger.getInstance().debug("Vision sensor detects something");
            	}
            }
        });
        sensorManager.getVision().setThreshold(50);
    }

    /** Will create and start all the required threads. */
    public void start() {
        driverThread = new Thread(driver);
        driverThread.start();

        sensorManagerThread = new Thread(sensorManager);
        sensorManagerThread.start();
        sensorManager.start();
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
        while (true) {
            try {
            	switch(state){
				case CHANGECOURSE:
					driver.moveBackward(Speed.LOW);
					changeCourseTimer+=10;
					if(changeCourseTimer>=2000){
						changeCourseTimer = 0;
						state = State.TURN;
					}
					break;
				case DETECTOBJECT:
					driver.setSpeed(Speed.LOW);
					break;
				case DRIVENORMAL:
					driver.moveForward(Speed.MEDIUM);					
					break;
				case TURN:
					int degrees = 0;
					if(!doneTurning){
						switch(getFreeDirection()){
						case 0:
							degrees = 90;
							break;
						case 1:
							degrees = -90;
							break;
						}
						driver.setRotation(degrees);
						doneTurning = true;
					}
					driver.moveForward(Speed.MEDIUM);
					turnTimer +=5;
					Logger.getInstance().debug("T: " + turnTimer);
					if(turnTimer>=1000){
						driver.setRotation(-degrees);
						turnTimer = 0;
						state = State.DRIVENORMAL;
						doneTurning = false;
						Logger.getInstance().debug("Done!");
					}
					break;
				case EMERGENCYSTATE:
					Thread.sleep(1000);
					Sound.playTone(44000, 100);
					break;
				default:
					break;            	
            	}
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Logger.getInstance().error("Something went wrong while sleeping, Controller class");
                Logger.getInstance().error(e);
                System.exit(0);
            }
        }
    }
    
    private int getFreeDirection(){
    	return 1;
    }
}
