package org.firstinspires.ftc.teamcode.subsystem;

import com.arcrobotics.ftclib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.hardware.Globals;
import org.firstinspires.ftc.teamcode.hardware.RobotHardware;
import org.jetbrains.annotations.NotNull;


public class IntakeSubsystem extends SubsystemBase {

    private RobotHardware robot;
    IntakeServoState intakeServo=IntakeServoState.INTAKE_UP;
    RollerIntakeState rollerIntakeState;
    public static int intakeServoStateId=0;

    public enum IntakeServoState{
        INIT,
        INTAKE_DOWN,
        INTAKE_UP
    }

    public enum RollerIntakeState{
        PIXEL_IN,
        PIXEL_OUT,
        INTAKE_ON,
        INTAKE_OFF
    }
    public IntakeSubsystem(RobotHardware robot) {this.robot=robot;//=RobotHardware.getInstance();
    }

    public void updateState(IntakeServoState state){
        this.intakeServo=state;  //Updating State
        switch (state){
            case INIT:
                SetIntakePosition(Globals.intakeServoInitPos);
                break;
            case INTAKE_DOWN:
                SetIntakePosition(Globals.intakeServoDownPos);
                break;
            case INTAKE_UP:
                SetIntakePosition(Globals.intakeServoUpPos);
                break;
        }
    }

    public IntakeServoState getIntakeServoState(IntakeServoState state){
        return  (state==IntakeServoState.INTAKE_UP)? IntakeServoState.INTAKE_DOWN:IntakeServoState.INTAKE_UP;
    }


    public void updateState(RollerIntakeState state){
    this.rollerIntakeState=state;
    switch (state){
        case INTAKE_ON:
            IntakeStart();
            break;
        case INTAKE_OFF:
            IntakeStop();
            break;
        }
    }

    @Override
    public void periodic(){

//        if(rollerIntakeState==RollerIntakeState.INTAKE_ON){
//            IntakeStart();
//        } else if (rollerIntakeState==RollerIntakeState.INTAKE_OFF){
//            IntakeStop();
//        } else if (rollerIntakeState==RollerIntakeState.PIXEL_OUT) {
//            IntakeReverse();
//        }
    }


    public void SetIntakePosition(double intakeServoPos) {
        robot.intakeRight.setPosition(intakeServoPos);
        robot.intakeLeft.setPosition(1- intakeServoPos);
    }
    //Intake Motor
    public  void IntakeStart()
    {
        intakeServoStateId=0;
        robot.Intake.setPower(0.8);
    }
    public  void IntakeStop()
    {
        robot.Intake.setPower(0.0);
    }
    public  void IntakeReverse()
    {
        robot.Intake.setPower(-0.8);
    }

}
