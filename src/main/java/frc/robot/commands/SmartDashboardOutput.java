// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BallTrackingSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.UltrasonicSubsystem;
import frc.robot.subsystems.VisionSubsystem;

public class SmartDashboardOutput extends CommandBase {
  /** Creates a new SmartDashboardOutput. */
  ShooterSubsystem shooter;
  Joystick stick;
  DriveSubsystem drive;
  BallTrackingSubsystem tracker;
  VisionSubsystem vision;
  UltrasonicSubsystem sonic;
  public SmartDashboardOutput(ShooterSubsystem shooter, Joystick stick, DriveSubsystem drive, BallTrackingSubsystem tracker, VisionSubsystem vision, UltrasonicSubsystem sonic) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.shooter = shooter;
    this.tracker = tracker;
    this.sonic = sonic;
    this.stick = stick;
    this.vision = vision;
    this.drive = drive;

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putNumber("Shooter Speed", shooter.getRPM());
    SmartDashboard.putBoolean("Hopper Full", tracker.isHopperFull());
    SmartDashboard.putData("Drivetrain", drive.diffDrive);;
    SmartDashboard.putBoolean("Ball Detected", vision.isThereABall());
    SmartDashboard.putData(drive.gyro);
    SmartDashboard.putNumber("Ultrasonic", sonic.getDistance());
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
