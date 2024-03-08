package org.firstinspires.ftc.teamcode.opmode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;


@Config
@TeleOp
public class IntakeTest extends LinearOpMode {

    public DcMotorEx intake=null,intake1,intake2,intake3;
    public static double power=1;
    public static double servoPos1=0.5;
    public static double servoPos2=0.5;
    public static double servoPosX=0.5;





    public Servo s1=null,s2=null;
    @Override
    public void runOpMode() throws InterruptedException {

        intake=hardwareMap.get(DcMotorEx.class,"inrake");
        intake1=hardwareMap.get(DcMotorEx.class,"inrake1");
        intake2=hardwareMap.get(DcMotorEx.class,"inrake2");
        intake3=hardwareMap.get(DcMotorEx.class,"inrake3");


        s1=hardwareMap.get(Servo.class,"s1");
        s2=hardwareMap.get(Servo.class,"s2");
        s1.setPosition(servoPos1);
        s2.setPosition(servoPos2);

        waitForStart();

        while (opModeIsActive()){

            if (gamepad1.a){
                intake.setPower(1);
                intake1.setPower(1);
                intake2.setPower(1);
                intake3.setPower(1);

            } else if (gamepad1.b) {
                intake.setPower(0);
                intake1.setPower(0);
                intake2.setPower(0);
                intake3.setPower(0);
            }


//            if(gamepad1.a){
//                s1.setPosition(servoPos1);
//                s2.setPosition(servoPos2);
//            } else if (gamepad1.b) {
//                s1.setPosition(servoPos1);
//            } else if (gamepad1.x) {
////                s2.setPosition(servoPos2);
//                s1.setPosition(0.65);  //0.65 open
//                s2.setPosition(0.35);
//
//            } else if (gamepad1.right_bumper){
////                s1.setPosition(servoPosX);  //0.65 open
//                s1.setPosition(0.65);  //0.65 open
//
//            }
//            else if (gamepad1.left_bumper){
////                s2.setPosition(servoPosX);  //0.35 open
//                s2.setPosition(0.35);
//
//            }
//            else if (gamepad1.dpad_up) {
////                s1.setPosition(s1.getPosition()+0.01);
//                s1.setPosition(0.5);
//            } else if (gamepad1.dpad_down) {
//                s2.setPosition(0.5);
//            } else if (gamepad1.dpad_right) {
//                s2.setPosition(servoPos1);
//
//            } else if (gamepad1.dpad_left) {
//                s1.setPosition(servoPos1);
//            } else if (gamepad1.a) {
//                intake.setPower(1);
//            }

//            telemetry.addData("s1",s1.getPosition());
//            telemetry.addData("s2",s2.getPosition());
            telemetry.addData("m1",intake.getCurrent(CurrentUnit.AMPS));
            telemetry.addData("m2",intake1.getCurrent(CurrentUnit.AMPS));
            telemetry.addData("m3",intake2.getCurrent(CurrentUnit.AMPS));
            telemetry.addData("m4",intake3.getCurrent(CurrentUnit.AMPS));
            telemetry.update();


        }



    }
}
