package br.com.caelum.financas.util;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.TipoMovimentacao;

public class TesteFuncoesJPQL {

	public static void main(String[] args) {
		

		Conta conta = new Conta();
		conta.setId(2);
		EntityManager em = new JPAUtil().getEntityManager();
		
		TypedQuery<Double> typedQuery = em.createNamedQuery("MediasPorDiaETipo", Double.class);
		typedQuery.setParameter("pConta", conta);
		typedQuery.setParameter("pTipo", TipoMovimentacao.SAIDA);
		List<Double> medias = typedQuery.getResultList();

		String jpql = "select sum(m.valor) from Movimentacao m where m.conta = :pConta" + " and m.tipo = :pTipo"
				+ " order by m.valor desc";

		Query query = em.createQuery(jpql);
		query.setParameter("pConta", conta);
		query.setParameter("pTipo", TipoMovimentacao.ENTRADA);
		BigDecimal resultado = (BigDecimal) query.getSingleResult();
		System.out.println("a soma da conta é: "+ resultado);
		em.close();
		
		EntityManager em2 = new JPAUtil().getEntityManager();
		String jpql2 = "select avg(m.valor) from Movimentacao m where " + " m.tipo = :pTipo"
				+ " group by m.data order by m.valor desc";

		TypedQuery<Double> query2 = em2.createQuery(jpql2, Double.class);
		query2.setParameter("pTipo", TipoMovimentacao.ENTRADA);
		List<Double> resultado2 = query2.getResultList();
		System.out.println("a media do dia 8: "+ resultado2.get(0));
		System.out.println("a media do dia 9: "+ resultado2.get(1));
		em2.close();
	}
}
