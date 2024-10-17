/**
 * Copyright 2024
 * Name: CommandExecutorImpl
 */
package ProxyPattern;

import java.io.IOException;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/17/2024
 */
public class CommandExecutorImpl implements CommandExecutor {

    @Override
    public void runCommand(String cmd) throws IOException {
        //some heavy implementation
        Runtime.getRuntime().exec(cmd);
        System.out.println("'" + cmd + "' command executed.");
    }
}
