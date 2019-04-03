# Perpule intern-ship problem statement web API Source Code
### This is a gradle project made using jersey library

I generated the project using the python [program](https://gist.github.com/joeydash/dd465a8892817edd3a6f7d05c5eff1a7) created by me

Documentation [here](https://documenter.getpostman.com/view/4150963/collection/RVuACnEb)

Postman collection [here](https://www.getpostman.com/collections/5f2b950549e5f11e310c)

Source code [here](https://github.com/joeydash/perpule_api.git)

Here is the schema of database

![schema](https://preview.ibb.co/eFBzcS/schema.png)

* after every edit you need to build the project
```bash
gradle build --info
```
* now host it using jetty runner
```bash
java -jar jetty-runner-8.1.9.v20130131.jar --port 9000 build/libs/accomox_api.war
```

* build and run
```bash
gradle build && java -jar jetty-runner-8.1.9.v20130131.jar --port 9000 build/libs/accomox_api.war
```

* ignore if you are not using docker
```bash
docker build . -t ae16b111/accomox_api

docker run -it --rm -p 3000:9000 ae16b111/accomox_api
```
