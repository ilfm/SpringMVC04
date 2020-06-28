<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<link rel="stylesheet" type ="text/css" href="<%=cp %>/css/mainStyle.css">

<%-- <link rel="stylesheet" type="text/css" href="<%=cp %>/css/MenuStyle.css"> --%>

</head>
<body>


<!-- ------------------------------
  #28.RegList.jsp
  - 지역 리스트 출력 페이지
  - 일반 직원이 접근하는  지역 리스트 출력 페이지
    (관리자가 접근하는 직우너 리스트 출력페이지는  EmployeeList.jsp 로 구성)
    
  ---------------------------------  
  -->


<div>

	<!--메뉴 영역  -->
	<div>
		<c:import url="EmployeeMenu.jsp"></c:import>
	
	</div>

	<!--콘텐츠 영역  -->
	<div id="content">
	
		<h1>[직원 정보] > [직원 리스트]</h1>
		<hr />
	
	</div>
	
	
	<br /><br />
	
	<!--  ----------------------------------------------------
	EMPLOYEEID NAME SSN BIRTHDAY LUNAR LUNARNAEM
	TELEPHONE  DEPARTMENTID DEPARTMENTNAME                 
	POSITIONID POSITIONNAME                   
	REGIONNAME                       
	BASICPAY   EXTRAPAY        PAY      GRADE	
	----------------------------------------------------------
	-->
	<table class="table" id="customers">
		<tr>
			<th>번호</th>
			<th>이름</th>
			<th>주민번호</th>
			<th>생년월일</th>
			<th>양/음력</th>
			<th>전화번호</th>
			<th>지역</th>
			<th>부서</th>
			<th>직위</th>

		</tr>
		<tr>
			<td>5</td>
			<td>신재민</td>
			<td>910116</td>
			<td>1991-01-16</td>
			<td>양력</td>
			<td>010-2231-9316</td>
			<td>서울</td>
			<td>개발부</td>
			<td>사원</td>
		</tr> 
	<!-- <tr>
			<td>1</td>
			<td>임효림</td>
			<td>960730</td>
			<td>1996-07-30</td>
			<td>양력</td>
			<td>010-3226-2488</td>
			<td>서울</td>
			<td>개발부</td>
			<td>사원</td>
			<td>1500000</td>
			<td>150000</td>
			<td>1650000</td>
			<td>관리자</td>
			
			<td><button type="button" class="btn">수정</button></td>
			<td><button type="button" class="btn">삭제</button></td>

		</tr> -->
<%-- 		
		<c:forEach var="employee" items="${employeeList }">
			<tr>
				<td>${employee.employeeId }</td>
				<td>${employee.name }</td>
				<td>${employee.ssn }-*******</td>
				<td>${employee.birthday }</td>
				<td>${employee.lunarName }</td>
				<td>${employee.telephone }</td>
				<td>${employee.regionName }</td>
				<td>${employee.departmentName }</td>
				<td>${employee.positionName }</td>
				<td>${employee.basicPay }</td>
				<td>
					<fmt:formatNumber value="${employee.basicPay }" groupingUsed="true"></fmt:formatNumber><!-- 숫자 형식으로 포맷을 맞춰서 하겠다.  -->
				</td>
				<td>${employee.extraPay }</td>
				<td>
					<fmt:formatNumber value="${employee.extraPay }" groupingUsed="true"></fmt:formatNumber>
				</td>
				<td>${employee.pay }</td>
				<td>
					<fmt:formatNumber value="${employee.pay }" groupingUsed="true"></fmt:formatNumber>
				</td>
				<td>${employee.grade==0 ? "관리자" : "일반사원" }</td>
				
				<td><button type="button" class="btn updateBtn" 
					value="${employee.employeeId }">수정</button></td>
				<td><button type="button" class="btn deleteBtn" value=${employee.employeeId }>삭제</button></td>
	
			</tr>	
		</c:forEach>
 --%>
		
		
		
		
	</table>
	

</div>
<!-- 회사 소개 및 어플리케이션 소개   -->
<div id="footer">
</div>


</body>
</html>