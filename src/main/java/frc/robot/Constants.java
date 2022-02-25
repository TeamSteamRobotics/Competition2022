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
        //public static final short middleWheelID = 77;
        public static final short intakeMotorID = 8;
       // public static final short intakeMotorOtherID = 9;
        public static final short kickerMotorID = 5;
        public static final short backspinMotorID = 9;
    }

    public final class IntakeSubsystemConstants {
        public static final double intakeMotorSpeed = 0.5;
        public static final short intakeSolenoidChannel = 0;
        public static final short kickerBarSolenoidChannel = 1;
    }

    public final class VisionTurnConstants {
        public static final double kP = .008;
        public static final double kI = 0.005;
        public static final double kD = 0.0005;
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

    public final class ButtonConstants {
        /*public static final short shootButton = 1;
        public static final short intakeButton = 2;
        public static final short poopyButton = 3;
        //public static final short advanceButton = 4;
        /*Formerly spinUpFlywheelButton was 0; there is no 0 button on the joystick: Fix*/
        //public static final short spinUpFlywheelButton = 1;
    } 

    public static final class XboxControllerConstants {
        public static final int leftTrigger = 5;
        public static final int rightTrigger = 6;
        public static final int backButton = 7;
        public static final int startButton = 8;
        public static final int buttonX = 3;
        public static final int buttonB = 2;
        public static final int buttonA = 1;
        public static final int buttonY = 4;
        public static final int leftStick = 9;
        public static final int rightStick = 10;
        public static final int leftPOV = 270;
        public static final int rightPOV = 90;
        public static final int upPOV = 0;
        public static final int downPOV = 180;

    }

    public static final class JoystickConstants {
        public static final int trigger = 1;
        public static final int thumbButton = 2;
        public static final int bottomLeftButton = 3;
        public static final int bottomRightButton = 4;
        public static final int topLeftButton = 5;
        public static final int topRightButton = 6;
        public static final int baseButton7 = 7;
        public static final int baseButton8 = 8;
        public static final int baseButton9 = 9;
        public static final int baseButton10 = 10;
        public static final int baseButton11 = 11;
        public static final int baseButton12 = 12;
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
        public static final double tolerance = 100;
    }
}
