/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.seven4ever.sensor;

/**
 *
 * @author marius
 */
import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.nxt.Motor;
public class Light {
    
    
    private static int value;
    private static int DARK = 45;
    public static void main(String[] args) throws Exception {
    LightSensor light = new LightSensor(SensorPort.S2);

    while (true) {
      value = light.readValue();
      if(value<DARK){
          
      }
    }
  }
    
}
