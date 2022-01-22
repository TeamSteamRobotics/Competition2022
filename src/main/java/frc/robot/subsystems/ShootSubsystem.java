// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShootSubsystem extends SubsystemBase {
  WPI_TalonSRX shooterMaster = new WPI_TalonSRX(9);
  //WPI_TalonSRX shooterFollower = new WPI_TalonSRX(21);

  /** Creates a new ShootSubsystem. */
  public ShootSubsystem() {
    /*shooterFollower.setInverted(true);
    shooterFollower.set(ControlMode.Follower, 9);*/
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void shoot() {
  
    shooterMaster.set(0.5);
    //shooterFollower.set(-0.5);
    
  }
  public void stop() {
    shooterMaster.set(0);
    //shooterFollower.set(0);
  }
}
