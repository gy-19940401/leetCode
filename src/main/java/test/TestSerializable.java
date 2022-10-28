package test;

import java.io.*;
import java.util.Date;

/**
 * @Description: 测试序列化与反序列化
 * @author: GanYang
 * @Date: 2022/10/24 22:31
 */
public class TestSerializable {


    static class User implements Serializable {
        private String name;
        private int age;

        public User() {
            System.out.println("Instance");
        }

        public User(String name, int age) {
            this.name = name;
            this.age = age;
            System.out.println("AllInstance");
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{" + this.name + this.age + "}";
        }

        private Object readResolve() throws Exception {
            return new Date(1);
        }
    }

    public static void main(String[] args) throws Exception {
        OutputStream output = new FileOutputStream("/tmp/test.txt");
        ObjectOutputStream outputStream = new ObjectOutputStream(output);
        User yiChanYanYu = new User("yiChanYanYu", 18);
        outputStream.writeObject(yiChanYanYu);

        FileInputStream in = new FileInputStream("/tmp/test.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(in);

        Object o = objectInputStream.readObject();

        System.out.println("反序列化 ： " + o);
    }
}
