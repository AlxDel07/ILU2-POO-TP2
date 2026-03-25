package frontiere;

import controleur.ControlAcheterProduit;
import personnages.Gaulois;

public class BoundaryAcheterProduit {
	private ControlAcheterProduit controlAcheterProduit;

	public BoundaryAcheterProduit(ControlAcheterProduit controlAcheterProduit) {
		this.controlAcheterProduit = controlAcheterProduit;
	}

	public void acheterProduit(String nomAcheteur) {
		if(!controlAcheterProduit.peutAcheter(nomAcheteur)) {
			System.out.println("Désolé, il faut être un villageois pour commercer ici");
		}else {
			StringBuilder questionProduit = new StringBuilder();
			questionProduit.append("Quel produit voulez-vous acheter ?\n");
			String produit = Clavier.entrerChaine(questionProduit.toString());
			Gaulois[] vendeurs = controlAcheterProduit.etalExistant(produit);
			if(vendeurs == null) {
				System.out.println("Désolé, personne ne vend ce produit au marché");
			}else {
				System.out.println("Chez qui voulez vous acheter : ");
				for(int i=0;i<vendeurs.length;i++) {
					System.out.println(i+1 +  " - " + vendeurs[i].getNom()); 
				}
				int numVendeur = Clavier.entrerEntier("");
				Gaulois vendeur = vendeurs[numVendeur - 1];
				StringBuilder questionQuantite = new StringBuilder();
				questionQuantite.append("Quelle quantité voulez-vous acheter ?");
				int quantite = Clavier.entrerEntier(questionQuantite.toString());
				int quantiteAchetee = controlAcheterProduit.acheter(vendeur, quantite);
				if(quantiteAchetee == 0) {
					System.out.println(nomAcheteur + " veut acheter " + quantite + " " + produit + 
							" malheureusement, il n'y en a plus");
				}else if(quantiteAchetee < quantite) {
					System.out.println(nomAcheteur + " veut acheter " + quantite + " " + produit + 
							" malheureusement, " + vendeur.getNom() + " n'en a plus que " + quantiteAchetee + " "
							+ nomAcheteur + " achète tout le stock");
				}else {
					System.out.println(nomAcheteur + " achète " + quantite + " " + produit + " à " + vendeur.getNom());
				}
			}
		}
	}
}
