FROM eclipse-temurin:21-jre-alpine
LABEL org.opencontainers.image.source=https://github.com/sumup/os-app-template/os-app-template

ENV APP_BASE="/home/sumup" \
    APP_NAME="os-app-template" \
    JAVA_OPTS="" \
    OTEL_ATTRIBUTES="sumup.org.tribe=OnlineStore,sumup.org.squad=OsOperate" \
    # service.name in Honeycomb
    OTEL_SERVICE_NAME="os-app-template" \
    SERVER_PORT="8080"

EXPOSE ${SERVER_PORT}

RUN apk update && apk upgrade && apk add curl openssl gcompat
# GRPC crash https://github.com/grpc/grpc-java/issues/8751
ENV LD_PRELOAD=/lib/libgcompat.so.0

RUN mkdir -p ${APP_BASE}/${APP_NAME}

# Otel agent
COPY "${APP_NAME}/build/output/libs" "${APP_BASE}/${APP_NAME}"

COPY ${APP_NAME}/build/libs/${APP_NAME}*.jar ${APP_BASE}/${APP_NAME}.jar

RUN echo ${SERVICE_IMAGE} > ${APP_BASE}/service_image.txt

CMD java $JAVA_OPTS \
    -Dotel.instrumentation.kafka.experimental-span-attributes=true \
    -Dotel.resource.attributes="${OTEL_ATTRIBUTES}" \
    -Dspring.profiles.active=${ENVIRONMENT} \
    -javaagent:${APP_BASE}/${APP_NAME}/opentelemetry-javaagent.jar \
    -jar ${APP_BASE}/${APP_NAME}.jar
