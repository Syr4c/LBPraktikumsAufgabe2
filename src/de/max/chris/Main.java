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

    public ListenElement diffList(ListenElement liste1, ListenElement liste2){
        boolean run = true;

        while(run){
            if(liste1 == null){
                run = false;
            }

            if(isIn(liste1, liste2) && run){
                liste2 = delElement("a", liste1, liste2);
            }

            if(liste1 != null){
                liste1 = liste1.getNext();
            }
        }

        return liste2;
    }


    /**
     *
     * @param i
     * @param l
     * @return
     */
    public boolean suffix(ListenElement i, ListenElement l){
        boolean success = false;
        boolean run = true;
        ListenElement ibackup = i;
        ListenElement lbackup = l;

        while(!success){
            if(i.getData().equals(l.getData())){
                ListenElement nextInnerELementI = i.getNext();
                ListenElement nextInnerElementL = l.getNext();

                while(run){
                    if(nextInnerELementI == null && nextInnerElementL == null){
                        run = false;
                        success = true;
                    }

                    if(nextInnerELementI.getData().equals(nextInnerElementL.getData()) && run){
                        nextInnerELementI = nextInnerElementL.getNext();
                        nextInnerElementL = nextInnerElementL.getNext();
                    } else {
                        run = false;
                    }
                }
            }

            l = l.getNext();
        }

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

                       if(nextInnerElementI.getData().equals(nextInnerElementL.getData()) && run){
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

    private ListenElement delE(ListenElement e, ListenElement returnList){
        boolean run = true;

        // Spezialfall, falls das Element e direkt dem ersten Element aus returnList entspricht.
        if(e.getData().equals(returnList.getData())){
            returnList = returnList.getNext();
            run = false;
        }

        while(run){
            // Durch die Struktur der einmal verketteten Liste ist es nötig das vorangegangene Element
            // zwischen zu speichern, da es sonst nicht möglich ist das überprüfte Element zu löschen
            // und die Liste wieder zusammenzufügen.
            ListenElement nextElementL = returnList.getNext();
            ListenElement savePrevious = returnList;

            if(nextElementL == null){
                run = false;
            } else {
                if(e.getData().equals(nextElementL.getData())){
                    deleteSuccessor(savePrevious);
                    run = false;
                }
            }
        }

        return returnList;
    }

    private ListenElement delL(ListenElement e, ListenElement returnList){
        boolean run = true;

        // Spezialfall, Element e ist gleich dem ersten Element von returnList
        // und e kommt nicht weiter in returnList vor.
        if(e.getData().equals(returnList.getData()) && !isIn(e, returnList)){
            returnList = returnList.getNext();
            run = false;
        }

        while(run){
            ListenElement nextElementL = returnList.getNext();
            ListenElement savePrevious = returnList;

            if(nextElementL == null){
                run = false;
            } else {
                if(e.getData().equals(nextElementL.getData()) && !isIn(e, returnList)){
                    deleteSuccessor(savePrevious);
                    run = false;
                }
            }
        }

        return returnList;
    }

    private ListenElement delA(ListenElement e, ListenElement returnList){
        boolean run = true;

        if(e.getData().equals(returnList.getData())){
            returnList = returnList.getNext();
        }

        while(run){
            ListenElement nextElementL = returnList.getNext();
            ListenElement savePrevious = returnList;

            if(nextElementL == null){
                run = false;
            } else {
                if(e.getData().equals(nextElementL.getData())){
                    deleteSuccessor(savePrevious);
                }
            }
        }

        return returnList;
    }


    public ListenElement substitute(String p, ListenElement e1, ListenElement e2, ListenElement returnList){
        if(p.equals("e")){
            returnList = substituteE(e1, e2, returnList);
        } else if(p.equals("l")) {
            returnList = substituteL(e1, e2, returnList);
        } else if(p.equals("a")) {
            returnList = substituteA(e1, e2, returnList);
        } else {
            throw new IllegalArgumentException("p hat einen falschen Wert erhalten");
        }

        return returnList;
    }

    public ListenElement substituteE(ListenElement e1, ListenElement e2, ListenElement returnList){

    }

    public ListenElement substituteL(ListenElement e1, ListenElement e2, ListenElement returnList){

    }

    public ListenElement substituteA(ListenElement e1, ListenElement e2, ListenElement returnList){
        boolean run = true;

        // Spezialfall
        if(returnList.getData().equals(e1.getData())){
            e2.setNext(returnList);
        }
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
