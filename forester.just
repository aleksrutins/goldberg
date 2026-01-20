mod forester

build-forest path:
    forester build {{ path }}/forest.toml

[script]
transform dir:
    echo -e "\033[1mtransform {{ dir }}\033[0m"
    for f in $(find "{{ dir }}/" -name "*.xml"); do
        xsltproc "{{ dir }}/default.xsl" "$f" > "${f%.xml}.html"
        rm "$f"
    done

    for f in $(find "{{ dir }}/" -name "*.xsl"); do
        rm "$f"
    done
