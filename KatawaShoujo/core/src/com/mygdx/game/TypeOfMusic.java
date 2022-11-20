package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class TypeOfMusic {
    public Music musicSound;
    public long musicId = 0;
    private String music;

    //Выбор саундтрека
    public void music(String location) {
        this.music = location;
        if(music.equals("MainMenu")) {
            musicSound = Gdx.audio.newMusic(Gdx.files.internal("Sound/MainMenu.mp3"));
        }
        playMusic(musicSound);
    }

    //Проигрывание саундтрека
    public void playMusic(Music soundMainMenu){
        soundMainMenu.play();
        soundMainMenu.setLooping(true);
    }
}
