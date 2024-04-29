# Prerequisites
- openjdk version 17+
- Installed Docker
- Optional: Installed IntelliJ IDEA

# Github
Clone Backend project via: `git clone https://github.com/monkey-tw/BookServer.git`

# Run Application In AWS
The application is running in AWS.The public IP is http://3.27.26.5 . 

eg: You can access book list page via: `http://3.27.26.5:8080/books`

# Run Backend project locally with Docker
- Open terminal and run: `cd BookServer`
- run `./build_and_run.sh`. (The build_and_run.sh script will detect if Docker is installed or not. If Docker is installed, it will run `docker run` to start the application. If Docker is not installed, it will run `java  -jar book-server-0.0.1-SNAPSHOT.jar` to start the application.)
- Run: `chmod +x ./build_and_run.sh` if you get permission denied error

## Optional: Run Backend project via IntelliJ IDEA
- In case there is any error when run `build_and_run.sh`, you can try this.
- Open BookServer project via IntelliJ IDEA
- Click `Run` button in the top menu, then click `Run 'BookServerApplication'`

# Achitecture
- Based on Spring MVC
- Based on H2 database.
- Unit tests with JUnit

# Test with BookApp
Refer to [BookApp](https://github.com/monkey-tw/BookApp.git) to run BookApp in simulator.
