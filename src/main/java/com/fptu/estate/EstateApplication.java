package com.fptu.estate;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import javax.crypto.SecretKey;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EstateApplication {

	public static void main(String[] args) {
//				Tạo key dùng mã hóa cho token
//		SecretKey key = Jwts.SIG.HS256.key().build();
//		String strKey = Encoders.BASE64.encode(key.getEncoded());
//		System.out.println("key: " + strKey);
		SpringApplication.run(EstateApplication.class, args);
	}

}
