// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

public class VomitAll extends CommandBase {
  /** Creates a new VomitAll. */
  HopperSubsystem hopper;
  IntakeSubsystem intake;
  public VomitAll(HopperSubsystem hopper, IntakeSubsystem intakeSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(hopper, intakeSubsystem);
    this.hopper = hopper;
    this.intake = intakeSubsystem;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    intake.deployIntake();
    hopper.moveBeltsToIntake();
    hopper.spinKickerWheel(-.4);
    intake.reverseIntake();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    hopper.stopBelt();
    hopper.stopKickerWheel();
    intake.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
