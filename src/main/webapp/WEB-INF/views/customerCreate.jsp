<!DOCTYPE html>
<html>
	<head>
		
	</head>
	
	<body>
		<form action="/customer/create" method="POST" enctype='application/json'>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
			<div><label>First Name: <input type="text" name="firstName"/></label></div>
            <div><label>Last Name: <input type="text" name="lastName"/></label></div>
            <div><input type="submit" value="Create"/></div>
		</form>
	</body>
</html>