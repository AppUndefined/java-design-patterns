/*
 * The MIT License
 * Copyright © 2014-2019 Ilkka Seppälä
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.iluwatar.prototype;

import cn.hutool.crypto.symmetric.DES;
import org.junit.jupiter.api.Test;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


/**
 * 
 * Application test
 *
 */
public class AppTest {
  @Test
  public void testBeanUtils() throws Exception {
      User user = new User();
      user.setName("宠物");
      ElfMage elfMage = new ElfMage("大法师");
      elfMage.setUser(user);
      ElfMage elfMage1 = new ElfMage();
      ByteArrayOutputStream out = new ByteArrayOutputStream();
      ObjectOutputStream oos = new ObjectOutputStream(out);
      oos.writeObject(elfMage);
      ByteArrayInputStream inputStream = new ByteArrayInputStream(out.toByteArray());
      ObjectInputStream ois = new ObjectInputStream(inputStream);
      oos.close();
      out.close();
      ois.close();
      inputStream.close();
      elfMage1 = (ElfMage) ois.readObject();
    System.out.println(elfMage.getUser()==elfMage1.getUser());

  }

    /**
     * 序列化对象到磁盘
     * @throws Exception
     */
  @Test
  public void ObjectToSerializable()throws Exception {
      SecretKey key = new SecretKeySpec("bingjianbingjianbingjian".getBytes(),"DESede");
      //加密要用Cipher来实现
      Cipher cipher   =   Cipher.getInstance( "DESede");
      cipher.init(Cipher.ENCRYPT_MODE,key);
      User user = new User();
      user.setName("宠物");
      user.setAddress("上海市上海市上海市上海市上海市上海市上海市上海市上海市上海市上海市上海市上海市上海市上海市上海市上海市上海市上海市上海市上海市上海市上海市上海市上海市上海市上海市上海市上海市上海市上海市上海市上海市上海市上海市上海市上海市上海市上海市上海市上海市上海市上海市上海市上海市上海市上海市上海市上海市上海市上海市上海市上海市上海市");
      user.setAge("123");
      user.setPhone("1037129032");
      ElfMage elfMage = new ElfMage("大法师");
      elfMage.setUser(user);
      File file = new File(user.getName());
      if(file.exists()){
          file.delete();
      }
      ObjectOutputStream oos = new ObjectOutputStream(new CipherOutputStream(new BufferedOutputStream(new FileOutputStream(file)),cipher));
      oos.writeObject(elfMage);
      oos.close();


  }
    /**
     * 读取磁盘到对象
     * @throws Exception
     */
  @Test
  public void SerializableToObject()throws Exception {
      File file = new File("宠物" );
      Cipher cipher1 = Cipher.getInstance("DESede");
      SecretKey key = new SecretKeySpec("bingjianbingjianbingjian".getBytes(),"DESede");
      cipher1.init(Cipher.DECRYPT_MODE,key);
      ObjectInputStream ois = new ObjectInputStream(new CipherInputStream(new BufferedInputStream(new FileInputStream(file)), cipher1));
      ElfMage object = (ElfMage) ois.readObject();
      System.out.println(object);
  }
  @Test
  public void 连接sqlite的jdbc() {
      try {
          Class.forName("org.sqlite.JDBC");// 加载驱动,连接sqlite的jdbc
          Connection connection= DriverManager.getConnection("jdbc:sqlite:test.db");//连接数据库zhou.db,不存在则创建
          Statement statement=connection.createStatement();   //创建连接对象，是Java的一个操作数据库的重要接口
          String sql="create table tables(name varchar(20),pwd varchar(20))";
          statement.executeUpdate("drop table if exists tables");//判断是否有表tables的存在。有则删除
          statement.executeUpdate(sql);            //创建数据库
          statement.executeUpdate("insert into tables values('zhou','156546')");//向数据库中插入数据
          ResultSet rSet=statement.executeQuery("select*from tables");//搜索数据库，将搜索的放入数据集ResultSet中
          while (rSet.next()) {            //遍历这个数据集
              System.out.println("姓名："+rSet.getString(1));//依次输出 也可以这样写 rSet.getString(“name”)
              System.out.println("密码："+rSet.getString("pwd"));
          }
          rSet.close();//关闭数据集
          connection.close();//关闭数据库连接
      } catch (Exception e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
      }

  }
  @Test
  public void test() {
    String[] args = {};
    App.main(args);
  }
}
