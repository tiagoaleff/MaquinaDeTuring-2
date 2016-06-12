/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.maquinaDeTuring.object;

/**
 *
 * @author tiagoaleff
 */
public class CelulaObject {
    
    private char simbolo; // simbolo que será gravado na fina
    private char estadoDestino; // estado que será direcionado a máquino 
    private char direcao; // lado que será lido na tabela

    public char getSimbolo() {
        if (simbolo != ' ') 
            return simbolo;
        return ' ';
    }

    public void setSimbolo(char simbolo) {
        this.simbolo = simbolo;
    }

    public char getEstadoDestino() {
        if (estadoDestino != ' ') 
            return estadoDestino;
        return ' ';
    }

    public void setEstadoDestino(char estadoDestino) {
        this.estadoDestino = estadoDestino;
    }

    public char getDirecao() {
        if (direcao != ' ') 
            return direcao;
        return ' ';
    }

    public void setDirecao(char direcao) {
                
        this.direcao = direcao;
    }
    
    public CelulaObject getCelula(){
        return this;
    }
    
    @Override
    public String toString(){
        return String.format("Direção: %c. Simbolo: %c. Estado: %c", 
                getDirecao(), getSimbolo(), getEstadoDestino());
    }
            
}
