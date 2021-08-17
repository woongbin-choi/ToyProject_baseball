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
 
## 📝  _Trouble_   

### 오류발생
```java
public class BootSpringBootApplication {
  public static void main(String[] args) {
    System.out.println("트러블 오류 해결코드");
  }
}
```
> __해결 방법__
> > 해결방법 설명

### 오류발생
```java
public class BootSpringBootApplication {
  public static void main(String[] args) {
    System.out.println("트러블 오류 해결코드");
  }
}
```
> __해결 방법__
> > 해결방법 설명

### 오류발생
```java
public class BootSpringBootApplication {
  public static void main(String[] args) {
    System.out.println("트러블 오류 해결코드");
  }
}
```
> __해결 방법__
> > 해결방법 설명

### 오류발생
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
