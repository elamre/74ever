package org.seven4ever.driver;

import org.seven4ever.util.Logger;

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
		Logger.getInstance().debug("1");
		driver.setSpeed(1700);
		Thread.sleep(2000);
		Logger.getInstance().debug("2");
		driver.setSpeed(100);
		driver.setRotation(40);
		Thread.sleep(1000);
		Logger.getInstance().debug("3");
	}
}
