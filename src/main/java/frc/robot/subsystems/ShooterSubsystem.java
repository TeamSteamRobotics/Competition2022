// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.FlywheelConstants;
import frc.robot.Constants.MotorIDConstants;

public class ShooterSubsystem extends SubsystemBase {
  /** Creates a new ShooterSubsystem. */

  WPI_TalonSRX flywheelMotor = new WPI_TalonSRX(MotorIDConstants.flywheelMotorID);
  

  public ShooterSubsystem() {
    flywheelMotor.setNeutralMode(NeutralMode.Coast);
    flywheelMotor.setSensorPhase(true);
    flywheelMotor.selectProfileSlot(0, 0);

    flywheelMotor.config_kP(0, .2);
    flywheelMotor.config_kI(0, .00004);
    flywheelMotor.config_kD(0, .05);
    //flywheelMotor.config_kF(0, .01760006);
    //flywheelMotor.config_kF(0, .02);
  }

  public void shoot(double speed){
    //flywheelMotor.set(-1*speed);
    flywheelMotor.set(ControlMode.Velocity, speed);
    System.out.println("Shooter RPMs: " + getRPM());
    System.out.println("Shooter Speed: " + flywheelMotor.getSelectedSensorVelocity());
  }

  public double getSTUs() {
    return flywheelMotor.getSelectedSensorVelocity();
  }

  public double getRPM() {
    return (flywheelMotor.getSelectedSensorVelocity() / 10);
  }

  // SHould probably fill this out at some time
  public double getTargetSpeed() {
    return 0.0;
  }

  public void stop(){
    flywheelMotor.set(0);
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}