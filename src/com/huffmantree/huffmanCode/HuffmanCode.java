package com.huffmantree.huffmanCode;

import java.io.*;
import java.util.*;

public class HuffmanCode {
    public static void main(String[] args) {

        //测试压缩文件
//        String srcFile = "E://src.png";
//        String dstFile = "E://dst.zip";
//
//        zipFile(srcFile,dstFile);


        //测试解压文件

        String zipFile = "E://dst.zip";
        String dstFile = "E://src2.png";
        unzipFile(zipFile,dstFile);
        System.out.println("解压文件ok");
        /*
        String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();
        System.out.println(contentBytes.length);


        byte[] huffmanCodesBytes = huffmanZip(contentBytes);
        System.out.println(Arrays.toString(huffmanCodesBytes)+"长度为"+huffmanCodesBytes.length);

        //
        byte[] source = decode(huffmanCodes, huffmanCodesBytes);
        System.out.println(new String(source));

        */
        /*//分布过程
        List<Node> nodes = getNodes(contentBytes);
        System.out.println(nodes);
        //创建霍夫曼树
        System.out.println("霍夫曼树");
        Node huffmanTree = createHuffmanTree(nodes);
        huffmanTree.preOrder();

        //测试是否生成了对应的霍夫曼编码
        Map<Byte, String> huffmanCodes = getCodes(huffmanTree);
        System.out.println("~生成的编码表"+huffmanCodes);

        //测试
        byte[] zip = zip(contentBytes, huffmanCodes);
        System.out.println("huffmanCodeBytes" + Arrays.toString(zip));

         */


    }

    //编写方法 完成解压

    /**
     *
     * @param zipFile 压缩文件
     * @param dstFile 解压路径
     */
    public static void unzipFile(String zipFile,String dstFile){
        //创建文件输入流
        InputStream is =null;
        OutputStream os = null;
        ObjectInputStream ois = null;

        try {
            is = new FileInputStream(zipFile);
            //创建一个和is关联的对象输入流
            ois = new ObjectInputStream(is);
            byte[] huffmanBytes = (byte[]) ois.readObject();
            //读取保存的霍夫曼编码表
            Map<Byte,String> huffmanCodes=(Map<Byte,String>) ois.readObject();

            byte[] bytes = decode(huffmanCodes, huffmanBytes);
            os = new FileOutputStream(dstFile);
            //写数据到文件中
            os.write(bytes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                os.close();
                ois.close();
                is.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }

    //编写方法，将一个文件进行压缩
    /**
     *
     * @param srcFile 传入文件的路径
     * @param dstFile   压缩后压缩文件的目录
     */
    public static void zipFile(String srcFile,String dstFile){
        //创建输出流
        OutputStream os =null;
        ObjectOutputStream oos = null;
        //创建输入流
        FileInputStream is =null;
        try  {
             is = new FileInputStream(srcFile);
            //创建一个和源文件大小一样的byte数组
            byte[] b = new byte[is.available()];
            //读取文件
            is.read(b);
            //使用霍夫曼编码,直接对源文件压缩
            byte[] huffmanBytes = huffmanZip(b);
            //创建文件的输出流存放压缩文件
            os= new FileOutputStream(dstFile);
            oos = new ObjectOutputStream(os);
            //把霍夫曼编码后的字节数组写入压缩文件
            oos.writeObject(huffmanBytes);//把huffmanBytes

            //这里我们以对象六的方式写入huffman编码，为了恢复源文件时使用
            //要把霍夫曼编码写入压缩文件
            oos.writeObject(huffmanCodes);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            try {
                oos.close();
                os.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //完成数据解压
    //1.先将huffmanCodeBytes[-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
    //  重新转换为 霍夫曼编码对应的二进制字符串
    //2. 霍夫曼编码对应的二进制字符串 转换成霍夫曼编码=》

    //编写一个方法完成对压缩数据的解码

    /**
     *
     * @param huffmanCodes 霍夫曼编码表
     * @param huffmanBytes 霍夫曼编码转换成的字节数组
     * @return 原来的字符串对应的数组
     */
    private static byte[] decode(Map<Byte,String > huffmanCodes,byte[] huffmanBytes){
        //1.先得到huffmanBytes对应的二进制的字符串
        StringBuilder stringBuilder = new StringBuilder();
        //2.将byte数组转成二进制的字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            //判断是不是最后一个字节
            boolean flag = (i== huffmanBytes.length-1);
           stringBuilder.append(bytesToBitString(!flag,b)) ;
        }

        //把字符串安装指定的霍夫曼编码进行编码
        //把霍夫曼编码表进行调换，因为反向查询
        Map<String, Byte> map = new HashMap<String, Byte>();
        for (Map.Entry<Byte,String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(),entry.getKey());
        }

        //创建一个集合存放byte
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length(); i++) {
            int count = 1;//小的计数器
            boolean flag = true;
            Byte b = null;
            while (flag){
                String key =stringBuilder.substring(i,i+count);//i不懂 count移动，指定匹配到一个字符
                b = map.get(key);
                if(b==null){
                    count++;
                }else {
                    flag = false;
                }
            }
            list.add(b);
            i+=count -1;
        }
        //当for循环结束后，list就存放了所有字符
        //把list中的数据放入到byte[] 并返回
        byte b[] = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }

    /**
     * 将byte转换成一个二进制字符串
     * @param flag 标志是否需要补高位，如果是true表示需要补高位,如果是最后一个字节无需补高位
     * @param b
     * @return b对应的二进制字符串，按照补码返回
     */
    private static String bytesToBitString(boolean flag,byte b){

        int temp = b;
        //如果是正数还需要补高位
        if(flag) {
            temp |= 256;//按位与 256  1 0000 0000|0000 0001
        }
        String s = Integer.toBinaryString(temp);//返回的是temp对应的二进制补码

//        System.out.println(s);
        if(flag) {
            return s.substring(s.length() - 8);
        }else {
            return s;
        }
    }





    //使用一个方法，将前面的方法封装起来，便于调用

    /**
     *
     * @param bytes 原始字符串对应的字节数组
     * @return  经过霍夫曼编码处理后的字节数组
     */
    private static byte[] huffmanZip(byte[] bytes){
        List<Node> nodes = getNodes(bytes);
        //根据nodes创建霍夫曼树
        Node huffmanTree = createHuffmanTree(nodes);
        //生成对应的霍夫曼编码
        Map<Byte, String> huffmanCodes = getCodes(huffmanTree);
        //根据生成的霍夫曼编码压缩得到压缩后的霍夫曼编码字节数组
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);

        return huffmanCodeBytes;
    }

    /**
     *
     * @param bytes 接受字节数组
     * @return 返回List形式，保存了字符和字符个数
     */
    private static List<Node> getNodes(byte[] bytes){

        ArrayList<Node> nodes = new ArrayList<>();

        //遍历bytes,存储每一个Byte出现的次数->map
        HashMap<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if(count==null){//map没数据
                counts.put(b,1);
            }else {
                counts.put(b,count+1);
            }
        }

        //把每个键值对转换成Node对线，并加入到nodes对象
        //遍历map
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    //编写一个方法，将字符串对应的byte[]数组，返回一个压缩后的byte[]

    /**
     *
     * @param bytes 原始字符串对应的bytes
     * @param huffmanCodes 生成的霍夫曼编码
     * @return 返回处理后的byte[]
     * 举例： String content = "i like like like java do you like a java"; =》 byte[] contentBytes = content.getBytes();
     * 返回的是 字符串 "1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000101111111100110001001010011011100"
     * => 对应的 byte[] huffmanCodeBytes  ，即 8位对应一个 byte,放入到 huffmanCodeBytes
     * huffmanCodeBytes[0] =  10101000(补码) => byte  [推导  10101000=> 10101000 - 1 => 10100111(反码)=> 11011000= -88 ]
     * huffmanCodeBytes[1] = -88
     */
    private static byte[] zip(byte[] bytes,Map<Byte,String> huffmanCodes){
        //1.利用huffmancodes 将bytes转化为霍夫曼编码对应的字符串

        StringBuilder stringBuilder = new StringBuilder();
        byte[] bytes1 = null;
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }

       // System.out.println("测试Stringbuilder=" + stringBuilder.toString());
        //2.将字符串转成byte[]
        //统计返回的 bytes[] huffmanCodeBytes.length
        int len;
        if(stringBuilder.length()%8==0){
            len = stringBuilder.length()/8;
        }else {
            len = stringBuilder.length()/8 + 1;
        }
        //创建存储压缩后的byte数组
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;
        for (int i = 0; i < stringBuilder.length(); i+=8) {//每八位对应一个byte
            String strByte;
            if(i+8 > stringBuilder.length()){
                strByte = stringBuilder.substring(i);
            }else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            //将strByte 转成一个Byte.放入huffmanCodeBytes
            huffmanCodeBytes[index] = (byte)Integer.parseInt(strByte,2);
            index++;
        }
        return huffmanCodeBytes;
    }

    //生成霍夫曼树对应的霍夫曼编码
    //思路：
    //1.将霍夫曼编码表存放在map里
    static Map<Byte,String> huffmanCodes = new HashMap<Byte,String>();
    //2.在生成霍夫曼编码表是需要凭借路径，定义一个StringBuilder 存储某个叶子节点的luj
    static StringBuilder stringBuilder = new StringBuilder();

    //为了调用方便重载
    public static Map<Byte,String> getCodes(Node node){
        if(node ==null){
            return null;
        }
        getCodes(node.left,"0",stringBuilder);
        getCodes(node.right,"1",stringBuilder);
        return huffmanCodes;
    }
    /**
     * 将传入的node的所有的叶子节点的霍夫曼编码得到，并放入到huffmanCodes集合中
     * @param node  传入结点
     * @param code  路径：左子节点为0，右子结点是1
     * @param stringBuilder
     */
    private static void getCodes(Node node,String code,StringBuilder stringBuilder){
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        stringBuilder2.append(code);
        if(node!=null){//如果node==null不处理
            //判断当前node是叶子结点还是非叶子节点
            if(node.data == null){
                //向左递归
                getCodes(node.left,"0",stringBuilder2);
                //向右递归
                getCodes(node.right,"1",stringBuilder2);
            }else{//说明是叶子节点
                huffmanCodes.put(node.data,stringBuilder2.toString());
            }
        }
    }
    //前序遍历
    private static void preOrder(Node root){
        if(root !=null){
            root.preOrder();
        }else {
            System.out.println("无法遍历");
        }
    }
    //通过List,创建霍夫曼树
    private static Node createHuffmanTree(List<Node> nodes){
        while (nodes.size()>1){
            Collections.sort(nodes);
            Node leftNode=nodes.get(0);
            Node rightNode=nodes.get(1);

            Node parent = new Node(null, leftNode.value+ rightNode.value);
            parent.left=leftNode;
            parent.right=rightNode;

            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }
        //nodes 最后的节点 就是霍夫曼树的根节点
        return nodes.get(0);
    }
}
//创建节点类
//为了让node对象持续排序，collectuins集合排序
//让node实现Compareble接口
class Node implements Comparable<Node>{
    Byte data;//存放字符本身'a'=>97
    int value;//权值
    Node left;
    Node right;

    public void preOrder(){
        System.out.println(this);
        if(this.left!=null){
            this.left.preOrder();
        }
        if(this.right!=null){
            this.right.preOrder();
        }
    }

    public Node(Byte data, int value) {
        this.data = data;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        //表示从小到大排序
        return this.value - o.value;
    }
}