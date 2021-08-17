package baseball;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;

/*
 	[클래스 설명]
  	CreatePlayerList: 선수를 생성하기 위한 클레스이다
  	선수들을 생성할때에 고유번호,이름,직업,스탯,가격만 생성해서 넣기에는 후일 경매장 또는 나의 팀에 영입할때에 기준이 되는 값이 필요하다
  	그때에 이름,직업,스탯,가격만을 랜덤으로 만든다면 중복되는 경우의 수 가 생길수 있기 때문에 기준이 되는 값이 필요하여 고유번호를 만들어주었다
*/
public class CreatePlayerList {
	
	public static String[] firstNames = {"김","최","이","박","나","권","지","우","강","남","하","유"};
	public static String[] midleNames = {"두","웅","한","지","계","강","한","성","용","광","용","형","영","재"};
	public static String[] lastNames = {"식","빈","수","나","우","호","환","석","현","하","석","수","금"};
	public static int player_count; // 이 변수를 이용해서 선수의 고유번호를 카운드할것이며
	
	public static String playerName;
	public static int stats;
	public static int money;
	public static int playerNum = 1; // 이 변수를 이용해서 선수의 고유번호를 카운트할것이며 TxTManager클래스에 saveGame과 loadGame메소드를 이용해 텍스트에 저장하고 불러오며 번호를 늘려가줄것이다.
	public static String role;
	
	
	
	/*
	   [createHitter() 메서드 설명]
	   타자를 생성하는 메서드이며 고유번호,이름,능력치,값,역할 을 값으로 넣어준다
	   [문제점]
	   고유번호인 playerNum은 야구선수가 한명씩 만들어 질 때마다 번호가 추가되어 생성된다.
	   이걸 처음 생성시에는 순서가 붙어서 0 ~ 원하는 숫자까지 생성되지만 다시 생성할때에는 번호가 0번부터 다시 붙는 문제가 있어서
	   이 문제를 방지하기위해 TxTManager클래스에서 고유번호를 세이브,로드 해주는 메서드를 만들어 고유번호를 택스트에 저장후 다시 불러와서 그 이후부터 만들수있는 메서드를 만들어두었다.
	 */
	public static String createHitter(){
		playerNum =  player_count++;
		playerName = createName();
		stats = hitterStats();
		money = hitterMoney(stats);
		role = "타자";
		return String.format(""
				+ "%d/%s/%s/%d/%d",playerNum,playerName,role,stats,money);
	}
	
	
	/*
	   [createPitcher() 메서드 설명]
	   투수를 생성하는 메서드이며 고유번호,이름,능력치,값,역할 을 값으로 넣어준다
	 */
	public static String createPitcher(){
		playerNum =  player_count++;
		playerName = createName();
		stats = pitcherStats();
		money = pitcherMoney(stats);
		role = "투수";
		return String.format("%d/%s/%s/%d/%d",playerNum,playerName,role,stats,money);
	}
	
	
	/*
	 	[createName() 메서드 설명]
	 	이름을 랜덤으로 생성하는 메서드이고 성,미들네임,라스트네임으로 나눠서 랜덤으로 줬다
	 	고유번호가 있기에 굳이 미들네임은 안나눠도 되지만 중복이 최대한 없으면 좋을거같아서 이렇게 넣어줬다.
	 */
	private static String createName() {
		return  firstNames[(int)(Math.random()*firstNames.length)]
				+midleNames[(int)(Math.random()*midleNames.length)]
						+lastNames[(int)(Math.random()*lastNames.length)];
	}
	
	/*
	 	[pitcherStats() , hitterStats() 메서드 설명]
	 	타자의 능력치는 1~5까지 랜덤으로 주고싶었고
	 	투수의 능력치는 4~8까지 랜덤으로 주고싶어서 랜덤값을 리턴해주었다
	 */
	public static int hitterStats() { // 타자
		return (int)(Math.random()*5+1);
	}
	
	public static int pitcherStats() { // 투수
		return (int)(Math.random()*5+4);
	}
	
	/*
	 	[hitterMoney(), pitcherMoney]
	 	스탯에 * 100 해서 선수의 가격을 리턴해주는 메서드이다.
	 	(투수가 더 비쌈...)
	 */
	public static int hitterMoney(int stats) {
		return (int)(Math.random()+100) * stats;
	}
	
	public static int pitcherMoney(int stats) {
		return (int)(Math.random()+100) * stats;
	}

}
