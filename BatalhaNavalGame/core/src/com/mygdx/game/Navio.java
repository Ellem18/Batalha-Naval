package com.mygdx.game;

import java.util.Random;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.awt.Point;

public class Navio {

    private final Texture navioImg; // img do navio
    private final int tamanho; // tam do navio
    private int x, y; // pos do navio
    private boolean horizontal; // horizontal ou vertical
    private boolean ocultarnavio = true;
    private boolean cliqueCelula = false;
    public boolean ocultarNavio;

    public Navio(Texture navioImg, int tamanho) {
        this.navioImg = navioImg;
        this.tamanho = tamanho;
        this.horizontal = true;
        Random random = new Random();
        Point navioPosicao = new Point(random.nextInt(10 - tamanho + 1), random.nextInt(10));
        while (verificarPosicoesNavios(navioPosicao)) { // verifica se a posição já está ocupada
            navioPosicao = new Point(random.nextInt(10 - tamanho + 1), random.nextInt(10));
        }
        x = navioPosicao.x;
        y = navioPosicao.y;
        addVerificarPosicoesNavios(); // adiciona o navio ao mapa de posições ocupadas
    }
    

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void mostrarNavio() {
        ocultarnavio = false;
    }

    public void desenhar(SpriteBatch sprite, float PosicaoX, float PosicaoY) {
        if (!ocultarnavio) { // verifica se o navio está escondido
            // desenha o navio na tela
            float ImagemX = PosicaoX + x * BatalhaNaval.quadrado;
            float ImagemY = PosicaoY + y * BatalhaNaval.quadrado;
            if (!horizontal) {
                // se o navio estiver orientado verticalmente ele ajusta a posição de desenho
                ImagemX -= (navioImg.getHeight() - BatalhaNaval.quadrado) / 2.0f;
            }
            sprite.draw(navioImg, ImagemX, ImagemY, horizontal ? BatalhaNaval.quadrado * tamanho : navioImg.getHeight(), horizontal ? navioImg.getHeight() : BatalhaNaval.quadrado * tamanho);
        }
    }
    
    public void mudar() {
        // muda a horizontal para vertical e vice-versa
        horizontal = !horizontal;
    }


    private boolean verificarPosicoesNavios(Point verificaPosicao) { //ele ve se a posição gerada esta ocupada por outro navio
        for (int a = 0; a < tamanho; a++) {
            Point pontoNavio = horizontal ? new Point(verificaPosicao.x + a, verificaPosicao.y) : new Point(verificaPosicao.x, verificaPosicao.y + a);
            if (BatalhaNaval.armazenaPosicoes.containsKey(pontoNavio)) {
                return true;
            }
        }
        return false;
    }
    
    private void addVerificarPosicoesNavios() { //garante q os navios nao fiquem sobrepostos
        for (int b = 0; b < tamanho; b++) {
            Point pontoNavio = horizontal ? new Point(x + b, y) : new Point(x, y + b);
            BatalhaNaval.armazenaPosicoes.put(pontoNavio, this);
        }
    }

    public boolean isClique() {
        return cliqueCelula;
    }

    public void setClique(boolean cliqueCelula) {
        this.cliqueCelula = cliqueCelula;
    }

    public boolean Clique(int posicaoCliqueX, int posicaoCliqueY) {
        float ImagemNavioX = posicaoCliqueX - x * BatalhaNaval.quadrado;
        float ImagemNavioY = posicaoCliqueY - y * BatalhaNaval.quadrado;
        if (!horizontal) {
            ImagemNavioX -= (navioImg.getHeight() - BatalhaNaval.quadrado) / 2.0f;
        }
        return (ImagemNavioX >= 0 && ImagemNavioX<= navioImg.getWidth() && ImagemNavioY >= 0 && ImagemNavioY<= navioImg.getHeight());
    }
    
    


    
}
