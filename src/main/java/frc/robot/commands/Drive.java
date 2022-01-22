// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class Drive extends CommandBase {
  /** Creates a new Drive. */
  DriveSubsystem driveSubsystem;
  DoubleSupplier speed;
  DoubleSupplier turn;

  public Drive(DriveSubsystem m_driveSubsystem, DoubleSupplier m_speed, DoubleSupplier m_turn) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_driveSubsystem);
    speed = m_speed;
    turn = m_turn;
    driveSubsystem = m_driveSubsystem;
  }


  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    driveSubsystem.drive(speed.getAsDouble(), turn.getAsDouble(), true);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
