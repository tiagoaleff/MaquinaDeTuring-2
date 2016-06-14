/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.maquinaDeTuring.model;

import br.maquinaDeTuring.object.CelulaObject;
import br.maquinaDeTuring.view.MaquinaDeTuringView;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;

/**
 *
 * @author tiagoaleff
 */
public class MaquinaModel implements Runnable{
    
    private String fita; // fita inserida pelo usuario
    private ArrayList<String> historicoEstados; // mostra de forma tabular as estados percorridos (q1, $) -> (d,D,4)
    private MatrizModel matrizAcoes;
    private int estadoAtual;
    private MaquinaDeTuringView frame;
    
    public MaquinaModel(MatrizModel matriz, String fita, MaquinaDeTuringView frame) {
        matrizAcoes = matriz;        
        this.fita = fita;
        estadoAtual = 0;                 
        this.frame = frame;                        
    }
    
    
    @Override
    public void run() {
     
        try {
            executarAnaliseEmFita();
        } catch (InterruptedException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage());
        }
        
    }
    
    
    public synchronized void executarAnaliseEmFita() throws InterruptedException {
                            
        CelulaObject acao = new CelulaObject();        
        boolean fimDePrograma = true;        
        
        System.out.println(matrizAcoes.toString());
        
        acao = matrizAcoes.getAcao(String.valueOf(fita.charAt(0)), estadoAtual);        
                        
        String novoSimboloString = "";
        String auxilixarString = "";
        String direcaoString = "";
        int posicaoAtualFita = 0;
        String historico = ""; // (estado lido, simbolo lido) -> (estado destino, simbolo gravado, direcao)                                      
     
        for (Component  i :frame.getComponents()) {
            i.repaint();
        }        
                
        while (!acao.isFimPrograma()) {        
                                             
            
            estadoAtual = acao.getEstadoDestino();
            novoSimboloString = acao.getSimbolo();
            direcaoString = acao.getDirecao();                           
                                   
            System.out.println("inicial: " + fita);
            System.out.println("estado atual: " + estadoAtual);
            System.out.println("novo simbolo: " + novoSimboloString);
            System.out.println("direcao: " + direcaoString);
            System.out.println("cabecote: " + posicaoAtualFita);
            
            int auxiliarPosicao = posicaoAtualFita; // para que seja possivel inserir no historico            
            String auxiliarCaracterFita = getCaracterFita(posicaoAtualFita);
            getNovaFita(novoSimboloString, posicaoAtualFita); // atualiza a fita                                    
            //setFitaView();
            System.out.println("valor da coluna: " + getCaracterFita(posicaoAtualFita) + " linha: " + estadoAtual);
                        
            posicaoAtualFita = getPosicao(acao.getDirecao(), posicaoAtualFita); // atualiza a variavel antes de obter na matriz
            acao = matrizAcoes.getAcao(getCaracterFita(posicaoAtualFita), estadoAtual); // obtem a nova acao                                    
            System.out.println("final: " + fita);                               
            
            setFitaView();
            setHistoricoEstadosView(auxiliarCaracterFita, posicaoAtualFita, acao.getEstadoDestino(), acao.getDirecao(), acao.isFimPrograma());
            
            try {                                
                Thread.sleep(3 * 1000);    
                frame.repaint();
            } catch (InterruptedException e) {
                throw new InterruptedException();
            }
            
                        
        }
        
        //JOptionPane.showMessageDialog(null, fita);        
        
    }
    
    
    public void setHistoricoEstadosView(String auxiliarCaracterFita, int posicaoAtualFita, int novoEstado,
        
        String novaDirecao, boolean fimDePrograma) {
        
        String historico = "";
        
        
        historico = "( " + estadoAtual + ", " + auxiliarCaracterFita + ")"
                + " = (" + novoEstado +", " + getCaracterFita(posicaoAtualFita) + ", "
                + " " + novaDirecao + ")";
        
        if (fimDePrograma) {
                        
            historico = "( " + estadoAtual + ", " + auxiliarCaracterFita + ")"
                + " = ( FIM DE PROGRAMA )";            
        }
        
        
        frame.setHistoricoEstadosAcoes(historico);
    }
    
    public String getCaracterFita (int posicaoAtualFita) {
        
        if (posicaoAtualFita >= fita.length() ) {
                    
            JOptionPane.showMessageDialog(null, fita);
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
    
    public void set(String fita){
        MaquinaDeTuringView.setFitaView(fita);
    }
    
    public synchronized void setFitaView () {     
        
        MaquinaDeTuringView.setFitaView(fita);
    }
    
    public void imprimirCampos () {
        
        /*for (JFormattedTextField i : MaquinaDeTuringView.getListaFields()) {
            
            System.out.println(i.getText());
            
        }*/
        
    }
                  
}
