package org.firstinspires.ftc.teamcode.subsystem;

import com.arcrobotics.ftclib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.hardware.RobotHardware;

public class OutakeSubsytem extends SubsystemBase {

    private RobotHardware robot;
    ShoulderState shoulderState=ShoulderState.INIT;
    public enum ShoulderState{
        PICK,
        DROP,
        INIT,
    }

    public OutakeSubsytem(RobotHardware robot) {
        this.robot = robot;
    }

    public void updateState(ShoulderState state){
        this.shoulderState=state;
        switch (state){
            case INIT:
                setOuttakeArm(0);
                break;
            case PICK:
                setOuttakeArm(0.01);
                break;
            case DROP:
                setOuttakeArm(0.95);
                break;
        }
    }

    @Override
    public void periodic() {
        super.periodic();
    }

    public  void setOuttakeArm(double AxonPos){
       robot.outtakeRight.setPosition(1-AxonPos);
       robot.outtakeLeft.setPosition(AxonPos);
    }








}
