// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ShootForReal extends ParallelCommandGroup {
  /** Creates a new ShootForReal. */

  public ShootForReal(ShooterSubsystem shooterSubsystem, DoubleSupplier shootSpeed, HopperSubsystem hopper, double beltSpeed) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    /*addCommands(new Shoot(shooterSubsystem, shootSpeed), 
                new ConditionalCommand(
                    new ParallelCommandGroup(
                        new MoveBelts(hopper)/*, new SpinMiddleWheel(hopper, beltSpeed)),
                    new WaitCommand(0), 
                    () -> shooterSubsystem.isAtSpeed()));*/
      //addCommands(new Shoot(shooterSubsystem, shootSpeed), new ConditionalCommand(new MoveBelts(hopper), new WaitCommand(0), () -> shooterSubsystem.isAtSpeed()));
  }

}
