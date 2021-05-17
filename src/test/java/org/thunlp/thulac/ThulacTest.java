package org.thunlp.thulac;

import org.junit.Test;
import org.thunlp.thulac.data.TaggedWord;

import java.io.IOException;
import java.util.List;

/*
 *
 * Data Lab - graph database organization.
 *
 */

/**
 * @author Yc-Ma
 * @PACKAGE_NAME: org.thunlp.thulac
 * @Description: TODO
 * @date 2021/5/14 9:20
 */
public class ThulacTest {

    private final static String TEXT = "5月14日消息，据国外媒体报道，在宣布将暂停接受比特币支付一天后，电动汽车制造商特斯拉CEO埃隆·马斯克（Elon Musk）于当地时间周四在推特上表示，他正在与狗狗币的开发人员合作，以提高系统交易效率。\n" +
            "据悉，这条推文让狗狗币的价格大幅上涨。Coingecko网站的数据显示，狗狗币在榜单上排名第四，24小时涨幅达10.4%，目前的价格为0.47美元。\n" +
            "此前一天，也就是本周三，马斯克曾发推文称，出于环保考虑，特斯拉将暂停接受比特币支付。\n" +
            "他表示：“我们对比特币开采和交易中快速增加的化石燃料使用感到担忧，尤其是煤炭，它的温室气体排放量在所有燃料中是最严重的。从很多层面上看，加密货币都是个好主意，我们也相信它有光明的未来，但这不能以环境为代价。”\n" +
            "他还补充表示，特斯拉不会出售其拥有的比特币，它打算在采矿转向可持续能源后再用比特币来进行交易。\n" +
            "据悉，马斯克一直是狗狗币和比特币等加密货币的支持者。此前，他曾多次在推特上提及狗狗币和其他加密货币。\n" +
            "今年3月份，他在推特上表示，特斯拉现在接受比特币支付。也就是说，该公司的电动汽车可以使用比特币购买。然而，面对一些环保人士和投资者的强烈抗议，该公司的立场发生了转变。\n" +
            "今年5月9日，马斯克旗下的另一家公司SpaceX宣布将接受狗狗币支付。当日，马斯克表示，该公司计划明年第一季度发射DOGE-1登月任务，该任务的费用将通过狗狗币支付。\n" +
            "至于特斯拉是否会接受狗狗币作为新的支付方式，目前还不得而知。但马斯克本周二在推特上发起了投票，询问粉丝是否希望特斯拉接受狗狗币作为新的支付方式。\n" +
            "结果，有将近80%人投票支持特斯拉接受狗狗币作为支付方式，而约20%的人表示不支持。\n" +
            "狗狗币创建于2013年，标志是一只柴犬，它是一种加密货币，很像比特币，可以在去中心化的网络上进行点对点交易。\n" +
            "在马斯克的大力支持下，狗狗币的价格在今年飙升了超过10000%，但它本身也陷入了争议之中。";

    @Test
    public void split_1() throws IOException {
        String result = Thulac.split(TEXT, true);
        System.out.println(result);
    }

    @Test
    public void split_2() throws IOException {
        String result = Thulac.split(TEXT, false);
        System.out.println(result);
    }

    @Test
    public void split_3() throws IOException {
        // 模型文件所在的目录
        String modelDir = "models/";
        // 用于分隔单词和标签的分隔符
        char separator = '_';
        // 用户指定的字典的可选文件名
        String userDict = "dic/user_defined.txt";
        // 是否将繁体中文转换为简体中文
        boolean useT2S = true;
        // 是否只输出分词结果【也可以输出词语和词性信息】
        boolean segOnly = true;
        // 是否在处理时使用过滤器
        boolean useFilter = false;
        String result = Thulac.split(TEXT, segOnly,modelDir, separator, userDict, useT2S, useFilter);
        System.out.println(result);
    }

    @Test
    public void split_4() throws IOException {
        // 用于分隔单词和标签的分隔符
        char separator = '_';
        // 用户指定的字典的可选文件名
        String userDict = "dic/user_defined.txt";
        // 是否将繁体中文转换为简体中文
        boolean useT2S = true;
        // 是否只输出分词结果【也可以输出词语和词性信息】
        boolean segOnly = true;
        // 是否在处理时使用过滤器
        boolean useFilter = false;
        String result = Thulac.split(TEXT, segOnly, separator, userDict, useT2S, useFilter);
        System.out.println(result);
    }

    @Test
    public void split_5() throws IOException {
        // 用于分隔单词和标签的分隔符
        char separator = '_';
        // 用户指定的字典的可选文件名
        String userDict = "dic/user_defined.txt";
        // 是否将繁体中文转换为简体中文
        boolean useT2S = true;
        // 是否只输出分词结果【也可以输出词语和词性信息】
        boolean segOnly = false;
        // 是否在处理时使用过滤器
        boolean useFilter = false;
        String result = Thulac.split(TEXT, segOnly, separator, userDict, useT2S, useFilter);
        System.out.println(result);
    }

    @Test
    public void split_6() throws IOException {
        // 用户指定的字典的可选文件名
        String userDict = "dic/user_defined.txt";
        // 是否将繁体中文转换为简体中文
        boolean useT2S = true;
        // 是否在处理时使用过滤器
        boolean useFilter = false;
        List<TaggedWord> result = Thulac.split(TEXT, userDict, useT2S, useFilter);
        result.forEach(v-> System.out.println(v.getWord()+"    "+v.getTag()+"  "+v.getDescription()));
    }
}


