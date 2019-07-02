/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adivinaanimales;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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
    FileWriter writer = new FileWriter("conocimientos.txt", false);
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
                bufferedWriter.write("0");
            }else if ((aux.getHijoI() != null) && (aux.getHijoD() == null)) {
                bufferedWriter.write("1");
            }else if ((aux.getHijoI() == null) && (aux.getHijoD() != null)) {
                bufferedWriter.write("2");
            }else if ((aux.getHijoI() != null) && (aux.getHijoD() != null)) {
                bufferedWriter.write("3");
            }
            
            if (aux.isPregunta() == true) {
                bufferedWriter.write("1");
            }else if (aux.isPregunta() == false) {
                bufferedWriter.write("0");
            }
            
            bufferedWriter.write(aux.getInfo());
            bufferedWriter.newLine();
            
            actualizarConocimientos(aux.getHijoI());
            actualizarConocimientos(aux.getHijoD());
            
        }
        
    }
    
    public void leerConocimientos(Nodo input) throws IOException{
        
        Nodo aux = input;
        int hijos = (char) bufferedReader.read();
        char esPregunta = (char) bufferedReader.read();
        
        if (esPregunta == 1) {
            aux.setPregunta(true);
        }else if (esPregunta == 0) {
            aux.setPregunta(false);
        }
        
        aux.setInfo(bufferedReader.readLine());
        
        switch (hijos){
            case 0:
                break;
            case 1:
                leerConocimientos(aux.getHijoI());
                break;
            case 2:
                leerConocimientos(aux.getHijoD());
                break;
            case 3:
                leerConocimientos(aux.getHijoI());
                leerConocimientos(aux.getHijoD());
                break;
            default:
                break;
                
        }
        
        
    }
    
}
