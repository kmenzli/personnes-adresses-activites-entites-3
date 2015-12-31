package org.academy.hibernate.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@SuppressWarnings("serial")
@Entity
@Table(name = "jpa09_hb_activite")
public class Activite implements Serializable {

	// champs
	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	@Version
	private int version;

	@Column(length = 30, nullable = false, unique = true)
	private String nom;

	// plus de relation inverse avec Personne
	// @ManyToMany(mappedBy = "activites")
	// private Set<Personne> personnes = new HashSet<Personne>();

	// constructeurs
	public Activite() {

	}

	public Activite(Long id, int version, String nom) {
		super();
		setId(id);
		setVersion(version);
		setNom(nom);
	}

	// getters et setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	// toString
	public String toString() {
		return String.format("Ac[%d,%d,%s]", getId(), getVersion(), getNom());
	}
}
