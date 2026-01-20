mod forester-theme

version := "0.2.2"
url := f'https://git.sr.ht/~aleksrutins/forester-theme/refs/download/{{ version }}/forester-theme-{{ version }}.tar.gz'

install:
    mkdir -p theme
    wget -O theme.tar.gz {{ url }}
    tar xzf theme.tar.gz -C theme
