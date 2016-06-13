/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.maquinaDeTuring.model;

import br.maquinaDeTuring.object.CelulaObject;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;
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
        
        int tamanhoDeListaDeSimbolos = listaSimbolos.size() + 1; // simbolo inicial somando
        CelulaObject celula = new CelulaObject(); // armazeno o valor dos campo das matriz
        
        //1- tamanho das linhas 2 - numero de colunas                
        matrizDeAcoes = new CelulaObject[totalEstados][tamanhoDeListaDeSimbolos];        
        
        // tabela do usuario
        int linhas = tabelaDeAcoes.getRowCount();
        int colunas = tabelaDeAcoes.getColumnCount();
        
        int linhaMatriz = 0;
        int colunaMatriz = 0;
                                        
        for (int i = 0; i < linhas; i++) {
            
            for (int j = 0; j < colunas; j++) {
                                
                /*celula.setEstadoDestino("");
                celula.setDirecao("");
                celula.setSimbolo("");                
                matrizDeAcoes[linhaMatriz][colunaMatriz] = celula;                       
                */
                if (j == 2) {                    
                    celula.setEstadoDestino(String.valueOf(tabelaDeAcoes.getValueAt(i, j)));                    
                }
                
                if (j == 3) {
                    
                    celula.setSimbolo(String.valueOf(tabelaDeAcoes.getValueAt(i, j)));
                }
                
                if (j == 4) {
                    
                    celula.setDirecao(String.valueOf(tabelaDeAcoes.getValueAt(i, j)));                    
                                        
                    if (colunaMatriz >= (tamanhoDeListaDeSimbolos - 1)) {
                        
                        matrizDeAcoes[linhaMatriz][colunaMatriz] = celula;                        
                        colunaMatriz = 0;
                        linhaMatriz++;
                       
                        
                    } else {
                       
                        matrizDeAcoes[linhaMatriz][colunaMatriz] = celula;                       
                        colunaMatriz++;
                    }
                    
                    celula = new CelulaObject();
                }                                   
            }
        }                                                              
    }
    
    public CelulaObject getAcao (String simboloEntrada, int estado) {
        
        CelulaObject celula = new CelulaObject();        
        listaSimbolos.add(0, "►");
        int coluna = listaSimbolos.indexOf(simboloEntrada);
        
        /*for (String i : listaSimbolos) {            
            System.out.println(i);
        }*/
                                        
        if (matrizDeAcoes[estado][coluna] == null) {
            
            System.out.println("Posicao nao existe");
            System.exit(0);
            
        }
                
        celula = matrizDeAcoes[estado][coluna].getCelula();        
        return celula;
    }
    
    @Override
    public String toString () {
        
        String toString = "";
        int coluna = 0;
        
        for (CelulaObject [] t : matrizDeAcoes) {
                                    
            for (CelulaObject i : t) {
                
                System.out.printf("%s - %s - %s \t" ,
                        i.getDirecao(), i.getSimbolo(), i.getEstadoDestino());                                                
            }                           
            System.out.println();
        }                

        return toString;        
    }
    
    public CelulaObject[][] getMatriz(){
        return matrizDeAcoes;
    }
              
}
