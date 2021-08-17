package baseball;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Scanner;

public class woongbinAuction {
	static MyTeam myteam = new MyTeam();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("1) 관리자\t\t2)구단주");
		System.out.println("번호를 눌러주세요 > ");
		int userNum = sc.nextInt();
		
		
		if (userNum == 1) {
			System.out.println("1) 선수 100명 생성\t\t2)선수 2명 생성");
			System.out.println("번호를 눌러주세요 > ");
			int adminNum = sc.nextInt();
			
			if (adminNum == 1) {
				firstInsertAuctionHouse();
			} else {
				insertAuctionHouse();
			}
			
		} else {
			printAuctionHouse();
			System.out.println("1) 선수구매\t2)선수판매");
			System.out.println("번호를 눌러주세요 > ");
			
			if (userNum == 1) {
				purchasePlayer();
			} else {
				sellingPlayer();
			}
		}
	}
	
	public static void firstInsertAuctionHouse() {
		
		try (BufferedWriter out = new BufferedWriter(new FileWriter(
				"경매장.txt", Charset.forName("UTF-8")),2048)){
			out.write("========================== 경매장 ==========================");
			out.write("\n");
			
			CreatePlayerList p = new CreatePlayerList();
			
			for (int i = 1; i <= 100; ++i) {
				if (i <= 50) {
					out.write(i+"/");
					out.write(p.createHitter());
					out.write("\n");
				} else {
					out.write(i+"/");
					out.write(p.createPitcher());
					out.write("\n");
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("경매장에 100명 선수를 추가하였습니다");
		
	}
	
	public static void insertAuctionHouse() {
		ArrayList<String> playerList = new ArrayList<>();
		try (BufferedWriter out = new BufferedWriter(new FileWriter(
				"경매장.txt", Charset.forName("UTF-8"),true),2048)){
			
			CreatePlayerList p = new CreatePlayerList();
			
			try (BufferedReader in = new BufferedReader(new FileReader(
					"경매장.txt", Charset.forName("UTF-8")),2048)){
				
			String line;
			while(( line = in.readLine()) != null ) {
				System.out.println(line);
				playerList.add(line);
			}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			out.write((int)(playerList.size())+"/");
			out.write(p.createHitter());
			out.write("\n");
			out.write(((int)(playerList.size())+1)+"/");
			out.write(p.createPitcher());
			out.write("\n");
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("경매장에 2명 선수를 추가하였습니다");
		
	}
	
	public static void printAuctionHouse() {
		try (BufferedReader in = new BufferedReader(new FileReader(
				"경매장.txt", Charset.forName("UTF-8")),2048)){
			
		String line;
		while(( line = in.readLine()) != null ) {
			System.out.println(line);
		}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("경매장에 모든 선수들의 리스트입니다.");
	}
	
	public static boolean purchasePlayer() {
		String[] split;
		ArrayList<String> lineNum = new ArrayList<>();
		ArrayList<String> lN = new ArrayList<>();
		
		Scanner sc = new Scanner(System.in);
		
		try (BufferedReader in = new BufferedReader(new FileReader(
				"경매장.txt", Charset.forName("UTF-8")),2048)){
			
			printAuctionHouse();
			
		System.out.println("구매하고 싶은 선수의 앞 번호를 입력해주세요 >");
		String user = sc.nextLine();
		int user_num = Integer.parseInt(user);
			
		String line;
		while(( line = in.readLine()) != null ) {
			lineNum.add(line);
			lN.add(lineNum.get(lineNum.size()-1));
			split = line.split("/");
			
			lineNum.clear(); 
		}
		System.out.println(lN.get(user_num));
//		myteam.add_player(lN.get(user_num));
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public static boolean sellingPlayer() {
		
		return false;
	}
	
	
}