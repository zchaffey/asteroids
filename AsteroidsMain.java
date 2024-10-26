
/** Asteroids Game
 *   @author (Zack Chaffey)
 *    @version (1.0)
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class AsteroidsMain implements KeyListener, ActionListener
{
        JFrame f1;
        JPanel main,sub;
        AsteroidsGraphics g1;
        JButton b1,b2;
        boolean endgame,startgame,shotfired,accelerate;
        double xc,yc,xdir,ydir,acc;
        int angle,anglemove,astime,score;
        ArrayList<Integer> keyspressed;
        ArrayList<Shot> shots;
        ArrayList<Asteroids> as;


    public AsteroidsMain()
    {
        setinitialvals();
        
        graphics();
       
        setupframe();
   
        runGame();
     
    }
    public void setupframe()
    {
        f1 = new JFrame("Asteroids");
          f1.setSize(700,600);
          f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          f1.setResizable(false);
          
         Container c1 = f1.getContentPane();
        
        b1=new JButton("Start");
        b1.addActionListener(this);
        b2=new JButton("End");
        b2.addActionListener(this);
        
         sub = new JPanel(); 
          sub.add(b1);
          sub.add(b2);
          sub.setBackground(Color.black);
                                      
        main = new JPanel();
          main.setLayout(new BorderLayout());    
          main.setSize(600,600);
          main.add(g1,BorderLayout.CENTER);
          main.add(sub,BorderLayout.SOUTH);
        
      
        
        c1.add(main);
        
        f1.show();
        
    }
    public void setinitialvals()
    {
        angle=0;
        xc=350;
        yc=310;
        xdir=0;
        ydir=0;
        acc=0;
        accelerate=false;
        keyspressed = new ArrayList<Integer>();
        shots = new ArrayList<Shot>();
        as = new ArrayList<Asteroids>();
        anglemove=0;
        shotfired=false;
    }
    public void graphics()
    {
         g1 = new AsteroidsGraphics(xc,yc,as,score,endgame);
         g1.addKeyListener(this);
        
    }
    public void runGame()
    {
        Thread runner = new Thread();
        while(endgame == false)
     { 
        try 
          { 
            runner.sleep(8); 
           }
          catch(InterruptedException e) {} 
          
        if (startgame)
        {
            asteroids();
            
            moveplayer();
            
            shots();
            
            intersectiondet();
            
            checkloc();
         }
           
           g1.repaint();
         }  
          }
          
        public void intersectiondet()
        {
            for (int index=0; index < as.size(); index++)
            {
             as.get(index).checkinplay();
            if(xc > as.get(index).getX()-20 && xc < as.get(index).getX()+20 && yc > as.get(index).getY()-20 && yc < as.get(index).getY()+20)
             { endgame=true;
               g1.updateendgame(endgame);
                }
           for (int i=0; i < shots.size(); i++){
              if (as.get(index).checkdetection(shots.get(i)))
                 {
                score=score+100;
                g1.updatescore(score);
                as.remove(index);
                shots.remove(i);
                i=shots.size();
            }
            //if (as.get(index).getHit() && as.get(index).checkdetection2(shots.get(i))){
                
                //shots.remove(i);
                //score=score+50;
                //g1.updatescore(score);
                
            //}
            
        }
           
          }
         
        }
        public void asteroids()
        {
            astime++;
            if (astime >=300)
            {
             int randside=(int)(Math.random()*4+1);
             if (randside==1)
             {
             int asxtop=(int)(Math.random()*650);
             int randdirtop=(int)(Math.random()*3+1);
             if (randdirtop==1)
             as.add(new Asteroids(asxtop,-30,1,1));
             if (randdirtop==2)
             as.add(new Asteroids(asxtop,-30,-1,1));
             if (randdirtop==3)
             as.add(new Asteroids(asxtop,-30,0,1));
            }
            if (randside==2)
            {
             int asxbot=(int)(Math.random()*650);
             int randdirbot=(int)(Math.random()*3+1);
             if (randdirbot==1)
             as.add(new Asteroids(asxbot,530,1,-1));
             if (randdirbot==2)
             as.add(new Asteroids(asxbot,530,-1,-1));
             if (randdirbot==3)
             as.add(new Asteroids(asxbot,530,0,-1));
            }
            if (randside==3)
            {
             int asylef=(int)(Math.random()*490);
             int randdirlef=(int)(Math.random()*3+1);
             if (randdirlef==1)
             as.add(new Asteroids(-30,asylef,1,-1));
             if (randdirlef==2)
             as.add(new Asteroids(-30,asylef,1,1));
             if (randdirlef==3)
             as.add(new Asteroids(-30,asylef,1,0));
            }
            if (randside==4)
            {
             int asyrig=(int)(Math.random()*490);
             int randdirrig=(int)(Math.random()*3+1);
             if (randdirrig==1)
             as.add(new Asteroids(700,asyrig,-1,-1));
             if (randdirrig==2)
             as.add(new Asteroids(700,asyrig,-1,1));
             if (randdirrig==3)
             as.add(new Asteroids(700,asyrig,-1,0));
            }
             astime=0;
            }
            for (int index=0; index<as.size(); index++)
             {
                as.get(index).moveAsteroid();
                g1.updateAsteroid(as);
             }
        }
        public void shots()
        {
            if (shotfired)
            {
                for (int index=0; index<shots.size(); index++)
                {
                shots.get(index).moveShot();
                g1.updateshots(shots,shotfired);
                if (shots.get(index).checkoutofbound())
                {
                    shots.remove(index);
                }
               }
            }
        }
        
       private void moveplayer()
       {
           accelerate=false;
           for (int index=0; index < keyspressed.size(); index++)
           {
               if (keyspressed.get(index) == 38)
               {
                   accelerate=true;
                xdir=Math.cos(Math.toRadians(angle));
                ydir=Math.sin(Math.toRadians(angle));
                }
               
                 if (keyspressed.get(index) ==37)
              {
                  angle=angle-3;
                }
               if  (keyspressed.get(index) ==39)
               {
                angle=angle+3;
                }
                
                }
           if (accelerate)
           {
                if (acc<= 2)
                {
                acc=acc+0.01;
            }
                xdir=xdir*acc;
                ydir=ydir*acc;
            
            }
            else
            {
                 if (acc>= 0)
                {
                acc=acc-0.01;
                
            }
                xdir=Math.cos(Math.toRadians(angle));
                ydir=Math.sin(Math.toRadians(angle));
                xdir=xdir*acc;
                ydir=ydir*acc;
            
            }
           xc=xc+xdir;
           yc=yc+ydir;
           angle=angle+anglemove;
           g1.updateplayer(xc,yc,angle);
        }
        private void checkloc()
        {
            
            if (xc<-20)
            xc=700;
            if (xc>700)
            xc=-20;
            if (yc<-20)
            yc=550;
            if (yc>550)
            yc=-20; 
        }
         public void actionPerformed (ActionEvent event)
       {  
        if (event.getSource() == b1)
        {
            startgame=true;
            g1.requestFocus();
         }
         
        if (event.getSource() == b2)
        {
           endgame = true;
           f1.dispose();
         }
         
         
     }
    
    
    
    
    public void keyReleased(KeyEvent evt) 
    {
         keyspressed.remove((Object)evt.getKeyCode()); 
    }
        public void keyTyped(KeyEvent evt) {}
        public void keyPressed(KeyEvent evt)
      {
         if(!(keyspressed.contains(evt.getKeyCode()))){
            keyspressed.add(evt.getKeyCode());
         }
          if (evt.getKeyCode() == 32)
               {
                  shotfired=true;
                  shots.add(new Shot(xc,yc,angle));
                }
      }
}
