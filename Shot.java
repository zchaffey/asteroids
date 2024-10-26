

public class Shot
{
     double x,y,xdir,ydir;
     
    public Shot(double x,double y,double angle)
    {
      this.x=x;
      this.y=y;
      xdir=2*Math.cos(Math.toRadians(angle));
      ydir=2*Math.sin(Math.toRadians(angle));
    }
    
    public int getX()
    {
        return (int)x;
    }
    
    public int getY()
    {
        return (int)y;
    }
    
    public boolean checkoutofbound()
    {
        return (x<-10 || x>710 || y<-10 || y>550);
    }
    public void moveShot()
    {
        x = x + xdir;
        y = y + ydir;  
    }  
}
