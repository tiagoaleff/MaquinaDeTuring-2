/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.maquinaDeTuring.controller;

import br.maquinaDeTuring.model.MatrizModel;
import br.maquinaDeTuring.exception.ExceptionTuring;
import br.maquinaDeTuring.model.MaquinaModel;
import br.maquinaDeTuring.view.MaquinaDeTuringView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author tiagoaleff
 */
public class MaquinaController implements ActionListener{

    private MatrizModel matrizDeAcao;
    private MaquinaDeTuringView frame;
    private String fitaEntrada; // obtem o valor da fita    
    private MaquinaModel maquina;
    
    public MaquinaController (MaquinaDeTuringView frame) {
        this.frame = frame;
    }
                
    @Override
    public void actionPerformed(ActionEvent e) {
                
        String action = e.getActionCommand();
        
        switch (action) {
            
            case "salvarTabela":                        
                try {
                    salvarMatrizDeAcao();
                } catch (ExceptionTuring ex) {
                    Logger.getLogger(MaquinaController.class.getName()).log(Level.SEVERE, null, ex);
                }                        
            break;
                 case "ExecutarFita":

                try {
                    executarFita();

                } catch (ExceptionTuring ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage());
                }

            break;
        }
        
    }
    
    private void salvarMatrizDeAcao () throws ExceptionTuring {
        
        matrizDeAcao = new MatrizModel(frame.getCampos(), frame.getEstados());
        matrizDeAcao.setMatrizAcoes(frame.getTabelaDeAcao());        
        
    }
    
    public void setSettingDefault() throws ExceptionTuring {
        salvarMatrizDeAcao();
    }    
    
    public  void executarFita () throws ExceptionTuring {        
        fitaEntrada = frame.getFita();        
        maquina = new MaquinaModel(matrizDeAcao, fitaEntrada, frame);                               
        maquina.run();
        
    }            
}
