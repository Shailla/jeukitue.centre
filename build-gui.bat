cd centre-gui
call ng build
cd ..
rd centre-boot\src\main\resources\public\gui /Q /S
xcopy centre-gui\dist\* centre-boot\src\main\resources\public\gui\ /S