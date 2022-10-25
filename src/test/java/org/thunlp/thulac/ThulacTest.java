package org.thunlp.thulac;

import org.junit.Test;
import org.thunlp.thulac.data.TaggedWord;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

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

    private static String TEXT = "與建議,時間過得好快呀。5月14日消息，据国外媒体报道，在宣布将暂停接受比特币支付一天后，电动汽车制造商特斯拉CEO埃隆·马斯克（Elon Musk）于当地时间周四在推特上表示，他正在与狗狗币的开发人员合作，以提高系统交易效率。\n" +
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
        TEXT = "與建議,時間過得好快呀";
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
        String userDict = "dic/user_defined.dic";
        // 是否将繁体中文转换为简体中文
        boolean useT2S = true;
        // 是否只输出分词结果【也可以输出词语和词性信息】
        boolean segOnly = true;
        // 是否在处理时使用过滤器
        boolean useFilter = false;
        String result = Thulac.split(TEXT, segOnly, modelDir, separator, userDict, useT2S, useFilter);
        System.out.println(result);
    }

    @Test
    public void split_4() throws IOException {
        // 用于分隔单词和标签的分隔符
        char separator = '_';
        // 用户指定的字典的可选文件名
        String userDict = "dic/user_defined.dic";
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
        String userDict = "dic/user_defined.dic";
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
        TEXT = "今年春天，奶奶家阳台上的花盆里不知怎么的，冒出了两株苦瓜树，我可从来没见过苦瓜树，忽然，一个想法在我脑海中隐隐出现了。为了观察苦瓜树，我每天中午一放了学就去奶奶家认真打量它。\n" +
                "\n" +
                "\n" +
                "\n" +
                "　　苦瓜树刚长出来的叶子是嫩绿的，上面长着一根白色的毛绒绒的小毛毛，没几天，叶子长大就变成碧绿的了，苦瓜树的叶子跟爬山虎的叶子长得很像，但苦瓜的叶子相对来说要比爬山虎的叶子软。\n" +
                "\n" +
                "\n" +
                "\n" +
                "　　随着叶子的长大，它身上的小毛毛也慢慢地消失了。\n" +
                "\n" +
                "\n" +
                "\n" +
                "　　苦瓜树的茎上跟新叶子差不多也长了白色的小毛毛，有些茎卷卷的，非常漂亮，茎的颜色是浅绿色的，看着很舒服。\n" +
                "\n" +
                "\n" +
                "\n" +
                "　　时间一天天过去了，苦瓜树也在缓缓长大。\n" +
                "\n" +
                "\n" +
                "\n" +
                "　　一天中午，放学后，我和往常一样到奶奶家观察苦瓜树，哎!还是没有长进，正当我苦恼的时候，忽然，一个小苦瓜在我眼前隐隐约约地出现了，呀!当时我还真有点不相信自己的眼睛，我拉开枝叶，一个深绿色的小苦瓜出现了，它细细的，身上长着又小又软的刺儿，它那尖尖的头上有一个跟小苦瓜一样颜色的花苞，小小的，不大引人注意。\n" +
                "\n" +
                "\n" +
                "\n" +
                "　　过了几天，小苦瓜的头上开了一朵小黄花，第二天，小黄花萎了，它的生命可真短啊!\n" +
                "\n" +
                "\n" +
                "\n" +
                "　　秋天渐渐来临了，小苦瓜越长越大，变成像翡翠一样的颜色了，它身上的小刺儿变成了一个个头圆圆的东西，那苦瓜又大又胖，挂在细细的茎上，好像随时都会掉下来似的。\n" +
                "\n" +
                "\n" +
                "\n" +
                "　　啊!好漂亮的一株苦瓜呀!";
        // 用户指定的字典的可选文件名
        String userDict = "dic/user_defined.dic";
        // 是否将繁体中文转换为简体中文
        boolean useT2S = true;
        // 是否在处理时使用过滤器
        boolean useFilter = false;
        List<TaggedWord> result = Thulac.split(TEXT, userDict, useT2S, useFilter);
        result.forEach(v -> System.out.println(v.getWord() + "    " + v.getTag() + "  " + v.getDescription()));
    }

    @Test
    public void split_7() throws IOException {
        TEXT = "Deprecated Gradle features were used in this build, making it incompatible with Gradle 7.0. 海德股份：首家民营AMC牌照，定增过会业务启动";
        // 用于分隔单词和标签的分隔符
        char separator = '_';
        // 用户指定的字典的可选文件名
        String[] userDicts = new String[]{"dic/user_defined.dic", "dic/user_defined_2.dic"};
//        String[] userDicts = new String[]{"dic/user_defined.dic"};
        // 是否将繁体中文转换为简体中文
        boolean useT2S = true;
        // 是否只输出分词结果【也可以输出词语和词性信息】
        boolean segOnly = false;
        // 是否在处理时使用过滤器
        boolean useFilter = false;
        String result = Thulac.split(TEXT, segOnly, separator, Arrays.asList(userDicts), useT2S, useFilter);
        System.out.println(result);
    }

    @Test
    public void split_8() throws IOException {
        TEXT = "葛洲坝2017年半年报点评：业绩符合预期，多元业务齐头并进";
        // 用于分隔单词和标签的分隔符
        char separator = '_';
        // 用户指定的字典的可选文件名
        // 加载大词典测试
//        String[] userDicts = new String[]{"dic/PRE_ORG_CN_DIC.txt"};
        String[] userDicts = new String[]{"dic/user_defined.dic"};
        // 是否将繁体中文转换为简体中文
        boolean useT2S = true;
        // 是否只输出分词结果【也可以输出词语和词性信息】
        boolean segOnly = false;
        // 是否在处理时使用过滤器
        boolean useFilter = false;
        String result = Thulac.split(TEXT, segOnly, separator, Arrays.asList(userDicts), useT2S, useFilter);
        System.out.println(result);
    }

    @Test
    public void markAsUsed() {
        /*
         * 求解步骤：
         * 1、二进制原码
         * 2、二进制反码
         * 3、二进制补码
         * 4、左移两位后的补码
         * 5、反码
         * 6、原码
         * 7、结果
         * */
        int index = 20 << 2;
        System.out.println(index);
    }

    @Test
    public void taggedWordWithUserdicList() throws IOException {
        /*
         * 初始化加载模型文件和用户自定义词典
         * */

        TEXT = "葛洲坝2017年半年报点评：业绩符合预期，多元业务齐头并进";
        // 用于分隔单词和标签的分隔符
        char separator = '_';
        // 用户指定的字典的可选文件名
        // 加载大词典测试
        String[] userDicts = new String[]{"dic/user_defined.dic"};
        // 是否将繁体中文转换为简体中文
        boolean useT2S = true;
        // 是否只输出分词结果【也可以输出词语和词性信息】
        boolean segOnly = false;
        // 是否在处理时使用过滤器
        boolean useFilter = false;

        // 模型文件所在的目录
        String modelDir = "models/";

        List<TaggedWord> result = Thulac.split(TEXT, Arrays.asList(userDicts), modelDir, separator, segOnly, useT2S, useFilter);
        result.forEach(v -> System.out.println(v.getWord() + "    " + v.getTag() + "  " + v.getDescription()));
    }

    @Test
    public void splitWithCache() throws IOException {
        /*
         * 初始化加载模型文件和用户自定义词典
         * */
        TEXT = "與建議,時間過得好快呀";

        // 加载大词典测试
        String[] userDicts = new String[]{"dic/user_defined.dic"};

        // 是否将繁体中文转换为简体中文
        boolean useT2S = true;
        // 是否在处理时使用过滤器
        boolean useFilter = true;

        /*
         * 使用缓存加载模型文件和用户自定义词典
         * 开始分词
         * */
        for (int i = 0; i < 10; i++) {
            if (i == 3) {
                useT2S = false;
                useFilter = true;
            }
            List<TaggedWord> result = Thulac.splitWithCache(TEXT, new ArrayList<>(Arrays.asList(userDicts)), useFilter);
            result.forEach(v -> System.out.println(v.getWord() + "    " + v.getTag() + "  " + v.getDescription()));
        }
        Thulac.clearCache();
    }

    @Test
    public void splitWithCache_02() throws Exception {
        /*
         * 初始化加载模型文件和用户自定义词典
         * */
        TEXT = "與建議,時間過得好快呀";

        // 加载大词典测试
        String[] userDicts = new String[]{"dic/user_defined.dic"};

        // 是否将繁体中文转换为简体中文
        boolean useT2S = true;
        // 是否在处理时使用过滤器
        boolean useFilter = true;

        /*
         * 使用缓存加载模型文件和用户自定义词典
         * 开始分词
         * */
        // 请求总数
        int clientTotal = 100;

        // 同时并发执行的线程数
        int threadTotal = 20;

        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();

                    List<TaggedWord> result = Thulac.splitWithCache(TEXT, new ArrayList<>(Arrays.asList(userDicts)), useFilter);
                    result.forEach(v -> System.out.println(v.getWord() + "    " + v.getTag() + "  " + v.getDescription()));

                    semaphore.release();
                } catch (Exception e) {
                    System.out.println("log.error exception:" + e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println("count:" + count);
    }

    @Test
    public void splitWithCache_03() throws IOException {
        /*
         * 初始化加载模型文件和用户自定义词典
         * */
        TEXT = "\uD86D\uDFA5\uD86D\uDFA5\uD86D\uDFA5\uD86D\uDFA5\uD86D\uDFA5撒打算撒打算王琦珼";
//        TEXT = "撒打算撒打算王琦珼，我説啊哪個不是的吧哈韓";

        // 加载大词典测试
        String[] userDicts = new String[]{"dic/user_defined.dic"};

        // 是否将繁体中文转换为简体中文
        boolean useT2S = true;
        // 是否在处理时使用过滤器
        boolean useFilter = true;
        List<TaggedWord> result = Thulac.splitWithCache(TEXT, new ArrayList<>(Arrays.asList(userDicts)), useFilter);
        result.forEach(v -> System.out.println(v.getWord() + "    " + v.getTag() + "  " + v.getDescription()));
    }

//    public static int count = 0; // 线程不安全

    // 原子锁-线程安全
    public static AtomicInteger count = new AtomicInteger(0);

    @Test
    public void splitWithCacheConcurrencyTest() throws Exception {
        // 请求总数
        int clientTotal = 5000;

        // 同时并发执行的线程数
        int threadTotal = 200;

        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (Exception e) {
                    System.out.println("log.error exception:" + e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println("count:" + count);
    }

    private static void add() {
//        count++;
        count.incrementAndGet();
    }

    @Test
    public void splitWithCache_04() throws IOException {
        /*
         * 初始化加载模型文件和用户自定义词典
         * */
        TEXT = "\uD86D\uDFA5\uD86D\uDFA5\uD86D\uDFA5\uD86D\uDFA5\uD86D\uDFA5撒打算撒打算王琦珼";
//        TEXT = "撒打算撒打算王琦珼，我説啊哪個不是的吧哈韓";
        System.out.println(TEXT);
        String reTEXT = new String(TEXT.getBytes("UTF-8") ,"UTF-8");
        char[] chars = reTEXT.toCharArray();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            String strCh;
//            if (i < chars.length && chars[i]=='\uD86D') {
            strCh = String.valueOf(chars[i] + chars[i + 1]);
//            } else {
//                strCh = String.valueOf(chars[i]);
//            }
            builder.append(strCh);
        }
    }

    @Test
    public void splitWithCache_05() {
        System.out.println(decodeUnicode("\uD86D\uDFA5"));
    }

    public String decodeUnicode(String dataStr) {
        int start = 0;
        int end;
        final StringBuffer buffer = new StringBuffer();
        while (start > -1) {
            end = dataStr.indexOf("\\u", start + 2);
            String charStr = "";
            if (end == -1) {
                charStr = dataStr.substring(start + 2, dataStr.length());
            } else {
                charStr = dataStr.substring(start + 2, end);
            }
            char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。
            buffer.append(new Character(letter).toString());
            start = end;
        }
        return buffer.toString();
    }
}

