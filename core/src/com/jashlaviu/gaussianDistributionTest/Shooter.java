package com.jashlaviu.gaussianDistributionTest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Shooter {
	
	private float x, y, centerX;
	
	private float velocity, defaultVelocity;
	private float width, windowWidth;
	
	private boolean isMoving;

	private Texture texture;
	private Texture back;
	
	
	public Shooter(){
		texture = new Texture(Gdx.files.internal("shooter.png"));
		back = new Texture(Gdx.files.internal("pixel1.png"));		
		windowWidth = Gdx.graphics.getWidth();
		
		x = 10;
		y = 10;
		width = texture.getWidth();		
		centerX = width/2;
		isMoving = true;
		
		defaultVelocity = 8;
		velocity = defaultVelocity;

	}
	
	public void move(float delta){
		if(isMoving){
			addX(velocity);
			if(x >= windowWidth - width - 10 || x <= 10){
				velocity = -velocity;
			}
		}
	}
	
	public void draw(SpriteBatch batch){
		batch.draw(back, 0, 0, windowWidth, texture.getHeight()+10);
		batch.draw(texture, x, y);		
	}	
	
	public void addX(float amount){
		x += amount;
		centerX = x + width/2;
	}
	
	public float getCenterX(){
		return centerX;
	}
	
	public void setVelocity(float v){
		velocity = v;
	}
	
	public void dispose(){
		texture.dispose();
		back.dispose();
	}
	
	public float getDefaultVelocity(){
		return defaultVelocity;
	}

}
