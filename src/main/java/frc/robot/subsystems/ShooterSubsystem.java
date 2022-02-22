// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.FlywheelConstants;
import frc.robot.Constants.MotorIDConstants;

public class ShooterSubsystem extends SubsystemBase {
  /** Creates a new ShooterSubsystem. */

  WPI_VictorSPX leftFlywheelMotor = new WPI_VictorSPX(MotorIDConstants.leftFlywheelMotorID); //follower
  WPI_TalonSRX rightFlywheelMotor = new WPI_TalonSRX(MotorIDConstants.rightFlywheelMotorID); //master

  WPI_TalonSRX backspinMotor = new WPI_TalonSRX(MotorIDConstants.backspinMotorID);

  public ShooterSubsystem() {
    rightFlywheelMotor.setNeutralMode(NeutralMode.Coast);
    rightFlywheelMotor.setSensorPhase(true);
    rightFlywheelMotor.selectProfileSlot(0, 0);
    leftFlywheelMotor.follow(rightFlywheelMotor);
    leftFlywheelMotor.setInverted(InvertType.OpposeMaster);
    //leftFlywheelMotor.set(ControlMode.Follower, MotorIDConstants.rightFlywheelMotorID);
    
    backspinMotor.setNeutralMode(NeutralMode.Coast);
    backspinMotor.setSensorPhase(false);
    backspinMotor.selectProfileSlot(0, 0);
    backspinMotor.config_kP(0, 0.01);
    backspinMotor.config_kI(0, .0000);
    backspinMotor.config_kD(0, 00);
    //backspinMotor.config_kF(0, .011);

    rightFlywheelMotor.config_kP(0, .23);
    rightFlywheelMotor.config_kI(0, .00002);
    rightFlywheelMotor.config_kD(0, .115);
    rightFlywheelMotor.config_kF(0, .011);
    //flywheelMotor.config_kF(0, .02);
  }

  public void shoot(double speed){
   rightFlywheelMotor.set(ControlMode.Velocity, speed);
   // backspinMotor.set(ControlMode.Velocity, speed *.5);
    //System.out.println("Shooter Speed: " + rightFlywheelMotor.getSelectedSensorVelocity(0));
    System.out.println("backspinMotor Speed: " + backspinMotor.getSelectedSensorVelocity(0));
  }

  public double getSTUs() {
    return rightFlywheelMotor.getSelectedSensorVelocity();
  }

  
  public boolean isAtSpeed() {
    return (Math.abs(rightFlywheelMotor.getClosedLoopError()) < Constants.FlywheelConstants.tolerance);
  }

  public double getRPM() {
    return (rightFlywheelMotor.getSelectedSensorVelocity() / 10);
  }

  // SHould probably fill this out at some time
  public double getTargetSpeed() {
    return 0.0;
  }

  public void stop(){
    rightFlywheelMotor.set(0);
    backspinMotor.set(0);
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}