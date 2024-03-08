package org.firstinspires.ftc.teamcode.copylogic;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

public class DebounceLogic extends LinearOpMode {

    public enum Toggle {
        ON,
        OFF
    }

    private Toggle currentToggleState = Toggle.OFF;

    /////////////////////////////////////////////////////////////////////
    private boolean lastButtonState = false;
    private ElapsedTime debounceTimer = new ElapsedTime();
    private static final double DEBOUNCE_DELAY_SECONDS = 0.2; // Adjust the debounce delay as needed
    ////////////////////////////////////////////////////////////////////

    public Toggle toggleSwitch(Toggle currentState) {
        return (currentState == Toggle.ON) ? Toggle.OFF : Toggle.ON;
    }

    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();

        while (opModeIsActive()) {

            /////////////////////////////////////////////////////////////////////
            boolean currentButtonState = gamepad1.a;

            if (currentButtonState != lastButtonState) {
                debounceTimer.reset();
            }

            lastButtonState = currentButtonState;
            /////////////////////////////////////////////////////////////////////

            /////////////////////////////////////////////////////////////////////
            if (debounceTimer.seconds() > DEBOUNCE_DELAY_SECONDS) {
                debounceTimer.reset();

                if (currentToggleState == Toggle.OFF) {
                    // Toggle state update
                    currentToggleState = toggleSwitch(currentToggleState);
                    telemetry.addData("Toggle State", "ON");
                } else {
                    // Handle the ON state case if needed
                    telemetry.addData("Toggle State", "OFF");
                }

                telemetry.update();
            }
            /////////////////////////////////////////////////////////////////////

            // Add any other logic that needs to run continuously
            idle();
        }
    }
}
