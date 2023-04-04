package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;



public class MyGdxGame extends Game {

    public static final int telaLargura = 800; //Largura da tela
    public static final int telaAltura  = 600; //Altura da tela
    Texture imgNavio; // Imagem do nav
    private Music OST; //Musica do jogo
    private Menu menu;

    @Override
    public void create() {
        OST = Gdx.audio.newMusic(Gdx.files.internal("assets/gameOst.ogg")); //referenciando a trilha 
        OST.setLooping(true); //para fazer loop
        OST.play(); //Começa a tocar
        menu = new Menu(this); //chama menu
        setScreen(menu); //mudar de tela 

      

    }
    
    @Override
    public void dispose() {
        OST.dispose();
        super.dispose();
        menu.dispose();
    }
}
