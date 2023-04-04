package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TrocaTela extends ScreenAdapter {

    private final MyGdxGame principal; // referência para a classe principal do jogo
    private OrthographicCamera camera; // câmera para visualizar a tela
    private SpriteBatch sprite; // batch para desenhar elementos na tela
    private BatalhaNaval naval; // tabuleiro do jogador
    private EventoTela tela; 
    static Navio[] navios; // array de Navio
    private Texture imgNavio = new Texture(Gdx.files.internal("barco.jpg")); // carrega a imagem do navio
    
    
  

    public TrocaTela(MyGdxGame principal) {
        this.principal = principal;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, MyGdxGame.telaLargura, MyGdxGame.telaAltura);
        sprite = new SpriteBatch();
        naval = new BatalhaNaval();


        // cria um array de 10 Navios
        navios = new Navio[10];
        navios[0] = new Navio(imgNavio, 5);
        navios[1] = new Navio(imgNavio, 4);
        navios[2] = new Navio(imgNavio, 4);
        navios[3] = new Navio(imgNavio, 3);
        navios[4] = new Navio(imgNavio, 3);
        navios[5] = new Navio(imgNavio, 3);
        navios[6] = new Navio(imgNavio, 2);
        navios[7] = new Navio(imgNavio, 2);
        navios[8] = new Navio(imgNavio, 2);
        navios[9] = new Navio(imgNavio, 2);

         // Adiciona o listener para os eventos de clique na tela
         tela = new EventoTela(naval);
        Gdx.input.setInputProcessor(tela);
    }
   


    

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        sprite.setProjectionMatrix(camera.combined);
        sprite.begin();
        // desenha os tabuleiros do jogador
        naval.construir(sprite, 140, 30);
        for (Navio navio : navios) {
            navio.desenhar(sprite, 140, 30); //PARA NAO SAIR DO TABULEIRO
        }

     

        sprite.end();
    }
    @Override
    public void dispose() {
        super.dispose();
        sprite.dispose();
        principal.imgNavio.dispose();
        
    }
}


        