# Proxy
cho phép tạo một đối tượng đại diện (proxy) cho một đối tượng khác, nhằm kiểm soát quyền truy cập hoặc thực hiện các tác vụ bổ sung khi tương tác với đối tượng đó.

## Minh hoạ
> CommandExecutorProxy và CommandExecutorImpl đều implement CommandExecutor
> -> CommandExecutorProxy là class handler xử lý, kiểm xoát truy cập của CommandExecutorImpl 

nhưng CommandExecutorImpl triển khai detail method cho enpoint
```java
@Override
public void runCommand(String cmd) throws IOException {
    //some heavy implementation
    Runtime.getRuntime().exec(cmd);
    System.out.println("'" + cmd + "' command executed.");
}
```

còn CommandExecutorProxy sử dụng thêm constructor để inject CommandExecutorImpl và xử lý logic
```java
public CommandExecutorProxy(String user, String pwd){
    if("trunganhvu".equals(user) && "trunganhvu".equals(pwd)) {
        isAdmin=true;
    }
    executor = new CommandExecutorImpl();
}
```

Và vẫn implement runCommand()
```java
@Override
public void runCommand(String cmd) throws Exception {
    if (isAdmin) {
        executor.runCommand(cmd);
    } else {
        if(cmd.trim().startsWith("rm")){
            throw new Exception("rm command is not allowed for non-admin users.");
        }else{
            executor.runCommand(cmd);
        }
    }
}
```
