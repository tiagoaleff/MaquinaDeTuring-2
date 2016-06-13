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
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

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
        numeroDeColunas = frame.getCampos().size() + 1;

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
        
        String [] simbolos = new String[numeroDeColunas + 2];      
        ArrayList<String> simbolosEntrada = frame.getCampos();
        //Inicial: ►"
        //int [] estados = new int [];
        
        int i = 1;                             
        simbolos[0] = "►";
        
        for (String iList : simbolosEntrada) {
                          
            simbolos[i] = iList;
            ++i;                        
            
        }          
        
        String [] cabecalhoTabela = {"Estados", "Ler", "Próximo Estado",
                                        "Escrever", "Direção"};
        
        DefaultTableModel defaultTable = 
                new DefaultTableModel(cabecalhoTabela, numeroDeEstados * numeroDeColunas){
                
                    @Override
                    public boolean isCellEditable(int row, int column) {

                        if (column == 0 || column == 1) {
                            return false;                                
                        }
                                                
                        return true;
                    }                    
                };
        
        frame.getTabelaDeAcao().setModel(defaultTable);
        formatCell(simbolos);
        
        int numeroEstado = 1;
        
        for (int col = 1; col < numeroDeColunas; col ++) {
            
            for (int line = 0; line < numeroDeEstados; line ++) {                                
                
                defaultTable.isCellEditable(line, col);
                
            }
            
        }
        frame.getTabelaDeAcao().setModel(defaultTable);              
    }    
    
    public void formatCell (String [] simbolos) {
    
        JTable tabelaEstados = frame.getTabelaDeAcao();
        
        String [] direcaoString = {"Direita", "Esquerda"};        
        JComboBox leituraEscrita = new JComboBox(simbolos);
        JComboBox direcao = new JComboBox(direcaoString);
        
        // configura o valor das linhas
        int row = tabelaEstados.getRowCount();
        int j = 0;
        int x = 0;
        int numeroDeEstado = 0;
        for (int i = 0; i < row; i++) {
         
            
            
            tabelaEstados.setValueAt(numeroDeEstado, i, 0); // configura o numero do estado
            tabelaEstados.setValueAt(simbolos[x], i, 1);
            
            System.out.println(numeroDeEstado);
            j++;  
            if ( j == numeroDeColunas){
                numeroDeEstado++;
                j = 0;
            }
            x++;
            if ( x == numeroDeColunas){
                //numeroDeEstado++;
                x = 0;
            }                                                                           
        }
        
        for (int i = 0; i < row; i++) {
            
            
            
        }

        // configura as colunas
        int totalDeColunas = tabelaEstados.getColumnCount();               
        for (int i = 0; i < totalDeColunas; i ++) {
            
            TableColumn col = tabelaEstados.getColumnModel().getColumn(i);                                                
            
            if (i == 3) {
                col.setCellEditor(new DefaultCellEditor(leituraEscrita));  
            }
            
             if (i == 4) {
                col.setCellEditor(new DefaultCellEditor(direcao));  
            }
            /*if (i != 0) {
                
                col.setCellEditor(new DefaultCellEditor(celulaFormadata));  
            } */           
                 
        }
            
        int totalDeLinhas = tabelaEstados.getRowCount();
        
        // configura a largura das linhas
        for (int i = 0; i < totalDeLinhas; i ++) {
            
            tabelaEstados.setRowHeight(i, 35);
            
        }
         
    }
    
}
