package com.zuluco.oculuz.payload.response;

public class TokenRefreshResponse {
  private String authToken;
  private String refreshToken;
  private String tokenType = "Bearer";

  public TokenRefreshResponse(String accessToken, String refreshToken) {
    this.authToken = accessToken;
    this.refreshToken = refreshToken;
  }

  public String getAuthToken() {
    return authToken;
  }

  public void setAuthToken(String token) {
    this.authToken = token;
  }

  public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  public String getTokenType() {
    return tokenType;
  }

  public void setTokenType(String tokenType) {
    this.tokenType = tokenType;
  }

}
