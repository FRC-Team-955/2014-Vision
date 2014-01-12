/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/*  Short Hand Names
    chn = channel
    enc = encoder
    bt = button
    ls = limit switch
*/
/**
 * @author Everyone 
 All of the configurations.
 */
public class Config 
{
	
	// Relays
	public static final int chnRelayLights = 1;
    
	// Vision 
	public static final double camViewVert = 41.2;
	public static final double targetHorizWidth = 23.5;
	public static final double targetHorizHeight = 4;
	public static final double targetVertWidth = 4.0;
	public static final double targetVertHeight = 32;
	public static final double resHalfY = 240;
	
    // Joystick 
	public static final int btVision = 1;
    public static final int buttonsOnJoystick = 13;
    public static final int chnJoystick = 1;   
	
    // Driverstation line 1-6 available
    public static final int stVisionDistance = 1;
	public static final int stVisionHotTarget = 2;
	public static final int stVisionStatus = 3;
}
