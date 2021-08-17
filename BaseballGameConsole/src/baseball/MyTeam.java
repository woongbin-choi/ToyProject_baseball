package baseball;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;

/*
  	[클래스 설명]
  	MyTeam 클래스는 선수를 사고팔수있는 자본금과 나의 팀을 생성할수 있는 메서드를 만들어둔 클래스이다.
 	지금은 미완성 단계이다. 앞으로 늘려가고싶은 기능은 나의 팀을 만들고 그 팀을 택스트 파일에 저장해뒀다가 원할때에 불러와 사용할수있게 해주고싶다
 	즉 팀을 5개씩 이름을 만들어 저장해둬도 마음껏 불러오고 싶다는것이다. 그리고 추가적으로 로그인 기능도 있으면 제미있을거 같다.
 */
public class MyTeam {
	
	private static String mWallet = "나의자본.txt"; 
	private static String wallet;
	
	public static void main(String[] args) {
		
	}
	/*
	 	[make_team()]
	 	이 메서드를 통해 내가 원하는 이름으로 팀을 생성할수있다
	 	
	*/
	public static void make_team(String name) {
		try (BufferedWriter out = new BufferedWriter(new FileWriter(
	            name+".txt", Charset.forName("UTF-8"),true),2048)){   
			
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
	}
	/*
	 	[holdMoney()]
	 	holdMoney는 경매장에서 선수를 사고 팔때에 쓸수있는 돈을 저장해두는 택스트 파일을 불러와 그 값을 인트로 리턴하여
	 	선수를 살때는 돈이 차감되고 팔때에는 더해질수있게 해주는 기능을 가진 메소드이다.
	 */
	public static int holdMoney(){
		String calc = "";
		int num = 0;
		try (BufferedReader in = new BufferedReader(new FileReader(
						mWallet, Charset.forName("UTF-8")),2048)){
			String len;
			while((len = in.readLine()) != null) {
				calc = len;
			}
		// 1. readLine은 텍스트 파일을 한줄씩 읽어오는 기능이있으며 읽어올 값이 없을때에는 null을 리턴하여준다 (나의자본.txt에는 읽어올 값이 한줄밖에 없어서 와일문을 돌릴필요는 굳이 없다 나중에 수정해두자) 
			num = Integer.parseInt(calc);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return num;
	}
	/*
	 	[walletPlus()] , [walletMinus()]
	 	두개의 매소드는 비슷한 역할이며 지갑에 돈을 더해서주냐 빼서주냐의 차이이기 떄문에 한번에 적어둔다.
	 	walletPlus는 holdMoney()에서 리턴받은 값을 기준으로 선수를 팔때 내 지갑에 돈을 더해주는 역할을 하는 메서드이다
	 	TxTManager클래스에 addPlayer_on_txt 메소드에서 선수를 다른 텍스트로 전해줄때에 그 선수의 가격이 스태틱변수 money를 통해 나가도록 해주었으며
	 	walletPlus()의 매개변수 salePrice에 money값을 받아서 선수를 팔때는 그 값을 더해주고 살때는 값을 빼서 내 지갑에 리턴해준다
	 */
	public static void walletPlus(int myMoney, int salePrice){
	
		try (BufferedWriter out = new BufferedWriter(new FileWriter(
				mWallet, Charset.forName("UTF-8")),2048)){
			
			myMoney = myMoney + salePrice; 
			out.write(myMoney+"\n");
			System.out.printf("판매금액 : %d 잔액 : %d",salePrice, myMoney);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void walletMinus(int myMoney, int Price){
		
		try (BufferedWriter out = new BufferedWriter(new FileWriter(
				mWallet, Charset.forName("UTF-8")),2048)){
			
			myMoney = myMoney - Price; 
			System.out.printf("차감금액 : %d 잔액 : %d",Price, myMoney);
			out.write(myMoney+"\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

}
