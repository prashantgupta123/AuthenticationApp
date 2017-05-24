package authenticateapp

import grails.plugin.springsecurity.annotation.Secured
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.Method
import net.sf.json.JSON
import org.grails.web.json.JSONObject

@Secured("permitAll")
class GoogleController {

    def success(String code) {
        render "code" + code
        String googleaccesstoken = ""
        JSONObject googleJsonResponseForAccessToken
        def http = new HTTPBuilder('http://localhost:8080/')
        http.request(Method.POST, JSON) {
            uri.path = "https://accounts.google.com/o/oauth2/token"
            requestContentType = "application/x-www-form-urlencoded"
            /*body = {
                code = code,
                "redirect_uri": "https%3A%2F%2Fdevelopers.google.com%2Foauthplayground",
                "client_id": "567572298196-u81lih94ll824i0n4f7n4l7h524k53kb.apps.googleusercontent.com",
                "client_secret": "jLjFhYAIwxpcXBvKbMrC3G0x",
                "scope": "",
                "grant_type": authorization_code
            }*/

            response.success = { resp, json ->
                println "POST response status: ${resp.statusLine}"
                googleJsonResponseForAccessToken = json
                googleaccesstoken = json.access_token
            }
        }
        JSONObject userInfo
        http.request(Method.GET) {
            uri.path = "https://www.googleapis.com/userinfo/v2/me"
            uri.query = [accesstoken: googleaccesstoken]
            headers.'Authorization' = "Bearer ${googleaccesstoken}"
            response.success = { resp, json ->
                println "Get response status: ${resp.statusLine}"
                userInfo = json
            }
        }
        render "User Name  ::  " + userInfo.name
        render "User Id  ::  " + userInfo.id
    }

    private String getAccessToken(String authorizationCode) {
        return null;
    }

    private Map getUserProfile(String accessToken) {
        return new LinkedHashMap();
    }
}
