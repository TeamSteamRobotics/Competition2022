// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class BallTrackingSubsystem extends SubsystemBase {
  /** Creates a new BallTrackingSubsystem. */

  DigitalInput hopperSensor = new DigitalInput(0);
  //DigitalInput middleSensor = new DigitalInput(1);
  DigitalInput intakeSensor = new DigitalInput(1);
  DigitalInput kickerSensor = new DigitalInput(2);

  public BallTrackingSubsystem() {

  }

  public boolean isAtIntake(){
    return intakeSensor.get();
  }

  public boolean isAtKicker(){
    return kickerSensor.get();
  }

  /*public boolean isAtMiddle(){
    return middleSensor.get();
  }*/

  public boolean isAtHopper(){
    return hopperSensor.get();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
