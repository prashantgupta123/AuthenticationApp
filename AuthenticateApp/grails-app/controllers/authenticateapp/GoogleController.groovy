package authenticateapp

import com.ttn.dto.GoogleProfile
import grails.plugin.springsecurity.annotation.Secured
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.*
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestTemplate

@Secured("permitAll")
class GoogleController {

    def success(String code) {
        GoogleProfile googleProfile=getGoogleUserProfile(code);
        render googleProfile as grails.converters.JSON
    }

    def success1() {
        render("Hello")
    }

    private GoogleProfile getGoogleUserProfile(String authorization_Code) {
        try {
            MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>();
            form.add("code", authorization_Code);
            form.add("client_id", "567572298196-8k60dusmfaspbeijgoalslubjfk0hq9c.apps.googleusercontent.com");
            form.add("client_secret", "tognAiGwaceA-w_xZOyFxY5O");
            form.add("redirect_uri", "http://localhost:8080/google/success");
            form.add("grant_type", "authorization_code");
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            ParameterizedTypeReference<Map<String, Object>> map = new ParameterizedTypeReference<Map<String, Object>>() {
            };
            RestTemplate restTemplate = new RestTemplate();
            Map<String, Object> response = restTemplate
                    .exchange("https://www.googleapis.com/oauth2/v4/token", HttpMethod.POST, new HttpEntity<>(form, headers), map).getBody();
            if (response.containsKey("error")) {
                log.error("check_token returned error: " + response.get("error"));
                return null;
            }
            GoogleProfile googleProfile = null;
            if (response.containsKey("access_token")) {
                log.info("token received" + response.get("access_token"));
                googleProfile = userGoogleProfile((String) response.get("access_token"));
            }
            return googleProfile;
        } catch (Exception e) {
            log.error("Error in fetching user profile from Google");
            log.error("stacktrace", e);
            return null;
        }
    }

    private GoogleProfile userGoogleProfile(String accessToken) {
        String googleUrlProfileUrl = new StringBuilder("https://www.googleapis.com/userinfo/v2/me").append("?access_token=").append(accessToken).toString();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<GoogleProfile> responseEntity = null;
        try {
            log.debug("### Request for UserProfile from GOOGLE : ");
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + accessToken);
            HttpEntity<Object> httpEntity = new HttpEntity<>(headers);
            responseEntity = restTemplate.exchange(googleUrlProfileUrl, HttpMethod.GET, httpEntity,
                    GoogleProfile.class);
            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                GoogleProfile responseDTO = responseEntity.getBody();
                log.debug("[X] Received User Profile FromGoogle : " + responseDTO.toString());
                return responseDTO;
            }
        } catch (Exception e) {
        }

    }
}

