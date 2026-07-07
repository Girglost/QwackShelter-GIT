package qwack_boot.service;

import org.springframework.beans.factory.annotation.Autowired;

import qwack_boot.api.requestDTO.animal.CreateChatRequest;
import qwack_boot.api.requestDTO.animal.UpdateChatRequest;
import qwack_boot.dao.IDAOQuackShelter;
import qwack_boot.model.Chat;
import qwack_boot.model.QuackShelter;

public class ChatService {

    @Autowired
    private AnimalService animalService;

    @Autowired
    private IDAOQuackShelter daoQuackShelter;

    public Chat insert(CreateChatRequest request) {

        QuackShelter refuge = daoQuackShelter
                .findById(request.getQwackShelterId())
                .orElseThrow();

        Chat chat = new Chat();

        chat.setNomAnimal(request.getNomAnimal());
        chat.setDateNaissance(request.getDateNaissance());
        chat.setCouleur(request.getCouleur());
        chat.setGenre(request.getGenre());
        chat.setSterilisation(request.isSterilisation());
        chat.setGestante(request.isGestante());
        chat.setCaracteres(request.getCaracteres());
        chat.setRace(request.getRace());

        chat.setQuackShelter(refuge);


        return (Chat) animalService.insert(chat);
    }

    public Chat update(Integer id, UpdateChatRequest request) {

        Chat chat = (Chat) animalService.getById(id);

        if (chat == null) {
            throw new RuntimeException("Chat introuvable");
        }

        chat.setNomAnimal(request.getNomAnimal());
        chat.setCouleur(request.getCouleur());
        chat.setGenre(request.getGenre());
        chat.setSterilisation(request.isSterilisation());
        chat.setGestante(request.isGestante());
        chat.setCaracteres(request.getCaracteres());
        chat.setRace(request.getRace());

        if (request.getQwackShelterId() != null) {
            QuackShelter refuge = daoQuackShelter
                    .findById(request.getQwackShelterId())
                    .orElseThrow(() -> new RuntimeException("Refuge introuvable"));

            chat.setQuackShelter(refuge);
        }

        return (Chat) animalService.update(chat);
    }

}
