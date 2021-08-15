package AHEUIcompiler;

import java.text.Normalizer;


/*//////////////////////////////////////////////////////////////////////////////
 * 
 *  주의 본 코드는 아희의 모든 프로그래밍을 지원하지 않습니다.
 *  지원되지 않는 기능들은 다음과 같습니다.
 *  
 *  
 *  ㅅ - 묶움 (제어 및 저장공간 선택)
 *  ㅡ 모음과 ㅣ 모음
 *  저장공간에 한개의 숫자가 있을때의 연산
 *  0으로의 나눗셈
 *  
 *  오류 위치는 0부터 시작합니다.
 */////////////////////////////////////////////////////////////////////////////


import java.util.ArrayList;
import java.util.Scanner;

public class Main{

	static String FirstSound;
	static String SecondSound;
	static String ThirdSound;
	
	static int x = 0;
	static int y = 0;
	static int nextmove;
	
	static int Max = 0;
	static ArrayList<Long> values = new ArrayList<Long>();
	static ArrayList<String> inputlinelist = new ArrayList<String>();
	
	static String[][] code;
	

	static Scanner input = new Scanner(System.in);
	
    public static void main(String[] args){
    	
    	System.out.println("명령을 하세요. 지원하지 않는 기능이 많은 것은 제작자가 귀찮아 하기 때문. 기술적으로 불가 한것이 전혀 아님");
		
		
		 //입력 받기
    	while(true){
    		
    		 String inputline = input.nextLine();
    		 
    		 inputlinelist.add(inputline);

     		if(Max <inputline.length()){
        		Max = inputline.length();	
    		}
     		// 엔터를  만나면 끝 
    		if("".equals(inputline)) { 
               break; 
            } 
    	}

    	code = new String[inputlinelist.size()][Max];
    	
    	
    	//명령 분해
    	for(int i = 0; i<inputlinelist.size(); i++){
    		String InputLine = inputlinelist.get(i);
    		
    		for(int ii = 0 ; ii<InputLine.length(); ii++){
    			code[i][ii] = InputLine.substring(ii , ii+1);
    		}
    		
    	}
    	
    	
    	//loop
    	while(true){
        	//System.out.println("x :"+x +"// y :"+y); //실행중인 좌표
        	//System.out.println(code[y][x]); //실행중인 코드
        	nextmove = codecheck(code[y][x]);
        	
			
			if(nextmove == 5){
				break;
			}else if(nextmove == 4){ // ㅣ모음 작동
				System.out.println("ㅣ모음 기능은 지원하지 않습니다.");
				Error();
			}else if(nextmove == 3){ // ㅡ 모음 작동
				System.out.println("ㅡ모음 기능은 지원하지 않습니다.");
				Error();
			}else{
	        	x += nextmove % 10 ;
	        	y += (nextmove-(nextmove % 10))/10;
	        	//System.out.println("move - x : "+x+" y : "+y);
	        	if(Max <= x){
	        		x -= Max;
	        	}else if(inputlinelist.size()-1 <= y){
	        		y -= inputlinelist.size()-1;
	        	}
        	}
			
			
		}
    	
    	
    }
    
    static int codecheck(String code){
    	
    	
    	printString_Unicode(code);

    	switch(FirstSound){

    	case "1102": //ㄴ
    		division();
    		break;
    	case "1103": //ㄷ
    		add();
    		break;
    	case "1104": //ㄸ
    		multiplication();
    		break;
    	case "1105": //ㄹ
    		remainder();
    		break;
    	case "1106": //ㅁ
    		Print();
    		break;
    	case "1107": //ㅂ
    		input();
    		break;
    	case "1108": //ㅃ
    		copy();
    		break;
		case "1109": // /t
			System.out.println("ㅅ자음 기능은 지원하지 않습니다.");
			Error();
			break;
    	case "110B": // ㅇ
    		break;
		case "110E": // /ㅊ
			System.out.println("ㅊ자음 기능은 지원하지 않습니다.");
			Error();
			break;
    	case "1110": //ㅌ
    		subtraction();
    		break;
    	case "1111": //ㅍ
    		change();
    		break;
    	case "1112": //ㅎ
    		return 5;
    	default:
    		break;	
	
	}
    	
    	switch(SecondSound){
    	
	    	case "1161": //ㅏ
	    		return 1;
	    	case "1163": //ㅑ
	    		return 2;
	    	case "1165": //ㅓ
	    		return -1;
	    	case "1167": //ㅕ
	    		return -2;
	    	case "1169": //ㅗ
	    		return -10;
	    	case "116D": //ㅛ
	    		return -20;
	    	case "116E": //ㅜ
	    		return 10;
	    	case "1172": //ㅠ
	    		return 20;
	    	case "1173": //ㅡ
	    		return 3;
	    	case "1175": //ㅣ
	    		return 4;
	    	default:
	    		return 1;	
    	
    	}
    	
    	
    }
    private static void change() {
    	values.add(values.get(values.size()-2));
    	values.remove(values.get(values.size()-3));
	}

	private static void copy() {
    	values.add(values.get(values.size()-1));
	}

	private static void Print() {
		switch(ThirdSound){
			case "11BC": // /ㅇ
				System.out.print(values.get(values.size()-1));
				values.remove(values.size()-1);
			break;
			case "11C2": // /ㅎ
				long StaringUnicode = values.get(values.size()-1);
			try {
				if(StaringUnicode >1000){
					System.out.print(decode("\\u"+Long.toHexString(StaringUnicode)));
				}else{
					if(StaringUnicode == 10){
						System.out.print("\n");
					}else{
					System.out.print(decode("\\u00"+Long.toHexString(StaringUnicode)));
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
				values.remove(values.size()-1);
				break;
	    	default:
	    		break;	
		}
	}
    
    private static void input() {
		switch(ThirdSound){
			case "11BC": // /ㅇ
				System.out.println("숫자를 입력하세요.");
				Long inputline = new Long(input.nextLine());
				values.add(inputline);
			break;
			case "11C2": // /ㅎ
				System.out.println("글자를 입력하세요.");
				String inputstringline = new String(input.nextLine());
				values.add((long)Integer.parseInt((String.format("%04X", inputstringline.codePointAt(0))) , 16));
				break;
			case "11A8": // /ㄱ
			case "11AB": // /ㄴ
			case "11BA": // /ㅅ
				values.add((long) 2);
				break;
			case "11AE": // /ㄷ
			case "11BD": // /ㅈ
			case "11BF": // /ㅋ
				values.add((long) 3);
	    		break;	
			case "11B7": // /ㅁ
			case "11B8": // /ㅂ
			case "11BE": // /ㅊ
			case "11C0": // /ㅌ
			case "11C1": // /ㅍ
			case "11A9": // /ㄲ
			case "11AA": // /ㄳ
			case "11BB": // /ㅆ
				values.add((long) 4);
	    		break;	
			case "11AF": // /ㄹ
			case "11AC": // /ㄵ
			case "11AD": // /ㄶ
				values.add((long) 5);
	    		break;	
			case "11B9": // /ㅄ
				values.add((long) 6);
	    		break;	
			case "11B0": // /ㄺ
			case "11B3": // /ㄽ
				values.add((long) 7);
	    		break;	
			case "11B6": // /ㅀ
				values.add((long) 8);
	    		break;	
			case "11B1": // /ㄻ
			case "11B2": // /ㄼ
			case "11B4": // /ㄾ
			case "11B5": // /ㄿ
				values.add((long) 9);
	    		break;	
	    	default:
	    		values.add((long) 0);
	    		break;	
		}
	}

	private static void printString_Unicode(String str) {
    	
        String nfds = Normalizer.normalize(str, Normalizer.Form.NFD);
        FirstSound = String.format("%04X", nfds.codePointAt(0));
        SecondSound = String.format("%04X", nfds.codePointAt(1));
        if(nfds.length() == 3){
            ThirdSound = String.format("%04X", nfds.codePointAt(2));         	
        }

    }
    
    private static void add(){
    	if(values.size() == 1){OneValue();return;}
    	values.add(values.get(values.size()-1)+values.get(values.size()-2));
    	values.remove(values.size()-2);
    	values.remove(values.size()-2);
    }
    

	private static void multiplication(){
		if(values.size() == 1){OneValue();return;}
    	values.add(values.get(values.size()-1)*values.get(values.size()-2));
    	values.remove(values.size()-2);
    	values.remove(values.size()-2);
    }
    
    private static void subtraction(){
    	if(values.size() == 1){OneValue();return;}
    	values.add(values.get(values.size()-2)-values.get(values.size()-1));
    	values.remove(values.size()-2);
    	values.remove(values.size()-2);
    }
  
    private static void division(){
    	if(values.size() == 1){OneValue();return;}
    	if(values.get(values.size()-2) == 0){
    		System.out.println("0으로 나눌 수 없습니다.");Error();
    		return;
    	}
    	values.add(values.get(values.size()-1)/values.get(values.size()-2));
    	values.remove(values.size()-2);
    	values.remove(values.size()-2);
    }
    
    private static void remainder(){
    	if(values.size() == 1){OneValue();return;}
    	values.add(values.get(values.size()-1)%values.get(values.size()-2));
    	values.remove(values.size()-2);
    	values.remove(values.size()-2);
    }
    
    private static void OneValue() {
		System.out.println("연산을 하기 위해서는 저장공간에 2개 이상의 숫자가 있어야 합니다.");Error();
	}
    
    private static void Error() {
		try {
			throw new Error("요류 위치 : x "+x+" y "+y);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
	public static String decode(String unicodeStr) throws Exception {
	    StringBuffer str = new StringBuffer();
	    int i = -1;
	    char ch = 0;
	    String decodeStr ="";   
	    int nextBegin = -1;
	  
	        while ((i = unicodeStr.indexOf("\\u")) > -1) {
	   
	            if (i + 6 > unicodeStr.length()) {
	                // 유니코드 값이 길이가 4자리가 안된다면 변환 무시
	                decodeStr ="\\u" + unicodeStr.substring(i + 2);
	                nextBegin = unicodeStr.length();
	       
	            } else {
	                try {
	                    ch = (char)Integer.parseInt(unicodeStr.substring(i + 2, i + 6), 16);
	                    decodeStr = String.valueOf(ch);
	                    nextBegin = i + 6;
	     
	                } catch (NumberFormatException e) {
	                    // 유니코드 값이 16진수 포맷이 아니라면 변환 무시
	                    decodeStr = "\\u" + unicodeStr.substring(i + 2, i + 6);
	                    nextBegin = i + 6;
	                }
	            }
	        str.append(unicodeStr.substring(0, i));
	        str.append(decodeStr);
	        unicodeStr = unicodeStr.substring(nextBegin);
	    }
	    str.append(unicodeStr);
	    return str.toString();
	}
    
}