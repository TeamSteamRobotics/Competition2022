// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Shoot extends SequentialCommandGroup {
  /** Creates a new Shoot. */

  /*test*/
  ShooterSubsystem shooter; 
  DoubleSupplier m_speed;
  public Shoot(ShooterSubsystem shooter, DoubleSupplier speed) {
    this.shooter = shooter; 
    this.m_speed = speed;
    addRequirements(shooter); 

  }

  @Override
  public void execute() {
    System.out.println("Requested Speed: " + m_speed.getAsDouble());
    shooter.shoot(m_speed.getAsDouble());
  }

  @Override
  public void end(boolean interrupted) {
    shooter.stop(); 
  }

  @Override
  public boolean isFinished() {
    return false; 
  }
}