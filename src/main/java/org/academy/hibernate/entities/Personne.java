package org.academy.hibernate.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@SuppressWarnings( { "unused", "serial" })
@Entity
@Table(name = "jpa09_hb_personne")
public class Personne implements Serializable {

	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	@Version
	private int version;

	@Column(length = 30, nullable = false, unique = true)
	private String nom;

	@Column(length = 30, nullable = false)
	private String prenom;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date datenaissance;

	@Column(nullable = false)
	private boolean marie;

	@Column(nullable = false)
	private int nbenfants;

	// relation principale Personne (one) -> Adresse (one)
	// impl�ment�e par la cl� �trang�re Personne(adresse_id) -> Adresse
	// cascade insertion Personne -> insertion Adresse
	// cascade maj Personne -> maj Adresse
	// cascade suppression Personne -> suppression Adresse
	// une Personne doit avoir 1 Adresse (nullable=false)
	// 1 Adresse n'appartient qu'� 1 personne (unique=true)
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "adresse_id", unique = true, nullable = false)
	private Adresse adresse;

	// relation Personne (many) -> Activite (many) via une table de jointure personne_activite
	// personne_activite(PERSONNE_ID) est cl� �tang�re sur Personne(id)
	// personne_activite(ACTIVITE_ID) est cl� �tang�re sur Activite(id)
	// plus de cascade sur les activit�s
	// @ManyToMany(cascade={CascadeType.PERSIST})
	@ManyToMany()
	@JoinTable(name = "jpa09_hb_personne_activite", joinColumns = @JoinColumn(name = "PERSONNE_ID"), inverseJoinColumns = @JoinColumn(name = "ACTIVITE_ID"))
	private Set<Activite> activites = new HashSet<Activite>();

	// constructeurs
	public Personne() {
	}

	public Personne(String nom, String prenom, Date datenaissance, boolean marie, int nbenfants) {
		setNom(nom);
		setPrenom(prenom);
		setDatenaissance(datenaissance);
		setMarie(marie);
		setNbenfants(nbenfants);
	}

	// toString
	public String toString() {
		return String.format("P[%d,%d,%s,%s,%s,%s,%d,%d]", getId(), getVersion(), getNom(), getPrenom(), new SimpleDateFormat("dd/MM/yyyy")
				.format(getDatenaissance()), isMarie(), getNbenfants(), getAdresse().getId());
	}

	// getters and setters
	public Long getId() {
		return id;
	}

	private void setId(Long id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	private void setVersion(int version) {
		this.version = version;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Date getDatenaissance() {
		return datenaissance;
	}

	public void setDatenaissance(Date datenaissance) {
		this.datenaissance = datenaissance;
	}

	public boolean isMarie() {
		return marie;
	}

	public void setMarie(boolean marie) {
		this.marie = marie;
	}

	public int getNbenfants() {
		return nbenfants;
	}

	public void setNbenfants(int nbenfants) {
		this.nbenfants = nbenfants;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public Set<Activite> getActivites() {
		return activites;
	}

	public void setActivites(Set<Activite> activites) {
		this.activites = activites;
	}

}
