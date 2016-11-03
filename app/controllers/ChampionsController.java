package controllers;

import models.Champion;
import play.mvc.Controller;
import services.ChampionService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Formation on 25/10/2016.
 */

public class ChampionsController extends Controller {

    public static void create(String ligne, Integer preference) {
        List<Champion> listeChampion = ChampionService.getAllChampions();
        renderTemplate("/Lignes/create.html",ligne,preference,listeChampion);
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
        Integer preferenceMax = getMaxValuePreference(listeChampion);
        List<Champion> listeChampion2 = new ArrayList();
        for(int i=1; i<=preferenceMax; i++){
            for(int j=0; j<listeChampion.size(); j++){
                if(listeChampion.get(j).preference==i){
                    listeChampion2.add(listeChampion.get(j));
                }
            }
        }
        renderTemplate("/Lignes/afficher.html",listeChampion2,tailleListes);
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

    public static Integer getMaxValuePreference(List<Champion> listeChampion) {
        List<Integer> preferences = new ArrayList();
        for (int i = 0; i < listeChampion.size(); ++i) {
            preferences.add(listeChampion.get(i).preference);
        }
        return Collections.max(preferences).intValue();
    }
}

