package org.firstinspires.ftc.teamcode.commandbase.command.teleopcommand;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commandbase.command.instantcommand.IntakeMotorCommand;
import org.firstinspires.ftc.teamcode.commandbase.command.instantcommand.IntakeSeroCommand;
import org.firstinspires.ftc.teamcode.commandbase.command.instantcommand.ShoulderCommand;
import org.firstinspires.ftc.teamcode.subsystem.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.OutakeSubsytem;

public class SequentialTest extends SequentialCommandGroup {
    public SequentialTest(IntakeSubsystem intake, OutakeSubsytem outake) {
        super(
                new IntakeSeroCommand(intake, IntakeSubsystem.IntakeServoState.INTAKE_DOWN),
                new WaitCommand(200),
                new IntakeMotorCommand(intake, IntakeSubsystem.RollerIntakeState.INTAKE_ON),
                new WaitCommand(500),
                new IntakeMotorCommand(intake, IntakeSubsystem.RollerIntakeState.INTAKE_OFF),
                new WaitCommand(400),
                new ShoulderCommand(outake, OutakeSubsytem.ShoulderState.DROP),
                new WaitCommand(500),
                new ShoulderCommand(outake, OutakeSubsytem.ShoulderState.DROP)




                );
    }
}
