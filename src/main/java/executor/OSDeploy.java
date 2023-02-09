package executor;

public interface OSDeploy {
    void createFolder();

    void downloadServer();

    void buildServer();
    void downloadDocker();

    void buildDockerfile();

    void downloadBuildFramework();

    void runDockerFile();

    void ngrokDownload();

    void customCommand(String command);
}
