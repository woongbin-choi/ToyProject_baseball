package baseball;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class GameStart {
	static Scanner sc = new Scanner(System.in);
	static MyTeam M = new MyTeam();
	static TxTManager T = new TxTManager();
	static InGame G = new InGame();

	public static void main(String[] args) {
		boolean set = true; // 선수 한명 아웃될때까지
		boolean gameset = true; // 선수 세명 아웃될때까지 
		int out_player = 0;
		int strike = 0;
		int ball = 0;
		int hit;
		String[] player_name = new String[6];

		System.out.println("1)기존유저\t2)신규유저");
		int select = sc.nextInt();
		int six = 0;

		if (select == 2) { 
			System.out.println("<게임이 시작되었습니다>\n\n[게임설명] : 야구구단을 창설한 후, "
					+ "선수들을 영입 후 게임을 시작해주세요 \n "
					+ "\t  선수들은 6명으로 구성되며 경기가 끝날 때 마다 경매장을 이용하실 수 있습니다\n"
					+ "\t  초기 자본은 3000원이며 경기 승리시 +2000 / 패배시 +500 을 획득하십니다 \n"
					+ "\t  먼저 야구구단의 이름을 설정해주세요 !");

			System.out.println("구단이름 >");
			String name = sc.next();
			M.make_team(name);
			System.out.printf("구단의 이름이 [%s] 입니다\n",name);
			System.out.println("보유머니 3000원으로 6명의 선수를 구매해주세요 !!");

			System.out.println(T.print_txt(T.aHouse));

			while(true) {
				System.out.println("구매하고 싶은 선수번호를 눌러주세요 > ");
				int pick = sc.nextInt();					
				T.addPlayer_on_txt(name+".txt", T.aHouse, pick);
				T.deletePlayer_on_txt(T.aHouse,T.del_list(T.aHouse,pick)); 
				M.walletMinus(M.holdMoney(), T.money);
				six++;
				if (six == 6) {
					System.out.println("선수구매를 종료하겠습니다");
					break;
				}
			}

			System.out.println("1)선수명단\t2)게임시작");
			select = sc.nextInt();

			String[] bat_turn = {"1","2","3","4","5","6"};


			if (select == 1) {
				System.out.println(T.print_txt(name+".txt"));

			} else {
				G.inGame_des();

				for(int i = 0 ; i < 6; ++i) {
					System.out.println(G.return_array(T.print_txt(name+".txt"))[i]);
					System.out.println(Arrays.toString(bat_turn));
					System.out.println("몇번째 타석에 넣으시겠습니까 ?");
					int pick = sc.nextInt()-1;

					bat_turn[pick] = bat_turn[pick].replace(bat_turn[pick], G.return_array(T.print_txt(name+".txt"))[i]);
				}

				System.out.println(Arrays.toString(bat_turn)); // 바뀐 타석 보여주기

				for (int i = 0; i < bat_turn.length; ++i) {
					player_name[i] = G.bringPlayerName(bat_turn[i]);
				}

				G.attack_des(); // 게임 설명			
				if (out_player == 3) {
					gameset = false;
				}

				while(gameset) {

					for(int i = 0; i < player_name.length; ++i) {

						while (set) {
							System.out.printf("%s타자\n1)휘두른다\t2)휘두르지 않는다\n",player_name[i]);
							System.out.println("선택해주세요 >");
							hit = sc.nextInt();

							if (hit == 1) {
								if (G.special()) {
									System.out.println("홈런입니다 !!");
									break; 

								} else if (G.success(G.bringPlayerStats(bat_turn[i]))){
									int ran = (int)(Math.random() * 3 + 1);
									int num = ran;
									player_name[i] += player_name[i] + G.check_where(num); 
									break;

								} else {
									System.out.println("Strike !!");
									strike ++;
									break;
								}
 
							} else {
								int ran = (int)(Math.random()* 2 + 1);
								if (ran == 1) {
									System.out.println("Strike !!");
									strike ++;
								} else {
									System.out.println("Ball !!");
									ball++;
								}
							}
							if (strike == 3) {
								System.out.println("3스트라이크 아웃 !! ");
								set = false;
							}
							if (ball == 4) {
								System.out.println("[볼넷] 진루 !! ");
								player_name[i] += player_name[i] + G.check_where(1);
								set = false;
							}
						}
					}
					System.out.println("OUT !!");
					gameset = false;

				}
			}


		}else {
			String user;
			System.out.print("불러오실 팀명을 입력하여 주세요 : ");
			user = sc.next();
			while(T.print_txt(user+".txt") == null) {
				System.out.println("팀명을 다시 입력해주세요");
				user = sc.next();
			}
			System.out.println(T.print_txt(user+".txt"));
			
			String[] bat_turn = {"1","2","3","4","5","6"};

			G.inGame_des();

			for(int i = 0 ; i < 6; ++i) {
				System.out.println(G.return_array(T.print_txt(user+".txt"))[i]);
				System.out.println(Arrays.toString(bat_turn));
				System.out.println("몇번째 타석에 넣으시겠습니까 ?");
				int pick = sc.nextInt()-1;

				bat_turn[pick] = bat_turn[pick].replace(bat_turn[pick], G.return_array(T.print_txt(user+".txt"))[i]);
			}
			for (int i = 0; i < bat_turn.length; ++i) {
				player_name[i] = G.bringPlayerName(bat_turn[i]);
			}
			// 5. bat_turn[] 안에 있는 선수의 정보를 넣어주면 bringPlayerName()를 이용해 선수의 이름만 player_name배열안에 넣어준다 

			G.attack_des(); // 게임 설명	
			System.out.println();
			
			if (out_player == 3) {
				gameset = false;
			}
			int count = 0 % 6;

			boolean out_loop = true;
			while(out_loop) {	
				
				boolean inner_loop = true;
				while(strike != 3 || ball != 4) {
					System.out.printf("%s타자\n1)휘두른다\t2)휘두르지 않는다\n",player_name[count]);
					System.out.println("선택해주세요 >");
					hit = sc.nextInt();

					if (hit == 1) {
						if (G.special()) {
							System.out.println("홈런입니다 !!");
		

						} else if (G.success(G.bringPlayerStats(bat_turn[count]))){
							System.out.println("안타입니다");
							int ran = (int)(Math.random() * 3 + 1);
							int num = ran;
							player_name[count] += player_name[count] + G.check_where(num); 
						

						} else {
							System.out.println("Strike 당했습니다!!");
							strike ++;
							if (strike == 3) {
								System.out.println("3스트라이크 아웃 !! ");
								break;
							}
						}

					}else {
						int ran = (int)(Math.random()* 2 + 1);
						if (ran == 1) {
							System.out.println("Strike 당했습니다!!");
							strike ++;
							if (strike == 3) {
								System.out.println("3스트라이크 아웃 !! ");
							}
						} else {
							System.out.println("Ball 개이득!!");
							ball++;
							if (ball == 4) {
								System.out.println("[볼넷] 진루 !! ");
								player_name[count] += player_name[count] + G.check_where(1);		
							}
						}
					}
					System.out.printf("[%dS] : [%dB]\n",strike,ball);
				}
				count++;
				strike = 0;
				ball = 0;
				
			}
		}

	}

}
