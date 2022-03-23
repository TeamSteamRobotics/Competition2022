// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class Music extends CommandBase {
  /** Creates a new Music. */
  DriveSubsystem drive;
  String song;
  public Music(DriveSubsystem drive, String songPath) {
    this.drive = drive;
    this.song = songPath;
    addRequirements(drive);
  }

  @Override
  public void initialize() {
    drive.loadMusic(song);
    drive.playMusic();
  }

  @Override
  public void end(boolean interrupted) {
    drive.stopMusic();
  }
}
