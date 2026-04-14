import java.awt.Color;
import java.util.Random;
/**
 * Class BallDemo - a short demonstration showing animation with the 
 * Canvas class. 
 *
 * @author Michael Kölling and David J. Barnes
 * @author Stephen M. Burns
 * @version 2026.04.13
 */

public class BallDemo   
{
    private Canvas myCanvas;
    private Box box;

    /**
     * Create a BallDemo object. Creates a fresh canvas and makes it visible.
     * 
     */
    public BallDemo()
    {
        myCanvas = new Canvas("Ball Demo", 600, 500);
        box=new Box (100,100,500,400, myCanvas);
        box.draw();
        
        Box box2 = new Box(myCanvas);
        box2.draw();
        
    }

    /**
     * boxBounce - simulate 5-50 balls bouncing within a box
     * 
     * @param numOfBalls number of balls to simulate bouncing, clamped between 5-50. 
     */
    public void boxBounce(int ballNumber)
    {
        Random rand=new Random();
        int xInstan;
        int yInstan;
        
        
        if(ballNumber<5 || ballNumber>50)
        {
            ballNumber=rand.nextInt((50-5+1)+5);
            System.out.println("Your number fell outside the range, so a proper number was generated");
        }
        
        BoxBall[] ballPile=new BoxBall[ballNumber];
        

        myCanvas.setVisible(true);
        //ball creation
        for(int i=0; i<ballPile.length; i++)
        {
            
            //RGB ranges from 0 to 255
            
            Color randoRgb= new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));
            
            yInstan=(int)(Math.random()*(box.getBottomWall()-box.getTopWall())+box.getTopWall());
            xInstan=(int)(Math.random()*(box.getRightWall()-box.getLeftWall())+box.getTopWall());
            
            ballPile[i]=new BoxBall(xInstan,yInstan,10,randoRgb,box,myCanvas);
            
            ballPile[i].draw();
        }
        //ball bouncing        
        while(true)
        {
            myCanvas.wait(2);
            box.draw();
            for(BoxBall ballMove : ballPile)
            {
                ballMove.move();
            }
        }

    }
    
    /**
     * Simulate two bouncing balls
     */
    public void bounce()
    {
        int ground = 400;   // position of the ground line

        myCanvas.setVisible(true);

        // draw the ground
        myCanvas.setForegroundColor(Color.BLACK);
        myCanvas.drawLine(50, ground, 550, ground);

        // create and show the balls
        BouncingBall ball = new BouncingBall(50, 50, 16, Color.BLUE, ground, myCanvas);
        ball.draw();
        BouncingBall ball2 = new BouncingBall(70, 80, 20, Color.RED, ground, myCanvas);
        ball2.draw();

        // make them bounce
        boolean finished =  false;
        while (!finished) {
            myCanvas.wait(50);           // small delay
            ball.move();
            ball2.move();
            // stop once ball has travelled a certain distance on x axis
            if(ball.getXPosition() >= 550 || ball2.getXPosition() >= 550) {
                finished = true;
            }
        }
    }
}
