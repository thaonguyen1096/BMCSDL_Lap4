package application;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.digest.DigestUtils;

public class aes {
	static Cipher cipher;

//	public static void main(String[] args) throws Exception {
//		String k = "1412503";
////		SecretKey secretKey = new SecretKeySpec(DigestUtils.md5(k), "AES");
////		cipher = Cipher.getInstance("AES");
//
//		String plainText = "AES Symmetric Encryption Decryption";
//		System.out.println("Plain Text Before Encryption: " + plainText);
//		
//		//Base64.Encoder encoder = Base64.getEncoder();
//		//String encryptedText = encoder.encodeToString(encryptedByte);
//		
//		byte[] encryptedText = encrypt(plainText, k);
//		System.out.println("Encrypted Text After Encryption: " + encryptedText);
//
//		String decryptedText = decrypt(encryptedText, k);
//		System.out.println("Decrypted Text After Decryption: " + decryptedText);
//	}

	public static byte[] encrypt(String plainText, String k)
			throws Exception {
		cipher = Cipher.getInstance("AES");
		SecretKey secretKey = new SecretKeySpec(DigestUtils.sha256(k), "AES");
		byte[] plainTextByte = plainText.getBytes();
		//cipher.in.init(Cipher.ENCRYPT_MODE, secretKey, params);
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		byte[] encryptedByte = cipher.doFinal(plainTextByte);
		//Base64.Encoder encoder = Base64.getEncoder();
		//String encryptedText = encoder.encodeToString(encryptedByte);
		return encryptedByte;
	}

	public static String decrypt(byte[] encryptedText, String k)
			throws Exception {
		cipher = Cipher.getInstance("AES");
		SecretKey secretKey = new SecretKeySpec(DigestUtils.sha256(k), "AES");
		//Base64.Decoder decoder = Base64.getDecoder();
		//byte[] encryptedTextByte = decoder.decode(encryptedText);
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		byte[] decryptedByte = cipher.doFinal(encryptedText);
		String decryptedText = new String(decryptedByte);
		return decryptedText;
	}
}