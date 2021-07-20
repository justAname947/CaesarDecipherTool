import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CaesarDecipherTool {
	private final static String ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static void main(String[] args) throws IOException {
		//this is a poem in German that is encrypted with caesar's encryption by a shift of 10.
		//for those who might not know it, it shifts all letters in a message to encode it and to decode it you have to shift them back
		//the letters. For example ABC with a shift of 1 (right) would result BCD. Shifting 1 back would result in ABC.
		Scanner sc = new Scanner(System.in);
		System.out.print("Geben Sie bitte das Shift ein(Fuer dieses Abgabe bitte 10 eingeben): ");
		int shiftValue = sc.nextInt();
		System.out.println("");
		sc.close();
		CaesarDecipherTool x = new CaesarDecipherTool();
		//String str = x.dateiLesen();//use this only when you have the "encoded" text file in the same file path.
		String str = "wosx lkew\r\n" + 
		"\r\n" + 
		"smr rkmudo, qbel exn zvkqdo wsmr,\r\n" + 
		"nkxx gkb wosx lkew qocodjd.\r\n" + 
		"nob xkmrlkb ukw exn pbkqdo wsmr.\r\n" + 
		"\"osx lsbxlkew scd oc! ckqdo smr\".\r\n" + 
		"exn todjd?\r\n" + 
		"\r\n" + 
		"qsxq wkxmrob gsxdobcdebw fyblos.\r\n" + 
		"wosx lkew cdord pocd sw qbexn.\r\n" + 
		"ob lveordo nsococ tkrb sw wks,\r\n" + 
		"nso lsoxox pvyqox gosd roblos!\r\n" + 
		"tk, exn?\r\n" + 
		"\r\n" + 
		"nso cyxxo cmrsox, smr vsop rsxkec\r\n" + 
		"exn ckr nso lveodox kx\r\n" + 
		"exn wkvdo wsb nso lsbxox kec\r\n" + 
		"exn nkmrdo kx nox obxdocmrwkec!\r\n" + 
		"exn nkxx?\r\n" + 
		"\r\n" + 
		"dbeq lkvn osx ukvdob boqoxcmrgkvv\r\n" + 
		"nso lveodoxzbkmrd nkfyx.\r\n" + 
		"nymr sx nox jszpovuovmrox kvv\r\n" + 
		"ckcc pocd osx uvosxob, qbeoxob lkvv!\r\n" + 
		"exn cmryx\r\n" + 
		"\r\n" + 
		"gkb texs nk exn tevs ew\r\n" + 
		"exn ukw keqecd exn qsxq.\r\n" + 
		"nk vsop smr ew lkew robew\r\n" + 
		"exn ckr- exn ckr- lsx smr noxx neww?-,\r\n" + 
		"nkcc ob fyvv kozpov rsxq!";
		// translation : Count of all letters
		System.out.println("** Anzahl aller Buchstaben ist **\n\n " + (int)x.anzahlAllerBuchstabenBerechnen(str)+"\n");
		//translation : Mapping of all letters
		System.out.println("** Mapping aller Buchstaben **\n\n" + x.mapAllerBuchstabenErzeugen(str) +"\n");
		//translation : absolute/relative frequency of every letter in relationship to all other letters
		System.out.println("** Absolute/relative haeuifigkeit von jedem Buchstabe in Bezug auf alle anderen Buchstaben **\n");
		x.relativeHjedesBuchstabens(x.mapAllerBuchstabenErzeugen(str), x.anzahlAllerBuchstabenBerechnen(str));
		//here the all symbols are first filtered out and then decoded with a shift of 10. 
		System.out.println("\n**Resultat**\n\n"+x.decryptText(x.textOhneSymbole(str), shiftValue)); //shift is 10
		//closing program text.
		System.out.println("\n** Programm wird beendet... **\n");
	}
	//reads file
	public String dateiLesen() throws IOException {
		 File file = new File("./encoded.txt"); 
		  Scanner sc = new Scanner(file);
		  String result = "";
		  while(sc.hasNextLine()) {
			  result += sc.nextLine()+"\n";
		  }
		  sc.close();
		  return result;
		}

	public double anzahlAllerBuchstabenBerechnen(String str) {
		  double result = 0;
		  for(int i = 0; i<str.length();i++) {
			  if(Character.isLetter(str.charAt(i))) {
				  result++;
			  }
			  
		  }
		  return result;
	  }
	
	public Map<Character, Integer> mapAllerBuchstabenErzeugen(String str){
	    
	    str = str.toUpperCase();
	    Map<Character, Integer> mapOfCounts = new HashMap<>();
	     //loop through characters of string being analyzed
	     for(int i = 0;i<str.length();i++) {
	       //loop through all character A-Z
	       for(int j = 0;j<ALPHA.length();j++) {
	         //if character(no commas, space etc) then map.
	         if(str.charAt(i)==ALPHA.charAt(j)){
	           //check if there is already a value to that key
	          if(mapOfCounts.containsKey(str.charAt(i))) {
	            //get that value
	            int x = mapOfCounts.get(str.charAt(i));
	            //remap it by adding 1.
	            mapOfCounts.put(str.charAt(i),(x+1));
	          }else{
	            mapOfCounts.put(str.charAt(i), 1);
	          }
	         }
	       }
	     }
	     return mapOfCounts;
	  }

	public void relativeHjedesBuchstabens(Map<Character, Integer> source, double alleBuchstaben) {
		
        source.forEach((k,v) -> System.out.printf("Buchstabe = %c: absolute Haeufigkeit = %2d, relative Haeufigkeit = %.3f. // Log(base 2) = %.3f.\n", k, v, v/alleBuchstaben, Math.log(1/(v/alleBuchstaben))/Math.log(2))); 
	}
	
	//text without symbols
	public String textOhneSymbole(String str) {
		str = str.toUpperCase();
		String result = "";
		for(int i = 0; i<str.length();i++) {
			  if(str.charAt(i)==','
			  || str.charAt(i)=='"'|| str.charAt(i)=='.'
			  || str.charAt(i)=='!'|| str.charAt(i)=='?'
			  || str.charAt(i)=='-') {
				  continue;
			  }else {
				  result+=str.charAt(i);
			  }
		}
		return result;
	}

	public static String encryptText(String wordToEnc, int shift) {
		  
		  char letterInSecret;
		  int indexOfLetterInAlpha;
		  String result="";
		  wordToEnc = wordToEnc.toUpperCase();

		  for(int i = 0; i < wordToEnc.length();i++) {
			  
				 letterInSecret = wordToEnc.charAt(i);
				 indexOfLetterInAlpha = ALPHA.indexOf(letterInSecret);
				 if(indexOfLetterInAlpha+shift>25) {
					 indexOfLetterInAlpha=indexOfLetterInAlpha-25+shift-1;
					 result+=ALPHA.charAt(indexOfLetterInAlpha);
				 }else {
					 indexOfLetterInAlpha+= shift ; // shift one forward.
					 result+=ALPHA.charAt(indexOfLetterInAlpha);
				 }
		  }
		  return result;
	}
	
	public String decryptText(String secret, int shift) {
		  
		  char letterInSecret;
		  int indexOfLetterInAlpha;
		  String result="";
		  secret = secret.toUpperCase();
		  
		  for(int i = 0; i < secret.length();i++) {
			 letterInSecret = secret.charAt(i);
			 if(letterInSecret=='\r') {
				 result+="";
				 continue;
			 }else if(letterInSecret=='\n') {
				 result+="\n";
				 continue;
			 }else if(letterInSecret==' ' ) {
				 result+=' ';
				 continue;
			 }else{
				 indexOfLetterInAlpha = ALPHA.indexOf(letterInSecret);	
				 if(indexOfLetterInAlpha-shift<0) {
					 indexOfLetterInAlpha=indexOfLetterInAlpha+25-shift+1;
					 result+=ALPHA.charAt(indexOfLetterInAlpha);
				 }else{
					 indexOfLetterInAlpha-= shift ; // shift shift is inputed from the start of the program and used here .
					 result+=ALPHA.charAt(indexOfLetterInAlpha);
				 }
			 }
		  }
		  return result;
	  }
}



