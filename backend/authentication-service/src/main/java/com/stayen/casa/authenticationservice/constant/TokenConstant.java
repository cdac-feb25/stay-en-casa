package com.stayen.casa.authenticationservice.constant;

import java.util.List;

public class TokenConstant {
	private static final String CLASS_NAME = TokenConstant.class.getSimpleName();

	public static final String JWT_KEY_ALGORITHM_NAME = "HmacSHA256";

	public static final String TOKEN_TYPE = "tokenType";

	public static final String TOKEN_ISSUER = "StayEn.Casa";
	
	public static final String EMAIL = "email";
	
	public static final String DEVICE_ID = "deviceId";
	
	public static final String LOGOUT_MSG = "Logged out successfully !!!";
	
	public static final int SALTING_ROUND = 5;
	
	private static final List<String> EXCLUDED_PATH = List.of(
		"/auth/login", 
		"/auth/register"
	);
	
	private static final List<String> ONLY_REFRESH_TOKEN_PATH = List.of(
		"/auth/token/refresh"
	);
	
	public static boolean isPathExcluded(String incomingPath) {
		System.out.println(CLASS_NAME + " - Incoming Path : " + incomingPath);
		return EXCLUDED_PATH.stream().anyMatch((path) -> path.equals(incomingPath));
	}
	
	public static boolean isRefreshTokenPath(String incomingPath) {
		System.out.println(CLASS_NAME + " - Incoming Refresh Path : " + incomingPath);
		return ONLY_REFRESH_TOKEN_PATH.stream().anyMatch((path) -> path.equals(incomingPath));
	}
	
}
