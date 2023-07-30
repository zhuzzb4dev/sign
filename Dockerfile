FROM openjdk:8-jre-alpine
MAINTAINER zhuzzb zhu_zhibin@outlook.com
#定义时区参数
ENV TZ=Asia/Shanghai

#设置时区
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo '$TZ' > /etc/timezone
EXPOSE 7000
COPY target/sign-0.0.1-SNAPSHOT.jar app.jar
ENV CRON="4 5 7 * * ?"
ENTRYPOINT ["sh", "-c", "java -Dtask.sign.cron=\"$CRON\" -jar /app.jar"]