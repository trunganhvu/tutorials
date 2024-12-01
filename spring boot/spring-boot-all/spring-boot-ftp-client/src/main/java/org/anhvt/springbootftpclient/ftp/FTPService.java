package org.anhvt.springbootftpclient.ftp;

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.OutputStream;

@Service
public class FTPService {

    @Autowired
    private FTPClient ftpClient;

    public boolean uploadFile(String remotePath, InputStream inputStream) {
        try {
            ftpClient.storeFile(remotePath, inputStream);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void downloadFile(String remotePath, OutputStream outputStream) throws Exception {
        if (!ftpClient.retrieveFile(remotePath, outputStream)) {
            throw new Exception("File not found on FTP server: " + remotePath);
        }
    }

    public boolean deleteFile(String remotePath) {
        try {
            return ftpClient.deleteFile(remotePath);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
