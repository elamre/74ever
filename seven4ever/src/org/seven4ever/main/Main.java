/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.seven4ever.main;

import org.seven4ever.util.Logger;

public class Main {

    /** @param args the command line arguments */
    public static void main(String[] args) {
        Controller controller;
        Thread controllerThread;
        controller = new Controller();
        controller.start();
        controllerThread = new Thread(controller);
        controllerThread.start();
        Logger.getInstance().system("Everything launched and ready for work!");
    }
}
