package com.dobie.backend.util.command;

import java.io.IOException;

public interface CommandService {

    boolean checkIsCloned(String path);

    void gitClone(String repositoryURL, String accessToken);

    void gitCheckout(String path, String branchName);

    void gitPull(String path);

    void build(String path, String projectName);

    void run(String path, int port1, int port2);

    void dockerComposeUp(String path);

    void dockerComposeDown(String path);

    void dockerStop(String containerName);

    void dockerStart(String containerName);

    void restartNginx();

    void stopNginx();

    void deleteNginxProxyConf(String projectId);

    void deleteFile(String fileName, String path);

    void deleteDirectory(String directoryPath);

    void getSSLTest(String domain) throws IOException;
}

