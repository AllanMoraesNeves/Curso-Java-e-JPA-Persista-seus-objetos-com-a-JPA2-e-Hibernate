package br.com.caelum.financas.util;

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
import sun.util.resources.CalendarData;

public class PopulaMovimentacoesComCategoria {
	public static void main(String[] args) {

		Conta conta = new Conta();
		conta.setId(7);

		Categoria categoria1 = new Categoria();
		Categoria categoria2 = new Categoria();
		categoria1.setId(1);
		categoria2.setId(2);

		Movimentacao m1 = new Movimentacao();
		m1.setData(Calendar.getInstance());
		m1.setDescricao("viagem a cruzeiro");
		m1.setTipo(TipoMovimentacao.ENTRADA);
		m1.setValor(new BigDecimal("5455.0"));
		m1.setConta(conta);

		List<Categoria> lista = new ArrayList<Categoria>();
		lista.add(categoria2);
		m1.setCategoria(lista);

		Movimentacao m2 = new Movimentacao();
		m2.setDescricao("viagem a rio de janeiro");
		m2.setTipo(TipoMovimentacao.ENTRADA);
		m2.setValor(new BigDecimal("400.0"));

		List<Categoria> lista2 = new ArrayList<Categoria>();
		lista.add(categoria1);
		m2.setCategoria(lista2);
		m2.setConta(conta);
		
		Calendar amanha = Calendar.getInstance();
		amanha.add(Calendar.DAY_OF_YEAR, 1);

		m2.setData(amanha); //amanha

		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		
		em.persist(m1);
		em.persist(m2);

		em.getTransaction().commit();
		em.close();
	}
}
