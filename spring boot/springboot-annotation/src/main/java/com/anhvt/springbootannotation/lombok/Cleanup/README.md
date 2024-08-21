#  @Cleanup
là một annotation trong Lombok được sử dụng để tự động giải phóng nguồn tài nguyên, chẳng hạn như đóng các luồng (Closeable, AutoCloseable), giải phóng bộ nhớ (@Cleanup("dispose")), hoặc thực hiện các công việc giải phóng tài nguyên tùy chỉnh thông qua phương thức được chỉ định. Nó sẽ tương tự như khối try-with-resources.
Open sẽ như bình thường
Close sẽ tự động khi hết khối lệch {} or method
### Không sử dung @Cleanup thực hiện close qua finally
```java
InputStream inputStream = null;
BufferedReader reader = null;

try {
    inputStream = CleanupTest.class.getResourceAsStream("/example.txt");
    reader = new BufferedReader(new InputStreamReader(inputStream));

    inputStreamFirst = inputStream;
    readerFirst = reader;

    //  Check InputStream and BufferedReader closed
    checkInputStreamClosed(inputStreamFirst);                   // inputStream opened
    checkBufferedReaderClosed(readerFirst);                     // bufferedReader opened

    String line;
    while ((line = reader.readLine()) != null) {
        System.out.print(line);                                 // 234
    }
    System.out.println();

} catch (IOException e) {
    e.printStackTrace();
} finally {
    if (reader != null) {
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    if (inputStream != null) {
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
// Check InputStream and BufferedReader closed
checkInputStreamClosed(inputStreamFirst);                       // inputStream has been closed!
checkBufferedReaderClosed(readerFirst);                         // bufferedReader has been closed!
```
Output
```shell
inputStream opened        // đã được open thủ công
bufferedReader opened     // đã được open thủ công
234
inputStream has been closed!    // đã được closed thủ công bằng finally
bufferedReader has been closed! // // đã được closed thủ công bằng finally
```

### Khi sử dụng @Cleanup
Việc khởi tạo như bình thường + closed tự động trong scope block {}
```java
{
    @Cleanup
    InputStream inputStream = CleanupTest.class.getResourceAsStream("/example.txt");    // src/main/resources/example.txt
    @Cleanup
    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
    inputStreamFirst = inputStream;
    readerFirst = reader;

    //  Check InputStream and BufferedReader closed
    checkInputStreamClosed(inputStreamFirst);           // inputStream opened
    checkBufferedReaderClosed(readerFirst);             // bufferedReader opened

    String line;
    while ((line = reader.readLine()) != null) {
        System.out.print(line);                         // 234
    }
    System.out.println();
}

// Check InputStream and BufferedReader closed
checkInputStreamClosed(inputStreamFirst);               // inputStream has been closed!
checkBufferedReaderClosed(readerFirst);                 // bufferedReader has been closed!
```

Output
```shell
inputStream opened          // đã được open thủ công
bufferedReader opened       // đã được open thủ công
234
inputStream has been closed!    // đã được closed tự động
bufferedReader has been closed! // đã được closed thủ công
```