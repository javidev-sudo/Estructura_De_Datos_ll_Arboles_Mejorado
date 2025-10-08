/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.arboles;
import adt.AVL;
import adt.NodoBinario;
import adt.ArbolBinarioBusqueda;
import adt.IArbolBusqueda;
import excepciones.ExcepcionDatoNoExiste;
import excepciones.ExcepcionDatoYaExiste;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import adt.ArbolMViasBusqueda;

/**
 *
 * @author 
 */
public class Arboles {

    public static void main(String[] args) throws ExcepcionDatoYaExiste, ExcepcionDatoNoExiste {
       
        
 

     
        ArbolMViasBusqueda<Integer> arbol = new ArbolMViasBusqueda<>(3);
        
        arbol.insertar(1);
        arbol.insertar(6);
        arbol.insertar(8);
        
        arbol.insertar(2);
        arbol.insertar(4);
        arbol.insertar(5);
        
        arbol.insertar(7);
        
        arbol.insertar(20);
        arbol.insertar(21);
        arbol.insertar(22);
        
        System.out.println(arbol.toString());
        System.out.println(arbol.recorridoPorNiveles());
        System.out.println(arbol.recorridoEnInOrden());
        System.out.println(arbol.recorridoEnPreOrden());
        System.out.println(arbol.size());
        
        
        
        
        
   
        
    }
   
        
        
    
}
