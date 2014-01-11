/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package core;

import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.image.BinaryImage;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.CriteriaCollection;
import edu.wpi.first.wpilibj.image.NIVision;
import edu.wpi.first.wpilibj.image.NIVisionException;

/**
 *
 * @author Merfoo
 */
public class Vision
{
    private AxisCamera camera;
    private CriteriaCollection cc;
    private final Rectangle horizTarget = new Rectangle(23.5, 4);
    private final Rectangle vertTarget = new Rectangle(32, 4);
    private final double viewAngleVert = 41.2; // Vertical view angle of the camera
    private final double resHalfY = 240; // Amount of pixels in the upper half of the camera
    private boolean foundHotTarget = false;
    
    public Vision()
    {
        camera = AxisCamera.getInstance();
        cc = new CriteriaCollection();
        cc.addCriteria(NIVision.MeasurementType.IMAQ_MT_AREA, 100, 65535, false);
    }
    
    public void update()
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
                Rectangle target = new Rectangle();
                target.setHeight(NIVision.MeasureParticle(image.image, i, false, NIVision.MeasurementType.IMAQ_MT_BOUNDING_RECT_HEIGHT));
                target.setWidth(NIVision.MeasureParticle(image.image, i, false, NIVision.MeasurementType.IMAQ_MT_BOUNDING_RECT_WIDTH));
                double distance = ((vertTarget.getHeight()/ target.getHeight()) * resHalfY) / MyMath.tan(viewAngleVert);
                
                if(target.getWidth() > target.getHeight())
                {
                    distance = ((horizTarget.getHeight()/ target.getHeight()) * resHalfY) / MyMath.tan(viewAngleVert);
                    foundHotTarget = true;
                }

                System.out.println("Image: " + i + ", Distance: " + distance + ", Hot: " + foundHotTarget);
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

    public boolean foundHotTarget()
    {
        return foundHotTarget;
    }
}