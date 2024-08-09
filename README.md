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

In order to test if the application is up, you can call its health endpoint:

```shell
  curl http://localhost:8080/actuator/health
```

You should get a response similar to this:

```json
  {
  "status": "UP",
  "diskSpace": {
    "status": "UP",
    "total": 249644974080,
    "free": 137188298752,
    "threshold": 10485760
  }
}
```

### Alternative script to run application

To skip all the setting up and building, just execute the following command:

```shell
./bin/run-in-docker.sh
```

For more information:

```shell
./bin/run-in-docker.sh --help
```

## Running the tests

You can run the project tests via Gradle by executing the following command:

```shell
./gradlew test
```

### And coding style tests

This project uses [Spotless Gradle plugin](https://github.com/diffplug/spotless)
to enforce its code style. The plugin will run automatically after every
successful build, test, and assemble stage. However, if you would like to run
it manually you can do so by running the following commands:

To apply the code style to the project run:

```shell
./gradlew spotlessApply
```

To check your code without applying any changes you can execute:

```shell
./gradlew spotlessCheck
```

## Api Documentation

The service exposes it's API specification in the following URL:
[swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html).

You can also fetch the information in JSON ([api-docs](http://localhost:8080/api-docs/))
or YAML ([api-docs.yaml](http://localhost:8080/api-docs.yaml)) formats.

The API requires the `x-sumup-authorization` header which have the following
structure:

```text
account_type=<ACCOUNT_TYPE>&userid=<USER_ID>&merchant_code=<MERCHANT_CODE>&merchant_id=<MERCHANT_ID>
```

for example:

```text
account_type=normal&userid=28&merchant_code=MCXDUFTH&merchant_id=28
```

The `userId`, `merchant_id` could be found in SOAP, `poslite` database and the
`platform` database in the tables `merchants` and `users`.

the `account_type` can contain only 2 values:

- NORMAL (main account)
- OPERATOR (sub-account)

## Plugins

To read more about the plugins included in this project click
[here](docs/plugins.md).

## Built With

- [OpenJdk 17](https://openjdk.java.net/projects/jdk/17/)
- [Kotlin](https://kotlinlang.org/)
- [SpringBoot](https://spring.io/projects/spring-boot) - The web framework used
- [Gradle](https://gradle.org/) - Dependency Management
- [GitHub Actions](https://docs.github.com/en/actions) - Continuous Integration
- [Docker](https://www.docker.com/) - Container handling

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available,
see the [tags on this repository](https://github.com/your/project/tags).

## Deploy

In order to allow autodeploy we need create and add bot to auto deploy group.
[Example](https://github.com/sumup/github-settings/pull/2014)
