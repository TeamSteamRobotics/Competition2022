// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.RaiseClimb;
import frc.robot.commands.BangBangClimb;
import frc.robot.commands.DeployIntake;
import frc.robot.commands.Drive;
import frc.robot.commands.GyroTurn;
import frc.robot.commands.Intake;
import frc.robot.commands.LowerClimb;
import frc.robot.commands.RetractIntake;
import frc.robot.commands.SequentialAuto;
import frc.robot.commands.SequentialAutoJank;
import frc.robot.commands.Shoot;
import frc.robot.commands.SmartDashboardOutput;
import frc.robot.commands.ThreeBallAuto;
import frc.robot.commands.VisionTurn;
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
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
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
  public final IntakeSubsystem m_intakeSubsystem = new IntakeSubsystem();
  private final BallTrackingSubsystem m_ballTrackingSubsystem = new BallTrackingSubsystem();
  private final UltrasonicSubsystem m_sonicSubsystem = new UltrasonicSubsystem();
  public final ClimbSubsystem m_climbSubsystem = new ClimbSubsystem();
  private final SequentialAuto m_sequentialAuto = new SequentialAuto(m_shooterSubsystem, m_driveSubsystem, m_intakeSubsystem, m_hopperSubsystem, m_ballTrackingSubsystem, m_visionSubsystem, m_sonicSubsystem);
  private final SequentialAutoJank m_sequentialAutoJank = new SequentialAutoJank(m_shooterSubsystem, m_driveSubsystem, m_intakeSubsystem, m_hopperSubsystem, m_ballTrackingSubsystem, m_visionSubsystem, m_sonicSubsystem);
  private final ThreeBallAuto m_threeBallAuto = new ThreeBallAuto(m_shooterSubsystem, m_driveSubsystem, m_intakeSubsystem, m_hopperSubsystem, m_ballTrackingSubsystem, m_visionSubsystem, m_sonicSubsystem);
  private final Joystick stick = new Joystick(0);






  SmartDashboardOutput m_smartDashboardOutput = new SmartDashboardOutput(m_shooterSubsystem, stick, m_driveSubsystem, m_ballTrackingSubsystem, m_visionSubsystem, m_sonicSubsystem);
  SendableChooser<Command> m_autoChooser = new SendableChooser<Command>();
 
  
  JoystickButton intakeButton = new JoystickButton(stick, 2);
  JoystickButton bangbang = new JoystickButton(stick, 3);
  //JoystickButton moveHopperForwardButton = new JoystickButton(stick, 6);
  JoystickButton undeployIntakeButton = new JoystickButton(stick, 7);
  JoystickButton deployIntakeButton = new JoystickButton(stick, 9); 
  JoystickButton shootButton = new JoystickButton(stick, 1);
  JoystickButton raiseClimbButton = new JoystickButton(stick, 6);
  JoystickButton lowerClimbButton = new JoystickButton(stick, 4);
  JoystickButton vomitButton = new JoystickButton(stick, 5);
  //JoystickButton climbUpButton = new JoystickButton(stick, 13);
  //JoystickButton climbDownButton = new JoystickButton(stick, 14);
  //JoystickButton climbToHeightButton = new JoystickButton(stick, 3);
 // JoystickButton gyroTurnButton = new JoystickButton(stick, 11);



 
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    //m_driveSubsystem.setDefaultCommand(new GyroTurn(m_driveSubsystem, 0));
    m_driveSubsystem.setDefaultCommand(new Drive(m_driveSubsystem, () -> stick.getY(), () -> stick.getX(), true, m_sonicSubsystem));
    //stick.setThrottleChannel(3);
    m_autoChooser.addOption("Sequential Auto", m_sequentialAuto);
    m_autoChooser.addOption("Sequential Auto Jank", m_sequentialAutoJank);
    m_autoChooser.addOption("Three Ball Auto", m_threeBallAuto);

    SmartDashboard.putData(m_autoChooser);
    
    
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    /*intakeButton.whileHeld(
      new ParallelCommandGroup(new Intake(m_intakeSubsystem), new MoveBelts(m_hopperSubsystem, 0.4))
    );*/
    intakeButton.whileHeld(new Intake(m_intakeSubsystem, m_hopperSubsystem, m_ballTrackingSubsystem));
    // deployIntakeButton.toggleWhenPressed(new DeployIntake(m_intakeSubsystem)); 
    undeployIntakeButton.toggleWhenPressed(new RetractIntake(m_intakeSubsystem)); 
    vomitButton.whileHeld(new VomitAll(m_hopperSubsystem, m_intakeSubsystem));
    raiseClimbButton.whileHeld(new RaiseClimb(m_climbSubsystem));
    lowerClimbButton.whileHeld(new LowerClimb(m_climbSubsystem));
    bangbang.whenPressed(new BangBangClimb(m_climbSubsystem));
    // visionTurnButton.whileHeld(new VisionTurn(m_driveSubsystem, m_visionSubsystem, false));                                                            
    /*allInOneButton.whileHeld(
        new ParallelCommandGroup(
          new Shoot(m_shooterSubsystem, () -> 25000, m_hopperSubsystem),
          new MoveBelts(m_hopperSubsystem, .3) 
          )); */
    shootButton.whileHeld(new Shoot(m_shooterSubsystem, () -> Constants.FlywheelConstants.shooterSpeed, m_hopperSubsystem, m_ballTrackingSubsystem));
                                                            

    //climbUpButton.whileHeld(new ClimbUp(m_climbSubsystem));
    //climbDownButton.whileHeld(new ClimbDown(m_climbSubsystem));
    //climbToHeightButton.whileHeld(new ClimbHeight(m_climbSubsystem, 0));
    // gyroTurnButton.whileHeld(new GyroTurn(m_driveSubsystem, 180));
  }  
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
      /*return m_threeBallAuto;
      return m_sequentialAuto;
      return m_sequentialAutoJank;*/
      return m_autoChooser.getSelected();
  }
}
