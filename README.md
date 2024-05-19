![dobie-banner](./assets/dobie-banner.PNG)

# Dobie 
> 초보자를 위한 인프라 서비스 플랫폼

Dobie는 배포를 처음 경험해보는 초보 개발자들이 많은 시간을 학습에 할애해야 한다는 문제를 인식했습니다. 이를 해결하기 위해, 처음 배포 환경을 구축하고 배포하는 시간을 획기적으로 줄여주고, 누구나 쉽게 사용할 수 있도록 설계했습니다. Dobie로 간편하고 신속한 배포 환경을 경험해보세요.

## Dobie 소개

- Dobie는 초보자도 쉽게 사용할 수 있는 인프라 서비스 플랫폼입니다.

- 사용자는 간단한 정보만을 입력하고 자신의 프로젝트를 배포하는 경험을 할 수 있습니다.

- Dobie는 Docker, Docker Compose를 활용하여 사용자의 프로젝트를 컨테이너로 관리합니다.

### 다양한 지원 환경
- **Backend**
    Spring Boot(maven), Spring Boot(gradle), Django, FastAPI
- **Frontend**
    React, Vue
- **Database**
    MySQL, MongoDB, Redis

### Https

- https 사용 버튼을 활성화 하면 쉽게 https 환경을 사용할 수 있습니다.

- Let's encrypt에서 SSL 인증서를 발급받아오는 과정을 자동화 하여 쉽게 https를 사용할 수 있습니다.

### 확장 가능한 오픈소스

- 
📚 **Skills**

- Backend 
<img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> <img src="https://img.shields.io/badge/springsecurity-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white">
- Frontend 
<img src="https://img.shields.io/badge/React-61DAFB?style=for-the-badge&logo=React&logoColor=white"> <img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=JavaScript&logoColor=white"> <img src="https://img.shields.io/badge/MUI-007FFF?style=for-the-badge&logo=MUI&logoColor=white">
- Infra 
<img src="https://img.shields.io/badge/amazonec2-FF9900?style=for-the-badge&logo=amazonec2&logoColor=white"> <img src="https://img.shields.io/badge/docker-2496ED?style=for-the-badge&logo=docker&lodockerr=white"> <img src="https://img.shields.io/badge/ubuntu-E95420?style=for-the-badge&logo=ubuntu&lodockerr=white"> <img src="https://img.shields.io/badge/nginx-009639?style=for-the-badge&logo=nginx&lodockerr=white">

## 사용자 가이드

1.  Dobie를 설치, 실행하기 위해 다음 스크립트를 실행하세요

```bash
# shell script 다운로드
wget https://raw.githubusercontent.com/eunnseok/dobie-deploy/main/install-dobie.sh
# shell script 실행 권한 부여
chmod +x [install-dobie.sh](http://install-dobie.sh/) 
# shell script 실행
sh install-dobie
```

2.  Dobie가 사용할 **3333포트**를 열어주세요
![1](./assets/1.png)

3.  domainName:3333 으로 접속해 Dobie를 시작해주세요
4.  Dobie에서 회원가입을 진행해주세요. 같은 프로젝트를 진행하는 팀원들과 한 계정을 공유해주세요.
![3](./assets/3.png)
5.  로그인 후 가이드 페이지에 따라 Dobie 사용을 진행해주세요
![2](./assets/2.png)


## Git Convention

### 브랜치 전략
- Git-flow 전략을 기반으로 main, develop 브랜치와 feature 보조 브랜치를 운용했습니다.
- main, develop, feature 브랜치로 나누어 개발을 하였습니다. 

**1. 기본 브랜치 전략**

- `main`브랜치: 안정적인 버전을 유지하는 브랜치로 배포 단계에서만 사용하는 브랜치입니다.
- `develop`브랜치:  개발 단계에서 git-flow의 master 역할을 하는 브랜치 입니다. `dev-be`와 `dev-fe`로 나뉘어 백엔드와 프론트엔드를 나누어 관리합니다. 이 브랜치는 주기적으로 `main`에 병합됩니다.

**2. 기능별 브랜치**

- 기능 브랜치 (`feature/`): 각 기능 단위로 독립적인 개발 환경을 위하여 사용하는 브랜치 입니다. 예를 들어, `feature/fe/login`, `feature/be/login`와 같이 명명할 수 있습니다. 개발이 완료되면 `dev-fe` 또는 `dev-be`에 병합합니다.
- 기능 키워드 작성은 'camel case'로 작성합니다.

### **커밋 메시지**

- 커밋 유형은 영어 대문자로 작성하기

| 커밋 유형 | 의미 |
| --- | --- |
| FEAT | 새로운 기능 추가 |
| FIX | 버그 수정 |
| DOCS | 문서 수정 |
| STYLE | 코드 formatting, 세미콜론 누락, 코드 자체의 변경이 없는 경우 |
| TEST | 테스트 코드, 리팩토링 테스트 코드 추가 |
| CHORE | 패키지 매니저 수정, 그 외 기타 수정 ex) .gitignore |
| DESIGN | CSS 등 사용자 UI 디자인 변경 |
| COMMENT | 필요한 주석 추가 및 변경 |
| RENAME | 파일 또는 폴더 명을 수정하거나 옮기는 작업만인 경우 |
| REMOVE | 파일을 삭제하는 작업만 수행한 경우 |
| !BREAKING CHANGE | 커다란 API 변경의 경우 |
| !HOTFIX | 급하게 치명적인 버그를 고쳐야 하는 경우 |
| REFACTOR | 기능의 변경 없이 코드의 내부 구조를 재정비하고 설계를 개선하는 데 초점. 종종 보다 규모가 큰 구조적 변경 포함. |
| REFINE | 코드의 가독성과 성능을 높이기 위한 세밀한 개선에 중점. 큰 구조적 변화 없이 전반적인 코드 품질과 유지 보수성 개선 |
- 커밋 유형 이후 본문은 한글로 작성하여 내용이 잘 전달될 수 있도록 할 것

## Contrubutor 사용 가이드

- Dobie를 사용하며 버그를 발견하거나 프로젝트에 기여하고자 하는 분들은 가이드를 참고해주세요

### 기여하기 전
- Dobie에 관한 모든 작업은 GitHub를 통해 전달됩니다.
- Dobie는 버그를 GitHub Issue를 통해 관리하고 있습니다. 새로운 버그를 등록하기 전에 이미 논의되고 있는 버그인지 먼저 확인해주세요.

### Issue를 보고하는 방법
Git Issue를 통해 자유롭게 문제를 제출하세요. GitHub bug 탬플릿을 준수하고 다음과 같은 내용을 포함하여 등록해주세요.

[Issue Lable](https://lab.ssafy.com/s10-final/S10P31B101/-/labels)을 참고하여 이슈를 제출해주세요

1. Issue 요약
2. Issue가 발생한 단계
3. Issue가 발생한 Dobie의 구성 또는 서비스
