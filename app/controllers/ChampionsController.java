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
        List<Champion> listeChampion = trier(ChampionService.getAllChampions());
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

    public static void changementPreference(Long idChampion, Champion champion) {
        ChampionService.changerPreference(idChampion,champion.preference);
        afficher();
    }

    public static List<Champion> trier(List<Champion> listeChampion){
        List<Champion> listeChampionTriee = new ArrayList();
        Integer preferenceMax = getMaxValuePreference(listeChampion);
        for(int i=1; i<=preferenceMax; i++){
            for(int j=0; j<listeChampion.size(); j++){
                if(listeChampion.get(j).preference==i){
                    listeChampionTriee.add(listeChampion.get(j));
                }
            }
        }
        return listeChampionTriee;
    }

    public static void afficher() {
        List<Integer> tailleListes = getMaxValueLigne(ChampionService.getAllChampions());
        List<Champion> listeChampion = trier(ChampionService.getAllChampions());
        int ordre = 1;
        List<Champion> poolChampions = combinaisons(listeChampion,ordre);
        renderTemplate("/Lignes/afficher.html",listeChampion,tailleListes,poolChampions);
    }

    public static List<Integer> getMaxValueLigne(List<Champion> listeChampion){
        List<Integer> longueurLigne = new ArrayList();
        List<Integer> resultat = new ArrayList();
        longueurLigne.add(listeChampion.stream().filter(champion -> champion.ligne.equals("Top")).collect(Collectors.toList()).size());
        longueurLigne.add(getMaxValuePreference(listeChampion.stream().filter(champion -> champion.ligne.equals("Top")).collect(Collectors.toList())));
        longueurLigne.add(listeChampion.stream().filter(champion -> champion.ligne.equals("Jungle")).collect(Collectors.toList()).size());
        longueurLigne.add(getMaxValuePreference(listeChampion.stream().filter(champion -> champion.ligne.equals("Jungle")).collect(Collectors.toList())));
        longueurLigne.add(listeChampion.stream().filter(champion -> champion.ligne.equals("Mid")).collect(Collectors.toList()).size());
        longueurLigne.add(getMaxValuePreference(listeChampion.stream().filter(champion -> champion.ligne.equals("Mid")).collect(Collectors.toList())));
        longueurLigne.add(listeChampion.stream().filter(champion -> champion.ligne.equals("Adc")).collect(Collectors.toList()).size());
        longueurLigne.add(getMaxValuePreference(listeChampion.stream().filter(champion -> champion.ligne.equals("Adc")).collect(Collectors.toList())));
        longueurLigne.add(listeChampion.stream().filter(champion -> champion.ligne.equals("Support")).collect(Collectors.toList()).size());
        longueurLigne.add(getMaxValuePreference(listeChampion.stream().filter(champion -> champion.ligne.equals("Support")).collect(Collectors.toList())));
        for(int i = 0; i <= 9; i++)
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

    public static List<Champion> combinaisons(List<Champion> listeChampion, int ordre){
        final int TOP=0;
        final int JUNGLE=4;
        final int MID=1;
        final int ADC=2;
        final int SUPPORT=3;
        List<Champion> listeStatique1 = new ArrayList();
        List<Champion> listeStatique2 = new ArrayList();
        List<Champion> listeStatique3 = new ArrayList();
        List<Champion> listeStatique4 = new ArrayList();
        List<Champion> listeStatique5 = new ArrayList();
        List<Champion> liste1 = new ArrayList();
        List<Champion> liste2 = new ArrayList();
        List<Champion> liste3 = new ArrayList();
        List<Champion> liste4 = new ArrayList();
        List<Champion> liste5 = new ArrayList();
        switch (ordre) {
            case 1:
                for(int i=0;i<listeChampion.size();i++){
                    if(listeChampion.get(i).ligne.equals("Top")){
                        listeStatique1.add(listeChampion.get(i));
                    }
                    if(listeChampion.get(i).ligne.equals("Jungle")){
                        listeStatique2.add(listeChampion.get(i));
                    }
                    if(listeChampion.get(i).ligne.equals("Mid")){
                        listeStatique3.add(listeChampion.get(i));
                    }
                    if(listeChampion.get(i).ligne.equals("Adc")){
                        listeStatique4.add(listeChampion.get(i));
                    }
                    if(listeChampion.get(i).ligne.equals("Support")){
                        listeStatique5.add(listeChampion.get(i));
                    }
                }
                liste1.addAll(listeStatique1);
                liste2.addAll(listeStatique2);
                liste3.addAll(listeStatique3);
                liste4.addAll(listeStatique4);
                liste5.addAll(listeStatique5);
                break;
            case 2:
                for(int i=0;i<listeChampion.size();i++){
                    if(listeChampion.get(i).ligne.equals("Jungle")){
                        listeStatique1.add(listeChampion.get(i));
                    }
                    if(listeChampion.get(i).ligne.equals("Top")){
                        listeStatique2.add(listeChampion.get(i));
                    }
                    if(listeChampion.get(i).ligne.equals("Mid")){
                        listeStatique3.add(listeChampion.get(i));
                    }
                    if(listeChampion.get(i).ligne.equals("Adc")){
                        listeStatique4.add(listeChampion.get(i));
                    }
                    if(listeChampion.get(i).ligne.equals("Support")){
                        listeStatique5.add(listeChampion.get(i));
                    }
                }
                liste1.addAll(listeStatique1);
                liste2.addAll(listeStatique2);
                liste3.addAll(listeStatique3);
                liste4.addAll(listeStatique4);
                liste5.addAll(listeStatique5);
                break;
        }
        LinkedList<Champion> listeChampions = new LinkedList<>();
        LinkedList<Champion> listeTemp = new LinkedList<>();
        List<Champion> listeCombosPossibles = new ArrayList<>();
        int j =1;
        int res =1;
        listeTemp.addAll(liste1);

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
                            for(int i=0;i<5;i++){
                                listeCombosPossibles.add(listeChampions.get(i));
                            }
                            listeChampions.removeLast();
                            res = res + 1;
                            break;
                        case 4:
                            listeTemp.clear();
                            liste4.remove(champManipule);
                            listeTemp.addAll(liste5);
                            j = j + 1;
                            break;
                        case 3:
                            listeTemp.clear();
                            liste3.remove(champManipule);
                            listeTemp.addAll(liste4);
                            j = j + 1;
                            break;
                        case 2:
                            listeTemp.clear();
                            liste2.remove(champManipule);
                            listeTemp.addAll(liste3);
                            j = j + 1;
                            break;
                        case 1:
                            listeTemp.clear();
                            liste1.remove(champManipule);
                            listeTemp.addAll(liste2);
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
                            listeTemp.addAll(liste4);
                            break;
                        case 3:
                            listeTemp.clear();
                            liste4.addAll(listeStatique4);
                            listeTemp.addAll(liste3);
                            break;
                        case 2:
                            listeTemp.clear();
                            liste3.addAll(listeStatique3);
                            listeTemp.addAll(liste2);
                            break;
                        case 1:
                            listeTemp.clear();
                            liste2.addAll(listeStatique2);
                            listeTemp.addAll(liste1);
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
}

