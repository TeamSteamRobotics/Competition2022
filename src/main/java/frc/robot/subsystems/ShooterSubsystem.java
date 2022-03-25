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

  WPI_TalonSRX backspinMotor = new WPI_TalonSRX(MotorIDConstants.backspinMotorID);

  public ShooterSubsystem() {
    rightFlywheelMotor.setNeutralMode(NeutralMode.Coast);
    rightFlywheelMotor.setSensorPhase(true);
    rightFlywheelMotor.selectProfileSlot(0, 0);
    leftFlywheelMotor.follow(rightFlywheelMotor);
    leftFlywheelMotor.setInverted(InvertType.OpposeMaster);
    
    backspinMotor.setNeutralMode(NeutralMode.Coast);
    backspinMotor.setSensorPhase(false);
    backspinMotor.selectProfileSlot(0, 0);
    backspinMotor.config_kP(0, 0.01);
    backspinMotor.config_kI(0, .0000);
    backspinMotor.config_kD(0, 00);
    //backspinMotor.config_kF(0, .011);

    rightFlywheelMotor.config_kP(0, .21);
    rightFlywheelMotor.config_kI(0, .0002);
    rightFlywheelMotor.config_kD(0, .001);
    rightFlywheelMotor.config_kF(0, .017);
    rightFlywheelMotor.config_IntegralZone(0, 200);
  }

  public void shoot(double speed){
    rightFlywheelMotor.set(ControlMode.Velocity, speed);
    backspinMotor.set(.2); //.4
  }

  public double getSTUs() {
    return rightFlywheelMotor.getSelectedSensorVelocity();
  }
  public void resetIntegral(){
    rightFlywheelMotor.setIntegralAccumulator(0);
    //rightFlywheelMotor.setIntegralAccumulator(1000000);
    //rightFlywheelMotor.config_kI(slotIdx, value, timeoutMs)

  }
  

  public double getLoopError(){
    return rightFlywheelMotor.getClosedLoopError();
  }
  public boolean getAccelerationError(){
    System.out.println("acceleration error" + rightFlywheelMotor.getErrorDerivative());
    return Math.abs(rightFlywheelMotor.getErrorDerivative()) < 20;
    
  }
  
  public boolean isAtSpeed() {
    
    if(this.getSTUs() <1000) {return false;}
    else{return (Math.abs(rightFlywheelMotor.getClosedLoopError()) < Constants.FlywheelConstants.velocityTolerance);}
  }

  public double getRPM() {
    return (this.rightFlywheelMotor.getSelectedSensorVelocity() / 4096) * 10 * 60;
  }

  // SHould probably fill this out at some time
  public double getTargetSpeed() {
    return 0.0;
  }

  public void stop(){
    rightFlywheelMotor.set(0);
    backspinMotor.set(0);
  }
  public void stopBackspin(){
    backspinMotor.set(0);
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
