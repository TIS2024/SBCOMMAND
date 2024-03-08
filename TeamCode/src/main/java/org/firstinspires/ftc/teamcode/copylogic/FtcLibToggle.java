package org.firstinspires.ftc.teamcode.copylogic;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.gamepad.ToggleButtonReader;

public class FtcLibToggle extends CommandOpMode {
    @Override
    public void initialize() {

    }

    @Override
    public  void run(){
        super.run();


        GamepadEx gamepad = new GamepadEx(gamepad1);
        ToggleButtonReader aReader = new ToggleButtonReader(gamepad, GamepadKeys.Button.A);

        while (opModeInInit()) {
            if (aReader.getState()) {
                // if toggle state true
            } else {
                // if toggle state false
            }
            aReader.readValue();
        }
    }

}
