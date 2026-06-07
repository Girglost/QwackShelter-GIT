package quack.test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import quack.model.Benevole;
import quack.model.Canard;
import quack.model.Caractere;
import quack.model.Chat;
import quack.model.Chien;
import quack.model.Emplacement;
import quack.model.Employe;
import quack.model.Famille;
import quack.model.Genre;
import quack.model.Lieu;
import quack.model.Patron;
import quack.model.Poule;
import quack.model.QuackShelter;
import quack.model.Visiteur;
import quack.model.typeBox;
import quack.service.AnimalService;
import quack.service.EmplacementService;
import quack.service.HistoriqueSanteService;
import quack.service.LieuService;
import quack.service.PersonneService;
import quack.service.QuackShelterService;
import quack.service.StatutAnimalService;

public class TestSpringJPA {

	@Autowired
	HistoriqueSanteService santeSrv;
	@Autowired
	EmplacementService emplacementSrv;
	@Autowired
	StatutAnimalService statutSrv;
	@Autowired
	LieuService lieuSrv;
	@Autowired
	PersonneService personneSrv;
	@Autowired
	AnimalService animalSrv;
	@Autowired
	QuackShelterService quackSrv;
	
	public void run()
	{
		 //  TEST DU MAPPING JPA
		
		Lieu lieu1  = new Lieu("Shelter", "14", "Rue Qwack", "Nantes", "44100");
		Lieu lieu2  = new Lieu("Maison", "14", "Avenue Coin", "Paris", "75016");
		Lieu lieu3  = new Lieu("Appartement", "12", "Boulevard  du General Coin", "Paris", "75014");
		Lieu lieu4  = new Lieu("Appartement", "8", "Chemin du Coin", "Paris", "75008");
		Lieu testLieu = new Lieu("TESTLIEU", "8TESTLIEU", "TESTLIEU", "TESTLIEU", "TESTLIEU");
		
		lieuSrv.insert(lieu1);
		lieuSrv.insert(lieu2);
		lieuSrv.insert(lieu3);
		lieuSrv.insert(lieu4);
		
		QuackShelter quackshelter = new QuackShelter(500000, 5, lieu1);
		
		quackSrv.insert(quackshelter);
		
		Visiteur visiteur = new Visiteur("Yohann", "Yohann", "Yohann", "Yohann", lieu2,LocalDate.now());
		Patron patron = new Patron( "Ronan", "Ronan", "Ronan", "Ronan", lieu1);
		Employe employe = new Employe("Clea","Clea","Clea","Clea", lieu3,true, 800.5,LocalDate.now(),quackshelter);
		Benevole benevole = new Benevole("Marie","Marie","Marie","Marie", lieu4,true,LocalDate.now(),quackshelter);
		
		personneSrv.insert(visiteur);
		personneSrv.insert(patron);
		personneSrv.insert(employe);
		personneSrv.insert(benevole);
		
		List<Caractere> caracteresChien = new ArrayList<>();
		Collections.addAll(caracteresChien, Caractere.Affecteux,Caractere.Calin,Caractere.Joueur);
		Chien chien = new Chien("Dog1", LocalDate.parse("2024-10-01"), "Blanc", "allégé",
				"Aucun", Famille.Canin, Genre.Male,caracteresChien , quackshelter,
				false, false,"Border Collie");
		
		
		List<Caractere> caracteresChat = new ArrayList<>();
		Collections.addAll(caracteresChat, Caractere.Affecteux,Caractere.Joueur);
		Chat chat = new Chat("chat1", LocalDate.parse("2025-12-01"), "tigré noir", "prise de masse",
				"diabete", Famille.Felin, Genre.Femelle,caracteresChat , quackshelter,
				true, false,"Tigré");
		
		
		List<Caractere> caracteresCoin = new ArrayList<>();
		Collections.addAll(caracteresCoin, Caractere.Timide,Caractere.Craintif);
		Canard coin = new Canard("Coin1", LocalDate.parse("2025-12-01"), "Vert", "seche",
				"aucun", Famille.Galide, Genre.Male,caracteresChat , quackshelter,
				false, true, "CoinCoin", false);
		
		
		List<Caractere> caracteresPoule = new ArrayList<>();
		Collections.addAll(caracteresPoule, Caractere.Agressif,Caractere.Craintif);
		Poule poule = new Poule("Poulette", LocalDate.parse("2025-12-01"), "tigré noir", "prise de masse",
				"diabete", Famille.Felin, Genre.Femelle,caracteresChat , quackshelter,
				false, true, "CoinCoin", true);
		
		animalSrv.insert(chien);
		animalSrv.insert(chat);
		animalSrv.insert(coin);
		animalSrv.insert(poule);
		
		
		Emplacement emplacement1 = new Emplacement(2, false, typeBox.Box);
		Emplacement emplacement2 = new Emplacement(4, false, typeBox.Aquarium);
		Emplacement emplacement3 = new Emplacement(3, false, typeBox.Cage);
		Emplacement emplacement4 = new Emplacement(6, false, typeBox.Poulailler);
		
		emplacementSrv.insert(emplacement1);
		emplacementSrv.insert(emplacement2);
		emplacementSrv.insert(emplacement3);
		emplacementSrv.insert(emplacement4);
		
		
		
		
		
	}
	
}
