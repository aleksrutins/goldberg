#!/usr/bin/env bash

for f in $(find "$1/" -name "*.xml"); do
    xsltproc "$1/default.xsl" "$f" > "${f%.xml}.html"
    rm "$f"
done

for f in $(find "$1/" -name "*.xsl"); do
    rm "$f"
done