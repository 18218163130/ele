package com.ele;



import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.ele.mapper") // 指定Mapper的路径
@EnableTransactionManagement // 开启事务支持
@EnableCaching // 开启缓存
public class EleApplication {

    public static void main(String[] args) {
        SpringApplication.run(EleApplication.class, args);

    }

}
