/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package adivinaanimales;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import javax.swing.JPanel;
/**
 *
 * @author Alejandro
 */
public class GraficoArbol extends JPanel{
   
    
    public Tree arbol;

    public GraficoArbol(Tree arbol) {
        this.arbol = arbol;
    }
    
    @Override
    protected void paintComponent(Graphics g){
    
        g.setFont(new Font("Tahoma", Font.BOLD, 20));
        Draw(g, 0, getWidth(), 0, getHeight() / arbol.altura(arbol.getRaiz()), arbol.getRaiz());
        
    } 
    
    public void Draw(Graphics g, int StartWidth, int EndWidth, int StartHeight, int Level, Nodo node) {
        String data = node.getInfo();
        g.setFont(new Font("Tahoma", Font.BOLD, 20));
        FontMetrics fm = g.getFontMetrics();
        int dataWidth = fm.stringWidth(data);
        g.drawString(data, (StartWidth + EndWidth) / 2 - dataWidth / 2, StartHeight + Level / 2);

        if (node.getHijoI() != null)            
        	Draw(g, StartWidth, (StartWidth + EndWidth) / 2, StartHeight + Level, Level, node.getHijoI());
        
        if (node.getHijoD() != null)
        	Draw(g, (StartWidth + EndWidth) / 2, EndWidth, StartHeight + Level, Level, node.getHijoD());
    }
    
}
