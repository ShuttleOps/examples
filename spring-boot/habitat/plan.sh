pkg_name=sample-spring-app
pkg_origin=shuttleops
pkg_maintainer="The ShuttleOps team support@shuttleops.io"
pkg_deps=(
  # the following two packages are frozen such that they both require the same version of glibc
  core/jre8/8.192.0 # Oracle's JRE8
  core/curl/7.65.3
)
pkg_build_deps=(
  core/maven
  core/jdk8 # Oracle's JDK8
  core/coreutils
  core/libxml2
)

DO_CHECK=true

pkg_version() {
  $(pkg_path_for core/libxml2)/bin/xmllint --xpath '/*[local-name()="project"]/*[local-name()="version"]/text()' "${PLAN_CONTEXT}"/../pom.xml
}

do_before() {
  do_default_before
  update_pkg_version
}

do_download() {
  return 0
}

do_unpack() {
  return 0
}

do_verify() {
  return 0
}

do_clean() {
  pushd "${PLAN_CONTEXT}"/..
  mvn clean
  popd
}

do_build() {
  pushd "${PLAN_CONTEXT}"/..
  mvn compile
  popd
}

do_check() {
  pushd "${PLAN_CONTEXT}"/..
  mvn test
  popd
}

do_install() {
  # Package application into JAR file
  pushd "${PLAN_CONTEXT}"/..
  mvn -DskipTests package spring-boot:repackage
  mvn -DskipTests verify
  popd

  # Copy JAR into package
  local jar_file_version="${pkg_name}-${pkg_version}.jar"
  local jar_file_no_version="${pkg_name}.jar"
  local build_target_path="${PLAN_CONTEXT}/../target"

  if [[ -f "${build_target_path}/${jar_file_version}" ]]; then
    cp ${build_target_path}/${jar_file_version} ${pkg_prefix}/${jar_file_no_version}
  elif [[ -f "${build_target_path}/${jar_file_no_version}" ]]; then
    cp ${build_target_path}/${jar_file_no_version} ${pkg_prefix}/${jar_file_no_version}
  else
    echo "Neither ${jar_file_version} or ${jar_file_no_version} could be found."
    return 1
  fi
}
