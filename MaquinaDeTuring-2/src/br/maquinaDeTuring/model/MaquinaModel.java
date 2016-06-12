/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.maquinaDeTuring.model;

import br.maquinaDeTuring.object.CelulaObject;
import java.lang.reflect.Array;
import java.util.ArrayList;

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
        
        System.out.println(matrizAcoes.toString());
        System.out.println(this.fita);
        
        
    }
    
    
    public void executarAnaliseEmFita() {
        
        CelulaObject acao;
        boolean fimDePrograma = false;
        int posicaoAtualFita = 0;
        
        while (fimDePrograma) {        
                        
            acao = matrizAcoes.getAcao(fita.substring(posicaoAtualFita, posicaoAtualFita + 1), 
                    estadoAtual);
            
            System.out.println(acao.toString());
            
            System.exit(0);
            
        }
        
    }
                  
}
