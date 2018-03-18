package br.com.caelum.financas.teste;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TesteJPARelacionamento {

	public static void main(String[] args) {

		Conta conta = new Conta();
		conta.setAgencia("9999");
		conta.setBanco("Itau");
		conta.setNumero("9999");
		conta.setTitular("Leonardo");

		Movimentacao movimentacao = new Movimentacao();
		movimentacao.setData(Calendar.getInstance());
		movimentacao.setDescricao("Churrascaria");
		movimentacao.setTipo(TipoMovimentacao.SAIDA);
		movimentacao.setValor(new BigDecimal("200.0"));
		movimentacao.setConta(conta);
		
		EntityManager em = new JPAUtil().getEntityManager();
		
		em.getTransaction().begin();
		em.persist(conta);
		em.persist(movimentacao);
		em.getTransaction().commit();
		
		em.close();
		
		EntityManager em2 = new JPAUtil().getEntityManager();
		Movimentacao m = em2.find(Movimentacao.class, 13);
		em2.getTransaction().begin();
		m.getConta().setTitular("Allan neves");
		em2.merge(movimentacao);
		em2.getTransaction().commit();
		em2.close();
		System.out.println(m.getConta());

	}

}
