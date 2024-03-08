package org.firstinspires.ftc.teamcode.commandbase.command.instantcommand;

import com.arcrobotics.ftclib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.subsystem.OutakeSubsytem;

public class ShoulderCommand extends InstantCommand {
    public ShoulderCommand(OutakeSubsytem outake,OutakeSubsytem.ShoulderState state) {
        super(
                ()->outake.updateState(state)
        );
    }
}
