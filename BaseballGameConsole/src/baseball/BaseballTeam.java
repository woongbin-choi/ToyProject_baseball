package baseball;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Scanner;

public class BaseballTeam {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("1)야구팀 추가하기\t2)야구팀 리스트");
		int num = sc.nextInt();
		
		if (num == 1) {
			add_BaseballTeam_list();
		} else {
			print_BaseballTeam_list();
		}
		
	}
	
	public static String makeBaseballTeam() {
		Scanner sc = new Scanner(System.in);
		System.out.println("생성하고 싶은 야구팀 이름을 적어주세요 >");
		String teamName = sc.next();
		
		int ran = (int)(Math.random() * 10 + 1);
		
		String team = teamName + (" /\t난이도: "+ran);
		
		return team;
	}
	
	public static void add_BaseballTeam_list() {
		try (BufferedWriter out = new BufferedWriter(new FileWriter(
				"src/scores/야구팀리스트.txt", Charset.forName("UTF-8"),true),2048)){
			
			out.write(makeBaseballTeam());
			out.write("\n");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("리스트에 해당 야구팀을 추가하였습니다");
	}
	
	public static void print_BaseballTeam_list() {
		try (BufferedReader in = new BufferedReader(new FileReader(
				"src/scores/야구팀리스트.txt", Charset.forName("UTF-8")),2048)){
			
		String line;
		while(( line = in.readLine()) != null ) {
			System.out.println(line);
		}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}