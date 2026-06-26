package qwack_boot.restcontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/home")
public class HomeFreeRestController {
    @GetMapping()
    public String free() {
        return "FREEEE (3) !!";
    }
}
