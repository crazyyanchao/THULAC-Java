package org.thunlp.thulac;

import org.thunlp.thulac.cb.CBTaggingDecoder;
import org.thunlp.thulac.data.POCGraph;
import org.thunlp.thulac.data.TaggedWord;
import org.thunlp.thulac.io.IInputProvider;
import org.thunlp.thulac.io.IOutputHandler;
import org.thunlp.thulac.io.StringOutputHandler;
import org.thunlp.thulac.postprocess.*;
import org.thunlp.thulac.preprocess.ConvertT2SPass;
import org.thunlp.thulac.preprocess.IPreprocessPass;
import org.thunlp.thulac.preprocess.PreprocessPass;
import org.thunlp.thulac.util.IOUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

/**
 * The central class which acts as core of the THULAC API. It provides several
 * convenient methods make things easier for users.
 */
public class Thulac {
    /**
     * Run the segmentation program with argument {@code segOnly}, taking input from the
     * given {@link String} and return the segmented output as a {@link String}.
     *
     * @param input   The input {@link String}.
     * @param segOnly Whether to output only segments.
     * @return The segmented output as a {@link String}.
     * @throws IOException If one of the model files fails to load.
     */
    public static String split(String input, boolean segOnly) throws IOException {
        StringOutputHandler outputProvider = IOUtils.outputToString();
        IInputProvider inputProvider = IOUtils.inputFromString(input);
        split(inputProvider, outputProvider, segOnly);
        return outputProvider.getString();
    }

    /**
     * @param input     输入的被分词文本
     * @param useFilter 是否在处理时使用过滤器
     * @param userDict  用户指定的字典的可选文件名
     * @param useT2S    是否将繁体中文转换为简体中文
     * @return
     * @Description: TODO(分词接口)
     */
    public static List<TaggedWord> split(String input, String userDict, boolean useT2S, boolean useFilter) throws IOException {
        StringOutputHandler outputProvider = IOUtils.outputToString();
        IInputProvider inputProvider = IOUtils.inputFromString(input);
        split("models/", '_', userDict, useT2S, false, useFilter, inputProvider, outputProvider);
        String result = outputProvider.getString();
        List<TaggedWord> taggedWordList = new ArrayList<>();
        String[] results = result.split(" ");
        for (String word : results) {
            String[] strArray = word.split("_");
            if (strArray.length > 1) {
                String wd = strArray[0];
                String tag = strArray[1];
                taggedWordList.add(new TaggedWord(wd, tag));
            }
        }
        return taggedWordList;
    }

    /**
     * @param input     输入的被分词文本
     * @param useFilter 是否在处理时使用过滤器
     * @param userDict  用户指定的字典的可选文件名
     * @param useT2S    是否将繁体中文转换为简体中文
     * @return
     * @Description: TODO(分词接口)
     */
    public static List<TaggedWord> split(String input, List<String> userDict, String modelDir, char separator, boolean segOnly, boolean useT2S, boolean useFilter) throws IOException {
        StringOutputHandler outputProvider = IOUtils.outputToString();
        IInputProvider inputProvider = IOUtils.inputFromString(input);
        splitWithUserDics(modelDir, separator, userDict.stream().distinct().collect(Collectors.toList()), useT2S, segOnly, useFilter, inputProvider, outputProvider);
        String result = outputProvider.getString();
        List<TaggedWord> taggedWordList = new ArrayList<>();
        String[] results = result.split(" ");
        for (String word : results) {
            String[] strArray = word.split(String.valueOf(separator));
            if (strArray.length > 1) {
                String wd = strArray[0];
                String tag = strArray[1];
                taggedWordList.add(new TaggedWord(wd, tag));
            }
        }
        return taggedWordList;
    }

    /**
     * @param input     输入的被分词文本
     * @param modelDir  模型文件所在的目录
     * @param segOnly   是否只输出分词结果【也可以输出词语和词性信息】
     * @param separator 用于分隔单词和标签的分隔符
     * @param useFilter 是否在处理时使用过滤器
     * @param userDict  用户指定的字典的可选文件名
     * @param useT2S    是否将繁体中文转换为简体中文
     * @return
     * @Description: TODO(分词接口)
     */
    public static String split(String input, boolean segOnly, String modelDir, char separator, String userDict, boolean useT2S, boolean useFilter) throws IOException {
        StringOutputHandler output = IOUtils.outputToString();
        IInputProvider inputProvider = IOUtils.inputFromString(input);
        split(modelDir, separator, userDict, useT2S, segOnly, useFilter, inputProvider, output);
        return output.getString();
    }

    /**
     * @param input     输入的被分词文本
     * @param segOnly   是否只输出分词结果【也可以输出词语和词性信息】
     * @param separator 用于分隔单词和标签的分隔符
     * @param useFilter 是否在处理时使用过滤器
     * @param userDict  用户指定的字典的可选文件名
     * @param useT2S    是否将繁体中文转换为简体中文
     * @return
     * @Description: TODO(分词接口)
     */
    public static String split(String input, boolean segOnly, char separator, String userDict, boolean useT2S, boolean useFilter) throws IOException {
        StringOutputHandler output = IOUtils.outputToString();
        IInputProvider inputProvider = IOUtils.inputFromString(input);
        split("models/", separator, userDict, useT2S, segOnly, useFilter, inputProvider, output);
        return output.getString();
    }

    /**
     * @param input     输入的被分词文本
     * @param segOnly   是否只输出分词结果【也可以输出词语和词性信息】
     * @param separator 用于分隔单词和标签的分隔符
     * @param useFilter 是否在处理时使用过滤器
     * @param userDict  用户指定的字典的可选文件名
     * @param useT2S    是否将繁体中文转换为简体中文
     * @return
     * @Description: TODO(分词接口)
     */
    public static String split(String input, boolean segOnly, char separator, List<String> userDict, boolean useT2S, boolean useFilter) throws IOException {
        StringOutputHandler output = IOUtils.outputToString();
        IInputProvider inputProvider = IOUtils.inputFromString(input);
        splitWithUserDics("models/", separator, userDict.stream().distinct().collect(Collectors.toList()), useT2S, segOnly, useFilter, inputProvider, output);
        return output.getString();
    }

    /**
     * Run the segmentation program with argument {@code segOnly}, taking input from the
     * given {@link File} and output the segmented return to a given {@link File}.<br>
     * This method returns directly if either {@code inputFile} or {@code outputFile}
     * is null.
     *
     * @param inputFile  The name of the input file.
     * @param outputFile The name of the output file.
     * @param segOnly    Whether to output only segments.
     * @throws IOException If one of the model files fails to load or either the input file or the output
     *                     file is {@code null}.
     */
    public static void split(String inputFile, String outputFile, boolean segOnly)
            throws IOException {
        if (inputFile == null || outputFile == null) {
            return;
        }
        IInputProvider input = IOUtils.inputFromFile(inputFile);
        IOutputHandler output = IOUtils.outputToFile(outputFile);
        split(input, output, segOnly);
    }

    /**
     * Run the segmentation program with argument {@code segOnly}, taking input from the
     * given {@link File} and output the segmented return to a given {@link File}.
     *
     * @param input   The input {@link File}.
     * @param output  The output {@link File}.
     * @param segOnly Whether to output only segments.
     * @throws IOException If one of the model files fails to load or either the input file or the output
     *                     file is {@code null}.
     */
    public static void split(File input, File output, boolean segOnly)
            throws IOException {
        if (input == null) {
            throw new FileNotFoundException("input == null!");
        }
        if (output == null) {
            throw new FileNotFoundException("output == null!");
        }
        IInputProvider inputProvider = IOUtils.inputFromFile(input);
        IOutputHandler outputHandler = IOUtils.outputToFile(output);
        split(inputProvider, outputHandler, segOnly);
    }

    /**
     * Run the segmentation program with argument {@code segOnly} and default values
     * for all others.
     *
     * @param input   The {@link IInputProvider} instance to provide input.
     * @param output  The {@link IOutputHandler} instance to handle output.
     * @param segOnly Whether to output only segments.
     * @throws IOException If I/O of either {@code input}, {@code output} or one of the model files
     *                     resulted in an exception.
     */
    public static void split(IInputProvider input, IOutputHandler output, boolean segOnly)
            throws IOException {
        split("models/", '_', null, false, segOnly, false, input, output);
    }

    /**
     * Run the segmentation program with full arguments.
     *
     * @param modelDir  The directory under which the model files are located.
     * @param separator The separator to use to separate words and tags.
     * @param userDict  The optional file name of the user-specified dictionary.
     * @param useT2S    Whether to transfer traditional Chinese to simplified Chinese before
     *                  segmentation.
     * @param segOnly   Whether to output only segments.
     * @param useFilter Whether to use filters while processing.
     * @param input     The {@link IInputProvider} instance to provide input.
     * @param output    The {@link IOutputHandler} instance to handle output.
     * @throws IOException If I/O of either {@code input}, {@code output} or one of the model files
     *                     resulted in an exception.
     */
    public static void split(
            String modelDir, char separator, String userDict,
            boolean useT2S, boolean segOnly, boolean useFilter,
            IInputProvider input, IOutputHandler output) throws IOException {
        try {
            input.onProgramStart();
            output.onProgramStart();

            // segmentation
            CBTaggingDecoder taggingDecoder = new CBTaggingDecoder();
            taggingDecoder.threshold = segOnly ? 0 : 10000;
            String prefix = modelDir + (segOnly ? "cws_" : "model_c_");
            taggingDecoder.loadFiles(prefix + "model.bin",
                    prefix + "dat.bin",
                    prefix + "label.txt");
            taggingDecoder.setLabelTrans();

            // preprocess passes
            List<IPreprocessPass> pre = new ArrayList<>();
            pre.add(new PreprocessPass());
            if (useT2S) {
                pre.add(new ConvertT2SPass(modelDir + "t2s.dat"));
            }

            // postprocess passes
            List<IPostprocessPass> post = new ArrayList<>();
            post.add(new DictionaryPass(modelDir + "ns.dat", "ns", false));
            post.add(new DictionaryPass(modelDir + "idiom.dat", "i", false));
            post.add(new DictionaryPass(modelDir + "singlepun.dat", "w", false));
            post.add(new TimeWordPass());
            post.add(new DoubleWordPass());
            post.add(new SpecialPass());
            post.add(new NegWordPass(modelDir + "neg.dat"));
            if (userDict != null) {
                post.add(new DictionaryPass(userDict, "uw", true, "UTF-8"));
            }
            if (useFilter) {
                post.add(new FilterPass(modelDir + "xu.dat", modelDir + "time.dat"));
            }

            // main loop
            List<TaggedWord> words = new Vector<>();
            POCGraph graph = new POCGraph();
            for (List<String> lineSegments = input.provideInput();
                 lineSegments != null;
                 lineSegments = input.provideInput()) {
                output.handleLineStart();
                for (String raw : lineSegments) {
                    for (IPreprocessPass pass : pre) {
                        raw = pass.process(raw, graph);
                    }
                    taggingDecoder.segment(raw, graph, words);
                    for (IPostprocessPass pass : post) {
                        pass.process(words);
                    }

                    output.handleLineSegment(words, segOnly, separator);
                }
                output.handleLineEnd();
            }
        } finally { // close resources even when program crashes
            input.onProgramEnd();
            output.onProgramEnd();
        }
    }

    /**
     * Run the segmentation program with full arguments.
     *
     * @param modelDir  The directory under which the model files are located.
     * @param separator The separator to use to separate words and tags.
     * @param userDict  The optional file name of the user-specified dictionary.
     * @param useT2S    Whether to transfer traditional Chinese to simplified Chinese before
     *                  segmentation.
     * @param segOnly   Whether to output only segments.
     * @param useFilter Whether to use filters while processing.
     * @param input     The {@link IInputProvider} instance to provide input.
     * @param output    The {@link IOutputHandler} instance to handle output.
     * @throws IOException If I/O of either {@code input}, {@code output} or one of the model files
     *                     resulted in an exception.
     */
    public static void splitWithUserDics(
            String modelDir, char separator, List<String> userDict,
            boolean useT2S, boolean segOnly, boolean useFilter,
            IInputProvider input, IOutputHandler output) throws IOException {
        try {
            input.onProgramStart();
            output.onProgramStart();

            // segmentation
            CBTaggingDecoder taggingDecoder = new CBTaggingDecoder();
            taggingDecoder.threshold = segOnly ? 0 : 10000;
            String prefix = modelDir + (segOnly ? "cws_" : "model_c_");
            taggingDecoder.loadFiles(prefix + "model.bin",
                    prefix + "dat.bin",
                    prefix + "label.txt");
            taggingDecoder.setLabelTrans();

            // preprocess passes
            List<IPreprocessPass> pre = new ArrayList<>();
            pre.add(new PreprocessPass());
            if (useT2S) {
                pre.add(new ConvertT2SPass(modelDir + "t2s.dat"));
            }

            // postprocess passes
            List<IPostprocessPass> post = new ArrayList<>();
            post.add(new DictionaryPass(modelDir + "ns.dat", "ns", false));
            post.add(new DictionaryPass(modelDir + "idiom.dat", "i", false));
            post.add(new DictionaryPass(modelDir + "singlepun.dat", "w", false));
            post.add(new TimeWordPass());
            post.add(new DoubleWordPass());
            post.add(new SpecialPass());
            post.add(new NegWordPass(modelDir + "neg.dat"));
            if (userDict != null) {
                post.add(new DictionaryPass(userDict, "uw", true, "UTF-8"));
            }
            if (useFilter) {
                post.add(new FilterPass(modelDir + "xu.dat", modelDir + "time.dat"));
            }

            // main loop
            List<TaggedWord> words = new Vector<>();
            POCGraph graph = new POCGraph();
            for (List<String> lineSegments = input.provideInput();
                 lineSegments != null;
                 lineSegments = input.provideInput()) {
                output.handleLineStart();
                for (String raw : lineSegments) {
                    for (IPreprocessPass pass : pre) {
                        raw = pass.process(raw, graph);
                    }
                    taggingDecoder.segment(raw, graph, words);
                    for (IPostprocessPass pass : post) {
                        pass.process(words);
                    }

                    output.handleLineSegment(words, segOnly, separator);
                }
                output.handleLineEnd();
            }
        } finally { // close resources even when program crashes
            input.onProgramEnd();
            output.onProgramEnd();
        }
    }

    //    private static CBTaggingDecoder TAGGING_DECODER;
    private static List<IPreprocessPass> PRE;
    private static List<IPostprocessPass> POST;
    private final static List<String> USER_DEFINE_DIC_LIST = new ArrayList<>();

    /**
     * @param
     * @return
     * @Description: TODO(初始化静态加载模型文件和用户自定义词典)
     */
    private static void init(List<String> userDict, boolean useT2S, boolean useFilter) throws IOException {
        String modelDir = "models/";
//        if (TAGGING_DECODER == null || PRE == null || POST == null) {
        if (PRE == null || POST == null) {
//            boolean segOnly = false;
            // init
//            TAGGING_DECODER = new CBTaggingDecoder();
            PRE = new ArrayList<>();
            POST = new ArrayList<>();

            // segmentation
//            TAGGING_DECODER.threshold = 10000;
            String prefix = "models/model_c_";
//            TAGGING_DECODER.loadFiles(prefix + "model.bin",
//                    prefix + "dat.bin",
//                    prefix + "label.txt");
//            TAGGING_DECODER.setLabelTrans();

            // preprocess passes
            PRE.add(new PreprocessPass());

            // postprocess passes
            POST.add(new DictionaryPass(modelDir + "ns.dat", "ns", false));
            POST.add(new DictionaryPass(modelDir + "idiom.dat", "i", false));
            POST.add(new DictionaryPass(modelDir + "singlepun.dat", "w", false));
            POST.add(new TimeWordPass());
            POST.add(new DoubleWordPass());
            POST.add(new SpecialPass());
            POST.add(new NegWordPass(modelDir + "neg.dat"));
        }

        /*
         * 更新参数
         * */
        ConvertT2SPass t2SPass = new ConvertT2SPass(modelDir + "t2s.dat");
        if (useT2S) {
            if (!PRE.contains(t2SPass)) {
                PRE.add(t2SPass);
            }
        } else {
            PRE.remove(t2SPass);
        }
        if (userDict != null) {
            userDict.removeAll(USER_DEFINE_DIC_LIST);
            if (!userDict.isEmpty()) {
                USER_DEFINE_DIC_LIST.addAll(userDict);
                POST.add(new DictionaryPass(userDict, "uw", true, "UTF-8"));
            }
        }
        FilterPass filterPass = new FilterPass(modelDir + "xu.dat", modelDir + "time.dat");
        if (useFilter) {
            if (!POST.contains(filterPass)) {
                POST.add(filterPass);
            }
        } else {
            POST.remove(filterPass);
        }
    }

    /**
     * @param
     * @return
     * @Description: TODO(分词)
     */
    public static void splitWithUserDics(
            char separator,
            boolean segOnly,
            IInputProvider input, IOutputHandler output) throws IOException {
        try {
            input.onProgramStart();
            output.onProgramStart();

            // init
            CBTaggingDecoder TAGGING_DECODER = new CBTaggingDecoder();

            // segmentation
            TAGGING_DECODER.threshold = 10000;
            String prefix = "models" + File.separator + "model_c_";
            TAGGING_DECODER.loadFiles(prefix + "model.bin",
                    prefix + "dat.bin",
                    prefix + "label.txt");
            TAGGING_DECODER.setLabelTrans();

            // main loop
            List<TaggedWord> words = new Vector<>();
            POCGraph graph = new POCGraph();
            for (List<String> lineSegments = input.provideInput();
                 lineSegments != null;
                 lineSegments = input.provideInput()) {
                output.handleLineStart();
                for (String raw : lineSegments) {
                    for (IPreprocessPass pass : PRE) {
                        raw = pass.process(raw, graph);
                    }
                    TAGGING_DECODER.segment(raw, graph, words);
                    for (IPostprocessPass pass : POST) {
                        pass.process(words);
                    }

                    output.handleLineSegment(words, segOnly, separator);
                }
                output.handleLineEnd();
            }
        } finally { // close resources even when program crashes
            input.onProgramEnd();
            output.onProgramEnd();
        }
    }

    /**
     * @param input 输入的被分词文本
     * @return
     * @Description: TODO(分词接口)
     */
    public static List<TaggedWord> splitWithCache(String input, List<String> userDicts, boolean useT2S, boolean useFilter) throws IOException {
        init(userDicts, useT2S, useFilter);
        StringOutputHandler outputProvider = IOUtils.outputToString();
        IInputProvider inputProvider = IOUtils.inputFromString(input);
        splitWithUserDics('_', false, inputProvider, outputProvider);
        String result = outputProvider.getString();
        List<TaggedWord> taggedWordList = new ArrayList<>();
        String[] results = result.split(" ");
        for (String word : results) {
            String[] strArray = word.split("_");
            if (strArray.length > 1) {
                String wd = strArray[0];
                String tag = strArray[1];
                taggedWordList.add(new TaggedWord(wd, tag));
            }
        }
        return taggedWordList;
    }

    /**
     * @param
     * @return
     * @Description: TODO(清空缓存对象)
     */
    public static void clearCache() {
//        TAGGING_DECODER = null;
        PRE = null;
        POST = null;
        USER_DEFINE_DIC_LIST.clear();
    }
}

