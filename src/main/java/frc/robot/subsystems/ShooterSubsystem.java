// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import java.beans.Encoder;
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
  WPI_VictorSPX backspinMotor = new WPI_VictorSPX(MotorIDConstants.backspinMotorID);
  //Encoder encoder = new Encoder();
  public ShooterSubsystem() {
    //rightFlywheelMotor.setNeutralMode(NeutralMode.Coast);
    //rightFlywheelMotor.setSensorPhase(false);
    //rightFlywheelMotor.selectProfileSlot(0, 0);
    leftFlywheelMotor.follow(rightFlywheelMotor);
    leftFlywheelMotor.setInverted(InvertType.OpposeMaster);
    //leftFlywheelMotor.set(ControlMode.Follower, MotorIDConstants.rightFlywheelMotorID);
    


    //rightFlywheelMotor.config_kP(0, .2);
    //rightFlywheelMotor.config_kI(0, .00004);
    //rightFlywheelMotor.config_kD(0, .05);
    //flywheelMotor.config_kF(0, .01760006);
    //flywheelMotor.config_kF(0, .02);
  }

  public void shoot(double speed){
    //flywheelMotor.set(-1*speed);
    rightFlywheelMotor.set(ControlMode.Velocity, speed);
    //System.out.println("Shooter RPMs: " + getRPM());
    //System.out.println("Shooter Speed: " + rightFlywheelMotor.getSelectedSensorPosition());
  }

  public void shoot2(double speed){
    rightFlywheelMotor.set(speed);
    //rightFlywheelMotor.set //ActiveTrajectoryVelocity(0);
    //System.out.println("Shooter Speed: " + rightFlywheelMotor.getSelectedSensorVelocity()); 
    System.out.println("Backspin Speed:" + backspinMotor.getSelectedSensorVelocity());
  }
  
  public void backspin(){
    backspinMotor.set(.25*rightFlywheelMotor.get());
    System.out.println("Backspin Speed:" + backspinMotor.getSelectedSensorVelocity());
  }

  public double getSTUs() {
    return rightFlywheelMotor.getSelectedSensorVelocity();
  }

  
public boolean isAtSpeed(DoubleSupplier speed){
  return (Math.abs(rightFlywheelMotor.getSelectedSensorVelocity() -speed.getAsDouble()) < Constants.FlywheelConstants.tolerance);
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
  }
  public void stopBackspin(){
    backspinMotor.set(0);
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}