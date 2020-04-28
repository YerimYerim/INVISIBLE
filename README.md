# team_project  
### :video_game: 안드로이드 게임프로그래밍 팀프로젝트

# 메인게임화면  
 - 배경이외의 공간은 조이스틱을 사용할 수 있는 공간이다. 
 - PLAYER 은 원 내부에서만 움직일 수 있다.  
   
<img src = "https://user-images.githubusercontent.com/40654954/80394287-9c0d5400-88ec-11ea-87c3-a72d02fa42d5.jpg" height="470px" width="280px"/> <img src ="https://user-images.githubusercontent.com/40654954/80394266-96177300-88ec-11ea-9734-274b74784532.jpg" height = "300px" width = "300px"/>

# Player
 - :joystick:조이스틱으로 방향을 지정할 수 있다.
 - 플레이어는 원으로 생겼으며 내부에 획득한 stone 들이 회전하고 있다. 
 - 가장 최근에 획득한 stone 의 색으로 변경된다.
 - 모든 색의 stone 을 획득해야지만 코어 색 하나를 부술수 있다. 
 - 가속도가 붙을 경우 붙은 속도에 따라 꼬리의 길이가 달라진다.
   
 <img src ="https://user-images.githubusercontent.com/40654954/80394310-9f084480-88ec-11ea-8254-a50e0e6a8496.jpg" height = "300px" width = "250px"/><img src ="https://user-images.githubusercontent.com/40654954/80394316-a0d20800-88ec-11ea-8053-45881f22efdb.jpg" height = "300px" width = "600px"/>

# Core & Light
 - 육각형 모양으로 되어있으며 안에는 6개의 각 다른색을 가진 삼각형으로 구성되어 있다.  
 - 모든 삼각형이 사라지게 되면 게임은 끝난다.
 - Light 는 60도 각도를 가진 부채꼴 모양이다.
 - Light 는 각 Core 가 보유중인 삼각형의 색상중 하나를 Light 색상으로 일정한 시간마다 지정할 수 있다.(바뀐다)
 - Core 는 자전하며 그에따라 Light도 같이 회전한다.  
   
<img src ="https://user-images.githubusercontent.com/40654954/80394266-96177300-88ec-11ea-9734-274b74784532.jpg" height = "300px" width = "300px"/><img src ="https://user-images.githubusercontent.com/40654954/80394270-97e13680-88ec-11ea-83a4-784a138d1edc.jpg" height = "300px" width = "150px"/>

# Stone
 - 각 스톤은 6가지 색상을 가졌다. 
 - 시간에 따라 생성되었다가 소멸된다.
 - Player와 충돌할 경우 소멸된다.
 
 # 순서도  
- 플레이어를 기준으로 메인 게임루프에 대해 순서도를 작성한 결과
  
<img src = "https://user-images.githubusercontent.com/40654954/80484602-f2cd6900-8992-11ea-8e50-01f5d886aa7d.png" height="500px" width="500px"/>
 
