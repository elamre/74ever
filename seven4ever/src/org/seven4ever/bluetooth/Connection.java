package org.seven4ever.bluetooth;

import lejos.nxt.comm.Bluetooth;
import lejos.nxt.comm.NXTConnection;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 4/11/13
 * Time: 3:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class Connection {
    NXTConnection connection = Bluetooth.waitForConnection();
}
