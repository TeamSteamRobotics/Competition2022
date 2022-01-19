// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {
  /** Creates a new IntakeSubsystem. */

  WPI_TalonSRX intakeMotor = new WPI_TalonSRX(3);

  public IntakeSubsystem() {}

  public void intake(){
    intakeMotor.set(0.25);
  }

  public void stop(){
    intakeMotor.set(0);
  }

  public void reverseIntake(){
    intakeMotor.set(-0.25);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
