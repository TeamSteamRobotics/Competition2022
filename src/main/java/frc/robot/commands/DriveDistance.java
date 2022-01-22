// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.DriveSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class DriveDistance extends PIDCommand {
  /** Creates a new DriveDistance. */
  
  double kP;
  double kI;
  double kD;

  public DriveDistance(DriveSubsystem drive, double distance) {
    super(
        // The controller that the command will use
        new PIDController(0.420, 0.05, 0.3),
        // This should return the measurement
        drive::getDistance,
        // This should return the setpoint (can also be a constant)
        distance,
        // This uses the output
        output -> {
          drive.drive(output, 0, true);
          // Use the output here
        });
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drive);
    // Configure additional PID options by calling `getController` here.
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }
}
