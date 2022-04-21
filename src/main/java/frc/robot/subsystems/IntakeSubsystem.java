// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.IntakeSubsystemConstants;
import frc.robot.Constants.MotorIDConstants;
import frc.robot.commands.Intake;

public class IntakeSubsystem extends SubsystemBase {

  //Creates the solenoids that will pneumatically actuate our pistons to manage our kicker bar and our intake
  Solenoid intakeSolenoid = new Solenoid(10, PneumaticsModuleType.CTREPCM, Constants.IntakeSubsystemConstants.intakeSolenoidChannel);
  Solenoid kickerBarSolenoid = new Solenoid(10, PneumaticsModuleType.CTREPCM, Constants.IntakeSubsystemConstants.kickerBarSolenoidChannel);
  //Creates the motor that will suck in the balls
  WPI_VictorSPX intakeMotor = new WPI_VictorSPX(MotorIDConstants.intakeMotorID);

  /** Creates a new IntakeSubsystem. */
  public IntakeSubsystem() {}

  /** spins the intake to suck in a ball */
  public void intake(){
    intakeMotor.set(IntakeSubsystemConstants.intakeMotorSpeed);
  }
/** sets the speed of the intake motor to zero */
  public void stop(){
    intakeMotor.set(0);
  }
/** spins the intake in the oppose direction to eject an intaked ball */
  public void reverseIntake(){
    intakeMotor.set(-IntakeSubsystemConstants.intakeMotorSpeed);
  }
  /** pneumatically actuates the pistons to deploy the intake */
  public void deployIntake(){
    intakeSolenoid.set(true);
  }
  /** pneumatically actuates the pistons to deploy the kickerbar */
  public void deployKicker(){
    kickerBarSolenoid.set(true);
  }
  /** pneumatically actuates the pistones to retract the intake */
  public void undeployIntake(){
    intakeSolenoid.set(false); 
  }
  /**pneumatically actuates the pistons to retract the kickerbar */
  public void undeployKicker(){
    kickerBarSolenoid.set(false);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
