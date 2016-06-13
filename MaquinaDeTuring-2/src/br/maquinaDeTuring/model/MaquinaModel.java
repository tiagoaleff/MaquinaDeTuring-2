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
        
        CelulaObject acao;        
        boolean fimDePrograma = true;
        int posicaoAtualFita = 0;
                       
        acao = matrizAcoes.getAcao(String.valueOf(fita.charAt(0)), estadoAtual);        
                
        String novoSimboloString = "";
        String auxilixarString = "";
        
        while (fimDePrograma) {        
                        
            if (acao.isFimPrograma()) {
                fimDePrograma = false;
                System.out.println("fim do programa");
                break;
            }
            novoSimboloString = acao.getSimbolo();
            estadoAtual = acao.getEstadoDestino();            
            fita = getNovaFita(novoSimboloString, posicaoAtualFita);                                    
            posicaoAtualFita = getPosicao(acao.getDirecao(), posicaoAtualFita);                        
                                                                     
        }
        
        System.out.println(fita);
        JOptionPane.showMessageDialog(null, "fim do programa");
        
    }
    
    public String getNovaFita(String novoSimboloString, int posicao) {
        
        String auxiliar = "";
        
        for (int i = 0; i < fita.length(); i++) {
            
            if (i != posicao) {
                auxiliar += String.valueOf(fita.charAt(i));
            } else {
                auxiliar += novoSimboloString;
            }
                        
        }
        
        return auxiliar;        
    }
                    
    public int getPosicao(String direcao, int posicao) {
        
        if (direcao.equals("Direita")) {
            return ++posicao;
        }
        return --posicao;
    }
                  
}
