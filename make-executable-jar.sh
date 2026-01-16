#!/usr/bin/env bash

JAVA_OPTS="--sun-misc-unsafe-memory-access=allow --enable-native-access=ALL-UNNAMED"

cat > goldberg <<EOF
@ 2>/dev/null # 2>nul & echo off & goto BOF
:
exec java $JAVA_OPTS  \$JAVA_OPTS -cp "\$0" 'com.farthergate.AppKt' "$@"
exit

:BOF
setlocal
@echo off
java $JAVA_OPTS %JAVA_OPTS% -cp "%~dpnx0" com.farthergate.AppKt %*
endlocal
exit /B %errorlevel%
EOF

cat target/goldberg-*.jar >> goldberg
chmod +x goldberg