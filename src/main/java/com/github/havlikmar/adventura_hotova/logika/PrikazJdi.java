/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.havlikmar.adventura_hotova.logika;

/**
 * Třída PrikazJdi implementuje pro hru příkaz jdi.
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author     Jarmila Pavlickova, Luboš Pavlíček, Jan Říha, upravil Martin Havlík
 * @version    LS 2016/2017 (upraveno 17.5.2017)
 */
public class PrikazJdi implements IPrikaz {
    private static final String NAZEV = "jdi";
    private HerniPlan plan;

    /**
    * Konstruktor třídy
    *
    * @param    plan herní plán, ve kterém se bude ve hře "chodit" 
    */    
    public PrikazJdi(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     * Provádí příkaz "jdi". Zkouší se vyjít do zadané lokace. Pokud lokace
     * existuje, vstoupí se do nového lokace. Pokud zadaná sousední lokace
     * (východ) není, vypíše se chybové hlášení.
     *
     * @param     parametry jako parametr obsahuje jméno lokace (východu), do kterého se má jít.
     * @return    zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String proved(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (sousední lokace), tak ....
            return "Kam mám jít? Musíš zadat jméno východu";
        }

        String smer = parametry[0];

        // zkoušíme přejít do sousední lokace
        Lokace sousedniLokace = plan.getAktualniLokace().vratSousedniLokaci(smer);

        if (sousedniLokace == null || !sousedniLokace.isDosazitelny()) {
            return "Do lokace: " + smer + " se odsud jít nedá!";
        }
        
        if (sousedniLokace.isZamcena()) {
            return "Lokace: " + smer + " je zamčená. K jejímu odemčení potřebuješ klíč";
        }
        else {
            plan.setAktualniLokace(sousedniLokace);
            String popis = sousedniLokace.dlouhyPopis();
            
            if (plan.getBatoh().vypisBatohu().equals("batoh:")) {
                return popis;
            }
            else {
                popis = popis + "\n" + plan.getBatoh().vypisBatohu();
                return popis;
            }
        }
    }

    /**
     * Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     * @return    nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}