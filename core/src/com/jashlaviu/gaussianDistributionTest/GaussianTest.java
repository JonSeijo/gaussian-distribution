package com.jashlaviu.gaussianDistributionTest;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class GaussianTest extends Game {
	public SpriteBatch batch;
	public ShapeRenderer shapeRenderer;
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		
		this.setScreen(new MainScreen(this));
		
	}

	@Override
	public void render () {
		super.render();
		
	}
}
