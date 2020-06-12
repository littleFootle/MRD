import java.util.ArrayList;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//解析文本的工具类
public class ParseUtil {

    //匹配每个字母部分开头正则表达式
    static String lStartEx = "[A-Z],[a-z]";


    //匹配每个字母部分结束的正则表达式
    static String lEndRegEx = "";

    //匹配单词名称以及其余内容的正则表达式
    static String wName = "^[\\u3000|\\u0020|\\u00A0]{2,}([A-Za-z,[\\u3000|\\u0020|\\u00A0]-]+)\\n[\\u3000|\\u0020|\\u00A0]{2,}(.+?)$";

    //匹配单词音标的正则表达式
    static String wSymbol = "(/ .*?;{1,1}.*?/)";


    //匹配单词词义和例句的正则表达式
    static String wMeaning = "";


    static ArrayList<Letter> letterList = new ArrayList<Letter>();

    static String lEndFlag="EOFLETTER";

    static String lFinishEx=lEndFlag;

    static String crlf="\r\n";

    static String lStartFlag="LETTER:";

    static String lBeginEx="LETTER:[A-Z]";

    //解析单词
    static void ParseWords(LinkedList<String> letters) {
        // 匹配单词名称
        Pattern pName = Pattern.compile(wName, Pattern.MULTILINE);
        //匹配单词音标
        Pattern pSymbol = Pattern.compile(wSymbol, Pattern.MULTILINE);
        //匹配单词词义例句等
        Pattern pMeaning = Pattern.compile(wMeaning, Pattern.MULTILINE);
        int i = 0;
        for (String letterCon : letters
        ) {

            String name = "";
            String symbol = "";
            String meaning = "";

            // 现在创建 matcher 对象
            Matcher mName = pName.matcher(letterCon);

            //Matcher mMeaning = pMeaning.matcher(letter);
            ArrayList<Word> wordList = new ArrayList<Word>();

            while (mName.find()) {
                //System.out.println(mName.group(1));
                Matcher mSymbol = pSymbol.matcher(mName.group(2));
                if (mSymbol.find()) {
                    symbol = mSymbol.group(1);
                }
                meaning=mSymbol.replaceFirst("");
                name = (mName.group(1));
                Word word = new Word(name, symbol, meaning);
                wordList.add(word);
            }

            Letter letter = new Letter((char) ('A' + i), wordList, wordList.size());
            letterList.add(letter);
            i++;

        }
        for (Letter letter : letterList
        ) {
            System.out.println(letter.name + ":" + letter.num+"个单词!");


            }

    }

    //判断是否到了一个字母的结束
    static boolean IsLetterEnd(String str) {
        return Pattern.matches(lEndRegEx, str);

    }

    //判断是否到了一个字母的开始
    static boolean IsLetterStart(String str) {
        return Pattern.matches(lStartEx, str);

    }


    static boolean IsLetterBegin(String str) {
        if(str==null){
            return false;
        }
        return Pattern.matches(lBeginEx, str);
    }

    static boolean IsLetterFinished(String str) {
        return Pattern.matches(lFinishEx, str);
    }




}
