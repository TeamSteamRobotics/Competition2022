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

    public final class VisionTurnConstants {
        public static final double kP = .2;
        public static final double kI = 0;
        public static final double kD = .1;
        public static final double velocityTolerance = 100;
        public static final double positionTolerance = 100;
    }

    public final class DriveConstants {
        public static final int leftBackMotorID = 1;
        public static final int leftFrontMotorID = 0;
        public static final int rightBackMotorID = 2;
        public static final int rightFrontMotorID = 3;
        public static final double ticksPerFeet = 2048;
        public static final double motorRotationsPerWheelRotation = 10.75;
        public static final double wheelRotationPerInch = 1/(6*Math.PI);
        public static final double inchesPerFeet = 12;
        //    1/(ticksPerFeet * motorRotationsPerWheelRotation * wheelRotationPerInch * inchesPerFeet)
        public static final double ftPerTick = 6*Math.PI/(2048 * 10.75 *12);
    }

    public final class ShooterConstants {
        public static final short flywheelMotorID = 4;
        
    }

    public final class HopperConstants {
        public static final short topHopperMotorID = 7;
        public static final short bottomHopperMotorID = 6;
        public static final short middleWheelID = 77;
    }

    public final class IntakeConstants {
        public static final short intakeMotorID = 8;
        public static final short intakeMotorOtherID = 9;
    }

    

    public final class ButtonConstants {
        public static final short shootButton = 1;
        public static final short intakeButton = 2;
        public static final short poopyButton = 3;
        //public static final short advanceButton = 4;
        /*Formerly spinUpFlywheelButton was 0; there is no 0 button on the joystick: Fix*/
        //public static final short spinUpFlywheelButton = 1;

    }

    public final class GyroTurnConstants {
        public static final double kP = 0;
        public static final double kI = 0;
        public static final double kD = 0;
    }

    public final class FlywheelConstants {
        public static final double kP = 5;
        public static final double kI = 0;
        public static final double kD = 0;
        public static final double kFfeedForward = 0;
    }
}
