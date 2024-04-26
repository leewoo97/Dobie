package com.dobie.backend.util.command;

public interface CommandService {

    boolean checkIsCloned(String path);

    void gitClone(String repositoryURL);

    void gitCloneGitLab(String repositoryURL, String username, String password);

    void gitCheckout(String path, String branchName);

    void gitPull(String path, String branchName);

    void build(String path, String projectName);

    void run(String path, int port1, int port2);

    void dockerComposeDown();

}
