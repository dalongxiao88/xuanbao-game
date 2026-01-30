package org.come.until;

import java.security.interfaces.RSAPrivateKey;
import javax.crypto.Cipher;
import java.security.interfaces.RSAPublicKey;

public class ClientPrivateInSercateUntil
{
    private static RSAPublicKey pubKey;
    private static Cipher cipher;
    static byte[] cipherText;
    private static RSAPrivateKey privKey;
    
    public static byte[] crateSercate(String mes) throws Exception {
        ClientPrivateInSercateUntil.cipher = Cipher.getInstance("RSA");
        ClientPrivateInSercateUntil.pubKey = (RSAPublicKey)SecreateUntil.getPublicKey(SecreateUntil.PUBLIC_PRIVATE_SERCATE);
        ClientPrivateInSercateUntil.cipher.init(1, ClientPrivateInSercateUntil.pubKey);
        return ClientPrivateInSercateUntil.cipherText = ClientPrivateInSercateUntil.cipher.doFinal(mes.getBytes());
    }
    
    private static String getSendMes(byte[] process) throws Exception {
        ClientPrivateInSercateUntil.pubKey = (RSAPublicKey)SecreateUntil.getPublicKey(SecreateUntil.PUBLIC_PRIVATE_SERCATE);
        (ClientPrivateInSercateUntil.cipher = Cipher.getInstance("RSA")).init(2, ClientPrivateInSercateUntil.pubKey);
        byte[] plainText = ClientPrivateInSercateUntil.cipher.doFinal(process);
        return new String(plainText);
    }
}
