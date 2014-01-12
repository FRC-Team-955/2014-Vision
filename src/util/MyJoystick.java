package util;
import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author Merfoo
 */
public class MyJoystick extends Joystick
{
    private boolean[] buttonLast = null;
    private boolean[] buttonSwitch = null;
    private boolean[] buttonPressed = null;
    private final double dpadTolerance = 0.2;
    private final int chanVert = 6;
    private final int chanHorz = 5;
    private final int amountOfButtons;
    
    public MyJoystick(int portOne, int buttonsOnJoy)
    {
        super(portOne);
        amountOfButtons = buttonsOnJoy;
        buttonLast = new boolean[amountOfButtons];
        buttonSwitch = new boolean[amountOfButtons];
        buttonPressed = new boolean[amountOfButtons];
        
        for(int index = 0; index < amountOfButtons; index++)
            buttonLast[index] = buttonSwitch[index] = buttonPressed[index] = false;     
    }
    
    /**
     * Sets the button
     * @param chan
     * @param newVal 
     */
    public void setButton(int chan, boolean newVal)
    {
        buttonPressed[chan-1] = newVal;
    }
    
    /**
     * Gets the pushed button
     * @param chan
     * @return 
     */
    public boolean getButton(int chan)
    {
        return buttonPressed[chan-1];
    }
    
    /**
     * Sets the switch
     * @param chan
     * @return 
     */
    public boolean getSwitch(int chan)
    {
        return buttonSwitch[chan-1];
    }
    
    /**
     * Flips the switch
     * @param chan 
     */
    public void flipSwitch(int chan)
    {
        setSwitch(chan, !getSwitch(chan));
    }
    
    /**
     * Sets the switch
     * @param chan
     * @param newVal 
     */
    public void setSwitch(int chan, boolean newVal)
    {
        buttonSwitch[chan-1] = newVal;
    }
    
    /**
     * Updates the buttons
     */
    public void updateButtons()
    {
		for(int index = 1; index <= amountOfButtons; index++)
		{
			buttonPressed[index-1] = (!buttonLast[index-1] && getRawButton(index));
			buttonLast[index-1] = getRawButton(index);
		}
    }
    
    /**
     * Gets the value of the dPad up
     * @return 
     */
    public boolean getDpadUp()
    {
        return (getRawAxis(chanVert) > dpadTolerance);
    }
     
    /**
     * Gets the value of the dPad right
     * @return 
     */
    public boolean getDpadRight()
    {
        return (getRawAxis(chanHorz) > dpadTolerance);
    }
    
    /**
     * Gets the value of the dPad down
     * @return 
     */
    public boolean getDpadDown()
    {
        return (getRawAxis(chanVert) < -dpadTolerance);
    }
        
    /**
     * Gets the value of the dPad left
     * @return 
     */
    public boolean getDpadLeft()
    {
        return (getRawAxis(chanHorz) < -dpadTolerance);
    }
}