package baseball;

import java.io.File;
import java.util.Arrays;

public class priview {
	
	public static void main(String[] args) {
		InGame.bringPlayerName("5/최식/타자/3/500");
		String[] ruta = {"1루타: ","2루타: ","3루타: "};
		System.out.println(ruta[2]+InGame.bringPlayerName("5/최식/타자/3/500"));

	}
}
