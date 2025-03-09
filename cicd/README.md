# Install jenkins in Windows
> https://www.jenkins.io/doc/book/installing/windows/

# Install jenkins in Linux
> https://www.jenkins.io/doc/book/installing/linux/

# Install jenkins in MacOS
> https://www.jenkins.io/doc/book/installing/macos/

# Install jenkins in Docker
https://www.jenkins.io/doc/book/installing/docker/

## Run container
> docker run -d --name cicd-jenkins -v jenkins_home:/var/jenkins_home -v /var/run/docker.sock:/var/run/docker.sock -p 8080:8080 -p 50000:50000 --restart=on-failure jenkins/jenkins:lts-jdk17

OR

> docker run -d --name cicd-jenkins -v jenkins_home:/var/jenkins_home -v /var/run/docker.sock:/var/run/docker.sock -p 8080:8080 -p 50000:50000 --user root --entrypoint "/bin/bash" --restart=on-failure jenkins/jenkins:lts-jdk17 -c "apt update && apt install -y docker.io && /usr/local/bin/jenkins.sh"

## Get key
> docker exec cicd-jenkins cat /var/jenkins_home/secrets/initialAdminPassword