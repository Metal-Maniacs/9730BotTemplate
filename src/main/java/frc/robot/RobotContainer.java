
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.pathplanner.lib.auto.NamedCommands;
import com.pathplanner.lib.commands.PathPlannerAuto;

import edu.wpi.first.math.MathUtil;
/*import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;*/
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import frc.robot.subsystems.Climb;

import edu.wpi.first.wpilibj2.command.Command;
//import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
//import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
//import java.util.List;
import edu.wpi.first.wpilibj2.command.button.Trigger;
/*import edu.wpi.first.wpilibj.PS4Controller.Button;
import frc.robot.Constants.AutoConstants;
import frc.robot.Constants.DriveConstants;*/
import frc.robot.Constants.OIConstants;
//import frc.robot.commands.LeftAuto;
import frc.robot.subsystems.DriveSubsystem;

/*
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
@SuppressWarnings("unused")
public class RobotContainer {
 
  // The robot's subsystems
  final DriveSubsystem m_robotDrive = new DriveSubsystem();

  private double climbmult = 1;

  // The driver's controller
  CommandXboxController m_driverController = new CommandXboxController(OIConstants.kDriverControllerPort);
  // The subsystem controller
  CommandXboxController m_subsystemController = new CommandXboxController(OIConstants.kSubsystemControllerPort);

  // Controller Buttons
  Trigger xButton = m_driverController.x();
 
  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {

    // Configure the button bindings
    configureButtonBindings();

    // Configure default commands

        m_robotDrive.setDefaultCommand(
            // The left stick controls translation of the robot.
            // Turning is controlled by the X axis of the right stick.
            new RunCommand(
                () -> m_robotDrive.drive(
                    MathUtil.applyDeadband(m_driverController.getLeftY(), OIConstants.kDriveDeadband),
                    -MathUtil.applyDeadband(m_driverController.getLeftX(), OIConstants.kDriveDeadband),
                    MathUtil.applyDeadband(m_driverController.getRightX(), OIConstants.kDriveDeadband),
                    true,
                    1),
                m_robotDrive));

        /*
        SendableChooser<String> autoChooser = new SendableChooser<>();
        autoChooser.setDefaultOption("Default Auto", "Default");
        autoChooser.addOption("Option A", "OptionA");
        autoChooser.addOption("Option B", "OptionB");
        SmartDashboard.putData("Auto Selector", autoChooser);
        */
    }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link edu.wpi.first.wpilibj.GenericHID} or one of its
   * subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then calling
   * passing it to a
   * {@link JoystickButton}.
   */
  private void configureButtonBindings() {    

    //DRIVER CONTROLLERS
    m_driverController.x().whileTrue(
        new RunCommand(
            () -> m_robotDrive.drive(
                MathUtil.applyDeadband(m_driverController.getLeftY(), OIConstants.kDriveDeadband),
                -MathUtil.applyDeadband(m_driverController.getLeftX(), OIConstants.kDriveDeadband),
                MathUtil.applyDeadband(m_driverController.getRightX(), OIConstants.kDriveDeadband),
                true,
                0.25),
            m_robotDrive)
    );

    m_driverController.y().whileTrue(
        new RunCommand(
            () -> m_robotDrive.drive(
                MathUtil.applyDeadband(m_driverController.getLeftY(), OIConstants.kDriveDeadband),
                -MathUtil.applyDeadband(m_driverController.getLeftX(), OIConstants.kDriveDeadband),
                MathUtil.applyDeadband(m_driverController.getRightX(), OIConstants.kDriveDeadband),
                true,
                0.5),
            m_robotDrive)
    );

      m_driverController.b().whileTrue(
        new RunCommand(
            () -> m_robotDrive.drive(
                MathUtil.applyDeadband(m_driverController.getLeftY(), OIConstants.kDriveDeadband),
                -MathUtil.applyDeadband(m_driverController.getLeftX(), OIConstants.kDriveDeadband),
                MathUtil.applyDeadband(m_driverController.getRightX(), OIConstants.kDriveDeadband),
                true,
                0.75),
            m_robotDrive)
    );
    /* 

    m_driverController.a().whileTrue(
        new RunCommand(
            () -> m_robotDrive.drive(
                -MathUtil.applyDeadband(m_driverController.getLeftY(), OIConstants.kDriveDeadband),
                -MathUtil.applyDeadband(m_driverController.getLeftX(), OIConstants.kDriveDeadband),
                -MathUtil.applyDeadband(m_driverController.getRightX(), OIConstants.kDriveDeadband),
                true,
                0.1),
            m_robotDrive)
    );
*/
    m_driverController.a().whileTrue(
        new StartEndCommand(
            () -> m_robotDrive.setX(), 
            () -> m_robotDrive.setX(), 
            m_robotDrive)
    );

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {

    // Below, are the lines that trigger the auto we want. They're all off because we have no autos

    //return new LeftAuto(m_robotDrive, 15);
    //return new RightAuto(m_robotDrive, 15);
    //return new AutoDriveForward(m_robotDrive, 15);
    //return new MiddleAuto(m_robotDrive, 15);
   return null;
  }
}
