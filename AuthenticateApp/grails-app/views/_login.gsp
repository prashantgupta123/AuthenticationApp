<div class="panel panel-primary" id="login-panel">
    <div class="panel-heading">
        <h3 class="panel-title">Login</h3>
    </div>

    <div class="panel-body">
        <g:form method="post">
            <div class="form-group form-group-email">
                <label for="loginUserName">Username*</label>
                <g:textField name="userName" required="" type="text" class="form-control" id="loginUserName" placeholder="Username"/>
                <div id="loginUserNameError"></div>
            </div>

            <div class="form-group form-group-password">
                <label for="loginPassword">Password*</label>
                <g:passwordField name="password" required="" type="password" class="form-control" id="loginPassword"
                                 placeholder="Password"/>
                <div id="loginPasswordError"></div>
            </div>

            <div class="form-group">
                <div class="checkbox">
                    <label>
                        <g:checkBox name="rememberMe" type="checkbox" value="Remember Me"/> Remember me
                    </label>
                </div>
            </div>

            <div class="form-group form-group-forget-password">
                <g:submitButton name="submit" id="loginSubmit" type="submit" class="btn btn-primary">Sign in</g:submitButton>
                <g:actionSubmit value="Facebook"></g:actionSubmit>
                <g:actionSubmit value="Google"></g:actionSubmit>
                <g:actionSubmit value="Github"></g:actionSubmit>
            </div>
        </g:form>
    </div>
</div>