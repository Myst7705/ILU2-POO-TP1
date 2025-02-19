package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;

	public Village(String nom, int nbVillageoisMaximum, int nbEtals) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		marche = new Marche(nbEtals);
	}

	private static class Marche {
		private Etal[] etals;

		public Marche(int nbEtals) {
			this.etals = new Etal[nbEtals];
			for (int i = 0; i < nbEtals; i++) {
				etals[i] = new Etal();
			}
		}

		private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		}

		private int trouverEtalLibre() {
			for (int i = 0; i < etals.length; i++) {
				if (!(etals[i].isEtalOccupe())) {
					return i + 1; // pour affichage joli
				}
			}
			return -1;
		}

		private Etal[] trouverEtals(String produit) {
			int tailleEtals = 0;
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].contientProduit(produit)) {
					tailleEtals++;
				}
			}

			Etal[] etalsProduit = new Etal[tailleEtals];
			int iEtalsProduit = 0;
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].contientProduit(produit)) {
					etalsProduit[iEtalsProduit] = etals[i];
					iEtalsProduit++;
				}
			}

			return etalsProduit;
		}
		
		private Etal trouverVendeur(Gaulois gaulois) {
			for (int i = 0; i < etals.length; i++) {
				if ((gaulois.getNom()).equals(etals[i].getVendeur().getNom())){
					return etals[i];
				}
			}
			return null;
		}
		
		private String afficherMarche() {
			StringBuilder strMarche = new StringBuilder();
			int nbEtalOccupes = 0;
			
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].isEtalOccupe()) {
					nbEtalOccupes++;
					strMarche.append(etals[i].afficherEtal());
				}
			}
			
			int nbEtalsPasOccupes = etals.length - nbEtalOccupes;
			
			strMarche.append("Il reste " + nbEtalsPasOccupes + " etals non utilises dans le marche.");
			return strMarche.toString();
		}
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef " + chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom() + " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	public String installerVendeur(Gaulois vendeur, String produit, int nbProduit) {
		StringBuilder strVendeur = new StringBuilder();
		strVendeur.append(vendeur.getNom() + " cherche un endroit pour vendre " + nbProduit + " " + produit + ".\n");
		int iMarche = this.marche.trouverEtalLibre();
		
		if (iMarche == -1) {
			strVendeur.append("Il n'y a malheureusement plus d'etals disponible.\n");
		} else {
			strVendeur.append("Le vendeur " + vendeur.getNom() + " vend des " + produit + " a l'etal n " + iMarche + ".\n");
		}
		marche.utiliserEtal(iMarche, vendeur, produit, nbProduit);
		
		return strVendeur.toString();
	}
	
	public String rechercherVendeursProduit(String produit) {
		StringBuilder strVendeur = new StringBuilder();
		
	}
	
}