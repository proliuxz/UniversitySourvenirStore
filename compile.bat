CD %~dp0
CALL setenv.bat
SET CUR_DIR=%CD%
if not exist classes mkdir classes
CD ./src/sg/edu/nus/iss/usstore
javac -d %CUR_DIR%/classes -cp %CUR_DIR%/lib/jdatepicker-1.3.4.jar dao/*.java device/*.java domain/*.java exception/*.java gui/*.java util/*.java