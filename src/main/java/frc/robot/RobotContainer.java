// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.DeployIntake;
import frc.robot.commands.Drive;
import frc.robot.commands.DriveToBall;
import frc.robot.commands.Intake;
import frc.robot.commands.MoveBelts;
import frc.robot.commands.RetractIntake;
import frc.robot.commands.Shoot;
import frc.robot.commands.SmartDashboardOutput;
import frc.robot.commands.SpinKickerWheel;
import frc.robot.commands.TurnToGoal;
import frc.robot.commands.VisionTurnToBall;
import frc.robot.commands.VomitAll;
import frc.robot.commands.VomitHopper;
import frc.robot.commands.VomitIntake;
import frc.robot.subsystems.BallTrackingSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.VisionSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveSubsystem m_driveSubsystem = new DriveSubsystem();
  private final VisionSubsystem m_visionSubsystem = new VisionSubsystem();
  //private final ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem();
  private final ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem(); 
  private final HopperSubsystem m_hopperSubsystem = new HopperSubsystem();
  private final IntakeSubsystem m_intakeSubsystem = new IntakeSubsystem();
  private final BallTrackingSubsystem m_ballTrackingSubsystem = new BallTrackingSubsystem();
  private final ExampleSubsystem m_exampleSubsytem = new ExampleSubsystem();
  private final Joystick stick = new Joystick(0);


  SmartDashboardOutput m_smartDashboardOutput = new SmartDashboardOutput(m_shooterSubsystem, stick, m_driveSubsystem);

  //JoystickButton shootButton = new JoystickButton(stick, 1);
  //JoystickButton shootButton = new JoystickButton(stick, ButtonConstants.shootButton);
  JoystickButton spinUpFlywheelButton = new JoystickButton(stick, 1);
  JoystickButton intakeButton = new JoystickButton(stick, 3);
  //JoystickButton advanceButton = new JoystickButton(stick, ButtonConstants.advanceButton);
 // JoystickButton vomitButton = new JoystickButton(stick, 12);
  //JoystickButton testButton = new JoystickButton(stick, 11);
  JoystickButton moveHopperForwardButton = new JoystickButton(stick, 6);
  JoystickButton undeployIntakeButton = new JoystickButton(stick, 7);
  JoystickButton deployIntakeButton = new JoystickButton(stick, 8); 
  JoystickButton kickerButton = new JoystickButton(stick, 2);
  
  JoystickButton vomitIntakeButton = new JoystickButton(stick, 5);
  JoystickButton vomitHopperButton = new JoystickButton(stick, 4);
  JoystickButton visionTurnButton = new JoystickButton(stick, 12);
  JoystickButton driveToBallButton = new JoystickButton(stick, 11);
 
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    m_driveSubsystem.setDefaultCommand(new Drive(m_driveSubsystem, () -> stick.getY(), () -> stick.getX(), true));
    stick.setThrottleChannel(3);

    
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    intakeButton.whileHeld(new ParallelCommandGroup(new Intake(m_intakeSubsystem), new MoveBelts(m_hopperSubsystem)));
    //intakeButton.whenPressed(new TurnToGoal(m_visionSubsystem, m_driveSubsystem));
    moveHopperForwardButton.whileHeld(new MoveBelts(m_hopperSubsystem));
    deployIntakeButton.toggleWhenPressed(new DeployIntake(m_intakeSubsystem)); 
    undeployIntakeButton.toggleWhenPressed(new RetractIntake(m_intakeSubsystem)); 
    //vomitButton.whileHeld(new VomitHopper(m_hopperSubsystem)); 
    vomitHopperButton.whileHeld(new VomitHopper(m_hopperSubsystem));
    vomitIntakeButton.whileHeld(new VomitAll(m_hopperSubsystem, m_intakeSubsystem));
    kickerButton.whileHeld(new SpinKickerWheel(m_hopperSubsystem, .4));
    visionTurnButton.whileHeld(new VisionTurnToBall(m_driveSubsystem, m_visionSubsystem));
    spinUpFlywheelButton.whileHeld(new Shoot(m_shooterSubsystem, () -> {return 25000;},
                                                                    /*() -> {
                                                                      double val = stick.getThrottle();
                                                                      return (val - 1) * 20000;
                                                                    //  }, */ m_hopperSubsystem ));    
    driveToBallButton.whileHeld(new DriveToBall(m_driveSubsystem, m_visionSubsystem))                                                                                                                           ;                                                             

    //shootButton.whenPressed(new ShootForReal(m_shooterSubsystem, () -> {return 30000;}, m_hopperSubsystem, .5));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}
