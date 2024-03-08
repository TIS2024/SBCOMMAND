package org.firstinspires.ftc.teamcode.commandbase.command.instantcommand;

import com.arcrobotics.ftclib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.subsystem.IntakeSubsystem;

public class IntakeSeroCommand extends InstantCommand {
    public IntakeSeroCommand(IntakeSubsystem intake,IntakeSubsystem.IntakeServoState state) {
        super(
                ()-> intake.updateState(state)
        );
    }
}
