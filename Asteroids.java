

public class Asteroids
{
    int x,y,xdir,ydir,len,hgt;
    boolean hit;

    public Asteroids(int x, int y, int xdir, int ydir)
    {
       this.x=x;
       this.y=y;
       this.xdir=xdir;
       this.ydir=ydir;
       hit=false;
       len=30;
       hgt=30;
    }
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public boolean getHit(){
        return hit;
    }
    public void setHit(boolean hit) {
        this.hit=hit;
    }
    public int getLength(){
        return len;
    }
    public int getHeight(){
        return hgt;
    }
 
    public void moveAsteroid()
    {
        x=x+xdir;
        y=y+ydir;
    }
    public boolean checkdetection(Shot s)
    {
       if (x>s.getX()-30 && x<s.getX()+30 && y>s.getY()-30 && y<s.getY()+30){
           len=15;
           hgt=15;
           hit=true;
           return true;
       }
       else{
           return false;
       }
    }
    public boolean checkdetection2(Shot s)
    {
       if (x>s.getX()-30 && x<s.getX()+30 && y>s.getY()-30 && y<s.getY()+30){
           hit=true;
           return true;
       }
       else{
           return false;
       }
    }
    public void checkinplay()
    {
        if (y>530)
        y=-30;
        if (y<-30)
        y=530;
        if (x<-30)
        x=700;
        if (x>700)
        x=0;
    }
    

}
