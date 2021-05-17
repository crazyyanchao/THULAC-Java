package org.thunlp.thulac.data;
/*
 *
 * Data Lab - graph database organization.
 *
 */

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yc-Ma
 * @PACKAGE_NAME: org.thunlp.thulac.data
 * @Description: TODO
 * @date 2021/5/17 15:32
 */
public class WordNature {

    public static final Map<String, String> MAP = new HashMap<>();

    static {
        MAP.put("n","名词");
        MAP.put("np","人名");
        MAP.put("ns","地名");
        MAP.put("ni","机构名");
        MAP.put("nz","其它专名");
        MAP.put("m","数词");
        MAP.put("q","量词");
        MAP.put("mq","数量词");
        MAP.put("t","时间词");
        MAP.put("f","方位词");
        MAP.put("s","处所词");
        MAP.put("v","动词");
        MAP.put("vm","能愿动词");
        MAP.put("vd","趋向动词");
        MAP.put("a","形容词");
        MAP.put("d","副词");
        MAP.put("h","前接成分");
        MAP.put("k","后接成分");
        MAP.put("i","习语");
        MAP.put("j","简称");
        MAP.put("r","代词");
        MAP.put("c","连词");
        MAP.put("p","介词");
        MAP.put("u","助词");
        MAP.put("y","语气助词");
        MAP.put("e","叹词");
        MAP.put("o","拟声词");
        MAP.put("g","语素");
        MAP.put("w","标点");
        MAP.put("x","其它");
        MAP.put("uw","自定义词");
    }
}

