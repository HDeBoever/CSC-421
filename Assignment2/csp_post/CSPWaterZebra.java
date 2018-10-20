import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

public class CSPWaterZebra extends CSP{

	// // Generate all the possible conditions given the input
	// public static ArrayList<ArrayList<String>> generateAllCombinations(String[] house_positions, String[] house_colours, String[] drinks, String[] nations, String[] pets, String[] cigs){
	// 	int num = 0;
	// 	//Cast all the hash sets
	//
	// 	ArrayList<String> temp_string_array = new ArrayList<String>();
	// 	ArrayList<ArrayList<String>> final_string_array = new ArrayList<ArrayList<String>>();
	//
	// 	for(String position : house_positions){
	// 		for(String house : house_colours){
	// 			for(String drink : drinks){
	// 				for(String nation : nations){
	// 					for(String pet : pets){
	// 						for(String cig : cigs){
	//
	// 							if(nation.equals("englishman") && house.equals("red")){
	// 								temp_string_array.add(house);
	// 								temp_string_array.add(drink);
	// 								temp_string_array.add(nation);
	// 								temp_string_array.add(pet);
	// 								temp_string_array.add(cig);
	//
	// 								final_string_array.add(temp_string_array);
	//
	// 								temp_string_array.clear();
	//
	// 								System.out.println(position + " " + house + " " + drink+ " " + nation + " "+ pet + " " + cig);
	// 								num += 1;
	// 							}
	// 						}
	// 					}
	// 				}
	// 			}
	// 		}
	// 		System.out.println();
	// 	}
	//
	// 	System.out.println(num);
	//
	// 	return final_string_array;
	// }

	public boolean isGood(Object X, Object Y, Object x, Object y){

		System.out.print(X);
		System.out.print(Y);

	}

	// TEST YOUR STUFF!
	public static void main(String[] args){

		//ArrayList<ArrayList<String>> combinations = generateAllCombinations(varHousePositions, varHouseColours, varDrink, varNationality, varPets, varFaggs);

		//Declare your variables!
		String[] varHousePositions = {"pos1", "pos2", "pos3", "pos4", "pos5"};
		String[] varHouseColours = {"colour1", "colour2", "colour3", "colour4", "colour5"};
		String[] varDrink = {"drink1", "drink2", "drink3", "drink4", "drink5"};
		String[] varNationality = {"nationality1", "nationality2", "nationality3", "nationality4", "nationality5"};
		String[] varPet = {"pet1", "pet2", "pet3", "pet4", "pet5"};
		String[] varCig = {"cig1", "cig2", "cig3", "cig4", "cig5"};

		//Declare your domains!
		String[] domainPosition = {"1", "2", "3", "4", "5"};
		String[] domainHouseColours = {"blue", "red", "yellow", "green", "ivory"};
		String[] domainDrink = {"coffee", "milk", "orange juice", "tea", "water"};
		String[] domainNationality = {"englishman", "spaniard", "norwegian", "ukranian", "japanese"};
		String[] domainPets = {"horse", "fox", "dog", "snail", "zebra"};
		String[] domainCigs = {"chesterfields", "kools", "lucky-strike", "old old", "parliament"};


		CSPGraphColoring csp = new CSPWaterZebraColoring();

		for(Object X : varDrink)
			csp.addDomain(X, domainDrink);
		for(Object X : varNationality)
			csp.addDomain(X, domainNationality);
		for(Object X : varPet)
			csp.addDomain(X, domainPets);
		for(Object X : varCig)
			csp.addDomain(X, domainCigs);
		for(Object X : varHouseColours)
			csp.addDomain(X, domainHouseColours);

		// Add all pair arcs
		csp.addBidirectionalArc("englishman", "red");
		csp.addBidirectionalArc("spaniard", "dog");
		csp.addBidirectionalArc("green", "coffee");
		csp.addBidirectionalArc("ukranian", "tea");
		// csp.addBidirectionalArc("green", "ivory house next to")???;
		csp.addBidirectionalArc("snails", "old gold");
		csp.addBidirectionalArc("yellow", "kools");
		csp.addBidirectionalArc("3", "milk");
		csp.addBidirectionalArc("1", "Norwegian");
		csp.addBidirectionalArc("chesterfields", "fox");
		// csp.addBidirectionalArc("horse", "kools"); ?????
		csp.addBidirectionalArc("lucky strike", "orange juice");
		csp.addBidirectionalArc("japanese", "parliament");
		csp.addBidirectionalArc("norwegian", "blue");

		// // To do after we come up with our constraints
		// for(Object[] p : pairs)
		// 	csp.addBidirectionalArc(p[0], p[1]);

		Search search = new Search(csp);
		System.out.println(search.BacktrackingSearch());

	}
}
