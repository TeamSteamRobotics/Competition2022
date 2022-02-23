// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.HopperSubsystem;

public class MoveBelts extends CommandBase {
  /** Creates a new MoveBeltToSensor. */
  HopperSubsystem hopper;
  double speed; 
  public MoveBelts(HopperSubsystem hopper, double speed) { 
    addRequirements(hopper);
    this.hopper = hopper;
    this.speed = speed;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    hopper.moveBeltsForward();
    //hopper.spinKickerWheel(speed);
    //System.out.println("asdfghjhfds");
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    hopper.stopBelt();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
