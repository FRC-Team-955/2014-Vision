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
public class Rectangle
{
    private double width;
    private double height;
    
    public Rectangle()
    {
        
    }
    
    public Rectangle(double newWidth, double newHeight)
    {
        width = newWidth;
        height = newHeight;
    }
    
    public void setWidth(double val)
    {
        width = val;
    }
    
    public void setHeight(double val)
    {
        height = val;
    }
    
    public double getWidth()
    {
        return width;
    }
    
    public double getHeight()
    {
        return height;
    }
}