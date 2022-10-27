package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class Options implements Screen {

    final GameNovella game;
    private TextButton.TextButtonStyle buttonStyle;
    private TextButton backButton;
    private OrthographicCamera camera;
    private Texture background;
    private Stage stage;
    private CheckBox.CheckBoxStyle checkBoxStyle;
    private CheckBox fullScreen;
    private TextureRegionDrawable textureSlider;
    private Slider.SliderStyle sliderStyle;
    private Slider sound;
    private TextureRegionDrawable textureBar;
    private ProgressBar.ProgressBarStyle barStyle;
    private ProgressBar bar;
    private boolean fullscreenSwitch;
    Skin skinSlider;
    Skin skinCheckBox;

    public Options(final GameNovella game){
        this.game = game;

        background = new Texture(Gdx.files.internal("menuBackground.png"));

        camera = new OrthographicCamera();
        camera.setToOrtho(false, game.WIDTH, game.HEIGHT);


        stage = new Stage(new StretchViewport(game.WIDTH, game.HEIGHT));

        Gdx.input.setInputProcessor(stage);


        /*
        skinSlider = new Skin();
        Pixmap pixmap = new Pixmap(10, 10, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skinSlider.add("white", new Texture(pixmap));

        textureBar = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("barGreen_horizontalMid.png"))));
        barStyle = new ProgressBar.ProgressBarStyle(skinSlider.newDrawable("white", Color.DARK_GRAY), textureBar);
        bar = new ProgressBar(0, 10, 0.5f, false, barStyle);
        bar.setPosition(100, 100);
        bar.setSize(290, bar.getPrefHeight());
        bar.setAnimateDuration(2);
        stage.addActor(bar);


         */


        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("fullScreen_checkBox.pack"));
        skinCheckBox = new Skin();

        skinCheckBox.addRegions(atlas);
        skinCheckBox.add("font",game.comicSans,BitmapFont.class);

        fullscreenSwitch = game.fullScreenMode;

        checkBoxStyle = new CheckBox.CheckBoxStyle();
        checkBoxStyle.font = game.comicSans;
        checkBoxStyle.fontColor = Color.valueOf("#8E8574");
        if(!fullscreenSwitch) {
            checkBoxStyle.up = new NinePatchDrawable(skinCheckBox.getPatch("unchecked_box"));
            checkBoxStyle.down = new NinePatchDrawable(skinCheckBox.getPatch("checked_box"));
        }else{
            checkBoxStyle.up = new NinePatchDrawable(skinCheckBox.getPatch("checked_box"));
            checkBoxStyle.down = new NinePatchDrawable(skinCheckBox.getPatch("unchecked_box"));
        }

        fullScreen = new CheckBox("", checkBoxStyle);
        fullScreen.setChecked(fullscreenSwitch);
        fullScreen.setPosition(135,200);
        fullScreen.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                fullscreenSwitch = fullScreen.isChecked();

                if(fullscreenSwitch){
                    checkBoxStyle.up = new NinePatchDrawable(skinCheckBox.getPatch("checked_box"));
                    checkBoxStyle.down = new NinePatchDrawable(skinCheckBox.getPatch("unchecked_box"));
                    Graphics.DisplayMode currentMode = Gdx.graphics.getDisplayMode();
                    Gdx.graphics.setFullscreenMode(currentMode);
                    game.fullScreenMode = true;

                }else{
                    checkBoxStyle.up = new NinePatchDrawable(skinCheckBox.getPatch("unchecked_box"));
                    checkBoxStyle.down = new NinePatchDrawable(skinCheckBox.getPatch("checked_box"));
                    Gdx.graphics.setWindowedMode(game.WIDTH, game.HEIGHT);
                    game.fullScreenMode = false;
                }



            }

        });

        buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = game.comicSans;
        buttonStyle.fontColor = Color.valueOf("#8E8574");

        backButton= new TextButton("Назад",buttonStyle);
        backButton.setPosition(135, 80);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenuScreen(game));
                dispose();
            }
        });
        stage.addActor(fullScreen);
        stage.addActor(backButton);
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        camera.update();

        game.batch.begin();
        game.batch.draw(background, 0, 0, game.WIDTH, game.HEIGHT);
        game.batch.end();

        if(stage != null){
            stage.act(Gdx.graphics.getDeltaTime());
            stage.draw();
        }
        game.batch.begin();
        game.comicSans.setColor( Color.BROWN);
        game.comicSans.getData().setScale(1,1);
        game.comicSans.draw(game.batch, game.madeInProgress, game.WIDTH/2 - 130, game.HEIGHT/2 + 20);
        game.batch.end();


        game.batch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void resize(int width, int height) {

        if(stage != null)stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        background.dispose();
        stage.dispose();
    }
}
