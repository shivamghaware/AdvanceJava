<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:forEach var="t" items="12 ,56, 78, 99" >
   In loop : 
	${t/2} <br/>
	
</c:forEach>