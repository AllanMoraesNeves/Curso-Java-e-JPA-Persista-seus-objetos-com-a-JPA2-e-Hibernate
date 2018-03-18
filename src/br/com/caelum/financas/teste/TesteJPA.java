package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.util.JPAUtil;

public class TesteJPA {

	public static void main(String[] args) {

		Conta conta = new Conta();
		conta.setTitular("Allan neves");
		conta.setBanco("bradesco");
		conta.setAgencia("043");
		conta.setNumero("54321");

		
		//EntityManagerFactory emf = Persistence.createEntityManagerFactory("financas");
		//EntityManager em  = emf.createEntityManager();
		EntityManager em = new JPAUtil().getEntityManager();
		
		em.getTransaction().begin();
		em.persist(conta);
		conta.setBanco("brasil");
		em.getTransaction().commit();
		
		em.close();
	}
}
