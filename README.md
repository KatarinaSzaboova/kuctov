# kuctov - jednoduché účtovníctvo

Príklad jednoduchej webovej aplikácie založenej na java mikro-frameworku: `Pippo`

## Požiadavky pre vývoj aplikácie
* [JDK 1.8](https://www.java.com/en/download/faq/develop.xml)
* [Docker](https://docs.docker.com/engine/installation/) (Nepovinné)
* Vhodné vývojové prostredie: [IntelliJ IDEA](https://www.jetbrains.com/idea/), [Eclipse](https://eclipse.org/downloads/), alebo [STS](https://spring.io/tools) (Nepovinné)

## Stiahnutie aplikácie
* Stiahneme kód aplikácie: `git clone https://github.com/KatarinaSzaboova/kuctov.git`

## Spustenie aplikácie
1. Presunieme sa do adresára aplikácie: `cd kuctov`
1. Vytvoríme aplikáciu: `./gradlew clean build`
1. Naštartujeme server: `java -jar build/libs/kuctov-1.0.jar`
1. Spustíme aplikáciu: [http://localhost:8080/](http://localhost:8080/)

