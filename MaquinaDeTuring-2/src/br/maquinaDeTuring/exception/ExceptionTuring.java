/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.maquinaDeTuring.exception;


/**
 *
 * @author tiagoaleff
 */
public class ExceptionTuring extends Exception{
    
    public ExceptionTuring () {
        super("Erro!");
    }
    
    public ExceptionTuring (String mensagem) {
        super(mensagem);        
    }
    
}
