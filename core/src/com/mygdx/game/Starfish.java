package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

    //Begin 2.2.
    public class Starfish extends BaseActor
    {
        //Begin 2.2.
        public Starfish(float x, float y, Stage s)
        {
            super(x, y, s);

            loadTexture("starfish.png");

            Action spin = Actions.rotateBy(30, 1);
            this.addAction(Actions.forever(spin));
        }
        //End 2.2.

    }
