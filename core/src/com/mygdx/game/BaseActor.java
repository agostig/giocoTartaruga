package com.mygdx.game;

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
    //End 1.1.

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
    }

    //Begin 1.2.
    public void setAnimation(Animation<TextureRegion> anim)
    {
        animation = anim;
        TextureRegion tr = animation.getKeyFrame(0);
        float w = tr.getRegionWidth();
        float h = tr.getRegionHeight();
        setSize( w, h );
        setOrigin( w/2, h/2 );
    }
    //End 1.2.

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
}
