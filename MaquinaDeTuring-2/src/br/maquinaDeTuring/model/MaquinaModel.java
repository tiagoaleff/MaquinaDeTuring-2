/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.maquinaDeTuring.model;

import br.maquinaDeTuring.object.CelulaObject;
import java.lang.reflect.Array;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author tiagoaleff
 */
public class MaquinaModel {
    
    private String fita; // fita inserida pelo usuario
    private ArrayList<String> historicoEstados; // mostra de forma tabular as estados percorridos (q1, $) -> (d,D,4)
    private MatrizModel matrizAcoes;
    private int estadoAtual;
    
    public MaquinaModel(MatrizModel matriz, String fita) {
        matrizAcoes = matriz;        
        this.fita = fita;
        estadoAtual = 0;             
        
        //this.fita  += "►";
    }
    
    
    public void executarAnaliseEmFita() {
        
        CelulaObject acao = new CelulaObject();        
        boolean fimDePrograma = true;        
        
        System.out.println(matrizAcoes.toString());
        
        acao = matrizAcoes.getAcao(String.valueOf(fita.charAt(0)), estadoAtual);        
                        
        String novoSimboloString = "";
        String auxilixarString = "";
        String direcaoString = "";
        int posicaoAtualFita = 0;
               
        while (!acao.isFimPrograma()) {        
                                                  
            estadoAtual = acao.getEstadoDestino();
            novoSimboloString = acao.getSimbolo();
            direcaoString = acao.getDirecao();               
                                   
            System.out.println("inicial: " + fita);
            System.out.println("estado atual: " + estadoAtual);
            System.out.println("novo simbolo: " + novoSimboloString);
            System.out.println("direcao: " + direcaoString);
            System.out.println("cabecote: " + posicaoAtualFita);
                        
            getNovaFita(novoSimboloString, posicaoAtualFita); // atualiza a fita                                    
            
            System.out.println("valor da coluna: " + getCaracterFita(posicaoAtualFita) + " linha: " + estadoAtual);
            
            posicaoAtualFita = getPosicao(acao.getDirecao(), posicaoAtualFita); // atualiza a variavel antes de obter na matriz
            acao = matrizAcoes.getAcao(getCaracterFita(posicaoAtualFita), estadoAtual); // obtem a nova acao                                    
            System.out.println("final: " + fita);            
           
        }
        
        System.out.println(fita);
        JOptionPane.showMessageDialog(null, fita);
        JOptionPane.showMessageDialog(null, "fim do programa");
        
    }
    
    public String getCaracterFita (int posicaoAtualFita) {
        
        if (posicaoAtualFita >= fita.length() ) {
        
            System.out.println("Parou!");            
            JOptionPane.showMessageDialog(null, fita);
            System.exit(0);            
            fita += "_";                         
        } 
        
        return String.valueOf(fita.charAt(posicaoAtualFita));   
    }
    
    public void getNovaFita(String novoSimboloString, int posicao) {
        
        String auxiliar = "";
        
        for (int i = 0; i < fita.length(); i++) {
            
            if (i != posicao) {
                auxiliar += String.valueOf(fita.charAt(i));
            } else {
                auxiliar += novoSimboloString;
            }
                        
        }
        
        fita = auxiliar;
    }
                    
    public int getPosicao(String direcao, int posicao) {
        
        if (direcao.equals("Direita")) {
            return ++posicao;
        }
        return --posicao;
    }
                  
}
