<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>User Information</title>
</head>
<body>

<h2>Submitted Student Information</h2>
   <table>
    <tr>
        <td>Email</td>
        <td>${email}</td>
    </tr>
    <tr>
        <td>Password</td>
        <td>${password}</td>
    </tr>
    <tr>
        <td>Confirm Password</td>
        <td>${confirmPassword}</td>
    </tr>
</table>  
</body>
</html>