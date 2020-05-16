FROM openjdk:8-jre-alpine

# Create app directory
WORKDIR /usr/kuctov

COPY build/libs/kuctov-1.0.jar /usr/kuctov

# Expose http ports
EXPOSE 8338

# Start java application
CMD ["java","-jar","kuctov-1.0.jar"]
