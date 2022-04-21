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
import edu.wpi.first.util.net.PortForwarder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class VisionSubsystem extends SubsystemBase {

  /*  Every time you enable the robot, a table of values is sent on the RIO's server and is stored in a table called Network Tables
      These tables have information about what color alliance we are on, what the PID values are, and each of our subsystems
      It also provides information about motor speeds and any additional inforamtion like what you are seeing here
      We use this table primarily for vision since our vision software sends target information to Network Tables
      You can access these tables using a program called Outline Viewer as a client
  */
  NetworkTableInstance table = NetworkTableInstance.getDefault(); //fetches the NetworkTable as soon as code is deployed
  NetworkTable fmsTable = table.getTable("FMSInfo"); //this is a subtable that contains information about alliance, match number, and anything else related to Field Management Systems(FMS)
  NetworkTableEntry isRedAlliance = fmsTable.getEntry("IsRedAlliance"); //an entry within the substable fmsTable

                                        //pass in the camera name exactly        
  PhotonCamera camera = new PhotonCamera("Microsoft_LifeCam_HD-3000"); //We use the PhotonCamera interface to directly access the vision data on network tables

  /** Creates a new VisionSubsystem. */
  public VisionSubsystem() {
    PortForwarder.add(5800, "photonvision.local", 5800); //must be done at competition to access the vision calibration interface
  }
  /**
   * Checks to see if we are the red alliance
   * @return if we are the red alliance
   */
  public boolean isRedAlliance() {
    return isRedAlliance.getBoolean(true); 
  }

  /**
   * Depending on what alliance we are on, get the best target(classified by the largest target) detected and get its yaw value
   * @return the yaw value of the ball in relation to the robot. Counterclockwise is positve
   */
  public double getBallAngle() {
      if (isRedAlliance()) {
        camera.setPipelineIndex(1);
        //switch to red ball pipeline
      } else {
        camera.setPipelineIndex(0); 
        //switch to blue ball pipeline
      }
    PhotonPipelineResult pipelineResult = camera.getLatestResult(); //gets the latest result from the camera
    PhotonTrackedTarget target = pipelineResult.getBestTarget(); //gets the best target based on most pixels detected
    if(pipelineResult.hasTargets()) { //if we have a target, get the target yaw value
      return -target.getYaw();
    } else {
      return 0; //if we do not have a target, return 0
    }
    
   
  }
  /**
   * checks to see if there is a ball present
   * @return if there is a ball present
   */
  public boolean isThereABall(){
    if(getBallAngle()==0){
      return false;
    }
    else{
      return true;
    }
  }
  
/**
 * fetches the goal angle from the data from the goal pipeline
 * @return the goal angle 
 */
  public double getGoalAngle() {
    camera.setPipelineIndex(3); //switch to the goal pipeline first
    PhotonPipelineResult pipelineResult = camera.getLatestResult();
    if (pipelineResult.hasTargets()) {
      PhotonTrackedTarget target = pipelineResult.getBestTarget();
      return target.getYaw();
    }
      return 0; //defualt value to return if there are no targets
  }

  /**
   * Depending on what alliance we are on, get the best target(classified by the largest target) detected and get the distance value
   * @return the X value distance of the ball in relation to the robot. Counterclockwise is positve
   */
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

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    
  }
}
