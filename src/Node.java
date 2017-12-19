import java.util.ArrayList;

public class Node {
	private int idAttribut; //l'indice de la colonne
	private String attribut; //Le nom de la colonne
	private boolean feuille; //test si le node est une feuille ou pas encore
	private ArrayList<Node> successeurs; //Les noeuds successeurs
	private ArrayList<String> etiquettes; // La valeurs dans les arcs
	
	
	
	//Méthodes
	
	public void setIdAttribut(int idAttribut) {
		this.idAttribut = idAttribut;
	}


	public void setEtiquettes(ArrayList<String> etiquettes) {
		this.etiquettes = etiquettes;
	}


	public void setAttribut(String attribut) {
		this.attribut = attribut;
	}


	//Constructeurs
	public Node(int idAttribut, String attribut, boolean feuille) {
		super();
		this.idAttribut = idAttribut;
		this.attribut = attribut;
		this.feuille = feuille;
	}
	
	public Node() {
		
	}
	
	
	//getters
	public int getIdAttribut() {
		return idAttribut;
	}
	
	public String getAttribut() {
		return attribut;
	}
	public void setFeuille(boolean feuille) {
		this.feuille = feuille;
	}


	public boolean isFeuille() {
		return feuille;
	}
	public ArrayList<Node> getSuccesseurs() {
		return successeurs;
	}
	
	public Node getSuccesseur(int i) {
		return  successeurs.get(i);
	}
	public ArrayList<String> getEtiquettes() {
		return etiquettes;
	}
	public String getEtiquette(int i) {
		return etiquettes.get(i);
	}
	
	public void ajouter(String etiquette, Node successeur) {
		successeurs.add(successeur); //add noeuds successeur
		etiquettes.add(etiquette); //add la valeur d'un arc
	}


	@Override
	public String toString() {
		return "Node [idAttribut=" + idAttribut + ", attribut=" + attribut + ", feuille=" + feuille + ", successeurs="
				+ successeurs + ", etiquettes=" + etiquettes +"]";
	}
	
	

}
