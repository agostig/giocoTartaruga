package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Turtle extends BaseActor
{
    public final static int IDLE=0;
    public final static int LEFT=1;
    public final static int TOP=2;
    public final static int RIGHT=3;
    public final static int BOTTOM=4;

    private int direction=0;
    private int spostamento=5;

    private float deltaT = 0;


    public Turtle(float x, float y, Stage s)
    {
        super(x,y,s);

        String[] filenames =
                {"turtle-1.png", "turtle-2.png", "turtle-3.png",
                        "turtle-4.png", "turtle-5.png", "turtle-6.png"};

        loadAnimationFromFiles(filenames, 0.1f, true);
        //End 2.0.



        setDirection(Turtle.IDLE);
    }

    public void setDirection(int d)
    {
        direction=d;
    }

    public void act(float dt)
    {
        super.act(dt);

        if(direction==Turtle.LEFT)
        {
            this.moveBy(-spostamento, 0);
            setDirection(Turtle.IDLE);
        }
        else if(direction==Turtle.TOP)
        {
            this.moveBy(0, spostamento);
            setDirection(Turtle.IDLE);
        }
        else if(direction==Turtle.RIGHT)
        {
            this.moveBy(spostamento, 0);
            setDirection(Turtle.IDLE);
//            deltaT += dt;
//            Gdx.app.log("#dt", String.valueOf(dt));
//            Gdx.app.log("#deltaT", String.valueOf(deltaT));
//            Gdx.app.log("#x", String.valueOf(this.getX()));
//            //if(deltaT > 0.333)
//                //setDirection(Turtle.IDLE);
//            if(deltaT > 1)

        }
        else if(direction==Turtle.BOTTOM)
        {
            this.moveBy(0, -spostamento);
            setDirection(Turtle.IDLE);
        }
    }
}

