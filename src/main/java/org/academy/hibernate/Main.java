package org.academy.hibernate;

import org.academy.hibernate.entities.Activite;
import org.academy.hibernate.entities.Adresse;
import org.academy.hibernate.entities.Personne;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

@SuppressWarnings( { "unused", "unchecked" })
public class Main {

	// constantes
	private final static String TABLE_PERSONNE_ACTIVITE = "jpa09_hb_personne_activite";

	private final static String TABLE_PERSONNE = "jpa09_hb_personne";

	private final static String TABLE_ACTIVITE = "jpa09_hb_activite";

	private final static String TABLE_ADRESSE = "jpa09_hb_adresse";

	// Contexte de persistance
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");

	private static EntityManager em = null;

	// objets partag�s
	private static Personne p1, p2, p3;

	private static Adresse adr1, adr2, adr3, adr4;

	private static Activite act1, act2, act3;

	public static void main(String[] args) throws Exception {
		// nettoyage base
		log("clean");
		clean();

		// dumps
		dumpPersonne();
		dumpAdresse();
		dumpActivite();

		// test1
		log("test1");
		test1();

		// test2
		log("test2");
		test2();

		// test3
		log("test3");
		test3();

		// test4
		log("test4");
		test4();

		// test5
		log("test5");
		test5();

		// test6
		log("test6");
		test6();

		// fin contexte de persistance
		if (em != null && em.isOpen())
			em.close();

		// fermeture EntityManagerFactory
		emf.close();
	}

	// r�cup�rer l'EntityManager courant
	private static EntityManager getEntityManager() {
		if (em == null || !em.isOpen()) {
			em = emf.createEntityManager();
		}
		return em;
	}

	// r�cup�rer un EntityManager neuf
	private static EntityManager getNewEntityManager() {
		if (em != null && em.isOpen()) {
			em.close();
		}
		em = emf.createEntityManager();
		return em;
	}

	// affichage contenu table Personne
	private static void dumpPersonne() {
		// contexte de persistance
		EntityManager em = getEntityManager();
		// d�but transaction
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		// affichage personnes
		System.out.println("[personnes]");
		for (Object p : em.createQuery("select p from Personne p order by p.nom asc").getResultList()) {
			System.out.println(p);
		}
		// fin transaction
		tx.commit();
	}

	// affichage contenu table Adresse
	private static void dumpAdresse() {
		// contexte de persistance
		EntityManager em = getEntityManager();
		// d�but transaction
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		// affichage personnes
		System.out.println("[adresses]");
		for (Object a : em.createQuery("select a from Adresse a").getResultList()) {
			System.out.println(a);
		}
		// fin transaction
		tx.commit();
	}

	// affichage contenu table Activite
	private static void dumpActivite() {
		// contexte de persistance
		EntityManager em = getEntityManager();
		// d�but transaction
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		// affichage personnes
		System.out.println("[activites]");
		for (Object a : em.createQuery("select a from Activite a").getResultList()) {
			System.out.println(a);
		}
		// fin transaction
		tx.commit();
	}

	// affichage contenu table Personne_Activite
	private static void dumpPersonne_Activite() {
		// contexte de persistance
		EntityManager em = getEntityManager();
		// d�but transaction
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		// affichage personnes/activit�s
		System.out.println("[personnes/activites]");
		Iterator iterator = em.createQuery("select p.id,p.activites.id from Personne p").getResultList().iterator();
		while (iterator.hasNext()) {
			Object[] row = (Object[]) iterator.next();
			System.out.format("[%d,%d]%n", (Long) row[0], (Long) row[1]);
		}
		// fin transaction
		tx.commit();
	}

	// raz BD
	private static void clean() {
		// contexte de persistance
		EntityManager em = getEntityManager();
		// d�but transaction
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		// requ�te
		Query sql1;
		// supprimer les �l�ments de la table de jointure
		sql1 = em.createNativeQuery("delete from " + TABLE_PERSONNE_ACTIVITE);
		sql1.executeUpdate();
		// supprimer les �l�ments de la table PERSONNE
		sql1 = em.createNativeQuery("delete from " + TABLE_PERSONNE);
		sql1.executeUpdate();
		// supprimer les �l�ments de la table ACTIVITE
		sql1 = em.createNativeQuery("delete from " + TABLE_ACTIVITE);
		sql1.executeUpdate();
		// supprimer les �l�ments de la table ADRESSE
		sql1 = em.createNativeQuery("delete from " + TABLE_ADRESSE);
		sql1.executeUpdate();
		// fin transaction
		tx.commit();
	}

	// logs
	private static void log(String message) {
		System.out.println("main : ----------- " + message);
	}

	// cr�ation d'objets
	public static void test1() throws ParseException {
		// contexte de persistance
		EntityManager em = getEntityManager();
		// cr�ation personnes
		p1 = new Personne("p1", "Paul", new SimpleDateFormat("dd/MM/yy").parse("31/01/2000"), true, 2);
		p2 = new Personne("p2", "Sylvie", new SimpleDateFormat("dd/MM/yy").parse("05/07/2001"), false, 0);
		p3 = new Personne("p3", "Sylvie", new SimpleDateFormat("dd/MM/yy").parse("05/07/2001"), false, 0);
		// cr�ation adresses
		adr1 = new Adresse("adr1", null, null, "49000", "Angers", null, "France");
		adr2 = new Adresse("adr2", "Les Mimosas", "15 av Foch", "49002", "Angers", "03", "France");
		adr3 = new Adresse("adr3", "x", "x", "x", "x", "x", "x");
		adr4 = new Adresse("adr4", "y", "y", "y", "y", "y", "y");
		// cr�ation activites
		act1 = new Activite();
		act1.setNom("act1");
		act2 = new Activite();
		act2.setNom("act2");
		act3 = new Activite();
		act3.setNom("act3");
		// persistance activites
		em.persist(act1);
		em.persist(act2);
		em.persist(act3);
		// associations personnes <--> adresses
		p1.setAdresse(adr1);
		adr1.setPersonne(p1);
		p2.setAdresse(adr2);
		adr2.setPersonne(p2);
		p3.setAdresse(adr3);
		adr3.setPersonne(p3);
		// associations personnes <--> activites
		p1.getActivites().add(act1);
		p1.getActivites().add(act2);
		p2.getActivites().add(act1);
		p2.getActivites().add(act3);
		// d�but transaction
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		// persistance des personnes (donc des adresses par cascade ainsi que des activit�s)
		em.persist(p1);
		em.persist(p2);
		em.persist(p3);
		// et de l'adresse a4 non li�e � une personne
		em.persist(adr4);
		// fin transaction
		tx.commit();
		// dumps
		dumpPersonne();
		dumpActivite();
		dumpAdresse();
		dumpPersonne_Activite();
	}

	// suppression Personne p1
	public static void test2() {
		// contexte de persistance
		EntityManager em = getEntityManager();
		// d�but transaction
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		// suppression personne p1
		em.remove(p1);
		// fin transaction
		tx.commit();
		// on affiche les nouvelles tables
		dumpPersonne();
		dumpActivite();
		dumpAdresse();
		dumpPersonne_Activite();
	}

	// suppression activite act1
	public static void test3() {
		// contexte de persistance
		EntityManager em = getEntityManager();
		// d�but transaction
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		// suppression activit� act1 de p2
		p2.getActivites().remove(act1);
		// on retire act1 du contexte de persistance
		em.remove(act1);
		// fin transactions
		tx.commit();
		// on affiche les nouvelles tables
		dumpPersonne();
		dumpActivite();
		dumpAdresse();
		dumpPersonne_Activite();
	}

	// r�cup�ration activit�s d'une personne
	public static void test4() {
		// contexte de persistance
		EntityManager em = getNewEntityManager();
		// d�but transaction
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		// on r�cup�re la personne p2
		System.out.format("1 - Activit�s de la personne p2 (JPQL) :%n");
		// on passe par la relation principale de p2
		p2 = em.find(Personne.class, p2.getId());
		System.out.format("2 - Activit�s de la personne p2 (relation principale) :%n");
		// on scanne ses activit�s
		for (Activite a : p2.getActivites()) {
			System.out.println(a.getNom());
		}
		// fin transaction
		tx.commit();
	}

	// r�cup�ration personnes faisant une activit� donn�e
	public static void test5() {
		// contexte de persistance
		EntityManager em = getNewEntityManager();
		// d�but transaction
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		System.out.format("1 - Personnes pratiquant l'activit� act3 (JPQL) :%n");
		// on demande les activit�s de p2
		for (Object pa : em.createQuery("select p.nom from Personne p join p.activites a where a.nom='act3'").getResultList()) {
			System.out.println(pa);
		}
		// fin transaction
		tx.commit();
	}

	// modification des activit�s d'une personne
	public static void test6() {
		// contexte de persistance
		EntityManager em = getNewEntityManager();
		// d�but transaction
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		// on r�cup�re personne p2
		p2 = em.find(Personne.class, p2.getId());
		// on r�cup�re activit� act2
		act2 = em.find(Activite.class, act2.getId());
		// p2 ne pratique plus que l'activit� act2
		p2.getActivites().clear();
		p2.getActivites().add(act2);
		// fin transaction
		tx.commit();
		// on affiche les nouvelles tables
		dumpPersonne();
		dumpActivite();
		dumpPersonne_Activite();
	}

}