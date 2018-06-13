<%-- <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<style>
.error {
	color: #ff0000;
}

.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
</head>

<body>
	<h2>User SignUp Form - JSR303 @Valid example</h2>

	<form:form method="POST" commandName="userDto"
		action="${pageContext.request.contextPath}/userSignUp/user/initiate">
		<form:errors path="*" cssClass="errorblock" element="div" />
		<table>
			<tr>
				<td>Email :</td>
				<td><form:input path="email" /></td>
				<td><form:errors path="email" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Password :</td>
				<td><form:input path="password" /></td>
				<td><form:errors path="password" cssClass="error" /></td>
			</tr>

			<tr>
				<td>Confirm Password :</td>
				<td><form:input path="confirmPassword" /></td>
				<td><form:errors path="confirmPassword" cssClass="error" /></td>
			</tr>
			<tr>
				<td colspan="3"><input type="submit" /></td>
			</tr>
		</table>
	</form:form>

</body>
</html> --%>