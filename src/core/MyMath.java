/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package core;


/**
 *
 * @author Merfoo
 */
public class MyMath
{
    public static double cos(double angle)
    {
        return Math.cos(angle * (Math.PI / 180));
    }

    public static double tan(double angle)
    {
        return Math.tan(angle * (Math.PI / 180));
    }
}
