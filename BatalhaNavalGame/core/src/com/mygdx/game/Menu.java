package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;

public class Menu extends ScreenAdapter {

    private final MyGdxGame principal; // ref para a classe principal do jogo
    private OrthographicCamera camera; //  visualiza a tela - tamanho e etc
    public static SpriteBatch sprite; // desenha as coisas na tela
    private BitmapFont fonte; // fonte para oq for escrito na tela
    private Texture imgMenu; // imagem q fica no fundo da tela

    public Menu(MyGdxGame principal) {
        this.principal = principal;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, MyGdxGame.telaLargura, MyGdxGame.telaAltura);
        sprite = new SpriteBatch();
        fonte = new BitmapFont();
        imgMenu = new Texture(Gdx.files.internal("menu.png"));
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        sprite.setProjectionMatrix(camera.combined);
        sprite.begin();
        sprite.draw(imgMenu, 0, 0, MyGdxGame.telaLargura, MyGdxGame.telaAltura);
        fonte.draw(sprite, "Aperte Enter para o jogo começar", MyGdxGame.telaLargura/ 2, MyGdxGame.telaAltura * 0.5f, 0, Align.center, false);
        sprite.end();
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            principal.setScreen(new TrocaTela(principal)); //Aqui faz mudar de tela
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        fonte.dispose();
        imgMenu.dispose();
        sprite.dispose();
        
    }
}
