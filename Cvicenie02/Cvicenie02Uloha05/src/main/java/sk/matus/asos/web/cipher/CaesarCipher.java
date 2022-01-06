/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.matus.asos.web.cipher;

/**
 *
 * @author Meidie
 */
public class CaesarCipher {

    private final int offset;

    public CaesarCipher(int offset) {
        this.offset = offset;
    }

    public String encrypt(String message) {
        return doCipher(message, offset);
    }

    public String decrypt(String message) {
        return doCipher(message, 26 - (offset % 26));
    }

    private String doCipher(String message, int offset) {

        char c;
        StringBuilder strBuilder = new StringBuilder();

        for (int i = 0; i < message.length(); i++) {
            c = message.charAt(i);

            if (Character.isLetter(c)) {
                c = (char) (message.charAt(i) + offset);
                if ((Character.isLowerCase(message.charAt(i)) && c > 'z')
                        || (Character.isUpperCase(message.charAt(i)) && c > 'Z')) {
                    c = (char) (message.charAt(i) - (26 - offset));
                }
            }
            strBuilder.append(c);
        }
        return strBuilder.toString();
    }
}
