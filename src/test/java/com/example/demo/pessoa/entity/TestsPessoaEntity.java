package testes.pessoa.entity;

import com.example.demo.pessoa.entity.Pessoa;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class TestsPessoaEntity {

	@Test
	void contextLoads() {
		System.out.println("Demo test");
	}
	@Test
	void testNewPessoaCPF_whenValidValuesProvided(){
		Pessoa pessoa = new Pessoa("Julia", "12345678901");
		Assert.notNull(pessoa, "Documento com length correto retornou erro");
	}
	@Test
	void testNewPessoaCNPJ_whenValidValuesProvided(){
		Pessoa pessoa = new Pessoa("Chris", "12345678901234");
		Assert.notNull(pessoa, "Documento com length correto retornou erro");
	}

}
