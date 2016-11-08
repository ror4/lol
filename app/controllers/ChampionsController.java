package controllers;

import models.Champion;
import play.mvc.Controller;
import services.ChampionService;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

/**
 * Created by Formation on 25/10/2016.
 */

public class ChampionsController extends Controller {

    public static void create(String ligne, int preference) {
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

    public static void changementPreference(Champion champion, int pref, Long idChampion) {
        ChampionService.changerPreference(champion, pref, idChampion);
        afficher();
    }

    public static void afficher() {
        List<Integer> tailleListes = getMaxValueLigne(ChampionService.getAllChampions());
        Integer preferenceMax = getMaxValuePreference(ChampionService.getAllChampions());
        List<Champion> listeChampion = new ArrayList();
        for(int i=1; i<=preferenceMax; i++){
            for(int j=0; j<ChampionService.getAllChampions().size(); j++){
                if(ChampionService.getAllChampions().get(j).preference==i){
                    listeChampion.add(ChampionService.getAllChampions().get(j));
                }
            }
        }
        List<Champion> poolChampions = combinaisons(listeChampion);
        renderTemplate("/Lignes/afficher.html",listeChampion,tailleListes,poolChampions);
    }

    public static List<Champion> combinaisons(List<Champion> listeChampion){
        final int TOP=0;
        final int JUNGLE=4;
        final int MID=1;
        final int ADC=2;
        final int SUPPORT=3;
        List<Champion> tops = new ArrayList();
        List<Champion> mids = new ArrayList();
        List<Champion> adcs = new ArrayList();
        List<Champion> supports = new ArrayList();
        List<Champion> junglers = new ArrayList();

        for(int i=0;i<listeChampion.size();i++){
            if(listeChampion.get(i).ligne.equals("Top")){
                tops.add(listeChampion.get(i));
            }
            if(listeChampion.get(i).ligne.equals("Jungle")){
                junglers.add(listeChampion.get(i));
            }
            if(listeChampion.get(i).ligne.equals("Mid")){
                mids.add(listeChampion.get(i));
            }
            if(listeChampion.get(i).ligne.equals("Adc")){
                adcs.add(listeChampion.get(i));
            }
            if(listeChampion.get(i).ligne.equals("Support")){
                supports.add(listeChampion.get(i));
            }
        }

        List<Champion> listeTop = new ArrayList();
        listeTop.addAll(tops);
        List<Champion> listeMid = new ArrayList();
        listeMid.addAll(mids);
        List<Champion> listeAdc = new ArrayList();
        listeAdc.addAll(adcs);
        List<Champion> listeSupport = new ArrayList();
        listeSupport.addAll(supports);
        List<Champion> listeJungle = new ArrayList();
        listeJungle.addAll(junglers);
        LinkedList<Champion> listeChampions = new LinkedList<>();
        LinkedList<Champion> listeTemp = new LinkedList<>();
        List<Champion> listeCombosPossibles = new ArrayList<>();
        int j =1;
        int res =1;
        listeTemp.addAll(listeTop);

        while (j < 6) {
            if (listeTemp.size() > 0) {
                Champion champManipule = listeTemp.pollFirst();
                listeChampions.add(champManipule);
                if (test(listeChampions)) {
                    switch (j) {
                        case 5:
//                            System.out.print("Resultat " + res + " : ");
//                            System.out.print(listeChampions.get(TOP).name + "(top), ");
//                            System.out.print(listeChampions.get(JUNGLE).name + "(jungle), ");
//                            System.out.print(listeChampions.get(MID).name + "(mid), ");
//                            System.out.print(listeChampions.get(ADC).name + "(adc), ");
//                            System.out.print(listeChampions.get(SUPPORT).name + "(support)");
//                            System.out.println("");
                            listeCombosPossibles.add(listeChampions.get(0));
                            listeChampions.removeLast();
                            res = res + 1;
                            break;
                        case 4:
                            listeTemp.clear();
                            listeSupport.remove(champManipule);
                            listeTemp.addAll(listeJungle);
                            j = j + 1;
                            break;
                        case 3:
                            listeTemp.clear();
                            listeAdc.remove(champManipule);
                            listeTemp.addAll(listeSupport);
                            j = j + 1;
                            break;
                        case 2:
                            listeTemp.clear();
                            listeMid.remove(champManipule);
                            listeTemp.addAll(listeAdc);
                            j = j + 1;
                            break;
                        case 1:
                            listeTemp.clear();
                            listeTop.remove(champManipule);
                            listeTemp.addAll(listeMid);
                            j = j + 1;
                            break;
                    }
                }
                else {
                    listeChampions.removeLast();
                }
            }
            else {
                if (j == 1) {
                    System.out.println("fin");
                    j=6;
                }
                else {
                    listeChampions.removeLast();
                    j = j - 1;
                    switch (j) {
                        case 4:
                            listeTemp.clear();
                            listeTemp.addAll(listeSupport);
                            break;
                        case 3:
                            listeTemp.clear();
                            listeSupport.addAll(supports);
                            listeTemp.addAll(listeAdc);
                            break;
                        case 2:
                            listeTemp.clear();
                            listeAdc.addAll(adcs);
                            listeTemp.addAll(listeMid);
                            break;
                        case 1:
                            listeTemp.clear();
                            listeMid.addAll(mids);
                            listeTemp.addAll(listeTop);
                            break;
                    }
                }
            }
        }
        return listeCombosPossibles;
    }

    public static boolean test(LinkedList<Champion> listeChampion){
        return true;
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

    public static int getMaxValuePreference(List<Champion> listeChampion) {
        List<Integer> preferences = new ArrayList();
        for (int i = 0; i < listeChampion.size(); ++i) {
            preferences.add(listeChampion.get(i).preference);
        }
        if(preferences.size()!=0){
            return Collections.max(preferences);
        }
        else{
            return 0;
        }
    }
}

