package com.moduretick.processor;

import java.text.NumberFormat;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import com.moduretick.domain.InteresseProdutoCliente;

@Component
public class ProcessatEmailProdutoClienteProcessor implements ItemProcessor<InteresseProdutoCliente, SimpleMailMessage> {
	
	@Override
	public SimpleMailMessage process(InteresseProdutoCliente interesseProdutoCliente) throws Exception {
		SimpleMailMessage email = new SimpleMailMessage();
		
		email.setFrom("xpto@no-reply.com");
		email.setTo(interesseProdutoCliente.getCliente().getEmail());
		email.setSubject("Promoçao Imperdivel");
		email.setText(gerarTextoPromocao(interesseProdutoCliente));
		
		Thread.sleep(2000);
		
		return email;
		
	}

	private String gerarTextoPromocao(InteresseProdutoCliente interesseProdutoCliente) {
		StringBuilder writer  = new StringBuilder();
		writer.append(String.format("Olá, %s!\n\n", interesseProdutoCliente.getCliente().getNome()));
		writer.append("Essa Promoção Pode Ser do Seu Interrese:\n\n");
		writer.append(String.format("%s - %s\n\n", interesseProdutoCliente.getProduto().getNome(), interesseProdutoCliente.getProduto().getDescricao()));
		writer.append(String.format("Por Apenas: %s!", 
				NumberFormat.getCurrencyInstance().format(interesseProdutoCliente.getProduto().getPreco())));
		
		return writer.toString();
	}
	
}
