package org.seven4ever.driver;

/**
 * Created with IntelliJ IDEA. User: Elmar Date: 4/12/13 Time: 2:06 PM To change
 * this template use File | Settings | File Templates.
 */
public class DriverTest {
    public static void main(String[] args) throws Exception {
        Driver driver;
        Thread thread;
        driver = new Driver();
        thread = new Thread(driver);
        thread.start();
        driver.setSpeed(150);
        Thread.sleep(1000);
        driver.turn(45);
        Thread.sleep(1000);
        driver.setSpeed(300);
        Thread.sleep(1000);
        driver.turn(-45);
        Thread.sleep(1000);
        driver.setSpeed(150);
        Thread.sleep(150);
        driver.stop();
    }
}
