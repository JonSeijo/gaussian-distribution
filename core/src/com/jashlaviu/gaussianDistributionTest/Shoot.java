package com.jashlaviu.gaussianDistributionTest;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Shoot {
	
	private float x, y;
	private float radius;
	
	private float mean;
	private float stdDev;
	private float defaultStd;
	
	private float red, green, blue, alpha;
	private float redMean, greenMean, blueMean, colorDeviation;
	
	private Shooter shooter;
	private Random gen;
		
	public Shoot(Shooter shooter){
		this.shooter = shooter;				
		gen = new Random();
		
		radius = 8;		
		// Where the majority of ball will be painted
		mean = Gdx.graphics.getHeight()/2 + 50;
		
		defaultStd = 30;
		stdDev = defaultStd;	
		
		alpha = 0.25f;
		
		// This represents the mean color in RGB way.
		redMean = 0.f/255.f;
		greenMean = 100.f/255.f;
		blueMean = 245.f/255.f;
		
		colorDeviation = 0.15f;
		generateColors();
	}
	
	public void update(){
		// x position is the same as shooter.x
		x = shooter.getCenterX() - radius;
		
		// generate y position in gaussian distribution.
		float r = (float)gen.nextGaussian();
		y = r * stdDev + mean;
	}
	
	public void draw(ShapeRenderer shapeRenderer){
		// Generate a new color each time a ball is painted
		generateColors(); 
		
		shapeRenderer.setColor(red, green, blue, alpha);		
		shapeRenderer.circle(x, y, radius);
	}
	
	/**
	 * Set new standard deviation.
	 * Default = 1.
	 * @param stdDev
	 */
	public void setStdDev(float stdDev){
		this.stdDev = stdDev;
	}
	
	public void generateColors(){
		//generates each one in gaussian distribution
		float r = (float)gen.nextGaussian();
		float g = (float)gen.nextGaussian();
		float b = (float)gen.nextGaussian();
		
		red = r * colorDeviation + redMean;		
		green = g * colorDeviation + greenMean;
		blue = b * colorDeviation + blueMean;
		
	}
	
	public void dispose(){
		
	}

	public float getDefaultStd() {
		return defaultStd;
	}

}
