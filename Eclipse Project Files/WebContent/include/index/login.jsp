<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>					
					<div class="col-lg-12">
                        <h1>Login</h1>
                    	<hr/>
						<br/>
                    	<pre>
                        <form action="LoginServlet" method="post">
							Email Address: <input type="email" name="email"/>
							Password:      <input type="password" name="password"/>
								
							<input type="button" name="register" onclick="window.location='?index=register'" value="Register"/>    <input type="submit" name="reactivate" value="Reactivate"/>    <input type="submit" name="login" value="Login"/>
						</form>
						</pre>
                    </div>