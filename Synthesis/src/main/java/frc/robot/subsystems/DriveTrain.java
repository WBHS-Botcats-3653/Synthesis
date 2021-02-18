/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.*;

import frc.robot.Constants;



/**
 * An example subsystem. You can replace me with your own Subsystem.
 * 
 */
public class DriveTrain<WPI_TalonSRX> extends Subsystem {
  /*

  */

  public double maxDrive = 1; //speed

  //the motor controllers
  private WPI_TalonSRX m_leftDriveM = null;
  private WPI_TalonSRX m_leftDriveS = null;
  private WPI_TalonSRX m_rightDriveM = null;
  private WPI_TalonSRX m_rightDriveS = null;

  //speed controllers
  private SpeedControllerGroup m_leftDrive = null;
  private SpeedControllerGroup m_rightDrive = null;

  //the differential drive makes a separation in the speed of the 2 sides (left and right motors)
  private DifferentialDrive m_driveTrain = null;

  public DriveTrain(){

    //initializing the motor controllers
    m_leftDriveM = new WPI_TalonSRX(Constants.leftDriveM);
    m_leftDriveS = new WPI_TalonSRX(Constants.leftDriveS);
    m_rightDriveM = new WPI_TalonSRX(Constants.rightDriveM);
    m_rightDriveS = new WPI_TalonSRX(Constants.rightDriveS);


    //sets the motors to neutral state.
    m_leftDriveM.setNeutralMode(NeutralMode.Brake);
    m_leftDriveS.setNeutralMode(NeutralMode.Brake);
    m_rightDriveM.setNeutralMode(NeutralMode.Brake);
    m_rightDriveS.setNeutralMode(NeutralMode.Brake);

    // Groups both left controls and both right controllers
    // to have the same values. Inverts one side so it controls
    // properly
    m_leftDrive = new SpeedControllerGroup(m_leftDriveM, m_leftDriveS);
    m_rightDrive = new SpeedControllerGroup(m_rightDriveM, m_rightDriveS);
    m_leftDrive.setInverted(true); //this makes the wheels spin in the correct direction


    //create a differential drive using the 2 otor groups
    m_driveTrain = new DifferentialDrive(m_leftDrive, m_rightDrive);
  }


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
