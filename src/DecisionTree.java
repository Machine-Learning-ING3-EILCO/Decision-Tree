import java.util.ArrayList;

public class DecisionTree {
	private Node racine;

	public DecisionTree() {
		super();
	}

	public void setRacine(Node racine) {
		this.racine = racine;
	}

	public double entropy(DataFrame data, int attributCible) {
		double entropy = 0;
		int total;
		total = data.getExemples().size();
		double[] nb = new double[data.getValeursAttributs().get(attributCible).size()];
		// nb retourne un tableau de taille égale au nbr possible des instanciations que
		// peut prendre une colonne
		// Exemple attributCible = 4 -> décision peut prendre 2 valeurs : oui et non

		/* Initialiser nb à 0 */
		for (int i = 0; i < nb.length; i++) {
			nb[i] = 0;
		}
		/* Calculer le nombre de chaque valeur */ // Nb oui & Nb non
		int i = 0;
		for (i = 0; i < nb.length; i++) {
			String Vi = data.getValeursAttributs().get(attributCible).get(i);
			nb[i]=data.select(attributCible, Vi).getExemples().size();
			/* Calculer l'entropy */
			if(nb[i]==0) continue;
			entropy =entropy + (-nb[i] / total) * (Math.log(nb[i] / total) / Math.log(2));
		}

		return entropy;

	}

	//Entropy de deux attributs 
	double entropy2Attributs(DataFrame data, int attributCible, int attribut) {
		double entropy2Attributs=0;
		for (String valeur:data.getValeursAttributs().get(attribut)) {
			DataFrame subData=data.select(attribut, valeur);
			entropy2Attributs=entropy2Attributs+entropy(subData,attributCible)*subData.getExemples().size()/data.getExemples().size();
		}
		return entropy2Attributs;
		
	}
	
	// La méthode Gain
	double gain (DataFrame data, int attributCible, int attribut) {
		double gain=0;
		gain= entropy(data, attributCible)-entropy2Attributs(data,attributCible,attribut);
		return gain;
		
	}
	
	//TODO impléménter allEquals pour la semaine prochaine 
	public boolean allEquals(DataFrame data, int attributCible) {
		boolean allEqual = true;
		if(data.getExemples().size() == 0) {
			return true;
		}
		double[] nb = new double[data.getValeursAttributs().get(attributCible).size()];
		// nb retourne un tableau de taille égale au nbr possible des instanciations que
		// peut prendre une colonne
		// Exemple attributCible = 4 -> décision peut prendre 2 valeurs : oui et non
		String Vi = data.getValeursAttributs().get(attributCible).get(0);
		System.out.println("VI" + Vi);
		/* Calculer le nombre de chaque valeur */ // Nb oui & Nb non
		for (int i = 1; i < nb.length; i++) {
			if(!Vi.equals(data.getValeursAttributs().get(attributCible).get(i))){
				//System.out.println(data.getValeursAttributs().get(attributCible).get(i));
				allEqual = false;
			}
			
		}
		return allEqual;
		
	}
	
	//TODO impléménter plusFrequente pour la semaine prochaine 
	//la méthode plusFrequente qui permet de retourner la colonne la plus séparente de données
	public int plusFrequente(DataFrame data, int attributCible) {
		int plusFrequente = 0;
		int[] nb= new int [data.getValeursAttributs().get(attributCible).size()];
		for(int i=0; i<nb.length; i++) {
			nb[i]=0;
		}
		for(ArrayList<String> ex: data.getExemples()) {
			String v= ex.get(attributCible);
			for(int i=0; i< nb.length;i++) {
				if(v.equals(data.getValeursAttributs().get(attributCible).get(i))) {
					nb[i]++;
				}
			}
			
			int iMax=0;
			int Max=nb[0];
			for(int  i=1;i<nb.length;i++) {
				if(Max<nb[i]) {
					Max=nb[i];
					iMax=i;
				}
			}
			plusFrequente = iMax;
		}
		return plusFrequente;
		
		
		
	}
	
	
	//id3  : l'algorithme qui construit l'arbre de décision
	Node id3(DataFrame data, int attributCible, ArrayList<Integer> attributs) {
		
		
		//-------------Code pour désigner la racine
		//calcule du gain de tous les attributs et retourner 
		//l'attribut ayant le plus grand gain
		
		
		if(allEquals(data, attributCible)) {
			racine.setFeuille(true);
			String Vi = data.getValeursAttributs().get(attributCible).get(0);
			racine.setIdAttribut(attributCible);
			//à continuer
		}
		else if ( attributs == null ) {
			racine.setFeuille(true);
			int indiceplusFrequent=	plusFrequente(data,attributCible);
			racine.setIdAttribut(attributCible);
		}
		else {
			//trouver l'attribut de plus grand gain
			int i=0; //indice de la colonne
			double maxGain=0;
			int indiceMaxGain=-1;
			for(i=0;i<attributCible;i++) {
				double gain=this.gain(data, attributCible, i);
				if(gain>maxGain) {
					maxGain=gain;
					indiceMaxGain=i;
				}
			}
			racine.setIdAttribut(indiceMaxGain);
			
			
			//id3 appel recursive
			//id3(subdata.select, attributCible, attributs.remove)
		}
		return racine;
	}
	
	
	
	
}
