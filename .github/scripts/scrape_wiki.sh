#!/bin/bash
set -o errexit
set -o nounset
set -o pipefail
# Takes a name for a scraper, and runs the corresponding main class
# associated with that scraper.

readonly TMP='tmp'
readonly RESOURCES='ui/src/main/resources'
readonly ROOT_PACKAGE='ddm.scraper'

readonly -A argMap=(
  ['skill-icons']="${ROOT_PACKAGE}.skillicons.Main images/skill-icons"
)

readonly -a params=(${argMap[$1]})
readonly mainClass="${params[0]}"
readonly relativeTarget="${params[1]}"
readonly tmpTarget="${TMP}/${relativeTarget}"
readonly target="${RESOURCES}/${relativeTarget}"

mkdir --verbose --parents "${tmpTarget}"
if [[ ! -d "${target}" ]]; then
  mkdir --verbose --parents "${target}"
fi

sbt "wikiScraper/runMain ${mainClass} ${tmpTarget}"
rsync --verbose \
      --recursive \
      --inplace \
      --times \
      --delete \
      "${tmpTarget}/" "${target}"