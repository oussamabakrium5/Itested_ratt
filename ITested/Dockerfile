FROM openjdk
COPY src Itested
WORKDIR Itested
RUN mkdir -p bin
RUN javac -d bin ./com/itested/login/login.java
WORKDIR bin
CMD [ "java", "com.itested.login.login" ]