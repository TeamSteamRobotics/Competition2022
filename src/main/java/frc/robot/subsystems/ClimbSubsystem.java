// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxAnalogSensor.Mode;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.ClimbConstants;
import frc.robot.Constants.MotorIDConstants;

public class ClimbSubsystem extends SubsystemBase {
  /** Creates a new ClimbSubsystem. */
  
  CANSparkMax leftClimb = new CANSparkMax(Constants.MotorIDConstants.leftClimbMotorID, MotorType.kBrushless);
  CANSparkMax rightClimb = new CANSparkMax(Constants.MotorIDConstants.rightClimbMotorID, MotorType.kBrushless);

  SparkMaxPIDController pidController = rightClimb.getPIDController(); //instantiate the PID of the rightClimbMotor
  RelativeEncoder rightEncoder = rightClimb.getEncoder(); //instaitiate the encoder of the rightClimbMotor

  public ClimbSubsystem() {
    leftClimb.follow(rightClimb, true);
    pidController.setP(0);
    pidController.setI(0);
    pidController.setD(0);
  }

  public void raiseClimb() {
    rightClimb.set(-.5);
  }

  public void retractClimb() {
    rightClimb.set(.5);
  }
  
  public void stopClimb() {
    rightClimb.set(0);
  }

  public double getClimbPosition() { 
    //postiton is measured in rotations (of motor I think, so it would be the same as ticks on a motor)
    //rightEncoder.setPositionConversionFactor(factor) //you can use this to change the readings of position to a more sensible unit but I have no idea what number that would be
    return rightEncoder.getPosition();
  }

  
  public void climbToPosition(double position) { //postition is rotations of the motor(ticks)
    //clamp the position value so that zero is the minimumClimbHeight and the other number is the maximumClimbHeight
    double clampedPosition = MathUtil.clamp(position, ClimbConstants.minimumClimbHeight, ClimbConstants.maximumClimbHeight);
    pidController.setReference(clampedPosition, ControlType.kPosition);
  }

  public void resetClimbPosition() {
    rightEncoder.setPosition(0); 
  }

  public boolean isAtClimbPosition() {
    return (Math.abs(pidController.getSmartMotionAllowedClosedLoopError(0)) < ClimbConstants.positionTolerance); //please look at this
  }

  public void setBrakeMode() {
    rightClimb.setIdleMode(IdleMode.kBrake);
    leftClimb.setIdleMode(IdleMode.kBrake);
  }

  public void setCoastMode() {
    rightClimb.setIdleMode(IdleMode.kCoast);
    leftClimb.setIdleMode(IdleMode.kCoast);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
