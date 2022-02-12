// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.MotorIDConstants;

public class DriveSubsystem extends SubsystemBase {

Gyro gyro;

WPI_TalonFX leftFrontMotor = new WPI_TalonFX(MotorIDConstants.leftFrontMotorID);
WPI_TalonFX leftBackMotor = new WPI_TalonFX(MotorIDConstants.leftBackMotorID);
WPI_TalonFX rightBackMotor = new WPI_TalonFX(MotorIDConstants.rightBackMotorID);
WPI_TalonFX rightFrontMotor = new WPI_TalonFX(MotorIDConstants.rightFrontMotorID);


MotorControllerGroup rightMotorController = new MotorControllerGroup(rightBackMotor, rightFrontMotor);
MotorControllerGroup leftMotorController = new MotorControllerGroup(leftBackMotor, leftFrontMotor);

public DifferentialDrive diffDrive = new DifferentialDrive(leftMotorController, rightMotorController);


public DriveSubsystem() {
  rightMotorController.setInverted(true);
  diffDrive.setSafetyEnabled(true);
  
  
  
}

public void drive(double speed, double rotation, boolean squareInputs) {
  diffDrive.arcadeDrive(-.7 * speed,-.7 * rotation, squareInputs);
  System.out.println(rightBackMotor.getSelectedSensorVelocity());
}

// Call this command at the start of the game. Sets the gyro reading to zero
public void resetGyro() {
  //gyro.zeroYaw();
  gyro.reset();
}

// Returns the gyro's reading of the robots current angle
public double getAngle() {
  return gyro.getAngle();
}

public void stop() {
  rightMotorController.set(0);
  leftMotorController.set(0);
  
  }

public double getDistance() {
  return (leftFrontMotor.getSelectedSensorPosition() + rightFrontMotor.getSelectedSensorPosition())/2 * Constants.DriveConstants.ftPerTick;
  
}



public void  resetEncoders(){
  leftFrontMotor.setSelectedSensorPosition(0);
  rightFrontMotor.setSelectedSensorPosition(0);

}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
