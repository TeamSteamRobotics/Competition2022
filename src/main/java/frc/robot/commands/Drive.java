// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.UltrasonicSubsystem;

public class Drive extends CommandBase {

  /** Creates a new Drive. */
DriveSubsystem driveSubsystem;
DoubleSupplier speed;
DoubleSupplier rotation; 
boolean squareInputs;
UltrasonicSubsystem sonic;
  public Drive(DriveSubsystem m_driveSubsystem, DoubleSupplier speed, DoubleSupplier rotation, boolean squareInputs, UltrasonicSubsystem sonic) {
    driveSubsystem = m_driveSubsystem;
    addRequirements(driveSubsystem);
    this.speed = speed;
    this.rotation = rotation;
    this.squareInputs = squareInputs;
    this.sonic = sonic;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    driveSubsystem.drive(speed.getAsDouble(), rotation.getAsDouble(), squareInputs, false);
    
   System.out.println(sonic.getDistance());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveSubsystem.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
