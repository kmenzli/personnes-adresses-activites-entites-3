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

public class InitDB {

	// constantes
	private final static String TABLE_PERSONNE_ACTIVITE = "jpa09_hb_personne_activite";

	private final static String TABLE_PERSONNE = "jpa09_hb_personne";

	private final static String TABLE_ACTIVITE = "jpa09_hb_activite";

	private final static String TABLE_ADRESSE = "jpa09_hb_adresse";

	public static void main(String[] args) throws ParseException {
		// Contexte de persistance
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = null;
		// on r�cup�re un EntityManager � partir de l'EntityManagerFactory pr�c�dent
		em = emf.createEntityManager();
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
		// cr�ation activites
		Activite act1 = new Activite();
		act1.setNom("act1");
		Activite act2 = new Activite();
		act2.setNom("act2");
		Activite act3 = new Activite();
		act3.setNom("act3");
		// cr�ation personnes
		Personne p1 = new Personne("p1", "Paul", new SimpleDateFormat("dd/MM/yy").parse("31/01/2000"), true, 2);
		Personne p2 = new Personne("p2", "Sylvie", new SimpleDateFormat("dd/MM/yy").parse("05/07/2001"), false, 0);
		Personne p3 = new Personne("p3", "Sylvie", new SimpleDateFormat("dd/MM/yy").parse("05/07/2001"), false, 0);
		// cr�ation adresses
		Adresse adr1 = new Adresse("adr1", null, null, "49000", "Angers", null, "France");
		Adresse adr2 = new Adresse("adr2", "Les Mimosas", "15 av Foch", "49002", "Angers", "03", "France");
		Adresse adr3 = new Adresse("adr3", "x", "x", "x", "x", "x", "x");
		Adresse adr4 = new Adresse("adr4", "y", "y", "y", "y", "y", "y");
		// associations personne <--> adresse
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
		// persistance des activites
		em.persist(act1);
		em.persist(act2);
		em.persist(act3);
		// persistance des personnes
		em.persist(p1);
		em.persist(p2);
		em.persist(p3);
		// et de l'adresse a4 non li�e � une personne
		em.persist(adr4);
		// affichage personnes
		System.out.println("[personnes]");
		for (Object p : em.createQuery("select p from Personne p order by p.nom asc").getResultList()) {
			System.out.println(p);
		}
		// affichage adresses
		System.out.println("[adresses]");
		for (Object a : em.createQuery("select a from Adresse a").getResultList()) {
			System.out.println(a);
		}
		// affichage activit�s
		System.out.println("[activites]");
		for (Object a : em.createQuery("select a from Activite a").getResultList()) {
			System.out.println(a);
		}
		// affichage personnes/activit�s
		System.out.println("[personnes/activites]");
		Iterator iterator = em.createQuery("select p.id,a.id from Personne p join p.activites a").getResultList().iterator();
		while (iterator.hasNext()) {
			Object[] row = (Object[]) iterator.next();
			System.out.format("[%d,%d]%n", (Long) row[0], (Long) row[1]);
		}
		// fin transaction
		tx.commit();
		// fin EntityManager
		em.close();
		// fin EntityManagerFactory
		emf.close();
		// log
		System.out.println("termin�...");

	}
}
