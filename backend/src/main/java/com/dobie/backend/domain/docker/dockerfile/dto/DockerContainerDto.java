package com.dobie.backend.domain.docker.dockerfile.dto;

public class DockerContainerDto {
    private String containerId;
    private String image;
    private String command;
    private String created;
    private String status;
    private String currentStatus;
    private String ports;
    private String innerPort;
    private String outerPort;
    private String names;

    // 생성자, getter 및 setter는 필요에 따라 추가
    public DockerContainerDto(String containerId, String image, String command, String created, String status, String currentStatus, String ports, String innerPort, String outerPort, String names) {
        this.containerId = containerId; //컨테이너 아이디
        this.image = image; //이미지 아이디
        this.command = command; //커맨드
        this.created = created; //생성 시각
        this.status = status; //상태
        this.currentStatus = currentStatus; //상태를 분석해서 Running 또는 Stopped를 리턴
        this.ports = ports; //포트번호
        this.innerPort = innerPort; //내부 포트번호
        this.outerPort = outerPort; //외부 포트번호
        this.names = names; //컨테이너 이름
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
                ", currentStatus='" + currentStatus + '\'' +
                ", ports='" + ports + '\'' +
                ", innerPorts='" + innerPort + '\'' +
                ", outerPorts='" + outerPort + '\'' +
                ", names='" + names + '\'' +
                '}';
    }
}
