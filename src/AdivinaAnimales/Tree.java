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

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }
           
    public void insertarRaiz(String a){
        raiz = new Nodo(a);
        raiz.setPregunta(true);
    }
    
    public boolean vacio(Nodo n){
        return n == null;
    }
    
    public int altura(Nodo root) {
		if (root == null)
			return 0;
		return Math.max(altura(root.getHijoI()), altura(root.getHijoD())) + 1;
    }
    
    public void inicio(){

        Nodo aux;
        this.insertarRaiz("Estás pensando en un animal?");

        aux = new Nodo("pájaro");             
        raiz.setHijoI(aux);

        aux = new Nodo("Tiene que ser un animal");
        raiz.setHijoD(aux);

        aux = new Nodo("¡Adiviné!");
        raiz.hijoI.setHijoI(aux);
        
        aux = new Nodo("Inicio: intentar de nuevo");
        raiz.hijoI.setHijoD(aux);

    }
    
    public Nodo mover(Nodo n){
        
        if(respuesta == false){
        
            return n.hijoD;
            
        }else{
        
            return n.hijoI; 
        
        }
        
    }
    
    public void insertar(Nodo n, String p, String r, boolean modifier){
               
        Nodo aux = new Nodo(n.getInfo());
        Nodo auxHijosI = new Nodo(n.getHijoI().getInfo());
        Nodo auxHijosD = new Nodo(n.getHijoD().getInfo());
        aux.setHijoI(auxHijosI);
        aux.setHijoD(auxHijosD);
        n.setInfo(p);
        n.setPregunta(true);

        if (modifier == true) {
            n.setHijoD(aux);
            aux = new Nodo(r);
            auxHijosI = new Nodo("¡Adiviné!");
            auxHijosD = new Nodo("Inicio: intentar de nuevo");
            aux.setHijoI(auxHijosI);
            aux.setHijoD(auxHijosD);
            n.setHijoI(aux);
        }

        if (modifier == false) {
            n.setHijoI(aux);
            aux = new Nodo(r);
            auxHijosI = new Nodo("¡Adiviné!");
            auxHijosD = new Nodo("Inicio: intentar de nuevo");
            aux.setHijoI(auxHijosI);
            aux.setHijoD(auxHijosD);
            n.setHijoD(aux);
        }
               
    }
    
    public Nodo find(Nodo n, String key) {
        
        Nodo result = null;

        if (n == null)
                return null;
        if (n.getInfo().equals(key))
                return n;
        if (n.getHijoI()!= null)
                result = find(n.getHijoI(), key);
        if (result == null)
                result = find(n.getHijoD(), key);

        return result;
                
    }
    
    
    public void actualizarConocimientos(Nodo input, boolean primera) throws IOException{
        
        Nodo aux = input;
        String temp = new String();
        
        if (primera == true) {
            bufferedWriter.write("CONOCIMIENTOS:");
            bufferedWriter.newLine();
        }
        
        if ((aux != null) && (aux.getInfo() != "")) {
            
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

            actualizarConocimientos(aux.getHijoI(), false);
            actualizarConocimientos(aux.getHijoD(), false);
                
        }
        
    }
    
    public void leerConocimientos(Nodo input, boolean primera) throws IOException{
        
        Nodo aux = input;
        Nodo hijosI = new Nodo("");
        Nodo hijosD = new Nodo("");
        
        if (primera == true) {
            bufferedReader.readLine();
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
                aux.setHijoI(hijosI);
                leerConocimientos(aux.getHijoI(), false);
            case '2':
                aux.setHijoD(hijosD);
                leerConocimientos(aux.getHijoD(), false);
            case '3':
                aux.setHijoI(hijosI);
                aux.setHijoD(hijosD);
                leerConocimientos(aux.getHijoI(), false);
                leerConocimientos(aux.getHijoD(), false);
        }            
        
    }
    
    public void resetear() throws IOException{
        FileWriter writer = new FileWriter("conocimientos.txt", false);
        writer.close();
    }
    
}
