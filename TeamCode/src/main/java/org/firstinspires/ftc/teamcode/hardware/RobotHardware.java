package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.drive.drivetrain.MecanumDrivetrain;
import org.firstinspires.ftc.teamcode.subsystem.IntakeSubsystem;

import javax.annotation.concurrent.GuardedBy;

public class RobotHardware {
    //Todo Intake Actuators
    public  Servo intakeLeft =null, intakeRight=null;  //Actuators for stack and pixel intake.
    public  DcMotorEx Intake=null;             //Roller Intake.

    //Todo Outake Actuators
    public  Servo outtakeCrank=null;          //
    public  Servo pixelLock=null;             //Holds pixel inside bucket.
    public  Servo safeLock=null;              //Safe lock pixel from falling from bucket end.
    public  Servo outtakeRight=null ,outtakeLeft=null;  //Actuators to rotate Bucket

    //Todo Elevator Actustors
    public DcMotorEx leftElevator=null;
    public DcMotorEx rightElevator=null;

    //Todo drivetrain
    public DcMotorEx dtFrontLeftMotor;
    public DcMotorEx dtFrontRightMotor;
    public DcMotorEx dtBackLeftMotor;
    public DcMotorEx dtBackRightMotor;
    public MecanumDrivetrain drivetrain;


    //Todo SettingUp IMU
    private final Object imuLock = new Object();
    @GuardedBy("imuLock")
    public BNO055IMU imu;
    private Thread imuThread;
    private double imuAngle = 0;
    private double imuOffset = 0;
    private double startOffset = 0;

    //Battery Voltage
    private ElapsedTime voltageTimer = new ElapsedTime();
    private double voltage = 12.0;



    //TODO ROBOT SETUP
    /////////////////////////////////////////////////////////////////////////////ROBOT SETUP BEGINS
    private static RobotHardware instance = null;    // ref variable to use robot hardware
    public boolean enabled;                          //boolean to return instance if robot is enabled.

    public static RobotHardware getInstance() {
        if (instance == null) {
            instance = new RobotHardware();
        }
        instance.enabled = true;
        return instance;
    }
    /////////////////////////////////////////////////////////////////////////////ROBOT SETUP ENDS
    //////
    public IntakeSubsystem intake;
    private HardwareMap hardwareMap;
//    public MecanumDrivetrain drivetrain;

    //Todo init() for hardware map
    //Call this method inside auto and teleop classes to instantly hardware map all actuators.
    public void init(HardwareMap hardwareMap, Telemetry telemetry){
    this.hardwareMap=hardwareMap;

    drivetrain = new MecanumDrivetrain();


        //Map Intake Actuators
    intakeLeft   = hardwareMap.get(Servo.class, "LeftIntake");
    intakeRight  = hardwareMap.get(Servo.class, "RightIntake");
    Intake       = hardwareMap.get(DcMotorEx.class, "Intake");

    //Map Outake Actuators
    outtakeCrank = hardwareMap.get(Servo.class, "BedTilt");
    safeLock     = hardwareMap.get(Servo.class, "Stopper");
    outtakeRight = hardwareMap.get(Servo.class, "RightAxon");
    outtakeLeft  = hardwareMap.get(Servo.class, "LeftAxon");
    pixelLock    = hardwareMap.get(Servo.class, "Crab");

    //Map Elevators Actuators
    rightElevator= hardwareMap.get(DcMotorEx.class, "sliderMotorOne");
    leftElevator = hardwareMap.get(DcMotorEx.class, "sliderMotorTwo");

    //DRIVE
        this.dtBackLeftMotor = hardwareMap.get(DcMotorEx.class, "leftRear");
        dtBackLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        dtBackLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        this.dtFrontLeftMotor = hardwareMap.get(DcMotorEx.class, "leftFront");
        dtFrontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        dtFrontLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        this.dtBackRightMotor = hardwareMap.get(DcMotorEx.class, "rightRear");
        dtBackRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        dtBackRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        this.dtFrontRightMotor = hardwareMap.get(DcMotorEx.class, "rightFront");
        dtFrontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        dtFrontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

    //Map IMU
        if (Globals.IS_IMU) {
            synchronized (imuLock) {
                imu = hardwareMap.get(BNO055IMU.class, "imu");
                BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
                parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
                imu.initialize(parameters);
            }
        }

    voltage = hardwareMap.voltageSensor.iterator().next().getVoltage();
    }


    public void startIMUThread(LinearOpMode opMode) {
        imuThread = new Thread(() -> {
            while (!opMode.isStopRequested()) {
                synchronized (imuLock) {
                    imuAngle = AngleUnit.normalizeRadians(imu.getAngularOrientation().firstAngle + startOffset);
                }
            }
        });
        imuThread.start();
    }

    public double getAngle() {
        return AngleUnit.normalizeRadians(imuAngle - imuOffset);
    }

    public void reset() {
        imuOffset = imuAngle;
    }


    //DRIVE TRAIN
    public void write() {
        drivetrain.write();
    }

    public void write( double slow) {
        drivetrain.write(slow);
    }













}
