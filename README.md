# 개발 환경
Java 기반 으로 Jsoup을 이용한 파싱

# 용도
영화의 현재 개봉작과 개봉 예정작을 확인을 하고 예고편이나 후기 같은 정보를 확인하고 바로 예매할수 있게 하기 위해 제작 하였다.

# 이용 대상
영화를 좋아하고 영화를 즐겨보는 현대인이 이용 대상이다.

# 개발 목적
-오늘날 영화에 대한 소식을 SNS로 많이 접하는데 광고 글에 의한 편행된 시선이기 때문에 신뢰성이 떨어진다<br>
-현재 개봉되어 상영하고 있는 영화는 물론, 앞으로 개봉될 영화들의 소식 또한 자세히 알 수 있는 프로그램이 부족하다,

# 주요 코드
소스 : 파싱(영화 순위)<br>
![image](https://user-images.githubusercontent.com/38156821/43882906-599d9cfe-9bec-11e8-9686-a73b40d120b3.png)
<br>
소스 : 영화 리스트<br>
![image](https://user-images.githubusercontent.com/38156821/43882922-697e0b40-9bec-11e8-82f1-6ecd15d49f04.png)
<br>![image](https://user-images.githubusercontent.com/38156821/43882930-6d9cbdca-9bec-11e8-8d79-1af52f3fd812.png)
<br>
소스 : 예매 하는 브라우져 띄우는 기능<br>
![image](https://user-images.githubusercontent.com/38156821/43882946-7743d23c-9bec-11e8-8d56-fc2d2461721c.png)
<br>
소스 : 인물, 줄거리, 퍼스터 정보 파싱<br>
![image](https://user-images.githubusercontent.com/38156821/43882950-7f5832a6-9bec-11e8-94d1-03c6bfde3291.png)
<br>![image](https://user-images.githubusercontent.com/38156821/43882954-8169ff84-9bec-11e8-8b5b-b463a60c48ce.png)
<br>![image](https://user-images.githubusercontent.com/38156821/43882959-8494d580-9bec-11e8-8ad9-3f636e942cf1.png)
<br>
소스 : 배우 정보가 없는 경우의 예외처리<br>
![image](https://user-images.githubusercontent.com/38156821/43882965-8a5d13e2-9bec-11e8-930c-cba6c19eb9a5.png)
<br>
소스 : 예고편 연결<br>
![image](https://user-images.githubusercontent.com/38156821/43882974-8f98c16c-9bec-11e8-929d-ba7dd7dff19c.png)
<br>![image](https://user-images.githubusercontent.com/38156821/43882980-936cec28-9bec-11e8-8056-0e3dc3b7be50.png)
<br>

# 특징
후기와 예고편은 빠르게 확인을 할 수 있으며 바로 원하는 영화관의 홈페이지로 빠르게 이동을 하여 자리확인과 빠른 예매를 할수 있다.

# 어려웠던점
Jsoup을 이용하여 파싱을 하다보니 태그값이 수시로 바뀌고 태그값을 찾기 위해 여러번 컴파일을 돌려본것.
ui디자인을 바꾸기 위해 Look & Feel 기능을 찾아 쓴것.

