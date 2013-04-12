package org.seven4ever.util;

import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 4/11/13
 * Time: 2:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class Ports {
    /** The touch sensor at the front of the robot for front collisions */
    public static final SensorPort TOUCHPORTFRONT = SensorPort.S3;
    /** The touch sensor at the back of the robot */
    public static final SensorPort TOUCHPORTBACK = SensorPort.S4;
    /** The vision sensor to detect obstacles and walls */
    public static final SensorPort VISIONPORT = SensorPort.S1;
    /** The light sensor to detect restricted zones */
    public static final SensorPort LIGHTPORT = SensorPort.S2;
    /** The vision motor port to rotate the vision sensor */
    public static final NXTRegulatedMotor VISIONMOTORPORT = Motor.C;
    /** The joint motor port to rotate the robot */
    public static final NXTRegulatedMotor JOINTMOTORPORT = Motor.B;
    /** The motor port to move the robot */
    public static final NXTRegulatedMotor MOTORPORT = Motor.A;
}
