import java.text.Normalizer;


public class Unicode_Test {
	

	public static void main(String[] args) {
		//codePointAt()
		//codePointAt_format()
		//nomalize()
		//StringViewByByte()
	}
	
	private static void StringViewByByte(){
	    String string = "한글";
	    byte[] bytes = string.getBytes();
	    for (byte b : bytes) {
	        System.out.print(String.format("0x%02X ", b));
	    }
	}
	
	private static void codePointAt_format(){
			String string = "안녕하세요";
			String a = "abcde";
			for (int i = 0; i < string.length(); i++) {
				System.out.print(i + " : ");
				System.out.print(String.format("U+%04X ", string.codePointAt(i)));
				System.out.println();
			}
			
			System.out.println();
			System.out.println("a");
			System.out.println();
			
			for (int i = 0; i < a.length(); i++) {
				System.out.print(i + " : ");
				System.out.print(String.format("U+%04X ", a.codePointAt(i)));
				System.out.println();
			}
		}
	
	private static void codePointAt(){
		String string = "안녕하세요";
		String a = "abcde";
		for (int i = 0; i < string.length(); i++) {
			System.out.print(i + " : ");
			System.out.print(string.codePointAt(i));
			System.out.println();
		}
		
		System.out.println();
		System.out.println("a");
		System.out.println();
		
		for (int i = 0; i < a.length(); i++) {
			System.out.print(i + " : ");
			System.out.print(a.codePointAt(i));
			System.out.println();
		}
	}
	
    private static void printString_Unicode(String str) {
        System.out.println(str);
        for (int i = 0; i < str.length(); i++) {
            System.out.print(String.format("U+%04X ", str.codePointAt(i)));
        }
        System.out.println();
    }
  
    private static void nomalize() {
        String hello = "한";
        printString_Unicode(hello);
        
        String nfd = Normalizer.normalize(hello, Normalizer.Form.NFD);
        printString_Unicode(nfd);
          
        String nfc = Normalizer.normalize(nfd, Normalizer.Form.NFC);
        printString_Unicode(nfc);
    }
    
}
