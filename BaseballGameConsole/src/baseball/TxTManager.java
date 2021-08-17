package baseball;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
/*
  	[클래스 설명]
  	택스트의 정보를 이용하는 메서드들을 모아놓은 클래스이다
 */
public class TxTManager {
	public static String aHouse = "경매장.txt";
	public static String mTiem = "나의팀.txt";
	public static String pNum = "선수번호.txt";
	public static int money;

	public static void main(String[] args) {
		
	}
	/*
		[addPlayer_on_txt()]
		path => 선수 => adress
		내가 원하는 선수의 정보를 불러와 원하는 파일에 추가해줄수 있는 역할을 한다.
	*/
	public static void addPlayer_on_txt(String adress,String path, int playerNum) {
		// 1. 매개변수 path는 내가 불러올 택스트파일의 주소이고 adress는 불러와서 꺼낸 값을 전달받을 택스트파일의 주소이며 playerNum은 원하는 선수의 고유번호를 입력받을 매개변수이다
		int len = 0;
		int count = 0;
		ArrayList<String> check_size = new ArrayList<>();

		try (BufferedReader in = new BufferedReader(new FileReader(
				path, Charset.forName("UTF-8")),2048);
					BufferedWriter out = new BufferedWriter(new FileWriter(
						adress, Charset.forName("UTF-8"),true),2048)
		){
		// 2.in에는 내가 불러들일 텍스트 파일의 주소를담고 out에는 다시 내보내줄 텍스트 파일의 주소를 담는다
			String check;
			while((check = in.readLine()) != null) {
				check_size.add(check);
				count++;
			}
		// readLine()은 택스트 파일에 한줄씩 읽어오다가 값이 없을때에 null을 리턴해줌으로 null값이 나올떄까지 와일문을 반복하여 안의 내용들을 꺼내준다	
		// 3. in에 담겨있는 내용을 한줄단위로 가져와 check_size에 하나씩 추가해준다 동시에 count를 체크하는 이유는 스트링 타입 2중 배열을 선언할 예정인데 선언할때에
		//	  정확한 사이즈가 입력되어야 하기 때문이다.
			String[][] list = new String[count][];
			for(String checks : check_size) {
				list[len++] = checks.split("/");
			}
		// 4. 이곳에서 스트링타입의 이중배열을 선언하고 스플릿으로 [0...]배열안의 [0][1][2][3][4]배열에 0고유번호,1이름2,직업,3스탯,4돈.. 이런식으로 들어갈수있게 만들어서
		// 	  그 선수의 정보들을 분리해서 담았다
			for(int i = 0; i < list.length; ++i) {
				if(playerNum == Integer.parseInt(list[i][0])) {
					money = Integer.parseInt(list[i][4]);
					out.write(check_size.get(i)+"\n");
					System.out.println("[결과] : "+check_size.get(i));
				}
			}		
		// 5. 분리해서 담은 2중배열의 [0...]배열안의 [0]번째 배열에는 고유번호가 들어가게 해두었기때문에 [i][0]으로 반복문을 돌려서
		//	  매개변수playerNum으로 받은 번호와 같다면 번호가 같을때의 값을 out에 있는 주소로 선수의 정보를 넘겨준다. 이중 배열안의 값을 그대로 옴겨도되지만 그렇게보낸다면 스플릿 "/" 기준으로 잘라서 /가
		//    사라지게됨으로 Stirng.join으로 다시 /기준으로 이어붙일수 있지만 그렇게 하지않고 3번 설명에 있는 check_size에 담아뒀던 정보를 가져와서 넣었다
		//    왜냐하면 check_size에 담겨져있는 순서와 [][]list에 담겨있는 순서가 같기 때문에 폴문안에서 체크될때에는 같은 값이 나오기때문에다.
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}
	/*
		[del_list()]
		내가 테스트 파일에서 삭제하고 싶은 선수의 정보를 제거하는 역할을 한다
		문제점:
		del_list 메소드 안에서 한번에 삭제를 하고 싶었지만 텍스트 파일안의 내용을 초기화하고 새 정보를 담아주기위해 false를 하는순간 모든 내용이 지워지기때문에 부득이하게
		메소드를 2개로 나눠서 한쪽에서는 원하는 선수를 제거한 배열을 전해주고 다른 메소드에서 파일안의 내용을 초기화하면서 새로받은(원하는 선수가 삭제된)내용을 담은 배열의 내용을 다시 파일에 넣어줄것이다.
	*/
	public static String[] del_list(String path, int playerNum) { 
		int len = 0;
		int count = 0;
		String[] delete;
		// 1. path에 불러올 파일의 주소를받고 playerNum에 삭제하고 싶은 선수의 고유번호를 받는다 고유번호는 ex)35/name/역할/stats/money 맨앞의 35번의 숫자가 고유번호이다
		ArrayList<String> check_size = new ArrayList<>();
		try (BufferedReader in = new BufferedReader(new FileReader(
				path, Charset.forName("UTF-8")),2048);
				){

			String check;
			while((check = in.readLine()) != null) {
				check_size.add(check);
				count++;
			}
		// 2. 이곳에서 path로 전해받은 주소의 택스트파일에 들어있는 선수목록을 check_size안에 넣어준다 카운트를 하는 이유는 list라는 이중배열의 크기값을 정해줘야하기때문이다.
			String[][] list = new String[count][];
			for(String checks : check_size) {
				list[len++] = checks.split("/");
			}
		// 3. 위에 addPlayer_on_txt에서도 설명하였지만 이곳에서 스트링타입의 이중배열을 선언하고 스플릿으로 
		//	[0...]배열안의 [0][1][2][3][4]배열에 0고유번호,1이름2,직업,3스탯,4돈.. 이런식으로 들어갈수있게 만들어서 그 선수의 정보들을 분리해서 담았다
			delete = new String[count];
			for(int i = 0; i < list.length; ++i) {			
				if(playerNum != Integer.parseInt(list[i][0])) {
					delete[i] = check_size.get(i)+"\n";
				}else {
					delete[i] = "";
				}
			}	
			return delete;
		// 4. delete라는 배열안에 선수들의 정보를 담아줄건데 이곳에서 삭제하고싶은 선수를 공백으로 만들어 배열에 담는다
		// 	  반복문을 설명하자면 삭제하고싶은 선수의 고유번호와 playerNum의 값이 일치하지 않을때에 delete배열안에 선수들의 정보를 넣어주고 
		//    값이 일치할때에는 공백을 넣어둬서 리스트안에서 정보를 삭제하고 공백만을 넣어둔후 delete배열을 리턴해준다
		} catch (IOException e) {
			e.printStackTrace();
		}	
		return null;
	}
	/*
	 	[deletePlayer_on_txt()]
	 	 del_list() 에서 리턴받은 원하는 선수가 삭제된 스트링 배열의 값을 다시 원하는 택스트 파일에 넣어주는 역할을 한다
	 	 ex) 경매장에서 선수 구매 => 내 팀에 추가 => 경매장에서 구매된 선수 삭제
	 	 갱매장에서 구매된 선수를 삭제후에 택스트 파일에서 몇번쨰에 있는 원하는 선수만 골라서 삭제할수 없기때문에 다시 꺼내서 삭제하고 택스트 파일에 넣어주는 작업을 하는것이다.
	 
	 */
	public static void deletePlayer_on_txt(String path,String[] delete) {
		try(BufferedWriter out = new BufferedWriter(new FileWriter(
				path, Charset.forName("UTF-8"),false),2048)){
			// 1. path는 택스트로 내보낼 주소를받고 delete는 삭재하려는 선수의 정보가 삭제되고 그 자리에 공백이 들어온 배열을 리턴받는 역할의 매개변수다
			for(int i = 0; i < delete.length; ++i) {			
				if(delete[i] != " ") {
					out.write(delete[i]);
				}
			}		
			// 2. 리턴받아온 delete배열의 랭스만큼 반복문을 돌리면서 delete안의 값이 공백이 아닐때에 선수들을 경매장또는,나의팀...등 path에 주소를 넣어준 파일에 넣어주는것이다
			//    " " != 공백이 아닐때 이기때문에 공백은 자동으로 거른다
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*
	 	[print_txt()]
	 	택스트 파일안에 무슨 내용이 있는지 매개변수로 주소값만 받고 출력해주는 메서드이다
	 */
	public static String print_txt(String path) {
		String check = new String();
		try (BufferedReader in = new BufferedReader(new FileReader(
				path, Charset.forName("UTF-8")),2048)){
			String line;
			while((line = in.readLine()) != null){
				check += line+"\n";
			}
		// 1. readLine()은 택스트 파일에 한 줄씩 읽어오고 내용이 없을때에는 null값을 리턴하여 준다 그래서 while문을 이용하여 null값이 나오기 전까지 내용을 계속 출력하게 만들어주었다.
		} catch (IOException e) {
			check = null;
		}
		return check;
	}
	/*
	  	[saveGame()]
	  	이곳은 선수를 생성할때에 고유번호를 만들어주는데(1,2,3,4,5....) 중복되지않게 1번부터 생성하는데 마지막에 생성된 선수의 고유번호를 받아서 파일에 저장해뒀다가
	  	선수를 만들때에 다시 저장된 카운트값을 전해줘서 저장된 값부터 선수의 번호가 나오게 해준다.
	  	
	 */
	public static void saveGame(int check, String adrees) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(adrees));

			String user_score = ""+check+"\n";
			System.out.println("user_score : "+user_score);
			out.write(user_score);
			// 1. 유저스코어라는 스트링타입의 변수에 매개변수 check를 통해 들어오는 선수의 고유번호값을 받아서 adrees변수에 지정해준 택스트파일에 저장해준다
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	/*
	 	[loadGame()]
	 	로드게임 메소드는 택스트에 카운트된 선수의 고유번호를 택스트 파일에서 꺼내와 세이브게임 메소드에 전해줘 100이라는 고유번호를 가진 선수가 있다면 100을 저장해뒀다가
	 	선수를 생성할때에 100이라는 숫자 다시 꺼내줘서 인트값으로 리턴해주고 다음 101,102... 이렇게 다음 번호부터 생성될수 있게 만들어 준다 
	*/
	public static int loadGame(String adrees) {
		try {
			BufferedReader in = new BufferedReader(new FileReader(adrees));
			
			String len = ""+in.readLine();

			CreatePlayerList.playerNum = Integer.parseInt(len);
			System.out.println("CreatePlayerList.playerNum  : "+CreatePlayerList.playerNum );
			// 1. Integer.parseInt를 이용하여 스트링값으로 꺼내온 고유번호를 인트값으로 넣어주고 전달해준다.
			// 2. CreatePlayerList.playerNum 은 고유번호를 만드는 스태틱변수이며 CreatePlayerList클래스에 선언되있다.
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return CreatePlayerList.playerNum + 1;		
	}
}
