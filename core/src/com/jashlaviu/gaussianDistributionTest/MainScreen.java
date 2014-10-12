package com.jashlaviu.gaussianDistributionTest;

import javax.swing.JOptionPane;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class MainScreen implements Screen {
	
	private GaussianTest game;
	private Shooter shooter;
	private Shoot shoot;
	
	public MainScreen(GaussianTest game){
		this.game = game;
		
		shooter = new Shooter();
		shoot = new Shoot(shooter);

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
			
		// CONTROL: change velocity and restart.
		}else if(Gdx.input.isKeyJustPressed(Keys.CONTROL_LEFT)){
			changeVelocity();
			clearScreen();
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
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

}
