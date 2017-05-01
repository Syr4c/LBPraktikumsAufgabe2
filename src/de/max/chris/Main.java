package de.max.chris;

import java.util.ArrayList;

public class Main {

    // [fixed #2]
    public boolean isAList(ListenElement l){
      boolean success = false;

      // Es wird überprüft, ob das nächste Element das Ende der Liste ist.
      if(l.getNext() != null){
          // Rekursiver Aufruf mit dem nächsten Element.
          isAList(l.getNext());
      } else {
          // Das Listen Ende ist erreicht, success wird auf true gesetzt
          success = true;
      }

      return success;
    }

    // [fixed #2]
    public ListenElement diffList(ListenElement liste1, ListenElement liste2){
        boolean nextNull = false;

        if(liste1 == null){
            nextNull = true;
        }

        if(isIn(liste2, liste1)){
            delA(liste2, liste1);
            diffList(liste1, liste2.getNext());
        } else {
            diffList(liste1, liste2.getNext());
        }

        return liste1;
    }


    public boolean suffix(ListenElement s, ListenElement l){

    }

    public boolean infix(ListenElement i, ListenElement l){

    }

    public boolean praefix(ListenElement p, ListenElement l){
        if(p.getData().equals(l.getData())){
            return true;
        } else {
            return false;
        }
    }

    // [fixed #2]
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


    // [Entscheidungs Methode]
    public ListenElement delElement(String p, ListenElement e, ListenElement l){
        // Die Methode entscheidet je nach übergebener Position, welche Teilmethode aufgerufen wird.
        // e steht für: das erste Vorkommen des übergebene Elementes wird gelöscht
        // l steht für: das letzte Vorkommen des übergebene Elementes wird gelöscht
        // a steht für: alle Vorkommen des übergebene Elementes werden gelöscht.

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

    // [fixed #2]
    private ListenElement delE(ListenElement e, ListenElement returnList){
        boolean nextNull = false;
        ListenElement nextList;

        if(returnList == null){
            nextNull = true;
        }

        // Sollte das gesuchte Element gleich dem aktuell überprüften Element sein,
        // gibt die Methode den Pointer auf das nächste Element zurück, somit wird
        // das gesuchte Objekt entfernt.
        if(e.getData().equals(returnList.getData()) && !nextNull){
            return returnList.getNext();
        } else {
            // Sollten die Elemente nicht identisch sein, wird rekursiv die Methode ein weiteres Mal aufgerufen.
            nextList = delE(e, returnList.getNext());
        }

        return append(returnList, nextList);
    }

    // [fixed 2]
    private ListenElement delL(ListenElement e, ListenElement returnList){
        boolean nextNull = false;
        ListenElement nextList;

        if(returnList == null){
            nextNull = true;
        }

        // Sollte das gesuchte element gleich dem aktuell überprüften Element sein und das Element
        // nicht weiter in dem Rest der Liste vorhanden sein, wird das Element gelöscht.
        if(e.getData().equals(returnList.getData()) && !nextNull && !isIn(e, returnList)){
            return returnList.getNext();
        } else {
            // Ansonsten wird rekursiv die gleiche Methode noch einmal aufgerufen.
            nextList = delL(e, returnList.getNext());
        }

        return append(returnList, nextList);
    }

    // [fixed 2]
    private ListenElement delA(ListenElement e, ListenElement returnList){
        boolean nextNull = false;
        ListenElement nextList;

        if(returnList == null){
            nextNull = true;
        }

        // Sollte das aktuelle Element gleich dem gesuchten Element sein, wird die Methode
        // delA erneut rekursiv aufgerufen.
        if(e.getData().equals(returnList.getData()) && !nextNull){
            return delA(e, returnList.getNext());
        } else {
            // Wenn der Fall eintritt, dass die Elemente nicht identisch sind, wird die Methode
            // ebenfalls rekursiv erneut aufgerufen, jedoch wird die Rückgabe appended, somit wird
            // kein Element gelöscht.
            nextList = delA(e, returnList.getNext());
        }

        return append(returnList, nextList);
    }

    // [Entscheidungs Methode]
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

    // [fixed 2]
    public ListenElement substituteE(ListenElement e1, ListenElement e2, ListenElement returnList){
        boolean nextNull = false;
        ListenElement nextList;

        // Sollte die leere Liste als Pointer übergeben worden sein, terminiert die Methode.
        if(returnList == null){
            nextNull = true;
        }

        // Sollte das aktuelle Element gleich dem gesuchten Element sein, gibt die Methode
        // eine Liste zurück, welche nicht mehr e1, sondern an dessen stelle e2 enthält.
        if(returnList.getData().equals(e1.getData()) && !nextNull){
            return append(e2, returnList.getNext());
        } else {
            // Sollte dieser Fall nicht eintreten, wird die Methode rekursiv mit dem nächsten
            // Element aufgerufen.
            nextList = substituteE(e1, e2, returnList.getNext());
        }

        // "Auf dem Rückweg" wird die erhaltene neue Liste mit dem vorherigen returnList Objekt appended
        return append(returnList, nextList);
    }

    // [fixed 2]
    public ListenElement substituteL(ListenElement e1, ListenElement e2, ListenElement returnList){
        boolean nextNull = false;
        ListenElement nextList;

        // Sollte die leere Liste als Pointer übergeben worden sein, terminiert die Methode.
        if(returnList == null){
            nextNull = true;
        }

        // Sollte das aktuelle Element gleich dem gesuchten Element sein und befindet sich in
        // der weiteren Liste kein weiteres Vorkommen, gibt die Methode eine neue Liste zurück,
        // in der das Element e1 mit dem Element e2 ausgetauscht wurde.
        if(returnList.getData().equals(e1.getData()) && !nextNull && !isIn(e1, returnList)){
            return append(e2, returnList.getNext());
        } else {
            // Sollte dieser Fall nicht eintreten, wird die Methode rekursiv erneut aufgerufen.
            nextList = substituteE(e1, e2, returnList.getNext());
        }

        // "Auf dem Rückweg" wird die erhaltene neue Liste mit dem vorherigen returnList Objekt appended
        return append(returnList, nextList);
    }

    // [fixed 2]
    public ListenElement substituteA(ListenElement e1, ListenElement e2, ListenElement returnList){
        boolean nextNull = false;
        ListenElement nextList;

        // Sollte die leere Liste als Pointer übergeben worden sein, terminiert die Methode.
        if(returnList == null){
            nextNull = true;
        }

        // Sollte das aktuelle Element gleich dem gesuchten Element sein, gibt die Methode
        // nach einem rekursiven Aufruf von sich selber, eine appended Liste zurück, in der
        // e1 mit e2 getauscht wurde.
        if(returnList.getData().equals(e1.getData()) && !nextNull){
            return substituteA(e1, e2, append(e2, returnList.getNext()));
        } else {
            // Sollten die Elemente nicht identisch sein, wird die Methode ohne Struktur Veränderungen
            // erneut rekursiv aufgerufen.
            nextList = substituteA(e1, e2, returnList.getNext());
        }

        // "Auf dem Rückweg" wird die erhaltene neue Liste mit dem vorherigen returnList Objekt appended
        return append(returnList, nextList);
    }

    //[fixed #2]
    private boolean isIn(ListenElement e, ListenElement l){
        boolean success = false;
        boolean nextNull = false;

        if(l.getNext() == null){ nextNull = true; }

        // Überprüfung von Objektgleichheit, wenn Objekte gleich sind, wird true zurück
        // gegeben.
        if(e.getData().equals(l.getData()) && !nextNull){
            success = true;
        } else {
            // Ansnonsten wird die Methode mit dem nächsten Element aus l aufgerufen.
            isIn(e, l.getNext());
        }

        return success;
    }

    //[fixed #2]
    private ListenElement append(ListenElement e, ListenElement p){
        //Es wird überprüft, ob das letzte Element von e erreicht wurde
        //wenn das passiert ist, appended er p an e.
        if(e.getNext() == null){
            e.setNext(p);
        } else {
            //Ansonsten wird append rekursiv wieder aufgerufen.
            append(e.getNext(), p);
        }

        return e;
    }


    public static void main(String[] args) {
        System.out.println("Hier kann dann alles ausgeführt werden!");
    }
}
