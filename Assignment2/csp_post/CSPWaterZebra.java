import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

public class CSPWaterZebra extends CSP{

	// Generate all the possible conditions given the input
	public static ArrayList<ArrayList<String>> generateAllCombinations(String[] houses, String[] drinks, String[] nations, String[] pets, String[] cigs){
		int num = 0;
		//Cast all the hash sets

		ArrayList<String> temp_string_array = new ArrayList<String>();
		ArrayList<ArrayList<String>> final_string_array = new ArrayList<ArrayList<String>>();
		for(String house : houses){
			for(String drink : drinks){
				for(String nation : nations){
					for(String pet : pets){
						for(String cig : cigs){

							temp_string_array.add(house);
							temp_string_array.add(drink);
							temp_string_array.add(nation);
							temp_string_array.add(pet);
							temp_string_array.add(cig);

							final_string_array.add(temp_string_array);

							temp_string_array.clear();

							System.out.println(house + " " + drink+ " " + nation + " "+ pet + " " + cig);
							num += 1;
						}
					}
				}
			}
			System.out.println();
		}

		System.out.println(num);

		return final_string_array;
	}

	public boolean isGood(Object X, Object Y, Object x, Object y){


		return false;
	}


	public static void main(String[] args){

		String[] varColours = {"blue", "red", "yellow", "green", "ivory"};

		String[] varDrink = {"coffee", "milk", "orange juice", "tea", "water"};

		String[] varNationality = {"Englishman", "Spaniard", "Norwegian", "Ukranian", "Japanese"};

		String[] varPets = {"horse", "fox", "dog", "snail", "zebra"};

		String[] varFaggs = {"Chesterfields", "Kools", "Lucky-Strike", "Old Gold", "Parliament"};

		ArrayList<ArrayList<String>> combinations = generateAllCombinations(varColours, varDrink, varNationality, varPets, varFaggs);




	}
}
