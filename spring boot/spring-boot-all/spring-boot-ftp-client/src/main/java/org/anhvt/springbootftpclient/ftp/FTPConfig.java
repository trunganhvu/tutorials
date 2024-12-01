package org.anhvt.springbootftpclient.ftp;

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class FTPConfig {

    @Value("${ftp.host}")
    private String host;

    @Value("${ftp.port}")
    private int port;

    @Value("${ftp.username}")
    private String username;

    @Value("${ftp.password}")
    private String password;

    @Bean
    public FTPClient ftpClient() throws IOException {
        FTPClient ftpClient = new FTPClient();
        ftpClient.connect(host, port);
        ftpClient.login(username, password);
        ftpClient.enterLocalPassiveMode(); // Sử dụng chế độ Passive để tránh lỗi NAT
        return ftpClient;
    }
}