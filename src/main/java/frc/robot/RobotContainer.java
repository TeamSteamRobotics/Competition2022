// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.RaiseClimb;
import frc.robot.commands.BangBangClimb;
import frc.robot.commands.Drive;
import frc.robot.commands.Intake;
import frc.robot.commands.LowerClimb;
import frc.robot.commands.RetractIntake;
import frc.robot.commands.SequentialAuto;
import frc.robot.commands.SequentialAutoJank;
import frc.robot.commands.Shoot;
import frc.robot.commands.SmartDashboardOutput;
import frc.robot.commands.TaxiAuto;
import frc.robot.commands.ThreeBallAuto;
import frc.robot.commands.VisionlessAuto;
import frc.robot.commands.VomitAll;
import frc.robot.subsystems.BallTrackingSubsystem;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.UltrasonicSubsystem;
import frc.robot.subsystems.VisionSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer 
{
  // The robot's subsystems and commands are defined here...
  private final DriveSubsystem m_driveSubsystem = new DriveSubsystem();
  //private final VisionSubsystem m_visionSubsystem = new VisionSubsystem();
  private final ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem(); 
  private final HopperSubsystem m_hopperSubsystem = new HopperSubsystem();
  public final IntakeSubsystem m_intakeSubsystem = new IntakeSubsystem();
  private final BallTrackingSubsystem m_ballTrackingSubsystem = new BallTrackingSubsystem();
  private final UltrasonicSubsystem m_sonicSubsystem = new UltrasonicSubsystem();
  public final ClimbSubsystem m_climbSubsystem = new ClimbSubsystem();
  //private final SequentialAuto m_sequentialAuto = new SequentialAuto(m_shooterSubsystem, m_driveSubsystem, m_intakeSubsystem, m_hopperSubsystem, m_ballTrackingSubsystem, m_visionSubsystem, m_sonicSubsystem);
  //private final SequentialAutoJank m_sequentialAutoJank = new SequentialAutoJank(m_shooterSubsystem, m_driveSubsystem, m_intakeSubsystem, m_hopperSubsystem, m_ballTrackingSubsystem, m_visionSubsystem, m_sonicSubsystem);
  //private final ThreeBallAuto m_threeBallAuto = new ThreeBallAuto(m_shooterSubsystem, m_driveSubsystem, m_intakeSubsystem, m_hopperSubsystem, m_ballTrackingSubsystem, m_visionSubsystem, m_sonicSubsystem);
  //private final TaxiAuto m_taxiAuto = new TaxiAuto(m_shooterSubsystem, m_driveSubsystem, m_intakeSubsystem, m_hopperSubsystem, m_ballTrackingSubsystem, m_visionSubsystem, m_sonicSubsystem);
  private final Joystick stick = new Joystick(0);
  //private final VisionlessAuto m_visionlessAuto = new VisionlessAuto(m_shooterSubsystem, m_driveSubsystem, m_intakeSubsystem, m_hopperSubsystem, m_ballTrackingSubsystem, m_visionSubsystem, m_sonicSubsystem);






  //SmartDashboardOutput m_smartDashboardOutput = new SmartDashboardOutput(m_shooterSubsystem, stick, m_driveSubsystem, m_ballTrackingSubsystem, m_visionSubsystem, m_sonicSubsystem);
  SendableChooser<Command> m_autoChooser = new SendableChooser<Command>();
 
  
  JoystickButton intakeButton = new JoystickButton(stick, 2);
  JoystickButton bangbang = new JoystickButton(stick, 3);
  //JoystickButton moveHopperForwardButton = new JoystickButton(stick, 6);
  JoystickButton retractIntakeButton = new JoystickButton(stick, 5);
  //JoystickButton deployIntakeButton = new JoystickButton(stick, 9); 
  JoystickButton shootButton = new JoystickButton(stick, 1);
  JoystickButton raiseClimbButton = new JoystickButton(stick, 12);
  JoystickButton lowerClimbButton = new JoystickButton(stick, 4);
  JoystickButton vomitButton = new JoystickButton(stick, 6);




 
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() 
  {
    // Configure the button bindings
    configureButtonBindings();
    //m_driveSubsystem.setDefaultCommand(new GyroTurn(m_driveSubsystem, 0));
    m_driveSubsystem.setDefaultCommand(new Drive(m_driveSubsystem, () -> stick.getY(), () -> stick.getX(), true));
    

    SmartDashboard.putData(m_autoChooser);
    //SmartDashboard.putNumber("Requested Speed", shooterSpeed);
    
    
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    intakeButton.whileHeld(new Intake(m_intakeSubsystem, m_hopperSubsystem, m_ballTrackingSubsystem));
    //undeployIntakeButton.toggleWhenPressed(new DeployIntake(m_intakeSubsystem)); 
    retractIntakeButton.toggleWhenPressed(new RetractIntake(m_intakeSubsystem)); 
    vomitButton.whileHeld(new VomitAll(m_hopperSubsystem, m_intakeSubsystem));
    raiseClimbButton.whileHeld(new RaiseClimb(m_climbSubsystem));
    lowerClimbButton.whileHeld(new LowerClimb(m_climbSubsystem));
    bangbang.whenPressed(new BangBangClimb(m_climbSubsystem));
    /*allInOneButton.whileHeld(
        new ParallelCommandGroup(
          new Shoot(m_shooterSubsystem, () -> 25000, m_hopperSubsystem),
          new MoveBelts(m_hopperSubsystem, .3) 
          )); */
   
    shootButton.whileHeld(new Shoot(m_shooterSubsystem, ()-> 20000, m_hopperSubsystem, m_ballTrackingSubsystem)); //19000 -> 19000 and .4 had shots that barely
  }  
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() 
  {
      /*return m_threeBallAuto;
      return m_sequentialAuto;
      return m_sequentialAutoJank;*/
      return m_autoChooser.getSelected();
  }
}
