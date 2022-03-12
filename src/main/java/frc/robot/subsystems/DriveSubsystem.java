// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.MotorIDConstants;

public class DriveSubsystem extends SubsystemBase {


public AHRS gyro = new AHRS();

WPI_TalonFX leftFrontMotor = new WPI_TalonFX(MotorIDConstants.leftFrontMotorID);
WPI_TalonFX leftBackMotor = new WPI_TalonFX(MotorIDConstants.leftBackMotorID);
WPI_TalonFX rightBackMotor = new WPI_TalonFX(MotorIDConstants.rightBackMotorID);
WPI_TalonFX rightFrontMotor = new WPI_TalonFX(MotorIDConstants.rightFrontMotorID);


MotorControllerGroup rightMotorController = new MotorControllerGroup(rightBackMotor, rightFrontMotor);
MotorControllerGroup leftMotorController = new MotorControllerGroup(leftBackMotor, leftFrontMotor);

public DifferentialDrive diffDrive = new DifferentialDrive(leftMotorController, rightMotorController);


public DriveSubsystem() {
  rightMotorController.setInverted(true); 
  resetGyro();
  

}

public void drive(double speed, double rotation, boolean squareInputs, boolean aimingForGoal) {
  if(aimingForGoal){diffDrive.arcadeDrive(1*speed, 1*rotation, squareInputs);}
  else{
  diffDrive.arcadeDrive(-1*speed, 1*rotation, squareInputs);  
  }
  
  
}

public void resetGyro() {
  gyro.reset();
}

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
