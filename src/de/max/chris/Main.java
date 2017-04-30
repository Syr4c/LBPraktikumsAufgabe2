package de.max.chris;

import java.util.ArrayList;

public class Main {

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
            if(liste2 == null){
                run = false;
            }
            if(isIn(liste2, liste1) && run){
                liste1 = delElement("a", liste2, liste1);
            }
            if(liste2 != null){
                liste2 = liste2.getNext();
            }
        }

        return liste1;
    }


    /**
     *
     * @param i
     * @param l
     * @return  Die Methode überprüft, ob eine übergebene Liste i dem Endteil der Liste l
     *          entspricht.
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

    public ArrayList<Integer> eoCount(ListenElement l){
        boolean run = true;
        ArrayList<Integer> returnList = new ArrayList<>();
        returnList.add(0); //even
        returnList.add(0); //odd
        ListType aktuellerTyp = ListType.EVEN;

        while(run){
            ListenElement saveCurrent = l;
            ListenElement nextElement = l.getNext();

            if(nextElement == null){
                run = false;
            } else {
                Object getData = nextElement.getData();
                ListenElement dataCast = (ListenElement) getData;

                if (isAList(dataCast)) {
                    ArrayList<Integer> rekursionList = eoCount(nextElement);
                    returnList.set(0, (returnList.get(0) + rekursionList.get(0)));
                    returnList.set(1, (returnList.get(1) + rekursionList.get(1)));
                }

                if (aktuellerTyp == ListType.EVEN) {
                    aktuellerTyp = ListType.ODD;
                } else {
                    aktuellerTyp = ListType.EVEN;
                }
            }
        }

        if(aktuellerTyp == ListType.EVEN){
            returnList.set(0,(returnList.get(0) + 1));
        } else {
            returnList.set(1,(returnList.get(1) + 1));
        }

        return returnList;
    }


    /**
     *
     * @param p
     * @param e
     * @param l
     * @return
     *      Precondition:
     *      - Eine Liste, eine Position und ein Element wird übergeben. P bezeichnet die Position: e erstes, l letztes, a alle Vorkommen
     *      Postcondition:
     *      - Ein Element innerhalb der übergebenen Liste wurde an der übergebenen position/en gelöscht,
     *      eventuell darauf folgende Elemente wurden richtig eingerückt, indem sie um einen Platz nach vorne verschoben worden
     *      sind und die Liste wurde zurückgeliefert(R).
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

    // [fixed]
    private ListenElement delE(ListenElement e, ListenElement returnList){
        boolean run = true;

        // Spezialfall, falls das Element e direkt dem ersten Element aus returnList entspricht.
        if(e.getData().equals(returnList.getData())){
            returnList = returnList.getNext();
            run = false;
        }

        ListenElement startReturnList = returnList;

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

            returnList = nextElementL;
        }

        return startReturnList;
    }

    // [fixed]
    private ListenElement delL(ListenElement e, ListenElement returnList){
        boolean run = true;

        // Spezialfall, Element e ist gleich dem ersten Element von returnList
        // und e kommt nicht weiter in returnList vor.
        if(e.getData().equals(returnList.getData()) && !isIn(e, returnList)){
            returnList = returnList.getNext();
            run = false;
        }

        ListenElement startReturnListe = returnList;

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

            returnList = nextElementL;
        }

        return returnList;
    }

    private ListenElement delA(ListenElement e, ListenElement returnList){
        boolean run = true;

        if(e.getData().equals(returnList.getData())){
            returnList = returnList.getNext();
        }

        ListenElement startReturnListe = returnList;

        while(run){
            ListenElement nextElementL = returnList.getNext();
            ListenElement savePrevious = nextElementL;

            if(nextElementL == null){
                run = false;
            } else {
                if(e.getData().equals(nextElementL.getData())){
                    deleteSuccessor(savePrevious);
                }
            }

            returnList = nextElementL;
        }

        return startReturnListe;
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

    // [fixed]
    public ListenElement substituteE(ListenElement e1, ListenElement e2, ListenElement returnList){
        boolean run = true;

        if(e1.getData().equals(returnList.getData())){
            e1.setNext(returnList.getNext());
            returnList = e1;
            run = false;
        }

        ListenElement startReturnList = returnList;

        while(run){
            ListenElement nextElement = returnList.getNext();
            ListenElement savePrevious = returnList;

            if(e1.getData().equals(nextElement.getData())){
                changeElement(e2, savePrevious);

                run = false;
            }

            if(!run){
                returnList = nextElement;
            }
        }

        return startReturnList;
    }

    // [fixed]
    public ListenElement substituteL(ListenElement e1, ListenElement e2, ListenElement returnList){
        boolean run = true;

        //Spezialfall, falls das erste Element der Liste e1 entspricht und e1 in der restlichen Liste nicht mehr
        //vorkommt
        if(returnList.getData().equals(e1.getData()) && !isIn(e1, returnList)){
            e2.setNext(returnList.getNext());
            returnList = e2;

            run = false;
        }

        ListenElement startReturnListe = returnList;

        while(run){
            ListenElement nextElement = returnList.getNext();
            ListenElement savePrevious = returnList;

            if(e1.getData().equals(nextElement.getData()) && !isIn(e2, nextElement)){
                changeElement(e2, savePrevious);
                run = false;
            }

            if(!run){
                returnList = nextElement;
            }
        }

        return startReturnListe;
    }


    public ListenElement substituteA(ListenElement e1, ListenElement e2, ListenElement returnList){
        // Fehler

        boolean run = true;

        // Spezialfall falls das erste Element direkt getauscht werden muss
        if(returnList.getData().equals(e1.getData())){
            e2.setNext(returnList);
            returnList = e2;
        }

        if(!isIn(e2, returnList)){
            run = false;
        }

        while(run){
            ListenElement nextElement = returnList.getNext();
            ListenElement savePrevious = returnList;

            if(nextElement == null){
                run = false;
            }

            if(nextElement.getData().equals(e1.getData()) && run){
                changeElement(e2, savePrevious);
                if(!isIn(e2, returnList)){
                    run = false;
                }
            }
        }

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

    /**
     *
     * @param e2
     * @param prev
     *
     *              Hilfsmethode um ein Element zu tauschen.
     */
    private void changeElement(ListenElement e2, ListenElement prev){
        ListenElement nextNextElement = prev.getNext().getNext();
        prev.setNext(e2);
        prev.getNext().setNext(nextNextElement);
    }

    public static void main(String[] args) {
        System.out.println("Hier kann dann alles ausgeführt werden!");
    }
}
