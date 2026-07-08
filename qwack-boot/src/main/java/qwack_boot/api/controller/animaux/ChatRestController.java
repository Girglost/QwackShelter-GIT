package qwack_boot.api.controller.animaux;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import qwack_boot.api.requestDTO.animal.CreateAnimalRequest;
import qwack_boot.api.requestDTO.animal.UpdateAnimalRequest;
import qwack_boot.api.responseDTO.animal.ChatResponse;
import qwack_boot.dao.IDAOAnimal;
import qwack_boot.model.Chat;
import qwack_boot.model.Famille;
import qwack_boot.service.ChatService;
import qwack_boot.service.QuackShelterService;

@RestController
@RequestMapping("api/chat")
public class ChatRestController {

    private final ChatService srvChat;
    private final QuackShelterService qSrv;

    ChatRestController(ChatService srvChat, IDAOAnimal daoAnimal, QuackShelterService qSrv) {
        this.srvChat = srvChat;
        this.qSrv = qSrv;
    }

    @PostMapping
    public ChatResponse ajouter(@RequestBody CreateAnimalRequest request) {
        Chat chat = new Chat();

        chat.setNomAnimal(request.getNomAnimal());
        chat.setDateNaissance(request.getDateNaissance());
        chat.setCouleur(request.getCouleur());
        chat.setRegimeAlimentaire(request.getRegimeAlimentaire());
        chat.setTraitement(request.getTraitement());
        chat.setGenre(request.getGenre());
        chat.setFamille(Famille.Felin);
        chat.setSterilisation(request.isSterilisation());
        chat.setGestante(request.isGestante());
        chat.setRace(request.getRace());
        chat.setCaracteres(request.getCaracteres());
        chat.setQuackShelter(qSrv.getById(request.getQwackShelterId()));

        srvChat.insert(chat);
        return ChatResponse.convert(chat);
    }

    @PutMapping("/{id}")
    public ChatResponse modifier(@PathVariable Integer id, @RequestBody UpdateAnimalRequest request) {
        Chat chat = new Chat();

        chat.setNomAnimal(request.getNomAnimal());
        chat.setDateNaissance(request.getDateNaissance());
        chat.setCouleur(request.getCouleur());
        chat.setRegimeAlimentaire(request.getRegimeAlimentaire());
        chat.setTraitement(request.getTraitement());
        chat.setGenre(request.getGenre());
        chat.setFamille(Famille.Felin);
        chat.setSterilisation(request.isSterilisation());
        chat.setGestante(request.isGestante());
        chat.setRace(request.getRace());
        chat.setCaracteres(request.getCaracteres());
        chat.setQuackShelter(qSrv.getById(request.getQwackShelterId()));
        
        srvChat.update(id, chat);

        return ChatResponse.convert(chat);
    }
}
