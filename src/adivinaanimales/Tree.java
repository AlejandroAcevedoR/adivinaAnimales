/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adivinaanimales;

/**
 *
 * @author Alejandro
 */
public class Tree {
    
    Nodo raiz;
    String pregunta;
    String respuesta;

    public Tree() {
        this.raiz =null;
    }
    
    public boolean vacio(Nodo n){
        return n==null;
    }
    
    public void mover(Nodo n, boolean a){
        if(a==true){
            irIzquierda(n.hijoI);
        }else if(a==false){
            
        }
    }
    
    public String irIzquierda(Nodo n){
        if(n.info==respuesta){
            return n.info;
        }
        return null;
    }
}
