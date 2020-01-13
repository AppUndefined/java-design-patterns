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
  public void test() {
    String[] args = {};
    App.main(args);
  }
}
