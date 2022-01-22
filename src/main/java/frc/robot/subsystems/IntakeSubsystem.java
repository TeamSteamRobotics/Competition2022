// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {
  /** Creates a new IntakeSubsystem. */

  WPI_TalonSRX leftIntakeMotor = new WPI_TalonSRX(9);
  //WPI_TalonSRX rightIntakeMotor = new WPI_TalonSRX(6);

 // MotorControllerGroup intakeMotors = new MotorControllerGroup(leftIntakeMotor, rightIntakeMotor);
  
  public IntakeSubsystem() {
    
  }

  public void intake(){
    leftIntakeMotor.set(0.23);
   
    //rightIntakeMotor.set(-0.5);
  }

  public void stopIntake() {
    leftIntakeMotor.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
