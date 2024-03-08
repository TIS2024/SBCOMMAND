package org.firstinspires.ftc.teamcode.commandbase.command.instantcommand;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.subsystem.IntakeSubsystem;

public class IntakeMotorCommand extends InstantCommand {
    public IntakeMotorCommand(IntakeSubsystem intake,IntakeSubsystem.RollerIntakeState state) {
        super(
                ()->intake.updateState(state)
        );
    }
}
