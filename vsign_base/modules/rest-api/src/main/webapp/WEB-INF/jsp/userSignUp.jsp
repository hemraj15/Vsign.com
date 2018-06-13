<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>User Sign Up</title>
</head>
<body>

<h2>User Information</h2>
<form:form method="POST" action="/assessment-api/tests/userDtoSignUp">
   <table>
    <tr>
        <td><form:label path="email">Email</form:label></td>
        <td><form:input path="email" /></td>
    </tr>
    <tr>
        <td><form:label path="password">Password</form:label></td>
        <td><form:input path="password" /></td>
    </tr>
    <tr>
        <td><form:label path="confirmPassword">Confirm Password</form:label></td>
        <td><form:input path="confirmPassword" /></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="Submit"/>
        </td>
    </tr>
</table>  
</form:form>
</body>
</html>