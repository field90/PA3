import java.awt.Point;
import java.util.LinkedList;
//Michael Field
//6.19.2011
//PA-3

public class Model 
{
	//gravity is a global constant
	final static double g=-9.8;
	static int counter=0;
	static boolean debuggable=false;
	static LinkedList<Disk>DiskList= new LinkedList();
	static LinkedList<Disk>StaticDiskList= new LinkedList();
	static LinkedList<Jewel>JewelList= new LinkedList();
	static LinkedList<Square>SquareList= new LinkedList();
	public static void main(String []args)
	{
		
		Jewel jewel1= new Jewel(5.,33.,185.);
		Jewel jewel2= new Jewel(5.,111.,44.);
		JewelList.add(jewel1);
		JewelList.add(jewel2);
		Square square1= new Square(10.0,140.29,326.1);
		SquareList.add(square1);
		
		Disk disk1 = new Disk(5,0,0,190.6,33.9);
		Disk disk2 = new Disk(5,0,0,80.6,20.9);
		Disk disk3 = new Disk(10,0,0,40.6,15.9);
		Disk disk4 = new Disk(17,0,0,16.6,40.9);
		Disk disk5 = new Disk(5,0,0,190.6,33.9);
		DiskList.add(disk1);
		DiskList.add(disk2);
		DiskList.add(disk3);
		DiskList.add(disk4);
		DiskList.add(disk5);
		
		//here is the game itself
		while(DiskList.isEmpty()==false)
		{
			fling(DiskList.getFirst());
		}
		//You win if you hit all the jewels
		if(JewelList.isEmpty())
		{
			System.out.println("You win!");			
		}
		else
		{
			System.out.println("You lose");
		}
	}
	//need a fling method which launches a disk. 
	public static void fling(Disk disk)
	{
		//controls messages to the user
		boolean hitFloor=false;
		boolean hitDisk=false;
		boolean hitSquare=false;
		boolean hitJewel=false;
		counter++;
		System.out.println("Firing disk #"+counter+"...");
		//through 100 seconds of time (t), the move method is called
		for(int t=0; t<100; t++)
		{
			
			disk.move(t);
			
			
			if (debuggable==true)
			{
				disk.testThis(); System.out.println(", time: "+ t);
			}
			//accounts for the floor
			
			if(t>1&&disk.getY()<=0.0)
			{
				hitFloor=true;
				break;
			}
			
			for(int i=0; i<StaticDiskList.size(); i++)
			{
				if (debuggable==true){
					System.out.println("Disk intersecting a static Disk: "+intersects(disk,StaticDiskList.get(i)));
				}
				
				if(intersects(disk,StaticDiskList.get(i))==true)
				{
					disk.setStatic();
					hitDisk=true;
					
				}
			}
			//in this loop you want to remove the jewel from the list because you have succesfully hit it.
			for(int i=0; i<JewelList.size(); i++)
			{
				
				if (debuggable==true)
				{
					System.out.println("Disk intersecting a jewel: "+intersects(disk,JewelList.get(i)));
				}
				if(intersects(disk,JewelList.get(i))==true)
				{
					disk.setStatic();
					JewelList.remove(i);
					hitJewel=true;
				}
			}
			for(int i=0; i<SquareList.size(); i++)
			{
				
				if (debuggable==true)
				{
					System.out.println("Disk intersecting a square: "+intersects(disk,SquareList.get(i)));
				}
				if(intersects(disk,SquareList.get(i))==true)
				{
					disk.setStatic();
					hitSquare=true;
				}
			}
		
		}
		//remove the disk from the list of active disks at the end of the loop
		//add disk to the static disk list
		DiskList.remove(disk);
		StaticDiskList.add(disk);	
		if(hitFloor==true)
		{
			System.out.println("Disk #"+counter+" hit the floor.");
		}
		if(hitDisk==true)
		{
			System.out.println("Disk #"+counter+" hit a static disk.");
		}
		if(hitSquare==true)
		{
			System.out.println("Disk #"+counter+" hit a square.");
		}
		if(hitJewel==true)
		{
			System.out.println("Disk #"+counter+" hit a Jewel. Nice!");
		}
		
	}
	 public static boolean intersects(Disk disk, Square square)
	 {
		 //distance from cirlce to 
		 double diskDistanceX= Math.abs(disk.getX()-square.getX()-(square.getWidth()/2));
		 //width is the same as height for a square
		 double diskDistanceY= Math.abs(disk.getY()-square.getY()-(square.getWidth()/2));
		 // if the distance from the disk to the square is greater than the squares width/2 + the disks radius
		 
		 if(diskDistanceX > (square.getWidth()/2)+disk.getRadius())	 
		 {
			 return false;
		 }
		 if(diskDistanceY > (square.getWidth()/2)+disk.getRadius())			 
		 {
			 return false;
		 }
		 //in these cases the poitns would be intersecting
		 if(diskDistanceX <=(square.getWidth()/2))
		 {
			return true; 
		 }
		 if(diskDistanceY <=(square.getWidth()/2))
		 {
			return true;  
		 }
		 //calculates the case where the disk my intersect the corner of the square
		 //computes the distance from the center of the disk and the corner
		 //then verifies that the distance is not more than the radius of the disk
		 Double cornerDistance_sq =(Math.pow((diskDistanceX-(square.getWidth()/2)),2))+Math.pow((diskDistanceY-(square.getWidth()/2)),2);
		 if(cornerDistance_sq>=(Math.pow(disk.getRadius(),2)))
		 {
			return true; 
		 }
		 else
		 {
			 return false;
		 }
		
		 
	 }
	 //this covers intersection between 2 disks;
	 public static boolean intersects(Disk disk1, Disk disk2)
	 {
		 //distance from cirlce to 
		 double a = (disk1.getRadius()+disk2.getRadius());
		
		 double diskDistanceX= (disk1.getX()-disk2.getX());
		 //width is the same as height for a square
		 double diskDistanceY= (disk1.getY()-disk2.getY());
	
		 if((a*a)>diskDistanceX*diskDistanceX+diskDistanceY*diskDistanceY)
		 {
			 return true;
		 }
		 else
		 {
			 return false;
		 }
	 }

	
	
	
}
