/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package core;

import edu.wpi.first.wpilibj.IterativeRobot;
import util.Config;
import util.MyJoystick;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Main extends IterativeRobot 
{
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
	MyJoystick joy;
	Vision vision;
	
    public void robotInit() 
	{
		System.out.println("Vision Code!");
		joy.setAxisChannel(MyJoystick.AxisType.kX, 3);
        joy.setAxisChannel(MyJoystick.AxisType.kY, 2);
		joy = new MyJoystick(Config.chnJoystick, Config.buttonsOnJoystick);
		vision = new Vision(joy);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() 
	{

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() 
	{
		vision.run();
    }    
}