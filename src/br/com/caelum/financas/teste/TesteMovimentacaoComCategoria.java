package br.com.caelum.financas.teste;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.caelum.financas.modelo.Categoria;
import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TesteMovimentacaoComCategoria {
	public static void main(String[] args) {

		Conta conta = new Conta();
		conta.setId(2);

		Categoria categoria1 = new Categoria("viagem");
		Categoria categoria2 = new Categoria("negocio");

		Movimentacao m1 = new Movimentacao();
		m1.setData(Calendar.getInstance());
		m1.setDescricao("viagem a sp");
		m1.setTipo(TipoMovimentacao.ENTRADA);
		m1.setValor(new BigDecimal("100.0"));
		m1.setConta(conta);

		List<Categoria> lista = new ArrayList<Categoria>();
		lista.add(categoria1);
		lista.add(categoria2);
		m1.setCategoria(lista);

		Movimentacao m2 = new Movimentacao();
		m2.setData(Calendar.getInstance());
		m2.setDescricao("viagem a belem");
		m2.setTipo(TipoMovimentacao.ENTRADA);
		m2.setValor(new BigDecimal("400.0"));

		List<Categoria> lista2 = new ArrayList<Categoria>();
		lista.add(categoria1);
		lista.add(categoria2);
		m2.setCategoria(lista2);
		m2.setConta(conta);

		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		em.persist(categoria1);
		em.persist(categoria2);
		
		em.persist(m1);
		em.persist(m2);

		em.getTransaction().commit();
		em.close();
	}
}
