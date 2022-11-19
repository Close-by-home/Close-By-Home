package closebyhome.closebyhome.service;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.SimpleEmail;

public class EmailService {

    public void EnviarEmail() {
        String meuEmail = "email@gmail.com";
        String senha = "senhaDeAppFornecidaPeloGoogle";

        SimpleEmail email = new SimpleEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator(meuEmail, senha));
        email.setSSLOnConnect(true);

        try {

            email.setFrom(meuEmail);
            email.setSubject("Augusto");
            email.setMsg("Feliz Anivesario");
            email.addTo(meuEmail);
            email.send();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
