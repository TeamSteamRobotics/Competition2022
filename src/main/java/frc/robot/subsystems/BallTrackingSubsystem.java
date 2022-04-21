// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class BallTrackingSubsystem extends SubsystemBase {
 
  //Creates the sensors mapped on the DIO ports of the RIO that will tell us the presence of an object by inhibiting infrared beams
  DigitalInput hopperSensor = new DigitalInput(2);
  DigitalInput intakeSensor = new DigitalInput(1);
  DigitalInput kickerSensor = new DigitalInput(0);


 /** Creates a new BallTrackingSubsystem. */
  public BallTrackingSubsystem() {

  }

  /**
   * Is a ball at the intake?
   * @return if a ball is at the intake
   */
  public boolean isAtIntake(){
    return !intakeSensor.get();

  }
  /**
   * Is a ball at the kicker wheel?
   * @return if a ball is at the kicker wheel
   */
  public boolean isAtKicker(){
    return !kickerSensor.get();
  }

  /**
   * Is a ball at the hopper in between the intake and the kicker?
   * @return if a ball is at the hopper 
   */
  public boolean isAtHopper(){
    return !hopperSensor.get();
  }
  /**
   * Is the hopper full?
   * @return if the hopper is full
   */
  public boolean isHopperFull() {
    return isAtKicker() && (isAtHopper());
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
