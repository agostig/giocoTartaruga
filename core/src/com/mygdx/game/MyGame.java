package com.mygdx.game;


//2.
import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGame extends GameBeta {

	/*private SpriteBatch batch;

	private Texture turtleTexture;
	private float turtleX;
	private float turtleY;
	private Rectangle turtleRectangle;

	private Texture starfishTexture;
	private float starfishX;
	private float starfishY;
	private Rectangle starfishRectangle;

	private Texture oceanTexture;
	private float oceanX;
	private float oceanY;

	private Texture winMessageTexture;
	private float winMessageX;
	private float winMessageY;*/

	private Turtle turtle;
	private Starfish starfish;
	private SeaBackground ocean;
	private ActorBeta winMessage;
	private Whirlpool tempWhirlpool;
	private Fish fish;
	private boolean win;


	//Begin 2.1.

	private Button buttonRight;
	private Button buttonLeft;
	private Button buttonTop;
	private Button buttonBottom;
	private TextButton.TextButtonStyle textButtonStyle;
	//End 2.1.



	@Override
	public void initialize()
	{
		//Begin 2.1.
		Gdx.app.setLogLevel(Application.LOG_DEBUG);


		Gdx.input.setInputProcessor(mainStage);
		//End 2.1.




		ocean = new SeaBackground(0, 0,mainStage);

		starfish = new Starfish(380,380, mainStage);

		turtle = new Turtle(20,20, mainStage);

		tempWhirlpool = new Whirlpool(100, 100, mainStage);

		fish = new Fish(90, 90, mainStage);



		winMessage = new ActorBeta();
		winMessage.setTexture( new Texture(Gdx.files.internal("you-win.png")) );
		winMessage.setPosition( 180,180 );
		winMessage.setVisible( false );
		mainStage.addActor( winMessage );

		win = false;



		//Begin 2.1.
		FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("arial.ttf"));

		FreeTypeFontGenerator.FreeTypeFontParameter fontParameters = new FreeTypeFontGenerator.FreeTypeFontParameter();
		fontParameters.size = 48;
		fontParameters.color = Color.WHITE;
		fontParameters.borderWidth = 2;
		fontParameters.borderColor = Color.BLACK;
		fontParameters.borderStraight = true;
		fontParameters.minFilter = Texture.TextureFilter.Linear;
		fontParameters.magFilter = Texture.TextureFilter.Linear;

		BitmapFont customFont = fontGenerator.generateFont(fontParameters);

		textButtonStyle = new TextButton.TextButtonStyle();
		Texture buttonTex = new Texture( Gdx.files.internal("badlogic.jpg") );
		NinePatch buttonPatch = new NinePatch(buttonTex, 24,24,24,24);
		textButtonStyle.up = new NinePatchDrawable( buttonPatch );
		textButtonStyle.font = customFont;
		textButtonStyle.fontColor = Color.GRAY;

		buttonRight =  new TextButton( "RIGHT", textButtonStyle );
		buttonRight.setSize(20*4,50);
		buttonRight.setPosition(10*7,Gdx.graphics.getHeight()-20*3);
		//buttonRight.setPosition(10*7,Gdx.graphics.getHeight()*baseHRatio-20*3);
		buttonRight.addListener(new InputListener(){
			@Override
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log("#INFO", "Press a Button");
				turtle.setDirection(Turtle.IDLE);
			}
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log("#INFO", "Pressed Text Button");
				//3.5.
				turtle.setDirection(Turtle.RIGHT);
				return true;
			}
		});

		buttonLeft =  new TextButton( "LEFT", textButtonStyle );
		buttonLeft.setSize(20*4,50);
		buttonLeft.setPosition(10*7,Gdx.graphics.getHeight()-50*3);
		//buttonLeft.setPosition(10*7,Gdx.graphics.getHeight()*baseHRatio-50*3);
		buttonLeft.addListener(new InputListener(){
			@Override
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log("#INFO", "Press a Button");
				turtle.setDirection(Turtle.IDLE);
			}
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log("#INFO", "Pressed Text Button");
				//3.5.
				turtle.setDirection(Turtle.LEFT);
				return true;
			}
		});

		buttonTop =  new TextButton( "TOP", textButtonStyle );
		buttonTop.setSize(20*4,50);
		buttonTop.setPosition(10*7,Gdx.graphics.getHeight()-80*3);
		//buttonTop.setPosition(10*7,Gdx.graphics.getHeight()*baseHRatio-80*3);
		buttonTop.addListener(new InputListener(){
			@Override
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log("#INFO", "Press a Button");
				turtle.setDirection(Turtle.IDLE);
			}
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log("#INFO", "Pressed Text Button");
				//3.5.
				turtle.setDirection(Turtle.TOP);
				return true;
			}
		});

		buttonBottom =  new TextButton( "BOTTOM", textButtonStyle );
		buttonBottom.setSize(20*4,50);
		buttonBottom.setPosition(10*7,Gdx.graphics.getHeight()-110*3);
		//buttonBottom.setPosition(10*7,Gdx.graphics.getHeight()*baseHRatio-110*3);
		buttonBottom.addListener(new InputListener(){
			@Override
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log("#INFO", "Press a Button");
				turtle.setDirection(Turtle.IDLE);
			}
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log("#INFO", "Pressed Text Button");
				//3.5.
				turtle.setDirection(Turtle.BOTTOM);
				return true;
			}
		});

		mainStage.addActor(buttonRight);
		mainStage.addActor(buttonLeft);
		mainStage.addActor(buttonTop);
		mainStage.addActor(buttonBottom);
		//End 2.1.
	}

	/*@Override
	public void render()
	{
		//turtleX+=2;
		ScreenUtils.clear(0, 0, 0, 1);

		turtleRectangle.setPosition(turtleX, turtleY);

		if(turtleRectangle.overlaps(starfishRectangle))
			win = true;

		//Clear screen.
		//Gdx.gl.glClearColor(0,0,0, 1);
		//Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//Draw graphics.
		batch.begin();

		batch.draw( oceanTexture, oceanX, oceanY );

		if(!win)
			batch.draw( starfishTexture, starfishX, starfishY );

		batch.draw( turtleTexture, turtleX, turtleY );

		if(win)
			batch.draw( winMessageTexture, winMessageX, winMessageY );

		batch.end();


		//Begin 2.1.
		mainStage.draw();
		//End 2.1.
	}*/

	@Override
	public void update(float dt)
	{
		//Begin 2.1.
		if(turtle.overlaps(starfish) && !starfish.isCollected() )
		{
			Gdx.app.log("#INFO", "collision detected.");

			starfish.collect();

			Whirlpool whirl = new Whirlpool(0,0, mainStage);
			whirl.centerAtActor( starfish );
			//whirl.setOpacity(0.25f);
			BaseActor youWinMessage = new BaseActor(0,0,mainStage);
			youWinMessage.loadTexture("you-win.png");
			youWinMessage.centerAtPosition(400,300);
			youWinMessage.setOpacity(0);
			youWinMessage.addAction( Actions.delay(1) );
			youWinMessage.addAction( Actions.after( Actions.fadeIn(1) ) );
		}
		//End 2.1.
	}

	@Override
	public void dispose () {

	}
}

