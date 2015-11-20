package PoolGame;

public class Ball {

	private int mynum;
	private double myxspeed = 0;
	private double myyspeed = 0;
	private boolean mystatus;
	private boolean hitstatus = false;
	private boolean hitwallstatus;
	private double xpos;
	private double ypos;
	
	public double xTraj;
	public double yTraj;
	
	final double diameter = 18.7;
	
	public Ball()
	{
		
	}
	
	public Ball(int num, double x, double y)
	{
		mynum = num;
		xpos = x;
		ypos = y;
		mystatus = true;
	}
	
	public Ball(int num, double x, double y, double xspeed, double yspeed)
	{
		mynum = num;
		xpos = x;
		ypos = y;
		mystatus = true;
		myxspeed = xspeed;
		myyspeed = yspeed;
	}
	
	public int getNum()
	{
		return mynum;
	}
	
	public void setSpeed(double x, double y)
	{
		myxspeed = x;
		myyspeed = y;
	}
	
	public void setPos(int x, int y)
	{
		xpos = x;
		ypos = y;
	}
	
	public void ballInPocket(boolean b)
	{
		mystatus = b;
	}
	
	public void setHitStatus(boolean b)
	{
		hitstatus = b;
	}
	
	public boolean getHitStatus()
	{
		return hitstatus;
	}
	
	public void setHitWallStatus(boolean b)
	{
		hitwallstatus = b;
	}
	
	public boolean getHitWallStatus()
	{
		return hitwallstatus;
	}
	
	public double getXPos()
	{
		return xpos;
	}
	
	public double getYPos()
	{
		return ypos;
	}
	
	public double getxSpeed()
	{
		return myxspeed;
	}
	
	public double getySpeed()
	{
		return myyspeed;
	}
	
	//move it!
	public void moveBall()
	{
			myxspeed = myxspeed*0.9;
			myyspeed = myyspeed*0.9;
			xTraj = xpos;
			yTraj = ypos;
			xpos = xpos+myxspeed;
			ypos = ypos+myyspeed;
			xTraj = xpos - xTraj;
			yTraj = ypos - yTraj;
	}
	
}
