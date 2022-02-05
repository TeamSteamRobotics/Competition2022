// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MotorIDConstants;

public class HopperSubsystem extends SubsystemBase {
  /** Creates a new HopperSubsystem. */

  WPI_TalonSRX topHopperMotor = new WPI_TalonSRX(MotorIDConstants.topHopperMotorID);
  WPI_TalonSRX bottomHopperMotor = new WPI_TalonSRX(MotorIDConstants.bottomHopperMotorID);
  WPI_TalonSRX middleWheel = new WPI_TalonSRX(MotorIDConstants.middleWheelID);

  public HopperSubsystem() {
    //bottomHopperMotor.setInverted(InvertType.OpposeMaster);
    //bottomHopperMotor.set(ControlMode.Follower, 0);

  }

  public void moveBeltsForward(){
  topHopperMotor.set(0.4);
  bottomHopperMotor.set(.4);
  System.out.println("hoppers moving kid");
  }

  public void moveBeltsToIntake(){
   topHopperMotor.set(-0.5);
   bottomHopperMotor.set(-0.5); 
  }

  public void stopBelt(){
    topHopperMotor.set(0);
    bottomHopperMotor.set(0);
  }

  public void spinMiddleWheel(double speed){
    middleWheel.set(speed);
  }

  public void stopMiddleWheel(){
    middleWheel.set(0);
  }

  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
