package fr.sapk.tb;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.cybergarage.upnp.*;
import org.cybergarage.upnp.device.*;

/**
 *
 * @author sapk
 */
public class IoTApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*
        ControlPoint ctrlPoint = new ControlPoint();
        boolean start = ctrlPoint.start(); 
         */
 /*
        CustomControlPoint ctrlPoint = new CustomControlPoint();
        DeviceList rootDevList = ctrlPoint.getDeviceList();

        int nRootDevs = rootDevList.size();
        for (int n = 0; n < nRootDevs; n++) {
            Device dev = rootDevList.getDevice(n);
            String devName = dev.getFriendlyName();
            System.out.println("[" + n + "] = " + devName);
        }
         */
        CustomControlPoint ctrlPoint = new CustomControlPoint();
        boolean ret = false;
        do {
            ctrlPoint.search();
            try {
                Thread.sleep(15000);
                //ctrlPoint.AllumeLampe();
                //ret = ctrlPoint.AllumeLampe();
                //ret = ctrlPoint.SetMessage("Bonjour");
                //ret = ctrlPoint.Beep();
                //System.out.println("Valeur de retour de la fonction : "+ ret);
                ret = ctrlPoint.SetMessage("Bonjour");
                System.out.println("Valeur de retour de la fonction : "+ ret);
                ret = ctrlPoint.EteindreLampe();
                Thread.sleep(5);
                ret = ctrlPoint.AllumeLampe();
                System.out.println("Valeur de retour de la fonction : "+ ret);
            } catch (InterruptedException ex) {
                Logger.getLogger(IoTApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (ret == false);
    }

}
