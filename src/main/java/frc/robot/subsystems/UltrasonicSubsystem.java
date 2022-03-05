// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.RobotController; 

public class UltrasonicSubsystem extends SubsystemBase {


  double rawValue; //voltage of pin representing distance
  private final AnalogInput ultrasonic;  

  public UltrasonicSubsystem() {
    ultrasonic = new AnalogInput(0);
  } 
  
  //returns current distance in cm
  public double getDistance() {
    rawValue = ultrasonic.getValue(); //get current voltage value
    double voltage_scale_factor = 5 / RobotController.getVoltage5V(); //convert it to fraction  
    double currentDistanceCentimeters = rawValue * voltage_scale_factor * 0.125; //convert fraction to cm
    return currentDistanceCentimeters; 
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
