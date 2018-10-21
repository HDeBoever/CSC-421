import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;
import java.lang.Math;

public class CSPWaterZebra extends CSP {


	static Set<Object> varColors = new HashSet<Object>( Arrays.asList(new String[] {"blue","green","ivory","red","yellow"}) );
	static Set<Object> varDrinks = new HashSet<Object>( Arrays.asList(new String[] {"coffee","milk","orange-juice","tea","water"}) );
	static Set<Object> varNationalities = new HashSet<Object>( Arrays.asList(new String[] {"englishman","japanese","norwegian","spaniard","ukrainian"}) );
	static Set<Object> varPets = new HashSet<Object>( Arrays.asList(new String[] {"dog","fox","horse","snail","zebra"}) );
	static Set<Object> varCigs = new HashSet<Object>( Arrays.asList(new String[] {"chesterfield","kools","lucky-strike","old-gold","parliament"}) );

	/*This what you need to implement for each problem.
	  It should return “true” if there is no violation of
	  any constraint involving variables X and Y with values x and y.

	  ie; variables X and Y: "englishman" and "red", with values "1" and "2"
	  so (if X = englishman && Y = red && x = 1 && y = 2) return false
	  since we know that englishman and red are both house 1, in this example
	  we see that englishman = house 1 and red = house 2, that violates the contraint
	*/
	public boolean isGood(Object X, Object Y, Object x, Object y) {



		//if X is not even mentioned in by the constraints, just return true
		//as nothing can be violated
		if(!C.containsKey(X))
			return true;

		//check to see if there is an arc between X and Y
		//if there isn't an arc, then no constraint, i.e. it is good
		if(!C.get(X).contains(Y))
			return true;

		//not equal constraint
		if(!x.equals(y))
			return true;

	    //1. englishman lives in the red house
		if( X.equals("englishman") && Y.equals("red") && !(Integer.parseInt((String)x) == Integer.parseInt((String)y)))
			return false;

		//2. The spaniard owns a dog
		if( X.equals("spaniard") && Y.equals("dog") && !(Integer.parseInt((String)x) == Integer.parseInt((String)y)))
			return false;

		//3. Coffee is drunk in the green house.
		if( X.equals("coffee") && Y.equals("green") && !(Integer.parseInt((String)x) == Integer.parseInt((String)y)))
			return false;


		// 4.The Ukrainian drinks tea.
		if( X.equals("ukrainian") && Y.equals("tea") && !(Integer.parseInt((String)x) == Integer.parseInt((String)y)))
			return false;

		// 5.The green house is directly to the right of the ivory house.
		// -Harder to model: subtraction to determine a unary constraint (ie green = 1? or 2? etc)

		// 6.The Old-Gold smoker owns snails.
		if( X.equals("old-gold") && Y.equals("snail") && !(Integer.parseInt((String)x) == Integer.parseInt((String)y)))
			return false;

		// 7.Kools are being smoked in the yellow house.
		if( X.equals("kools") && Y.equals("yellow") && !(Integer.parseInt((String)x) == Integer.parseInt((String)y)))
			return false;

		// 8.Milk is drunk in the middle house.
		if( X.equals("milk") && !(Integer.parseInt((String)x) == 3) )
			return false;

		// 9.The Norwegian lives in the first house on the left.
		if( X.equals("norwegian") && !(Integer.parseInt((String)x) == 1))
			return false;

		// 10.The Chesterfield smoker lives next to the fox owner.
		// -Harder to model: chesterfield = leftOf houseOf( fox owner ) | rightOf houseOf(foxOwner)
		if( X.equals("chesterfield") && Y.equals("fox") && !( Math.abs(Integer.parseInt((String)x) - Integer.parseInt((String)y)) == 1 ) )
			return false;

		// 11.Kools are smoked in the house next to the house where the horse is kept.
		// -Harder to model: kools = leftOf( houseOf(horse) ) | rightOf( houseOf(horse) )
		if( X.equals("kools") && Y.equals("house") && !( Math.abs(Integer.parseInt((String)x) - Integer.parseInt((String)y)) == 1 ) )
			return false;

		// 12.The Lucky-Strike smoker drinks orange juice.
		if( X.equals("lucky-strike") && Y.equals("orange-juice") && !(Integer.parseInt((String)x) == Integer.parseInt((String)y)))
			return false;

		// 13.The Japanese smokes Parliament.
		if( X.equals("japanese") && Y.equals("parliament") && !(Integer.parseInt((String)x) == Integer.parseInt((String)y)))
			return false;

		// 14.The Norwegian lives next to the blue house.
		// -Harder to model: norwegian = leftOf( houseOf(blue) ) | rightOf( houseOf(blue) )
		if( X.equals("norwegian") && Y.equals("blue") && !( Math.abs(Integer.parseInt((String)x) - Integer.parseInt((String)y)) == 1 ) )
			return false;

		//UNIQUENESS
		if( varColors.contains(X) && varColors.contains(Y) && !(X.equals(Y)) && (Integer.parseInt((String)x) == Integer.parseInt((String)y)) )
			return false;

		return false;
	}

	public static void main(String[] args) throws Exception {

		/*

		***Arc DONE***
		1.The Englishman lives in the red house.
		-addBidirectionalArc

		***DONE***
		2.The Spaniard owns a dog.
		-addBidirectionalArc

		***DONE***
		3.Coffee is drunk in the green house.
		-addBidirectionalArc

		***DONE***
		4.The Ukrainian drinks tea.
		-addBidirectionalArc



		5.The green house is directly to the right of the ivory house.
		-Harder to model: subtraction to determine a unary constraint (ie green = 1? or 2? etc)



		***DONE***
		6.The Old-Gold smoker owns snails.
		-addBidirectionalArc

		***DONE***
		7.Kools are being smoked in the yellow house.
		-addBidirectionalArc

		***DONE***
		8.Milk is drunk in the middle house.
		-Unary: we know middle house is 3

		***DONE***
		9.The Norwegian lives in the first house on the left.
		-Unary: we know first house on left is 1, ie norwegian = 1



		10.The Chesterfield smoker lives next to the fox owner.
		-Harder to model: chesterfield = leftOf houseOf( fox owner ) | rightOf houseOf(foxOwner)

		11.Kools are smoked in the house next to the house where the horse is kept.
		-Harder to model: kools = leftOf( houseOf(horse) ) | rightOf( houseOf(horse) )



		***DONE***
		12.The Lucky-Strike smoker drinks orange juice.
		-addBidirectionalArc

		***DONE***
		13.The Japanese smokes Parliament.
		-addBidirectionalArc

		14.The Norwegian lives next to the blue house.
		-Harder to model: norwegian = leftOf( houseOf(blue) ) | rightOf( houseOf(blue) )
		*/

		CSPWaterZebra csp = new CSPWaterZebra();
		String[] domain = {"1","2","3","4","5"};

	  /*The variables are "red, green, blue, old gold, norwegian etc"
		and they are assigned anything in the domain, the domain is 1-5 (ie house 1 to 5) for any var


	  //------------UNARY-------------
		Unary constraints - "just remove values from domains"
 		ie milk = 3, norwegian = 1
 		ie; domain of the "milk" variables is just 3
 		ie; domain of the "norwegian" variable is just 1*/
		for(Object X : varColors)
			csp.addDomain(X, domain);

		//Drinks (with unary constraints for domain)
		for(Object X : varDrinks) {
			if( ((String) X).equals("milk") ) {
				String[] milkDomain = {"3"};
				csp.addDomain(X, milkDomain);
			}
			else {
				String[] notMilkDomain = {"1","2","4","5"};
				csp.addDomain(X, notMilkDomain);
			}
		}

		//Nationalities (with unary constraints)
		for(Object X : varNationalities) {
			if( ((String) X).equals("norwegian") ) {
				String[] norwegianDomain = {"1"};
				csp.addDomain(X, norwegianDomain);
			}
			else {
				String[] notNorwegianDomain = {"2","3","4","5"};
				csp.addDomain(X, notNorwegianDomain);
			}
		}


		//-----------BI DIRECTIONAL--------------
		for(Object X : varPets)
			csp.addDomain(X, domain);

		for(Object X : varCigs)
			csp.addDomain(X, domain);

		String[][] pairs = { {"englishman","red"}, {"spaniard","dog"}, {"coffee","green"}, {"ukrainian","tea"},
	 						 {"old-gold","snail"}, {"kools","yellow"}, {"lucky-strike","orange-juice"}, {"japanese","parliament"}};

		for(Object[] p : pairs)
			csp.addBidirectionalArc(p[0], p[1]);

		//-------UNIQUENESS-------
		//Colors
		for(Object X : varColors) {
			for(Object Y : varColors) {
				csp.addBidirectionalArc(X, Y);
			}
		}
	   //Drinks
	   for(Object X : varDrinks) {
	       for(Object Y : varDrinks) {
			   csp.addBidirectionalArc(X, Y);
		   }
	    }
		//Nationalities
		for(Object X : varNationalities) {
 	       for(Object Y : varNationalities) {
 			   csp.addBidirectionalArc(X, Y);
 		   }
 	   }
	   //Pets
	   for(Object X : varPets) {
	       for(Object Y : varPets) {
			   csp.addBidirectionalArc(X, Y);
		   }
	   }
	   //Cigs
	   for(Object X : varCigs) {
	       for(Object Y : varCigs) {
			   csp.addBidirectionalArc(X, Y);
		   }
	   }

		Search search = new Search(csp);
		System.out.println(search.BacktrackingSearch());
	}
}
