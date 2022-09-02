package CloseByHome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CloseByHomeApplication {

	public static void main(String[] args) {

		SpringApplication.run(CloseByHomeApplication.class, args);


		UsuarioComum a = new UsuarioComum(
				"Carlos","177","Carlos@gmail.com",
				"carlos123" ,false,"20/08/2000",20,21,909070
		);

		BancoDeDados teste = new BancoDeDados();
		teste.adiconarUsuario(a);

		a.setListaDeUsuarios(teste.getUsuarios());
	}

}
