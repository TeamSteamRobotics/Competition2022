// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimbSubsystem;

public class ClimbPosition extends CommandBase {

  ClimbSubsystem climb;
  double climbPosition;
  /** Creates a new ClimbPosition. */
  public ClimbPosition(ClimbSubsystem climb, double climbPosition) {
    this.climb = climb;
    this.climbPosition = climbPosition;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(climb);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    climb.setCoastMode();
    //climb.resetClimbPosition();
  }


  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    climb.climbToPosition(climbPosition);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    climb.setBrakeMode();
    climb.stopClimb();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
   return climb.isAtClimbHeight();
  }
}
