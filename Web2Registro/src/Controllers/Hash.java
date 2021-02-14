package Controllers;

public class Hash {

	/*En esta clase se desarrollara la funcion que permitira
	 * el hashing de la contraseña, en este caso se encriptara
	 * el string de la contraseña en MD5 */
	
	public static String getHash(String txt, String hashType) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest
                    .getInstance(hashType);
            byte[] array = md.digest(txt.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
                        .substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
	
	public static String md5(String txt) {
        return Hash.getHash(txt, "MD5");
    }
	
	/*Luego de haber desarrollado esto, en la clase del servlet
	 * se podra llamar a esta funcion para realizar el hashing */
	
}
