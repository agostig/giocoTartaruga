package com.mygdx.game;



public class Player extends ActorBeta
{
    public final static int IDLE=0;
    public final static int LEFT=1;
    public final static int TOP=2;
    public final static int RIGHT=3;
    public final static int BOTTOM=4;

    private int direction=0;
    private int speed=100;

    public Player()
    {
        super();

        setDirection(Player.IDLE);
    }

    public void setDirection(int d)
    {
        direction=d;
    }

    public void act(float dt)
    {
        super.act(dt);

        if(direction==Player.LEFT)
        {
            this.moveBy(-speed, 0);
            setDirection(Player.IDLE);
        }
        else if(direction==Player.TOP)
        {
            this.moveBy(0, speed);
            setDirection(Player.IDLE);
        }
        else if(direction==Player.RIGHT)
        {
            this.moveBy(speed, 0);
            setDirection(Player.IDLE);
        }
        else if(direction==Player.BOTTOM)
        {
            this.moveBy(0, -speed);
            setDirection(Player.IDLE);
        }
    }
}
