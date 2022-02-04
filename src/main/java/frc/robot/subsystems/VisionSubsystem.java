// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.Arrays;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.math.geometry.Transform2d;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Main;
import frc.robot.Robot;

public class VisionSubsystem extends SubsystemBase {
  /** Creates a new VisionSubsystem. */

  NetworkTableInstance table = NetworkTableInstance.getDefault();
  NetworkTable visionTable = table.getTable("photonvision").getSubTable("Microsoft LifeCam HD-3000");
  NetworkTable fmsTable = table.getTable("FMSInfo");
  NetworkTableEntry isRedAlliance = fmsTable.getEntry("IsRedAlliance");
  NetworkTableEntry poseEntry = visionTable.getEntry("targetPose");
  NetworkTableEntry yawEntry = visionTable.getEntry("targetYaw");

  Transform2d defaultTransform2d;

  PhotonCamera camera = new PhotonCamera("photonvision");

  public VisionSubsystem() {

  }

  public boolean isRedAlliance() {
    return isRedAlliance.getBoolean(true);
  }

  public Transform2d getBallDistance() {
    /*var result = camera.getLatestResult();
    PhotonTrackedTarget target = result.getBestTarget();
    if (result.hasTargets()) {
      if (isRedAlliance()) {
        camera.setPipelineIndex(1); //change to red ball pipeline
        return target.getCameraToTarget();
      } else {
        camera.setPipelineIndex(0); //change to blue ball pipeline
        return target.getCameraToTarget();
      } else {
        return
      }*/
      return defaultTransform2d;
      
  }
 

  public double getTargetDistance() {
    double[] defaultPose = {0,0,0};
    double[] targetPose = poseEntry.getDoubleArray(defaultPose);
    return Math.hypot(targetPose[0], targetPose[1]);
  }

  public double getTargetX() {
    return yawEntry.getDouble(0) / 30;
  }

  public double getTargetYaw() {
    return yawEntry.getDouble(0);
  }

  public double[] getCoordinates() {
    double[] pose = poseEntry.getDoubleArray(new double[3]);
    return Arrays.copyOfRange(pose, 0, 2);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
