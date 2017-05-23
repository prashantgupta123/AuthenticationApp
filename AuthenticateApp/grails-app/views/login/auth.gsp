<!DOCTYPE html>
<html>
<head>
    <title>Facebook Login JavaScript Example</title>
    <meta charset="UTF-8">
</head>

<body>
<script>

    (function (d, s, id) {
        var js, fjs = d.getElementsByTagName(s)[0];
        if (d.getElementById(id)) return;
        js = d.createElement(s);
        js.id = id;
        js.src = "//connect.facebook.net/en_US/sdk.js";
        fjs.parentNode.insertBefore(js, fjs);
    }(document, 'script', 'facebook-jssdk'));

    function statusChangeCallback(response) {
        console.log('statusChangeCallback');
        console.log(response);
        // The response object is returned with a status field that lets the
        // app know the current login status of the person.
        // Full docs on the response object can be found in the documentation
        // for FB.getLoginStatus().
        if (response.status === 'connected') {
            // Logged into your app and Facebook.
            testAPI();
        } else {
            // The person is not logged into your app or we are unable to tell.
            document.getElementById('status').innerHTML = 'Please log ' + 'into this app.';
        }
    }

    function checkLoginState() {
        FB.getLoginStatus(function (response) {
            statusChangeCallback(response);
        });
    }

    function checkLogoutState() {
        FB.logout(function (response) {
            console.log("Logout Successfully")
        });
    }

    window.fbAsyncInit = function () {
        FB.init({
            appId: '484631385201709',
            cookie: true,  // enable cookies to allow the server to access
                           // the session
            xfbml: true,  // parse social plugins on this page
            version: 'v2.8' // use graph api version 2.8
        });
        FB.getLoginStatus(function (response) {
            statusChangeCallback(response);
        });
    }

    function testAPI() {
        console.log('Welcome!  Fetching your information.... ');
        FB.api('/me?fields=id,name,first_name,last_name,picture,email', function (response) {
            console.log(response)
            console.log('Successful login for: ' + response.name);
            document.getElementById('status').innerHTML =
                'Thanks for logging in, ' + response.name + '!';
        });
    }
</script>

<div id="status">
</div>

<fb:login-button scope="public_profile,email" onlogin="checkLoginState();">
</fb:login-button>
<fb:login-button scope="public_profile,email" onlogin="checkLogoutState();">
</fb:login-button>

<div class="container-fluid">
    <div class="row">
        <div class="col-xs-12">
            <g:render template="/login"/>
        </div>
    </div>
</div>

<oauth2:connect provider="facebook" id="google-connect-link">Facebbok</oauth2:connect>

Logged with facebook?
<oauth2:ifLoggedInWith provider="facebook">yes</oauth2:ifLoggedInWith>
<oauth2:ifNotLoggedInWith provider="facebook">no</oauth2:ifNotLoggedInWith>

</body>
</html>