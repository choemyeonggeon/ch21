package com.greedy.section01.javaconfig;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

public class Application01 {
	
	private static String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static String USER = "C##GREEDY";
	private static String PASSWORD ="GREEDY";
	
	public static void main(String[] args) {

		/*DB 접속에 관한 환경설정*/
		/*-----------------------------
		 * jdbcTransactionFactory : 수동 커밋
		 * ManagedTransectionFactory : 자동 커밋
		 * ------------------------------------
		 * PooledDateSource : ConnectionPool 사용
		 * UnPooledDataSource : ConnectionPool 사용하지 않음
		 * */
		Environment environment = 
				new Environment("dev"												//환경정보이름
						,new JdbcTransactionFactory()								//트랜젝션 매니저 종류 결정(JDBC OR MANAGED)
						,new PooledDataSource(DRIVER,URL,USER,PASSWORD));			//ConnectionPool 사용 유무(pooled or UnPooled)
		
		/* 생성한 환경 설정 정보를 가지고 마이바티스 설정 객체 생성*/
		Configuration configuration = new Configuration(environment);
		/*설정 객체에 매퍼 등록*/
		configuration.addMapper(Mapper.class);
		/*sqlSessionFactory : sqlSession 객체를 생성하기 위한 팩토리 역할을 수행하는 인터페이스
		 *sqlSessionFactoryBuilder : sqlSessionFactory 인터페이스 타입의 하위 구현 객체를 생성학 ㅣ위한 빌드 역할 수행
		 *build() : 설정에 대한 정보를 담고 있는 Confoguration 타입의 객체 혹은 외부 설정 파일과 연결된 스트림을 매개변수로 전달하면
		 *			sqlSessionFacgory 인터페이스 타입의 객체를 반환하는 메소드 
		 * */
		
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
		
		/* openSession() : SqlSession 인터페이스 타입의 객체를 반환한느 메소드, boolean 타입을 인자로 전달
		 * false : Connection 인터페이스 타입 객체로 DML 수행 후 auto commit에 대한 옵션을 false로 저장 (권장)
		 * true : Connection 인터페이스 타입 객체로 DML 수행후 auto commit 에 대한 옵션을 true로 지정*/
		SqlSession sqlSession = sqlSessionFactory.openSession(false);
	
		Mapper mapper = sqlSession.getMapper(Mapper.class);
		
		java.util.Date date = mapper.selectSysdate();
		
		System.out.println(date);
		
		sqlSession.close();
	}

}
