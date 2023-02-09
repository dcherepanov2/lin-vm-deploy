package executor;

import com.jcraft.jsch.*;
import java.io.ByteArrayOutputStream;

public class SSHExecutor {

    private final Session session;

    private final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(SSHExecutor.class.getName());
    public SSHExecutor(String host,String username,  String password, int port){
        try {
            session = new JSch().getSession(username, host,port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
        } catch (JSchException e) {
            throw new RuntimeException(e);
        }
    }

    public String executeCommandAndReturn(String command){
        String commandReturn;
        try {
            ChannelExec channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand(command);
            ByteArrayOutputStream byteArrayInputStream = new ByteArrayOutputStream();
            channel.setOutputStream(byteArrayInputStream);
            channel.connect();
            while (channel.isConnected()){
                logger.info("Wait while command: " + command + " finish execute ");
                Thread.sleep(1000L);
            }
            commandReturn = byteArrayInputStream.toString();
        } catch (JSchException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return commandReturn;
    }
}
