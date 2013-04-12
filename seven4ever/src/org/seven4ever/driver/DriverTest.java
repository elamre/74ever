package org.seven4ever.driver;

import org.seven4ever.util.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 4/12/13
 * Time: 2:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class DriverTest {
    public static void main(String[] args) throws Exception {
        Driver driver;
        Thread thread;
        driver = new Driver();
        thread = new Thread(driver);
        thread.start();
        Logger.getInstance().debug("Forward");
        driver.setSpeed(2000);
        Thread.sleep(4000);
        Logger.getInstance().debug("Turning");
        driver.setSpeed(100);
        driver.turn(100);
        Thread.sleep(5000);
        Logger.getInstance().debug("Forward");
        driver.setSpeed(2000);
        Thread.sleep(2500);
        Logger.getInstance().debug("Stopping");
        driver.stop();
        Thread.sleep(2500);
        Logger.getInstance().debug("Forward");
        driver.setSpeed(2000);
        Thread.sleep(2500);
        Logger.getInstance().debug("EMERGENCY STOP");
        driver.emergencyStop();
    }
}
