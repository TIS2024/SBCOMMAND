package org.firstinspires.ftc.teamcode.copylogic;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class ToggleLogicEnum extends LinearOpMode {

    public enum Toggle {
        ON,
        OFF
    }

    public Toggle toggleSwitch(Toggle currentState) {
        return (currentState == Toggle.ON) ? Toggle.OFF : Toggle.ON;
    }

    @Override
    public void runOpMode() throws InterruptedException {

        Toggle currentToggleStateA = Toggle.OFF;
        Toggle currentToggleStateB = Toggle.OFF;




        waitForStart();

        while (opModeIsActive()) {
            
            ///////////////////////////////////////////////////////////////////////
            if (gamepad1.a) {
                if (currentToggleStateA == Toggle.OFF) {
                    // Toggle state update
                    currentToggleStateA = toggleSwitch(currentToggleStateA);
                } else {
                    // Handle the ON state case if needed
                }
                // Todo chech DebounceLogic Class for adding debounce
            }
            ///////////////////////////////////////////////////////////////////////
            if (gamepad1.b) {
                if (currentToggleStateB == Toggle.OFF) {
                    // Toggle state update
                    currentToggleStateB = toggleSwitch(currentToggleStateB);
                } else {
                    // Handle the ON state case if needed
                }
                // Todo chech DebounceLogic Class for adding debounce
            }
            ///////////////////////////////////////////////////////////////////////

        }
    }
}
