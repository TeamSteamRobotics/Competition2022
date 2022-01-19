// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.function.BooleanSupplier;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {
  /** Creates a new ShooterSubsystem. */

  WPI_TalonSRX shooterMotor = new WPI_TalonSRX(5);
  

  public ShooterSubsystem() {}

  public void shoot(double speed){
    shooterMotor.set(speed);
  }

  public void stop(){
    shooterMotor.set(0);
  }

  public boolean isShooterAtMaxSpeed( /*fix this */ boolean isShooterMax){
    //create logic
    return isShooterMax;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
