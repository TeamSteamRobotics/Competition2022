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
  int counter;
  Boolean[] array = new Boolean[2];
  int loopCounter = 0;

  public Intake(IntakeSubsystem intake, HopperSubsystem hopper) {
    this.intake = intake;
    this.hopper = hopper;
    addRequirements(intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    counter = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    intake.intake(); 
    System.out.println("intakine");

    if (!ballTrackingSubsystem.isHopperFull()) {
      hopper.moveBeltsForward();
      hopper.spinKickerWheel(0.1);
      intake.intake();
    } else {
      hopper.stopKickerWheel();
      hopper.stopBelt();
      intake.stop();
    } 
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
  /*
  loopCounter = (loopCounter + 1 ) % 2;
  //consectutive isAtIntake() loop values is stored in array, then compared to each other to see if the value changed
  array[loopCounter] = ballTrackingSubsystem.isAtIntake();
  if(array[0] != array[1]){
    counter++;
  }

    if (ballTrackingSubsystem.isAtIntake()){

    }

if (counter == 2) {
  intake.undeployIntake();
}
else if (counter == 1) {
  intake.intake();
    
  } 
else{

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
