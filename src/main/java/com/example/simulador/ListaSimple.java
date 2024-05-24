package com.example.simulador;

import listasimple.ElementoLS;

public class ListaSimple {
    private ElementoLS[] datos;
    private int maximo;
    private int num_elementos;
    public ListaSimple(int maximo){
        this.maximo=maximo;
        this.datos=new ElementoLS[maximo];
        this.num_elementos=0;}
    public boolean isVacia(){
        if (num_elementos==0){
            return true;}else{
            return false;}}
    public void vaciar(){
        datos=new ElementoLS[maximo];
        num_elementos=0;}
    private int add(ElementoLS elemento) {
        if (num_elementos < maximo) {
            datos[num_elementos] = elemento;
            num_elementos += 1;
            return num_elementos;
        } else {
            return -1;}}
    public void add(String s){
        ElementoLS Ele=new ElementoLS(s);
        add(Ele);}
    public void add(Object o){
        ElementoLS Ele=new ElementoLS(o);
        add(Ele);}
    public void insert(String s, int posicion){
        ElementoLS ele=new ElementoLS(s);
        this.datos[posicion]=ele;}
    public void insert(Object o, int posicion){
        ElementoLS ele=new ElementoLS(o);
        this.datos[posicion]=ele;}
    public int del(int posicion){
        if(posicion>=0 && posicion<num_elementos) {
            for(int i=posicion; i<num_elementos-1; i++){
                datos[i]=datos[i+1];}
            num_elementos--;
        }else{
            return -1;}
        return posicion;}
    public int getNum_elementos() {return num_elementos;}
    public int getPosicion(ElementoLS el){
        for(int i=0; i<num_elementos;i++){
            if(datos[i]==el){
                return i;}}
        return -1;}
    public ElementoLS getPrimero(){
        if (num_elementos>0){
            return datos[0];}
        return null;}
    public ElementoLS getUltimo(){
        if (num_elementos>0){
            return datos[num_elementos-1];}
        return null;}
    private ElementoLS getSiguiente(ElementoLS el){
        if (num_elementos>0 && getPosicion(el)<num_elementos-1){
            return datos[getPosicion(el)+1];}
        return null;
    }
    public ElementoLS getElemento(int posicion){
        if (posicion>=0 && posicion<= num_elementos-1 ){
            return datos[posicion];}
        return null;}
}
