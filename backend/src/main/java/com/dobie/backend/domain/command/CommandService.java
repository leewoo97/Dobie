package com.dobie.backend.domain.command;

public interface CommandService {

    void gitClone(String repositoryURL);

    void build(String path, String projectName);

    void run(String path, int port1, int port2);
}
