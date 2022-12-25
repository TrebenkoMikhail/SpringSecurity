package controlers;

import entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import servecies.UserService;

@RestController
@RequiredArgsConstructor
public class DemoController {
    private final UserService userService;
    @GetMapping(path = "/")
    public String homePage() { return "home"; }

    @GetMapping(path = "/unsecured")
    public String unsecuredPage() { return "unsecured"; }

    @GetMapping(path = "auth_page")
    public  String authenticatedPage() { return "authenticated"; }

    @GetMapping(path = "admin")
    // @PreAuthorize ("hashRole('ADMIN')")
    public String adminPage() {return "admin"; }

    @GetMapping(path = "/user_info")
    public String daoTestPage(Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> ResourceNotFoundException("Username not found"));
        return "Authenticated user info: " + user.getUsername() + " : " + user.getEmail();
    }
}
