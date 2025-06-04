package com.example.models;

import java.util.Date;

public class Emprunt {
    private int numEmprunt;
    private int numEtudiant;
    private int codeLivre;
    private Date dateEmprunt;
    private Date dateRetourPrevue;
    private Date dateRetourEffective;
    private String statut;

    public Emprunt() {}

    public Emprunt(int numEmprunt, int numEtudiant, int codeLivre, Date dateEmprunt,
                   Date dateRetourPrevue, Date dateRetourEffective, String statut) {
        this.numEmprunt = numEmprunt;
        this.numEtudiant = numEtudiant;
        this.codeLivre = codeLivre;
        this.dateEmprunt = dateEmprunt;
        this.dateRetourPrevue = dateRetourPrevue;
        this.dateRetourEffective = dateRetourEffective;
        this.statut = statut;
    }

    public int getNumEmprunt() {
        return numEmprunt;
    }

    public void setNumEmprunt(int numEmprunt) {
        this.numEmprunt = numEmprunt;
    }

    public int getNumEtudiant() {
        return numEtudiant;
    }

    public void setNumEtudiant(int numEtudiant) {
        this.numEtudiant = numEtudiant;
    }

    public int getCodeLivre() {
        return codeLivre;
    }

    public void setCodeLivre(int codeLivre) {
        this.codeLivre = codeLivre;
    }

    public Date getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(Date dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public Date getDateRetourPrevue() {
        return dateRetourPrevue;
    }

    public void setDateRetourPrevue(Date dateRetourPrevue) {
        this.dateRetourPrevue = dateRetourPrevue;
    }

    public Date getDateRetourEffective() {
        return dateRetourEffective;
    }

    public void setDateRetourEffective(Date dateRetourEffective) {
        this.dateRetourEffective = dateRetourEffective;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}

