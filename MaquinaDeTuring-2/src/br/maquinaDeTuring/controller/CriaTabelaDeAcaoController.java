/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.maquinaDeTuring.controller;

import br.maquinaDeTuring.exception.ExceptionTuring;
import br.maquinaDeTuring.object.MascaraCelulas;
import br.maquinaDeTuring.view.MaquinaDeTuringView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author tiagoaleff
 */
public class CriaTabelaDeAcaoController implements ActionListener{
    
    // obter a action e encaminhar o programa para o metodo correto
    private MaquinaDeTuringView frame;
    private MascaraCelulas formatarCelularTabela;
    private int numeroDeEstados;
    private int numeroDeColunas;   
    private int numeroDeLinhas;   
    
            
    public CriaTabelaDeAcaoController(MaquinaDeTuringView frame) {
              
        this.frame = frame;        

    }

    @Override
    public void actionPerformed(ActionEvent e) {      
                
        String  action = e.getActionCommand();
                
        switch (action) {
            
            case "gerarColunasEstados" :
                try {
                 definirColunasEstados();                      
                } catch (ExceptionTuring ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage());
                } catch (NumberFormatException exceptionNumber) {
                    JOptionPane.showMessageDialog(frame, "Campo Quantidade de Estados não informado");
                }
                
                break;
                
            case "limparTabela" :
                try {
                 limparTabelaEmMatriz();   
                } catch (ExceptionTuring ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage());
                } catch (NumberFormatException exceptionNumber) {
                    JOptionPane.showMessageDialog(frame, "Erro ao limpar campos da tabela");
                }
                
                break;
               default:
                   JOptionPane.showMessageDialog(frame, "Deu Ruim!");
        }
                               
    }
    
    public void definirColunasEstados () throws ExceptionTuring{

        numeroDeEstados = frame.getEstados();
        numeroDeColunas = frame.getCampos().size();

        gerarTabelaDeAcao();                       
    }
    
    public void definirSimbolos () {
        // obter a string e formatala em um vetor de string/char
        // acidiciona-la como celula
    }
    
    public void salvarTabelaEmMatriz () {
        
    }    
    
    public void limparTabelaEmMatriz () throws ExceptionTuring{
        gerarTabelaDeAcao();
    }    
    
    private void gerarTabelaDeAcao() throws ExceptionTuring{
        
        String [] campoTabela = new String[numeroDeColunas + 2];      
        ArrayList<String> simbolosEntrada = frame.getCampos();
        Collections.sort(simbolosEntrada);
        int i = 2;
                
        campoTabela[0] = "Estados";       
        campoTabela[1] = "Inicial: ►";
        
        for (String iList : simbolosEntrada) {
                          
            campoTabela[i] = iList;
            ++i;                        
            
        }          
        
        try {                        

            DefaultTableModel defaultTable = 
                new DefaultTableModel(campoTabela, numeroDeEstados){
                    @Override
                    public boolean isCellEditable(int row, int column) {

                        if (column == 0) {
                            return false;                                
                        }
                        return true;
                    }                    
                };
           
            frame.getTabelaDeAcao().setModel(defaultTable);            
            // formata novas celulas da tabela
            formatarCelularTabela = new MascaraCelulas();
            formatarCelularTabela.formatCell(frame.getTabelaDeAcao());
                    
            int numeroEstado = 1;           
            
            for (int linha = 0; linha < (numeroDeEstados); linha ++) {
                            
                defaultTable.setValueAt(linha, linha, 0); // Estados =  Dividir o numero delinhas por colunas
                ++numeroEstado;                                   
                
            }
            
            // torna as celulas etivateis
            for (int col = 1; col < numeroDeColunas; col ++) {
                                
                    for (int line = 0; line < numeroDeEstados; line ++) {
                    
                        defaultTable.isCellEditable(line, col);
                            
                    }                                
                
            }
            
            frame.getTabelaDeAcao().setModel(defaultTable);
            
        } catch (ExceptionTuring e) {
            throw new ExceptionTuring("Erro configurar tabela");
        }              
    }    
}
