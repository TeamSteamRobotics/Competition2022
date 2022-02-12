// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MotorIDConstants;

public class HopperSubsystem extends SubsystemBase {
  /** Creates a new HopperSubsystem. */

  WPI_VictorSPX topHopperMotor = new WPI_VictorSPX(MotorIDConstants.topHopperMotorID);
  WPI_VictorSPX bottomHopperMotor = new WPI_VictorSPX(MotorIDConstants.bottomHopperMotorID);
  WPI_VictorSPX kickerWheel = new WPI_VictorSPX(MotorIDConstants.kickerMotorID);

  public HopperSubsystem() {
    bottomHopperMotor.follow(topHopperMotor);
    //bottomHopperMotor.set(ControlMode.Follower, MotorIDConstants.topHopperMotorID);
    bottomHopperMotor.setInverted(InvertType.OpposeMaster);
    

  }

  public void moveBeltsForward(){
  topHopperMotor.set(0.4);
  //bottomHopperMotor.set(-.4);
  System.out.println("hoppers moving kid");
  
  }

  public void moveBeltsToIntake(){
   topHopperMotor.set(-0.5);
   System.out.println(topHopperMotor.getSelectedSensorVelocity());
   //bottomHopperMotor.set(0.5); 
  }

  public void stopBelt(){
    topHopperMotor.set(0);
    //bottomHopperMotor.set(0);
  }

  public void spinKickerWheel(double speed){
    kickerWheel.set(-speed);
  }

  public void stopKickerWheel(){
    kickerWheel.set(0);
  }

  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
