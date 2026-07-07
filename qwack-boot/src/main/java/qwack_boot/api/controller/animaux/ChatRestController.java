package qwack_boot.api.controller.animaux;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import qwack_boot.api.requestDTO.animal.CreateChatRequest;
import qwack_boot.api.requestDTO.animal.UpdateChatRequest;
import qwack_boot.api.responseDTO.animal.ChatResponse;
import qwack_boot.dao.IDAOAnimal;
import qwack_boot.model.Chat;
import qwack_boot.service.ChatService;

@RestController
@RequestMapping("api/chat")
public class ChatRestController {

    @Autowired
    private IDAOAnimal daoAnimal;

    @Autowired
    private ChatService srvChat;

    @PostMapping
    public ChatResponse ajouter(@RequestBody CreateChatRequest request) {
        Chat chat = srvChat.insert(request);
        return ChatResponse.convert(chat);
    }

    @PutMapping("/{id}")
    public ChatResponse modifier(@PathVariable Integer id, @RequestBody UpdateChatRequest request) {
        Chat chat = srvChat.update(id, request);

        return ChatResponse.convert(chat);
    }
}
