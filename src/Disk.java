import java.awt.Point;


//they have a center, radius, and velocity
public class Disk 
{
	//fields
	
	private double x;
	private double y;
	private double initialX;
	private double initialY;
	private double radius;
	private double initialVertVelocity;
	private double horzVelocity;
	private boolean isStatic=false;
	
	//constructors
	public Disk(double radius, double initialX, double initialY, double initialVertVelocity, double horzVelocity)
	{
		
		this.initialX=initialX;
		this.initialY=initialY;
		this.x=initialX;
		this.y=initialY;
		this.radius=radius;
		this.initialVertVelocity=initialVertVelocity;
		this.horzVelocity=horzVelocity;
	}	
	//called every time you want to change the disks location
	public void move(int time)
	{
		if(isStatic==false)
		{
		x=(initialX+horzVelocity*time);
		y=(initialY+(initialVertVelocity*time)+(0.5*Model.g*Math.pow(time, 2)));
		}
		//if it is static you don't move
	}
	
	//accessor methods

	public double getRadius()
	{
		return radius;
	}
	public double getX()
	{
		return x;
	}
	public double getY()
	{
		return y;
	}
	public double getIX()
	{
		return initialX;
	}
	public double getIY()
	{
		return initialY;
	}
	public double gethorzV()
	{
		return horzVelocity;
	}
	public double getIVV()
	{
		return initialVertVelocity;
	}
	//mutator methods
	public void setX(double d)
	{
		x=d;
	}
	public void setY(double d)
	{
		y=d;
	}
	//sets the object static
	public void setStatic()
	{
		isStatic=true;
	}
	public void testThis()
	{
		System.out.print("x: "+x+", y: "+ y);
	}
}
