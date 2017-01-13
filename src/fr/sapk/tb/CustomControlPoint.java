package fr.sapk.tb;

import org.cybergarage.upnp.*;
import org.cybergarage.upnp.device.*;
import org.cybergarage.upnp.ssdp.SSDPPacket;

/**
 *
 * @author sapk
 */
public class CustomControlPoint extends ControlPoint implements DeviceChangeListener, SearchResponseListener {

    public CustomControlPoint() {
        addDeviceChangeListener(this);
        addSearchResponseListener(this);
        start();
        search("upnp:rootdevice");
    }

    public void deviceAdded(Device dev) {
        if (dev.isDeviceType("urn:schemas-upnp-org:device:MediaServer:1")) {
            return; //Skip (Window, Plex)
        }
        parseDevice("deviceAdded", dev);
    }

    public void deviceRemoved(Device dev) {
        parseDevice("deviceRemoved", dev);
    }

    public void deviceNotifyReceived(SSDPPacket ssdpPacket) {
        String uuid = ssdpPacket.getUSN();
        String target = ssdpPacket.getST();
        String location = ssdpPacket.getLocation();
        System.out.println("NotifyReceived -> [" + uuid + "] = "
                + "\n target: " + target
                + "\n location: " + location
        );
        //parseDevice(uuid, getDevice(uuid));
        //System.out.println("[" + uuid + "] = " + ssdpPacket.getData());
    }

    public void deviceSearchResponseReceived(SSDPPacket ssdpPacket) {
        String uuid = ssdpPacket.getUSN();
        String target = ssdpPacket.getST();
        String location = ssdpPacket.getLocation();
        System.out.println("SearchResponseReceived -> [" + uuid + "] = "
                + "\n target: " + target
                + "\n location: " + location
        );
        //parseDevice(uuid, getDevice(uuid));
        //System.out.println("[" + uuid + "] = " + ssdpPacket.getData());
    }

    public void parseDevice(String uuid, Device device) {
        if (device != null) {
            System.out.println("parseDevice Parsing Device : "
                    + "\n DeviceType: " + device.getDeviceType()
                    + "\n getFriendlyName: " + device.getFriendlyName()
                    + "\n getServiceList: " + device.getServiceList()
                    + "\n getModelName: " + device.getModelName()
                    + "\n getModelDescription: " + device.getModelDescription()
            );
            ServiceList serviceList = device.getServiceList();
            for (int i = 0; i < serviceList.size(); i++) {
                Service service = serviceList.getService(i);
                System.out.println(""
                        //+ "\n   device.getFriendlyName: " + device.getFriendlyName()
                        + "\n   service.getServiceID: " + service.getServiceID()
                        + "\n   service.getServiceType: " + service.getServiceType()
                        + "\n   service.getActionList: " + service.getActionList()
                );
                ActionList actionList = service.getActionList();
                for (int a = 0; a < actionList.size(); a++) {
                    Action action = actionList.getAction(a);
                    System.out.println(""
                            //+ "\n     device.getFriendlyName: " + device.getFriendlyName()
                            //+ "\n     service.getServiceID: " + service.getServiceID()
                            + "\n     action.getName: " + action.getName()
                            + "\n     action.getArgumentList: " + action.getArgumentList()
                    );
                    ArgumentList argList = action.getArgumentList();
                    for (int n = 0; n < argList.size(); n++) {
                        Argument arg = argList.getArgument(n);
                        System.out.println(""
                                //+ "\n     device.getFriendlyName: " + device.getFriendlyName()
                                //+ "\n     service.getServiceID: " + service.getServiceID()
                                + "\n       arg.getName: " + arg.getName()
                                + "\n       arg.getRelatedStateVariableName : " + arg.getRelatedStateVariableName()
                        );
                    }
                }
            }

        } else {
            System.out.println("parseDevice Skipping Device (null): "
                    + "\n uuid : " + uuid
            );
        }
    }

    /*
    void GetClock() {
        Device lampe = getDevice(“limsiIroomX10CM11”); 
        StateVariable timeStateVar = lampe.getStateVariable(“time”); 
    }
     */
    public Boolean AllumeLampe() {
        System.out.println("AllumeLampe()");
        Device el = getDevice("LIMSI CM11");
        if (el == null) {
            System.out.println("  Failed to retrieve LIMSI CM11");
            return false;
        }
        parseDevice("test", el);
        Action setLampeOn = el.getAction("ExecuteCommand");
        setLampeOn.setArgumentValue("ElementName", "Lampe_Bureau2");
        setLampeOn.setArgumentValue("Command", "on");
        return setLampeOn.postControlAction();
    }

    public Boolean EteindreLampe() {
        System.out.println("AllumeLampe()");
        Device el = getDevice("LIMSI CM11");
        if (el == null) {
            System.out.println("  Failed to retrieve LIMSI CM11");
            return false;
        }
        parseDevice("test", el);
        Action setLampeOn = el.getAction("ExecuteCommand");
        setLampeOn.setArgumentValue("ElementName", "Lampe_Bureau2");
        setLampeOn.setArgumentValue("Command", "off");
        return setLampeOn.postControlAction();
    }

    public Boolean SetMessage(String m) {
        System.out.println("SetMessage()"
                + "\n Message: " + m
        );
        Device screen = getDevice("LIMSI PhotoTextViewer");
        if (screen == null) {
            System.out.println("  Failed to retrieve LIMSI PhotoTextViewer");
            return false;
        }
        parseDevice("test", screen);
        Action setText = screen.getAction("SetText");
        setText.setArgumentValue("Text", m);
        return setText.postControlAction();
    }

    public Boolean Beep() {
        System.out.println("Beep()");
        Device el = getDevice("LIMSI AudioPlayer");
        if (el == null) {
            System.out.println("  Failed to retrieve LIMSI AudioPlayer");
            return false;
        }
        parseDevice("test", el);
        Action beep = el.getAction("Beep");
        beep.setArgumentValue("Player", "IRoom_Beeper");
        return beep.postControlAction();
    }

    public String ArduinoGetStatus() {
        System.out.println("Arduino()");
        Device el = getDevice("ArduinoLampe");
        if (el == null) {
            System.out.println("  Failed to retrieve ArduinoLampe");
            return "Terminal Not Found";
        }
        parseDevice("test", el);
        Action arduino = el.getAction("GetStatus");
        if (arduino.postControlAction()) {
            ArgumentList list = arduino.getOutputArgumentList();
            String ret = "Value : ";
            for (int i = 0; i < list.size(); i++) {
                Argument arg = list.getArgument(i);
                ret += arg.getName() + ":" + arg.getValue();
            }
            return ret;
        } else {
            return "Get failed";
        }
    }
    public boolean ArduinoSetTarget(String value) {
        System.out.println("Arduino()");
        Device el = getDevice("ArduinoLampe");
        if (el == null) {
            System.out.println("  Failed to retrieve ArduinoLampe");
            return false;
        }
        parseDevice("test", el);
        Action arduino = el.getAction("SetTarget");

        arduino.setArgumentValue("newTargetValue", value);
        return arduino.postControlAction();
    }
    public String ArduinoGetTarget() {
        System.out.println("Arduino()");
        Device el = getDevice("ArduinoLampe");
        if (el == null) {
            System.out.println("  Failed to retrieve ArduinoLampe");
            return "Terminal Not Found";
        }
        parseDevice("test", el);
        Action arduino = el.getAction("GetTarget");
        
        if (arduino.postControlAction()) {
            ArgumentList list = arduino.getOutputArgumentList();
            String ret = "Value : ";
            for (int i = 0; i < list.size(); i++) {
                Argument arg = list.getArgument(i);
                ret += arg.getName() + ":" + arg.getValue();
            }
            return ret;
        } else {
            return "Get failed";
        }
        
    }
    
    public String ArduinoGetAutomatic() {
        System.out.println("Arduino()");
        Device el = getDevice("ArduinoLampe");
        if (el == null) {
            System.out.println("  Failed to retrieve ArduinoLampe");
            return "Terminal Not Found";
        }
        parseDevice("test", el);
        Action arduino = el.getAction("GetAutomatic");
        
        if (arduino.postControlAction()) {
            ArgumentList list = arduino.getOutputArgumentList();
            String ret = "Value : ";
            for (int i = 0; i < list.size(); i++) {
                Argument arg = list.getArgument(i);
                ret += arg.getName() + ":" + arg.getValue();
            }
            return ret;
        } else {
            return "Get failed";
        }
        
    }
    public boolean ArduinoSetAutomatic(String value) {
        System.out.println("Arduino()");
        Device el = getDevice("ArduinoLampe");
        if (el == null) {
            System.out.println("  Failed to retrieve ArduinoLampe");
            return false;
        }
        parseDevice("test", el);
        Action arduino = el.getAction("SetAutomatic");

        arduino.setArgumentValue("automatic", value);
        return arduino.postControlAction();
    }
    
    public String ArduinoGetSensibility() {
        System.out.println("Arduino()");
        Device el = getDevice("ArduinoLampe");
        if (el == null) {
            System.out.println("  Failed to retrieve ArduinoLampe");
            return "Terminal Not Found";
        }
        parseDevice("test", el);
        Action arduino = el.getAction("GetSensibility");
        
        if (arduino.postControlAction()) {
            ArgumentList list = arduino.getOutputArgumentList();
            String ret = "Value : ";
            for (int i = 0; i < list.size(); i++) {
                Argument arg = list.getArgument(i);
                ret += arg.getName() + ":" + arg.getValue();
            }
            return ret;
        } else {
            return "Get failed";
        }
        
    }
    public boolean ArduinoSetSensibility(String value) {
        System.out.println("Arduino()");
        Device el = getDevice("ArduinoLampe");
        if (el == null) {
            System.out.println("  Failed to retrieve ArduinoLampe");
            return false;
        }
        parseDevice("test", el);
        Action arduino = el.getAction("SetSensibility");
        
        arduino.setArgumentValue("sensibility", value);
        return arduino.postControlAction();
    }
}
