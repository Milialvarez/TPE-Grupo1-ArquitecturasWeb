package micro.example.gateway.Controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import micro.example.gateway.security.jwt.JwtFilter;
import micro.example.gateway.service.dto.login.LoginDTO;
import micro.example.gateway.security.jwt.TokenProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authenticate" )
@RequiredArgsConstructor
public class    JwtController {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @PostMapping()
    public ResponseEntity<JWTToken> authorize(@Valid @RequestBody LoginDTO request ) {
        System.out.println(request);
        System.out.println(request.getEmail());
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        );
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate( authenticationToken );
        SecurityContextHolder.getContext().setAuthentication( authentication );
        final var jwt = tokenProvider.createToken( authentication );
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add( JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt );
        return new ResponseEntity<>( new JWTToken( jwt ), httpHeaders, HttpStatus.OK );
    }

    static class JWTToken {

        private String idToken;

        JWTToken(String idToken) {
            this.idToken = idToken;
        }

        @JsonProperty("id_token")
        String getIdToken() {
            return idToken;
        }

        void setIdToken(String idToken) {
            this.idToken = idToken;
        }
    }
}
