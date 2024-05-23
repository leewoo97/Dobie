# README.md

---

![단락 텍스트.png](README%20md%20e79e283e946148e9b036ee7fa886d541/%25EB%258B%25A8%25EB%259D%25BD_%25ED%2585%258D%25EC%258A%25A4%25ED%258A%25B8.png)

# Dobie

---

### 🏆삼성 청년 SW아카데미(SSAFY) 10th 자율 프로젝트 우수상(2위)🏆

> 초보자를 위한 인프라 서비스 플랫폼
> 

## 목차

[기획 배경](#**🚩-**기획-배경)

[프로젝트 소개](#**🚩-**프로젝트-소개)

[프로젝트 차별성](#**🚩-**프로젝트-차별성)

[Shell Script](# 🚩-Shell-Script)

[디렉터리 구조](#**🚩-**디렉터리-구조)

[주요 구현 기능](#🚩- 주요-구현-기능)

[프로젝트 설치 및 기여 방법](#**🚩-**프로젝트-설치-및-기여-방법)

[팀원 소개](#🚩-팀원-소개)

## **🚩** 기획 배경

Dobie는 배포를 처음 경험해보는 초보 개발자들이 많은 시간을 학습에 할애해야 한다는 문제를 인식했습니다. 이를 해결하기 위해, 처음 배포 환경을 구축하고 배포하는 시간을 획기적으로 줄여주고, 누구나 쉽게 사용할 수 있도록 설계했습니다. Dobie로 간편하고 신속한 배포 환경을 경험해보세요.

## **🚩** 프로젝트 소개

- Dobie는 초보자도 쉽게 사용할 수 있는 인프라 서비스 플랫폼입니다.
- 사용자는 간단한 정보만을 입력하고 자신의 프로젝트를 배포하는 경험을 할 수 있습니다.
- Dobie는 Docker, Docker Compose를 활용하여 사용자의 프로젝트를 컨테이너로 관리합니다.

📚 **사용 기술 스택**

```markdown
- Frontend 
<img src="https://img.shields.io/badge/React-61DAFB?style=for-the-badge&logo=React&logoColor=white">
<img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=JavaScript&logoColor=white">
<img src="https://img.shields.io/badge/MUI-007FFF?style=for-the-badge&logo=MUI&logoColor=white">
- Backend
<img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
<img src="https://img.shields.io/badge/springsecurity-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white">
- Infra
<img src="https://img.shields.io/badge/amazonec2-FF9900?style=for-the-badge&logo=amazonec2&logoColor=white">
<img src="https://img.shields.io/badge/docker-2496ED?style=for-the-badge&logo=amazonec2&logoColor=white">
<img src="https://img.shields.io/badge/ubuntu-E95420?style=for-the-badge&logo=ubuntu&logoColor=white">
<img src="https://img.shields.io/badge/nginx-009639?style=for-the-badge&logo=nginx&logoColor=white">
- 협업 툴
<img src="https://img.shields.io/badge/gitlab-FC6D26?style=for-the-badge&logo=gitlab&logoColor=white">
<img src="https://img.shields.io/badge/jira-0052CC?style=for-the-badge&logo=jira&logoColor=white">
<img src="https://img.shields.io/badge/notion-000000?style=for-the-badge&logo=notion&logoColor=white">
<img src="https://img.shields.io/badge/swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=white">
- 디자인
<img src="https://img.shields.io/badge/figma-F24E1E?style=for-the-badge&logo=figma&logoColor=white">
```

## **🚩** 프로젝트 차별성

- docker, docker-compose, nginx 등을 CLI환경이 아닌 GUI환경을 사용하여 배포 과정이 쉽고 간편합니다.
- 사용자가 docker와 docker-compose 등을 직접 설치하는 과정 없이 Dobie의 Shell Script 다운 명령어와, 실행 명령어를 통해 Dobie 사용 환경을 구축할 수 있습니다.
- WebHook 기능을 통해 특정 브랜치에 merge시 자동 배포가 진행됩니다.
- 토글 버튼 하나로 쉽게 SSL인증서를 발급 받고 https를 사용할 수 있습니다.
- 데이터베이스를 사용하지 않고 json 파일에 필요한 데이터를 저장하여 불필요한 서버 리소스를 절약하였습니다.
- 다양한 프레임워크를 지원합니다
    
    
    | Backend | <img src="https://img.shields.io/badge/django-092E20?style=for-the-badge&logo=django&logoColor=white"><img src="https://img.shields.io/badge/fastapi-009688?style=for-the-badge&logo=fastapi&logoColor=white"><img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">(maven / gradle) |
    | --- | --- |
    | Frontend | <img src="https://img.shields.io/badge/React-61DAFB?style=for-the-badge&logo=React&logoColor=white"><img src="https://img.shields.io/badge/vuedotjs-4FC08D?style=for-the-badge&logo=vuedotjs&logoColor=white"> |
    | Database | <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"><img src="https://img.shields.io/badge/mongodb-47A248?style=for-the-badge&logo=mongodb&logoColor=white"><img src="https://img.shields.io/badge/redis-DC382D?style=for-the-badge&logo=redis&logoColor=white"> |

## **🚩 Shell Script**

- Shell Script
    
    ```bash
    #!/bin/bash
    
    # 스크립트 실행 시 발생할 수 있는 모든 에러를 처리합니다.
    set -e
    
    # 1. Docker 설E치
    # Docker가 이미 설치되어 있지 않은 경우 설치합니다.
    
    for pkg in docker.io docker-doc docker-compose docker-compose-v2 containerd runc; do sudo apt-get remove $pkg; done
    
    sudo apt-get update -y
    sudo apt-get install -y ca-certificates curl
    sudo install -m 0755 -d /etc/apt/keyrings
    sudo curl -fsSL https://download.docker.com/linux/ubuntu/gpg -o /etc/apt/keyrings/docker.asc
    sudo chmod a+r /etc/apt/keyrings/docker.asc
    
    # Docker의 공식 APT repository를 시스템에 추가합니다.
    echo "deb [arch=amd64 signed-by=/etc/apt/keyrings/docker.asc] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
    
    # Docker 관련 패키지를 설치합니다.
    sudo apt-get update -y
    sudo apt-get install -y docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin
    
    # 2. Let's Encrypt 설치
    # Let's Encrypt 설치 여부 확인
    if dpkg -l | grep -q letsencrypt; then
        # 이미 Let's Encrypt가 설치되어 있는 경우, 삭제
        echo "Let's Encrypt를 삭제합니다..."
        sudo apt-get remove letsencrypt -y
    fi
    
    # Let's Encrypt 설치
    echo "Let's Encrypt를 설치합니다..."
    sudo apt-get update
    sudo apt-get install letsencrypt -y
    
    # 설치 확인
    if [ -x "$(command -v letsencrypt)" ]; then
        echo "Let's Encrypt 설치가 완료되었습니다."
    else
        echo "Let's Encrypt 설치에 실패했습니다."
    fi
    
    # 3. nginx config 폴더 생성
    # /var/dobie/nginx 폴더가 없으면 생성합니다.
    if [ ! -d "/var/dobie/nginx" ]; then
        echo "nginx 폴더가 없어서 새로 생성합니다..."
        sudo mkdir -p /var/dobie/nginx
    else
        echo "nginx 폴더가 이미 존재합니다."
    fi
    
    # cd /var/dobie/nginx
    # sudo wget https://raw.githubusercontent.com/eunnseok/dobie-deploy/main/default.conf
    # cd ~
    
    # 4. data(json) 폴더 생성
    # /var/dobie/data 폴더가 없으면 생성합니다.
    if [ ! -d "/var/dobie/data" ]; then
        echo "data 폴더가 없어서 새로 생성합니다..."
        sudo mkdir -p /var/dobie/data
    else
        echo "data 폴더가 이미 존재합니다."
    fi
    
    # sslLog  파일 생성
    # /logfile.log 파일이 없으면 생성합니다.
    if [ ! -f "/logfile.log" ]; then
        echo "logfile.log 파일이 없어서 새로 생성합니다..."
        sudo touch /logfile.log
        if [ -f "/logfile.log" ]; then
            echo "로그파일 생성완료"
        else
            echo "로그파일 생성실패"
        fi
    else
        echo "logfile.log 파일이 이미 존재합니다."
    fi
    
    cd /var/dobie/data
    sudo wget https://raw.githubusercontent.com/eunnseok/dobie-deploy/main/data/user.json
    sudo wget https://raw.githubusercontent.com/eunnseok/dobie-deploy/main/data/project.json
    sudo wget https://raw.githubusercontent.com/eunnseok/dobie-deploy/main/data/refreshToken.json
    cd ~
    
    # 5. Docker network 생성
    # 'dobie' 네트워크가 이미 존재하는지 확인합니다.
    if ! sudo docker network ls | grep -qw dobie; then
        echo "dobie 네트워크가 없어서 새로 생성합니다..."
        sudo docker network create dobie
    else
        echo "dobie 네트워크가 이미 존재합니다."
    fi
    
    # 6. 파이프 생성
    pipe_path="/var/dobie/ssl"
    
    # named pipe가 존재하지 않을 때만 실행
    if [ ! -p "$pipe_path" ]; then
      # mkfifo 명령을 실행할 수 있는지 확인하고 권한 부여
      if ! [ -x "$(command -v mkfifo)" ]; then
        echo 'mkfifo 명령을 찾을 수 없습니다. 권한을 부여합니다.'
        sudo chmod +x "$(command -v mkfifo)"
        if [ $? -ne 0 ]; then
          echo 'Error: 권한을 부여할 수 없습니다.'
        fi
      fi
      
      # named pipe를 생성할 디렉토리에 쓰기 권한이 없을 때 권한 부여
      if [ ! -w "$(dirname "$pipe_path")" ]; then
        echo 'named pipe를 생성할 디렉토리에 쓰기 권한이 없습니다. 권한을 부여합니다.'
        sudo chmod +w "$(dirname "$pipe_path")"
        if [ $? -ne 0 ]; then
          echo 'Error: 권한을 부여할 수 없습니다.'
        fi
      fi
      
      # named pipe 생성
      sudo mkfifo "$pipe_path"
      if [ $? -eq 0 ]; then
        echo "Named pipe 생성 완료: $pipe_path"
      else
        echo "Error: Named pipe를 생성할 수 없습니다."
      fi
    else
      echo "이미 존재하는 named pipe입니다: $pipe_path"
    fi
    
    #7. 서버 재시작 시 파이프 자동연
    script_path="/pipe_config/pipe_script.sh"
    
    # /pipe_config 디렉토리가 존재하지 않거나, 해당 디렉토리에 pipe_script.sh 파일이 없을 때에만 작성 및 저장
    if [ ! -d "/pipe_config" ] || [ ! -f "$script_path" ]; then
        
        # 스크립트 작성
        echo '#!/bin/bash
        sudo sh -c "
        while true; do
            output=$(eval "$(cat /var/dobie/ssl)")
            echo \"$output\" | sudo tee -a /logfile.log >/dev/null
        done
        " &' > pipe_script.sh
    
        sudo chmod +x pipe_script.sh
    
        # 스크립트 저장
        sudo mkdir -p /pipe_config
        sudo mv pipe_script.sh /pipe_config/
        
        if [ -f "/pipe_config/pipe_script.sh" ]; then
            echo "스크립트 작성 및 저장이 완료되었습니다."
        else
            echo "스크립트를 저장하는데 문제가 발생했습니다."
        fi
    else
        echo "이미 /pipe_config 디렉토리가 존재하거나 해당 디렉토리에 pipe_script.sh 파일이 존재합니다."
    fi
    
    sudo chmod +x /pipe_config/pipe_script.sh
    
    cron_setting="@reboot $script_path"
    cron_settings_file="cron_settings"
    
    # named pipe가 존재하고 스크립트 파일이 존재할 때에만 실행
    if [ -p "$pipe_path" ] && [ -f "$script_path" ]; then
        # 이미 해당 설정이 존재하는지 확인
        if ! crontab -l | grep -qF "$cron_setting"; then
            # 새로운 설정을 파일에 저장
            echo "$cron_setting" > "$cron_settings_file"
    
            # 새로운 crontab 파일로 적용
            crontab "$cron_settings_file"
            
            # 임시 파일 제거
            rm "$cron_settings_file"
    
            # crontab에 설정이 적용되었는지 확인
            if crontab -l | grep -qF "$cron_setting"; then
                echo "새로운 crontab 설정을 추가했습니다: $cron_setting"
            else
                echo "Error: 새로운 crontab 설정을 추가하지 못했습니다: $cron_setting"
            fi
        else
            echo "이미 해당 crontab 설정이 존재합니다: $cron_setting"
        fi
    else
        echo "Named pipe와/또는 스크립트 파일이 존재하지 않습니다."
    fi
    
    # 8. git 설치 / 소스코드 clone /.env 파일(ip 관련) 생성
    sudo apt install git
    
    git clone https://oauth2:rYWbAwDm9hUVynwz5PHp@lab.ssafy.com/s10-final/S10P31B101.git
    
    export IP_ADDRESS=$(curl -4 ifconfig.me)
    echo "REACT_APP_SERVER=http://$IP_ADDRESS:8010/api" > ./S10P31B101/frontend/.env
    
    # 9. docker-compose.yaml 가져온 후 실행
    echo "Dobie의 docker-compose.yaml 을 가져옵니다."
    wget https://raw.githubusercontent.com/eunnseok/dobie-deploy/main/docker-compose.yaml
    
    if [ $? -eq 0 ]; then
        echo "Docker Compose 파일이 성공적으로 저장되었습니다."
        sudo docker compose -f docker-compose.yaml up -d
    else
        echo "Docker Compose 파일을 가져오는 데 문제가 발생했습니다."
    fi
    
    echo "스크립트 실행이 완료되었습니다."
    
    # 9. 파이프 연결
    # named pipe가 존재할 때에만 실행
    if [ -p "$pipe_path" ]; then
        sudo sh -c 'while true; do output=$(eval "$(cat /var/dobie/ssl)"); echo "$output" | sudo tee /logfile.log >/dev/null; done' &
    fi
    ```
    

## **🚩** 디렉터리 구조

- Frontend
    
    ```
    
    📦src
     ┣ 📂api
     ┃ ┣ 📂project
     ┃ ┃ ┗ 📜Project.js
     ┃ ┣ 📜Axios.js
     ┃ ┣ 📜CheckProcess.jsx
     ┃ ┣ 📜Docker.jsx
     ┃ ┣ 📜Member.jsx
     ┃ ┣ 📜ngixn.jsx
     ┃ ┗ 📜Project.jsx
     ┣ 📂assets
     ┃ ┣ 📂guide
     ┃ ┃ ┣ 📜AWS.png
     ┃ ┃ ┣ 📜AWSSetting.png
     ┃ ┃ ┣ 📜Building1.png
     ┃ ┃ ┣ 📜DockerCompose1.png
     ┃ ┃ ┣ 📜DockerCompose2.png
     ┃ ┃ ┣ 📜Dockerfile1.png
     ┃ ┃ ┣ 📜Dockerfile2.png
     ┃ ┃ ┣ 📜FolderBE.png
     ┃ ┃ ┣ 📜FolderFE.png
     ┃ ┃ ┣ 📜GitHubToken.png
     ┃ ┃ ┣ 📜GitLabToken.png
     ┃ ┃ ┣ 📜Https.png
     ┃ ┃ ┣ 📜LogImg1.png
     ┃ ┃ ┣ 📜LogImg2.png
     ┃ ┃ ┣ 📜navbarImg.png
     ┃ ┃ ┣ 📜ProjectInfoImg.png
     ┃ ┃ ┣ 📜ProjectNameGitHub.png
     ┃ ┃ ┣ 📜projectNameGitLab.png
     ┃ ┃ ┣ 📜RunAllServices1.png
     ┃ ┃ ┣ 📜RunAllServices2.png
     ┃ ┃ ┣ 📜RunAllServices3.png
     ┃ ┃ ┣ 📜RunLoading.png
     ┃ ┃ ┣ 📜RunStatus.png
     ┃ ┃ ┣ 📜structure.png
     ┃ ┃ ┣ 📜Webhook1.png
     ┃ ┃ ┣ 📜Webhook2.png
     ┃ ┃ ┣ 📜WebhookGitHub1.png
     ┃ ┃ ┣ 📜WebhookGitHub2.png
     ┃ ┃ ┣ 📜WebhookGitHub3.png
     ┃ ┃ ┣ 📜WebhookGitLab1.png
     ┃ ┃ ┣ 📜WebhookGitLab2.png
     ┃ ┃ ┗ 📜WebhookGitLab3.png
     ┃ ┣ 📂webhook
     ┃ ┃ ┣ 📜webhookbutton.png
     ┃ ┃ ┣ 📜webhookregist.png
     ┃ ┃ ┗ 📜webhooks.png
     ┃ ┣ 📜btn_create.png
     ┃ ┣ 📜btn_delete.png
     ┃ ┣ 📜btn_dockercompose.png
     ┃ ┣ 📜btn_dockerfile.png
     ┃ ┣ 📜btn_guide.png
     ┃ ┣ 📜btn_main.png
     ┃ ┣ 📜btn_modify.png
     ┃ ┣ 📜btn_newpjt.png
     ┃ ┣ 📜btn_ningxconf.png
     ┃ ┣ 📜close.png
     ┃ ┣ 📜close2.png
     ┃ ┣ 📜copy.png
     ┃ ┣ 📜createIcon.png
     ┃ ┣ 📜deleteIcon.png
     ┃ ┣ 📜disable.png
     ┃ ┣ 📜djangoIcon.png
     ┃ ┣ 📜documentIcon.png
     ┃ ┣ 📜editIcon.png
     ┃ ┣ 📜fastapiIcon.png
     ┃ ┣ 📜github.png
     ┃ ┣ 📜gitlab.png
     ┃ ┣ 📜guideIcon.png
     ┃ ┣ 📜hide.png
     ┃ ┣ 📜homeIcon.png
     ┃ ┣ 📜homeIcon2.png
     ┃ ┣ 📜link.png
     ┃ ┣ 📜live.png
     ┃ ┣ 📜loading.json
     ┃ ┣ 📜logIcon.png
     ┃ ┣ 📜mascot.png
     ┃ ┣ 📜mongodbIcon.png
     ┃ ┣ 📜mysqlIcon.png
     ┃ ┣ 📜newpjtIcon.png
     ┃ ┣ 📜nopage.PNG
     ┃ ┣ 📜notFound.PNG
     ┃ ┣ 📜reactIcon.png
     ┃ ┣ 📜redisIcon.png
     ┃ ┣ 📜reload.png
     ┃ ┣ 📜rerun.png
     ┃ ┣ 📜restart.png
     ┃ ┣ 📜run.png
     ┃ ┣ 📜runloading.json
     ┃ ┣ 📜saveloading.json
     ┃ ┣ 📜settingIcon.png
     ┃ ┣ 📜settings.png
     ┃ ┣ 📜show.png
     ┃ ┣ 📜springIcon.png
     ┃ ┣ 📜stop.png
     ┃ ┣ 📜stoploading.json
     ┃ ┣ 📜togleButton.png
     ┃ ┣ 📜uploadIcon.png
     ┃ ┗ 📜vueIcon.png
     ┣ 📂components
     ┃ ┣ 📂common
     ┃ ┃ ┣ 📜Container.jsx
     ┃ ┃ ┣ 📜Container.module.css
     ┃ ┃ ┣ 📜DescBox.jsx
     ┃ ┃ ┣ 📜DescBox.module.css
     ┃ ┃ ┣ 📜FrameworkImg.jsx
     ┃ ┃ ┣ 📜FrameworkImg.module.css
     ┃ ┃ ┣ 📜GetBox.jsx
     ┃ ┃ ┣ 📜GetBox.module.css
     ┃ ┃ ┣ 📜GetCopyBox.jsx
     ┃ ┃ ┣ 📜GetCopyBox.module.css
     ┃ ┃ ┣ 📜GetLinkBox.jsx
     ┃ ┃ ┣ 📜GetLinkBox.module.css
     ┃ ┃ ┣ 📜GetRadioBox.jsx
     ┃ ┃ ┣ 📜GetRadioBox.module.css
     ┃ ┃ ┣ 📜GetSecretBox.jsx
     ┃ ┃ ┣ 📜GetSecretBox.module.css
     ┃ ┃ ┣ 📜GetToggleBox.jsx
     ┃ ┃ ┣ 📜GetToggleBox.module.css
     ┃ ┃ ┣ 📜InputBox.jsx
     ┃ ┃ ┣ 📜InputBox.module.css
     ┃ ┃ ┣ 📜InputSelectBox.jsx
     ┃ ┃ ┣ 📜InputSelectBox.module.css
     ┃ ┃ ┣ 📜LoadingAnimation.jsx
     ┃ ┃ ┣ 📜NavLeft.jsx
     ┃ ┃ ┣ 📜NavLeft.module.css
     ┃ ┃ ┣ 📜NavLeftCreate.jsx
     ┃ ┃ ┣ 📜NavLeftCreate.module.css
     ┃ ┃ ┣ 📜NavLeftGuide.jsx
     ┃ ┃ ┣ 📜NavLeftGuide.module.css
     ┃ ┃ ┣ 📜NavLeftUpdate.jsx
     ┃ ┃ ┣ 📜NavLeftUpdate.module.css
     ┃ ┃ ┣ 📜NavTop.jsx
     ┃ ┃ ┣ 📜NavTop.module.css
     ┃ ┃ ┣ 📜ProjectTop.jsx
     ┃ ┃ ┣ 📜ProjectTop.module.css
     ┃ ┃ ┣ 📜RadioBox.jsx
     ┃ ┃ ┣ 📜RadioBox.module.css
     ┃ ┃ ┣ 📜RunAnimaion.jsx
     ┃ ┃ ┣ 📜RunAnimaion.module.css
     ┃ ┃ ┣ 📜SaveAnimaion.jsx
     ┃ ┃ ┣ 📜StopAnimaion.jsx
     ┃ ┃ ┣ 📜ToggleBox.jsx
     ┃ ┃ ┗ 📜ToggleBox.module.css
     ┃ ┣ 📂create
     ┃ ┃ ┣ 📜BackendFrame.jsx
     ┃ ┃ ┣ 📜BackendFrame.module.css
     ┃ ┃ ┣ 📜DatabaseFrame.jsx
     ┃ ┃ ┣ 📜DatabaseFrame.module.css
     ┃ ┃ ┣ 📜FrontendFrame.jsx
     ┃ ┃ ┣ 📜FrontendFrame.module.css
     ┃ ┃ ┣ 📜ProjectFrame.jsx
     ┃ ┃ ┗ 📜ProjectFrame.module.css
     ┃ ┣ 📂guide
     ┃ ┃ ┣ 📜DobieFrame.jsx
     ┃ ┃ ┣ 📜DobieFrame.module.css
     ┃ ┃ ┣ 📜KeyFeatureFrame.module.css
     ┃ ┃ ┣ 📜KeyFeaturesFrame.jsx
     ┃ ┃ ┣ 📜RegistFrame.jsx
     ┃ ┃ ┣ 📜RegistFrame.module.css
     ┃ ┃ ┣ 📜RunStopFrame.jsx
     ┃ ┃ ┣ 📜RunStopFrame.module.css
     ┃ ┃ ┣ 📜SupportFrame.jsx
     ┃ ┃ ┗ 📜SupportFrame.module.css
     ┃ ┣ 📂main
     ┃ ┃ ┣ 📜ProjectItem.jsx
     ┃ ┃ ┣ 📜ProjectItem.module.css
     ┃ ┃ ┣ 📜ProjectList.jsx
     ┃ ┃ ┗ 📜ProjectList.module.css
     ┃ ┣ 📂manage
     ┃ ┃ ┣ 📜BackendFrame.jsx
     ┃ ┃ ┣ 📜BackendFrame.module.css
     ┃ ┃ ┣ 📜DatabaseFrame.jsx
     ┃ ┃ ┣ 📜DatabaseFrame.module.css
     ┃ ┃ ┣ 📜FileFrame.jsx
     ┃ ┃ ┣ 📜FileFrame.module.css
     ┃ ┃ ┣ 📜FrontendFrame.jsx
     ┃ ┃ ┣ 📜FrontendFrame.module.css
     ┃ ┃ ┣ 📜ProjectFrame.jsx
     ┃ ┃ ┣ 📜ProjectFrame.module.css
     ┃ ┃ ┣ 📜RunButton.jsx
     ┃ ┃ ┣ 📜RunButton.module.css
     ┃ ┃ ┣ 📜RunProjectItem.jsx
     ┃ ┃ ┣ 📜RunProjectItem.module.css
     ┃ ┃ ┣ 📜RunProjectList.jsx
     ┃ ┃ ┣ 📜RunProjectList.module.css
     ┃ ┃ ┣ 📜StopButton.jsx
     ┃ ┃ ┣ 📜StopButton.module.css
     ┃ ┃ ┣ 📜WebhookFrame.jsx
     ┃ ┃ ┗ 📜WebhookFrame.module.css
     ┃ ┣ 📂modal
     ┃ ┃ ┣ 📜LoadingModal.jsx
     ┃ ┃ ┣ 📜LoadingModal.module.css
     ┃ ┃ ┣ 📜LogModal.jsx
     ┃ ┃ ┣ 📜LogModal.module.css
     ┃ ┃ ┣ 📜Modal.jsx
     ┃ ┃ ┣ 📜Modal.module.css
     ┃ ┃ ┣ 📜NewModal.jsx
     ┃ ┃ ┗ 📜NewModal.module.css
     ┃ ┗ 📂update
     ┃ ┃ ┣ 📜BackendFrame.jsx
     ┃ ┃ ┣ 📜BackendFrame.module.css
     ┃ ┃ ┣ 📜DatabaseFrame.jsx
     ┃ ┃ ┣ 📜DatabaseFrame.module.css
     ┃ ┃ ┣ 📜FrontendFrame.jsx
     ┃ ┃ ┣ 📜FrontendFrame.module.css
     ┃ ┃ ┣ 📜ProjectFrame.jsx
     ┃ ┃ ┗ 📜ProjectFrame.module.css
     ┣ 📂pages
     ┃ ┣ 📂createPage
     ┃ ┃ ┣ 📜CreateBackendPage.jsx
     ┃ ┃ ┣ 📜CreateDatabasePage.jsx
     ┃ ┃ ┣ 📜CreateFrontendPage.jsx
     ┃ ┃ ┗ 📜CreateProjectPage.jsx
     ┃ ┣ 📂errorpage
     ┃ ┃ ┗ 📜ErrorPage.jsx
     ┃ ┣ 📂guidepage
     ┃ ┃ ┣ 📜GuidePage.jsx
     ┃ ┃ ┗ 📜GuidePage.module.css
     ┃ ┣ 📂mainpage
     ┃ ┃ ┣ 📜MainPage.jsx
     ┃ ┃ ┗ 📜MainPage.module.css
     ┃ ┣ 📂managepage
     ┃ ┃ ┣ 📜BackendPage.jsx
     ┃ ┃ ┣ 📜DatabasePage.jsx
     ┃ ┃ ┣ 📜FilePage.jsx
     ┃ ┃ ┣ 📜FrontendPage.jsx
     ┃ ┃ ┣ 📜ProjectPage.jsx
     ┃ ┃ ┣ 📜RunPage.jsx
     ┃ ┃ ┣ 📜RunPage.module.css
     ┃ ┃ ┗ 📜WebhookPage.jsx
     ┃ ┣ 📂startpage
     ┃ ┃ ┣ 📜LoginPage.jsx
     ┃ ┃ ┣ 📜LoginPage.module.css
     ┃ ┃ ┣ 📜SignUpPage.jsx
     ┃ ┃ ┣ 📜SignUpPage.module.css
     ┃ ┃ ┣ 📜WelcomePage.jsx
     ┃ ┃ ┗ 📜WelcomePage.module.css
     ┃ ┣ 📂updatePage
     ┃ ┃ ┣ 📜UpdateBackendPage.jsx
     ┃ ┃ ┣ 📜UpdateDatabasePage.jsx
     ┃ ┃ ┣ 📜UpdateFrontendPage.jsx
     ┃ ┃ ┗ 📜UpdateProjectPage.jsx
     ┃ ┣ 📜Test.js
     ┃ ┗ 📜TestPage.jsx
     ┣ 📂stores
     ┃ ┣ 📜fileStore.jsx
     ┃ ┣ 📜modalStore.jsx
     ┃ ┣ 📜projectStore.jsx
     ┃ ┗ 📜userStore.jsx
     ┣ 📜App.css
     ┣ 📜App.jsx
     ┣ 📜App.test.jsx
     ┣ 📜index.css
     ┣ 📜index.js
     ┣ 📜Main.jsx
     ┗ 📜setupTests.jsx
    ```
    
- Backend
    
    ```
    📦src
     ┣ 📂main
     ┃ ┣ 📂java
     ┃ ┃ ┗ 📂com
     ┃ ┃ ┃ ┗ 📂dobie
     ┃ ┃ ┃ ┃ ┗ 📂backend
     ┃ ┃ ┃ ┃ ┃ ┣ 📂config
     ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜JsonConfig.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MultipartJackson2HttpMessageConverter.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜SecurityConfig.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜SwaggerConfig.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜WebConfig.java
     ┃ ┃ ┃ ┃ ┃ ┣ 📂domain
     ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂docker
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PathTestController.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ProceedingContainerController.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dockercompose
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜DockerComposeService.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜DockerComposeServiceImpl.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dockerfile
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜DockerfileController.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜DockerContainerDto.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜DockerfileService.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜DockerfileServiceImpl.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂readjson
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ReadJsonService.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ReadJsonServiceImpl.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂nginx
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜NginxController.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜NginxConfigService.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜NginxConfigServiceImpl.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂project
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ProjectController.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂file
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜FileGetDto.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜FilePostDto.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜FilePutDto.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BackendGetResponseDto.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BackendRequestDto.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜DatabaseGetResponseDto.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜DatabaseRequestDto.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜FrontendGetResponseDto.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜FrontendRequestDto.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜GitGetResponseDto.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜GitRequestDto.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜NginxConfigDto.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜NginxProxyDto.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ProjectGetResponseDto.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ProjectRequestDto.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂entity
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Backend.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Database.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Frontend.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Git.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Project.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜SettingFile.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ProjectRepository.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ProjectService.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ProjectServiceImpl.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂user
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserController.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜LoginResponseDto.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserDto.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂entity
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜User.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserRepository.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜UserService.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserServiceImpl.java
     ┃ ┃ ┃ ┃ ┃ ┣ 📂exception
     ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂exception
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂build
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BackendBuildFailedException.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜DjangoBuildFailedException.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜DockerComposeCreateFailedException.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜FastApiBuildFailedException.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜FrontendBuildFailedException.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜GetSSLFailedException.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜NginxConfDeleteFailedException.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜NginxConfigNotFoundException.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜NginxCreateFailedException.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜NginxRestartFailedException.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜NginxStopFailedException.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ProjectPathNotFoundException.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ProjectStartFailedException.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ProjectStopFailedException.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ServiceStartFailedException.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ServiceStopFailedException.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜SSLCertificateIssueFailedException.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜SSLLogDeleteFailedException.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂docker
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜DockerPsErrorException.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜DockerPsLinePartsErrorException.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂Environment
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AnalyzeProjectContainerErrorException.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BackendFilePathNotExistException.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BackendFrameWorkNotFoundException.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BuildGradleNotFoundException.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ContainerLogNotFoundException.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CurrentStatusNotFoundException.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜DbInitNotFoundException.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜DbInitPathNotExistException.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜DjangoFilePathNotExistException.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜DockerComposeFileContentNotFoundException.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜DockerContainerFrameworkErrorException.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜DockerFileContentNotFoundException.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜FastApiFilePathNotExistException.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜FrontendFilePathNotExistException.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜FrontendFrameWorkNotFoundException.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜JsonToMapErrorException.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜makeDockerComposefilePathContentException.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜makeDockerfilePathContentException.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PackageJsonNotFoundException.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PomXmlNotFoundException.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PortNumberNotFoundException.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜RequirementsTxtNotFoundException.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜TypeErrorException.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂file
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜DeleteFileFailedException.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜NginxFileNotFoundException.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜SaveFileFailedException.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂git
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜GitCheckoutFailedException.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜GitCloneFailedException.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜GitInfoNotFoundException.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜GitPullFailedException.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂format
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂code
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ApiResponse.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂response
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ErrorCode.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ResponseCode.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂handler
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜GlobalExceptionHandler.java
     ┃ ┃ ┃ ┃ ┃ ┣ 📂security
     ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂entrypoint
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜JwtAuthenticationEntryPoint.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂filter
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜JwtAuthenticationFilter.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜RefreshTokenRequestFilter.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜TokenExceptionFilter.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂jwt
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜TokenInfo.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂entity
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜RefreshToken.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜RefreshTokenRepository.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜TokenManager.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜TokenService.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂response
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜FilterResponse.java
     ┃ ┃ ┃ ┃ ┃ ┣ 📂util
     ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂command
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CommandService.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜CommandServiceImpl.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂file
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜FileManager.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CookieUtil.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜FilterUtil.java
     ┃ ┃ ┃ ┃ ┃ ┗ 📜BackendApplication.java
     ┃ ┗ 📂resources
     ┃ ┃ ┣ 📂data
     ┃ ┃ ┃ ┣ 📜project.json
     ┃ ┃ ┃ ┣ 📜refreshToken.json
     ┃ ┃ ┃ ┗ 📜user.json
     ┃ ┃ ┣ 📜application-filter.yaml
     ┃ ┃ ┣ 📜application-jwt.yaml
     ┃ ┃ ┗ 📜application.yaml
     ┗ 📂test
     ┃ ┗ 📂java
     ┃ ┃ ┗ 📂com
     ┃ ┃ ┃ ┗ 📂dobie
     ┃ ┃ ┃ ┃ ┗ 📂backend
     ┃ ┃ ┃ ┃ ┃ ┗ 📜BackendApplicationTests.java
    ```
    

## **🚩** 주요 구현 기능

### 회원가입, 로그인 페이지

- 회원가입을 통해 자신의 프로젝트를 관리할 수 있습니다.
- 저장된 회원 데이터는 json파일  형식으로 저장됩니다.
- Spring Security + JWT 방식으로 구현되었습니다.

![Untitled](README%20md%20e79e283e946148e9b036ee7fa886d541/Untitled.gif)

### 메인페이지

- 등록된 모든 프로젝트의 프로젝트 명, 도메인 주소, git Link 정보를 확인할 수 있습니다.
- 프로젝트 별 실행 상태를 확인하고 빌드, 전체 실행, 전체 중지 기능을 실행할 수 있습니다.

![Untitled](README%20md%20e79e283e946148e9b036ee7fa886d541/Untitled%201.gif)

### 프로젝트 등록 페이지

- 새로운 프로젝트를 등록할 수 있습니다.
- named pipe를 사용하여 토글버튼을 통해 간편하게 https를 사용할 수 있도록 구현하였습니다.
- 입력 정보
    
    
    | Project | 프로젝트명, Domain Name, Git Type, Git Clone URL, Access Token, Branch, https 적용 여부 |
    | --- | --- |
    | Backend | 프레임워크, 언어버전, 폴더 경로, Nginx Location, 내부 포트 번호 |
    | Frontend | 프레임워크, 언어버전, 폴더 경로, Nginx Location, 내부 포트 번호 |
    | DB | 데이터베이스, Username, Password, 데이터베이스 명, 초기 데이터 파일 경로,
    외부 포트 번호, 내부 포트 번호 |

![Untitled](README%20md%20e79e283e946148e9b036ee7fa886d541/Untitled%202.gif)

### 가이드 페이지

- 도비를 쉽게 사용할 수 있도록 각 기능에 대한 가이드를 제시해 줍니다.

![Untitled](README%20md%20e79e283e946148e9b036ee7fa886d541/Untitled%203.gif)

### 프로젝트 수정 페이지

- 기존에 있던 프로젝트를 수정할 수 있습니다.
- 수정 후 빌드 버튼 클릭 시 빌드 파일들이 갱신 됩니다.

![Untitled](README%20md%20e79e283e946148e9b036ee7fa886d541/Untitled%204.gif)

### 프로젝트 상세조회 페이지

- 등록한 프로젝트의 상세정보를 조회할 수 있습니다.

**Run 페이지**

- 프로젝트의 전체 실행 및 중지상태를 파악할 수 있습니다.
- 프로젝트 내 각 컨테이너의 정보와 실행상태, 로그를 확인할 수 있습니다.
- 프로젝트의 docker-compose, docker, nginx config 파일을 확인할 수 있습니다.
- 프로젝트를 삭제할 수 있습니다.

![Untitled](README%20md%20e79e283e946148e9b036ee7fa886d541/Untitled%205.gif)

**Project, Backend, Frontend, DB**

- 프로젝트 등록 시 작성했던 프로젝트 관련 정보들을 확인할 수 있습니다.

![Untitled](README%20md%20e79e283e946148e9b036ee7fa886d541/Untitled%206.gif)

### WebHook설정 페이지

- 자동배포를 위해 WebHook을 설정할 수 있습니다.
- WebHook 설정을 위한 자세한 설명이 제시되어 있습니다.

![Untitled](README%20md%20e79e283e946148e9b036ee7fa886d541/Untitled%207.gif)

### 환경변수 관리 페이지

- .env, .yml 등 .gitignore에 포함되어 git에 등록되지 않는 환경파일들을 저장 및 삭제할 수 있습니다.
- 서버에 직접 파일을 옮기는 작업 대신 파일 첨부 기능으로 쉽게 환경변수 파일을 저장할 수 있습니다.

![Untitled](README%20md%20e79e283e946148e9b036ee7fa886d541/Untitled%208.gif)

## **🚩** 프로젝트 설치 및 기여 방법

[Contribute.md](Contribute.md)

## **🚩** 팀원 소개

| 고은석 | 박혜선 | 심규리 |
| --- | --- | --- |
| ![eun](./assets/eun.jpg) | ![seon](./assets/seon.png) | ![ri](./assets/ri.jpg) |
| BE :  JSON file I/O, 설치 shell script
FE : 프로젝트 생성 페이지
Infra: microk8s + Jenkins를 통한 배포용 이미지 생성 | BE : 도커 컴포즈 파일 생성, 환경변수 파일 업로드/삭제 API
FE : 프로젝트 디자인 및 css, 환경 변수 파일 페이지 | BE : Spring Security와 jwt 인증, 인가 구현, 로그인 API
FE : 프로젝트 수정 페이지, API 연결 |
| 오건영 | 유호정 | 이우진 |
| ![kun](./assets/kun.jpg) | ![ho](./assets/ho.PNG) | ![woo](./assets/woo.png) |
| BE : 리눅스 명령어 실행 API, Webhooks 활용 자동배포
FE : 가이드페이지 | BE:  Nginx Reverse Proxy 설정,
Nginx Frontend 웹 서버 설정, 
SSL인증서 발급 API
FE : 프로젝트 실행상태 조회, 프로젝트 상세조회 | BE : 도커파일 생성, 
컨테이너 상태 및 로그 조회 API,
도커파일, 도커컴포즈 파일 조회 API
FE : 컨테이너 로그 조회, 프로젝트 목록 페이지 css |
