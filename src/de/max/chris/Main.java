package de.max.chris;

import java.util.ArrayList;

public class Main {


    public boolean isAList(ListenElement l){
      boolean success = false;

      if(l.getNext() != null){
          isAList(l.getNext());
      } else {
          success = true;
      }

      return true;
    }

    public ListenElement diffList(ListenElement liste1, ListenElement liste2){
        boolean run = true;

        // Die Methode durchläuft alle Elemente der übergebenen Liste.
        while(run){
            // Wenn das Ende der Liste erreicht ist, bricht die Schleife am Ende ab
            // und gibt die Ergebnisliste zurück.
            if(liste2 == null){
                run = false;
            }

            // Es wird überprüft, ob das zu überprüfende Element aus der 2. Liste (L2)
            // in 1. Liste (L1) vorkommt. Wenn das so ist, wird das Element aus der 1.
            // Liste gelöscht.
            if(isIn(liste2, liste1) && run){
                liste1 = delElement("a", liste2, liste1);
            }

            // Es wird der Pointer auf das nächste ListenElement gesetzt und die Schleife
            // wiederholt sich.
            if(liste2 != null){
                liste2 = liste2.getNext();
            }
        }

        return liste1;
    }

    // WICHTIG WICHTIG WICHTIG WICHTIG WICHTIG WICHTIG WICHTIG
    public boolean suffix(ListenElement s, ListenElement l){
        boolean success = false;
        boolean run = true;
        ListenElement sbackup = s;
        ListenElement lbackup = l;

        // Die Schleife iteriert über die übergebene Liste l.
        // bei jedem Element von l wir überprüft, ob es dem ersten Element aus
        // s entspricht.
        while(!success){
            if(s.getData().equals(l.getData())){
                ListenElement nextInnerELementS = s.getNext();
                ListenElement nextInnerElementL = l.getNext();

                // Sollte dieser Fall eintreten, springt die Methode in den inneren Schleifenblock
                // und überprüft ab nun jedes folgenden Element von l mit jedem folgenden Element von s.
                while(run){

                    // sind alle Elemente 'gleich' und am Ende weisen beide Listen leere Listen (null) auf,
                    // ist s ein suffix von l und die Methode gibt true zurück.
                    if(nextInnerELementS == null && nextInnerElementL == null){
                        run = false;
                        success = true;
                    }

                    // Sobald die beiden aktuell überprüften Elemente nicht mehr gleich sind, bricht die
                    // innere Schleife ab, da s zumindest bis zu diesem Zeitpunkt kein suffix von l sein kann.
                    if(nextInnerELementS.getData().equals(nextInnerElementL.getData()) && run){
                        nextInnerELementS = nextInnerElementL.getNext();
                        nextInnerElementL = nextInnerElementL.getNext();
                    } else {
                        run = false;
                    }
                }

                // Resett von s auf den Startzustand, setzen von l auf den Pointer der aktuell erreichten Position.
                s = sbackup;
                l = nextElementL;
            }

            l = l.getNext();
        }

        // Die Methode gibt den Inhalt der Boolean Variable success zurück.
        return success;
    }


    public boolean infix(ListenElement i, ListenElement l){
        boolean run = true;
        boolean success = false;

        ListenElement nextElementI = i;
        ListenElement backupi = i;
        ListenElement nextElementL = l;

        // Die Schleife iteriert über die übergebene Liste l.
        while(!success){

               // Sobald das aktuell überprüfte Element aus l gleich dem ersten Element aus
               // s ist, überprüft die Methode alle weiteren aufeinander folgenden Element
               // beider Listen, ob diese gleich sind.
               if(nextElementI.getData().equals(nextElementL.getData())){
                   ListenElement nextInnerElementI = nextElementI.getNext();
                   ListenElement nextInnerElementL = nextElementL.getNext();

                   while(run){

                        // Sollte i am ende eine Leereliste (null)
                        // enthalten und alle vorheriegen Elemente waren identisch
                        // verlässt die Methode die Schleife und gibt true zurück, da i ein infix von l ist.
                       if(nextInnerElementI == null){
                           run = false;
                           success = true;
                       }

                       // Die Elemente aus i und l werden miteinander verglichen.
                       if(nextInnerElementI.getData().equals(nextInnerElementL.getData()) && run){

                            // Pointer auf die nächsten Elemente werden gesetzt.
                           nextInnerElementI = nextInnerElementI.getNext();
                           nextInnerElementL = nextInnerElementL.getNext();
                       } else {
                           run = false;
                       }
                   }
               }

               // Sollte i noch nicht am Ende sein, wird i resettet, so das bei dem
               // nächsten durchlauf wieder das erste Element aus i mit dem aktuellen
               // Element aus l verglichen werden kann.
               i = backupi;
               nextElementL = nextElementL.getNext();
               run = true;
        }

        return success;
    }

    public boolean praefix(ListenElement p, ListenElement l){
        boolean run = true;
        boolean success = false;

        // Es wird überprüft, ob das erste Element aus p gleich dem ersten Element aus l ist.
        // Sollte dies der Fall sein, geht die Methode in die if Abfrage.
        if(p.getData().equals(l.getData())){
            ListenElement nextElementL = l.getNext();
            ListenElement nextElementP = p.getNext();

            // Sollte das nächste Element der übergebenen p Liste null sein und alle davor gefolgenden
            // Element waren identisch mit denen die in l überprüft wurden, gibt die Methode true
            // zurück, da p ein Präfix von l ist.
            while(run){
                if(nextElementP == null){
                    run = false;
                    success = true;
                }

                // Jedes aktuelle Element aus l und p werden miteinander verglichen, sind sie
                // indentisch werden die Pointer auf die darauf folgenden Elemente gesetzt.
                // Sollten sie nicht identisch sein, wird die Schleife abgebrochen und die
                // Methode gibt false zurück, da p kein Präfix von l ist.
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

        // Die übergebene Liste wird Element für Element durchlaufen.
        while(run){
            ListenElement saveCurrent = l;
            ListenElement nextElement = l.getNext();

            // Sollte das nächste Element vom aktuellen Element die leere Liste sein, wird die
            // boolean Variable run auf null gesetzt und somit die Schleife abgebrochen, alle bisherigen
            // Ergebnise werden zurück gegeben.
            if(nextElement == null){
                run = false;
            } else {
                // Es wird überprüft, ob das aktuelle Elemente eine Liste beinhaltet.
                Object getData = nextElement.getData();
                ListenElement dataCast = (ListenElement) getData;

                // Sollte dies der Fall sein, wird die Methode eoCount rekursiv mit dem ausgelesenen
                // ListenElement aufgerufen. Sobald die rekursive Methode eoCount das Ergebnis zurück
                // gibt, wird es mit den bereits gesammelten Informationen addiert
                if (isAList(dataCast)) {
                    ArrayList<Integer> rekursionList = eoCount(nextElement);
                    returnList.set(0, (returnList.get(0) + rekursionList.get(0)));
                    returnList.set(1, (returnList.get(1) + rekursionList.get(1)));
                }

                // Bei jedem Schleifendurchlauf wechselt der Typ der Liste von Even zu Odd
                if (aktuellerTyp == ListType.EVEN) {
                    aktuellerTyp = ListType.ODD;
                } else {
                    aktuellerTyp = ListType.EVEN;
                }
            }
        }

        // Am Ende jeder Listen Iteration wird das jeweilige Ergbnis (ob eine überprüfte Liste gerade oder unngerade ist)
        // ausgewertet und ein jeweiliger Wert addiert.
        if(aktuellerTyp == ListType.EVEN){
            returnList.set(0,(returnList.get(0) + 1));
        } else {
            returnList.set(1,(returnList.get(1) + 1));
        }

        return returnList;
    }


    // Die Methode entscheidet je nach übergebener Position, welche Teilmethode aufgerufen wird.
    // e steht für: das erste Vorkommen des übergebene Elementes wird gelöscht
    // l steht für: das letzte Vorkommen des übergebene Elementes wird gelöscht
    // a steht für: alle Vorkommen des übergebene Elementes werden gelöscht.
    public ListenElement delElement(String p, ListenElement e, ListenElement l){
        ListenElement returnList = null;

        if(p.equals("e")){
            returnList = delE(e, l);

        } else if(p.equals("l")){
            returnList = delL(e, l);

        } else if(p.equals("a")){
            returnList = delA(e, l);

            // Sollte ein Wert übergeben werden, der nicht in der Menge der erlaubten Position liegt,
            // wirft die Methode eine IllegalArgumentException.
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
