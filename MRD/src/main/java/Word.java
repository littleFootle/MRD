

//该类负责在内存临时存储从字母中解析具体的单词，以便写入磁盘文件。
//读音、词性、每个词义、例句
public class Word {
    //该单词的拼写
    String name;

    //该单词的音标
    String phSymbol;


    //该单词的词义和例句
    String meaning;

    public Word(String name, String phSymbol, String meaning) {
        this.name = name;
        this.phSymbol = phSymbol;
        this.meaning = meaning;
    }
}
