package controllers;

import models.Champion;
import play.mvc.Controller;
import services.ChampionService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Formation on 25/10/2016.
 */

public class ChampionsController extends Controller {

    public static void create(String ligne, Integer preference, Boolean modeSuppression) {
        List<Champion> listeChampion = ChampionService.getAllChampions();
        renderTemplate("/Lignes/create.html",ligne,preference,listeChampion,modeSuppression);
    }

    public static void save(Champion champion) {
        ChampionService.enregistrer(champion);
        afficher();
    }

    public static void delete(Long idChampion) {
        ChampionService.supprimer(idChampion);
        afficher();
    }

    public static void afficher() {
        List<Champion> listeChampion = ChampionService.getAllChampions();
        List<Integer> tailleListes = getMaxValueLigne(listeChampion);
        renderTemplate("/Lignes/afficher.html",listeChampion,tailleListes);
    }

    public static List<Integer> getMaxValueLigne(List<Champion> listeChampion){
        List<Integer> longueurLigne = new ArrayList();
        List<Integer> resultat = new ArrayList();
        longueurLigne.add(listeChampion.stream().filter(champion -> champion.ligne.equals("Top")).collect(Collectors.toList()).size());
        longueurLigne.add(listeChampion.stream().filter(champion -> champion.ligne.equals("Jungle")).collect(Collectors.toList()).size());
        longueurLigne.add(listeChampion.stream().filter(champion -> champion.ligne.equals("Mid")).collect(Collectors.toList()).size());
        longueurLigne.add(listeChampion.stream().filter(champion -> champion.ligne.equals("Adc")).collect(Collectors.toList()).size());
        longueurLigne.add(listeChampion.stream().filter(champion -> champion.ligne.equals("Support")).collect(Collectors.toList()).size());
        for(int i = 0; i <= 4; i++)
        {
            resultat.add(longueurLigne.get(i));
        }
        resultat.add(Collections.max(longueurLigne));
        return resultat;
    }
}
