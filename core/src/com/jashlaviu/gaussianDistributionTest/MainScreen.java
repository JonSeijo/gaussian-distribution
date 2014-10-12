package com.jashlaviu.gaussianDistributionTest;

import javax.swing.JOptionPane;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class MainScreen implements Screen {
	
	private boolean showText;
	
	private GaussianTest game;
	private Shooter shooter;
	private Shoot shoot;
	private BitmapFont font;
	
	public MainScreen(GaussianTest game){
		this.game = game;
		
		font = new BitmapFont();
		shooter = new Shooter();
		shoot = new Shoot(shooter);
		
		showText = true;  // Function to show inside "clearScreen()"

		clearScreen();
	}

	@Override
	public void render(float delta) {
		// Checks if keys are pressed and handles it
		processInput();
		
		// Texture draws
		game.batch.begin();		
		shooter.move(delta);
		shooter.draw(game.batch);

		game.batch.end();
		
		
		// Shapes updates and draws
		// Alpha transparency does not work. Need to enable this to make it working.
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		
		game.shapeRenderer.setAutoShapeType(true);
		game.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);  //Otherwise just the exterior will be draw
		
		shoot.update(); //Updates the random position and color.
		shoot.draw(game.shapeRenderer);
		
		game.shapeRenderer.end();		
		Gdx.gl.glDisable(GL20.GL_BLEND);

	}		

	@Override
	public void resize(int width, int height) {
		// Resizing is disabled
	}
	
	@Override
	// Disposes textures and batch. Frees memory.
	public void dispose() {
		game.batch.dispose();
		shooter.dispose();
		shoot.dispose();
	}
	
	private void processInput(){
		// SPACE: change standard deviation and restart.
		if(Gdx.input.isKeyJustPressed(Keys.SPACE)){
			changeStdDev();
			clearScreen();
			reposition();
			
		// CONTROL: change velocity and restart.
		}else if(Gdx.input.isKeyJustPressed(Keys.CONTROL_LEFT)){
			changeVelocity();
			clearScreen();
			reposition();
		}
		if(Gdx.input.isKeyJustPressed(Keys.TAB)){
			showText = !showText;
		}
	}
	


	private void changeStdDev() {		
		// Ask for user input in a text message
		String inputStd = JOptionPane.showInputDialog(null, "Enter the standard deviation \n" +
				"(default: " + shoot.getDefaultStd() + " )",
				"Standard Deviation", JOptionPane.QUESTION_MESSAGE);
		float sd;
		//Convert the input string to a float number
		try{
			sd = Float.parseFloat(inputStd);
		}catch(Exception ex){    
			// If input is invalid, the default value is passed
			sd = shoot.getDefaultStd();
		}
		
		shoot.setStdDev(sd);		
	}
	
	private void changeVelocity(){
			// Ask for user input in a text message
			String inputVel = JOptionPane.showInputDialog(null, "Enter the velocity \n" +
					"(Default: " + shooter.getDefaultVelocity() + " )",
					"Velocity", JOptionPane.QUESTION_MESSAGE);
			
			float v;
			//Convert the input string to a float number
			try{
				v = Float.parseFloat(inputVel);
			}catch(Exception ex){
				// If input is invalid, the default value is passed
				v = shooter.getDefaultVelocity();
			}
			
			shooter.setVelocity(v);
	}

	private void clearScreen(){
		// "Paints" the screen in white once.
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);	
		if(showText)
			showGui();		
	}
	
	private void showGui(){
		game.batch.begin();
		font.setColor(0f, 0.4f, .95f, .50f);
		font.draw(game.batch, "Standard Deviation: " + shoot.getStdDev(),
				5 , Gdx.graphics.getHeight() - 10);
		font.draw(game.batch, "Velocity: " + Math.abs(shooter.getVelocity()),
				5 , Gdx.graphics.getHeight() - 30);
		game.batch.end();
		
	}	
	
	private void reposition() {
		shooter.setX(10);		
	}

	@Override
	public void show() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

}
