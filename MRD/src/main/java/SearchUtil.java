import java.util.ArrayList;

public class SearchUtil {
    static ArrayList<Letter> letterList = new ArrayList<Letter>();
    //搜索单词
    static void searchByWords(String aim){
        if(aim.length()>0){
            //目标单词的首字母
           char f= aim.charAt(0);
            int index=letterToIndex(f);
           if( index !=-1){
               Letter letter=letterList.get(index);
               for (Word word:letter.words
                    ) {if(word.name.contains(aim)){
                      showWordInfo(word);
               }

               }
           }
        }
        else {
            System.out.println("首字母必须为字母!!");
        }
    }
    //将大小写字母转化为int类型的序号,如果参数不属于字母字符，则 返回-1.
  static   private int letterToIndex(char c){
        int index=-1;
        //字符为大写
        if(Character.isUpperCase(c)){
            index=c-'A';
        }
        else if(Character.isLowerCase(c)){
            index=c-'a';
        }
        return index;
    }
    static   private void showWordInfo(Word word){
        System.out.println("单词名:"+word.name);
        System.out.println("音标:"+word.phSymbol);
        System.out.println("释义:"+word.meaning);
    }

    public void fuck(){

    }
}
