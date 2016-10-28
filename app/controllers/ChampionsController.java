package controllers;

import models.Champion;
import play.mvc.Controller;
import services.ChampionService;

import java.util.List;

/**
 * Created by Formation on 25/10/2016.
 */

public class ChampionsController extends Controller {

    public static void create(String ligne) {
        renderTemplate("/Lignes/create.html",ligne);
    }

    public static void save(Champion champion) {
        ChampionService.enregistrer(champion);
        afficher();
    }

    public static void afficher() {
        List<Champion> listeChampion = ChampionService.getAllChampions();
        renderTemplate("/Lignes/afficher.html",listeChampion);
    }
}
