// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants.VisionTurnConstants;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.VisionSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class VisionTurnToBall extends PIDCommand {
  /** Creates a new VisionTurn. */
  public VisionTurnToBall(DriveSubsystem drive, VisionSubsystem vision) {
    super(
        // The controller that the command will use
        new PIDController(VisionTurnConstants.kP, VisionTurnConstants.kI, VisionTurnConstants.kD),
        // This should return the measurement
        () -> -vision.getBallAngle(),
        // This should return the setpoint (can also be a constant)
        0,
        // This uses the output
        output -> {
          drive.drive(0, output, false);
          // Use the output here
        });
    addRequirements(drive, vision);
    getController().setTolerance(VisionTurnConstants.positionTolerance, VisionTurnConstants.velocityTolerance);
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }
}
