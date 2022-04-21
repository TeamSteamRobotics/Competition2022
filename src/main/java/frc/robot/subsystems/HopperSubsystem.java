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

//Creates the three motors in charge of managing the hopper
  WPI_VictorSPX topHopperMotor = new WPI_VictorSPX(MotorIDConstants.topHopperMotorID);
  WPI_VictorSPX bottomHopperMotor = new WPI_VictorSPX(MotorIDConstants.bottomHopperMotorID);
  WPI_VictorSPX kickerWheel = new WPI_VictorSPX(MotorIDConstants.kickerMotorID);

  /** Creates a new HopperSubsystem. */
  public HopperSubsystem() {
    bottomHopperMotor.follow(topHopperMotor);
    bottomHopperMotor.setInverted(InvertType.OpposeMaster);
  }
  /** moves the belts of the hopper towards the flywheel */
  public void moveBeltsForward(){
  topHopperMotor.set(0.2);  
  }
 /** moves the belts of the hopper towards the intake */
  public void moveBeltsToIntake(){
   topHopperMotor.set(-0.5);
  }
/** stops the moving belts */
  public void stopBelt(){
    topHopperMotor.set(0);
  }

 /** spins the kicker wheel to a variable speed
  * @param speed the speed(percent output) at which to spin the kicker wheel
 */
  public void spinKickerWheel(double speed){
    kickerWheel.set(-speed);
  }
/** stops the kicker wheel from spinning */
  public void stopKickerWheel(){
    kickerWheel.set(0);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
