// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class VisionSubsystem extends SubsystemBase {
  /** Creates a new VisionSubsystem. */




  
  NetworkTableInstance table = NetworkTableInstance.getDefault();
  //NetworkTable visionTable = table.getTable("photonvision").getSubTable("Microsoft LifeCam HD-3000");
  NetworkTable fmsTable = table.getTable("FMSInfo");
  NetworkTableEntry isRedAlliance = fmsTable.getEntry("IsRedAlliance");
 // NetworkTableEntry poseEntry = visionTable.getEntry("targetPose");
  //NetworkTableEntry yawEntry = visionTable.getEntry("targetYaw");

  PhotonCamera camera = new PhotonCamera("Microsoft_LifeCam_HD-3000 (1)");
  /*PhotonPipelineResult result = camera.getLatestResult();
  PhotonTrackedTarget target = result.getBestTarget();*/


  public VisionSubsystem() {
  }

  public boolean isRedAlliance() {
    return isRedAlliance.getBoolean(true);
  }

  public double getBallAngle() {
      if (isRedAlliance()) {
        camera.setPipelineIndex(1);
        System.out.println("red");
        //switch to red pipeline
      } else {
        camera.setPipelineIndex(0); 
        System.out.println("blue");
        //switch to blue pipeline
      }
    PhotonPipelineResult pipelineResult = camera.getLatestResult();
    PhotonTrackedTarget target = pipelineResult.getBestTarget();
  
    if(pipelineResult.hasTargets()) {
      
      System.out.println("Yaw:: " + target.getYaw());
      return target.getYaw();
    } else {
      
      return 0; //default distance value
    }
    
   
  }

  public double getGoalAngle() {
    camera.setPipelineIndex(0);
    PhotonPipelineResult res = camera.getLatestResult();
    PhotonTrackedTarget goal = res.getBestTarget();
    if(res.hasTargets())
    {
    return goal.getYaw();
    }
    return 0;
  }
 
  public double getBallDistance() {
    if (isRedAlliance()) {
      camera.setPipelineIndex(1);
      //switch to red pipeline
    } else {
      camera.setPipelineIndex(2);  
    }
      PhotonPipelineResult pipelineResult = camera.getLatestResult();
    if(pipelineResult.hasTargets()) {
      PhotonTrackedTarget target = pipelineResult.getBestTarget();
      System.out.println(target.getCameraToTarget().getX()); 
       return target.getCameraToTarget().getX();
    
    }
  return 0;
  }

  public double getHoopDistance() {
    camera.setPipelineIndex(2); //switch to hoop pipeline
    PhotonPipelineResult pipelineResult = camera.getLatestResult();
    if(pipelineResult.hasTargets()) {
      PhotonTrackedTarget target = pipelineResult.getBestTarget();
      return Math.hypot(target.getCameraToTarget().getX(), target.getCameraToTarget().getY());
    }
    return 0; 
    //default distance value
    /*double[] defaultPose = {0,0,0};
    double[] targetPose = poseEntry.getDoubleArray(defaultPose);
    return Math.hypot(targetPose[0], targetPose[1]);*/
  }

  public double getHoopDegrees() {
    camera.setPipelineIndex(2);
    PhotonPipelineResult pipelineResult = camera.getLatestResult();
    if (pipelineResult.hasTargets()) {
      PhotonTrackedTarget target = pipelineResult.getBestTarget();
      return target.getYaw();
    }
    return 0;
  }

  /*public double getTargetYaw() {
    return target.getYaw();
    //return yawEntry.getDouble(0);
  }*/
  /*public double[] getCoordinates() {
    double[] pose = poseEntry.getDoubleArray(new double[3]);
    return Arrays.copyOfRange(pose, 0, 2);
  }*/

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    
  }
}
