# kuctov - jednoduché účtovníctvo

Jednoduchá webová aplikácia vytvorená v jazyku Java

## Požiadavky pre vývoj aplikácie
* [JDK 1.8](https://www.java.com/en/download/faq/develop.xml)
* Vývojové prostredie: [IntelliJ IDEA](https://www.jetbrains.com/idea/), [Eclipse](https://eclipse.org/downloads/), [STS](https://spring.io/tools) apod. (Nepovinné)
* [Docker](https://docs.docker.com/engine/installation/) (Nepovinné)

## Stiahnutie kódu aplikácie z GitHub
* Stiahneme kód aplikácie: `git clone https://github.com/KatarinaSzaboova/kuctov.git`

## Spustenie aplikácie
1. Presunieme sa do adresára aplikácie: `cd kuctov`
1. Vytvoríme aplikáciu: `./gradlew clean build`
1. Naštartujeme server: `java -jar build/libs/kuctov-1.0.jar`
1. Spustíme aplikáciu: [http://localhost:8338/](http://localhost:8338/)

## Spustenie aplikácie v docker kontajneri
1. Vytvoríme aplikáciu: `./gradlew clean build`
1. Vytvoríme docker obraz: `docker build -t kuctov .`
1. Spustíme kontajner v ktorom beží server: `docker run -p 8338:8338 kuctov`
1. Spustíme aplikáciu: [http://localhost:8338/](http://localhost:8338/)

## Stiahnutie kontajneru aplikácie z DockerHub
* Stiahneme build aplikácie: `docker pull katarinaszaboova/kuctov`
* Spustíme aplikáciu: `docker run -p 8338:8338 katarinaszaboova/kuctov`