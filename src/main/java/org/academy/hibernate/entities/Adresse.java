package org.academy.hibernate.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@SuppressWarnings("serial")
@Entity
@Table(name="jpa09_hb_adresse")
public class Adresse implements Serializable{

	// champs
	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false)
	@Version
	private int version;

	@Column(length = 30, nullable = false)
	private String adr1;
	@Column(length = 30)
	private String adr2;
	@Column(length = 30)
	private String adr3;
	@Column(length = 5, nullable = false)
	private String codePostal;
	@Column(length = 20, nullable = false)
	private String ville;
	@Column(length = 3)
	private String cedex;
	@Column(length = 20, nullable = false)
	private String pays;
	@OneToOne(mappedBy = "adresse")
	private Personne personne;

	// constructeurs
	public Adresse() {

	}

	public Adresse(String adr1, String adr2, String adr3, String codePostal, String ville, String cedex, String pays) {
		super();
		this.adr1 = adr1;
		this.adr2 = adr2;
		this.adr3 = adr3;
		this.codePostal = codePostal;
		this.ville = ville;
		this.cedex = cedex;
		this.pays = pays;
	}

	// getters et setters
	public String getAdr1() {
		return adr1;
	}

	public void setAdr1(String adr1) {
		this.adr1 = adr1;
	}

	public String getAdr2() {
		return adr2;
	}

	public void setAdr2(String adr2) {
		this.adr2 = adr2;
	}

	public String getAdr3() {
		return adr3;
	}

	public void setAdr3(String adr3) {
		this.adr3 = adr3;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getCedex() {
		return cedex;
	}

	public void setCedex(String cedex) {
		this.cedex = cedex;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public Personne getPersonne() {
		return personne;
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
	}

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

	// toString
	public String toString() {
		return String.format("A[%d,%s,%s,%s,%s,%s,%s,%s]", getId(),getAdr1(), getAdr2(), getAdr3(), getCodePostal(), getVille(),
				getCedex(), getPays());
	}
}
