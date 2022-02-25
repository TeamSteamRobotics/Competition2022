// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Shoot extends CommandBase {
  /** Creates a new Shoot. */

  /*test*/
  ShooterSubsystem shooter; 
  DoubleSupplier m_speed;
  HopperSubsystem hopper;
  public Shoot(ShooterSubsystem shooter, DoubleSupplier speed, HopperSubsystem hopper) {
    this.shooter = shooter; 
    this.m_speed = speed;
    this.hopper = hopper;
    addRequirements(shooter); 

  }
  
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  @Override
  public void execute() {

    shooter.shoot(m_speed.getAsDouble());
    if(shooter.isAtSpeed()){
      hopper.moveBeltsForward();
      hopper.spinKickerWheel(0.6);
    }
    else{
      hopper.stopKickerWheel();
      hopper.stopBelt();
    } 
  }
  

  @Override
  public void end(boolean interrupted) {
    shooter.stop(); 
    shooter.stopBackspin();
    hopper.stopBelt();
    hopper.stopKickerWheel();
    shooter.resetIntegral();
  }

  @Override
  public boolean isFinished() {
    return false; 
  }
}