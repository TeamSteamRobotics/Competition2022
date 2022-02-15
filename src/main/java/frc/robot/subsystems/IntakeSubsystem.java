// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MotorIDConstants;

public class IntakeSubsystem extends SubsystemBase {
  /** Creates a new IntakeSubsystem. */

  DoubleSolenoid doubleSolenoid = new DoubleSolenoid(10, PneumaticsModuleType.CTREPCM, 0, 5);
  WPI_TalonSRX intakeMotor = new WPI_TalonSRX(MotorIDConstants.intakeMotorID);

  public IntakeSubsystem() {}

  public void intake(){
    intakeMotor.set(0.5);
  }

  public void stop(){
    intakeMotor.set(0);
  }

  public void reverseIntake(){
    intakeMotor.set(-0.5);
  }
  public void deployIntake(){
    doubleSolenoid.set(Value.kForward); //kickerbar

    //doubleSolenoid.set(Value.kReverse);
    //System.out.println("Value forward: " + Value.kForward);
  }
  public void undeployIntake(){
    doubleSolenoid.set(Value.kReverse); //kickerbar
    //System.out.println("Value reverse: " + Value.kReverse);
  }
  
  public void stopSolenoid(){
    doubleSolenoid.set(Value.kOff);
    //System.out.println("Value off: " + Value.kOff);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
