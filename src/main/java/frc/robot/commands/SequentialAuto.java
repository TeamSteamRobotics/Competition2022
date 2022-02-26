// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.BallTrackingSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.commands.Shoot;
import frc.robot.subsystems.ShooterSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class SequentialAuto extends SequentialCommandGroup {
  /** Creates a new SequentialAuto. */
  ShooterSubsystem shooter;
  DriveSubsystem drive;
  IntakeSubsystem intake;
  HopperSubsystem hopper;
  BallTrackingSubsystem tracker;

  public SequentialAuto(ShooterSubsystem shooter, DriveSubsystem drive, IntakeSubsystem intake, HopperSubsystem hopper, BallTrackingSubsystem tracker) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.shooter = shooter;
    this.drive = drive;
    this.intake = intake;
    this.hopper = hopper;
    this.tracker = tracker;
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new Shoot(shooter, () -> 25000, hopper, tracker),
      new GyroTurn(drive, 15)

      );
  }
}
