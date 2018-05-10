# glowing-waffle

## Cloning Repository:
  git clone https://github.com/guilhermebotossi/glowing-waffle.git

## Java
  -Version 1.8<br>
  -The JAVA_HOME enviroment variable, must be defined<br>
    -If not, please follow the directions, in the URL below, for windows and Linux<br>
    https://www.jukuko.com/set-java_home-path-variables-windows-linux-bash-profile/<br>

## To run Application
  Navigate to the folder where you cloned the repository and enter into the folder "boleto-api".
  
  ### Linux flavors:
    ./gradlew bootRun
  ### Windows 
    gradlew.bat bootRun

# Access the Controllers through swagger
http://localhost:8080/swagger-ui.html

# Endpoints :

Mapped "{[/rest/bankslips/{id}/cancel],methods=[DELETE]}"<br>
Mapped "{[/rest/bankslips],methods=[GET]}"<br>
Mapped "{[/rest/bankslips/{id}],methods=[GET]}"<br>
Mapped "{[/rest/bankslips/{id}/pay],methods=[PUT]}"<br>
Mapped "{[/rest/bankslips],methods=[POST]}"<br>
