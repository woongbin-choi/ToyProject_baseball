package baseball;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Scanner;

public class AuctionHouse {
	   /*
	     	AuctionHouse
	     	이곳은 선수를 사고팔수있는 기능과 CreatePlayerList 클래스에서 메서드를 가져와 선수를 생성하는 역할을가진 경매장 클래스이다.
	   */
	   public static Scanner sc = new Scanner(System.in);
	   
	   static TxTManager T = new TxTManager();
	   static MyTeam M = new MyTeam();
	   
	   public static void main(String[] args) {
	      Scanner sc = new Scanner(System.in);
	      
	      System.out.println("1) 관리자\t\t2)구단주");
	      System.out.println("번호를 눌러주세요 > ");
	      int userNum = sc.nextInt();

	      if (userNum == 1) {
	         insertAuctionHouse();
	      } else {
	        System.out.println(T.print_txt(T.aHouse));
	         System.out.println("1) 선수구매\t2)선수판매");
	         userNum = sc.nextInt();
	         if (userNum == 1) {
	        	 System.out.println("번호를 눌러주세요 > ");
	        	 userNum = sc.nextInt();
	            T.addPlayer_on_txt(T.mTiem, T.aHouse, userNum);
	            T.deletePlayer_on_txt(T.aHouse,T.del_list(T.aHouse,userNum));
	            M.walletMinus(M.holdMoney(), T.money);
	            
	         } else {
	        	 System.out.println(T.print_txt(T.mTiem));;
	        	 System.out.println("번호를 눌러주세요 > ");
		         userNum = sc.nextInt();
	        	 T.addPlayer_on_txt(T.aHouse, T.mTiem, userNum);
		         T.deletePlayer_on_txt(T.mTiem,T.del_list(T.mTiem,userNum));
		         M.walletPlus(M.holdMoney(), T.money);
	         }
	      }   
	   }
	   /*
	    	[insertAuctionHouse()]
	    	이곳에서는 TxTManager와 MyTeam 클래스에서 만든 메서드들을 이용하여 원하는 포지션의 선수를 생성하는 메서드이다.
	    	 TxTManager와 MyTeam에서 기능을 가져다가 쓰기떄문에 크게 주석처리 할 내용이 없다.
	   */
	   public static void insertAuctionHouse() {
	      int pick = 0;
	      CreatePlayerList.player_count = T.loadGame("선수번호.txt");
	      // 1. CreatePlayerList.player_count = T.loadGame("선수번호.txt"); 에서 택스트 파일에 저장된 선수의 고유번호를 받아 다음 번호가 생성되서 선수마다 고유의 번호를 가질수있게 해준다
	      CreatePlayerList p = new CreatePlayerList();
	      
	      try (BufferedWriter out = new BufferedWriter(new FileWriter(
	            "경매장.txt", Charset.forName("UTF-8"),true),2048)){   

	         System.out.println("0.선수추가 종료 1.선수추가 타자 2.선수추가 투수");
	         while(pick == 0) {
	            pick = sc.nextInt();
	            
	            if(pick == 1) {
	               System.out.print("생성하고싶은 타자의 수를 입력해주세요 : ");
	               pick = sc.nextInt();
	               for(int i = 0; i < pick; ++i) {
	               out.write(p.createHitter());
	               out.write("\n");
	               }
	            }else if(pick == 2) {
	               System.out.print("생성하고싶은 투수의 수를 입력해주세요 : ");
	               pick = sc.nextInt();
	               for(int i = 0; i < pick; ++i) {
	               out.write(p.createPitcher());
	               out.write("\n");
	               }
	            }
	         }
	       
	         T.saveGame(CreatePlayerList.playerNum,"선수번호.txt");
	       // 2. saveGame을 통해서 마지막으로 나온 선수의 고유번호를 택스트파일에 전해줘서 넣어준다.  
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
	      System.out.println("경매장에 선수를 추가하였습니다");
	   }   
	}