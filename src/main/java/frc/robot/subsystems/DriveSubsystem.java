// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase {
WPI_TalonSRX leftFrontMotor = new WPI_TalonSRX(0);
WPI_TalonSRX leftBackMotor = new WPI_TalonSRX(1);
WPI_TalonSRX rightBackMotor = new WPI_TalonSRX(2);
WPI_TalonSRX rightFrontMotor = new WPI_TalonSRX(3);

MotorControllerGroup rightMotorController = new MotorControllerGroup(rightBackMotor, rightFrontMotor);
MotorControllerGroup leftMotorController = new MotorControllerGroup(leftBackMotor, leftFrontMotor);


DifferentialDrive diffDrive = new DifferentialDrive(leftMotorController, rightMotorController);

public DriveSubsystem() {
  rightMotorController.setInverted(true);

}

public void drive(double speed, double rotation, boolean squareInputs) {
  diffDrive.arcadeDrive(speed, rotation, squareInputs);
} 

public void stop() {
  rightMotorController.set(0);
  leftMotorController.set(0);
  
  }
  /** Creates a new DriveSubsystem. */


  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}