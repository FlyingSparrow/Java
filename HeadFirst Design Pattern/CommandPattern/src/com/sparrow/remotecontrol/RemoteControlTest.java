package com.sparrow.remotecontrol;

import com.sparrow.command.impl.GarageDoorOpenCommand;
import com.sparrow.command.impl.LightOnCommand;
import com.sparrow.device.GarageDoor;
import com.sparrow.device.Light;

/**
 * @author wangjianchun
 * @create 2018/7/25
 */
public class RemoteControlTest {

    public static void main(String[] args) {
        SimpleRemoteControl remoteControl = new SimpleRemoteControl();
        Light light = new Light("");
        LightOnCommand lightOn = new LightOnCommand(light);

        GarageDoor garageDoor = new GarageDoor("");
        GarageDoorOpenCommand garageOpen = new GarageDoorOpenCommand(garageDoor);

        remoteControl.setCommand(lightOn);
        remoteControl.buttonWasPressed();
        remoteControl.setCommand(garageOpen);
        remoteControl.buttonWasPressed();
    }
}
