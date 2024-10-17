/**
 * Copyright 2024
 * Name: ProxyPatternTest
 */
package ProxyPattern;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/17/2024
 */
public class ProxyPatternTest {

    public static void main(String[] args){
        CommandExecutor executor = new CommandExecutorProxy("trunganhvu", "trunganhvu");
        try {
            executor.runCommand("cmd pwd");              // 'cmd ls' command executed.
            executor.runCommand("cmd ls");              // 'cmd /c dir' command executed.
            executor.runCommand("cmd rm -rf abc.pdf");  // 'cmd rm -rf abc.pdf' command executed.
        } catch (Exception e) {
            System.out.println("Exception Message::"+e.getMessage());
        }

    }

}