/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package robot;
import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;

/**
 *
 * @author marius
 */
public class Calibration {
LightSensor light = new LightSensor(SensorPort.S2);
    public int clibrateLight() {
        
     int value;
        Button.ENTER.waitForPressAndRelease();
        value = light.readValue();
        return value;   
    }    
    public int clibrateDark() {
        
        int value;
        Button.ENTER.waitForPressAndRelease();
        value = light.readValue();
        return value;
    }
    
    public static void main (String[] args) throws Exception{
      
    	Calibration cal = new Calibration();
    	
    	System.out.println("Press a button to capture");
       	int lv = cal.clibrateLight();
    	System.out.println("Value: "+lv);
    	Thread.sleep(5000);
    	LCD.clear();
    	
    	System.out.println("Press a button to capture");
       	int dv = cal.clibrateDark();
    	System.out.println("Value: "+dv);
    	Thread.sleep(5000);
    	LCD.clear();
    	
    }
}
