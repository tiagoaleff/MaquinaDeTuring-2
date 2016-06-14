/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.maquinaDeTuring.thread;

import br.maquinaDeTuring.model.MaquinaModel;
import br.maquinaDeTuring.view.MaquinaDeTuringView;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tiagoaleff
 */
public class Temporizador implements Runnable{

    private MaquinaDeTuringView frame;
    private MaquinaModel maquina;
    //private MaquinaModel

    public Temporizador(MaquinaDeTuringView frame, MaquinaModel maquina) {
        
        this.frame = frame;
        this.maquina = maquina;
        
    }    
    
    @Override
    public void run() {

        while(true){
        
            frame.atualiza();                
        
           try {
           
               Thread.sleep(1000);
           } catch (InterruptedException ex) {
               Logger.getLogger(Temporizador.class.getName()).log(Level.SEVERE, null, ex);
           }                       
        }
    
    
    }
    
}
