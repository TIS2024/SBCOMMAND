package org.firstinspires.ftc.teamcode.opmode;

import static org.firstinspires.ftc.teamcode.hardware.Globals.OpModeLoopTime;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.ScheduleCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.checkerframework.checker.units.qual.C;
import org.firstinspires.ftc.teamcode.commandbase.command.instantcommand.IntakeMotorCommand;
import org.firstinspires.ftc.teamcode.commandbase.command.instantcommand.IntakeSeroCommand;
import org.firstinspires.ftc.teamcode.commandbase.command.instantcommand.ShoulderCommand;
import org.firstinspires.ftc.teamcode.commandbase.command.teleopcommand.SequentialTest;
import org.firstinspires.ftc.teamcode.hardware.Globals;
import org.firstinspires.ftc.teamcode.hardware.MathUtils;
import org.firstinspires.ftc.teamcode.hardware.Pose;
import org.firstinspires.ftc.teamcode.hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.subsystem.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.OutakeSubsytem;

@TeleOp
@Config
public class TeleopTest extends CommandOpMode {

    private final RobotHardware robot = RobotHardware.getInstance();
    private GamepadEx gamepadEx;
    private GamepadEx gamepadEx2;
    private double loopTime = 0.0;

    private double previousTime = 0;

    private IntakeSubsystem intake=null;
    private OutakeSubsytem outake=null;

    ElapsedTime timer;
    @Override
    public void initialize() {
        CommandScheduler.getInstance().reset();

        Globals.IS_AUTO = false;

        robot.init(hardwareMap,telemetry);

        intake=new IntakeSubsystem(robot);
        outake=new OutakeSubsytem(robot);
        gamepadEx = new GamepadEx(gamepad1);
        gamepadEx2 = new GamepadEx(gamepad2);


//        intake.SetIntakePosition(0.2);


    //        gamepadEx.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER)
    //                .whenPressed(new IntakeToggleCommand(robot,IntakeSubsystem.IntakeServoState.INIT));
    //
        gamepadEx.getGamepadButton(GamepadKeys.Button.DPAD_UP)
                .whenPressed(new IntakeSeroCommand(intake, IntakeSubsystem.IntakeServoState.INTAKE_UP));

        gamepadEx.getGamepadButton(GamepadKeys.Button.DPAD_DOWN)
                .whenPressed(new IntakeSeroCommand(intake,IntakeSubsystem.IntakeServoState.INTAKE_DOWN));
        gamepadEx.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT)
                .whenPressed(new IntakeMotorCommand(intake, IntakeSubsystem.RollerIntakeState.INTAKE_ON));

        gamepadEx.getGamepadButton(GamepadKeys.Button.DPAD_LEFT)
                .whenPressed(new IntakeMotorCommand(intake, IntakeSubsystem.RollerIntakeState.INTAKE_OFF));

        gamepadEx.getGamepadButton(GamepadKeys.Button.A)
                .whenPressed(new ShoulderCommand(outake, OutakeSubsytem.ShoulderState.INIT));

        gamepadEx.getGamepadButton(GamepadKeys.Button.B)
                .whenPressed(new ShoulderCommand(outake, OutakeSubsytem.ShoulderState.DROP));

        gamepadEx.getGamepadButton(GamepadKeys.Button.X)
                .whenPressed(new SequentialTest(intake,outake));





        while (opModeInInit()) {
            telemetry.addLine("Robot Initialized.");
            telemetry.update();
        }

    }

    @Override
    public void run(){
        super.run();
//        CommandScheduler.getInstance().run();

        if(timer == null){
            timer = new ElapsedTime();
//            robot.startIMUThread(this);
        }

        robot.drivetrain.set(
                new Pose(
                        -gamepad1.left_stick_x,
                        gamepad1.left_stick_y,
                        MathUtils.joystickScalar(-gamepad1.left_trigger + gamepad1.right_trigger, 0.01)
                ), 0
        );


        robot.write();
        double loop = System.nanoTime();
        telemetry.addData("hz ", 1000000000 / (loop - loopTime));
        loopTime = loop;
        telemetry.update();
    }

}
