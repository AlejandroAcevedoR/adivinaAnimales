/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adivinaanimales;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
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
    FileWriter writer = new FileWriter("conocimientos.txt", true);
    BufferedWriter bufferedWriter = new BufferedWriter(writer);
    FileReader reader = new FileReader("conocimientos.txt");
    BufferedReader bufferedReader = new BufferedReader(reader);

    public Tree() throws IOException{
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
        
    Nodo aux;
    Nodo auxHijo;
        
        if(raiz == null){
            
            raiz = new Nodo(inicio);            
            aux = new Nodo("Es un pájaro?"); 
            
            auxHijo = new Nodo("Soy el más grande!");
            aux.setHijoI(auxHijo);
            auxHijo = new Nodo("aqui van las preguntas");
            aux.setHijoD(auxHijo);
                        
            raiz.setHijoI(aux);
            
            aux = new Nodo("Tiene que ser un animal");
            raiz.setHijoD(aux);
            
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
        String temp = new String();
        
        if (aux != null) {
            
            if ((aux.getHijoI() == null) && (aux.getHijoD() == null)) {
                temp = temp + "0";
            }else if ((aux.getHijoI() != null) && (aux.getHijoD() == null)) {
                temp = temp + "1";
            }else if ((aux.getHijoI() == null) && (aux.getHijoD() != null)) {
                temp = temp + "2";
            }else if ((aux.getHijoI() != null) && (aux.getHijoD() != null)) {
                temp = temp + "3";
            }
            
            if (aux.isPregunta() == true) {
                temp = temp + "1";
            }else if (aux.isPregunta() == false) {
                temp = temp + "0";
            }
            
            temp = temp + aux.getInfo();
            bufferedWriter.write(temp);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            
            actualizarConocimientos(aux.getHijoI());
            actualizarConocimientos(aux.getHijoD());
            
        }
        
    }
    
    public void leerConocimientos(Nodo input, boolean primera) throws IOException{
        
        Nodo aux = input;
        
        if (primera == true) {
            bufferedReader.read();
        }
        
        char hijos = (char)bufferedReader.read();
        char esPregunta = (char)bufferedReader.read();

        if (esPregunta == '1') {
            aux.setPregunta(true);
        }else if (esPregunta == '0') {
            aux.setPregunta(false);
        }

        aux.setInfo(bufferedReader.readLine());

        switch (hijos){
            case '1':
                leerConocimientos(aux.getHijoI(), false);
            case '2':
                leerConocimientos(aux.getHijoD(), false);
            case '3':
                leerConocimientos(aux.getHijoI(), false);
                leerConocimientos(aux.getHijoD(), false);
        }            
        
    }
    
    public void resetear() throws IOException{
        FileWriter writer = new FileWriter("conocimientos.txt", false);
        writer.close();
    }
    
}
