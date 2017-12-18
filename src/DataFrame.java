import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataFrame {
	private ArrayList<String> nomsAttributs; //nom des colonnes 
	private ArrayList<ArrayList<String>> exemples;
	private ArrayList<ArrayList<String>> valeursAttributs;//les valeurs possibles des arcs d'un noeud
	
	
	public DataFrame() {
		nomsAttributs = new ArrayList<String>();
    	exemples = new ArrayList<ArrayList<String>>();
    	valeursAttributs = new ArrayList<ArrayList<String>>();
	}
	
	public DataFrame(String nomFichier) {
    	nomsAttributs = new ArrayList<String>();
    	exemples = new ArrayList<ArrayList<String>>();
    	valeursAttributs = new ArrayList<ArrayList<String>>();
    	
    	try {
    	    File f = new File(nomFichier);
    	    Scanner scanner = new Scanner(f);
    	    
    	  			
    	    // la premi√®re ligne : noms des attributs
    	    Scanner sc = new Scanner(scanner.nextLine());
    	    while(sc.hasNext()) {
    		nomsAttributs.add(sc.next());
    		ArrayList<String> v = new ArrayList<String>();
    		valeursAttributs.add(v);
    	    }
    	    sc.close();
    			
    	    // les suivantes pour lire les exemples
    	    String v;
    	    while(scanner.hasNext()) {
    		sc = new Scanner(scanner.nextLine());
    		ArrayList<String> exemple = new ArrayList<String>();
    		int j = 0;

		// une boucle pour r√©cup√©rer les valeurs des attributs 
    		while(sc.hasNext()) {
    		    v = sc.next();
    		    if(!valeursAttributs.get(j).contains(v)) valeursAttributs.get(j).add(v);
    		    exemple.add(v);
    		    j++;
    		}
    		sc.close();
    		exemples.add(exemple);
    	    }
    	    
    	    scanner.close();

    	} catch (FileNotFoundException exception) {
    	    System.out.println("File not found");
    	}
    	
    	
        }



	
	//getters and setters 
	public ArrayList<String> getNomAttributs() {
		return nomsAttributs;
	}
	

	public void setNomAttributs(ArrayList<String> nomAttributs) {
		this.nomsAttributs = nomAttributs;
	}

	public ArrayList<ArrayList<String>> getExemples() {
		return exemples;
	}
	
    //get l'instance qui se trouve dans la ligne i
	public ArrayList<String> getExemple(int i) {
		return exemples.get(i);
	}

	public ArrayList<ArrayList<String>> getValeursAttributs() {
		return valeursAttributs;
	}

	public void setValeursAttributs(ArrayList<ArrayList<String>> valeursAttributs) {
		this.valeursAttributs = valeursAttributs;
	}
	
	
	//la mÈthode select permet de selectionner une cellule de la matrice de donnÈes
	public DataFrame select(int idAttribut, String valeur) {
	
		DataFrame df = new DataFrame();
		df.setNomAttributs(this.nomsAttributs);
		df.setValeursAttributs(valeursAttributs);
		
		for(int i=0; i<exemples.size();i++)
		{
			if(exemples.get(i).get(idAttribut).equals(valeur)) {
				df.exemples.add(exemples.get(i));
			}
		}
		return df;
	}

	@Override
	public String toString() {
		return "DataFrame [nomAttributs=" + nomsAttributs + ", exemples=" + exemples + ", valeursAttributs="
				+ valeursAttributs + "]";
	}
	
	
}
