/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.maquinaDeTuring.object;

import br.maquinaDeTuring.exception.ExceptionTuring;
import static com.sun.glass.ui.Cursor.setVisible;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.text.ParseException;
import javax.swing.DefaultCellEditor;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author tiagoaleff
 */
public class MascaraCelulas extends JFrame{
    
    private MaskFormatter celula;
    private JFormattedTextField celulaFormadata;
    
    public MascaraCelulas () throws ExceptionTuring{
        
        try {
            celula = new MaskFormatter("U – A – #"); // definição da mascara nas celulas
            celula.setPlaceholderCharacter('_');
        } catch (ParseException e) {
            throw new ExceptionTuring("Erro ao criar mascara: " + e.getMessage());
        }
        
        celulaFormadata = new JFormattedTextField(celula); // adiciona a formacao ao campo        
    }
       
    /*
        Formata celulas das colunas. Cria mascara e altura
    */
    public void formatCell (JTable tabelaEstados) {
                        
        int totalDeColunas = tabelaEstados.getColumnCount();
               
        for (int i = 0; i < totalDeColunas; i ++) {
            
            if (i != 0) {
                // obtem celula por celula
                TableColumn col = tabelaEstados.getColumnModel().getColumn(i);
                // adiciona a mascara ao campo da coluna
                col.setCellEditor(new DefaultCellEditor(celulaFormadata));  
            }            
                 
        }
            
        int totalDeLinhas = tabelaEstados.getRowCount();
        
        // configura a largura das linhas
        for (int i = 0; i < totalDeLinhas; i ++) {
            
            tabelaEstados.setRowHeight(i, 35);
            
        }
         
    }
    
    public JFormattedTextField getFitaFormatada () throws ExceptionTuring{
        
        JFormattedTextField fitaFormatada = new JFormattedTextField();
        MaskFormatter fitaFormato = new MaskFormatter();
        
        try {
            fitaFormato = new MaskFormatter("AAAAAAAAAAAAAAAAAAAAAAAAAAA"); // definição da mascara nas celulas
            fitaFormato.setPlaceholderCharacter('_');
        } catch (ParseException e) {
            throw new ExceptionTuring("Erro ao criar mascara da fita: " + e.getMessage());
        }
        
        fitaFormatada = new JFormattedTextField(fitaFormato); // adiciona a formacao ao campo        
        return fitaFormatada;
    }
        
    
       
}
