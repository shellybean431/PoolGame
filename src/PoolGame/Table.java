package PoolGame;

import java.util.*;

public class Table {

	final double x = 800;
	final double y = 400;
	final double diameter = 20;

	
	List<Ball> collection = new ArrayList<>();	
	List<Ball> pocket = new ArrayList<>();	

	//default constructor
	public Table()
	{

	}

	//Check if the ball is in a pocket
	public boolean checkFall(Ball b)
	{
		double xpos = b.getXPos();
		double ypos = b.getYPos();
		boolean condition = false;

		if((xpos>=0&&xpos<20)||(xpos>390&&xpos<410)||(xpos>790&&xpos<810))
		{
			condition = true;
		}
		if((condition)&&((ypos>=0&&ypos<10)||(ypos>390&&ypos<410)))
		{
			b.ballInPocket(true);
			return true;
		}
		else return false;
		
	}
	
	//
	public boolean checkTrajCollision(Ball a, Ball b)
	{
		double k = b.yTraj/b.xTraj;
		double m = b.getYPos()-k*b.getXPos();
		if(Math.abs(k*a.getXPos()+m-b.getYPos())<=10)
		{
			return true;
		}
		else return false;
	}
	
	
	//Check if two balls collide
	public boolean checkCollision(Ball a, Ball b)
	{
		double distance = Math.sqrt(a.getXPos()-b.getXPos())*(a.getXPos()-b.getXPos()) + (a.getYPos()-b.getYPos())*(a.getYPos()-b.getYPos());
		if (distance <= diameter||checkTrajCollision(a, b))
		{
			a.setHitStatus(true);
			b.setHitStatus(true);
			return true;
		}
		else 
		{
			a.setHitStatus(false);
			b.setHitStatus(false);
			return false;
		}
	}
	
	//Check collision with each one of the balls in the collection
	public boolean checkHit(Ball b)
	{
		for(int i = 0; i<collection.size(); i++)
		{
			if(b.getNum()!=collection.get(i).getNum())
			{
				return (checkCollision(b, collection.get(i)));
			}
		}
		return false;
	}
	
	//Check if hits wall
	public boolean checkWall(Ball b)
	{
		if(b.getXPos()>800||b.getXPos()<0)
		{
			return true;
		}
		else if(b.getYPos()>400||b.getYPos()<0)
		{

			return true;
		}
		
		return false;

	}
	
	public void hitWallResult(Ball b)
	{
		if(b.getXPos()>790||b.getXPos()<10)
		{
			b.setHitStatus(true);
			b.setPos((int)(b.getXPos()/790)*790, (int)(b.getYPos()));
			b.setSpeed(-b.getxSpeed(), b.getySpeed());
		}
		if(b.getYPos()>390||b.getYPos()<10)
		{
			b.setHitStatus(true);
			b.setPos((int)(b.getXPos()), (int)(b.getYPos()/390)*390);
			b.setSpeed(b.getxSpeed(), -b.getySpeed());
		}
		
		b.setHitStatus(false);
	}
		

	public Ball findHit(Ball b)
	{
		for(int i=0; i<collection.size(); i++)
		{
			if(b.getNum()!=collection.get(i).getNum()&&collection.get(i).getHitStatus())
			{
				return collection.get(i);
			}
		}
		return null;
	}
	
	public void ballInit()
	{
		double x = 50;
		double y = 50;
		int m = 0, n = 0, l = 0, p = 0;
		for (int i = 0; i<16; i++)
		{
			if(i==0)
			{
				collection.add(new Ball(i, 600, 200));
			}
			else if(i<6)
			{
				collection.add(new Ball(i, 200-80, 200-40+(m*20)));
				m++;
			}
			else if(i<10)
			{
				collection.add(new Ball(i, 200-60, 200-30+(n*20)));
				n++;
			}
			else if(i<13)
			{
				collection.add(new Ball(i, 200-40, 200-20+(l*20)));
				l++;
			}
			else if(i<15)
			{
				collection.add(new Ball(i, 200-20, 200-10+(p*20)));
				p++;
			}
			else if(i<16)
			{
				collection.add(new Ball(i, 200, 200));
			}
		}
	}
	
	//What happens after two balls collide
	public void hitResult(Ball a, Ball b)
	{		
		double speedax = a.getxSpeed();
		double speeday = a.getySpeed();
		double speedbx = b.getxSpeed();
		double speedby = b.getySpeed();
		double impactSpeedy = speedby-speeday;
		double impactSpeedx = speedbx-speedax;
		
		speedax = speedax + impactSpeedx;
		speeday = speeday + impactSpeedy;
		speedbx = speedbx - impactSpeedx;
		speedby = speedby - impactSpeedy;
				
		a.setSpeed(speedax, speeday);
		b.setSpeed(speedbx, speedby);
		
		a.setHitStatus(false);
		b.setHitStatus(false);
	}
	
}
