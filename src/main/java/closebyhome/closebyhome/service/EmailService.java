package closebyhome.closebyhome.service;

import closebyhome.closebyhome.dto.UsuarioDto;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public void EnviarEmail(String emailUsuario,String senhaUsuario, String codigCondominio,String nome) {
        String meuEmail = "closebyhomegp5@gmail.com";
        String senha = "psurrtdypqzmjght";

        SimpleEmail email = new SimpleEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator(meuEmail, senha));
        email.setSSLOnConnect(true);

        try {

            email.setFrom(meuEmail);
            email.setSubject("CloseByHome");
            email.setMsg("" +
                    "Olá" + nome + "" +
                    "somos a CloseByHome sejá bem-vindo ! \n" +
                    "Esses são os dados para o primeiro acessoda conta: \n" +
                    "Codigo do Condominio :" + codigCondominio+ "\n" +
                    "Email:" + emailUsuario+ "\n" +
                    "Senha:" + senhaUsuario);
            email.addTo(emailUsuario);
            email.send();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
