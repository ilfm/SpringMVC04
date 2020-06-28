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
<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
<script type="text/javascript">

	$(function()
	{
			
		// 바보 짓 
		/* if(delCheck != 0)
		{
			$(".deleteBtn").attr('disabled',true);		
			console.log("delcheck",${region.delCheck });
			
		}		
		 */	
		
		$(".updateBtn").click(function()
		{
			
			//alert("수정 버튼 클릭 ~!!");
			
			//업데이트 시 employeeId 만 보내면 된다.
			$(location).attr("href","regionupdateform.action?regionId=" + $(this).val());
			
		});
		
		//삭제 버튼 클릭 시 액션 처이
		$(".deleteBtn").click(function()
		{
			//테스트
			//alert($(this).val());
			
			if(confirm("현재 선택한 데이터를 정말 삭제하시겠습니까?"))
			{
				$(location).attr("href","regiondelete.action?regionId="+ $(this).val());				
				
			}			
			
		});		
		
	});

</script>
<%-- <link rel="stylesheet" type="text/css" href="<%=cp %>/css/MenuStyle.css"> --%>

</head>
<body>


<!-- ------------------------------
  #14.EmployeeList.jsp
  - 직원 리스트 출력 페이지
  - 관리자가 접근하여 직원 리스트 출력 페이지
    (일반 직원이 접근하는 직원 리스트 출력 페이지는 Emplist.jsp 로 구성할 예정)
    
  ---------------------------------  
  -->


<div>

	<!--메뉴 영역  -->
	<div>
		<c:import url="EmployeeMenu.jsp"></c:import>
	
	</div>

	<!--콘텐츠 영역  -->
	<div id="content">
	
		<h1>[부서 관리] > [부서 리스트]</h1>
		<hr />
		
		<div>
			<form action="">
				<input type="button" value="부서 추가" class="btn" 
					onclick="location.href='departmentinsertform.action'"/>
			</form>
		</div>
	
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
			<th>부서번호</th>
			<th>부서이름</th>
	
			
			<th>수정</th>
			<th>삭제</th>
			

		</tr>
<!-- 		<tr>
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
		<c:forEach var="department" items="${departmentList }">
			<tr>
				<td>${department.departmentId }</td>
				<td>${department.departmentName }</td>
				<%-- <td>${region.delCheck }</td> --%>
				<td><button type="button" class="btn updateBtn" 
					value="${department.departmentId }">수정</button></td>
				<td><button type="button" class="btn deleteBtn" value=${department.departmentId }
					 ${department.delcheck > 0 ? "disabled" : ""}
				>삭제</button></td>
				
			</tr>	
		</c:forEach>
		
		
		
		
	</table>
	

</div>
<!-- 회사 소개 및 어플리케이션 소개   -->
<div id="footer">
</div>


</body>
</html>