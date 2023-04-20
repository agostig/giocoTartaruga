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
    private int spostamento=50;

    private float deltaT = 0;


    public Turtle(float x, float y, Stage s)
    {
        super(x,y,s);


        String[] filenames =
                {"turtle-1.png", "turtle-2.png", "turtle-3.png",
                        "turtle-4.png", "turtle-5.png", "turtle-6.png"};

        loadAnimationFromFiles(filenames, 0.1f, true);
        //End 2.0.

        setBoundaryPolygon(8);

        setDirection(Turtle.IDLE);



        //Begin 3.4.
        setAcceleration(400);
        setMaxSpeed(400);
        setDeceleration(5000);
        //End 3.4.*/
    }

    public void setDirection(int d)
    {
        direction=d;
    }

    public void act(float dt)
    {
        super.act(dt);


        /*
        //DA USERE NEL GIOCO
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
         */

        if(direction==Turtle.LEFT)
            accelerateAtAngle(180);
        else if(direction==Turtle.TOP)
            accelerateAtAngle(90);
        else if(direction==Turtle.RIGHT)
            accelerateAtAngle(0);
        else if(direction==Turtle.BOTTOM)
            accelerateAtAngle(270);

        applyPhysics(dt);

        setAnimationPaused( !isMoving() );

        if ( getSpeed() > 0 )
            setRotation( getMotionAngle() );
    }
}

