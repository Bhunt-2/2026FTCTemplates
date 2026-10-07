package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Subsystems.Drive;
import org.firstinspires.ftc.teamcode.Subsystems.OTOS;
import org.firstinspires.ftc.teamcode.Subsystems.Limelight;


public class robot extends OpMode{

    Drive drivingPrereqs = new Drive();
    OTOS OTOSconfig = new OTOS();
    Limelight limelightConfig = new Limelight();

    double forward, strafe, rotate;

    @Override
    public void init() {

        drivingPrereqs.init(hardwareMap);
        limelightConfig.init(hardwareMap);

        telemetry.addData("Init Completed. Wait 1 second for OTOS calibration to finish.", null);
        telemetry.update();

    }

    @Override
    public void start(){
        limelightConfig.setupLimelight();
    }

    @Override
    public void loop() {
    //Driving Code
        forward = -gamepad1.left_stick_y;
        strafe = -gamepad1.left_stick_x;
        rotate = gamepad1.right_stick_x;

        drivingPrereqs.FieldOrientedTranslate(forward, strafe, rotate);

    //Limelight
        limelightConfig.getLimelightResult();
        limelightConfig.updateRobotPosition();

    //Telemetry
        telemetry.clearAll();
        telemetry.addData("Commanded Forward Amount", forward);
        telemetry.addData("Commanded Strafe Amount", strafe);
        telemetry.addData("Commanded Rotate Amount", rotate);
        telemetry.addData("Limelight Result", limelightConfig.llResult);
        telemetry.addData("Estimated Bot Pose", OTOSconfig.myOtos.getPosition());
        telemetry.update();

    }
}