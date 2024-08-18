# archetypes

| **Type** | **Status**                                                                                                                                                             |
|----------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Release  | [![Release](https://github.com/sumup/archetypes/actions/workflows/release.yml/badge.svg)](https://github.com/sumup/archetypes/actions/workflows/release.yml) |

## Getting Started

These instructions will get you a copy of the project up and running on your
local machine for development and testing purposes. See deployment for notes on
how to deploy the project on a live system.

### Prerequisites

To run the project you need to install the following:

- JDK 17 or newer
- Docker

### Building the application

The project uses [Gradle](https://gradle.org) as a build tool. It already contains
`./gradlew` wrapper script, so there's no need to install gradle.

To build the project execute the following command:

```shell
  ./gradlew build
```

### Running the application

Create the image of the application by executing the following command:

```shell
  ./gradlew assemble
```

You can run this project directly from Gradle by executing the following
command:

```shell
./gradlew bootRun
```

Otherwise, you can create docker image:

```shell
  docker-compose build
```

For Apple M1 processor run the following instead:

```shell
DOCKER_BUILDKIT=0 docker-compose build
```

Run the distribution (created in `archetypes/build/install/archetypes`
directory) by executing the following command:

```shell
  docker-compose up && ./gradlew bootRun
```

This will start the API container exposing the application's port
(set to `8080` in this app).
