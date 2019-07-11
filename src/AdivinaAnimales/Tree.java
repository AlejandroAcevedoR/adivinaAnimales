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
    
    //utilizada para luego dibujar el arbol
    public int altura(Nodo root) {
		if (root == null)
			return 0;
		return Math.max(altura(root.getHijoI()), altura(root.getHijoD())) + 1;
    }
    
    //valores default
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
    
    //utilizado para reorganizar el arbol al insertar conocimientos nuevos
    public Nodo mover(Nodo n){
        
        if(respuesta == false){
        
            return n.hijoD;
            
        }else{
        
            return n.hijoI; 
        
        }
        
    }
    
    //utilizado para insertar nuevos conocimientos al arbol existente
    public void insertar(Nodo n, String p, String r, boolean modifier){
        
        //valores auxiliares y de entrada
        Nodo aux = new Nodo(n.getInfo());
        Nodo auxHijosI = new Nodo(n.getHijoI().getInfo());
        Nodo auxHijosD = new Nodo(n.getHijoD().getInfo());
        aux.setHijoI(auxHijosI);
        aux.setHijoD(auxHijosD);
        n.setInfo(p);
        n.setPregunta(true);
        
        //modifier indica si el nuevo conocimiento pertenece al animal antiguo o el nuevo, lo cual cambia ciertas acciones
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
    
    //toma la raíz del árbol y un string, y retorna el nodo del árbol que tenga ese string si existe
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
    
    //funcion para actualizar el archivo de conocimientos
    public void actualizarConocimientos(Nodo input, boolean primera) throws IOException{
        
        Nodo aux = input;
        String temp = new String();
        
        //escribe la primera linea
        if (primera == true) {
            bufferedWriter.write("CONOCIMIENTOS:");
            bufferedWriter.newLine();
        }
        
        if ((aux != null) && (aux.getInfo() != "")) {
            
            //escribe el numero de hijos del nodo
            if ((aux.getHijoI() == null) && (aux.getHijoD() == null)) {
                temp = temp + "0";
            }else if ((aux.getHijoI() != null) && (aux.getHijoD() == null)) {
                temp = temp + "1";
            }else if ((aux.getHijoI() == null) && (aux.getHijoD() != null)) {
                temp = temp + "2";
            }else if ((aux.getHijoI() != null) && (aux.getHijoD() != null)) {
                temp = temp + "3";
            }
            
            //escribe si el nodo es pregunta
            if (aux.isPregunta() == true) {
                temp = temp + "1";
            }else if (aux.isPregunta() == false) {
                temp = temp + "0";
            }
            
            //escribe el dato del nodo
            temp = temp + aux.getInfo();           
            
            bufferedWriter.write(temp);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            
            //continua recursivamente
            actualizarConocimientos(aux.getHijoI(), false);
            actualizarConocimientos(aux.getHijoD(), false);
                
        }
        
    }
    
    //funcion para actualizar el arbol a partir del archivo de conocimientos
    public void leerConocimientos(Nodo input, boolean primera) throws IOException{
        
        //inicializacion de valores
        Nodo aux = input;
        Nodo hijosI = new Nodo("");
        Nodo hijosD = new Nodo("");
        
        //salta la primera linea del archivo de texto
        if (primera == true) {
            bufferedReader.readLine();
        }
        
        //lee numero de hijos y valor de pregunta
        char hijos = (char)bufferedReader.read();
        char esPregunta = (char)bufferedReader.read();

        //escribe nodo a partir de los valores leidos
        if (esPregunta == '1') {
            aux.setPregunta(true);
        }else if (esPregunta == '0') {
            aux.setPregunta(false);
        }

        aux.setInfo(bufferedReader.readLine());
        
        //continua recursivamente dependiendo del numero de hijos del nodo
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
    
    //asegura que conocimientos.txt se encuentra vacio antes de escribirle de nuevo
    public void resetear() throws IOException{
        FileWriter writer = new FileWriter("conocimientos.txt", false);
        writer.close();
    }
    
}
