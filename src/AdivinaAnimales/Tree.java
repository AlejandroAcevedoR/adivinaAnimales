/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adivinaanimales;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Alejandro
 */
public class Tree {
    
    Nodo raiz;
    boolean respuesta;
    String inicio = "Estás pensando en un animal?";
    
    //inicializacion del BufferedWriter
    FileWriter writer;
    BufferedWriter bufferedWriter = new BufferedWriter(writer);

    public Tree() throws IOException {
        this.writer = new FileWriter("conocimientos.txt", false);
        this.raiz = null;
    }

    public boolean isRespuesta() {
        return respuesta;
    }

    public void setRespuesta(boolean respuesta) {
        this.respuesta = respuesta;
    }

    public Nodo getRaiz() {
        return raiz;
    }    
    
    public boolean vacio(Nodo n){
        return n == null;
    }
    
    public void inicio(){
        if(raiz == null){
            raiz = new Nodo(inicio);
            raiz.setPregunta(false);
        }
    }
    
    
    public void insertar(Nodo n, String p, String r, boolean modifier){
        if(respuesta == false){
            Nodo aux = n;
            n.setInfo(p);
            n.setPregunta(true);
            if (modifier == false) {
                n.setHijoD(aux);
                aux.setInfo(r);
                n.setHijoI(aux);
            }
            if (modifier == true) {
                n.setHijoI(aux);
                aux.setInfo(r);
                n.setHijoD(aux);
            }
        }
    }
    
    public void actualizarConocimientos(Nodo input) throws IOException{
        
        Nodo aux = input;
        
        if (aux != null) {
            
            if ((aux.getHijoI() == null) && (aux.getHijoD() == null)) {
                bufferedWriter.write("0 ");
            }else if ((aux.getHijoI() != null) && (aux.getHijoD() == null)) {
                bufferedWriter.write("1 ");
            }else if ((aux.getHijoI() == null) && (aux.getHijoD() != null)) {
                bufferedWriter.write("2 ");
            }else if ((aux.getHijoI() != null) && (aux.getHijoD() != null)) {
                bufferedWriter.write("3 ");
            }
            
            bufferedWriter.write(aux.getInfo());
            bufferedWriter.newLine();
            
            actualizarConocimientos(aux.getHijoI());
            actualizarConocimientos(aux.getHijoD());
            
        }
        
    }
    
}
