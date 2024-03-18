package com.fptu.estate;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import java.util.Locale;
import java.util.TimeZone;
import javax.crypto.SecretKey;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@SecurityScheme(name = "Authorization", scheme = "bearer", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
@OpenAPIDefinition(servers = {@Server(url = "/", description = "Default Server URL")})

public class EstateApplication {

	public static void main(String[] args) {
//				Tạo key dùng mã hóa cho token
//		SecretKey key = Jwts.SIG.HS256.key().build();
//		String strKey = Encoders.BASE64.encode(key.getEncoded());
//		System.out.println("key: " + strKey);
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		init();
//		System.out.println(timeZone);
		SpringApplication.run(EstateApplication.class, args);
	}

	private static void init() {
//		Locale.setDefault();
	}

}
