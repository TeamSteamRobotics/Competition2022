// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class DriveSubsystem extends SubsystemBase {

AHRS gyro = new AHRS();

WPI_TalonSRX leftFrontMotor = new WPI_TalonSRX(DriveConstants.leftFrontMotorID);
WPI_TalonSRX leftBackMotor = new WPI_TalonSRX(DriveConstants.leftBackMotorID);
WPI_TalonSRX rightBackMotor = new WPI_TalonSRX(DriveConstants.rightBackMotorID);
WPI_TalonSRX rightFrontMotor = new WPI_TalonSRX(DriveConstants.rightFrontMotorID);

MotorControllerGroup rightMotorController = new MotorControllerGroup(rightBackMotor, rightFrontMotor);
MotorControllerGroup leftMotorController = new MotorControllerGroup(leftBackMotor, leftFrontMotor);

DifferentialDrive diffDrive = new DifferentialDrive(leftMotorController, rightMotorController);

public DriveSubsystem() {
  rightMotorController.setInverted(true);
}

public void drive(double speed, double rotation, boolean squareInputs) {
  diffDrive.arcadeDrive(speed, rotation, squareInputs);
}

// Call this command at the start of the game. Sets the gyro reading to zero
public void resetGyro() {
  gyro.zeroYaw();
}

// Returns the gyro's reading of the robots current angle
public double getAngle() {
  return gyro.getAngle();
}

public void stop() {
  rightMotorController.set(0);
  leftMotorController.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}