package services;

import models.Champion;

import java.util.List;

/**
 * Created by Formation on 26/10/2016.
 */
public class ChampionService {

    public static void enregistrer(Champion champion) {
        //quelles sont mes criteres pour ajouter une ligne ??
        if(!champion.nom.isEmpty()) {// le champ nom pas vide
            // et il faut que le couple (nom,ligne) n'existe pas
            List<Champion> liste = Champion.find("nom = ? AND ligne=?",champion.nom,champion.ligne).fetch();
            if(liste.size() ==0){
                champion.save();
            }
        }
    }

    public static void supprimer(Long idChampion) {
        Champion.delete("id=?",idChampion);
    }

    public static void changerPreference(Champion champion, int pref, Long idChampion) {
        Champion champion2 = new Champion();
        champion2.nom = champion.nom;
        champion2.ligne = champion.ligne;
        champion2.preference=pref;
        Champion.delete("id=?",idChampion);
        champion2.save();
    }

    public static List<Champion> getAllChampions(){
        return Champion.findAll();
    }


}
