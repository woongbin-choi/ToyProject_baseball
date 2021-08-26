# _⚾ Baseball Game_ (Toy Project)    
***
### Period : June 2021,  2weeks
### Personnel : 개인프로젝트  
***
## 📌 _Used Technology_ : JAVA 11.0.1 
***
## 📌 _Description_ 
Database를 배우기 전 Java 기본 문법과 객체지향적으로 클래스를 나누는 복습을 위해 만든 Toy Project<br>
data 관리를 위해 Stream으로 txt파일에 저장하고 로드하는 방식으로 게임 구현<br>
사용자는 구단을 생성하고 경매장에서 원하는 선수들을 영입하여 게임하는 방식<br>
스탯별로 선수들의 영입가가 다르고 게임 승리시 받는 상금으로 선수들을 영입 / 방출하며 자신만의 구단가치를 올리는 게임<br>


***
## 📌 _Core Trouble shooting_   
```java
public class Baseball {
  public static void main(String[] args) {
    try (BufferedReader in = new BufferedReader(new FileReader(
                  path, Charset.forName("UTF-8")),2048);
         BufferedWriter out = new BufferedWriter(new FileWriter(
                  path, Charset.forName("UTF-8"),false),2048)
         )
  }
}
```
> __Reader와 Writer 동시에 실행되어서 <br>FileOutputStream(String name, boolean append) append가 false이면<br> 기존 내용 삭제하고 새로운 내용을 덮어 주는데 메서드가 실행되는 순간 기존의 내용이 삭제되어<br> 파일 안의 모든 정보가 날아가 불러올 자료가 없게 된다는 것이다__


```java
(원하는 선수를 삭제해서 배열에 담는 메서드)
public class Baseball {
  public static void main(String[] args) {
    public static String[] del_list(String path, int playerNum) { 
      int len = 0;
      int count = 0;
      String[] delete;
      ArrayList<String> check_size = new ArrayList<>();
      try (BufferedReader in = new BufferedReader(new FileReader(
            path, Charset.forName("UTF-8")),2048);
            ){

         String check;
         while((check = in.readLine()) != null) {
            check_size.add(check);
            count++;
         }
         String[][] list = new String[count][];
         for(String checks : check_size) {
            list[len++] = checks.split("/");
         }
         delete = new String[count];
         
         for(int i = 0; i < list.length; ++i) {         
            if(playerNum != Integer.parseInt(list[i][0])) {
               delete[i] = check_size.get(i)+"\n";
            }else {
               delete[i] = "";
            }
         }   
  }
}
```
```java
(삭제된 선수의 정보를 담은 배열을 받아 파일의 기존 내용을 삭제하고 원하는 정보를 넣어주는 메서드)
public class Baseball {
  public static void main(String[] args) {
    public static void deletePlayer_on_txt(String path,String[] delete) {
      try(BufferedWriter out = new BufferedWriter(new FileWriter(
            path, Charset.forName("UTF-8"),false),2048)){
         for(int i = 0; i < delete.length; ++i) {         
            if(delete[i] != " ") {
               out.write(delete[i]);
            }
         }      
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
  }
}
```
이 문제를 해결하기 위해 메서드를 2개로 분리하였고<br>
하나는 원하는 선수를 삭제한 정보를 가지고 있는 배열을 리턴해주는 메서드를 만들었고<br>
나머지 하나는 파일의 기존 내용을 삭제하고 원하는 선수가 삭제된 정보를 가지고 있는 배열을 받아서<br>
파일에 다시 담아주는 메서드를 만들었다
 
## 📝  _Troubles_   

```java
public class Baseball {
  public static void main(String[] args) {
    while((check = in.readLine()) != null) {
	check_size.add(check);
	count++;
     }
    String[][] list = new String[count][];
  }
}
```
> __선수들의 정보중 일정 부분만을 선택할 수 있어야 한다__
> > 이중 배열에 담아서 문제를 해결하기로 하였으나 배열에 사이즈를 어떻게 줘야 할지가 문제였다<br>
배열은 동적 할당이 되지않기에 내가 파일에서 읽어온 사이즈를 알아야 했고,<br>
이 문제를 해결하기 위해 파일에서 정보를 읽어오는 while문이 돌 때에<br>
그 횟수를 카운트하여 문제를 해결하였다

***
```java
public class Baseball {
  public static void main(String[] args) {
    String [][] list = new String [count][];
	
   for(String checks : check_size) {
    list[len++] = checks.split("/");
   }

   for(int i = 0; i < list.length; ++i) {
      if(playerNum == Integer.parseInt(list [i][0])) {
         money = Integer.parseInt(list [i][4]);
         out.write(check_size.get(i)+"\n");
         System.out.println("[결과] : "+check_size.get(i));
      }
  }
 }
}
```
> __선수 스탯 가져오기__
> > 파일에 정보를 넣을 때에 '/'를 기준으로 넣어 스플릿을 사용하여 꺼낼 것은 생각하였으나<br>
어디에 담아야 선수들에 스탯을 잘 꺼내올 수 있을지 고민하였고,<br>
해결방안은 이중 배열을 이용하여 문제를 해결하였다

***

```java
public static void saveGame(int check, String adrees) {
      try {
         BufferedWriter out = new BufferedWriter(new FileWriter(adrees));

         String user_score = ""+check+"\n";
         System.out.println("user_score : "+user_score);
         out.write(user_score);
         out.close();
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }   
   }

public static int loadGame(String adrees) {
      try {
         BufferedReader in = new BufferedReader(new FileReader(adrees));
         
         String len = ""+in.readLine();

         CreatePlayerList.playerNum = Integer.parseInt(len);
         System.out.println("CreatePlayerList.playerNum  : "+CreatePlayerList.playerNum );
         in.close();
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
      return CreatePlayerList.playerNum + 1;      
   }
```
> __선수를 영입/방출하기 위해서는 고유번호가 필요하다__
> > 모든 선수들의 정보를 불러와 고유번호만을 체크하여 가장 큰 번호를 알아내고<br> 그다음의 숫자부터
선수를 생성하면 되는데<br> 파일을 이용하여 선수의 고유번호를 세이브하고 로드하는 방식을 택하였다

***  

## 🔆 _Bragging Code_    

> `선수 리스트에서 삭제하기`
> >  1. path에 불러올 파일의 주소를받고 playerNum에 삭제하고 싶은 선수의 고유번호를 받는다<br><br>2. path로 전해받은 주소의 택스트파일에 들어있는 선수목록을 check_size안에 넣어준다<br> 카운트를 하는 이유는 list라는 이중배열의 크기값을 정해줘야하기때문이다<br><br>3.delete라는 배열안에 선수들의 정보를 담아줄건데 이곳에서 삭제하고싶은 선수를 공백으로 만들어 배열에 담는다<br>
			  반복문을 설명하자면 삭제하고싶은 선수의 고유번호와 playerNum의 값이 일치하지 않을때에<br> delete배열안에 선수들의 정보를 넣어주고 
		    값이 일치할때에는 공백을 넣어둬서<br> 리스트안에서 정보를 삭제하고 공백만을 넣어둔후 delete배열을 리턴해준다
```java
public static String[] del_list(String path, int playerNum) { 
		int len = 0;
		int count = 0;
		String[] delete;
		ArrayList<String> check_size = new ArrayList<>();
		try (BufferedReader in = new BufferedReader(new FileReader(
				path, Charset.forName("UTF-8")),2048);
				){

			String check;
			while((check = in.readLine()) != null) {
				check_size.add(check);
				count++;
			}
			String[][] list = new String[count][];
			for(String checks : check_size) {
				list[len++] = checks.split("/");
			}
			delete = new String[count];
			for(int i = 0; i < list.length; ++i) {			
				if(playerNum != Integer.parseInt(list[i][0])) {
					delete[i] = check_size.get(i)+"\n";
				}else {
					delete[i] = "";
				}
			}	
			return delete;
		  
		} catch (IOException e) {
			e.printStackTrace();
		}	
		return null;
	}
```   

***  

> `타석 바꾸기`
> > 1. 선수의 순번 배정을 위해 bat_turn 배열안에 1~6까지의 숫자를 넣어준다<br>2. int pick으로 값을 받는데 배열이기에 -1을 해줘서 0번 자리부터 교환 할 수 있게 해준다<br>3. 콘솔에 bat_turn 배열을 띄워 1,2,3,4,5,6을 보여주고 선수들이 순서대로 나올때에 그 선수를 replace라는 기능을 이용하여<br>bat_turn배열에 입력한 숫자의 자리와 값을 교환하여 bat_turn 배열안에 선수의 정보를 담아준다<br> 
```java
public class Baseball {
  public static void main(String[] args) {
 	String[] bat_turn = {"1","2","3","4","5","6"};
         
         for(int i = 0 ; i < 6; ++i) {
            System.out.println(G.return_array(T.print_txt(user+".txt"))[i]);
            System.out.println(Arrays.toString(bat_turn));
            System.out.println("몇번째 타석에 넣으시겠습니까 ?");
            int pick = sc.nextInt()-1;

            bat_turn[pick] = bat_turn[pick].replace(bat_turn[pick], G.return_array(T.print_txt(user+".txt"))[i]);
         }
 }
}
```


## 📸 _ScreenShot_   
1. 구단 설정하기   
  <img width="532" alt="KakaoTalk_20210817_132804121" src="https://user-images.githubusercontent.com/82255957/129663781-ebacfad4-88c5-4b7d-a914-1edbd3218ce2.png">
2. 선수 구매/상대 팀 결정/ 타석바꾸기 
<img width="962" alt="KakaoTalk_20210817_132804121_01" src="https://user-images.githubusercontent.com/82255957/129663819-d5882a25-233c-40a6-bc39-f25aad5db168.png">
 
<img width="862" alt="KakaoTalk_20210817_132804121_02" src="https://user-images.githubusercontent.com/82255957/129663840-2b8a322a-a5c6-4fb1-bcb0-05c11207b651.png">
3. 게임 진행 과정
<img width="211" alt="KakaoTalk_20210817_132804121_03" src="https://user-images.githubusercontent.com/82255957/129663858-3523dcb6-6605-4ba9-b825-e20230fa68a9.png">

## _회고록_
처음으로 Java Toy Project를 진행하면서 그동안 배웠던 문법들을 다시 한번 정리할 수 있었고<br>
많이 부족한 코드이지만 객체지향적으로 계속해서 나눠가는 연습을 할 수 있었다<br>
Toy Project는 오류들을 만났을 때 스트레스 받지 않는 선에서 고쳐나가는 재미를 느끼게 해주었다<br>
이제는 DB와 Spring을 배워나가는걸 이용해서 그동안 만들고 싶었던 프로젝트를 다시 한번 도전해보고 싶은 마음이 들었다
