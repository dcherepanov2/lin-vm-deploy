import executor.OSDeploy;
import executor.UbuntuDeploy;

public class Deploy {
    public static void main(String[] args) {
        OSDeploy osDeploy = new UbuntuDeploy();
        osDeploy.createFolder();
        osDeploy.downloadServer();
        osDeploy.downloadBuildFramework();
        osDeploy.downloadDocker();
        osDeploy.buildServer();
        osDeploy.buildDockerfile();
        osDeploy.runDockerFile();
        osDeploy.ngrokDownload();
    }
}
