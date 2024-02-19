# 使用OpenJDK 8基础镜像
FROM openjdk:8-jdk-alpine

# 安装git
RUN apk update && apk add --no-cache git maven

# 设置工作目录
WORKDIR /app

# 克隆代码到容器中
RUN git clone https://github.com/x1138633899/happy_tire.git . && \
    mvn clean package && pwd  && cd target && ls -ls

# 暴露应用程序的端口
EXPOSE 8080

# 设置启动命令，运行Spring Boot应用
CMD ["java", "-jar", "/app/target/happiness-1.0-SNAPSHOT.jar"]
