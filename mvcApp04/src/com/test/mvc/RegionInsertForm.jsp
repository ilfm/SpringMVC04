<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	//필터 쓰기 전까지 사용하기
	request.setCharacterEncoding("utf-8");
	String cp = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" type ="text/css" href="<%=cp %>/css/jquery-ui.css">

<!-- 제이 쿼리 코어 라이브러리  -->
<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
<!-- ※ 꼭 제이쿼리 코어 라이브러리 밑에  -->
<script type="text/javascript" src="<%=cp %>/js/jquery-ui.js"></script>
<script type="text/javascript">


	$(document).ready(function()
	{
		
		
		// 에러(span 엘리먼트) 안내 초기화
		$("#err").css("display","none")
		
		
		
		
		//직원 추가 버튼이 클릭 되었을 때 수행할 코드 처리
		$("#submitBtn").click(function()
		{
			//1.데이터 검사
			//-- 공란(입력항목 누락)이 있는지에 대한 여부 확인
			if($("#regionName").val() == "" )
			{
				$("#err").html("입력 항목이 누락 되었습니다.");
				$("#err").css("display","inline");
				return;// 액션 처리 중단
				
				
			}
			
			// 정규 표현식
			
			$("#regionForm").submit();
			
		});
		
		
		
		
	});
	

	
	



</script>




</head>
<body>


<div>

	<!-- 메뉴 영역 -->
	<div>
		<c:import url="EmployeeMenu.jsp"></c:import>
	</div>

	<!-- 콘텐츠 영역  -->
	<div id="content">
	
		<h1>[지역 관리] > [지역 정보 입력]</h1>
		<hr />
	
	
		<form action="regioninsert.action" method="post" id="regionForm">
			<table>
				<tr>
					<th>지역명</th>
					<td><input type="text" id="regionName" name="regionName" placeholder="지역명" /></td>
				</tr>
				
				<tr>
					<td colspan="2" align="center">
						<br /><br />
						<button  type="button" class="btn" id="submitBtn" style="width: 40%">지역추가</button>
						<button  type="button" class="btn" id="listBtn" style="width: 40%">지역리스트</button>
						
						<br />
						<span id="err" style="color:red; font-weigth:bold; display:none;">
						
						</span>
					</td>
				</tr>
				
			</table>
		</form>
	
	
	
	
	
	</div>

	<!-- 회사 소개 및 애플리케이션 소개   -->
	<div id="footer">
	</div>
	

</div>





</body>
</html>