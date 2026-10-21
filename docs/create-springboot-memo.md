# Spring Boot最小構成プロジェクト
## Spring Bootの最小構成プロジェクトを作成する手順を示す.

## 項目
- パターン1　※素直に動作できた.　
- パターン2  ※mvn spring-boot:runでエラーがでる. 

## パターン1
Linux上で Spring Boot最小構成プロジェクトをコマンドだけで作る ならこんな感じです。

### プロジェクト作成
```text
mkdir -p spring-sample/src/main/java/com/example/demo  
cd spring-sample
```
### pom.xml作成
```text
cat > pom.xml <<'EOF'
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
         https://maven.apache.org/xsd/maven-4.0.0.xsd">

    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.5.6</version>
    </parent>

    <groupId>com.example</groupId>
    <artifactId>demo</artifactId>
    <version>0.0.1-SNAPSHOT</version>

    <properties>
        <java.version>17</java.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>
EOF
```

### メインクラス作成
```java
cat > src/main/java/com/example/demo/DemoApplication.java <<'EOF'
package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
EOF
```

### Controller作成  
```java
cat > src/main/java/com/example/demo/HelloController.java <<'EOF'  
package com.example.demo;  

import org.springframework.web.bind.annotation.GetMapping;  
import org.springframework.web.bind.annotation.RestController;  

@RestController  
public class HelloController {  

    @GetMapping("/hello")  
    public String hello() {  
        return "Hello Spring Boot!";  
    }  

}
EOF
```

### 起動
mvn spring-boot:run  

### 動作確認  
別ターミナルで  
curl http://localhost:8080/hello  

### 結果  
Hello Spring Boot!  

### ディレクトリ構成確認  
```text
表示例  
spring-sample 
├── pom.xml
└── src
    └── main
        └── java
            └── com
                └── example
                    └── demo
                        ├── DemoApplication.java
                        └── HelloController.java
```
これが Spring Boot の学習用としてはほぼ最小構成です。  

## パターン2 
Spring Boot の学習用なら、Linuxに Java + Maven + Spring Boot を入れれば十分です。  
### 1. Java確認
```text
java -version
```  
入っていなければ（Ubuntu系）
```text  
sudo apt update  
sudo apt install openjdk-17-jdk
```  
確認
```text
java -version  
```

### 2. Mavenインストール
```text
mvn -version
```  
入っていなければ
```text
sudo apt install maven
```  
確認
```text  
mvn -version
```

### 3. Spring Bootプロジェクト作成
```text
mkdir spring-sample  
cd spring-sample  
```
Spring Initializrで作成したプロジェクトを配置するか、Gitから取得します。 例  
```text
git clone https://github.com/spring-projects/spring-petclinic.git  
```

### 4. 起動  
プロジェクト直下で  
- ./mvnw spring-boot:run  ※これは動作できなかった.
- mvn spring-boot:run  ※ここでエラー情報がトレースされたことを確認した.

### 5. 動作確認
- ブラウザ:http://localhost:8080
- コマンド：curl http://localhost:8080  

### 6. Jar化
```text
mvn clean package  
```
作成物
```text  
target/*.jar
```  
実行
```text  
java -jar target/app.jar  
```

## Ansibleでやるなら
```text 
- hosts: localhost
  become: yes

  tasks:
    - name: Java導入
      apt:
        name: openjdk-17-jdk
        state: present

    - name: Maven導入
      apt:
        name: maven
        state: present
```

まずは  
- Java 17導入  
- Maven導入  
- Spring Bootプロジェクト作成  
- mvn spring-boot:run  
を行うと、spring bootの最小構成のプロジェクトが作成できる.

参考:AI先生  

## memo
- 次に Spring Boot + PostgreSQL接続 をやると、いいらしい.
- 
- 
