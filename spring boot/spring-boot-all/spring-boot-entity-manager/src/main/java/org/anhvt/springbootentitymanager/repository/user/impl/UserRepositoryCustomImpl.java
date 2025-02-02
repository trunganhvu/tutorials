package org.anhvt.springbootentitymanager.repository.user.impl;

import org.anhvt.springbootentitymanager.repository.RepositoryCustomUtils;
import org.anhvt.springbootentitymanager.repository.user.UserRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepositoryCustomImpl extends RepositoryCustomUtils implements UserRepositoryCustom {
    @Autowired
    private JpaTransactionManager jpaTransactionManager;

    @Override
    public int totalSearch(String keyword) {
        Map<String, Object> parameters = new HashMap<>();

        String sqlQuery = "SELECT COUNT(*) FROM public.user " +
                " WHERE name LIKE :keyword ";

        parameters.put("keyword", keyword);

        Object total = this.getResultList(sqlQuery, parameters, jpaTransactionManager);
        if (total instanceof List<?> list && !list.isEmpty()) {
            Object firstElement = list.get(0);
            return firstElement != null ? Integer.parseInt(firstElement.toString()) : 0;
        }
        return -1;
    }
}
