# 测试
## 从文件获取输入并输出到console
```
java -jar thulac-1.0.jar -input input.txt
```
## 从文件获取输入并输出到文件
```
java -jar thulac-1.0.jar -input input.txt -output output.txt
```
## 从文件获取输入并输出到文件指定输出内容之间的分隔符
```
java -jar thulac-1.0.jar -input input.txt -deli / -output output.txt
```
## 从文件获取输入并输出到console，使用自定义词典
```
java -jar thulac-1.0.jar -user userdict.txt -input input.txt
```

```
n/名词 np/人名 ns/地名 ni/机构名 nz/其它专名
m/数词 q/量词 mq/数量词 t/时间词 f/方位词 s/处所词
v/动词 vm/能愿动词 vd/趋向动词 a/形容词 d/副词
h/前接成分 k/后接成分 i/习语 j/简称
r/代词 c/连词 p/介词 u/助词 y/语气助词
e/叹词 o/拟声词 g/语素 w/标点 x/其它
uw/自定义词
```

