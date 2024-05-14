package com.dobie.backend.util.command;

import java.io.IOException;

public interface CommandService {

    boolean checkIsCloned(String path);

    void gitClone(String repositoryURL, String accessToken);

    void gitCheckout(String path, String branchName);

    void gitPull(String path) throws IOException;

//    void gitPull(String path, String branchName);

    void build(String path, String projectName);

    void run(String path, int port1, int port2);

    void dockerComposeUp(String path);

    void dockerComposeDown(String path);

    void dockerStop(String containerName);

    void dockerStart(String containerName);

    void restartNginx();

    void deleteNginxProxyConf(String projectId);
    String getSSL(String domain);

}

