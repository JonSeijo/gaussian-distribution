package com.jashlaviu.gaussianDistributionTest.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.jashlaviu.gaussianDistributionTest.GaussianTest;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1200;
		config.height = 800;
		config.resizable = false;
		new LwjglApplication(new GaussianTest(), config);
	}
}
