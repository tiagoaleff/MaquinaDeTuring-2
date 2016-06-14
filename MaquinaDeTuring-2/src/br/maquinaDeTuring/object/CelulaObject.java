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
    
    private String simbolo; // simbolo que será gravado na fina
    private String estadoDestino; // estado que será direcionado a máquino 
    private String direcao; // lado que será lido na tabela    
    private boolean fimPrograma; // variavel testa se o programa acabou
    

    public CelulaObject() {        
        fimPrograma = false;
        simbolo = "";
        estadoDestino = "0";
        direcao = "";                
    }
        
    public String getSimbolo() {
        if (simbolo == null || simbolo.trim().equals(""))             
            return "";
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
                
        if (simbolo == null || simbolo.trim().equals("")) {
            return;
        }
        this.simbolo = simbolo;                    
        
    }

    public int getEstadoDestino() {
        
        if (estadoDestino == null || estadoDestino.trim().equals("") || estadoDestino.equals("null")) {
            return 0;
        }                    
        if (isFimPrograma()) {
            return 0;
        }                
        return Integer.parseInt(estadoDestino);        
    }

    public void setEstadoDestino(String estadoDestino) {
        
        if (estadoDestino != null) 
            this.estadoDestino = estadoDestino;
                        
        if (estadoDestino.equals("■")) {
            this.estadoDestino = "";
            setFimPrograma("fim");
        } 
    }

    public String getDirecao() {
        
        if (direcao == null || direcao.trim().equals(""))         
            return " ";    
        return direcao;
    }

    public void setDirecao(String direcao) {                        
        this.direcao = direcao;
    }
    
    public CelulaObject getCelula(){
        
        if (this == null) 
            return null;
        return this;
    }        

    public boolean isFimPrograma() {
        return fimPrograma;
    }

    public void setFimPrograma(String fim) {
        
        fimPrograma = false;
        
        if (fim.equals("fim")) {
            fimPrograma = true;
        }        
    }
            
    
    @Override
    public String toString(){
        return String.format("Direção: %s. Simbolo: %s. Estado: %s", 
                getDirecao(), getSimbolo(), getEstadoDestino());
    }
            
}
