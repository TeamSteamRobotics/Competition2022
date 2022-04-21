// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import java.beans.Encoder;
import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.FlywheelConstants;
import frc.robot.Constants.MotorIDConstants;

public class ShooterSubsystem extends SubsystemBase {

  //Create the shooter motors. Victors always follow Talons
  WPI_VictorSPX leftFlywheelMotor = new WPI_VictorSPX(MotorIDConstants.leftFlywheelMotorID); //follower
  WPI_TalonSRX rightFlywheelMotor = new WPI_TalonSRX(MotorIDConstants.rightFlywheelMotorID); //master
  //Create the motor to handle backspin
  WPI_TalonSRX backspinMotor = new WPI_TalonSRX(MotorIDConstants.backspinMotorID);
  /** Creates a new ShooterSubsystem. */
  public ShooterSubsystem() {
    rightFlywheelMotor.setNeutralMode(NeutralMode.Coast); //when the motor does not receive any input, have it naturally come to a stop
    rightFlywheelMotor.setSensorPhase(true); //ensures that our perception of forward/backward agrees with motor directionality
    rightFlywheelMotor.selectProfileSlot(0, 0); //the PID slot of the rightFlywheelMotor
    leftFlywheelMotor.follow(rightFlywheelMotor); //creates the master(right) - follower(left) system
    leftFlywheelMotor.setInverted(InvertType.OpposeMaster); //since motors are oriented oppositely, we want them spinning in opposite directions
    
    backspinMotor.setNeutralMode(NeutralMode.Coast); //when the motor does not receive any input, have it naturally come to a stop
    backspinMotor.setSensorPhase(false); //invert the sensor phase so that motor directionality agrees with our perception of direction
    backspinMotor.selectProfileSlot(0, 0); //the PID slot of the leftFlywheelMotor
    backspinMotor.config_kP(0, 0.05); //kP - proportional error adjustment
    backspinMotor.config_kI(0, .00001); //kI - integral keeps track of error built up over time and counters that build up especially near our setpoint
    backspinMotor.config_kD(0, .005); //kD - adjusts the rate of change of the error 
    backspinMotor.config_kF(0, .0229); //kF - out initial guess (in terms of voltage) to get near the setpoint and then PID will make us get to the setpoint from there

    rightFlywheelMotor.config_kP(0, .21); //kP - proportional error adjustment
    rightFlywheelMotor.config_kI(0, .0002); //kI - integral keeps track of error built up over time and counters that build up especially near our setpoint
    rightFlywheelMotor.config_kD(0, .001); //kD - adjusts the rate of change of the error 
    rightFlywheelMotor.config_kF(0, .017); //kF - out initial guess (in terms of voltage) to get near the setpoint and then PID will make us get to the setpoint from there
    rightFlywheelMotor.config_IntegralZone(0, 200); //ask Anand idk man
  }

  /**
   * Shoots a ball by supplying an ideal flywheel speed and an ideal backspin speed to make it in the hoop
   * @param speed the speed of the flywheel needed to comfortably land in the goal
   */
  public void shoot(double speed){
    rightFlywheelMotor.set(ControlMode.Velocity, speed); 
    backspinMotor.set(ControlMode.Velocity, 10000);
    //System.out.println(backspinMotor.getSelectedSensorVelocity());
    //System.out.println(rightFlywheelMotor.getSelectedSensorVelocity());
  }
/**
 * STUs = Stupid Talon Units (Cycles Per Revolution - 4096 Cycles/Rev) 
 * <p>
 * fetches the speed of the flywheel motor
 * @return speed of the flywheel motor in Cycles Per Revolution
 */
  public double getSTUs() {
    return rightFlywheelMotor.getSelectedSensorVelocity();
  }

  /** resets the accumulated error over time */
  public void resetIntegral(){
    rightFlywheelMotor.setIntegralAccumulator(0);
    //rightFlywheelMotor.setIntegralAccumulator(1000000);
    //rightFlywheelMotor.config_kI(slotIdx, value, timeoutMs)

  }
  
/** gets the current velocity error between output and the setpoint 
 * <p>
 * error = setpoint - output
 */
  public double getLoopError(){
    return rightFlywheelMotor.getClosedLoopError();
  }

 /**
  * gets the rate of change of the error which is velocity which is acceleration and puts a tolerance to that for both the backspin and the flywheel
  * @return if the acceleration is within tolerance
  */ 
  public boolean getAccelerationError(){
    return (Math.abs(backspinMotor.getErrorDerivative()) < 20) && Math.abs(rightFlywheelMotor.getErrorDerivative()) < 20;    
  }
  /**
   * Checks to see if our desired speed is within the determined tolerance
   * @return if the flywheel is at the right speed to shoot
   */
  public boolean isAtSpeed() {
    if(this.getSTUs() <1000) {
      return false;
    } else {
      return (Math.abs(backspinMotor.getClosedLoopError()) < Constants.FlywheelConstants.velocityTolerance) && (Math.abs(rightFlywheelMotor.getClosedLoopError()) < Constants.FlywheelConstants.velocityTolerance);}
  }

  /**
   * Converts the velcoity reading to revolutions per minute
   * @return velocity of flywheel in RPM
   */
  public double getRPM() {
    return (this.rightFlywheelMotor.getSelectedSensorVelocity() / 4096) * 10 * 60;
  }
  /**
   * sets the flywheel motors and backspin motor to zero
   */
  public void stop(){
    rightFlywheelMotor.set(0);
    backspinMotor.set(0);
  }
  /**
   * sets the backspin motor to zero percent output
   */
  public void stopBackspin(){
    backspinMotor.set(0);
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
