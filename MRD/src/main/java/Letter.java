import java.util.ArrayList;

//该类负责在内存中临时存储从整个词典中解析单个字母，以便写入磁盘文件。
//(^[a-zA-Z]+)\n
public class Letter {
    //字母的名称，这里统一用大写
    char name;

    //以该字母开头的单词集合
    ArrayList<Word> words;

    //该单词集合的容量,即words.length
    int num;


    public Letter(char name, ArrayList<Word> words, int num) {
        this.name = name;
        this.words = words;
        this.num = num;
    }

}
