package quack.test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

import quack.model.Animal;
import quack.model.Benevole;
import quack.model.Canard;
import quack.model.Caractere;
import quack.model.Cause;
import quack.model.Chat;
import quack.model.Chien;
import quack.model.Emplacement;
import quack.model.Employe;
import quack.model.Famille;
import quack.model.Genre;
import quack.model.HistoriqueSante;
import quack.model.Lieu;
import quack.model.Patron;
import quack.model.Personne;
import quack.model.Personnel;
import quack.model.Poule;
import quack.model.QuackShelter;
import quack.model.Statut;
import quack.model.StatutAnimal;
import quack.model.Visite;
import quack.model.Visiteur;
import quack.model.typeBox;
import quack.service.AnimalService;
import quack.service.EmplacementService;
import quack.service.HistoriqueSanteService;
import quack.service.LieuService;
import quack.service.PersonneService;
import quack.service.QuackShelterService;
import quack.service.StatutAnimalService;
import quack.service.VisiteService;

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
	@Autowired
	VisiteService visiteSrv;
	@Autowired
	StatutAnimalService statutAnimalSrv;
	@Autowired
	HistoriqueSanteService historiqueSanteSrv;
	
	static Personne connected = null;
	
	public static int saisieInt(String message)
	{
		Scanner monScanner = new Scanner(System.in);
		System.out.println(message);
		return monScanner.nextInt();
	}

	public static double saisieDouble(String message)
	{
		Scanner monScanner = new Scanner(System.in);
		System.out.println(message);
		return monScanner.nextDouble();
	}

	public static String saisieString(String message)
	{
		Scanner monScanner = new Scanner(System.in);
		System.out.println(message);
		return monScanner.nextLine();
	}

	public static boolean saisieBoolean(String message)
	{
		Scanner monScanner = new Scanner(System.in);
		System.out.println(message);
		return monScanner.nextBoolean();
	}
	
	public void menuPrincipal()
	{
		System.out.println("----BIENVENUE AU QUACK SHELTER-------");
		System.out.println("1 - Se connecter");
		System.out.println("2 - Créer un compte visiteur");
		System.out.println("3 - Devenir Bénévole");
		System.out.println("4 - Quitter");

		int choix = saisieInt("Choisir un menu");
		switch(choix)
		{
		case 1 : seConnecter();break;
		case 2 : devenirVisiteur();break;
		case 3 : engagerBenevole();break;
		case 4 : System.exit(0);break;
		}

		menuPrincipal();
	}
	public void devenirVisiteur() {

		System.out.println("Creation d'un Compte Visiteur");
		String nom = saisieString("Entrer votre nom");
		String prenom = saisieString("Entrer votre prenom");
		
		String login = saisieString("Entrer votre login");
		
		while(personneSrv.loginExist(login)) {
			System.out.println("Login déjà utilisé");
			login = saisieString("Entrer un autre login");
		}
		
		String password = saisieString("Entrer votre mot de passe");

		int choixLieu = saisieInt("Vous avez dans : 1 - Une maison, 2 - Un appartement ?");
		String typeLieu = null;
		switch (choixLieu) {
		case 1:
			typeLieu = "Maison";
			break;
		case 2: 
			typeLieu = "Appartement";
			break;

		default:
			typeLieu = "Inconnu";
			break;
		}

		System.out.println("Entrer votre Adresse :");
		String numero = saisieString("Numero : ");
		String voie = saisieString("Voie : ");
		String ville = saisieString("Ville : ");
		String cp = saisieString("CP : ");



		Lieu habitation = new Lieu(typeLieu, numero, voie, ville, cp);
		LocalDate dateInscription = LocalDate.now();

		Visiteur visiteur = new Visiteur(nom,prenom,login,password,habitation,dateInscription);

		personneSrv.insert(visiteur);

		System.out.println("Bonjour "+visiteur.getLogin()+", vous êtes maintenant Visiteur id ="+visiteur.getId());
	}
	
	public void engagerBenevole() {
		
		System.out.println("Creation d'un Compte Bénévole");
		String nom = saisieString("Entrer le nom");
		String prenom = saisieString("Entrer le prenom");
		String login = saisieString("Entrer le login");
		
		while(personneSrv.loginExist(login)) {
			System.out.println("Login déjà utilisé");
			login = saisieString("Entrer un autre login");
		}
		
		String password = saisieString("Entrer le mot de passe");
		
		int choixLieu = saisieInt("Type D'habitation : 1 - Maison, 2 - Appartement ?");
		String typeLieu = null;
		switch (choixLieu) {
		case 1:
			typeLieu = "Maison";
			break;
		case 2: 
			typeLieu = "Appartement";
			break;

		default:
			typeLieu = "Inconnu";
			break;
		}
		
		System.out.println("Entrer l'adresse :");
		String numero = saisieString("Numero : ");
		String voie = saisieString("Voie : ");
		String ville = saisieString("Ville : ");
		String cp = saisieString("CP : ");
		Lieu habitation = new Lieu(typeLieu, numero, voie, ville, cp);
		LocalDate dateEngagement = LocalDate.now();
		
		QuackShelter quackshelter = quackSrv.getAll().get(0);
		
		Benevole benevole = new Benevole(nom, prenom,login,password, habitation,
				false,dateEngagement,quackshelter);
		//System.out.println("benevole ID avant insert "+ benevole);
		personneSrv.insert(benevole);
		//System.out.println("benevole ID apres insert "+ benevole);
		System.out.println(benevole.getLogin()+" est maintenant bénévole !");
	}
	public void seConnecter() {
		String login = saisieString("Entrer votre Login");
		String password = saisieString("Entrer votre Mot de Passe");
		
		connected = personneSrv.getByLoginAndPassword(login, password);
		while(connected == null) {
			System.out.println("Login ou mot de passe incorrect");
			login = saisieString("Entrer votre Login");
			password = saisieString("Entrer votre Mot de Passe");
			connected = personneSrv.getByLoginAndPassword(login, password);
		}
		
		System.out.println("Bonjour "+connected.getLogin()+" !");
		
		if(connected instanceof Patron) {
			menuPatron();
		}
		
		if(connected instanceof Visiteur) {
			System.out.println("Menu Visiteur !");
			menuVisiteur();
		}
		if (connected instanceof Employe)
		{
			System.out.println("Menu Employe !");
			if(((Employe) connected).isAdmin()) {
				System.out.println("MENU EMPLOYE ADMIN");
				//menuAdmin();
			}else {
				//menuEmploye();
			}
		}
		if (connected instanceof Benevole)
		{
			System.out.println("Menu Benevole !");
			if(((Benevole) connected).isAdmin()) {
				System.out.println("MENU BENEVOLE ADMIN");
				//menuAdmin();
			}else {
				menuBenevole();
			}
		}
	}
	private void deconnexion() {
		connected = null;
		menuPrincipal();
		return;
	}

	
	//////////// FONCTIONS DU BENEVOLE ////////////////////

	private void menuBenevole() {
		System.out.println("Espace Benevole");
		System.out.println("1 - Voir les animaux");
		System.out.println("2 - Balader un animal");
		System.out.println("3 - Faire un don");
		System.out.println("4 - Voir les visites du jour");
		System.out.println("5 - Voir les adoptions");
		System.out.println("6 - Déconnexion");
		int choix = saisieInt("");
		
		switch (choix) {
		case 1:
			List<Animal> animaux = animalSrv.getAll();
			for(Animal a : animaux) {
				System.out.println(a.getNomAnimal()+" - "+ a.getFamille());
			}
			break;
		case 2:
			BaladerUnAnimal();
			break;
		case 3:
			faireDon();
			break;
		case 4:
			LocalDateTime today = LocalDateTime.now();
			
			DateTimeFormatter dateFormatter =
			DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.FRANCE);

			DateTimeFormatter timeFormatter =
					DateTimeFormatter.ofPattern("HH:mm");

			String date = today.format(dateFormatter);
			

			System.out.println("Visites du "+date);
			System.out.println(date);
			List<Visite> visitesDuJour = visiteSrv.getAllByDate(today);
			for(Visite v : visitesDuJour) {
				String time = v.getDateVisite().format(timeFormatter);
				System.out.println("A "+time+" : "+v);
			}
			break;
		case 5:
			List<StatutAnimal> adoptions = statutAnimalSrv.getAll();
			for(StatutAnimal s : adoptions) {
				System.out.println(s.getDateDepart()+" : "+s.getAdoptant()+" - "+s.getAnimal().getNomAnimal()+", "+s.getAnimal().getFamille());
			}
			break;
		case 6:
			deconnexion();
			break;

		default:
			break;
		}
		menuVisiteur();
	}

	private void BaladerUnAnimal() {
		System.out.println("Animaux présents au Shelter");
		List<Animal> animaux = animalSrv.getByStatut(Statut.Present);
		for(Animal a : animaux) {
			System.out.println(a.getId() + " - "+a.getNomAnimal()+", "+a.getFamille());
		}
		int choixBalade = saisieInt("Choisir un animal a balader");
		
		Animal animalEnBalade = animalSrv.getById(choixBalade);
		
		StatutAnimal statutAnimal  = animalEnBalade.getStatutAnimal();
		statutAnimal.setStatut(Statut.EnBalade);
		statutAnimalSrv.update(statutAnimal);
		
		System.out.println(connected.getLogin()+" et "+animalEnBalade.getNomAnimal()+"("+animalEnBalade.getFamille()+") sont en balade !");
	}

//////////////FONCTIONS DU VISITEUR /////////////////////////
	private void menuVisiteur() {
		System.out.println("Espace Visiteur");
		
		System.out.println("1 - Voir les animaux disponibles");
		System.out.println("2 - Demander une visite");
		System.out.println("3 - Demander une adoption");
		System.out.println("4 - Faire un don");
		System.out.println("5 - Consulter mes visites");
		System.out.println("6 - Consulter mes adoptions");
		System.out.println("7 - Modifier mon profil");
		System.out.println("8 - Déconnexion");
		int choix = saisieInt("");
		
		switch (choix) {
		case 1:
			List<Animal> animauxDispo = animalSrv.getDispoWithCaracteres();
			for(Animal a: animauxDispo) {
				System.out.println(a);
			}
			break;
		case 2:
			demanderVisite();
			break;
		case 3:
			demanderAdoption();
			break;
		case 4:
			faireDon();
			break;
		case 5:
			System.out.println(connected.getId());
			List<Visite> visites = visiteSrv.getByIdPersonne(connected.getId());
			System.out.println("Tes Visites");
			for(Visite v : visites) {
				System.out.println(v);
			}
			break;
		case 6:
			List<StatutAnimal> adoptions = statutAnimalSrv.getByAdoptant(connected.getId());
			System.out.println("Tes adoptions");
			for(StatutAnimal s : adoptions) {
				System.out.println("- Le "+s.getDateDepart() +" - "+s.getAnimal().getNomAnimal()+" : "+s.getAnimal().getFamille());
			}
			break;
		case 7:
			modifierVisiteur(connected);
			break;
		case 8:
			deconnexion();
			break;

		default:
			break;
		}
		menuVisiteur();
	}
	

	private void modifierVisiteur(Personne visiteur) {
		System.out.println("Modification de votre compte");
		String nom = saisieString("Entrer votre nom");
		String prenom = saisieString("Entrer votre prenom");
		
		String login = connected.getLogin();
		
		String ancienPassword = saisieString("Entrer votre ancien mot de passe");
		
		
		while(!ancienPassword.equals(visiteur.getPassword())) {
			System.out.println("Mot de passe incorrect");
			System.out.println("ANCIEN PASSWORD = "+ancienPassword+" -- "+visiteur.getPassword());
			ancienPassword = saisieString("Entrer a nouveau l'ancien mot de passe");
		}
		
		String password = saisieString("Entrer votre  nouveau mot de passe");

		int choixLieu = saisieInt("Vous avez dans : 1 - Une maison, 2 - Un appartement ?");
		String typeLieu = null;
		switch (choixLieu) {
		case 1:
			typeLieu = "Maison";
			break;
		case 2: 
			typeLieu = "Appartement";
			break;

		default:
			typeLieu = "Inconnu";
			break;
		}

		System.out.println("Entrer votre Adresse :");
		String numero = saisieString("Numero : ");
		String voie = saisieString("Voie : ");
		String ville = saisieString("Ville : ");
		String cp = saisieString("CP : ");



		Lieu habitation = new Lieu(typeLieu, numero, voie, ville, cp);
		LocalDate dateInscription = LocalDate.now();

		visiteur.setHabitation(habitation);
		visiteur.setNom(nom);
		visiteur.setPrenom(prenom);
		visiteur.setHabitation(habitation);
		visiteur.setPassword(password);
		personneSrv.update(visiteur);

		System.out.println("Modification du compte Visiteur de "+visiteur.getLogin());	
	}


	private void demanderAdoption() {
		List<QuackShelter> quackShelters = quackSrv.getAll();
		 for(QuackShelter q : quackShelters) {
			 System.out.println(q.getId() +" - "+q.getLieu());
		 }
		int choixShelter = saisieInt("Choisir un QuackShelter");
		
		List<Animal> animauxDispo = animalSrv.getDispoWithCaracteres();
		
		System.out.println("Liste des animaux disponibles à l'adoption");
		
		for(Animal a : animauxDispo) {
			System.out.println(a.getId()+" - "+a.getNomAnimal()+" - "+a.getFamille()+" - "+a.getStatutAnimal());
			System.out.println(a.getCaractere());
			a = animalSrv.getByIdWithHistoriqueSante(a.getId());
			System.out.println(a.getHistoriqueSante());
		}
		int choixAnimal = saisieInt("Choisir un animal a adopter");
		personneSrv.demanderAdoption(choixShelter,connected,choixAnimal);
	}

	private void faireDon() {
		List<QuackShelter> quackShelters = quackSrv.getAll();
		 for(QuackShelter q : quackShelters) {
			 System.out.println(q.getId() +" - "+q.getLieu());
		 }
		int choixShelter = saisieInt("Choisir un QuackShelter");
		double don = saisieDouble("Faire un don de : ");
		personneSrv.faireDon(choixShelter, don);
	}

	private void demanderVisite() {
		List<QuackShelter> quackShelters = quackSrv.getAll();
		 for(QuackShelter q : quackShelters) {
			 System.out.println(q.getId() +" - "+q.getLieu());
		 }
		int choixShelter = saisieInt("Choisir un QuackShelter");
		String date = saisieString("Choisir une date (dd/MM/yyyy HH:mm)");
		personneSrv.demanderVisite(connected,choixShelter, date);
	}
	//////////////FONCTIONS DU PATRON /////////////////////////
	private void menuPatron() {
		System.out.println("Espace Patron");
		
		System.out.println("1 - Gerer les QuackShelters");
		System.out.println("2 - Se déconnecter");

		int choix = saisieInt("Choisir un menu");
		switch(choix)
		{
			case 1 : gestionQuackShelters();break;
			case 2 :
				deconnexion();
		}
		menuPatron();
	}

	private void gestionQuackShelters() {
		System.out.println("Liste des Quack Shelter");
		List<QuackShelter> quackshelters = quackSrv.getAll();
		System.out.println("Choisir un QuackShelter");
		for(QuackShelter quackShelter : quackshelters) {
			System.out.println("ID - "+quackShelter.getId());
		}
		
		int idShelter = saisieInt("");
		QuackShelter quackShelter = quackSrv.getById(idShelter);
		
		System.out.println(" 1 - Gestion du Personnel");
		System.out.println(" 2 - Gestion des animaux");
		System.out.println(" 3 - Gestion des visites");
		System.out.println(" 4 - Retour");
		int choix = saisieInt("Que voulez-vous y faire ?");
		
		
		switch (choix) {
		case 1:
			gestionPersonnel();
			break;
		case 2:
			gestionAnimaux();
			break;
		case 3:
			gestionVisites();
			break;
		case 4: menuPatron();break;
		default:
			break;
		}
		gestionQuackShelters();
	}

	private void gestionVisites() {
		System.out.println("Gestion des Visites");
		System.out.println("1 - Liste des visites");
		System.out.println("2 - Ajouter une visite");
		System.out.println("3 - Supprimer une visite");
		System.out.println("4 - retour");
		
		int choix = saisieInt("Que voulez-vous faire ?");
		switch (choix) {
		case 1:
			List<Visite> visites = visiteSrv.getAll();
			if(visites.isEmpty()) {
				System.out.println("Aucune Visite reçue");
			}else {
				for(Visite v : visites) {
					System.out.println(v.getQuackshelter()+" - "+v.getId()+" - "+v.getDateVisite()+" - "+v.getVisiteur());
				}
			}
			break;
		case 2:
			List<QuackShelter> quackShelters = quackSrv.getAll();
			 for(QuackShelter q : quackShelters) {
				 System.out.println(q.getId() +" - "+q.getLieu());
			 }
			int choixShelter = saisieInt("Choisir un QuackShelter");
			String date = saisieString("Choisir une date (dd/MM/yyyy HH:mm)");
			
			System.out.println("Liste des visiteurs");
			List<Visiteur> visiteurs = personneSrv.getAllVisiteur();
			for(Visiteur v:visiteurs) {
				System.out.println(v.getId()+" - "+v.getLogin());
			}
			int idVisiteur = saisieInt("Qui Visite ?");
			Personne visiteur = personneSrv.getById(idVisiteur);
			
			personneSrv.demanderVisite(visiteur,choixShelter, date);
			break;
		case 3: 
			visites = visiteSrv.getAll();
			if(visites.isEmpty()) {
				System.out.println("Aucune Visite reçue");
			}else {
				for(Visite v : visites) {
					System.out.println(v.getQuackshelter()+" - "+v.getId()+" - "+v.getDateVisite()+" - "+v.getVisiteur());
				}
			}
			int idVisite = saisieInt("Choisir la visite a supprimer");
			Visite visite = visiteSrv.getById(idVisite);
			visiteSrv.delete(idVisite);
			
			System.out.println("Visite "+visite.getId()+" - "+visite.getDateVisite()+" supprimé ! ");
		case 4:
			gestionQuackShelters();
			break;
		default:
			break;
		}
		
		
	}

	private void gestionAnimaux() {
		System.out.println("1 - Liste des animaux");
		System.out.println("2 - Etat de santé des animaux");
		System.out.println("3 - Faire une adoption");
		System.out.println("4 - Recueillir un animal");
		System.out.println("5 - Supprimer un animal");
		System.out.println("6 - Retour");
		 
		int choix = saisieInt("Que voulez-vous faire ?");
		switch (choix) {
		case 1:
			List<Animal> animaux = animalSrv.getAll();
			for(Animal a:animaux) {
				System.out.println(a.getId()+" - "+a.getNomAnimal()+" - "+a.getFamille()+" arrivé le :"+a.getStatutAnimal().getDateArrivee());
			}
			break;
		case 2:
			animaux = animalSrv.getAll();
			for(Animal a:animaux) {
				System.out.println(a.getId()+" - "+a.getNomAnimal()+" - "+a.getFamille());
			}
			int idAnimal = saisieInt("Choisir un animal");
			 Animal animal = animalSrv.getByIdWithHistoriqueSante(idAnimal);
			 List<HistoriqueSante> historiqueSante = animal.getHistoriqueSante();
			 for(HistoriqueSante hs:historiqueSante) {
				 System.out.println(hs);
			 }
			break;
		case 3:
			List<Personne> personnes = personneSrv.getAll();
			for(Personne p:personnes) {
				System.out.println(p.getId()+" - "+p.getLogin());
			}
			int idAdoptant = saisieInt("Qui adopte ?");
			
			Personne adoptant = personneSrv.getById(idAdoptant);
			
			List<Animal> animauxDispo = animalSrv.getDispoWithCaracteres();
			
			System.out.println("Liste des animaux disponibles à l'adoption");
			
			for(Animal a : animauxDispo) {
				System.out.println(a.getId()+" - "+a.getNomAnimal()+" - "+a.getFamille()+" - "+a.getStatutAnimal());
				System.out.println(a.getCaractere());
			}
			int choixAnimal = saisieInt("Choisir un animal a adopter");
			personneSrv.demanderAdoption(1,adoptant,choixAnimal);
			
			Animal adopted = animalSrv.getById(choixAnimal);
			
			System.out.println(adoptant.getLogin()+" a adopté "+adopted.getId()+" - "+adopted.getNomAnimal());
			break;
		case 4:
			
			System.out.println("Recueil d'un animal en détresse");
			System.out.println("Type d'animal : ");
			System.out.println("1 - "+Chien.class);
			System.out.println("2 - "+Chat.class);
			System.out.println("3 - "+Canard.class);
			System.out.println("4 - "+Poule.class);
			int choixTypeAnimal = saisieInt("Choisir le type d'animal");
			
			String nom = saisieString("Nom de l'animal");
			LocalDate dateNaissance = LocalDate.parse(saisieString("date de Naissance (AAAA-MM-JJ)"));
			String couleur = saisieString("Couleurs de l'animal");
			String regime = saisieString("Régime alimentaire");
			String traitement = saisieString("Traitement");
			Famille famille = Famille.valueOf(saisieString("Famille : "+Famille.values()));
			Genre genre = Genre.valueOf(saisieString("Genre : "+Genre.values()));
			
			QuackShelter qwackShelter=quackSrv.getById(1);
			boolean sterilisation = saisieBoolean("Stérilisé ? (true/false)");
			boolean gestante = saisieBoolean("En gestation ? (true/false)");
			String race = saisieString("Race");
			
			System.out.println("Selectionner l'emplacement");
			List<Emplacement> emplacements = emplacementSrv.getAll();
			for(Emplacement e:emplacements) {
				System.out.println(e.getId()+" - "+e.getBox());
			}
			int idEmplacement = saisieInt("");
			Emplacement emplacement = emplacementSrv.getById(idEmplacement);
			
			switch (choixTypeAnimal) {
			case 1:
				Chien chien = new Chien(nom, couleur, genre, qwackShelter, sterilisation, gestante, race);
				
				StatutAnimal StatutAnimal = new StatutAnimal(LocalDate.now(), null, Statut.Present, 
						emplacement, null, chien);
				chien.setStatutAnimal(StatutAnimal);
				animalSrv.insert(chien);
				break;
			case 2:
				Chat chat = new Chat(nom, couleur, genre, qwackShelter, sterilisation, gestante, race);
				
				StatutAnimal = new StatutAnimal(LocalDate.now(), null, Statut.Present, 
						emplacement, null, chat);
				chat.setStatutAnimal(StatutAnimal);
				animalSrv.insert(chat);
				break;
			case 3:
				boolean pondeuse = saisieBoolean("Pondeuse ? (true/false)");
				boolean estSauvage = saisieBoolean("Sauvage ? (true/false)");
				Canard canard = new Canard(nom, couleur,genre,qwackShelter, pondeuse, race, estSauvage);
				
				StatutAnimal = new StatutAnimal(LocalDate.now(), null, Statut.Present, 
						emplacement, null, canard);
				canard.setStatutAnimal(StatutAnimal);
				animalSrv.insert(canard);
				break;
			case 4:
				pondeuse = saisieBoolean("Pondeuse ? (true/false)");
				estSauvage = saisieBoolean("Sauvage ? (true/false)");
				Poule poule = new Poule(nom, couleur, genre, qwackShelter, pondeuse, race);
				
				StatutAnimal = new StatutAnimal(LocalDate.now(), null, Statut.Present, 
						emplacement, null, poule);
				poule.setStatutAnimal(StatutAnimal);
				animalSrv.insert(poule);
				break;

			default:
				break;
			}
		case 5:
			animaux = animalSrv.getAll();
			for(Animal a:animaux) {
				System.out.println(a.getId()+" - "+a.getNomAnimal()+" - "+a.getFamille());
			}
			idAnimal = saisieInt("Choisir un animal a supprimer");
			Animal animalSuppr = animalSrv.getById(idAnimal);
			animalSrv.delete(idAnimal);
			System.out.println(" L'animal "+animalSuppr.getId()+" - "+animalSuppr.getNomAnimal()+"  a été supprimé");
			break;
			
		case 6:
			gestionAnimaux();
			break;
		default:
			break;
		}
		
	}

	private void gestionPersonnel() {
			System.out.println("1 - Liste du personnel");
			System.out.println("2 - Embaucher du personnel");
			System.out.println("3 - Virer une personne");
			System.out.println("4 - Retour");
			int choix = saisieInt("");
			switch (choix) {
			case 1:
				List<Personnel> personnels = personneSrv.getAllPersonnel();
				for(Personnel p : personnels) {
					System.out.println(p.getId()+" - "+p.getNom()+" - "+p.getPrenom()+" - "+p.getClass().getSimpleName());
				}
				break;
			case 2:
				System.out.println("Type de poste : 1 - Benevole, 2 - Employe");
				int choixPoste = saisieInt("");
				switch (choixPoste) {
				case 1:
					engagerBenevole();
					break;
				case 2:
					engagerEmploye();
					break;
				default:
					break;
				}
				break;
			case 3:
				virerPersonne();
				break;
			case 4:
				gestionQuackShelters();
				break;
			default:
				break;
			}
	}

	private void virerPersonne() {
		List<Personne> personnes = personneSrv.getAll();
		for(Personne p:personnes) {
			System.out.println(p.getId()+" - "+p.getClass().getSimpleName()+" - "+p.getLogin());
		}
		int idPersonne  = saisieInt("Choisir une personne");
		Personne personneViree = personneSrv.getById(idPersonne);
		personneSrv.deleteById(idPersonne);
		
		System.out.println(personneViree.getId()+" - "+personneViree.getLogin()+" est viré !");
	}


	private void engagerEmploye() {
		System.out.println("Creation d'un Compte Employe");
		String nom = saisieString("Entrer le nom");
		String prenom = saisieString("Entrer le prenom");
		String login = saisieString("Entrer le login");
		while(personneSrv.loginExist(login)) {
			System.out.println("Login déjà utilisé");
			login = saisieString("Entrer un autre login");
		}
		String password = saisieString("Entrer le mot de passe");
		
		int choixLieu = saisieInt("Type D'habitation : 1 - Maison, 2 - Appartement ?");
		String typeLieu = null;
		switch (choixLieu) {
		case 1:
			typeLieu = "Maison";
			break;
		case 2: 
			typeLieu = "Appartement";
			break;

		default:
			typeLieu = "Inconnu";
			break;
		}
		
		System.out.println("Entrer l'adresse :");
		String numero = saisieString("Numero : ");
		String voie = saisieString("Voie : ");
		String ville = saisieString("Ville : ");
		String cp = saisieString("CP : ");
		Lieu habitation = new Lieu(typeLieu, numero, voie, ville, cp);
		LocalDate dateEmbauche = LocalDate.now();
		double salaire = 2000.5;
		
		QuackShelter quackshelter = quackSrv.getAll().get(0);
		Employe employe = new Employe(nom, prenom,login,password, habitation,
				false,salaire,dateEmbauche,quackshelter);
		//System.out.println("benevole ID avant insert "+ benevole);
		personneSrv.insert(employe);
		//System.out.println("benevole ID apres insert "+ benevole);
		System.out.println(employe.getLogin()+" est maintenant Employe !");
	}

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
		
		
		Emplacement emplacement1 = new Emplacement(2, false, typeBox.Box);
		Emplacement emplacement2 = new Emplacement(4, false, typeBox.Aquarium);
		Emplacement emplacement3 = new Emplacement(3, false, typeBox.Cage);
		Emplacement emplacement4 = new Emplacement(6, false, typeBox.Poulailler);
		
		emplacementSrv.insert(emplacement1);
		emplacementSrv.insert(emplacement2);
		emplacementSrv.insert(emplacement3);
		emplacementSrv.insert(emplacement4);
		
		
		List<Caractere> caracteresChien = new ArrayList<>();
		Collections.addAll(caracteresChien, Caractere.Affecteux,Caractere.Calin,Caractere.Joueur);
		Chien chien = new Chien("Dog1", LocalDate.parse("2024-10-01"), "Blanc", "allégé",
				"Aucun", Famille.Canin, Genre.Male,caracteresChien , quackshelter,
				false, false,"Border Collie");
		
		StatutAnimal statutChien = new StatutAnimal(LocalDate.now(), null, Statut.Present, 
				emplacement3, null, chien);
		chien.setStatutAnimal(statutChien);
		HistoriqueSante historiqueChien = new HistoriqueSante("Vomissement et fièvre", 35.0, Cause.Maladie, chien);
		List<HistoriqueSante> historiquesChien = new ArrayList();
		historiquesChien.add(historiqueChien);
		chien.setHistoriqueSante(historiquesChien);
		
		
		List<Caractere> caracteresChat = new ArrayList<>();
		Collections.addAll(caracteresChat, Caractere.Affecteux,Caractere.Joueur);
		Chat chat = new Chat("chat1", LocalDate.parse("2025-12-01"), "tigré noir", "prise de masse",
				"diabete", Famille.Felin, Genre.Femelle,caracteresChat , quackshelter,
				true, false,"Tigré");
		StatutAnimal statutChat = new StatutAnimal(LocalDate.now(), null, Statut.EnSoin, 
				emplacement3, null, chat);
		chat.setStatutAnimal(statutChat);
		HistoriqueSante historiqueChat= new HistoriqueSante("Castration", 35.0, Cause.Sterilisation, chat);
		List<HistoriqueSante> historiquesChat = new ArrayList();
		historiquesChat.add(historiqueChat);
		chat.setHistoriqueSante(historiquesChat);
		
		List<Caractere> caracteresCoin = new ArrayList<>();
		Collections.addAll(caracteresCoin, Caractere.Timide,Caractere.Craintif);
		Canard coin = new Canard("Coin1", LocalDate.parse("2025-12-01"), "Vert", "seche",
				"aucun", Famille.Galide, Genre.Male,caracteresChat , quackshelter,
				false, true, "CoinCoin", false);
		StatutAnimal statutCoin = new StatutAnimal(LocalDate.now(), null, Statut.EnBalade, 
				emplacement4, null, coin);
		coin.setStatutAnimal(statutCoin);
		HistoriqueSante historiqueCoin= new HistoriqueSante("entorse", 35.0, Cause.Blessure, coin);
		List<HistoriqueSante> historiquesCoin = new ArrayList();
		historiquesCoin.add(historiqueCoin);
		coin.setHistoriqueSante(historiquesCoin);
		
		
		
		List<Caractere> caracteresPoule = new ArrayList<>();
		Collections.addAll(caracteresPoule, Caractere.Agressif,Caractere.Craintif);
		Poule poule = new Poule("Poulette", LocalDate.parse("2025-12-01"), "tigré noir", "prise de masse",
				"diabete", Famille.Felin, Genre.Femelle,caracteresChat , quackshelter,
				false, true, "CoinCoin", true);
		StatutAnimal statutPoule = new StatutAnimal(LocalDate.now(), null, Statut.Present, 
				emplacement4, null, poule);
		poule.setStatutAnimal(statutPoule);
		HistoriqueSante historiquePoule= new HistoriqueSante("Vaccin lepre", 35.0, Cause.Vaccin, poule);
		List<HistoriqueSante> historiquesPoule = new ArrayList();
		historiquesPoule.add(historiquePoule);
		poule.setHistoriqueSante(historiquesPoule);
		
		animalSrv.insert(chien);
		animalSrv.insert(chat);
		animalSrv.insert(coin);
		animalSrv.insert(poule);
		
		menuPrincipal();
		
	}
	
}
