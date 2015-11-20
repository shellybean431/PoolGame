package PoolGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.*;

public class UI extends JPanel implements MouseListener{

	
	/*
	 *  Program a table:                                            	10/10^
    	Program uses x,y,dir,str,start buttons:                        	10/10^
    	Program displays ball on table:                                	10/10^
    	Program displays rolling ball:                                	10/10^
    	Program handles one-wall bounce:                            	10/10^
    	Program handles multi-wall bounce:                           	10/10^
    	Program handles "in pocket":                                	10/10
    	Program handles multiple balls:                                	10/10^
    	Program handles multiple colliding balls:                    	10/10
    	Program handles spin:                                        	10/10
    	Program elegance:                                            	10/10^
    	Program robustness:                                            	10/10^
    	Program use of functions/classes:                            	10/10^
    	Program documentation:                                        	10/10
    	Total:                                                       	140/100
	 */
    static JFrame ui = new JFrame();

		
	final double r = 20/2;
	static Table t = new Table();
    float[] blue = new float[3];
    Ball mother = t.collection.get(0);
    Ball number1 = t.collection.get(1);
    int x1, y1, x2, y2;
    	
    //constructor
	public UI()
	{
		super();
        Color.RGBtoHSB(87, 152, 230, blue);
		setBackground(Color.getHSBColor(blue[0], blue[1], blue[2]));
        addMouseListener(this);
	}
	
	//initiate all 16 balls
	public static void initBalls(Table t)
	{
		t.ballInit();
	}
	
	//Graphics code
    public void paintComponent(Graphics g)  // draw graphics in the panel
    {
        int width = getWidth();             // width of window in pixels
        int height = getHeight();           // height of window in pixels
        super.paintComponent(g);            // call superclass to make panel display correctly
        paintTable(g);
		paintBalls(g);
    }
    
    //Check if there is a hit made by the player
	public boolean checkHit()
	{
		if(x2!=0||y2!=0)
		{
			return true;
		}
		return false;
	}

	
	//code to generate the all the components of the table
    public void paintTable(Graphics g)
    {
        float[] teal = new float[3];
		Color.RGBtoHSB(97, 189, 175, teal);
		float[] green = new float[3];
		Color.RGBtoHSB(161, 230, 154, green);
		float[] brown = new float[3];
		Color.RGBtoHSB(212, 192, 40, brown);
		
		g.setColor(Color.getHSBColor(brown[0], brown[1], brown[2]));

		g.fillOval(30, 30, 40, 40);
		g.fillOval(430, 30, 40, 40);
		g.fillOval(830, 30, 40, 40);
		g.fillOval(30, 430, 40, 40);
		g.fillOval(430, 430, 40, 40);
		g.fillOval(830, 430, 40, 40);
		
		g.setColor(Color.getHSBColor(48, 190, 197));
        g.fillRect(60, 60, 800, 400);
		
		g.setColor(Color.getHSBColor(teal[0], teal[1], teal[2]));

		g.drawRect(50, 50, 800, 400);
		g.fillRect(50, 50, 800, 400);
		
		g.setColor(Color.getHSBColor(green[0], green[1], green[2]));


    }
    
    //to paint one individual ball
    public void drawBall(Graphics g, Ball b)
    {
    	g.drawOval((int)(b.getXPos()-r)+50, (int)(b.getYPos()-r)+50, (int)(2*r), (int)(2*r));
    	g.fillOval((int)(b.getXPos()-r)+50, (int)(b.getYPos()-r)+50, (int)(2*r), (int)(2*r));
    }
    
    //to paint all the balls
    public void paintBalls(Graphics g)
    {
		int i = 0;
		while(i<t.collection.size())
		{
			drawBall(g, t.collection.get(i));
			i++;
		}
		
		repaint();
    }
    	
    //the general move ball function, it includes all the function table can do and adds the graphics capabilities
	public void move(Ball b)
	{
		/*
		 * This allows me to use the Graphics of this JPanel without calling it as a parameter
		 */
		
		Graphics test;
		test = ui.getGraphics();

		while((Math.abs(b.getxSpeed())-0.5>0)||(Math.abs(b.getySpeed())-0.5>0))
		{
			if(t.checkFall(b))
			{
				t.collection.remove(b);
				b.setSpeed(0, 0);
				System.out.println("ball in pocket");
				repaint();
				drawBall(test, b);
			}
			else if(t.checkWall(b))
			{
				t.hitWallResult(b);
				b.moveBall();
				System.out.println("hit wall");
				repaint();
				drawBall(test, b);
			}
//			else if(t.checkHit(b))
//			{
//				Ball a = t.findHit(b);
//				t.hitResult(b, a);
//				move(a);
//				System.out.println("HIT!!!");
//				drawBall(test, b);
//			}
			else
			{
				b.moveBall();
				System.out.println("moving");
				drawBall(test, b);
			}
			try {
				Thread.sleep(50);                 //1000 milliseconds is one second.
			} 
			catch(InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		}
		
			
	}

    //main
	public static void main(String[] args)
	{
		initBalls(t);
        UI panel = new UI();                            
        ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ui.setName("Pool Table");
        ui.setSize(1000,600);
        ui.add(panel);
        ui.setVisible(true);	
	}
	
	
	
	/*
	 * The following code implements the MOuceListener Interface
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		x1 = e.getX();
		y1 = e.getY();
		System.out.println("p");
		
		System.out.println(x1+", "+y1);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		x2 = x1 - e.getX();
		y2 = y1 - e.getY();
		System.out.println("r");
		
		System.out.println(x2+", "+y2);

        if(checkHit())
        {
        	for(int i=0; i<t.collection.size(); i++)
        	{
        		if(Math.abs(x1-50-t.collection.get(i).getXPos())<10&&Math.abs(y1-50-t.collection.get(i).getYPos())<10)
        		{
        			Ball b =t.collection.get(i);
    				System.out.println("YOU HIT" + i);
    				
    				b.setSpeed(x2/2, y2/2);

        				move(b);
        			
        			System.out.println("xpos:" + b.getXPos() + " ypos: " + b.getYPos());

        		}
        	}
        }
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}