FROM java:8

MAINTAINER alois.pin@softeam.fr

WORKDIR /apps/evenement-parcours-integration

COPY target/evenement-parcours-integration.jar /apps/evenement-parcours-integration/evenement-parcours-integration.jar

EXPOSE 8080

CMD java -jar evenement-parcours-integration.jar