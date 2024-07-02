package mauro.montan.forohub.security;


import mauro.montan.forohub.users.LoginDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthenticationController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtToken> login(@RequestBody LoginDto user) {

        Authentication token = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        Authentication authToken = authenticationManager.authenticate(token);

        System.out.println(authToken.getPrincipal());
        var jwtToken = tokenService.generateToken(user.fromPrincipal(authToken.getPrincipal()));

        return ResponseEntity.ok().body(new JwtToken(jwtToken));
    }


}
