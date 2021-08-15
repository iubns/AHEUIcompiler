package AHEUIcompiler;

import java.text.Normalizer;


/*//////////////////////////////////////////////////////////////////////////////
 * 
 *  ���� �� �ڵ�� ������ ��� ���α׷����� �������� �ʽ��ϴ�.
 *  �������� �ʴ� ��ɵ��� ������ �����ϴ�.
 *  
 *  
 *  �� - ���� (���� �� ������� ����)
 *  �� ������ �� ����
 *  ��������� �Ѱ��� ���ڰ� �������� ����
 *  0������ ������
 *  
 *  ���� ��ġ�� 0���� �����մϴ�.
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
    	
    	System.out.println("����� �ϼ���. �������� �ʴ� ����� ���� ���� �����ڰ� ������ �ϱ� ����. ��������� �Ұ� �Ѱ��� ���� �ƴ�");
		
		
		 //�Է� �ޱ�
    	while(true){
    		
    		 String inputline = input.nextLine();
    		 
    		 inputlinelist.add(inputline);

     		if(Max <inputline.length()){
        		Max = inputline.length();	
    		}
     		// ���͸�  ������ �� 
    		if("".equals(inputline)) { 
               break; 
            } 
    	}

    	code = new String[inputlinelist.size()][Max];
    	
    	
    	//��� ����
    	for(int i = 0; i<inputlinelist.size(); i++){
    		String InputLine = inputlinelist.get(i);
    		
    		for(int ii = 0 ; ii<InputLine.length(); ii++){
    			code[i][ii] = InputLine.substring(ii , ii+1);
    		}
    		
    	}
    	
    	
    	//loop
    	while(true){
        	//System.out.println("x :"+x +"// y :"+y); //�������� ��ǥ
        	//System.out.println(code[y][x]); //�������� �ڵ�
        	nextmove = codecheck(code[y][x]);
        	
			
			if(nextmove == 5){
				break;
			}else if(nextmove == 4){ // �Ӹ��� �۵�
				System.out.println("�Ӹ��� ����� �������� �ʽ��ϴ�.");
				Error();
			}else if(nextmove == 3){ // �� ���� �۵�
				System.out.println("�Ѹ��� ����� �������� �ʽ��ϴ�.");
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

    	case "1102": //��
    		division();
    		break;
    	case "1103": //��
    		add();
    		break;
    	case "1104": //��
    		multiplication();
    		break;
    	case "1105": //��
    		remainder();
    		break;
    	case "1106": //��
    		Print();
    		break;
    	case "1107": //��
    		input();
    		break;
    	case "1108": //��
    		copy();
    		break;
		case "1109": // /t
			System.out.println("������ ����� �������� �ʽ��ϴ�.");
			Error();
			break;
    	case "110B": // ��
    		break;
		case "110E": // /��
			System.out.println("������ ����� �������� �ʽ��ϴ�.");
			Error();
			break;
    	case "1110": //��
    		subtraction();
    		break;
    	case "1111": //��
    		change();
    		break;
    	case "1112": //��
    		return 5;
    	default:
    		break;	
	
	}
    	
    	switch(SecondSound){
    	
	    	case "1161": //��
	    		return 1;
	    	case "1163": //��
	    		return 2;
	    	case "1165": //��
	    		return -1;
	    	case "1167": //��
	    		return -2;
	    	case "1169": //��
	    		return -10;
	    	case "116D": //��
	    		return -20;
	    	case "116E": //��
	    		return 10;
	    	case "1172": //��
	    		return 20;
	    	case "1173": //��
	    		return 3;
	    	case "1175": //��
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
			case "11BC": // /��
				System.out.print(values.get(values.size()-1));
				values.remove(values.size()-1);
			break;
			case "11C2": // /��
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
			case "11BC": // /��
				System.out.println("���ڸ� �Է��ϼ���.");
				Long inputline = new Long(input.nextLine());
				values.add(inputline);
			break;
			case "11C2": // /��
				System.out.println("���ڸ� �Է��ϼ���.");
				String inputstringline = new String(input.nextLine());
				values.add((long)Integer.parseInt((String.format("%04X", inputstringline.codePointAt(0))) , 16));
				break;
			case "11A8": // /��
			case "11AB": // /��
			case "11BA": // /��
				values.add((long) 2);
				break;
			case "11AE": // /��
			case "11BD": // /��
			case "11BF": // /��
				values.add((long) 3);
	    		break;	
			case "11B7": // /��
			case "11B8": // /��
			case "11BE": // /��
			case "11C0": // /��
			case "11C1": // /��
			case "11A9": // /��
			case "11AA": // /��
			case "11BB": // /��
				values.add((long) 4);
	    		break;	
			case "11AF": // /��
			case "11AC": // /��
			case "11AD": // /��
				values.add((long) 5);
	    		break;	
			case "11B9": // /��
				values.add((long) 6);
	    		break;	
			case "11B0": // /��
			case "11B3": // /��
				values.add((long) 7);
	    		break;	
			case "11B6": // /��
				values.add((long) 8);
	    		break;	
			case "11B1": // /��
			case "11B2": // /��
			case "11B4": // /��
			case "11B5": // /��
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
    		System.out.println("0���� ���� �� �����ϴ�.");Error();
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
		System.out.println("������ �ϱ� ���ؼ��� ��������� 2�� �̻��� ���ڰ� �־�� �մϴ�.");Error();
	}
    
    private static void Error() {
		try {
			throw new Error("��� ��ġ : x "+x+" y "+y);
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
	                // �����ڵ� ���� ���̰� 4�ڸ��� �ȵȴٸ� ��ȯ ����
	                decodeStr ="\\u" + unicodeStr.substring(i + 2);
	                nextBegin = unicodeStr.length();
	       
	            } else {
	                try {
	                    ch = (char)Integer.parseInt(unicodeStr.substring(i + 2, i + 6), 16);
	                    decodeStr = String.valueOf(ch);
	                    nextBegin = i + 6;
	     
	                } catch (NumberFormatException e) {
	                    // �����ڵ� ���� 16���� ������ �ƴ϶�� ��ȯ ����
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