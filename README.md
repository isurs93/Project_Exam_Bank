# Project_Exam_Bank

mvc 패턴 기반
IDE : STS
Language : java, html, css, javascript /
Back-End : jsp / 
SQL : MySQL /
Sever : Apache Tomcat /

기출 문제 은행을 구현하였습니다.

회원가입, 로그인, 회원 정보 찾기, 회원 탈퇴 구현
선택한 문제 구매 및 관리 페이지, 마이 페이지 구현
공지 사항, QnA, 기출문제 게시판 구현
기출문제 응시, 오답 확인 페이지 구현
관리자 페이지 구현

유저는 회원 가입 후 로그인해야 기출문제 게시판에 접근 할 수 있습니다.
기출 문제 리스트에서 자신이 원하는 문제를 클릭하면 미리보기 창을 거쳐 결제창으로 넘어갑니다.
결제하면 마이페이지에서 자신이 결제한 문제를 볼 수 있고 풀어 볼 수 있습니다.

문제 응시와 동시에 타이머가 시작 되고 4개의 라디오 버튼에서 1개를 클릭해 답을 선택 합니다.
하나의 문제라도 답이 선택 되지 않으면 제출을 할 수 없습니다.

제출하면 틀린 문제 번호와 점수를 확인 할 수 있습니다.
바로 오답 문제를 확인 할 수 있으며 마이 페이지에서도 확인 할 수 있습니다.

QnA 게시판은 모든 유저가 글을 올릴 수 있고 수정과 삭제는 본인과 관리자만 할 수 있습니다.
공지 사항 게시판의 글의 CUD는 관리자만 접근 할 수 있습니다.
