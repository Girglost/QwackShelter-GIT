package qwack_boot.service;

import org.springframework.stereotype.Service;

import qwack_boot.model.Chat;

@Service
public class ChatService {

    private final AnimalService animalService;


    ChatService(AnimalService animalService) {
        this.animalService = animalService;
    }


    public Chat insert(Chat chat) {

        // QuackShelter refuge = daoQuackShelter
        //         .findById(request.getQwackShelterId())
        //         .orElseThrow();

        // Chat chat = new Chat();

        // chat.setNomAnimal(request.getNomAnimal());
        // chat.setDateNaissance(request.getDateNaissance());
        // chat.setCouleur(request.getCouleur());
        // chat.setGenre(request.getGenre());
        // chat.setSterilisation(request.isSterilisation());
        // chat.setGestante(request.isGestante());
        // chat.setCaracteres(request.getCaracteres());
        // chat.setRace(request.getRace());

        // chat.setQuackShelter(refuge);


        return (Chat) animalService.insert(chat);
    }

    public Chat update(Integer id, Chat chat) {

        // Chat chat = (Chat) animalService.getById(id);

        // if (chat == null) {
        //     throw new RuntimeException("Chat introuvable");
        // }

        // chat.setNomAnimal(request.getNomAnimal());
        // chat.setCouleur(request.getCouleur());
        // chat.setGenre(request.getGenre());
        // chat.setSterilisation(request.isSterilisation());
        // chat.setGestante(request.isGestante());
        // chat.setCaracteres(request.getCaracteres());
        // chat.setRace(request.getRace());

        // if (request.getQwackShelterId() != null) {
        //     QuackShelter refuge = daoQuackShelter
        //             .findById(request.getQwackShelterId())
        //             .orElseThrow(() -> new RuntimeException("Refuge introuvable"));

        //     chat.setQuackShelter(refuge);
        // }
        chat.setId(id);
        return (Chat) animalService.update(chat);
    }

}
