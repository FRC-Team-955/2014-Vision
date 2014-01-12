/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package core;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.image.BinaryImage;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.CriteriaCollection;
import edu.wpi.first.wpilibj.image.NIVision;
import edu.wpi.first.wpilibj.image.NIVisionException;
import util.Config;
import util.MyJoystick;
import util.MyMath;
import util.Station;

/**
 *
 * @author Merfoo
 */
public class Vision
{
    private AxisCamera camera;
    private CriteriaCollection cc;
	private Relay lights;
	private MyJoystick joy;
	private boolean foundHotTarget;
    
    public Vision(MyJoystick stick)
    {
		joy = stick;
        camera = AxisCamera.getInstance();
        cc = new CriteriaCollection();
        cc.addCriteria(NIVision.MeasurementType.IMAQ_MT_AREA, 100, 65535, false);
		lights = new Relay(Config.chnRelayLights);
		foundHotTarget = false;
    }
    
    public void run()
    {
		if(joy.getButton(Config.btVision))
			joy.setSwitch(Config.btVision, !joy.getSwitch(Config.btVision));
		
		if(joy.getSwitch(Config.btVision))
		{
			lights.set(Relay.Value.kForward); // Turn on the camera lights
			update();
		}
		
		else
			lights.set(Relay.Value.kOff);	// Turn of the camera lights
    }

	private void update()
	{
		foundHotTarget = false;
		
		try
		{
			/*
				Get camera image, convert to binary keeping only green objects,
				filter out small particles,
			*/

			ColorImage image = camera.getImage();
			BinaryImage thresholdImage = image.thresholdHSV(105, 137, 230, 255, 133, 183);   // Keep only green objects
			BinaryImage filteredImage = thresholdImage.particleFilter(cc);  // Filter our small particles
			final int particleReportLength = filteredImage.getNumberParticles();

			for(int i = 0; i < particleReportLength; i++)
			{
				final double targetHeight = (NIVision.MeasureParticle(filteredImage.image, i, false, NIVision.MeasurementType.IMAQ_MT_BOUNDING_RECT_HEIGHT));
				final double targetWidth = (NIVision.MeasureParticle(filteredImage.image, i, false, NIVision.MeasurementType.IMAQ_MT_BOUNDING_RECT_WIDTH));
				double distance = ((Config.targetVertHeight / targetHeight) * Config.resHalfY) / MyMath.tan(Config.camViewVert);

				if(targetWidth > targetHeight)
				{
					distance = ((Config.targetHorizHeight / targetHeight) * Config.resHalfY) / MyMath.tan(Config.camViewVert);
					foundHotTarget = true;
				}

				Station.print(Config.stVisionDistance, "Distance: " + distance);
				Station.print(Config.stVisionHotTarget, "Found Hot: " + foundHotTarget);
				Station.print(Config.stVisionStatus, "Vision Running: " + joy.getSwitch(Config.btVision));
			}

			filteredImage.free();
			thresholdImage.free();
			image.free();
		}

		catch (NIVisionException ex)
		{
			ex.printStackTrace();
		}

		catch (AxisCameraException ex)
		{
			ex.printStackTrace();
		}
	}
	
	/**
	 * Returns boolean whether vision found a hot target
	 * @return 
	 */
    public boolean foundHotTarget()
    {
        return foundHotTarget;
    }
}