package model;

public interface DeployMachine {

    void executeCommandAndPrint(String command);

    String executeCommandAndReturn(String command);
}
