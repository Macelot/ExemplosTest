package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Word {

    private ArrayList<String> words;
    private String secret;
    private Character[] associated;
    private ArrayList<String[]> secretsWords;

    private String[] decrypt;
    private String special="#";
    private int sizeAlfa=44;

    /** Método atribuir valor em words
     * @param file String - arquivo a ser carregado. Este arquivo tem um dicionário de palavras
     * */
    public void setWords(String file){
        words = new ArrayList<String>();
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            line = br.readLine();
            words.add(line);
            while(line!=null){
                words.add(line);
                line = br.readLine();
            }
        }catch(Exception e){
            System.out.println("Error on load file "+file);
        }
    }

    /** Método atribuir valor em secret
     * @param file String - arquivo a ser carregado. Este arquivo tem frase secreta
     * @return void
     * */
    public void setSecret(String file){
        secret="";
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while(true){
                line = br.readLine();
                if(line!=null){
                    secret = secret + line+special;;
                }else{
                    break;
                }
            }
        }catch(Exception e){
            System.out.println("Error on load file "+file);
        }
    }


    /** Método atribuir valor em associated
     * @return void
     * */
    public void setAssociated(){
        associated = new Character[sizeAlfa];
        for (int i = 0; i<26;i++){
            associated[i]=(char)(i+65);
        }
        associated[26]='.';
        associated[27]=',';
        associated[28]=';';
        associated[29]='!';
        associated[30]='?';
        associated[31]='Á';
        associated[32]='Ã';
        associated[33]='À';
        associated[34]='Â';
        associated[35]='É';
        associated[36]='Ê';
        associated[37]='Í';
        associated[38]='Ó';
        associated[39]='Õ';
        associated[40]='Ô';
        associated[41]='Ú';
        associated[42]='Ü';
        associated[43]='Ç';
    }

    public String getSecret() {
        return secret;
    }

    public Character[] getAssociated() {
        return associated;
    }

    public void setAssociated(Character[] associated) {
        this.associated = associated;
    }


    //demais getter e setter
    //...

    /** Método para descriptografar
     * @param key é a chave que achamos que pode descriptografar
     * @param message é a mensagem criptografada
     * @return String com a mensagem descriptografada usando a key informada por parâmetro
     * */
    public String makeKey(int key, String message){
        String result="";
        Character charPos='\0';
        int pos=0;
        for (int i=0;i<message.length();i++){
            charPos=message.charAt(i);
            for (int j=0; j<associated.length;j++){
                if(charPos.equals(associated[j])){
                    pos=j+key;
                    if(pos>=sizeAlfa){
                        pos-=sizeAlfa;
                    }
                    result=result+associated[pos];
                }
            }
        }
        return result;
    }

    /** Método para descriptografar automaticamente
     * @return ArrayList de String[]s com as mensagens descriptografadas, cada palavra separada em uma posição de um array[]
     * */
    public ArrayList<String[]> makekey0to5(){
        //test key 0 up to 5
        secretsWords = new ArrayList<String[]>();
        //split message
        String[] secretWords = getSecret().split(special);
        String[] line;
        for (int i=0;i<5;i++){
            //cript all words with the key i
            line = new String[secretWords.length];
            for (int j=0;j<secretWords.length;j++){
                //System.out.println("Word "+secretWords[j]);
                line[j] = makeKey(i,secretWords[j]);
                //System.out.println("Word "+ line[j]);
            }
            secretsWords.add(line);
        }
        return secretsWords;
    }

    /** Método semelhante ao método makeKey
     * @param x int - chave a ser testada
     * @return array de Strings
     * */
    public String[] makekeyx(int x){
        //test only key X
        String[] secretWords = getSecret().split(special);
        String[] line;
        line = new String[secretWords.length];
        for (int j=0;j<secretWords.length;j++){
            line[j] = makeKey(x,secretWords[j]);
        }
        return line;
    }

    /** Método para pegar o valor de decrypt. O valor será concatenado em uma única String
     * @return String
     * */
    public String getDecrypt() {
        String temp="";
        for (String a: decrypt) {
            temp+=a+" ";
        }
        return temp;
    }

}
