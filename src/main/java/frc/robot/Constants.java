// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    public final class MotorIDConstants {
        public static final short leftBackMotorID = 1;
        public static final short leftFrontMotorID = 0;
        public static final short rightBackMotorID = 2;
        public static final short rightFrontMotorID = 3;
        public static final short leftFlywheelMotorID = 4;
        public static final short rightFlywheelMotorID = 12;
        public static final short topHopperMotorID = 7;
        public static final short bottomHopperMotorID = 6;
        public static final short intakeMotorID = 8;
        public static final short kickerMotorID = 5;
        public static final short backspinMotorID = 9;
        public static final short rightClimbMotorID = 10;
        public static final short leftClimbMotorID = 11;
    }

    public final class IntakeSubsystemConstants {
        public static final float intakeMotorSpeed = 0.5f;
        public static final short intakeSolenoidChannel = 0;
        public static final short kickerBarSolenoidChannel = 1;
    }

    public final class VisionTurnConstants {
        //jerky but pretty solid PIDit
        public static final double kP = .005;
        public static final double kI = 0;
        public static final double kD = .00;
        public static final double kPBY = .01; //ball yith for kPBY, kIBY, kDBY
        public static final double kIBY = 0;
        public static final double kDBY = 0;
        public static final double velocityTolerance = 0;
        public static final double positionTolerance = 0;
    }

    public final class DriveConstants {
        public static final double ticksPerMotorRotation = 2048;
        public static final double motorRotationsPerWheelRotation = 10.75;
        public static final double wheelRotationPerInch = 1/(6*Math.PI);
        public static final double inchesPerFeet = 12;
        //    1/(ticksPerFeet * motorRotationsPerWheelRotation * wheelRotationPerInch * inchesPerFeet)
        public static final double ftPerTick = 6*Math.PI/(2048 * 10.75 *12);
    }

    public final class ClimbConstants {
        public static final double positionTolerance = 0;
        public static final double velocityTolerance = 0;
        public static final double minimumClimbHeight = 80;
        public static final double maximumClimbHeight = 90;
    }

    public final class GyroTurnConstants {
        public static final double kP = .003;
        public static final double kI = .0;
        public static final double kD = 0.002;
        public static final double velocityTolerance = .5;
        public static final double positionTolerance = 5;
    }

    public final class FlywheelConstants {
        public static final double shooterSpeed = 25000;
        public static final double maxShooterSpeed = 40000;
        public static final double kP = 5;
        public static final double kI = 0;
        public static final double kD = 0;
        public static final double kFfeedForward = 0;
        public static final double tolerance = 100;
    }

}
