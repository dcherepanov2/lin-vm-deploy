package model;

import executor.SSHExecutor;
import util.PropertyUtil;

import java.util.logging.Logger;

public class DeployMachineImpl implements DeployMachine{
    private PropertyUtil propertyUtil = new PropertyUtil("src/main/resources/deploy-machine.properties");
    private final String host;
    private final int port;

    private final String username;

    private final String password;

    private final SSHExecutor sshExecutor;

    private final Logger logger = Logger.getLogger(DeployMachineImpl.class.getName());
    public DeployMachineImpl() {
        host = propertyUtil.getProperty("vm.host");
        password = propertyUtil.getProperty("vm.password");
        port = Integer.parseInt(propertyUtil.getProperty("vm.port"));
        username = propertyUtil.getProperty("vm.username");
        sshExecutor = new SSHExecutor(host,username,password,port);
    }

    @Override
    public void executeCommandAndPrint(String command){
        String finishCommand = sshExecutor.executeCommandAndReturn(command);
        logger.info(finishCommand);
    }

    @Override
    public String executeCommandAndReturn(String command){
        return sshExecutor.executeCommandAndReturn(command);
    }
}
