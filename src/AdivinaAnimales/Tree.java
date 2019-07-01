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
    boolean respuesta;
    String inicio = "esta pensando en un gato?";

    public Tree() {
        this.raiz = null;
    }

    public boolean isRespuesta() {
        return respuesta;
    }

    public void setRespuesta(boolean respuesta) {
        this.respuesta = respuesta;
    }
    
    
    
    public boolean vacio(Nodo n){
        return n==null;
    }
    
    public void inicio(){
        if(raiz==null){
            raiz=new Nodo(inicio);
            raiz.setPregunta(false);
        }
    }
    
    
    public void insertar(Nodo n, String p, String r){
        if(respuesta == false){
            Nodo aux = n;
            n.setInfo(p);
            n.setPregunta(true);
            n.setHijoD(aux);
            aux.setInfo(r);
            n.setHijoI(aux);
        }
    }
}
