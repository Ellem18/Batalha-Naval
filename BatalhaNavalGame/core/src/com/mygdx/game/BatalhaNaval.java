package com.mygdx.game;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.awt.Point;



public class BatalhaNaval {

    public static final int quadrado = 55; // tamanho das celulas 
    //public static final int tabuleiro = quadrado * 10; 
    final boolean[][] clicadas; // matriz para controlar células já clicadas
    int a, b;
    private final Texture imgMar;
    private final Texture imgBomba;
    MyGdxGame game;

  


    final float[][] campo; // matriz para armazenar os estados das células do tabuleiro
    public static Map<Point, Navio> armazenaPosicoes = new HashMap<Point, Navio>();
    

   
  

    public BatalhaNaval() {



        campo = new float[10][10];
        clicadas = new boolean[10][10];
        // inicializa a campo com 0
        for (a = 0; a < 10; a++) {      
            for (b = 0; b < 10; b++) {
                campo[a][b] = 0; //celula vazia
                clicadas[a][b] = false;
                
                
            }
        }

        imgMar = new Texture("maar.jpg");
        imgBomba = new Texture("bomba.jpg");

       
    }

  public void construir(SpriteBatch sprite, float x, float y) {
    for (int a = 0; a < 10; a++) {
        for (int b = 0; b < 10; b++) {
            if (clicadas[a][b]) { // verifica se a célula foi clicada
                sprite.draw(imgBomba, x + b * quadrado, y + (9 - a) * quadrado);
            } else {
                sprite.draw(imgMar, x + b * quadrado, y + (9 - a) * quadrado);
            }
        }
    }
}

    

    public float getQuadrado(int x, int y) {
        // retorna o estado da célula na posição x e y
        if (x < 0 || x > 9 || y < 0 || y > 9) {
            // se a posição estiver fora do tabuleiro, retorna -1
            return -1;
        } else {
            return campo[x][y];
        }
    }

    public void setQuadrado(int x, int y, float modo) {
        // define o modo da célula na posição x e y
        if (x >= 0 && x <= 9 && y >= 0 && y <= 9) {
            campo[x][y] = modo;
        }
    }

    public void verificarFimDoJogo() {
        for (Navio navio : TrocaTela.navios) {
            if (!navio.ocultarNavio) {
                // se algum navio ainda não foi destruído, o jogo ainda não acabou
                return;
            }
        }
        // todos os navios foram destruídos, exibir tela de parabéns
        TelaParabens parabens = new TelaParabens(this);
        game.setScreen(parabens);
    }
    
    
}
