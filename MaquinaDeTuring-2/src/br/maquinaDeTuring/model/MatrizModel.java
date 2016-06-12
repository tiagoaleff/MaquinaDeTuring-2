/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.maquinaDeTuring.model;

import br.maquinaDeTuring.object.CelulaObject;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JTable;

/**
 *
 * @author tiagoaleff
 */
public class MatrizModel {
    
    private ArrayList<String> listaSimbolos;
    private int totalEstados;
    private CelulaObject [][] matrizDeAcoes;
    
    public MatrizModel (ArrayList<String> listaSimbolos, int linhas) {
        
        this.listaSimbolos = listaSimbolos;
        this.totalEstados = linhas;
                
    }
    
    public void setMatrizAcoes(JTable tabelaDeAcoes) {
        
        // numeros de linhas superior
        int linhasColunas = listaSimbolos.size() + 1; // + 1 porcausa do simbolo inicial

        CelulaObject celula;
        matrizDeAcoes = new CelulaObject[totalEstados][linhasColunas];
                      
        int totalDeColunas = tabelaDeAcoes.getColumnCount();        
        linhasColunas++;
        // adiciona os valores da tabela na matriz
        for (int i = 0; i < totalEstados; i ++) {
            
            for (int j = 1; j < linhasColunas; j ++) {
                                             
                celula = formatarCampoComMascara( 
                        String.valueOf(tabelaDeAcoes.getValueAt(i, j)));
                
                System.out.println(celula.toString());
                System.out.printf("i: %d - j: %d", i, j);
                System.out.println("");
                
                matrizDeAcoes[i][j - 1] = celula;                                              
            }
                                         
        }                            
    }
    
    public CelulaObject getAcao (String simboloEntrada, int estado) {
        
        CelulaObject celula = new CelulaObject();
        
        int coluna = listaSimbolos.lastIndexOf(simboloEntrada);
        
        
        
        if (matrizDeAcoes[coluna][estado] == null) {
            
            System.out.println("Posicao nao existe");
            System.exit(0);
            
        }        
        matrizDeAcoes[coluna][estado].getCelula();
        return celula;
    }
    
    @Override
    public String toString () {
        
        String toString = "";
        int coluna = 0;
        
        for (CelulaObject [] t : matrizDeAcoes) {
                                    
            for (CelulaObject i : t) {
                
                System.out.printf("%c - %c - %c \t" ,
                        i.getDirecao(), i.getSimbolo(), i.getEstadoDestino());                                                
            }                           
            System.out.println();
        }                

        return toString;        
    }
    
    public CelulaObject formatarCampoComMascara(String campo) {
                       
       // System.out.println("teste");
        CelulaObject celula = new CelulaObject();        
                
        celula.setDirecao(campo.charAt(0));
        celula.setSimbolo(campo.charAt(4));
        celula.setEstadoDestino(campo.charAt(8));            
       
        return celula;
        
    }    
        
}
