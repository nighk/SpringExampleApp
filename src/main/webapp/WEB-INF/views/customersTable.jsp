<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<style>
			table {
			    font-family: arial, sans-serif;
			    border-collapse: collapse;
			    width: 100%;
			}
			
			td, th {
			    border: 1px solid #dddddd;
			    text-align: left;
			    padding: 8px;
			}
			
			tr:nth-child(even) {
			    background-color: #dddddd;
			}
		</style>
	</head>
	
	<body>
		<table>
			<tr>
				<th>Id</th>
				<th>First Name</th>
				<th>Last Name</th>	
			</tr>
			
			<c:forEach items="${customers}" var="element"> 
			  	<tr>
			  		<td>${element.getId()}</td>
				    <td>${element.getFirstName()}</td>
				    <td>${element.getLastName()}</td>  
			    </tr>
			</c:forEach>
		</table>
	</body>
</html>