
# Docker 启动SpringBoot项目


## 搭建SpringBoot项目

使用Idea或者在 https://start.spring.io/ 构建一个spring-boot-web项目

添加入下接口：
```java

@RestController
public class HelloController {

    @GetMapping("hello")
    public String hello() {
        return "hello";
    }
}

```
添加接口测试（使用rest client）

```http
### hello
GET http://localhost:8080/hello
Accept: application/json

```
目前项目都是默认配置，端口为8080，启动项目，使用接口测试访问接口


```http
GET http://localhost:8080/hello

HTTP/1.1 200 
Content-Type: application/json
Content-Length: 5
Date: Tue, 26 Oct 2021 08:39:11 GMT
Keep-Alive: timeout=60
Connection: keep-alive

hello

Response code: 200; Time: 744ms; Content length: 5 bytes

```
到此处，spring项目没有问题

## 构建docker镜像
构建java镜像，官方指南如下：
https://docs.docker.com/language/java/run-containers/
我没有成功
如下演示实际操作成功案例：使用maven-docker插件

```xml

        <plugin>
                <groupId>com.spotify</groupId>
                <artifactId>docker-maven-plugin</artifactId>
                <version>1.0.0</version>
                <!--将插件绑定在某个phase执行-->
                <executions>
                    <execution>
                        <id>build-image</id>
                        <!--用户只需执行mvn package ，就会自动执行mvn docker:build-->
                        <phase>package</phase>
                        <goals>
                            <goal>build</goal>
                        </goals>
                    </execution>
                </executions>

                <configuration>
                    <!--指定生成的镜像名,这里是我们的作者名+项目名-->
                    <imageName>cainiao/${project.artifactId}</imageName>

                    <!--指定标签 这里指定的是镜像的版本，我们默认版本是latest-->
                    <imageTags>
                        <imageTag>latest</imageTag>
                    </imageTags>

                    <!--指定基础镜像jdk1.8-->
                    <baseImage>java</baseImage>
                    <!--
                    镜像制作人本人信息
                    <maintainer>bruceliu@email.com</maintainer>
                    -->
                    <!--切换到ROOT目录-->
                    <workdir>/ROOT</workdir>

                    <!--查看我们的java版本-->
                    <cmd>["java", "-version"]</cmd>

                    <!--${project.build.finalName}.jar是打包后生成的jar包的名字-->
                    <entryPoint>["java", "-jar", "/${project.build.finalName}.jar"]</entryPoint>

                    <!--指定远程 docker api地址-->
<!--                    <dockerHost>http://192.168.29.133:2375</dockerHost>-->

                    <!-- 这里是复制 jar 包到 docker 容器指定目录配置 -->
                    <resources>
                        <resource>
                            <targetPath>/</targetPath>
                            <!--jar 包所在的路径  此处配置的 即对应 target 目录-->
                            <directory>${project.build.directory}</directory>
                            <!--用于指定需要复制的文件 需要包含的 jar包 ，这里对应的是 Dockerfile中添加的文件名　-->
                            <include>${project.build.finalName}.jar</include>
                        </resource>
                    </resources>

                </configuration>
            </plugin>
```

除此之外，新增Dockerfile .dockerignore文件

新增Dockerfile

```dockerfile
# syntax=docker/dockerfile:1

FROM openjdk:8

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline

COPY src ./src

CMD ["./mvnw", "spring-boot:run"]

```

使用maven打包即可生成docker镜像
```shell
mvn package
```
查看镜像
```shell
docker images
```
如下：
```shell
REPOSITORY                       TAG       IMAGE ID       CREATED             SIZE
ag                               latest    98709edc331a   29 minutes ago      654MB
cainiao/spring-web-docker-demo   latest    429daa781b90   About an hour ago   664MB
<none>                           <none>    0650b50e0465   About an hour ago   638MB
java                             latest    d23bdf5b1b1b   4 years ago         643MB
```

## 启动镜像

使用如下命令，将容器8080端口映射到宿主机端口8080
```shell
docker run --publish 8080:8080 cainiao/spring-web-docker-demo

```
使用如下命令，将容器8080端口映射到宿主机端口8081
```shell
docker run --publish 8081:8080 cainiao/spring-web-docker-demo

```
使用rest client访问

```http request
GET http://localhost:8081/hello

HTTP/1.1 200 
Content-Type: application/json
Content-Length: 5
Date: Tue, 26 Oct 2021 08:51:06 GMT
Keep-Alive: timeout=60
Connection: keep-alive

hello

Response code: 200; Time: 7313ms; Content length: 5 bytes
```

