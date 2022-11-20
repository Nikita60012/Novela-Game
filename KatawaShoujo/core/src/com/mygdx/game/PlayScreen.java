package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class PlayScreen implements Screen {
    final Katawa game;
    private OrthographicCamera camera;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    public PlayScreen(final Katawa game){
        this.game = game;

    }

    @Override
    public void show() {
        map = new TmxMapLoader().load(Gdx.files.internal("maps/map.tmx").file().getAbsolutePath());
        renderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera();
    }

    @Override
    public void render(float delta) {
    Gdx.gl.glClearColor(0,0,0,1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.position.set(0, 00, 0);
        renderer.setView(camera);
    renderer.render();

    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
    dispose();
    }

    @Override
    public void dispose() {

    }
}
