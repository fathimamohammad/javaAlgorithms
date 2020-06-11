package ppc.code;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Problem3 {
     public static Pattern pattern = Pattern.compile("(\\d)[\\D]*[\\?][\\d]*[\\D]*[\\?][\\d]*[\\D]*[\\?](\\d)");//("((\\d)[^\\d\\?]*[\\?\\D]{3}[^\\d\\?]*(\\d))");
     public static void main(String[] args) {
    	 	//System.out.println("FIND="+check("3aaaf?S??7"));
    	 	String strs[]= {"",//true
    	 			"arrb6???4xxbl5???eee5",//true
    	 			"acc?7??sss?3rr1??????5",//true
    	 			"5??aaaaaaaaaaaaaaaaaaa?5?5",//false
    	 			"9???1???9???1???9",//true
    	 			"aa6?9",//true
    	 			
    	 			"Kjishfkjsnf????6??4s"};//false
 

    	 for (String str:strs) {
    		 System.out.println(str+"=>"+check(str));
    	 }

     }
	
	 
	 public static boolean check(String s) {
		 Pattern p1 = Pattern.compile("(\\d)([\\D]*[\\?]+[\\D]*)(\\d)");
		 boolean match = true;
		 Matcher m1 = p1.matcher(s);
		 while(m1.find()) {
			 if (Integer.parseInt(m1.group(1)) + Integer.parseInt(m1.group(3)) == 10) {
			//	 System.out.println(m1.group(2));
				 if (!Pattern.matches("[\\D]*[\\?][\\d]*[\\D]*[\\?][\\d]*[\\D]*[\\?][\\d]*[\\D]*",m1.group(2))) {
					 return false;
				 }
			 }
			 m1.region(m1.end() - 1, m1.regionEnd()); 
		 }
		 return match;
		
		  }
}
