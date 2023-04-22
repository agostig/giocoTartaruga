package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.compression.lzma.Base;

public class Rock extends BaseActor
{
    public Rock(float x, float y, Stage s)
    {
        super(x,y,s);
        loadTexture("rock.png");

        setBoundaryPolygon(8);
    }
}

