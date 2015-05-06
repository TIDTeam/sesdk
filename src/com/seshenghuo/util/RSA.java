/**
 * 
 */
package com.seshenghuo.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.seshenghuo.logger.L;

/**
 * @author carlli
 * 
 */
public class RSA {

	private static final String ALGORITHM = "RSA";
	private static final String SIGNATURE_ALGORITHM = "MD5withRSA";
	private static final int KEY_LENGTH = 1024;
	private static final byte[] DEFAULT_SEED = "seshenghuo.com".getBytes();
	
	private static final String KEY_PATH = Util.getRuntimePath() + "security/rsa/";
	private static final String PUK_SUFFIX = ".puk.key";
	private static final String PRK_SUFFIX = ".prk.key";
	
	static{
		init();
	}

	/**
	 * 
	 */
	private RSA() {
		// TODO Auto-generated constructor stub
	}
	
	private static void init(){
		File file = new File(KEY_PATH);
		
		if(!file.exists()){
			file.mkdirs();
		}
		
		setSeed(DEFAULT_SEED);
	}

	private static void generateKey(final byte[] seed) {
		try {
			String hex = Convert.bytes2hex(seed);
			
			File prkFile = new File(KEY_PATH + hex + PRK_SUFFIX);
			File pukFile = new File(KEY_PATH + hex + PUK_SUFFIX);
			
			
			if(!prkFile.exists() || !pukFile.exists()){	
				
				if(!prkFile.createNewFile()){
					L.error(RSA.class, "generateKey()", "PRK.NEW", "Create private key failed! ");
				}
				
				if(!prkFile.createNewFile()){
					L.error(RSA.class, "generateKey()", "PUK.NEW", "Create public key failed! ");
				}
				

				final KeyPairGenerator keyGen = KeyPairGenerator
						.getInstance(ALGORITHM);
				final SecureRandom sr = new SecureRandom();
				
				sr.setSeed(seed);
				
				keyGen.initialize(KEY_LENGTH, sr);
				final KeyPair key = keyGen.generateKeyPair();
				
				ObjectOutputStream pukos = new ObjectOutputStream(new FileOutputStream(pukFile));
				pukos.writeObject(key.getPublic());
				pukos.flush();
				pukos.close();
				
				ObjectOutputStream prkos = new ObjectOutputStream(new FileOutputStream(prkFile));
				prkos.writeObject(key.getPrivate());
				prkos.flush();
				prkos.close();
				
				
				L.info(RSA.class, "generateKey()", "INFO", "Generate Key: " + hex);
				//System.out.println("Generate Key: " + hex);
			}else{
				L.info(RSA.class, "generateKey()", "INFO", "Use History Key: " + hex);
				//System.out.println("Use History Key: " + hex);
			}
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			L.error(RSA.class, "generateKey(byte[])", "NoSuchAlgorithmException",
					e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			L.error(RSA.class, "generateKey(byte[])", "IOException",
					e.getMessage());
		}
	}

	private static void setSeed(final byte[] seed){
		generateKey(seed);
	}
	
	public static String encrypt(final byte[] seed, final String origin) {
		byte[] cipher = null;
		String hex = null;

		try {
			final Cipher c = Cipher.getInstance(ALGORITHM);
			c.init(Cipher.ENCRYPT_MODE, RSAKey.getPublicKey(seed));
			cipher = c.doFinal(origin.getBytes());
			hex = Convert.bytes2hex(cipher);

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			L.error(RSA.class, "encrypt(byte[])", "NoSuchAlgorithmException",
					e.getMessage());
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			L.error(RSA.class, "encrypt(byte[])", "NoSuchPaddingException",
					e.getMessage());
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			L.error(RSA.class, "encrypt(byte[])", "InvalidKeyException",
					e.getMessage());
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			L.error(RSA.class, "encrypt(byte[])", "IllegalBlockSizeException",
					e.getMessage());
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			L.error(RSA.class, "encrypt(byte[])", "BadPaddingException",
					e.getMessage());
		}

		return hex;
	}
	
	public static String encrypt(final long seed, final String origin) {
		return encrypt(Convert.long2bytes(seed), origin);
	}
	
	public static String encrypt(final String seed, final String origin) {
		return encrypt(seed.getBytes(), origin);
	}
	
	public static String encrypt(final String origin) {
		return encrypt(DEFAULT_SEED, origin);
	}

	public static String decrypt(final byte[] seed, final String encryptData) {
		byte[] cipher = null;
		String origin = null;

		try {
			final Cipher c = Cipher.getInstance(ALGORITHM);
			c.init(Cipher.DECRYPT_MODE, RSAKey.getPrivateKey(seed));
			byte[] b = Convert.hex2bytes(encryptData);

			L.info(RSA.class, "decrypt()", "INFO", "Data Bytes: " + b.length);

			cipher = c.doFinal(b);

			origin = new String(cipher);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			L.error(RSA.class, "decrypt(byte[])", "NoSuchAlgorithmException",
					e.getMessage());
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			L.error(RSA.class, "decrypt(byte[])", "NoSuchPaddingException",
					e.getMessage());
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			L.error(RSA.class, "decrypt(byte[])", "InvalidKeyException",
					e.getMessage());
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			L.error(RSA.class, "decrypt(byte[])", "IllegalBlockSizeException",
					e.getMessage());
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			L.error(RSA.class, "decrypt(byte[])", "BadPaddingException",
					e.getMessage());
		}

		return origin;
	}
	
	public static String decrypt(final long seed, final String encryptData) {
		return decrypt(Convert.long2bytes(seed), encryptData);
	}
	
	public static String decrypt(final String seed, final String encryptData) {
		return decrypt(seed.getBytes(), encryptData);
	}
	
	public static String decrypt(final String encryptData) {
		return decrypt(DEFAULT_SEED, encryptData);
	}
	
	public static String sign(final byte[] seed, final byte[] data) {
		String sign = null;

		try {
			Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
			signature.initSign(RSAKey.getPrivateKey(seed));
			signature.update(data);
			sign = Convert.bytes2hex(signature.sign());
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			// e1.printStackTrace();
			L.error(RSA.class, "sign(byte[])", "NoSuchAlgorithmException",
					e1.getMessage());
		} catch (InvalidKeyException e1) {
			// TODO Auto-generated catch block
			// e1.printStackTrace();
			L.error(RSA.class, "sign(byte[])", "InvalidKeyException", e1.getMessage());
		} catch (SignatureException e1) {
			// TODO Auto-generated catch block
			// e1.printStackTrace();
			L.error(RSA.class, "sign(byte[])", "SignatureException", e1.getMessage());
		}

		return sign;
	}
	public static String sign(final long seed, final byte[] data) {
		return sign(Convert.long2bytes(seed), data);
	}
	
	public static String sign(final String seed, final byte[] data) {
		return sign(seed.getBytes(), data);
	}
	
	public static String sign(final byte[] data) {
		return sign(DEFAULT_SEED, data);
	}
	
	public static String sign(final byte[] seed, final String src) {
		return sign(seed, src.getBytes());
	}
	
	public static String sign(final long seed, final String src) {
		return sign(Convert.long2bytes(seed), src);
	}
	
	public static String sign(final String seed, final String src) {
		return sign(seed.getBytes(), src);
	}
	
	public static String sign(final String src) {
		return sign(DEFAULT_SEED, src);
	}
	
	public static boolean verify(final byte[] seed, final byte[] data, final String sign) {
		boolean verify = false;

		try {
			Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
			signature.initVerify(RSAKey.getPublicKey(seed));
			signature.update(data);

			verify = signature.verify(Convert.hex2bytes(sign));
		}  catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			// e1.printStackTrace();
			L.error(RSA.class, "verify(byte[])", "NoSuchAlgorithmException",
					e1.getMessage());
		} catch (InvalidKeyException e1) {
			// TODO Auto-generated catch block
			// e1.printStackTrace();
			L.error(RSA.class, "verify(byte[])", "InvalidKeyException",
					e1.getMessage());
		} catch (SignatureException e1) {
			// TODO Auto-generated catch block
			// e1.printStackTrace();
			L.error(RSA.class, "verify(byte[])", "SignatureException",
					e1.getMessage());
		}

		return verify;
	}
	
	public static boolean verify(final long seed, final byte[] data, final String sign) {
		return verify(Convert.long2bytes(seed), data, sign);
	}
	
	public static boolean verify(final String seed, final byte[] data, final String sign) {
		return verify(seed.getBytes(), data, sign);
	}
	
	public static boolean verify(final byte[] data, final String sign) {
		return verify(DEFAULT_SEED, data, sign);
	}

	public static boolean verify(final byte[] seed, final String src, final String sign) {
		return verify(seed, src.getBytes(), sign);
	}
	
	public static boolean verify(final long seed, final String src, final String sign) {
		return verify(Convert.long2bytes(seed), src, sign);
	}
	
	public static boolean verify(final String seed, final String src, final String sign) {
		return verify(seed.getBytes(), src, sign);
	}
	
	public static boolean verify(final String src, final String sign) {
		return verify(DEFAULT_SEED, src, sign);
	}
	
	private static final class RSAKey{
		private static final PublicKey getPublicKey(final byte[] seed){
			setSeed(seed);
			
			String hex = Convert.bytes2hex(seed);
			
			try {
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(KEY_PATH + hex + PUK_SUFFIX));				
				final PublicKey key = (PublicKey)in.readObject();				
				in.close();
				
				final X509EncodedKeySpec x509 = new X509EncodedKeySpec(key.getEncoded());
				final KeyFactory kf = KeyFactory.getInstance(ALGORITHM);				
				final PublicKey x509_pub = kf.generatePublic(x509);
				
				return x509_pub;
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				L.error(RSAKey.class, "getPublicKey()", "FileNotFoundException", e.getMessage());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				L.error(RSAKey.class, "getPublicKey()", "IOException", e.getMessage());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				L.error(RSAKey.class, "getPublicKey()", "ClassNotFoundException", e.getMessage());
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				L.error(RSAKey.class, "getPublicKey()", "NoSuchAlgorithmException", e.getMessage());
			} catch (InvalidKeySpecException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				L.error(RSAKey.class, "getPublicKey()", "InvalidKeySpecException", e.getMessage());
			}
			
			return null;
		}
		
		private static final PrivateKey getPrivateKey(final byte[] seed){
			setSeed(seed);
			
			String hex = Convert.bytes2hex(seed);
			
			try {
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(KEY_PATH + hex + PRK_SUFFIX));				
				final PrivateKey key = (PrivateKey)in.readObject();
				in.close();
				
				final PKCS8EncodedKeySpec pkcs8 = new PKCS8EncodedKeySpec(key.getEncoded());
				final KeyFactory kf = KeyFactory.getInstance(ALGORITHM);
				final PrivateKey pkcs8_pri = kf.generatePrivate(pkcs8);
				
				return pkcs8_pri;
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				L.error(RSAKey.class, "getPrivateKey()", "FileNotFoundException", e.getMessage());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				L.error(RSAKey.class, "getPrivateKey()", "IOException", e.getMessage());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				L.error(RSAKey.class, "getPrivateKey()", "ClassNotFoundException", e.getMessage());
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				L.error(RSAKey.class, "getPrivateKey(byte[])",
						"NoSuchAlgorithmException", e.getMessage());
			} catch (InvalidKeySpecException e) {
				// TODO Auto-generated catch block
				L.error(RSAKey.class, "getPrivateKey(byte[])",
						"InvalidKeySpecException", e.getMessage());
			}
			
			return null;
		}
	}
}
