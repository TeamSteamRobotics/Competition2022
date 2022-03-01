// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.BallTrackingSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.commands.Shoot;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.VisionSubsystem;

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
  VisionSubsystem vision;

  public SequentialAuto(ShooterSubsystem shooter, DriveSubsystem drive, IntakeSubsystem intake, HopperSubsystem hopper, BallTrackingSubsystem tracker, VisionSubsystem vision) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.shooter = shooter;
    this.drive = drive;
    this.intake = intake;
    this.hopper = hopper;
    this.tracker = tracker;
    this.vision = vision;
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new Shoot(shooter, () -> 25000, hopper, tracker),
      //need to move back a little from the wall to get turning room?
      new VisionTurn(drive, vision, false),
      //run intake and drive towards ball until the ball is in the robot (sets off a sensor)
      new ParallelDeadlineGroup(
        new Drive(drive, () -> -.5, () -> 0, false).withInterrupt(() ->(tracker.isAtHopper() || tracker.isAtIntake() || tracker.isAtKicker())), 
        new Intake(intake, hopper, tracker)),

      new GyroTurn(drive, 15),

      //either visionturn and shoot from here or go up to goal

      new VisionTurn(drive, vision, true)

      );
  }
}
