package com.mygdx.game;

//Begin 1.0.
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Intersector.MinimumTranslationVector;
import java.util.ArrayList;
//End 1.0.
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

public class BaseActor extends Actor
{
    //Begin 1.1.
    private Animation<TextureRegion> animation;
    private float elapsedTime;
    private boolean animationPaused;

    //Begin 2.6.
    private Vector2 velocityVec;
//End 2.6.

    //Begin 2.8.
    private Vector2 accelerationVec;
    private float acceleration;
//End 2.8.

    //Begin 3.1.
    private float maxSpeed;
    private float deceleration;
//End 3.1.

    private Polygon boundaryPolygon;

    public BaseActor(float x, float y, Stage s)
    {
        //Begin 1.0.
        //Call constructor from Actor class.
        super();

        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        //Perform additional initialization tasks.
        setPosition(x,y);
        s.addActor(this);
        //End 1.0.


        //Begin 1.1.
        animation = null;
        elapsedTime = 0;
        animationPaused = false;
        //End 1.1.


        //Begin 2.6.
        velocityVec = new Vector2(0,0);
        //End 2.6.


        //Begin 2.8.
        accelerationVec = new Vector2(0,0);
        acceleration = 0;
        //End 2.8.


        //Begin 3.1.
        maxSpeed = 1000;
        deceleration = 0;
        //End 3.1.
    }


    //Creare diverse timeline e richiamare setAnimation diverse volte
    public void setAnimation(Animation<TextureRegion> anim)
    {
        animation = anim;
        TextureRegion tr = animation.getKeyFrame(0);
        float w = tr.getRegionWidth();
        float h = tr.getRegionHeight();
        setSize( w, h );
        setOrigin( w/2, h/2 );


        //Begin 1.2.
        if(boundaryPolygon == null)
            setBoundaryRectangle();

    }


    //Begin 1.3.
    public void setAnimationPaused(boolean pause)
    {
        animationPaused = pause;
    }
    //End 1.3.

    //Begin 1.4.
    public void act(float dt)
    {
        super.act( dt );

        if (!animationPaused)
            elapsedTime += dt;
    }
    //Begin 1.4.

    //Begin 1.5.
    public void draw(Batch batch, float parentAlpha)
    {
        super.draw( batch, parentAlpha );

        //Apply color tint effect.
        Color c = getColor();
        batch.setColor(c.r, c.g, c.b, c.a);

        if ( animation != null && isVisible() )
            batch.draw( animation.getKeyFrame(elapsedTime),
                    getX(), getY(), getOriginX(), getOriginY(),
                    getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation() );
    }
    //End 1.5.



    //GESTIONE ANIMAZIONI
    //Begin 1.6.
    public Animation<TextureRegion> loadAnimationFromFiles(String[] fileNames,
                                                           float frameDuration,
                                                           boolean loop)
    {
        int fileCount = fileNames.length;
        Array<TextureRegion> textureArray = new Array<TextureRegion>();

        for (int n = 0; n < fileCount; n++)
        {
            String fileName = fileNames[n];
            Texture texture = new Texture( Gdx.files.internal(fileName) );
            texture.setFilter( TextureFilter.Linear, TextureFilter.Linear );
            textureArray.add( new TextureRegion( texture ) );
        }

        Animation<TextureRegion> anim = new Animation<TextureRegion>(frameDuration, textureArray);

        if (loop)
            anim.setPlayMode(PlayMode.LOOP);
        else
            anim.setPlayMode(PlayMode.NORMAL);

        if (animation == null)
            setAnimation(anim);

        return anim;
    }
    //End 1.6.

    //Begin 1.7.
    public Animation<TextureRegion> loadAnimationFromSheet(String fileName,
                                                           int rows, int cols,
                                                           float frameDuration, boolean loop)
    {
        Texture texture = new Texture(Gdx.files.internal(fileName), true);
        texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        int frameWidth = texture.getWidth() / cols;
        int frameHeight = texture.getHeight() / rows;

        TextureRegion[][] temp = TextureRegion.split(texture,
                frameWidth,
                frameHeight);

        Array<TextureRegion> textureArray = new Array<TextureRegion>();

        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++)
                textureArray.add( temp[r][c] );

        Animation<TextureRegion> anim = new Animation<TextureRegion>(frameDuration, textureArray);

        if (loop)
            anim.setPlayMode(PlayMode.LOOP);
        else
            anim.setPlayMode(PlayMode.NORMAL);

        if (animation == null)
            setAnimation(anim);

        return anim;
    }
    //End 1.7.

    //Begin 1.8.
    public Animation<TextureRegion> loadTexture(String fileName)
    {
        String[] fileNames = new String[1];
        fileNames[0] = fileName;
        return loadAnimationFromFiles(fileNames, 1, true);
    }
    //End 1.8.

    //Begin 1.9.
    public boolean isAnimationFinished()
    {
        return animation.isAnimationFinished(elapsedTime);
    }
    //End 1.9.


    //GESTIONE MOVIMENTI
    //Begin 2.7.
    public void setSpeed(float speed)
    {
        //If length is zero, then assume motion angle is zero degrees.
        if (velocityVec.len() == 0)
            velocityVec.set(speed, 0);
        else
            velocityVec.setLength(speed);
    }

    public float getSpeed()
    {
        return velocityVec.len();
    }

    public void setMotionAngle(float angle)
    {
        velocityVec.setAngleDeg(angle);
    }

    public float getMotionAngle()
    {
        return velocityVec.angleDeg();
    }

    public boolean isMoving()
    {
        return (getSpeed() > 0);
    }
//End 2.7.



    //Begin 2.9.
    public void setAcceleration(float acc)
    {
        acceleration = acc;
    }

    public void accelerateAtAngle(float angle)
    {
        accelerationVec.add( new Vector2(acceleration, 0).setAngleDeg(angle) );
    }
//End 2.9.



    //Begin 3.0.
    public void accelerateForward()
    {
        accelerateAtAngle( getRotation() );
    }
//End 3.0.



    //Begin 3.2.
    public void setMaxSpeed(float ms)
    {
        maxSpeed = ms;
    }

    public void setDeceleration(float dec)
    {
        deceleration = dec;
    }
//End 3.2.



    //Begin 3.3.
    public void applyPhysics(float dt)
    {
        //Apply acceleration.
        velocityVec.add( accelerationVec.x * dt, accelerationVec.y * dt );

        float speed = getSpeed();

        //Decrease speed (decelerate) when not accelerating.
        if (accelerationVec.len() == 0)
            speed -= deceleration * dt;

        //Keep speed within set bounds.
        speed = MathUtils.clamp(speed, 0, maxSpeed);

        //Update velocity.
        setSpeed(speed);

        //Apply velocity.
        moveBy( velocityVec.x * dt, velocityVec.y * dt );

        //Reset acceleration.
        accelerationVec.set(0,0);
    }


    //GESTIONE COLLISIONI
    //Begin 1.1.
    public void setBoundaryRectangle()
    {
        float w = getWidth();
        float h = getHeight();
        float[] vertices = {0,0, w,0, w,h, 0,h};
        boundaryPolygon = new Polygon(vertices);
    }
//End 1.1.

    public void setBoundaryPolygon(int numSides)
    {
        float w = getWidth();
        float h = getHeight();
        float angleDiv = 6.28f / numSides;
        float[] vertices = new float[2*numSides];
        for (int i = 0; i < numSides; i++)
        {
            float angle = i * angleDiv;

            //x-coordinate.
            vertices[2*i] = w/2 * MathUtils.cos(angle) + w/2; //1
            //y-coordinate.
            vertices[2*i+1] = h/2 * MathUtils.sin(angle) + h/2; //0
        }
        boundaryPolygon = new Polygon(vertices);
    }

    public Polygon getBoundaryPolygon()
    {
        boundaryPolygon.setPosition( getX(), getY() );
        boundaryPolygon.setOrigin( getOriginX(), getOriginY() );
        boundaryPolygon.setRotation ( getRotation() );
        boundaryPolygon.setScale( getScaleX(), getScaleY() );

        return boundaryPolygon;
    }

    //Begin 1.5.
    public boolean overlaps(BaseActor other)
    {
        //Gdx.app.log("#INFO", "overlaps().");

        Polygon poly1 = this.getBoundaryPolygon();
        Polygon poly2 = other.getBoundaryPolygon();

        //Gdx.app.log("#INFO", String.valueOf(poly1));
        //Gdx.app.log("#INFO", String.valueOf(poly2));

        //Initial test to improve performance.
        if(!poly1.getBoundingRectangle().overlaps(poly2.getBoundingRectangle()) ) {
            return false;
        }

        return Intersector.overlapConvexPolygons( poly1, poly2 );
    }
//End 1.5.

    public void centerAtPosition(float x, float y)
    {
        setPosition( x - getWidth()/2, y - getHeight()/2 );
    }

    public void centerAtActor(BaseActor other)
    {
        centerAtPosition( other.getX() + other.getWidth()/2 ,
                other.getY() + other.getHeight()/2 );
    }

    public void setOpacity(float opacity) {
        this.getColor().a = opacity;
    }

    //Begin 1.1.
    public Vector2 preventOverlap(BaseActor other)
    {
        Polygon poly1 = this.getBoundaryPolygon();
        Polygon poly2 = other.getBoundaryPolygon();

        //Initial test to improve performance
        if (!poly1.getBoundingRectangle().overlaps(poly2.getBoundingRectangle()))
            return null;

        MinimumTranslationVector mtv = new MinimumTranslationVector();
        boolean polygonOverlap = Intersector.overlapConvexPolygons(poly1, poly2, mtv);

        if (!polygonOverlap)
            return null;

        this.moveBy(mtv.normal.x * mtv.depth, mtv.normal.y * mtv.depth);

        return mtv.normal;
    }
//End 1.1.


    public static ArrayList<BaseActor> getList(Stage stage, String className)
    {
        ArrayList<BaseActor> list = new ArrayList<BaseActor>();

        className="com.mygdx.game."+className;
        //Gdx.app.log("#CLASSNAME", className);
        Class theClass = null;

        try
        {
            theClass = Class.forName(className);
        }
        catch(Exception error)
        {
            Gdx.app.log("#ERRORE", error.toString());

            error.printStackTrace();
        }

        //Gdx.app.log("#CLASS", theClass.toString());
        for(Actor a : stage.getActors())
        {
            if( theClass.isInstance( a ) ) {
                //Gdx.app.log("#CLASS", theClass.toString());
                //Gdx.app.log("#ACTOR", a.toString());
                list.add((BaseActor) a);
            }
        }

        return list;
    }
//End 1.1.



    //Begin 1.2.
    public static int count(Stage stage, String className)
    {
        return getList(stage, className).size();
    }
//End 1.2.

}
