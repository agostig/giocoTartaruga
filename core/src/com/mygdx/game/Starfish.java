package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

    //Begin 2.2.
    public class Starfish extends BaseActor
    {



        //Begin 1.7.
        public boolean collected;
        //End 1.7.



        public Starfish(float x, float y, Stage s)
        {
            super(x, y, s);

            loadTexture("starfish.png");



            //Begin 1.5 tris.
            setBoundaryPolygon(8);
            //End 1.5 tris.



            //Begin 1.8.
            collected = false;
            //End 1.8.



            Action spin = Actions.rotateBy(30, 1);
            this.addAction( Actions.forever(spin) );
        }



        //Begin 1.9.
        public boolean isCollected()
        {
            return collected;
        }

        public void collect()
        {
            collected = true;
            clearActions();
            addAction( Actions.fadeOut(1) );
            addAction( Actions.after( Actions.removeActor() ) );
        }
        //End 1.9.
    }
