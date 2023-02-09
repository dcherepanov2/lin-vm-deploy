package executor;

import model.DeployMachine;
import model.DeployMachineImpl;
import util.PropertyUtil;
public class UbuntuDeploy implements OSDeploy{
    private final DeployMachine javaDeployMachine;
    private final String commandPath;
    private final String git;
    private final String dirname;

    private final String projectName;
    
    private final String buildName;
    private final String dockerIntoPort;
    private final String dockerOutPort;
    private final String ngrokToken;

    public UbuntuDeploy() {
        this.javaDeployMachine = new DeployMachineImpl();
        PropertyUtil propertyUtil = new PropertyUtil("src/main/resources/deploy-commands.properties");
        commandPath = propertyUtil.getProperty("command.path");
        git = propertyUtil.getProperty("git.link");
        dirname = propertyUtil.getProperty("dirname");
        projectName = propertyUtil.getProperty("project.name");
        buildName = propertyUtil.getProperty("project.build.name");
        dockerIntoPort = propertyUtil.getProperty("docker.port.into.container.run");
        dockerOutPort = propertyUtil.getProperty("docker.port.out.container.run");
        ngrokToken = propertyUtil.getProperty("ngrok.auth.token");
    }

    @Override
    public void createFolder() {
        javaDeployMachine.executeCommandAndPrint("mkdir "+commandPath+"/"+dirname);
    }

    @Override
    public void downloadServer() {
        javaDeployMachine.executeCommandAndPrint("cd "+commandPath+"/"+dirname+" && "+ "git clone " + git);
    }

    @Override
    public void buildServer() {
        javaDeployMachine.executeCommandAndPrint("cd "+commandPath+"/"+dirname+"/"+projectName+" && "+" mvn clean install package");
    }

    @Override
    public void downloadDocker() {
        javaDeployMachine.executeCommandAndPrint("sudo apt-get install docker-ce docker-ce-cli containerd.io docker-compose-plugin");
    }

    @Override
    public void buildDockerfile() {
        javaDeployMachine.executeCommandAndPrint("cd "+commandPath+"/"+dirname+"/"+projectName+" && "+"docker build -t "+ buildName + " .");
    }

    @Override
    public void downloadBuildFramework() {
        javaDeployMachine.executeCommandAndPrint("wget https://mirrors.estointernet.in/apache/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz");
        javaDeployMachine.executeCommandAndPrint("tar -xvf apache-maven-3.6.3-bin.tar.gz");
        javaDeployMachine.executeCommandAndPrint("mv apache-maven-3.6.3 /opt/");
        javaDeployMachine.executeCommandAndPrint("M2_HOME='/opt/apache-maven-3.6.3'");
        javaDeployMachine.executeCommandAndPrint("PATH=\"$M2_HOME/bin:$PATH\"");
        javaDeployMachine.executeCommandAndPrint("export PATH");
        System.out.println(javaDeployMachine.executeCommandAndReturn("mvn -version"));
    }

    @Override
    public void runDockerFile() {
        javaDeployMachine.executeCommandAndPrint("docker run -d -p "+dockerOutPort+":"+dockerIntoPort+" -t "+buildName);
    }

    @Override
    public void ngrokDownload() {
        javaDeployMachine.executeCommandAndPrint("snap install ngrok");
        javaDeployMachine.executeCommandAndPrint("ngrok config add-authtoken "+ngrokToken);
    }
    @Override
    public void customCommand(String command) {
        javaDeployMachine.executeCommandAndPrint(command);
    }
}
