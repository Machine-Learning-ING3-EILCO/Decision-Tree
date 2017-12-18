import javax.swing.SingleSelectionModel;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DataFrame df= new DataFrame("exemples.csv");
		DecisionTree dt = new DecisionTree();
		System.out.println(df);
		
		
		
	DataFrame subdf = 	df.select(0, "soleil");
	
	System.out.println(subdf);
		
	System.out.println("L'entropy de la cible: "+dt.entropy(df, 4));
	
	System.out.println("Gain Météo :"+dt.gain(df, 4, 0));
	System.out.println("Gain Amis: "+dt.gain(df, 4, 1));
	System.out.println("Gain Vent: "+dt.gain(df, 4, 2));
	System.out.println("Gain Jour: "+dt.gain(df, 4, 3));
	
	System.out.println("tout est égal :"+dt.allEquals(df, 4));
	System.out.println("Plus fréquent:"+dt.plusFrequente(df, 4));

	}

}
