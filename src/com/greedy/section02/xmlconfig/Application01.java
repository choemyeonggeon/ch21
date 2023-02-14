
package com.greedy.section02.xmlconfig;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Application01 {

   public static void main(String[] args) {

      String resource = "com/greedy/section02/xmlconfig/mybatis-config.xml";
   try {
      InputStream inputStream = Resources.getResourceAsStream(resource);
      
      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
      SqlSession session = sqlSessionFactory.openSession();
      
      System.out.println(session);
      
      java.util.Date date = session.selectOne("mapper.selectSysdate");
 
      System.out.println(date);
      
      session.close();
      
   } catch (IOException e) {
      e.printStackTrace();
   }

}
}