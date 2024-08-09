#!/bin/bash

# Script to initialise project by executing steps as follows:
#   - Replace port number
#   - Replace package `spring-boot-app-template`
#   - Replace app name from `spring-boot-app-template`
#   - Clean-up README file from template related info
#   - Self-destruct

export LANGUAGE=en_US.UTF-8
export LANG=en_US.UTF-8
export LC_ALL=en_US.UTF-8

read -rp "Port number for new app (press Enter for default 8080): " port
if [ -z "$port" ]
then
      port="8080"
fi

read -rp "Replace application name with: " component_name
read -rp "Replace \`os.apptemplate\` package name with: " package

pushd "$(dirname "$0")"/.. > /dev/null || exit 1

declare -a files_with_port=(
  "README.md"
  "./os-app-template/Dockerfile"
  "./os-app-template/src/main/resources/application.yml"
)

declare -a files_with_slug=(
  "build.gradle.kts"
  "settings.gradle.kts"
  "README.md"
  "docker-compose.yml"
  "./buildSrc/src/main/groovy/os-app-template.publishing-conventions.gradle"
  "./os-app-template/build.gradle.kts"
  "./os-app-template/Dockerfile"
  "./os-app-template/src/main/kotlin/com/sumup/os/apptemplate/controllers/RootController.kt"
  "./.github/workflows/linting.yml"
  "./.github/workflows/pull_request.yml"
  "./.github/workflows/release.yml"
)

declare -a subdirectories_to_rename_package=(
  "main"
  "test"
)

declare -a headers_to_delete=(
  "Purpose"
  "What's inside"
  "Setup"
)


# Replace port number
for i in "${files_with_port[@]}"
do
  perl -i -pe "s/8080/$port/g" "${i}"
done

# Replace spring-boot-template slug
for i in "${files_with_slug[@]}"
do
  perl -i -pe "s/os-app-template/$component_name/g" "${i}"
done

# Replace demo package in all files under ./src
find ./os-app-template/src -type f -print0 | xargs -0 perl -i -pe "s/os.apptemplate/os.$package/g"
find ./os-app-template/src -type f -print0 | xargs -0 perl -i -pe "s/os-app-template/$component_name/g"
perl -i -pe "s/os.os-app-template/os.$package/g" build.gradle.kts
perl -i -pe "s/os.apptemplate/os.$package/g" build.gradle.kts
perl -i -pe "s/os.os-app-template/os.$package/g" ./os-app-template/build.gradle.kts
perl -i -pe "s/os.apptemplate/os.$package/g" ./os-app-template/build.gradle.kts
perl -i -pe "s/#CI//g" .github/workflows/pull_request.yml

# Rename directory to provided package name

rm -rf "${component_name}"

for dir in "${subdirectories_to_rename_package[@]}"
do
  git mv "./os-app-template/src/${dir}/kotlin/com/sumup/os/apptemplate" "./os-app-template/src/${dir}/kotlin/com/sumup/os/${package}"
done

git mv "./os-app-template" "${component_name}"

# Rename buildSrc files

find . -type f -name "os-app-template*" -not -path "**/build/**" |
sed 's/\(.*\)\(os-app-template\)\(.*\)/git mv "\1\2\3" "\1'"${component_name}"'\3"/g' |
sh

# Clean-up README file
for i in "${headers_to_delete[@]}"
do
  perl -0777 -i -p0e "s/## $i.+?\n(## )/\$1/s" README.md
done

# Rename title to slug
perl -i -pe "s/.*\n/# $component_name\n/g if 1 .. 1" README.md

# Self-destruct
echo  "Self destroy in 3... 2... 1..."
rm bin/init.sh

# Return to original directory
popd > /dev/null || exit 1