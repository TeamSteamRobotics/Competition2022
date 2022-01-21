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

  public Shoot(ShooterSubsystem shooter, HopperSubsystem hopper, DoubleSupplier shooterWheelSpeed, DoubleSupplier middleWheelSpeed, /*fix this */ BooleanSupplier isShooterMax) {
    super(
      new SpinShooterWheel(shooter, shooterWheelSpeed.getAsDouble()), 
      new ConditionalCommand(
          new ParallelCommandGroup 
              (
              new SpinMiddleWheel(hopper, middleWheelSpeed.getAsDouble()),
              new MoveBelts(hopper) 
              ), 
          new WaitCommand(1),
          /*fix this */isShooterMax) 
      
    );
  }
    public Shoot(ShooterSubsystem shooter, HopperSubsystem hopper, double shooterWheelSpeed, double middleWheelSpeed, /*fix this */ BooleanSupplier isShooterMax) {
      super(
        new SpinShooterWheel(shooter, shooterWheelSpeed), 
        new ConditionalCommand(
            new ParallelCommandGroup 
                (
                new SpinMiddleWheel(hopper, middleWheelSpeed),
                new MoveBelts(hopper) 
                ), 
            new WaitCommand(1),
            /*fix this */isShooterMax) 
        
      );
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands();
  }
}
