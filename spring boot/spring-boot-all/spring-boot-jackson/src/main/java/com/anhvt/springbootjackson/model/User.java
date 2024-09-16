/**
 * Copyright 2024
 * Name: User
 */
package com.anhvt.springbootjackson.model;

import com.anhvt.springbootjackson.configuration.GenericDeserializer;
import com.anhvt.springbootjackson.configuration.GenericSerializer;
import com.anhvt.springbootjackson.configuration.UserDeserializer;
import com.anhvt.springbootjackson.configuration.UserSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 9/15/2024
 */
@Data
@JsonIgnoreProperties({ "password", "age" })
@JsonSerialize(using = GenericSerializer.class)
@JsonDeserialize(using = GenericDeserializer.class)
public class User implements Serializable {

    private static final long serialVersionUID = 6222176558369919436L;

    public interface UserNameView {
    };

    public interface AllUserFieldView extends UserNameView {
    };

    @JsonView(UserNameView.class)
    private String userName;

    @JsonView(AllUserFieldView.class)
    private int age;

    // @JsonIgnore
    @JsonView(AllUserFieldView.class)
    private String password;

    @JsonProperty("bth")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonView(AllUserFieldView.class)
    private Date birthday;
}