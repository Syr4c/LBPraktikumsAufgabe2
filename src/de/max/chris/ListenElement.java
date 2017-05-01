package de.max.chris;


public class ListenElement {
    private Object data;
    private ListenElement next;

    public ListenElement(){

    }

    public ListenElement(ListenElement next, Object data){
        this.next = next;
        this.data = data;
    }

    public void setData(Object data){
        this.data = data;
    }

    public Object getData(){
        return data;
    }

    public void setNext(ListenElement next){
        this.next = next;
    }

    public ListenElement getNext(){
        return next;
    }
}