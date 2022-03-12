// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import org.photonvision.PhotonCamera;
import org.photonvision.PhotonUtils;
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

  PhotonCamera camera = new PhotonCamera("Microsoft_LifeCam_HD-3000");
  //PhotonCamera camera2 = new PhotonCamera("Microsoft_LifeCam_HD-3000 (1)");
  
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
        //switch to red pipeline
      } else {
        camera.setPipelineIndex(0); 
        //switch to blue pipeline
      }
    PhotonPipelineResult pipelineResult = camera.getLatestResult();
    PhotonTrackedTarget target = pipelineResult.getBestTarget();
    if(pipelineResult.hasTargets()) {
      return -target.getYaw();
    } else {
      return 0; 
    }
    
   
  }
  public boolean isThereABall(){
    if(getBallAngle()==0){
      return false;
    }
    else{return true;}
  }
  

  public double getGoalAngle() {
    camera.setPipelineIndex(3);
      PhotonPipelineResult pipelineResult = camera.getLatestResult();
      if (pipelineResult.hasTargets()) {
        PhotonTrackedTarget target = pipelineResult.getBestTarget();
        return target.getYaw();
      }
         return 0;
       }



  
  public double getBallDistance() {
    if (isRedAlliance()) {
      camera.setPipelineIndex(1);
    } else {
      camera.setPipelineIndex(2);  
    }
      PhotonPipelineResult pipelineResult = camera.getLatestResult();
    if(pipelineResult.hasTargets()) {
      PhotonTrackedTarget target = pipelineResult.getBestTarget();
       return target.getCameraToTarget().getX();
    }
  return 0;
  }


  public double getTargetDegrees(boolean aimingForGoal) {
    if(aimingForGoal){
      return getGoalAngle();
    }  
    else{
      return getBallAngle();}
    }
  
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    
  }
}
