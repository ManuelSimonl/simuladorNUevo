package com.example.simulador;

public class ElementoLS {
    private Object data;

    public ElementoLS(Object o) {
    }

    public void ElementoLS(Object data){
        this.data=data;}
    public Object getData(){
        return data;}
    public Object setData(Object o){
        this.data=o;
        return o;}
    public int getX() {
        return ((Recurso) data).getX();
    }
    //Ambos metodos son para usarlos en la funcion de encontrar el recurso más cercano en la clase individuo
    public int getY() {
        return ((Recurso) data).getY();
    }
}

