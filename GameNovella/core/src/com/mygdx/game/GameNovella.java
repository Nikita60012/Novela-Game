package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class GameNovella extends Game {

	SpriteBatch batch;

	BitmapFont comicSans;
	final String madeInProgress = "В процессе разработки";
	final int WIDTH = 1280;
	final int HEIGHT = 720;
	boolean fullScreenMode = false;

	@Override
	public void create () {
		batch = new SpriteBatch();
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/FontComicSans.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 32;
		parameter.characters = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЗЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя.,!?-";
		comicSans = generator.generateFont(parameter);
		generator.dispose();

		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {super.render();}

	@Override
	public void dispose () {
		super.dispose();
		batch.dispose();
		comicSans.dispose();
	}
}
