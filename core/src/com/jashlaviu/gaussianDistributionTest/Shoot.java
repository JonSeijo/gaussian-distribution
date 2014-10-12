package com.jashlaviu.gaussianDistributionTest;

import java.util.Random;

import com.badlogic.gdx.Gdx;
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
		
		// Where the majority of balls will be painted, mean of the gaussian distribution
		mean = Gdx.graphics.getHeight()/2 + 50;
		
		defaultStd = 30;		// Standard deviation
		stdDev = defaultStd;  	
		
		alpha = 0.25f;	// Transparency. Lower value = more transparency.
		
		// This represents the mean color in RGB way.
		redMean = 0.f/255.f;
		greenMean = 100.f/255.f;  //0.39215..   to fit in range (0, 1);
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
	
	// Set a new standard deviation
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
	
	public float getStdDev(){
		return stdDev;
	}
	

}
