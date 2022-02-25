// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.fasterxml.jackson.databind.ser.std.StdArraySerializers.BooleanArraySerializer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BallTrackingSubsystem;

import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.IntakeSubsystem;


public class Intake extends CommandBase {

  /** Creates a new Hopper. */

  IntakeSubsystem intake;
  HopperSubsystem hopper;
  BallTrackingSubsystem ballTrackingSubsystem;
  int counter = 0;
  

  public Intake(IntakeSubsystem intake, HopperSubsystem hopper, BallTrackingSubsystem ballTrackingSubsystem) {
    this.intake = intake;
    this.hopper = hopper;
    this.ballTrackingSubsystem = ballTrackingSubsystem;
    addRequirements(intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
    //counter = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    /*if(ballTrackingSubsystem.isAtHopper() && ballTrackingSubsystem.isAtKicker()){
      counter++;
    }*/

    
   // System.out.println(ballTrackingSubsystem.isAtKicker() + " Kicker Sensor");
    System.out.println(ballTrackingSubsystem.isAtHopper() + " Hopper Sensor"); 
   // System.out.println(ballTrackingSubsystem.isAtIntake() + " Intake Sensor");
    
    if (!ballTrackingSubsystem.isAtKicker()) {
      hopper.moveBeltsForward();
      System.out.println("Running if Statement");
      hopper.spinKickerWheel(0.15);
      intake.intake();
    } 
      else if (!ballTrackingSubsystem.isHopperFull() /*&& counter<1*/) {
        hopper.moveBeltsForward();
        hopper.stopKickerWheel();
        intake.intake();
        System.out.println("Running If-else Statement");
      }
    else {
      hopper.stopKickerWheel();
      hopper.stopBelt();
      intake.stop();
      intake.undeployIntake();
    } 
}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intake.stop();
    hopper.stopBelt();
    hopper.stopKickerWheel();
   // hopper.stopMiddleWheel();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
