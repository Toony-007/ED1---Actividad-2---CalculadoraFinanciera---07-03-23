@echo off
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
REM Universidad de los Andes (Bogot� - Colombia)
REM Departamento de Ingenier�a de Sistemas y Computaci�n 
REM Licenciado bajo el esquema Academic Free License version 2.1 
REM
REM Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
REM Ejercicio: n8_calculadoraFinanciera
REM Autor: Juan Camilo Cort�s Medina - 27-ago-2008
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

REM ---------------------------------------------------------
REM Ejecuci�n de las pruebas
REM ---------------------------------------------------------

cd..
java -ea -classpath ./lib/calculadoraFinanciera.jar;./test/lib/calculadoraFinancieraTest.jar;./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.calculadoraFinanciera.test.CalculadoraFinancieraTest
java -ea -classpath ./lib/calculadoraFinanciera.jar;./test/lib/calculadoraFinancieraTest.jar;./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.calculadoraFinanciera.test.CreditoTest
java -ea -classpath ./lib/calculadoraFinanciera.jar;./test/lib/calculadoraFinancieraTest.jar;./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.calculadoraFinanciera.test.MesAmortizacionTest
cd bin