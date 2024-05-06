package com.dobie.backend.domain.docker.dockerfile.dto;

public class DockerContainerDto {
    private String containerId;
    private String image;
    private String command;
    private String created;
    private String status;
    private String ports;
    private String names;

    // 생성자, getter 및 setter는 필요에 따라 추가
    public DockerContainerDto(String containerId, String image, String command, String created, String status, String ports, String names) {
        this.containerId = containerId;
        this.image = image;
        this.command = command;
        this.created = created;
        this.status = status;
        this.ports = ports;
        this.names = names;
    }

    // toString 메소드는 객체 정보를 문자열로 출력할 때 유용
    @Override
    public String toString() {
        return "DockerContainer{" +
                "containerId='" + containerId + '\'' +
                ", image='" + image + '\'' +
                ", command='" + command + '\'' +
                ", created='" + created + '\'' +
                ", status='" + status + '\'' +
                ", ports='" + ports + '\'' +
                ", names='" + names + '\'' +
                '}';
    }
}
