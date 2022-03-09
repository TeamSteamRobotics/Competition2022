// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.BallTrackingSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.UltrasonicSubsystem;
import frc.robot.subsystems.VisionSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ThreeBallAuto extends SequentialCommandGroup {
  /** Creates a new ThreeBallAuto. */
  ShooterSubsystem shooter;
  DriveSubsystem drive;
  IntakeSubsystem intake;
  HopperSubsystem hopper;
  BallTrackingSubsystem tracker;
  VisionSubsystem vision;
  UltrasonicSubsystem sonic;
  BooleanSupplier end = () -> (tracker.isAtHopper() || tracker.isAtIntake() || tracker.isAtKicker());

  public ThreeBallAuto(ShooterSubsystem shooter, DriveSubsystem drive, IntakeSubsystem intake, HopperSubsystem hopper, BallTrackingSubsystem tracker, VisionSubsystem vision, UltrasonicSubsystem sonic) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.shooter = shooter;
    this.drive = drive;
    this.intake = intake;
    this.hopper = hopper;
    this.tracker = tracker;
    this.vision = vision;
    this.sonic = sonic;
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new InstantCommand(drive::resetGyro, drive),

      /*new SequentialCommandGroup(
        new ParallelRaceGroup(
          new WaitCommand(1),
          new Drive(drive, () -> 0, () -> -.2, false, sonic)),
        new ParallelRaceGroup(
          new WaitCommand(2),
          new Drive(drive, () -> 0, () -> .2, false, sonic))
        
      ).withInterrupt(()->vision.isThereABall()),

      
      new ParallelRaceGroup(
        new WaitCommand(2),
        new VisionTurn(drive, vision, false)),

      new WaitCommand(3),

      new Drive(drive, () -> 0, () -> .2, false, sonic).withInterrupt(()->vision.isThereABall()),

      new ParallelRaceGroup(
        new WaitCommand(2),
        new VisionTurn(drive, vision, false)),*/

      new GyroTurn(drive, drive.getAngle()+60)

      //new Drive(drive, () -> -.2, () -> 0, true, sonic).withInterrupt(() -> (sonic.getDistance()<40)),

      //new GyroTurn(drive, 0)
      

    
    /*
      new InstantCommand(drive::resetGyro, drive),
      new ParallelRaceGroup(
        new WaitCommand(3),
        new Shoot(shooter, ()-> Constants.FlywheelConstants.shooterSpeed, hopper, tracker)),
      

      new ParallelRaceGroup(
        //new Drive(drive, () -> .5, () -> 0, false, sonic),
        new VisionTurn(drive, vision, false),
        new Intake(intake, hopper, tracker)
      ).withInterrupt(tracker::isAtHopper),

      new Drive(drive,() -> -0.2, () -> 0, false, sonic).withInterrupt(() -> sonic.getDistance() <40),
      new GyroTurn(drive, 0),
      //new VisionTurn(drive, vision, false).withInterrupt(() -> (sonic.getDistance()<40)),
      //new Drive(drive, () -> -.4, () -> 0, true, sonic).withInterrupt(() -> (sonic.getDistance()<40)),
      new ParallelDeadlineGroup(new WaitCommand(1), 
      new Shoot(shooter, ()-> Constants.FlywheelConstants.shooterSpeed, hopper, tracker))*/
);
      
  }
}