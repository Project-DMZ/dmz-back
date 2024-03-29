package com.dmz.global.config;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * packageName    : com.dmz.global.config
 * fileName       : JasyptConfig
 * author         : MinKyu Park
 * date           : 2024-01-03
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-01-03        MinKyu Park       최초 생성
 */
@Configuration
public class JasyptConfig {

	@Value("${jasypt.encryptor.password}")
	private String encryptKey;

	/**
	 * String encryptor string encryptor.
	 *
	 * @return the string encryptor
	 */
	@Bean("jasyptStringEncryptor")
	public StringEncryptor stringEncryptor(){
		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
		SimpleStringPBEConfig config = new SimpleStringPBEConfig();
		config.setPassword(encryptKey);
		config.setPoolSize("1");
		config.setAlgorithm("PBEWithMD5AndDES");
		config.setStringOutputType("base64");
		config.setKeyObtentionIterations("1000");
		config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
		encryptor.setConfig(config);
		return encryptor;
	}
}