<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Login</title>
</head>

<body>

<div class="container-fluid">
    <div class="row">
        <div class="col-xs-12">
            <g:render template="login"/>
            <a href="https://accounts.google.com/o/oauth2/auth?redirect_uri=http%3A%2F%2Flocalhost:8080%2Fgoogle%2Fsuccess&response_type=code&client_id=567572298196-u81lih94ll824i0n4f7n4l7h524k53kb.apps.googleusercontent.com&scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.profile&approval_prompt=force&access_type=offline"> SignInWithGoogle </a>
        </div>
    </div>
</div>

</body>
</html>