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
import frc.robot.Constants.MotorIDConstants;

public class IntakeSubsystem extends SubsystemBase {
  /** Creates a new IntakeSubsystem. */
  Solenoid intakeSolenoid = new Solenoid(10, PneumaticsModuleType.CTREPCM, 0);
  Solenoid kickerBarSolenoid = new Solenoid(10, PneumaticsModuleType.CTREPCM, 1);
  //DoubleSolenoid doubleSolenoid = new DoubleSolenoid(10, PneumaticsModuleType.CTREPCM, 0, 5);
  WPI_VictorSPX intakeMotor = new WPI_VictorSPX(MotorIDConstants.intakeMotorID);

  public IntakeSubsystem() {}

  public void intake(){
    intakeMotor.set(0.5);
    //System.out.println("intakeMotor ::: " + intakeMotor.get());
  }

  public void stop(){
    intakeMotor.set(0);
  }

  public void reverseIntake(){
    intakeMotor.set(-0.5);
  }
  public void deployIntake(){
    intakeSolenoid.set(true); //kickerbar
    kickerBarSolenoid.set(true);

    //doubleSolenoid.set(Value.kReverse);
    //System.out.println("Value forward: " + Value.kForward);
  }
  public void undeployIntake(){
    intakeSolenoid.set(false); //kickerbar
    kickerBarSolenoid.set(false);
  }
  
  public void stopSolenoid(){}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
