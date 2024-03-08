//package org.firstinspires.ftc.teamcode.commandbase.command.teleopcommand;
//
//import com.arcrobotics.ftclib.command.Command;
//import com.arcrobotics.ftclib.command.ConditionalCommand;
//
////import org.firstinspires.ftc.teamcode.commandbase.command.instantcommand.IntakeCommand;
//import org.firstinspires.ftc.teamcode.commandbase.command.instantcommand.IntakeSeroCommand;
//import org.firstinspires.ftc.teamcode.hardware.RobotHardware;
//import org.firstinspires.ftc.teamcode.subsystem.IntakeSubsystem;
//
//import java.util.function.BooleanSupplier;
//
//public class IntakeToggleCommand extends ConditionalCommand {
//
//  public IntakeToggleCommand(RobotHardware robot,IntakeSubsystem.IntakeServoState currentState){
//      super(
//              new IntakeSeroCommand(robot,IntakeSubsystem.IntakeServoState.INIT),
//              new ConditionalCommand(
//                      new IntakeSeroCommand(robot,IntakeSubsystem.IntakeServoState.INTAKE_UP),
//                      new IntakeSeroCommand(robot,IntakeSubsystem.IntakeServoState.INTAKE_DOWN),
//                      ()->(robot.intake.getIntakeServoState(currentState)==(IntakeSubsystem.IntakeServoState.INIT))
//                      ),
//              ()->(robot.intake.getIntakeServoState(currentState) == (IntakeSubsystem.IntakeServoState.INTAKE_UP))
//      );
//  }
//}
