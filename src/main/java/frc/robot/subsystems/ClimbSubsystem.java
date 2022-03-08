// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MotorIDConstants;

public class ClimbSubsystem extends SubsystemBase {
  /** Creates a new ClimbSubsystem. */
  WPI_TalonSRX upperClimbMotor = new WPI_TalonSRX(MotorIDConstants.upperClimbMotorID);
  WPI_VictorSPX lowerClimbMotor = new WPI_VictorSPX(MotorIDConstants.lowerClimbMotorID);

  MotorControllerGroup climbMotors = new MotorControllerGroup(upperClimbMotor, lowerClimbMotor);

  public ClimbSubsystem() {
    lowerClimbMotor.follow(upperClimbMotor);
  }

  public void climbUp() {
    upperClimbMotor.set(.5);
  }

  public void climbDown() {
    upperClimbMotor.set(.5);
  }
  public void stopClimb() {
    upperClimbMotor.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
