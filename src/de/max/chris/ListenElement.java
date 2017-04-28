package de.max.chris;


public class ListenElement {
    private Data data;
    private ListenElement next;

    public ListenElement(){

    }

    public ListenElement(ListenElement next, Data data){
        this.next = next;
        this.data = data;
    }

    public void setData(Data data){
        this.data = data;
    }

    public Data getData(){
        return data;
    }

    public void setNext(ListenElement next){
        this.next = next;
    }

    public ListenElement getNext(){
        return next;
    }
}
