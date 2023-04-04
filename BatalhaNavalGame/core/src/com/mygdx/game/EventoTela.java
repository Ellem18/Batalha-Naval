package com.mygdx.game;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;


public class EventoTela implements InputProcessor {

    private Vector2 lastTouchPosition = new Vector2();
    private BatalhaNaval naval; // tabuleiro do jogador
    private Sound somAguaAtingida;
    private Sound somNavio;
   

    

    public EventoTela(BatalhaNaval naval) {
        
        this.naval = naval;
        somAguaAtingida = Gdx.audio.newSound(Gdx.files.internal("assets/aguaAtingida.mp3"));
        somNavio = Gdx.audio.newSound(Gdx.files.internal("assets/navioAbatido.mp3"));
    }


    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        
        lastTouchPosition.set(screenX, screenY);
    
               // Converte a posição do toque para as coordenadas da matriz do tabuleiro
               int x = (int) ((lastTouchPosition.x-60) / BatalhaNaval.quadrado);
               int y = (int) (lastTouchPosition.y / BatalhaNaval.quadrado);
       

        //So para verificar
    //System.out.println("Toque: " + lastTouchPosition.x + "," + lastTouchPosition.y);
    //System.out.println("Coordenadas: " + x + "," + y);
   

    
        // Verifica se as coordenadas estão dentro dos limites da matriz
        if (x >= 0 && x < 10 && y >= 0 && y < 10) {

            try {

                boolean AchouNavio = false;

                for (Navio navio : TrocaTela.navios) {
                    if (navio.Clique(screenX, screenY)) {
                        somNavio.play(2.0f);
                        navio.mostrarNavio();
                        navio.setClique(true);
                     AchouNavio = true;
                        break;
                    }
                }

                if (!AchouNavio) {
                    naval.clicadas[x][y] = true;
                    // Define a célula como clicada na posição correspondente ao toque
                    somAguaAtingida.play(1.0f);
                    
                
                    // Redesenha o tabuleiro com a imagem da bomba na célula clicada
                    naval.construir(Menu.sprite, 0, 0);

                    naval.verificarFimDoJogo();
                    

                }

                
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        }
    
        return true;
    }
    

    public Vector2 getLastTouchPosition() {
        return lastTouchPosition;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}