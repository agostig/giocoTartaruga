package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Whirlpool extends BaseActor
{
    //Begin 2.1.
    public Whirlpool(float x, float y, Stage s)
    {
        super(x,y,s);

        loadAnimationFromSheet("whirlpool.png", 2, 5, 0.1f, true);
    }
}
