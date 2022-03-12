// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.PerpetualCommand;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.robot.subsystems.BallTrackingSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.Constants;
import frc.robot.commands.Shoot;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.UltrasonicSubsystem;
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
  UltrasonicSubsystem sonic;
  BooleanSupplier end = () -> (tracker.isAtHopper() || tracker.isAtIntake() || tracker.isAtKicker());

  public SequentialAuto(ShooterSubsystem shooter, DriveSubsystem drive, IntakeSubsystem intake, HopperSubsystem hopper, BallTrackingSubsystem tracker, VisionSubsystem vision, UltrasonicSubsystem sonic) {
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
        
      ).withInterrupt(()->vision.isThereABall()),*/

      

      /*

      new ParallelRaceGroup(
        new WaitCommand(2),
        new Intake(intake, hopper, tracker),
        new VisionTurn(drive, vision, false)).withInterrupt(() -> tracker.isAtIntake()),

        new Drive(drive,() -> -0.2, () -> 0, false, sonic).withInterrupt(() -> sonic.getDistance() <100),


        new ParallelRaceGroup(
          new WaitCommand(3),
          //new Intake(intake, hopper, tracker),
          new GyroTurn(drive, 0).withInterrupt(() -> sonic.getDistance() <50)),

        
        */
        

        






        new ParallelRaceGroup(
          new Intake(intake, hopper, tracker),
          new VisionTurn(drive, vision, false)).withInterrupt(() -> tracker.isAtIntake()),
  
        new GyroTurn(drive, 0).withInterrupt(() -> sonic.getDistance() <50),





        new ParallelRaceGroup(
          new WaitCommand(4),
          //new Intake(intake, hopper, tracker),
          new Shoot(shooter, () -> 25000, hopper, tracker))


      //new Drive(drive,() -> -0.2, () -> 0, false, sonic).withInterrupt(() -> sonic.getDistance() <100),
      /*
      new ParallelRaceGroup(
        new VisionTurn(drive, vision, true),
        new WaitCommand(3)
      ),*//*
      new ParallelRaceGroup(
        new WaitCommand(3),
        new GyroTurn(drive, 0)
        //new Drive(drive, () -> -0.3, () -> 0, false, sonic)
      )*/
      //new Shoot(shooter, () -> 25000, hopper, tracker)
);
      
  }
}
