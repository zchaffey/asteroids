
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;



public class AsteroidsGraphics  extends JPanel
{
     
     boolean startgame,endgame,shotfired;
     int angle,score;
     ArrayList<Shot> shots;
     ArrayList<Asteroids> as;
     double xc,yc;
     Shot sh;
    public AsteroidsGraphics(double xc, double yc,ArrayList<Asteroids> as, int score, boolean endgame)               
  {
        this.xc=xc;
        this.yc=yc;
        this.as=as;
        this.score=score;
        this.endgame=endgame;
        setBackground(Color.black);
    }

    public void updateplayer(double xc, double yc,int angle)
    {
        this.xc=xc;
        this.yc=yc;
        this.angle=angle;
    }
    
    public void updateshots(ArrayList<Shot> shots,boolean shotfired)
    {
        this.shots=shots;
        this.shotfired=shotfired;
    }
    
    public void updateAsteroid(ArrayList<Asteroids> as)
    {
        this.as=as;
    }
    
    public void updatescore (int score)
    {
        this.score=score;
    }
    
    public void updateendgame (boolean endgame)
    {
        this.endgame=endgame;
    }
   
    public void paint (Graphics g)         
    {
        super.paint(g);
        Graphics2D g2= (Graphics2D)g;
        
        g2.setColor(Color.white);
      
       int[] x= {-20,-10,-20,20};
       int[] y= {-10,0,10,0};
       
        g2.translate((int)xc,(int)yc);
        g2.rotate(Math.toRadians(angle));
       
       g2.fillPolygon(x,y,4);
       
       g2.rotate(Math.toRadians(-angle));
       if (shotfired)
        {
          for (int index=0; index < shots.size(); index++)
          {
          g2.setColor(Color.red);
          g2.fillOval((int)(shots.get(index).getX()-xc) ,(int)(shots.get(index).getY()-yc),5,5);
         }
        }
        for (int index=0; index < as.size(); index++)
        {
            g2.setColor(Color.white);
            g2.fillRect((int)(as.get(index).getX()-xc),(int)(as.get(index).getY()-yc),as.get(index).getLength(),as.get(index).getHeight());
        }
        g2.setColor(Color.white);
        g2.setFont(new Font("Arial",Font.BOLD,20));
        g2.drawString("Score:"+score,25-(int)xc,500-(int)yc);
        
        if (endgame)
        {
            g2.setColor(Color.red);
            g2.setFont(new Font("Arial",Font.BOLD,80));
            g2.drawString("GAME OVER",100-(int)xc,275-(int)yc);
        }
     }    
        
}
