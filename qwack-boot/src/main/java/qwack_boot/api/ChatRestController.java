package qwack_boot.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import qwack_boot.api.requestDTO.animal.CreateChatRequest;
import qwack_boot.dao.IDAOAnimal;
import qwack_boot.model.Chat;

@RestController
@RequestMapping("api/chat")
public class ChatRestController {

    @Autowired
    private IDAOAnimal daoAnimal;

    @PostMapping
    public CreateChatRequest ajouter(@RequestBody Chat chat) {
        return CreateChatRequest.convert((Chat) daoAnimal.save(chat));
    }

    @PutMapping("/{id}")
    public CreateChatRequest modifier(@PathVariable Integer id, @RequestBody Chat chat) {
        chat.setId(id);
        return CreateChatRequest.convert((Chat) daoAnimal.save(chat));
    }
}
