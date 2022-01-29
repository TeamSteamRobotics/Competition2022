// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants.FlywheelConstants;
import frc.robot.subsystems.ShooterSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class SpinUpFlywheel extends PIDCommand {
  /** Creates a new SpinUpFlywheel. */

  public SpinUpFlywheel(ShooterSubsystem shooterSubsystem, double setPoint) {
    super(
        // The controller that the command will use
        new PIDController(FlywheelConstants.kP, FlywheelConstants.kI, FlywheelConstants.kD),
        // This should return the measurement
        () -> shooterSubsystem.getMotorSpeed(),
        // This should return the setpoint (can also be a constant)
        () -> setPoint,
        // This uses the output
        output -> {shooterSubsystem.shoot(output);
          // Use the output here
        });

        //H
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
