package com.dobie.backend.domain.command;

public interface CommandService {

    boolean checkIsCloned(String path);

    void gitClone(String repositoryURL);

    void gitPull(String path, String branchName);

    void build(String path, String projectName);

    void run(String path, int port1, int port2);
}
