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
public class Nodo {
    
    public String info;
    public Nodo hijoI;
    public Nodo hijoD;

    public Nodo(String I) {
        this.info=I;
        this.hijoI=null;
        this.hijoD=null;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Nodo getHijoI() {
        return hijoI;
    }

    public void setHijoI(Nodo hijoI) {
        this.hijoI = hijoI;
    }

    public Nodo getHijoD() {
        return hijoD;
    }

    public void setHijoD(Nodo hijoD) {
        this.hijoD = hijoD;
    }
    
    
}
