// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase {
  /** Creates a new DriveSubsystem. */

  //motors initiated 
  
  WPI_TalonSRX leftBack = new WPI_TalonSRX(1);
  WPI_TalonSRX leftFront = new WPI_TalonSRX(0);
  WPI_TalonSRX rightBack = new WPI_TalonSRX(3);
  WPI_TalonSRX rightFront = new WPI_TalonSRX(2);
  //grouping up wheels so same speed for diff drive haha

  

  //SpeedControllerGroup left = new SpeedControllerGroup(leftBack, leftFront);
  //SpeedControllerGroup right = new SpeedControllerGroup(rightBack, rightFront);

  MotorControllerGroup leftGroup = new MotorControllerGroup(leftBack, leftFront);
  MotorControllerGroup rightGroup = new MotorControllerGroup(rightBack, rightFront);

  
  //drive command stuff yep.
  DifferentialDrive diffDrive = new DifferentialDrive(leftGroup, rightGroup);
 
 
 public DriveSubsystem() {
   rightGroup.setInverted(true);
 }

  public void drive(double speed, double turn, boolean squareInputs){
    
    diffDrive.arcadeDrive(speed, turn, squareInputs);

  }

  public void stop(){

    diffDrive.arcadeDrive(0, 0);

  }

  public double getDistance(){
    return 0.5/4096 * (leftFront.getSelectedSensorPosition() - rightFront.getSelectedSensorPosition()) / 2.0;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }



}
