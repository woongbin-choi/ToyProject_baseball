package baseball;

import java.util.ArrayList;
import java.util.Scanner;

// 타자 4명 투수 2명  
// 컴퓨터 0 플레이어 1
public class InGame {
	static TxTManager T = new TxTManager();
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {

		System.out.println(return_array("승리.txt")[0]);
	}


	public static void inGame_des() {
		System.out.println("게임 승리시 +1000, 패배시 +500 획득하십니다");
		System.out.println(T.print_txt("야구팀리스트.txt"));
		System.out.println("게임할 상대 구단명을 적어주세요 >");
		String name = sc.nextLine();
		System.out.printf("경기가 시작되었습니다.\n 상대 : [ %s구단 ]\n",name);
		System.out.println("게임은 3회말까지 진행되고, 사용자 선공입니다 [타자 순번을 정해주세요]");
	}

	public static void attack_des() {
		System.out.println("공격차례 입니다\n모든타자는 10퍼센트 확률로 홈런을 칠 수 있고\n"
				+ "홈런을 치지 못하였을 때는, 선수의 스탯확률로 안타를 칠 수 있습니다\n"
				+ "매 공격시 타자가 배트를 휘두룰지 선택하실 수 있습니다");
	}

	public static void defense_des() {
		System.out.println("수비차례 입니다\n투수는 공을 던질때마다 10퍼센트 확률로"
				+ "무조건 스트라이크로 던질 수 있고,\n 그렇지 않을 때는 선수의 스탯확률로 스트라이크를 던집니다"
				+ "그 외의 모든 룰은 야구룰에 적용됩니다");
	}

	public static void rotation() {

	}

	public static int bringPlayerStats(String player) {
		ArrayList<String> player_stats = new ArrayList<>();
		for(String temp : player.split("/")) {
			player_stats.add(temp);
		}
		return Integer.parseInt(player_stats.get(3));
	}

	public static String bringPlayerName(String player) {
		ArrayList<String> player_name = new ArrayList<>();
		for(String temp : player.split("/")) {
			player_name.add(temp);
		}
		return player_name.get(1);
	}

	public static boolean special() {
		int ran = (int)(Math.random() * 10 + 1);
		if(ran == 5) {
			return true;
		}
		return false;
	}

	public static String[] return_array(String addrees) {
		int count = 0;
		int len = 0;
		for(String counts : addrees.split("\n")) {
			count++;
		}
		String[] check = new String[count];
		for(String play : addrees.split("\n")) {
			check[len++] = play;
		}
		return check;
	}

	public static String check_where(int number) {
		String num = number + "";
		String where = "/" + num;

		return where;
	}
	
	public static String ruta(int ran) {
		String[] ranruta = {"1루타입니다","2루타입니다","3루타입니다"};
		
		String result = "HIT !!\n"+ranruta[ran];
		
		return result;
	}
	
	public static boolean success(int stat) {
		int ran = (int)(Math.random() * 10 + 1);
		
		if (ran <= stat) {
			return true;
		} else {
			return false;
		}
	}
}