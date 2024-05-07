package com.dobie.backend.util.command;

public interface CommandService {

    boolean checkIsCloned(String path);

    void gitClone(String repositoryURL);

    void gitCloneGitLab(String repositoryURL, String accessToken);

    void gitCheckout(String path, String branchName);

    void gitPull(String path);

//    void gitPull(String path, String branchName);

    void build(String path, String projectName);

    void run(String path, int port1, int port2);

    void dockerComposeUp(String path);

    void dockerComposeDown(String path);

    void dockerStop(String containerName);

}

