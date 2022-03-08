// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimbConstants;
import frc.robot.Constants.MotorIDConstants;

public class ClimbSubsystem extends SubsystemBase {
  /** Creates a new ClimbSubsystem. */
  CANSparkMax rightLifter = new CANSparkMax(MotorIDConstants.rightClimbMotorID, MotorType.kBrushless);
  CANSparkMax leftLifter = new CANSparkMax(MotorIDConstants.leftClimbMotorID, MotorType.kBrushless);


  SparkMaxPIDController pidController = rightLifter.getPIDController(); //instantiate the PID of the rightClimbMotor
  RelativeEncoder rightEncoder = rightLifter.getEncoder(); //instaitiate the encoder of the rightClimbMotor

  public ClimbSubsystem() {
    leftLifter.follow(rightLifter);
    pidController.setP(0);
    pidController.setI(0);
    pidController.setD(0);
  }
  //try to use these Up and Down methods first before doing PID stuff
  public void climbUp() { 
    rightLifter.set(.5);
  }

  public void climbDown() {
    rightLifter.set(-.5);
    
  }
  public void stopClimb() {
    rightLifter.set(0);
  }

  public double getClimbPosition() { 
    //postiton is measured in rotations (of motor I think, so it would be the same as ticks on a motor)
    //rightEncoder.setPositionConversionFactor(factor) //you can use this to change the readings of position to a more sensible unit but I have no idea what number that would be
    return rightEncoder.getPosition();

  }

  public void climbToPosition(double position) { //postition is rotations of the motor(ticks)
    pidController.setReference(position, ControlType.kSmartMotion); //smart motion is the built in PID of the NEOs
  }

  public void resetClimbPosition() {
    rightEncoder.setPosition(0); 
  }

  public boolean isAtClimbHeight() {
    return (Math.abs(pidController.getSmartMotionAllowedClosedLoopError(0)) < ClimbConstants.positionTolerance); //please look at this
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
