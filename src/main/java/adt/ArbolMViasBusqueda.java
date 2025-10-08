/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adt;

import excepciones.ExcepcionDatoNoExiste;
import excepciones.ExcepcionDatoYaExiste;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class ArbolMViasBusqueda <T extends Comparable<T>> implements IArbolBusqueda<T>{
    protected static final int ORDEN_MINIMO = 3;
    protected static final int POSICION_INVALIDA = -1;
    
    protected NodoMVias<T> raiz;
    protected int orden;

    public ArbolMViasBusqueda(int orden) {
        this.orden = orden;
    }
    
    
    @Override
    public void vaciar() {
       this.raiz = NodoMVias.nodoVacio();
    }

    @Override
    public boolean esArbolVacio() {
       return this.raiz == NodoMVias.nodoVacio();
    }
    
    @Override
    public void insertar(T datoAInsertar) throws ExcepcionDatoYaExiste {
        if(datoAInsertar == null){
            throw new IllegalArgumentException("No se puede insertar datos nulos!!");
        }
        if(this.esArbolVacio()){
            this.raiz = new NodoMVias<>(this.orden,datoAInsertar);
            return;
        }
        
        NodoMVias<T> nodoEnTurno = this.raiz;//guardamos la raiz en un nodoEnTurno
        
        do {
            int posionDeDato = this.buscarPosicionDeDatoEnNodo(nodoEnTurno, datoAInsertar); // puede dar -1 , o un numero mayor
            if(posionDeDato != POSICION_INVALIDA){
                throw new ExcepcionDatoYaExiste();
            }
            if(nodoEnTurno.esHoja()){ // si el nodo es hoja
                if(nodoEnTurno.estanDatosLLenos()){
                    // no hay espacio
                     int posicionPorDondeBajar = this.buscarPosicionPorDondeBajar(nodoEnTurno, datoAInsertar); // falta implementar
                     NodoMVias<T> nuevoNodo = new NodoMVias<>(this.orden,datoAInsertar);
                     nodoEnTurno.setHijo(posicionPorDondeBajar, nuevoNodo);
                }else {
                    //si hay espacio
                    this.insertarDatoOrdenadoEnNodo(nodoEnTurno, datoAInsertar);
                    
                }
                nodoEnTurno = NodoMVias.nodoVacio();
            } else { // si no es hoja
                int posicionPorDondeBajar = this.buscarPosicionPorDondeBajar(nodoEnTurno, datoAInsertar);
                 if(nodoEnTurno.esHijoVacio(posicionPorDondeBajar)){
                     NodoMVias<T> nodoNuevo = new NodoMVias<>(this.orden,datoAInsertar);
                     nodoEnTurno.setHijo(posicionPorDondeBajar, nodoNuevo);
                     nodoEnTurno =  NodoMVias.nodoVacio();
                 }else {
                     nodoEnTurno = nodoEnTurno.getHijo(posicionPorDondeBajar);
                 }
            }
            
        } while (!NodoMVias.esNodoVacio(nodoEnTurno));
         
    }
    
    protected int buscarPosicionDeDatoEnNodo(NodoMVias<T> nodoEnTurno, T datoAInsertar) {
        for (int i = 0; i < nodoEnTurno.nroDeDatosNoVacios(); i++) {
            T dataEnTurno = nodoEnTurno.getDato(i);
            if(datoAInsertar.compareTo(dataEnTurno) == 0){
                return i;
            }
        }      
        return POSICION_INVALIDA;
    }

    @Override
    public void eliminar(T dato) throws ExcepcionDatoNoExiste {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public T buscar(T dato) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean contiene(T dato) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int size() {
        return size(this.raiz);
    }
    
    private int size(NodoMVias<T> nodoEnTurno){
        if(NodoMVias.esNodoVacio(nodoEnTurno)){
            return 0;
        }
        int contadorDeDatos = 0;
        for (int i = 0; i < nodoEnTurno.nroDeDatosNoVacios(); i++) {
            contadorDeDatos += size(nodoEnTurno.getHijo(i)) + 1;
        }
        
        contadorDeDatos += size(nodoEnTurno.getHijo(nodoEnTurno.nroDeDatosNoVacios()));
        return contadorDeDatos;
    }

    @Override
    public int altura() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    @Override
    public int nivel() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<T> recorridoEnInOrden() {
        List<T> listaDeRecorrido = new ArrayList<>();
        recorridoEnInOrden(this.raiz,listaDeRecorrido);
        return listaDeRecorrido;
    } 
    
    private void recorridoEnInOrden(NodoMVias<T> nodoEnTurno, List<T> listaDeRecorrido){
        if(!NodoMVias.esNodoVacio(nodoEnTurno)){
            for (int i = 0; i < nodoEnTurno.nroDeDatosNoVacios(); i++) {
                recorridoEnInOrden(nodoEnTurno.getHijo(i), listaDeRecorrido);
                listaDeRecorrido.add(nodoEnTurno.getDato(i));
            }
            recorridoEnInOrden(nodoEnTurno.getHijo(nodoEnTurno.nroDeDatosNoVacios()), listaDeRecorrido);
        }
    }

    @Override
    public List<T> recorridoEnPreOrden() {
        List<T> listaDeRecorrido = new ArrayList<>();
        recorridoEnPreOrden(this.raiz,listaDeRecorrido);
        return listaDeRecorrido;
    }
    
    private void recorridoEnPreOrden(NodoMVias<T> nodoEnTurno, List<T> listaDeRecorrido) {
        if(!NodoMVias.esNodoVacio(nodoEnTurno)){
            for (int i = 0; i < nodoEnTurno.nroDeDatosNoVacios(); i++) {
                listaDeRecorrido.add(nodoEnTurno.getDato(i));
                recorridoEnPreOrden(nodoEnTurno.getHijo(i),listaDeRecorrido);
            }
            recorridoEnPreOrden(nodoEnTurno.getHijo(nodoEnTurno.nroDeDatosNoVacios()), listaDeRecorrido);
        }
    }

    @Override
    public List<T> recorridoEnPostOrden() {
        List<T> listaDeRecorrido = new LinkedList<>();
        recorridoEnPostOrden(this.raiz,listaDeRecorrido);
        return listaDeRecorrido;
    }
    
    private void recorridoEnPostOrden(NodoMVias<T> nodoEnTurno, List<T> listaDerecorrido){
        
        if(!NodoMVias.esNodoVacio(nodoEnTurno)){
            recorridoEnPostOrden(nodoEnTurno.getHijo(0), listaDerecorrido);
            
            for (int i = 1; i <= nodoEnTurno.nroDeDatosNoVacios(); i++) {
                recorridoEnPostOrden(nodoEnTurno.getHijo(i), listaDerecorrido);
                listaDerecorrido.add(nodoEnTurno.getDato(i-1));
            }
        }
    }

    @Override
    public List<T> recorridoPorNiveles() {
        List<T> recorridoNiveles = new LinkedList<>();
        if(!this.esArbolVacio()){
            Queue<NodoMVias<T>> colaDeNodosMvias = new LinkedList<>();
            colaDeNodosMvias.offer(raiz);
            
            while(!colaDeNodosMvias.isEmpty()){
                NodoMVias<T> nodoEnTurno = colaDeNodosMvias.poll();
                
                for (int i = 0; i < nodoEnTurno.nroDeDatosNoVacios(); i++) {
                   recorridoNiveles.add(nodoEnTurno.getDato(i));
  
                   if(!nodoEnTurno.esHijoVacio(i)){
                       colaDeNodosMvias.offer(nodoEnTurno.getHijo(i));
                   }
                }  
                if(!nodoEnTurno.esHijoVacio(nodoEnTurno.nroDeDatosNoVacios())){
                    colaDeNodosMvias.offer(nodoEnTurno.getHijo(nodoEnTurno.nroDeDatosNoVacios()));
                }            
            }           
        }       
        return recorridoNiveles;
    }

    private int buscarPosicionPorDondeBajar(NodoMVias<T> nodoEnTurno, T datoAInsertar) {
        for (int i = 0; i < nodoEnTurno.nroDeDatosNoVacios(); i++) {
           if(datoAInsertar.compareTo(nodoEnTurno.getDato(i)) < 0){
              return i;
           }else if(datoAInsertar.compareTo(nodoEnTurno.getDato(i)) == 0){
               return POSICION_INVALIDA; // -1
           }
        }   
        return orden; //el orden de la que es los valores
    }

    public void insertarDatoOrdenadoEnNodo(NodoMVias<T> nodoEnTurno, T datoAInsertar) {
        int n = nodoEnTurno.nroDeDatosNoVacios();
        
        for (int i = 0; i < n; i++) {
            if(datoAInsertar.compareTo(nodoEnTurno.getDato(i)) < 0){ 
                
                for(int j = n - 1;j >= i; j--){
                    T datoCopia = nodoEnTurno.getDato(j);
                    nodoEnTurno.setDato(j+1, datoCopia);
                }
                nodoEnTurno.setDato(i, datoAInsertar);
                break;             
            }else{
                if(i == n-1){
                    nodoEnTurno.setDato(i+1, datoAInsertar);
                }
            }
        } 
    }
    
    @Override
    public String toString() {
        if (NodoMVias.esNodoVacio(raiz)) {
            return "Árbol vacío";
        }

        StringBuilder sb = new StringBuilder();
        Queue<NodoMVias<T>> cola = new LinkedList<>();
        cola.add(raiz);

        while (!cola.isEmpty()) {
            int size = cola.size();
            for (int i = 0; i < size; i++) {
                NodoMVias<T> nodo = cola.poll();

                sb.append("[");
                for (int j = 0; j < nodo.nroDeDatosNoVacios(); j++) {
                    sb.append(nodo.getDato(j));
                    if (j < nodo.nroDeDatosNoVacios() - 1) {
                        sb.append(", ");
                    }
                }
                sb.append("] ");

                // Encolar hijos
                for (int j = 0; j < nodo.listaDeHijos.size(); j++) {
                    if (!nodo.esHijoVacio(j)) {
                        cola.add(nodo.getHijo(j));
                    }
                }
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    
   

}
