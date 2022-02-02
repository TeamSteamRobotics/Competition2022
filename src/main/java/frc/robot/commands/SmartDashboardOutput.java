// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class SmartDashboardOutput extends CommandBase {
  /** Creates a new SmartDashboardOutput. */
  ShooterSubsystem shooter;
  Joystick stick;
  DriveSubsystem drive;
  public SmartDashboardOutput(ShooterSubsystem shooter, Joystick stick, DriveSubsystem drive) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.shooter = shooter;
    this.stick = stick;
    this.drive = drive;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putNumber("Shooter Speed", -shooter.getRPM());
    SmartDashboard.putNumber("Throttle", (stick.getThrottle() + 1) * 20000); 

    // If the shooter is within +/-100 the value that we want it to be at, be green
    if((shooter.getSTUs() <= (((stick.getThrottle() - 1) * 20000) + 100) && shooter.getSTUs() >= (((stick.getThrottle() - 1) * 20000) - 100)) || 
       (shooter.getSTUs() <= (shooter.getTargetSpeed() + 100) && shooter.getSTUs() >= shooter.getTargetSpeed() - 100)) {
      SmartDashboard.putBoolean("Shooter At Speed", true);
    }
    else {
      SmartDashboard.putBoolean("Shooter At Speed", false);
    }

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
