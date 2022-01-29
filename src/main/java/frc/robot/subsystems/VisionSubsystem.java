// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.Arrays;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class VisionSubsystem extends SubsystemBase {
  /** Creates a new VisionSubsystem. */

  NetworkTableInstance table = NetworkTableInstance.getDefault();
  NetworkTable visionTable = table.getTable("chameleon-vision").getSubTable("Microsoft LifeCam HD-3000");
  NetworkTableEntry poseEntry = visionTable.getEntry("targetPose");
  NetworkTableEntry yawEntry = visionTable.getEntry("targetYaw");

  public VisionSubsystem() {

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
