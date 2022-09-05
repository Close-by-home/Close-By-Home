package CloseByHome;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    Usuario user = new Usuario() {
    };

    //Login
    @PostMapping("/login/{email}/{senha}/{codCondominio}")
    public String getLogin(
                                        @PathVariable String email,
                                        @PathVariable String senha,
                                        @PathVariable String codCondominio){


        String res = user.logar(
                 email,
                 senha,
                 codCondominio
        );

        return res;
    }

    //Logff
    @PostMapping("/deslogar/{email}")
    public String postDeslogar(
            @PathVariable String email
                                             ){

        String res = user.deslogar(email);

        return res;
    }

}
