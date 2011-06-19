import java.awt.Point;

//static objects, have a center and width, and never move
public class Square 
{
	private double x;
	private double y;
	private double width;
	//vertices

	public Square(Double width, Double initialX,Double initialY)
	{
		this.width=width;
		this.x=initialX;
		this.y=initialY;
	}
	
	public double getX()
	{
		return x;
	}
	public double getY()
	{
		return y;
	}
	public double getWidth()
	{
		return width;
	}
	public void testThis()
	{
		System.out.print("x: "+x+", y: "+ y);
	}

}
