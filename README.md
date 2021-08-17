# _⚾ Movie Kiosk_ (Toy Project)    
***
### Period : June 2021,  2weeks
### Personnel : 개인프로젝트  
***
## 📌 _Used Technology_ : JAVA 11 
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
> __Reader와 Writer이 동시에 실행되어서 <br>FileOutputStream(String name, boolean append) append가 false이면<br> 기존 내용 삭제하고 새로운 내용을 덮어 주는데 메서드가 실행되는 순간 기존의 내용이 삭제되어<br> 파일 안의 모든 정보가 날아가 불러올 자료가 없게 된다는 것이다__


```java
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



*** 
```java
public class BootSpringBootApplication {
  public static void main(String[] args) {
    System.out.println("트러블 오류 해결코드");
  }
}
```
> __해결 방법__
> > 해결방법 설명

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

## 📸 _ScreenShot_   
  ![ex_screenshot](./img/1.png)


## _회고록_
~~~~~~~~~~작성~~~~~~~~~~~
_
