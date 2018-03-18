package br.com.caelum.financas.testeJPQL;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;

public class testeJPQL {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("financas");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Conta conta = new Conta();
		conta.setId(2);
		// String sql = "select m from Movimentacao m where m.conta.id = 2";
		String sql = "select m from Movimentacao m where m.conta = :pConta and m.tipo = :pTipo order by m.valor desc";

		Query q = em.createQuery(sql);
		q.setParameter("pConta", conta);
		q.setParameter("pTipo", TipoMovimentacao.ENTRADA);
		List<Movimentacao> lista = q.getResultList();

		for (Movimentacao movimentacao : lista) {
			System.out.println(lista);
		}
	}

}
