// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BallTrackingSubsystem;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

public class Intake extends CommandBase {

  /** Creates a new Hopper. */

  IntakeSubsystem intake;
  HopperSubsystem hopper;

  public Intake(IntakeSubsystem intake, HopperSubsystem hopper) {
    this.intake = intake;
    this.hopper = hopper;
    addRequirements(intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    intake.intake(); 
    hopper.moveBeltsForward();
    //System.out.println("intakine");
    /*
    if(tracker.isAtMiddle()){
      if(tracker.isAtHopper()){
        intake.stop();
        hopper.stopBelt();
        hopper.stopMiddleWheel();
      } else {
        intake.intake();
      }
    } else {
      intake.intake();
      while(!tracker.isAtMiddle()){
        hopper.moveBeltsForward();}

    }
    */

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intake.stop();
    hopper.stopBelt();
   // hopper.stopMiddleWheel();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
