

public class EmbaralhadorInverterParImpar implements Embaralhador {

    @Override
    public String embaralhar(String palavra) {
    	char[] chars = palavra.toCharArray();
        for (int i = 0; i < chars.length - 1; i += 2) {
            char temp = chars[i];
            chars[i] = chars[i + 1];
            chars[i + 1] = temp;
        }
        return new String(chars);
    }

}
	