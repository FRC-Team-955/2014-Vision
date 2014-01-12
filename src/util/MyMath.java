/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 * @author Pedro Custom math functions: round and power.
 */
public class MyMath 
{
	/**
	 * Return the tan of the angle
	 * @param angle in degrees
	 * @return 
	 */
	public static double cos(double angle)
	{
		return Math.cos(angle * (Math.PI / 180));
	}
	
	/**
	 * Returns the tan of the angle 
	 * @param angle in degrees
	 * @return 
	 */
	public static double tan(double angle)
	{
		return Math.tan(angle * (Math.PI / 180));
	}
	
    /**
     * Rounds the double to the hundreth's place, ex. 12.345 -> 12.35.
     * @param value Value to be rounded
     * @return 
     */
    public static double round(double value) 
	{
        return Math.floor(value * 100 + 0.5) / 100;
    }
}
