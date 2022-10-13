package sparta.project.realboard.Jwt;

public interface JwtProperties {
    String ACCESS_SECRET_KEY = "sehunsehunaccess"; // 우리 서버만 알고 있는 비밀값
    String REFRESH_SECRET_KEY = "sehunsehunrefresh"; // 우리 서버만 알고 있는 비밀값
    int ACCESS_EXPIRATION_TIME = 86400000; // 10일 (1/1000초)
    int REFRESH_EXPIRATION_TIME = 864000000; // 10일 (1/1000초)
    String TOKEN_PREFIX = "Bearer ";
    String ACCESS_HEADER_STRING = "Authorization";
    String REFRESH_HEADER_STRING = "RefreshAuthorization";
}