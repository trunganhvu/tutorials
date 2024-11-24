package org.anhvt.springbootmasterslavepostgre.config;

import org.anhvt.springbootmasterslavepostgre.enums.DataSourceType;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataSourceAspect {

    @Before("execution(* org.anhvt.springbootmasterslavepostgre.repository..*.save*(..)) " +
            "|| execution(* org.anhvt.springbootmasterslavepostgre.repository..*.insert*(..)) " +
            "|| execution(* org.anhvt.springbootmasterslavepostgre.repository..*.update*(..)) " +
            "|| execution(* org.anhvt.springbootmasterslavepostgre.repository..*.delete*(..))" +
            "|| execution(* org.anhvt.springbootmasterslavepostgre.service..*.save*(..))" +
            "|| execution(* org.anhvt.springbootmasterslavepostgre.service..*.update*(..))")
    public void setWriteDataSourceType() {
        System.out.println(DataSourceType.WRITE);
        DataSourceContextHolder.setDataSourceType(DataSourceType.WRITE);
    }

    @Before("execution(* org.anhvt.springbootmasterslavepostgre.service..*.find*(..)) " +
            "|| execution(* org.anhvt.springbootmasterslavepostgre.repository..*.find*(..)) " +
            "|| execution(* org.anhvt.springbootmasterslavepostgre.repository..*.get*(..)) " +
            "|| execution(* org.anhvt.springbootmasterslavepostgre.repository..*.read*(..))" +
            "|| execution(* org.anhvt.springbootmasterslavepostgre.service..*.find*(..))")
    public void setReadDataSourceType() {
        System.out.println(DataSourceType.READ);

        DataSourceContextHolder.setDataSourceType(DataSourceType.READ);
    }

    @After("@annotation(org.springframework.transaction.annotation.Transactional)")
    public void after(JoinPoint joinPoint) {
        DataSourceContextHolder.clearDataSourceType();
    }
}