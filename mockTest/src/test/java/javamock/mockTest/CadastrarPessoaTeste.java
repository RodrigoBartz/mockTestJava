package javamock.mockTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CadastrarPessoaTeste {
	
	@Mock
	private ApiDosCorreios apiDosCorreios;
	
	@InjectMocks
	private CadastrarPessoa cadastrarPessoa;
	
	@Test
	void testCadastrarPessoa() {
		
		DadosLocalizacao dadosLocalizacao = new DadosLocalizacao("SP", "Atibaia", "Av. Brasil", "Casa", "Centro");
		
		Mockito.when(apiDosCorreios.buscaDadosComBaseNoCep("22291140")).thenReturn(dadosLocalizacao);
		
		Pessoa clayton = cadastrarPessoa.cadastrarPessoa("Clayton", "12345678", LocalDate.of(1976, 04, 05), "22291140");
		
		assertEquals("Clayton", clayton.getNome());
		assertEquals("12345678", clayton.getDocumento());
		assertEquals("SP", clayton.getEndereco().getUf());
		assertEquals("Casa", clayton.getEndereco().getComplemento());
	}
}
