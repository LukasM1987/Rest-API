call runcrud.bat
if "%ERRORLEVEL%" == "0" goto run
goto fail

:run
start chrome "http://localhost:8080/crud/v1/task/getTasks"
if "%ERRORLEVEL%" == "0" goto end
echo.
echo Cannot opening the website
goto fail

:fail
echo.
echo There were errors

:end
echo.
echo showtasks.bat work is finished