package com.libgdx.java;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import static com.libgdx.java.movement.stop;
import static com.libgdx.java.pocket.*;
import static com.libgdx.java.utils.Constants.*;

public class main extends ApplicationAdapter implements InputProcessor {

	boolean force=false,hit=false,player1hit=false,player2hit=false,balllstop=true;
	int Force=0;
	double angle;
	float posx,posy,disA,disB=20,power,mousex,mousey,time=0;
	private OrthographicCamera camera;
	boolean b0=true,b1=true,b2=true,b3=true,b4=true,b5=true,b6=true,b7=true,b8=true,b9=true,b10=true,b11=true,
	b12=true,b13=true,b14=true,b15=true;
	private Box2DDebugRenderer b2dr;
	balls ballmaker=new balls();
	World world;
	private SpriteBatch batch;
	private Texture background, board, striker, textureball1, textureball2, textureball3, textureball4, textureball5, textureball6,
			textureball7, table, textureball8, textureball9, textureball10, textureball11, textureball12,insidetray,
			textureball13, textureball14, textureball15,ballbox,cue1,cue2;
	private Sprite sprite1, sprite2,stick1,stick2,ballboximg;


//	public main() {
//	}

	@Override
	public void create() {
		Gdx.input.setInputProcessor(this);
		world = new World(new Vector2(0,0), false);
		world.setContactListener(new ListenerClass());
		b2dr = new Box2DDebugRenderer();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth()/PPM, Gdx.graphics.getHeight()/PPM);
		player = ballmaker.createBall(450,422,14,false,world,0);
		ground1 = createBox(408, 421, 0, 300, true);
		ground2 = createBox(1132, 421, 0, 300, true);
		ground3 = createBox(589, 600, 304, 0, true);
		ground4 = createBox(589, 241, 304, 0, true);
		ground5 = createBox(950, 601, 304, 0, true);
		ground6 = createBox(950, 241, 304, 0, true);
		balltray1=createBox(770,32,466,0,true);
		balltray2=createBox(770,1,466,0,true);
		balltray3=createBox(545,20,0,40,true);
		balltray4=createBox(993,20,0,40,true);
		ball1=ballmaker.createBall(850,422,14,false,world,1);
		ball2=ballmaker.createBall(880,407,14,false,world,2);
		ball3=ballmaker.createBall(880,437,14,false,world,3);
		ball4=ballmaker.createBall(910,392,14,false,world,4);
		ball5=ballmaker.createBall(910,422,14,false,world,5);
		ball6=ballmaker.createBall(910,452,14,false,world,6);
		ball7=ballmaker.createBall(940,377,14,false,world,7);
		ball8=ballmaker.createBall(940,407,14,false,world,8);
		ball9=ballmaker.createBall(940,437,14,false,world,9);
		ball10=ballmaker.createBall(940,467,14,false,world,10);
		ball11=ballmaker.createBall(970,362,14,false,world,11);
		ball12=ballmaker.createBall(970,392,14,false,world,12);
		ball13=ballmaker.createBall(970,422,14,false,world,13);
		ball14=ballmaker.createBall(970,452,14,false,world,14);
		ball15=ballmaker.createBall(970,482,14,false,world,15);
		striker = new Texture("ball0.png");
		//System.out.println(striker.getWidth());
		textureball1 = new Texture("ball1.png");
		textureball2 = new Texture("ball2.png");
		textureball3 = new Texture("ball3.png");
		textureball4 = new Texture("ball4.png");
		textureball5 = new Texture("ball5.png");
		textureball6 = new Texture("ball6.png");
		textureball7 = new Texture("ball7.png");
		textureball8 = new Texture("ball8.png");
		textureball9 = new Texture("ball9.png");
		textureball10 = new Texture("ball10.png");
		textureball11 = new Texture("ball11.png");
		textureball12 = new Texture("ball12.png");
		textureball13 = new Texture("ball13.png");
		textureball14 = new Texture("ball14.png");
		textureball15 = new Texture("ball15.png");
		ballbox=new Texture("Ballbox.png");
		insidetray=new Texture("InsideTray.png");
		cue1=new Texture("cuePurple.png");
		cue2=new Texture("cueGreen.png");
		batch = new SpriteBatch();
		background = new Texture("parquet.jpg");
		board = new Texture("poolTable.png");
		sprite1 = new Sprite(board);
		sprite1.setPosition(Gdx.graphics.getWidth() / 2 - sprite1.getWidth() / 2,
				Gdx.graphics.getHeight() / 2 - sprite1.getHeight() / 2);
		sprite2 = new Sprite(background);
		sprite2.setSize(1540, 845);
		sprite2.setPosition(0, 0);
		stick1=new Sprite(cue1);
		stick1.setPosition(200,100);
		stick1.setRotation(0);
		stick2=new Sprite(cue2);
		stick2.setPosition(200,100);
		stick2.setRotation(0);
		ballboximg=new Sprite(insidetray);
		ballboximg.setPosition(545,0);
		ballboximg.setSize(450,40);

	}

	@Override
	public void render() {
		inputUpdate(Gdx.graphics.getDeltaTime());
		time+=Gdx.graphics.getDeltaTime();
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

		sprite2.draw(batch);
		sprite1.draw(batch);
		ballboximg.draw(batch);
		if(b1)
			b1=check(ball1);
		batch.draw(textureball1, ball1.getPosition().x*PPM-(textureball1.getWidth()/2), ball1.getPosition().y*PPM-(textureball1.getWidth()/2), textureball1.getWidth(), textureball1.getHeight());
		if(b2)
			b2=check(ball2);
		batch.draw(textureball2, ball2.getPosition().x*PPM-(textureball2.getWidth()/2), ball2.getPosition().y*PPM-(textureball2.getWidth()/2), textureball2.getWidth(), textureball2.getHeight());
		if(b3)
			b3=check(ball3);
		batch.draw(textureball3, ball3.getPosition().x*PPM-(textureball3.getWidth()/2), ball3.getPosition().y*PPM-(textureball3.getWidth()/2), textureball3.getWidth(), textureball3.getHeight());
		if(b4)
			b4=check(ball4);
		batch.draw(textureball4, ball4.getPosition().x*PPM-(textureball4.getWidth()/2), ball4.getPosition().y*PPM-(textureball4.getWidth()/2), textureball4.getWidth(), textureball4.getHeight());
		if(b5)
			b5=check(ball5);
		batch.draw(textureball5, ball5.getPosition().x*PPM-(textureball5.getWidth()/2), ball5.getPosition().y*PPM-(textureball5.getWidth()/2), textureball5.getWidth(), textureball5.getHeight());
		if(b6)
			b6=check(ball6);
		batch.draw(textureball6, ball6.getPosition().x*PPM-(textureball6.getWidth()/2), ball6.getPosition().y*PPM-(textureball6.getWidth()/2), textureball6.getWidth(), textureball6.getHeight());
		if(b7)
			b7=check(ball7);

		batch.draw(textureball7, ball7.getPosition().x*PPM-(textureball7.getWidth()/2), ball7.getPosition().y*PPM-(textureball7.getWidth()/2), textureball7.getWidth(), textureball7.getHeight());
		if(b8)
			b8=check(ball8);

		batch.draw(textureball8, ball8.getPosition().x*PPM-(textureball8.getWidth()/2), ball8.getPosition().y*PPM-(textureball8.getWidth()/2), textureball8.getWidth(), textureball8.getHeight());
		if(b9)
			b9=check(ball9);

		batch.draw(textureball9, ball9.getPosition().x*PPM-(textureball9.getWidth()/2), ball9.getPosition().y*PPM-(textureball9.getWidth()/2), textureball9.getWidth(), textureball9.getHeight());
		if(b10)
			b10=check(ball10);
		batch.draw(textureball10, ball10.getPosition().x*PPM-(textureball10.getWidth()/2), ball10.getPosition().y*PPM-(textureball10.getWidth()/2), textureball10.getWidth(), textureball10.getHeight());
		if(b11)
			b11=check(ball11);
		batch.draw(textureball11, ball11.getPosition().x*PPM-(textureball11.getWidth()/2), ball11.getPosition().y*PPM-(textureball11.getWidth()/2), textureball11.getWidth(), textureball11.getHeight());
		if(b12)
			b12=check(ball12);

		batch.draw(textureball12, ball12.getPosition().x*PPM-(textureball12.getWidth()/2), ball12.getPosition().y*PPM-(textureball12.getWidth()/2), textureball12.getWidth(), textureball12.getHeight());
		if(b13)
			b13=check(ball13);
		batch.draw(textureball13, ball13.getPosition().x*PPM-(textureball13.getWidth()/2), ball13.getPosition().y*PPM-(textureball13.getWidth()/2), textureball13.getWidth(), textureball13.getHeight());
		if(b14)
			b14=check(ball14);

		batch.draw(textureball14, ball14.getPosition().x*PPM-(textureball14.getWidth()/2), ball14.getPosition().y*PPM-(textureball14.getWidth()/2), textureball14.getWidth(), textureball14.getHeight());
		if(b15)
			b15=check(ball15);

		batch.draw(textureball15, ball15.getPosition().x*PPM-(textureball15.getWidth()/2), ball15.getPosition().y*PPM-(textureball15.getWidth()/2), textureball15.getWidth(), textureball15.getHeight());
		if(b0)
		{
			boolean a=check(player);
			if(!a) {
				player.setLinearVelocity(0, 0);
				player.setTransform(450/ PPM, 422 / PPM, 0);
			}

		}
		batch.draw(striker, player.getPosition().x*PPM-(striker.getWidth()/2), player.getPosition().y*PPM-(striker.getWidth()/2), striker.getWidth(), striker.getHeight());
		//System.out.println(time);
		if((player1hit||player2hit) &&time>2)
		{
			balllstop=stop();
			//System.out.println(balllstop);
		}
		if(player1 && player1hit && balllstop && solid==0)
		{
			player1=false;
			player2=true;
			player1hit=false;
			//System.out.println("player1Solid");
			//System.out.println(solid+stripe+cue+eight);
			//System.out.println("Solid porenai");
			//System.out.println("player2marbe");
			STRIPE-=stripe;
		}
		else if((player1 && player1hit && balllstop && solid!=0))
		{
			//System.out.println("player1solid");
			//System.out.println(solid+" points");
			//System.out.println("player1again");
			player1hit=false;
			point1+=solid;
			SOLID-=solid;
			STRIPE-=STRIPE;
		}
		 if(player2 &&player2hit&& balllstop && stripe==0)
		{
			player2=false;
			player1=true;
			player2hit=false;
			//System.out.println("player2Stripe");
			//System.out.println(solid+stripe+cue+eight);
			//System.out.println(stripe);
			//System.out.println("player1marbe");
			SOLID-=solid;

		}
		 else if(player2 &&player2hit&& balllstop && stripe!=0)
		 {
			 //System.out.println("player2stripe");
			 //System.out.println(stripe+" points");
			 //System.out.println("player2again");
			 player2hit=false;
			 point2+=stripe;
			 SOLID-=solid;
			 STRIPE-=stripe;
		 }
		if(balllstop||time<0.2) {

			if(player1)
			  stick1.draw(batch);
			else
			  stick2.draw(batch);
		}
		batch.draw(ballbox,530,0);

		batch.end();
		//b2dr.render(world, camera.combined);
		world.step(1 / 60f, 6, 2);
		camera.update();
		if(balllstop) {
			solid = 0;
			stripe = 0;
			cue = 0;
			eight = 0;
		}
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void dispose() {
		world.dispose();
		b2dr.dispose();
		batch.dispose();
		background.dispose();
		board.dispose();
		textureball1.dispose();
		textureball2.dispose();
		textureball3.dispose();
		textureball4.dispose();
		textureball5.dispose();
		textureball6.dispose();
		textureball7.dispose();
		textureball8.dispose();
		textureball9.dispose();
		textureball10.dispose();
		textureball11.dispose();
		textureball12.dispose();
		textureball13.dispose();
		textureball14.dispose();
		textureball15.dispose();
		wavSound1.dispose();
		wavSound2.dispose();
		//hitweak.dispose();
	}

	public void inputUpdate(float delta) {
		int horizontalForce = 0;
		if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
			//System.out.println(Gdx.input.getX()+" "+(HEIGHT-1-Gdx.input.getY()));
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN))
			player.applyForceToCenter(0, -50, false);
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
			player.applyForceToCenter(-50, 0, false);
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
			player.applyForceToCenter(50, 0, false);
		if (Gdx.input.isKeyJustPressed(Input.Keys.UP))
			player.applyForceToCenter(0, 50, false);
		if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)&&hit) {
			power=5;
			Force+=3;
			int x1,y1;
			x1= (int) (player.getPosition().x*PPM);
			y1=(int)(player.getPosition().y*PPM);
			disA= (float) Math.sqrt((y1-mousey)*(y1-mousey)+(x1-mousex)*(x1-mousex))+20;
			disB+=power;
			disA+=disB;
			posx=(disA*x1-disB*mousex)/(disA-disB);
			posy=(disA*y1-disB*mousey)/(disA-disB);
			stick1.setPosition((int)posx,(int)posy);
			stick2.setPosition((int)posx,(int)posy);
		}

}


	public Body createBox(int x, int y, int width, int height, boolean isStatic) {
		Body pBody;
		BodyDef def = new BodyDef();
		if (isStatic)
			def.type = BodyDef.BodyType.StaticBody;
		else
			def.type = BodyDef.BodyType.DynamicBody;

		def.position.set(x/PPM, y/PPM);
		def.fixedRotation = true;
		pBody = world.createBody(def);
		PolygonShape shape = new PolygonShape();
		shape.setAsBox((width / 2)/PPM, (height / 2)/PPM);
		pBody.createFixture(shape, 10f);
		pBody.setUserData("Wall");
		shape.dispose();
		return pBody;
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (button == Input.Buttons.RIGHT) {
			stick1.setPosition(-500,-500);
			stick2.setPosition(-500,-500);
		}
		if(button==Input.Buttons.LEFT)
		{
			if(!hit) {
				if(player1) {
					stick1.setPosition(player.getPosition().x * PPM, player.getPosition().y * PPM);
					//stick2.setPosition(-500,-500);
					stick2.setPosition(player.getPosition().x * PPM, player.getPosition().y * PPM);
				}
				else
				{
					//stick1.setPosition(-500,-500);
					stick1.setPosition(player.getPosition().x * PPM, player.getPosition().y * PPM);
					stick2.setPosition(player.getPosition().x * PPM, player.getPosition().y * PPM);
				}
			power = 0;
			disB = 20;
			hit = true;
		}
			else if(hit && Force>0) {
				//System.out.println(point1 +" "+point2);
				if(Force>180)
					Force=180;
				Sound hitmedium = Gdx.audio.newSound(Gdx.files.internal("sounds/ball_hit_medium.wav"));
				Sound hithard=Gdx.audio.newSound(Gdx.files.internal("sounds/ball_hit_hard.wav"));
				Sound hitweak=Gdx.audio.newSound(Gdx.files.internal("sounds/ballhitweak.wav"));
				float dX=mousex-player.getPosition().x*PPM;
				float dY=mousey-player.getPosition().y*PPM;
				float forceX=(Math.abs(dX)+Math.abs(dY))/(Math.abs(dY));
				float forceY=(Math.abs(dX)+Math.abs(dY))/(Math.abs(dX));
				float total=Math.abs(forceX)+Math.abs(forceY);
				forceX=forceX/total;
				forceY=forceY/total;
				if(dX<0)
					forceX*=-1;
				if(dY<0)
					forceY*=-1;
				player.applyForceToCenter(forceX*Force, forceY*Force, false);
				if(Force<50)
					hitweak.play();
				if(Force<100)
					hitmedium.play();
				else
					hithard.play();

				if(player1) {
					stick1.setPosition(player.getPosition().x * PPM, player.getPosition().y * PPM);
					//stick2.setPosition(-500,-500);
					stick2.setPosition(player.getPosition().x * PPM, player.getPosition().y * PPM);

					player1hit = true;
					player2hit=false;
					balllstop=false;
					time=0;
				}
				else
				{
					//stick1.setPosition(-500,-500);
					stick1.setPosition(player.getPosition().x * PPM, player.getPosition().y * PPM);
					stick2.setPosition(player.getPosition().x * PPM, player.getPosition().y * PPM);
					player2hit = true;
					player1hit=false;
					balllstop=false;
					time=0;

				}
				hit=false;
				Force=0;
			}
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		mousex=screenX;
		mousey=HEIGHT-1-screenY;
		int x2,y2;
		x2= (int) (player.getPosition().x*PPM);
		y2=(int)(player.getPosition().y*PPM);
		angle=Math.atan((y2-mousey)/(x2-mousex));
		angle=Math.toDegrees(angle);
		if(mousex<x2) {
			stick1.setRotation((float) angle);
			stick2.setRotation((float) angle);

		}
		else if(mousex>x2) {
			stick1.setRotation(180 - (float) -angle);
			stick2.setRotation(180 - (float) -angle);

		}

		stick1.setOrigin(0,0);
		stick2.setOrigin(0,0);

		return false;
	}

	@Override
	public boolean scrolled(int amount)

	{
		return false;
	}


}
