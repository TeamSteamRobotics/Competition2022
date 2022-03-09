// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.ClimbConstants;
import frc.robot.Constants.MotorIDConstants;

public class ClimbSubsystem extends SubsystemBase {
  /** Creates a new ClimbSubsystem. */
  
  CANSparkMax leftClimb = new CANSparkMax(Constants.MotorIDConstants.lowerClimbMotorID, MotorType.kBrushless);
  CANSparkMax rightClimb = new CANSparkMax(Constants.MotorIDConstants.rightBackMotorID, MotorType.kBrushless);

  public ClimbSubsystem() {
    leftClimb.follow(rightClimb, true);
  }

  public void raiseClimb() {
    rightClimb.set(-.5);
  }

  public void retractClimb() {
    rightClimb.set(.75);
  }
  
  public void stopClimb() {
    rightClimb.set(0);
    leftClimb.set(0);
  }

  // public double getClimbPosition() {
  //   return upperClimbMotor.getSelectedSensorPosition();
  // }

  // public void climbToPosition(double position) {
  //   upperClimbMotor.set(ControlMode.Position, position); //you may need to do this for lowerMotor but im not sure
  // }

  // public void resetClimbPosition() {
  //   upperClimbMotor.setSelectedSensorPosition(0);
  // }

  // public boolean isAtClimbHeight() {
  //   return (Math.abs(upperClimbMotor.getClosedLoopError()) < ClimbConstants.positionTolerance);
  // }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
