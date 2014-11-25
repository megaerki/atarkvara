@ECHO OFF

REM Üritame kasutada Windows cmd, et projekt üles pushida

SET USERNAME=%1
SET PASSWORD=%2

git add --all .

git commit -m "%3"

git push https://%USERNAME%:%PASSWORD%@github.com/megaerki/atarkvara.git