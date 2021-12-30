# library_management
도서관 관리 프로젝트입니다. 원하는 책을 검색하고 예약할 수 있으며, 희망도서를 신청할 수 있습니다.

# 기획의도
현재 국립, 사립 도서관 등에서는 도서 관리 프로그램을 통해 그 도서관의 책들을 관리하고 보존하고 있습니다.
 저희는 이러한 프로그램이 어떻게 제작하고 설계하는지 궁금하여
기존 도서관의 웹사이트들을 벤치마킹하여 도서 정보를 제공하고 사용자에게 필요한 기능을 가진 사이트를 만들고자 합니다.

# 사용기술 및 환경
java8, spring, maven, jdbc, oracle, html5, css3, java script

# 요구사항 작성
아이디와 비밀번호를 기반으로 로그인 기능을 구현하며, 회원은 일반 회원과 관리자 2가지로 한다.
일반 회원은 도서관에 추가하고 싶은 도서를 신청할 수 있다. 이를 ‘희망도서’라고 한다.
관리자는 회원이 신청한 ‘희망도서’를 확인하여 그에 대해 답변을 할 수 있고, 원한다면 새로운 도서를 추가할 수 있다.
‘희망도서’는 로그인 된 상태의 회원만이 작성할 수 있다.
도서 검색은 누구나 할 수 있으나 예약은 로그인 된 상태의 회원만이 가능하다.
검색은 책 제목과 저자로 세분화 하여 할 수 있어야 한다.

# 엔티티 관계 정의서
![image](https://user-images.githubusercontent.com/75685520/147651773-4b642d10-5154-494d-b268-30a39429d534.png)

# 테이블 명세서
![image](https://user-images.githubusercontent.com/75685520/147651825-36320c2d-1607-4b90-b8ed-33c7beb10124.png)
![image](https://user-images.githubusercontent.com/75685520/147651860-af796dfb-7402-450e-a51c-ccc1f5a91c39.png)
![image](https://user-images.githubusercontent.com/75685520/147651879-a616bb7a-a39f-40bb-a745-55c617a875e4.png)

# 프로세스 모델링 시나리오 구성
![image](https://user-images.githubusercontent.com/75685520/147651907-801d16b7-9bcb-4311-8579-f8f06ae0033f.png)

# 화면 설계 - 프로토타입
회원 프로토타입

<p align="center">
 <img src="https://user-images.githubusercontent.com/75685520/147653437-948048f5-b21d-476b-849c-c6e8cabc3143.jpg" width="300" height="300">
 <img src="https://user-images.githubusercontent.com/75685520/147653455-44b25277-852c-4c3a-92d1-f2f83b9e2696.jpg" width="300" height="300">
 <img src="https://user-images.githubusercontent.com/75685520/147668650-af41bb27-b959-4c7a-9512-162de9c751e9.jpg" width="300" height="300">
 <img src="https://user-images.githubusercontent.com/75685520/147668719-c9558d69-d876-426e-8604-caad5834715a.jpg" width="300" height="300">
 <img src="https://user-images.githubusercontent.com/75685520/147668732-52ea56cb-6fe6-47e2-a7cb-e27462f9d354.jpg" width="300" height="300">
 <img src="https://user-images.githubusercontent.com/75685520/147668793-5bbd226a-169a-4c76-888d-78a17cf18bbd.jpg" width="300" height="300">
 <img src="https://user-images.githubusercontent.com/75685520/147668746-b7e4aad0-d983-4297-aa91-07c4ecb95be9.jpg" width="300" height="300">
 <img src="https://user-images.githubusercontent.com/75685520/147669082-bc2a2c4d-d5fa-4d5c-88d7-8bd1e1392907.jpg" width="300" height="300">
 <img src="https://user-images.githubusercontent.com/75685520/147669179-99eb2424-7211-4dcc-ae0f-e70ca096586e.jpg" width="300" height="300">
</p>


관리자 프로토타입

<p align="center">
 <img src="https://user-images.githubusercontent.com/75685520/147653437-948048f5-b21d-476b-849c-c6e8cabc3143.jpg" width="300" height="300">
 <img src="https://user-images.githubusercontent.com/75685520/147653455-44b25277-852c-4c3a-92d1-f2f83b9e2696.jpg" width="300" height="300">
 <img src="https://user-images.githubusercontent.com/75685520/147668650-af41bb27-b959-4c7a-9512-162de9c751e9.jpg" width="300" height="300">
 <img src="https://user-images.githubusercontent.com/75685520/147668719-c9558d69-d876-426e-8604-caad5834715a.jpg" width="300" height="300">
 <img src="https://user-images.githubusercontent.com/75685520/147668732-52ea56cb-6fe6-47e2-a7cb-e27462f9d354.jpg" width="300" height="300">
 <img src="https://user-images.githubusercontent.com/75685520/147668793-5bbd226a-169a-4c76-888d-78a17cf18bbd.jpg" width="300" height="300">
 <img src="https://user-images.githubusercontent.com/75685520/147669179-99eb2424-7211-4dcc-ae0f-e70ca096586e.jpg" width="300" height="300">
 <img src="https://user-images.githubusercontent.com/75685520/147669372-3dcd0cba-1853-468b-a563-96991a765a81.jpg" width="300" height="300">
 <img src="https://user-images.githubusercontent.com/75685520/147669458-bb7d82fa-9b87-4637-a1be-3919398f50e6.jpg" width="300" height="300">
 <img src="https://user-images.githubusercontent.com/75685520/147669535-76ba1300-7585-4f17-a871-978b8970478e.jpg" width="300" height="300">
 <img src="https://user-images.githubusercontent.com/75685520/147669630-15cb4204-c905-4032-b884-d43bac0a6c56.jpg" width="300" height="300">
 <img src="https://user-images.githubusercontent.com/75685520/147669697-ce91984a-85ef-48a7-9951-389286b15f9f.jpg" width="300" height="300">
 <img src="https://user-images.githubusercontent.com/75685520/147669754-140567f1-328f-45a3-9ae9-847e7ca12c50.jpg" width="300" height="300">
 <img src="https://user-images.githubusercontent.com/75685520/147669814-7b8c0658-69fd-4b48-a099-6ecc8ea56523.jpg" width="300" height="300">
 <img src="https://user-images.githubusercontent.com/75685520/147669887-59d16613-619e-4c84-b292-465dca31b7b2.jpg" width="300" height="300">
 <img src="https://user-images.githubusercontent.com/75685520/147669941-70475e23-b548-4c45-b217-af7915913158.jpg" width="300" height="300">
</p>
