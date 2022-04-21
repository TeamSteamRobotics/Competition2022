// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.Arrays;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.music.Orchestra;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.MotorIDConstants;

public class DriveSubsystem extends SubsystemBase {

//creates the navX gyro under the AHRS class
public AHRS gyro = new AHRS();
//creates the falcons used for the drive train
WPI_TalonFX leftFrontMotor = new WPI_TalonFX(MotorIDConstants.leftFrontMotorID);
WPI_TalonFX leftBackMotor = new WPI_TalonFX(MotorIDConstants.leftBackMotorID);
WPI_TalonFX rightBackMotor = new WPI_TalonFX(MotorIDConstants.rightBackMotorID);
WPI_TalonFX rightFrontMotor = new WPI_TalonFX(MotorIDConstants.rightFrontMotorID);
//an interface that allows us to use the falcons to play audio
Orchestra orch = new Orchestra(Arrays.asList(leftFrontMotor, leftBackMotor, rightBackMotor, rightFrontMotor));
//structures the falcons into left and right side groups that are controlled by motor controllers
MotorControllerGroup rightMotorController = new MotorControllerGroup(rightBackMotor, rightFrontMotor);
MotorControllerGroup leftMotorController = new MotorControllerGroup(leftBackMotor, leftFrontMotor);



//creates the differential drive by using the left and right sides 
public DifferentialDrive diffDrive = new DifferentialDrive(leftMotorController, rightMotorController);
/*The Differntial Drive works by facotring in the speeds of each side: 
1) Left Speed > Right Speed, the robot turns right
2) Left Speed < Right Speed, the robot turns left
3) |Left Speed| = |Right Speed| & directions are same, the robot goes straight in its orientation
4) |Left Speed| = |Right Speed| & directions are opposite, the robot goes spins about its axis
*/

/**Creates a new DriveSubsystem */
public DriveSubsystem() {
  rightMotorController.setInverted(true); 
  resetGyro();
}
/**
 * Drive our robot using an arcade-style control system
 * @param speed The robot's speed along the X axis [-1.0..1.0]. Forward is positive.
 * @param rotation The robot's rotation rate around the Z axis [-1.0..1.0]. Clockwise is positive.
 * @param squareInputs If set, decreases the input sensitivity at low speeds.
 */
public void drive(double speed, double rotation, boolean squareInputs) {
  diffDrive.arcadeDrive(-1*speed, 1*rotation, squareInputs);  
}
/**
 * loads music onto the falcons
 * @param songFilePath the path of the audio file to load
 */
public void loadMusic(String songFilePath) {
  orch.loadMusic(songFilePath);
}
/** plays the music loaded */
public void playMusic() {
  orch.play();
}
/** stops the played music */
public void stopMusic() {
  orch.stop();
}
/** resets the gyro readings */
public void resetGyro() {
  gyro.reset();
}
/**
 * Gets the yaw angle of the gyro where counterclockwise is positive. 
 * <p>
 * Can be over 360 since the sensor in continuous.
 * <p>
 * @return the yaw angle of the gyro in degrees.  
 */
public double getAngle() {
  return gyro.getAngle();
}
/** sets the left and right sides of the drivetrain to speeds of zero */
public void stop() {
  rightMotorController.set(0);
  leftMotorController.set(0);
  }
/**
 * gets the average distance traveled of the drivetrain and converts it into feet
 * @return the distance traveled in feet
 */
public double getDistance() {
  return (leftFrontMotor.getSelectedSensorPosition() + rightFrontMotor.getSelectedSensorPosition())/2 * Constants.DriveConstants.ftPerTick;
}

/**resets the encoders of the falcons to zero */
public void resetEncoders(){
  leftFrontMotor.setSelectedSensorPosition(0);
  rightFrontMotor.setSelectedSensorPosition(0);
}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
