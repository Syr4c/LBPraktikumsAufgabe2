package de.max.chris;

public class Main {

    public static void main(String[] args) {

    }

    /**
     *
     * @param l
     * @return  Gibt immer true zurueck sobald die Methode aufgerufen wird.
     *          Wurde die Methode erfolgreich aufgerufen, muss es eine Liste sein.
     */
    public boolean isAList(ListenElement l){
        return true;
    }


    /**
     *
     * @param i
     * @param l
     * @return
     */
    public boolean suffix(ListenElement i, ListenElement l){
        boolean success = false;

        return success;
    }

    /**
     *
     * @param i
     * @param l
     * @return  Die Methode bekommt zwei Listenköpfe übergeben und soll überprüfen, ob die Liste
     *          i ein Infix von der Liste l ist. Die tut sie, indem sie schrittweise die einzelnen
     *          Elemente miteinander vergleicht. Sollte ein Element aus i gleich einem Element aus l
     *          sein, wird nundann überprüft, ob die darauf folgenden Elemente in l gleich sind.
     */
    public boolean infix(ListenElement i, ListenElement l){
        boolean run = true;
        boolean success = false;

        ListenElement nextElementI = i;
        ListenElement nextElementL = l;

        while(!success){
               if(nextElementI.getData().equals(nextElementL.getData())){
                   ListenElement nextInnerElementI = nextElementI.getNext();
                   ListenElement nextInnerElementL = nextElementL.getNext();

                   while(run){
                       if(nextInnerElementI == null){
                           run = false;
                           success = true;
                       }

                       if(nextInnerElementI.getData().equals(nextInnerElementL.getData())){
                           nextInnerElementI = nextInnerElementI.getNext();
                           nextInnerElementL = nextInnerElementL.getNext();
                       } else {
                           run = false;
                       }
                   }
               }

               nextElementL = nextElementL.getNext();
               run = true;
        }

        return success;
    }

    /**
     *
     * @param p
     * @param l
     * @return  Die Methode bekommt zwei Listenköpfe mit den Parametern p, l übergeben. Sie soll prüfen, ob die
     *          übergebene Liste p ein Präfix von l ist. Es wird zunächst überprüft, ob das erste Element gleich
     *          ist. Ist das nicht so, gibt die Methode false zurück. Sollten die Elemente gleich sein, wird nun
     *          jedes weitere Element aus beiden Listen verglichen, wenn alle gleich sind, wird true zurück gegeben,
     *          ansonsten false.
     */
    public boolean praefix(ListenElement p, ListenElement l){
        boolean run = true;
        boolean success = false;

        if(p.getData().equals(l.getData())){
            ListenElement nextElementL = l.getNext();
            ListenElement nextElementP = p.getNext();

            while(run){
                if(nextElementP == null){
                    run = false;
                    success = true;
                }

                if(nextElementP.getData().equals(nextElementL.getData())){
                    nextElementL = nextElementL.getNext();
                    nextElementP = nextElementP.getNext();
                } else {
                    run = false;
                }
            }
        }

        return  success;
    }

    /**
     *
     * @param p
     * @param e
     * @param l
     * @return
     *
     * Precondition:
    - Eine Liste, eine Position und ein Element wird übergeben. P bezeichnet die Position: e erstes, l letztes, a alle Vorkommen
    Postcondition:
    - Ein Element innerhalb der übergebenen Liste wurde an der übergebenen position/en gelöscht,
    eventuell darauf folgende Elemente wurden richtig eingerückt, indem sie um einen Platz nach vorne verschoben worden
    sind und die Liste wurde zurückgeliefert(R).
     *
     */

    public ListenElement delElement(String p, ListenElement e, ListenElement l){
        ListenElement returnList = null;

        if(p.equals("e")){
            returnList = delE(e, l);

        } else if(p.equals("l")){
            returnList = delL(e, l);

        } else if(p.equals("a")){
            returnList = delA(e, l);

        } else {
            throw new IllegalArgumentException("p hat einen falschen Wert.");
        }

        return returnList;
    }

    private ListenElement delE(ListenElement e, ListenElement l){
        boolean run = true;
        ListenElement returnList = null;

        // Spezialfall, wenn das Element e direkt dem ersten Element aus l entspricht.
        if(e.getData().equals(l.getData())){
            returnList = l.getNext();
            run = false;
        }

        ListenElement nextElementL = l.getNext();
        ListenElement savePrevious = l;

        while(run){
            if(e.getData().equals(nextElementL.getData())){

            }
        }

        return returnList;
    }

    private ListenElement delL(ListenElement e, ListenElement l){
        ListenElement returnList = new ListenElement();

        return returnList;
    }

    private ListenElement delA(ListenElement e, ListenElement l){
        ListenElement returnList = new ListenElement();

        return returnList;
    }


    /**
     *
     * @param e
     * @return  Helper-Methode zum Löschen des nächsten Elementes vom übergebeben Element. Liefer true zurück,
     *          wenn das Löschen erfolgreich war, ansonsten false. Sollte das Löschen nicht möglich sein, da
     *          z.B. das nächste Element von dem übergebenen Element null ist, wird false zurück gegeben und
     *          nichts verändern.
     */
    private boolean deleteSuccessor(ListenElement e){
        ListenElement nextNextElement = null;

        if(e.getNext() != null){
            nextNextElement = e.getNext().getNext();
        } else {
            return false;
        }

        e.setNext(nextNextElement);

        return true;
    }


    /**
     *
     * @param e
     * @param l
     * @return  Helper-Methode zum überprüfen, ob das übergebene Element e in der übergebenen Liste l vorkommt.
     */
    private boolean isIn(ListenElement e, ListenElement l){
        boolean run = true;
        boolean success = false;

        ListenElement nextElementL = l;

        while(run){
            if(e.getData().equals(nextElementL.getData())){
                success = true;
                run = false;
            } else {
                nextElementL = nextElementL.getNext();
                if(nextElementL == null){
                    run = false;
                }
            }
        }

        return success;
    }
}
